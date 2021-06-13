package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.controller.ModelObserver;
import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.match_items.DevelopmentGrid;
import it.polimi.ingsw.model.match_items.LeadersList;
import it.polimi.ingsw.model.match_items.MarketDirection;
import it.polimi.ingsw.model.match_items.Marketplace;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static it.polimi.ingsw.controller.match.MatchPhase.*;

public class Match {

    private ArrayList<Player> players = new ArrayList<>();
    private Player activePlayer;
    private DevelopmentGrid developmentGrid = new DevelopmentGrid();
    private Marketplace marketplace = new Marketplace();
    private LeadersList leadersList = new LeadersList();
    private MatchPhase phase = LOBBY;
    private HashMap<String, BiConsumer<Player, String[]>> commands = new HashMap<>();
    private ModelObserver modelObserver = new ModelObserver();
    private boolean isSinglePlayer;
    private MatchManager matchManager;
    private String matchId;


    Match(Player leader, boolean isSinglePlayer, MatchManager matchManager, String matchId) throws MatchException {
        this.matchManager = matchManager;
        this.matchId = matchId;

        marketplace.attach(modelObserver);
        developmentGrid.attach(modelObserver);

        this.isSinglePlayer = isSinglePlayer;

        setDefaultCommands();

        activePlayer = leader;
        addPlayer(leader);
    }



    Match(String status, MatchManager matchManager, String matchId) {

        this.matchManager = matchManager;
        this.matchId = matchId;
        setDefaultCommands();

        isSinglePlayer = Boolean.parseBoolean(status.split(" xXx ")[1]); //type of match

        //restore match items
        developmentGrid = new DevelopmentGrid(Arrays.asList(status.split(" xXx ")[4].split(", ")).stream().mapToInt(Integer::parseInt).toArray());
        marketplace = new Marketplace(Arrays.asList(status.split(" xXx ")[5].split(", ")).stream().mapToInt(Integer::parseInt).toArray());

        developmentGrid.attach(modelObserver);
        marketplace.attach(modelObserver);

        String[] playersNames = status.split(" xXx ")[2].split(" ");
        String[] playersStati = Arrays.copyOfRange(status.split(" xXx "), 6, 6 + playersNames.length);

        //restore players
        for(int i = 0; i < playersNames.length; ++i) {
            try {
                Player p = new Player(playersNames[i], this, i, playersStati[i], marketplace, developmentGrid, isSinglePlayer, leadersList);
                players.add(p);
                modelObserver.attachTo(p);
            } catch (Exception e){
                endMatch();
                return;
            }
        }
        activePlayer = players.get(Integer.parseInt(status.split(" xXx ")[3]));

        phase = TURN_START; //it's always saved at the beginning of a turn

        System.out.print("\nMatch " + matchId + " restored");
    }




    synchronized void addPlayer(Player p) throws MatchException {

        //if it's a single player match and there is someone connected or a different player tries to connect, throw the error
        if (players.size() != 0 && isSinglePlayer && (players.stream().anyMatch(plr -> plr.isConnected()) || !p.getName().equals(players.get(0).getName()))){
            throw new MatchException("This is a single player match");
        }

        if(players.stream().anyMatch(player -> player.getName().equals(p.getName()) && !player.isConnected())){
            //in this case there is a player with the same name who disconnected, so you can replace him
            Player replacingPlayer = players.stream().filter(player -> player.getName().equals(p.getName()) && !player.isConnected()).collect(Collectors.toList()).get(0);
            replacingPlayer.reconnect(p);

            //tell each player a new player connected
            for (Player plr : players){
                info(plr);
            }

            reintroduceReconnectedPlayer(replacingPlayer);
        }
        else if(phase != LOBBY){
            throw new MatchException("Match already started");
        }
        else if(players.stream().anyMatch(player -> player.getName().equals(p.getName()) && player.isConnected())){
            throw new MatchException("There is already a player named \"" + p.getName() + "\" in this match");
        }

        else if(players.size() >= 4){
            throw new MatchException("The match is full already");
        }
        else {
            //simply add the new player
            players.add(p);
            p.attachDashboard(new Dashboard(false, marketplace, developmentGrid, p.getName(), leadersList, isSinglePlayer));
            modelObserver.attachTo(p); //attach the model observer to the dashboard of this player

            p.sendController("show lobby"); //tell the player to show the lobby screen

            //tell each player a new player connected
            for (Player plr : players){
                info(plr);
            }
        }
    }



