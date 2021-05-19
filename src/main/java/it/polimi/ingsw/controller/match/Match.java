package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.controller.ModelObserver;
import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.match_items.DevelopmentGrid;
import it.polimi.ingsw.model.match_items.MarketDirection;
import it.polimi.ingsw.model.match_items.Marketplace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static it.polimi.ingsw.controller.match.MatchPhase.*;

public class Match {

    private ArrayList<Player> players = new ArrayList<>();
    private Player activePlayer;
    private DevelopmentGrid developmentGrid = new DevelopmentGrid();
    private Marketplace marketplace = new Marketplace();
    private MatchPhase phase = LOBBY;
    private HashMap<String, BiConsumer<Player, String[]>> commands = new HashMap<>();
    private ModelObserver modelObserver = new ModelObserver();


    Match(Player leader) throws MatchException {
        addPlayer(leader);
        activePlayer = leader;
        setDefaultCommands();

        marketplace.attach(modelObserver);
        developmentGrid.attach(modelObserver);

        leader.attachDashboard(new Dashboard(false, marketplace, developmentGrid, leader.getName()));
        modelObserver.attachTo(leader); //attach the model observer to the dashboard of this player
    }



    synchronized void addPlayer(Player p) throws MatchException {
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
            p.attachDashboard(new Dashboard(false, marketplace, developmentGrid, p.getName()));
            modelObserver.attachTo(p); //attach the model observer to the dashboard of this player

            //tell each player a new player connected
            for (Player plr : players){
                info(plr);
            }

            p.sendController("show lobby"); //tell the player to show the lobby screen
        }
    }



    synchronized void reintroduceReconnectedPlayer(Player reconnected){
        //prepare player order string to send to the reconnected view
        StringBuilder playersNamesInOrder = new StringBuilder();
        for (Player p : players){
            playersNamesInOrder.append(p.getName() + " ");
        }
        reconnected.sendController("start " + (reconnected.getOrder()+1) + " " + playersNamesInOrder);

        marketplace.notifyViews();
        developmentGrid.notifyViews();
        reconnected.getDashboard().notifyViews();
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
        switch (phase) {
            case LOBBY:
                //if the leader disconnected and there is another active player replace him
                if(player == activePlayer) {
                    List<Player> activePlayers = players.stream().filter(p -> p.isConnected()).collect(Collectors.toList());
                    if (!activePlayers.isEmpty()) {
                        activePlayer = activePlayers.get(0);
                    }
                }
                break;

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
                break;
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

        //put players 3 and 4 one step ahead in the faith track
        try {
            players.get(2).getDashboard().goAhead();
            players.get(3).getDashboard().goAhead();
        } catch (IndexOutOfBoundsException ioobe){
            //there wasn't player 3 or 4, no problem
        }

        //draw 4 random cards for leader selection
        players.forEach(p -> p.getDashboard().fillLeadersPicks());

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

        checkPreMatchDone();
    }

    private void marketplace(Player player, String... args) {
        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
            player.sendController("error You can't buy marbles now!");
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
            for (Player p : players){
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

        phase = TURN_END;
    }

    private void move(Player player, String... args) {

        if(player != activePlayer){
            player.sendController("error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
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

        int index = players.indexOf(activePlayer) + 1;
        index = index == players.size() ? 0 : index;

        activePlayer = players.get(index);

        phase = TURN_START;

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

        try {
            player.getDashboard().checkProduction(Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]), Boolean.parseBoolean(args[4]), Boolean.parseBoolean(args[5]), Boolean.parseBoolean(args[6]));
        } catch (Exception e){
            player.sendController("error " + e.getMessage());
            return;
        }

        player.getDashboard().produce(Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]), Boolean.parseBoolean(args[4]), Boolean.parseBoolean(args[5]), Boolean.parseBoolean(args[6]));

        phase = TURN_END;
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

        int slot = Integer.parseInt(args[1]);
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

        int slot = Integer.parseInt(args[1]);
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
            player.getDashboard().playLeader(Integer.parseInt(args[1]));
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
            player.getDashboard().discardLeader(Integer.parseInt(args[1]));
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
            //TODO there should be a way to clear leaders, if not this state is forever
            return;
        }

        checkPreMatchDone();
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
            broadcast("show matchStart");

            //if the player is alive, tell him it's his turn to play, if not perform auto action
            if (activePlayer.isConnected()) {
                activePlayer.sendController("yourTurn");
            }
            else {
                update("dead", activePlayer);
            }
        }
    }

}