    synchronized void reintroduceReconnectedPlayer(Player reconnected){
        //prepare player order string to send to the reconnected view
        StringBuilder playersNamesInOrder = new StringBuilder();
        for (Player p : players){
            playersNamesInOrder.append(p.getName() + " ");
        }
        reconnected.sendController("start " + (reconnected.getOrder()+1) + " " + playersNamesInOrder);

        if (phase != LOBBY) {
            marketplace.notifyViews();
            developmentGrid.notifyViews();
            reconnected.getDashboard().notifyViews();
        }

        if(phase == LOBBY){
            reconnected.sendController("show lobby");
        }
        else if(phase == PRE_MATCH){
            reconnected.sendController("show preMatch");
        }
        else {
            reconnected.sendController("show dashboard");
        }
    }



    public void update(String message, Player player) {
        new Thread(() -> performAction(message, player)).start();
    }



    private synchronized void performAction(String message, Player player) {
        String[] tokens = message.split(" ");

        try {
            commands.get(tokens[0]).accept(player, tokens);
        } catch (NullPointerException npe){
            //command not recognized
            player.sendController("error " + tokens[0] + " is not a recognized command");
        }
    }



    //actions
    private void info(Player player, String... args) {
        StringBuilder infoBox = new StringBuilder("setPlayers");

        for (Player p : players){
            //if the player is disconnected make him red, if he is the current player make him green
            if(p == activePlayer){
                infoBox.append(" curr " + p.getName());
            }
            else if(p.isConnected()) {
                infoBox.append(" on " + p.getName());
            }
            else {
                infoBox.append(" off " + p.getName());
            }
        }

        infoBox.append(" " + phase.toString());

        player.sendController(infoBox.toString());
    }

    private void dead(Player player, String... args) {
        //if no player is active, end the match
        if (players.stream().filter(p -> p.isConnected()).count() == 0){
            endMatch();
            return;
        }

        switch (phase) {
            case LOBBY:
                //if the leader disconnected and there is another active player replace him
                if(player == activePlayer) {
                    List<Player> activePlayers = players.stream().filter(p -> p.isConnected()).collect(Collectors.toList());
                    if (!activePlayers.isEmpty()) {
                        activePlayer = activePlayers.get(0);
                    }
                }
                players.forEach(p -> info(p));
                return;

            case PRE_MATCH:
                //fill the depot with coins
                int order = player.getOrder();
                if (order == 1 || order == 2){
                    update("select coin", player);
                }
                else if(order == 3){
                    update("select coin", player);
                    update("select coin", player);
                }

                //pick the first 2 leaders
                update("pickLeaders 1 2", player);
                return;
        }

        if(player == activePlayer) {
            switch (phase) {
                case TURN_START:
                case TURN_END:
                    update("skipTurn", player); //simply end turn
                    break;

                case MARKETPLACE:
                    update("discard", player); //discard what remained
                    update("endTurn", player); //end turn
                    break;
            }
        }
    }

    private void start(Player player, String... args) {
        if(!player.equals(activePlayer)){
            player.sendController("error You are not the leader");
            return;
        }
        if (phase != LOBBY) {
            player.sendController("error Match already started");
            return;
        }

        System.out.print("\nMatch" + matchId + " started with " + players.size() + " players");

        Collections.shuffle(players); //randomize players order
        activePlayer = players.get(0); //set the first player to play

        //tell each player his number
        for (int i = 0; i < players.size(); ++i){
            players.get(i).setOrder(i);
        }

        //send the first update for the marketplace and the development grid
        marketplace.notifyViews();
        developmentGrid.notifyViews();

        //prepare player order string to send to the views
        StringBuilder playersNamesInOrder = new StringBuilder();
        for (Player p : players){
            playersNamesInOrder.append(p.getName() + " ");
        }
        //tell the players the match started
        for (int i = 0; i < players.size(); ++i){
            players.get(i).sendController("start " + (i+1) + " " + playersNamesInOrder);
        }

        activePlayer.getDashboard().giveInkwell(); //give the inkwell to the first player

        //draw 4 random cards for leader selection
        players.forEach(p -> p.getDashboard().fillLeadersPicks());

        //put players 3 and 4 one step ahead in the faith track
        try {
            players.get(2).getDashboard().goAhead();
            players.get(3).getDashboard().goAhead();
        } catch (IndexOutOfBoundsException ioobe){
            //there wasn't player 3 or 4, no problem
        }

        phase = PRE_MATCH;
        //make the view show the leaders/supplies pick
        for (Player p : players){
            p.sendController("show preMatch");
        }

        //avoid first turn for disconnected players
        for(Player p : players.stream().filter(p -> !p.isConnected()).collect(Collectors.toList())) {
            update("dead", p);
        }
    }

    private void select(Player player, String... args)  {
        if(phase != PRE_MATCH){
            player.sendController("error You can't use this command now");
            return;
        }
        if((player.getSelectedItemsInPreMatch() >= 0 && player.getOrder() == 0) ||
           (player.getSelectedItemsInPreMatch() >= 1 && player.getOrder() == 1) ||
           (player.getSelectedItemsInPreMatch() >= 1 && player.getOrder() == 2) ||
           (player.getSelectedItemsInPreMatch() >= 2 && player.getOrder() == 3)){
            player.sendController("error You've already selected all the items");
            return;
        }

        //try to add to first row of warehouse, if you cannot (because 4th player before chose a different supply), then add to second row
        try {
            player.getDashboard().trustedAddSupply(DepotID.WAREHOUSE3, WarehouseObjectType.stringToType(args[1]));
        } catch (Exception e){
            try {
                player.getDashboard().trustedAddSupply(DepotID.WAREHOUSE2, WarehouseObjectType.stringToType(args[1]));
            } catch (Exception e1){
                player.sendController("error An unknown error occurred. Retry.");
                return;
            }
        }

        player.addSelectedItemsInPreMatch(); //report that the player chose one supply to start

        checkPreMatchDoneSpecificPlayer(player); //check if this specific player has selected all of his items
        checkPreMatchDone(); //check if all of the players have selected all of their items
    }

    private void marketplace(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            player.sendController("hide unassignedMarbles");
            return;
        }
        if(phase != TURN_START){
            player.sendController("error You can't buy marbles now!");
            player.sendController("hide unassignedMarbles");
            return;
        }

        MarketDirection dir = args[1].equals("h") ? MarketDirection.HORIZONTAL : MarketDirection.VERTICAL;
        int index = Integer.parseInt(args[2]);

        //check boundaries
        if(dir == MarketDirection.HORIZONTAL && index == 4){
            player.sendController("error If you choose horizontal, index must be between 1 and 3");
            return;
        }

        player.getDashboard().buySupplies(dir, index-1);

        phase = MARKETPLACE; //set new phase

        player.sendController("show dashboard"); //show the dashboard to the player
        player.sendController("refresh");
    }

    private void moveMarble(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != MARKETPLACE){
            player.sendController("error You can't move marbles now!");
            return;
        }

        MarbleColor color = MarbleColor.stringToColor(args[1]);
        DepotID id = DepotID.stringToId(args[2]);
        try {
            player.getDashboard().assignMarble(id, color);
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
        }
        if(player.getDashboard().getUnassignedSuppliesQuantity() == 0){
            player.sendController("message You assigned all of your marbles. Discard the remaining red marbles to go ahead on the faith track!");
        }
    }

    private void colorMarble(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != MARKETPLACE){
            player.sendController("error You can't color your marbles now!");
            return;
        }

        MarbleColor color = MarbleColor.stringToColor(args[1]);
        try {
            player.getDashboard().transformWhiteMarble(color);
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
        }
    }

    private void discard(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != MARKETPLACE){
            player.sendController("error You can't discard your marbles now!");
            return;
        }

        Pair<Integer, Boolean> ret = player.getDashboard().discardSupplies();
        int opponentsFaithSteps = ret.first;
        boolean triggeredPope = ret.second;

        //first, check if the active player triggered a vatican report
        if(triggeredPope){
            for (Player p : players.stream().filter(plr -> plr != player).collect(Collectors.toList())){
                p.getDashboard().vaticanReport();
            }
        }

        //now move the opponents one step at a time
        for (int i = 0; i < opponentsFaithSteps; ++i) {
            boolean someoneTriggeredVaticanReport = false;
            for (Player p : players.stream().filter(plr -> plr != player).collect(Collectors.toList())) {
                someoneTriggeredVaticanReport |= p.getDashboard().goAheadDontTrigger();
            }
            //if a vatican report has been triggered, then call the vatican report on everybody
            if (someoneTriggeredVaticanReport){
                for (Player p1 : players) {
                    p1.getDashboard().vaticanReport();
                }
            }
        }

        if (checkWinner()){
            phase = GAME_OVER;
            endMatch();
            return;
        }
        else {
            phase = TURN_END;
        }
    }

    private void move(Player player, String... args) {

        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != TURN_START && phase != TURN_END){
            player.sendController("error You can't move supplies now!");
            return;
        }

        WarehouseObjectType supply = WarehouseObjectType.stringToType(args[1]);
        DepotID from = DepotID.stringToId(args[2]);
        DepotID to = DepotID.stringToId(args[3]);

        try {
            player.getDashboard().moveSupply(from, to, supply);
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
        }
    }

    private void buy(Player player, String... args) {

        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
            player.sendController("error You can't buy cards now!");
            return;
        }

        int row = Integer.parseInt(args[1])-1;
        int col = Integer.parseInt(args[2])-1;
        int space = Integer.parseInt(args[3])-1;

        try {
            player.getDashboard().buyDevelopment(col, row, space);
            if (checkWinner()){
                phase = GAME_OVER;
                endMatch();
                return;
            }
            else {
                phase = TURN_END;
            }
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
        }

    }

    private void endTurn(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != TURN_END){
            player.sendController("error You must perform an action before ending your turn!");
            return;
        }

        player.getDashboard().clearPaycheck();
        player.getDashboard().clearProductions();

        if (isSinglePlayer) {
            extractSinglePlayerTile();
        }

        int index = players.indexOf(activePlayer) + 1;
        index = index == players.size() ? 0 : index;

        activePlayer = players.get(index);

        phase = TURN_START;

        matchManager.saveMatchState(matchId); //save match state

        //if the player is alive, tell him it's his turn to play, if not perform auto action
        if(activePlayer.isConnected()) {
            activePlayer.sendController("yourTurn");
        }
        else {
            update("dead", activePlayer);
        }
    }

    private void produce(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
            player.sendController("error You can't produce now!");
            return;
        }
        if (args[1].equals("false") && args[2].equals("false") && args[3].equals("false") && args[4].equals("false") && args[5].equals("false") && args[6].equals("false")){
            player.sendController("error You must activate at least one production!");
            return;
        }

        try {
            player.getDashboard().checkProduction(Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]), Boolean.parseBoolean(args[4]), Boolean.parseBoolean(args[5]), Boolean.parseBoolean(args[6]));
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
            return;
        }

        boolean vaticanReport = player.getDashboard().produce(Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]), Boolean.parseBoolean(args[4]), Boolean.parseBoolean(args[5]), Boolean.parseBoolean(args[6]));

        if (vaticanReport){
            for (Player p : players.stream().filter(plr -> plr != player).collect(Collectors.toList())){
                p.getDashboard().vaticanReport();
            }
        }

        if (checkWinner()){
            phase = GAME_OVER;
            endMatch();
            return;
        }
        else {
            phase = TURN_END;
        }
    }

    private void swapBase(Player player, String... args) {
        if (player != activePlayer) {
            player.sendController("error It's not your turn!");
            return;
        }
        if (phase != TURN_START) {
            player.sendController("error You can't switch production input/output now!");
            return;
        }

        int slot = Integer.parseInt(args[1]) -1;
        WarehouseObjectType newWot = WarehouseObjectType.stringToType(args[2]);

        try {
            player.getDashboard().swapBaseProduction(slot, newWot);
        } catch (Exception e) {
            player.sendController("error " + e.getMessage());
        }
    }

    private void swapLeader(Player player, String... args) {
        if (player != activePlayer) {
            player.sendController("error It's not your turn!");
            return;
        }
        if (phase != TURN_START) {
            player.sendController("error You can't switch production input/output now!");
            return;
        }

        int slot = Integer.parseInt(args[1]) -1;
        WarehouseObjectType newWot = WarehouseObjectType.stringToType(args[2]);

        try {
            player.getDashboard().swapLeaderProduction(slot, newWot);
        } catch (Exception e) {
            player.sendController("error " + e.getMessage());
        }
    }

    private void activateLeader(Player player, String... args) {
        if (player != activePlayer) {
            player.sendController("error It's not your turn!");
            return;
        }
        if (phase != TURN_START && phase != TURN_END) {
            player.sendController("error You can't activate your leader now!");
            return;
        }

        try {
            player.getDashboard().playLeader(Integer.parseInt(args[1])-1);
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
        }
    }

    private void discardLeader(Player player, String... args) {
        if (player != activePlayer) {
            player.sendController("error It's not your turn!");
            return;
        }
        if (phase != TURN_START && phase != TURN_END) {
            player.sendController("error You can't discard your leader now!");
            return;
        }

        try {
            boolean vaticanReport = player.getDashboard().discardLeader(Integer.parseInt(args[1])-1);

            if (vaticanReport){
                players.forEach(p -> p.getDashboard().vaticanReport());
            }

            if (checkWinner()){
                phase = GAME_OVER;
                endMatch();
                return;
            }
            else {
                phase = TURN_END;
            }
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
        }
    }

    private void pickLeaders(Player player, String... args) {
        if (phase != PRE_MATCH) {
            player.sendController("error You' ve already picked your leaders!");
            return;
        }

        if(args[1].equals(args[2])){
            player.sendController("error Choose 2 different leaders!");
            return;
        }

        try {
            player.getDashboard().pickLeader(Integer.parseInt(args[1])-1);
            player.addSelectedLeaderInPreMatch();
            player.getDashboard().pickLeader(Integer.parseInt(args[2])-1);
            player.addSelectedLeaderInPreMatch();
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
            return;
        }

        checkPreMatchDoneSpecificPlayer(player); //check if this specific player has selected all of his items
        checkPreMatchDone(); //check if all of the players have selected all of their items
    }

    private void swapWarehouse(Player player, String... args) {
        if (player != activePlayer) {
            player.sendController("error It's not your turn!");
            return;
        }
        if (phase != TURN_START && phase != TURN_END && phase != MARKETPLACE) {
            player.sendController("error You can't do this now!");
            return;
        }

        try {
            player.getDashboard().swapWarehouseRows(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        } catch (SupplyException se){
            player.sendController("error " + se.getMessage());
        }

    }

    private void selected(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != TURN_START && phase != TURN_END){
            player.sendController("error You can't move supplies now!");
            return;
        }


        ArrayList<DepotID> allowed = player.getDashboard().getAllowedDepots(DepotID.stringToId(args[2]), WarehouseObjectType.stringToType(args[1]));

        StringBuilder allowedString = new StringBuilder(" ");
        for (DepotID id : allowed){
            switch (id) {
                case COFFER:
                    allowedString.append("coffer ");
                    break;
                case WAREHOUSE1:
                    allowedString.append("wh1 ");
                    break;
                case WAREHOUSE2:
                    allowedString.append("wh2 ");
                    break;
                case WAREHOUSE3:
                    allowedString.append("wh3 ");
                    break;
                case LEADER1:
                    allowedString.append("leader1 ");
                    break;
                case LEADER2:
                    allowedString.append("leader2 ");
                    break;
                case DEVELOPMENT1:
                    allowedString.append("dev1 ");
                    break;
                case DEVELOPMENT2:
                    allowedString.append("dev2 ");
                    break;
                case DEVELOPMENT3:
                    allowedString.append("dev3 ");
                    break;
                case BASE_PRODUCTION:
                    allowedString.append("baseProd ");
                    break;
                case PAYCHECK:
                    allowedString.append("paycheck ");
                    break;
            }
        }

        player.sendController("setActive" + allowedString);
    }

    private void skipTurn(Player player, String... args) {
        phase = TURN_END;
        update("endTurn", player);
    }

    private void exit(Player player, String... args) {
        if (phase != LOBBY) {
            player.sendController("error You can't leave now!");
            return;
        }

        players.remove(player);
        performAction("dead", player);

        player.destroy();
    }



    //sets the list of default commands and their respective actions
    private void setDefaultCommands() {
        commands.put("start", this::start);
        commands.put("info", this::info);
        commands.put("dead", this::dead);
        commands.put("select", this::select);
        commands.put("ECG", (p, s) -> {});
        commands.put("marketplace", this::marketplace);
        commands.put("moveMarble", this::moveMarble);
        commands.put("colorMarble", this::colorMarble);
        commands.put("discard", this::discard);
        commands.put("move", this::move);
        commands.put("buy", this::buy);
        commands.put("endTurn", this::endTurn);
        commands.put("produce", this::produce);
        commands.put("swapBase", this::swapBase);
        commands.put("swapLeader", this::swapLeader);
        commands.put("activateLeader", this::activateLeader);
        commands.put("discardLeader", this::discardLeader);
        commands.put("pickLeaders", this::pickLeaders);
        commands.put("swapRows", this::swapWarehouse);
        commands.put("selected", this::selected);
        commands.put("skipTurn", this::skipTurn);
        commands.put("exit", this::exit);
    }



    //sends the message to every player in the match
    private synchronized void broadcast(String message){
        //avoid first turn for disconnected players
        for(Player p : players) {
            p.sendController(message);
        }
    }


    private synchronized void checkPreMatchDone(){
        boolean doneItems = true;
        for(int i = 0; i < players.size(); ++i){
            Player p = players.get(i);
            if((p.getSelectedItemsInPreMatch() != 0 && p.getOrder() == 0) ||
                    (p.getSelectedItemsInPreMatch() != 1 && p.getOrder() == 1) ||
                    (p.getSelectedItemsInPreMatch() != 1 && p.getOrder() == 2) ||
                    (p.getSelectedItemsInPreMatch() != 2 && p.getOrder() == 3)){
                doneItems = false;
            }
        }

        boolean doneLeaders = true;
        for (Player p : players){
            if(p.getSelectedLeadersInPreMatch() < 2){
                doneLeaders = false;
                break;
            }
        }

        if(doneItems && doneLeaders) {
            phase = TURN_START;
            broadcast("show dashboard");

            //if the player is alive, tell him it's his turn to play, if not perform auto action
            if (activePlayer.isConnected()) {
                //activePlayer.sendController("yourTurn");
            }
            else {
                update("dead", activePlayer);
            }
        }
    }


    private synchronized void checkPreMatchDoneSpecificPlayer(Player p){
        boolean doneItems = true;
        if((p.getSelectedItemsInPreMatch() != 0 && p.getOrder() == 0) ||
                (p.getSelectedItemsInPreMatch() != 1 && p.getOrder() == 1) ||
                (p.getSelectedItemsInPreMatch() != 1 && p.getOrder() == 2) ||
                (p.getSelectedItemsInPreMatch() != 2 && p.getOrder() == 3)){
            doneItems = false;
        }

        boolean doneLeaders = true;
        if(p.getSelectedLeadersInPreMatch() < 2){
            doneLeaders = false;
        }

        if (doneItems && doneItems){
            p.sendController("show wait");
        }

    }


    //checks if someone won, if so sends the players the game over
    private synchronized boolean checkWinner(){
        //check if the match ended
        if (players.stream().anyMatch(p -> p.getDashboard().isMatchEnded())){
            //find who won
            Player winner = activePlayer;
            for (Player p : players){
                Pair<Integer, Integer> winnerPoints = winner.getDashboard().getWinPoints();
                Pair<Integer, Integer> pPoints = p.getDashboard().getWinPoints();
                if ((winnerPoints.first == pPoints.first && winnerPoints.second < pPoints.second) || winnerPoints.first < pPoints.first){
                    winner = p;
                }
            }

            //tell the players who won
            broadcast("show endMatch");
            broadcast("message " + winner.getName() + " won the match! " + winner.getDashboard().getWinPoints() + " points!");

            return true;
        }

        return false;
    }


    private synchronized void extractSinglePlayerTile(){
        boolean matchEnded = activePlayer.getDashboard().extractActionTile();

        if (matchEnded) {
            phase = GAME_OVER;
            activePlayer.sendController("show endMatch");
            activePlayer.sendController("message You LOST!");
            endMatch();
            return;
        }
    }


    public synchronized void endMatch(){
        players.forEach(p -> p.destroy());
        matchManager.notifyMatchEnded(matchId);
        System.out.print("\nMatch " + matchId + " ended");
    }


    public synchronized ArrayList<Player> getPlayers(){
        return players;
    }

    public synchronized Pair<DevelopmentGrid, Marketplace> getMatchItems() {
        return new Pair<>(developmentGrid, marketplace);
    }

    public synchronized int getActivePlayer(){
        return activePlayer.getOrder();
    }

    public synchronized boolean isSinglePlayer(){
        return isSinglePlayer;
    }

    public synchronized String getMatchId() {
        return matchId;
    }

}
