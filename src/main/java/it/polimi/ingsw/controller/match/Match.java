package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cli.fancy_console.FancyConsole;

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


    Match(Player leader){
        players.add(leader);
        activePlayer = leader;
        setDefaultCommands();

        leader.attachDashboard(new Dashboard(false, marketplace, developmentGrid));
    }



    synchronized void addPlayer(Player p) throws MatchException {
        if(phase != LOBBY){
            throw new MatchException("Match already started");
        }
        else if(players.stream().anyMatch(player -> player.getName().equals(p.getName()) && player.isConnected())){
            throw new MatchException("There is already a player named \"" + p.getName() + "\" in this match");
        }
        else if(players.stream().anyMatch(player -> player.getName().equals(p.getName()) && !player.isConnected())){
            //in this case there is a player with the same name who disconnected, so you can replace him
            Player replacingPlayer = players.stream().filter(player -> player.getName().equals(p.getName()) && !player.isConnected()).collect(Collectors.toList()).get(0);
            replacingPlayer.reconnect(p);
        }
        else if(players.size() >= 4){
            throw new MatchException("The match is full already");
        }
        else {
            //simply add the new player
            players.add(p);
            p.attachDashboard(new Dashboard(false, marketplace, developmentGrid));
        }
    }


    //given a player it tells you his order in the match
    private synchronized int playerOrder(Player p) {
        for(int i = 0; i < players.size(); ++i){
            if(p.equals(players.get(i))){
                return i;
            }
        }

        return -1;
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
            player.send((byte) 0, "error " + tokens[0] + " is not a recognized command");
        }
    }



    //actions
    private void info(Player player, String... args) {
        StringBuilder infoBox = new StringBuilder("message ");

        for (Player p : players){
            //if the player is disconnected make him red
            if(p == activePlayer){
                infoBox.append(FancyConsole.reset() + FancyConsole.FRAMED(FancyConsole.GREEN(" " + p.getName() + " ")) + " ");
            }
            else if(p.isConnected()) {
                infoBox.append(FancyConsole.reset() + FancyConsole.FRAMED(" " + p.getName() + " ") + " ");
            }
            else {
                infoBox.append(FancyConsole.reset() + FancyConsole.FRAMED(FancyConsole.RED(" " + p.getName() + " ")) + " ");
            }
        }

        infoBox.append("\t\t" + FancyConsole.UNDERLINED(FancyConsole.MAGENTA(phase.toString())));

        player.send((byte)0, infoBox.toString());
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
                int order = playerOrder(player);
                if (order == 1 || order == 2){
                    update("select coin", player);
                }
                else if(order == 3){
                    update("select coin", player);
                    update("select coin", player);
                }
                break;
        }



        if(player != activePlayer){
            return;
        }

        switch (phase){
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

    private void start(Player player, String... args) {
        if(!player.equals(activePlayer)){
            player.send((byte) 0, "error You are not the leader");
            return;
        }
        if (phase != LOBBY) {
            player.send((byte) 0, "error Match already started");
            return;
        }

        Collections.shuffle(players); //randomize players order
        activePlayer = players.get(0); //set the first player to play
        activePlayer.getDashboard().giveInkwell(); //gives the inkwell to the first player

        //tell the players the match started
        for (int i = 0; i < players.size(); ++i){
            players.get(i).send((byte) 0, "start " + (i+1));
        }

        phase = PRE_MATCH;

        //put players 3 and 4 one step ahead in the faith track
        try {
            players.get(2).getDashboard().goAhead();
            players.get(3).getDashboard().goAhead();
        } catch (IndexOutOfBoundsException ioobe){
            //there wasn't player 3 or 4, no problem
        }

        //avoid first turn for disconnected players
        for(Player p : players.stream().filter(p -> !p.isConnected()).collect(Collectors.toList())) {
            update("dead", p);
        }
    }

    private void select(Player player, String... args)  {
        if(phase != PRE_MATCH){
            player.send((byte) 0, "error You can't use this command now");
            return;
        }
        if((player.getSelectedItemsInPreMatch() >= 0 && playerOrder(player) == 0) ||
           (player.getSelectedItemsInPreMatch() >= 1 && playerOrder(player) == 1) ||
           (player.getSelectedItemsInPreMatch() >= 1 && playerOrder(player) == 2) ||
           (player.getSelectedItemsInPreMatch() >= 2 && playerOrder(player) == 3)){
            player.send((byte) 0, "error You've already selected all the items");
            return;
        }

        //try to add to first row of warehouse, if you cannot (because 4th player before chose a different supply), then add to second row
        try {
            player.getDashboard().trustedAddSupply(DepotID.WAREHOUSE1, WarehouseObjectType.stringToType(args[1]));
        } catch (Exception e){
            try {
                player.getDashboard().trustedAddSupply(DepotID.WAREHOUSE2, WarehouseObjectType.stringToType(args[1]));
            } catch (Exception e1){
                player.send((byte) 0, "error An unknown error occurred. Retry.");
                return;
            }
        }

        player.addSelectedItemsInPreMatch(); //report that the player chose one supply to start

        //if all the players chose all of their supplies, make them choose their leaders.
        boolean done = true;
        for(int i = 0; i < players.size(); ++i){
            Player p = players.get(i);
            if((p.getSelectedItemsInPreMatch() != 0 && playerOrder(p) == 0) ||
               (p.getSelectedItemsInPreMatch() != 1 && playerOrder(p) == 1) ||
               (p.getSelectedItemsInPreMatch() != 1 && playerOrder(p) == 2) ||
               (p.getSelectedItemsInPreMatch() != 2 && playerOrder(p) == 3)){
                done = false;
            }
        }

        if (done){
            phase = TURN_START; //FIXME when we have leaders selection screen
            broadcast("message Now select your leaders");
            //TODO actually show the 4 leaders
        }
    }

    private void marketplace(Player player, String... args) {
        if(player != activePlayer){
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
            player.send((byte) 0, "error You can't buy marbles now!");
            return;
        }

        MarketDirection dir = args[1].equals("h") ? MarketDirection.HORIZONTAL : MarketDirection.VERTICAL;
        int index = Integer.parseInt(args[2]);

        //check boundaries
        if(dir == MarketDirection.HORIZONTAL && index == 4){
            player.send((byte) 0, "error If you choose horizontal, index must be between 1 and 3");
            return;
        }

        player.getDashboard().buySupplies(dir, index-1);

        phase = MARKETPLACE; //set new phase

        player.send((byte) 0, "show dashboard"); //show the dashboard to the player
    }

    private void moveMarble(Player player, String... args) {
        if(player != activePlayer){
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != MARKETPLACE){
            player.send((byte) 0, "error You can't move marbles now!");
            return;
        }

        MarbleColor color = MarbleColor.stringToColor(args[1]);
        DepotID id = DepotID.stringToId(args[2]);
        try {
            player.getDashboard().assignMarble(id, color);
        } catch (Exception e){
            player.send((byte) 0, "error " + e.getMessage());
        }
        if(player.getDashboard().getUnassignedSuppliesQuantity() == 0){
            phase = TURN_END; //set next phase
            player.send((byte) 0, "message You assigned all of your marbles!");
        }
    }

    private void colorMarble(Player player, String... args) {
        if(player != activePlayer){
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != MARKETPLACE){
            player.send((byte) 0, "error You can't color your marbles now!");
            return;
        }

        MarbleColor color = MarbleColor.stringToColor(args[1]);
        try {
            player.getDashboard().transformWhiteMarble(color);
        } catch (Exception e){
            player.send((byte) 0, "error " + e.getMessage());
        }
    }

    private void discard(Player player, String... args) {
        if(player != activePlayer){
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != MARKETPLACE){
            player.send((byte) 0, "error You can't discard your marbles now!");
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
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
            player.send((byte) 0, "error You can't move supplies now!");
            return;
        }

        WarehouseObjectType supply = WarehouseObjectType.stringToType(args[1]);
        DepotID from = DepotID.stringToId(args[2]);
        DepotID to = DepotID.stringToId(args[3]);

        try {
            player.getDashboard().moveSupply(from, to, supply);
        } catch (Exception e){
            player.send((byte) 0, "error " + e.getMessage());
        }
    }

    private void buy(Player player, String... args) {

        if(player != activePlayer){
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
            player.send((byte) 0, "error You can't buy cards now!");
            return;
        }

        int row = Integer.parseInt(args[1]);
        int col = Integer.parseInt(args[2]);
        int space = Integer.parseInt(args[3]);

        try {
            player.getDashboard().buyDevelopment(col, row, space);
        } catch (Exception e){
            player.send((byte) 0, "error " + e.getMessage());
        }
    }

    private void endTurn(Player player, String... args) {
        if(player != activePlayer){
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != TURN_END){
            player.send((byte) 0, "error You must perform an action before ending your turn!");
            return;
        }

        int index = players.indexOf(activePlayer) + 1;
        index = index == players.size() ? 0 : index;

        activePlayer = players.get(index);

        phase = TURN_START;

        activePlayer.send((byte) 0, "yourTurn");
    }

    private void produce(Player player, String... args) {
        if(player != activePlayer){
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if(phase != TURN_START){
            player.send((byte) 0, "error You can't produce now!");
            return;
        }

        try {
            player.getDashboard().checkProduction(Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]), Boolean.parseBoolean(args[4]), Boolean.parseBoolean(args[5]), Boolean.parseBoolean(args[6]));
        } catch (Exception e){
            player.send((byte) 0, "error " + e.getMessage());
            return;
        }

        player.getDashboard().produce(Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]), Boolean.parseBoolean(args[4]), Boolean.parseBoolean(args[5]), Boolean.parseBoolean(args[6]));

        phase = TURN_END;
    }

    private void swapBase(Player player, String... args) {
        if (player != activePlayer) {
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if (phase != TURN_START) {
            player.send((byte) 0, "error You can't switch production input/output now!");
            return;
        }

        int slot = Integer.parseInt(args[1]);
        WarehouseObjectType newWot = WarehouseObjectType.stringToType(args[2]);

        try {
            player.getDashboard().swapBaseProduction(slot, newWot);
        } catch (Exception e) {
            player.send((byte) 0, "error " + e.getMessage());
        }
    }

    private void swapLeader(Player player, String... args) {
        if (player != activePlayer) {
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if (phase != TURN_START) {
            player.send((byte) 0, "error You can't switch production input/output now!");
            return;
        }

        int slot = Integer.parseInt(args[1]);
        WarehouseObjectType newWot = WarehouseObjectType.stringToType(args[2]);

        try {
            player.getDashboard().swapLeaderProduction(slot, newWot);
        } catch (Exception e) {
            player.send((byte) 0, "error " + e.getMessage());
        }
    }

    private void activateLeader(Player player, String... args) {
        if (player != activePlayer) {
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if (phase != TURN_START && phase != TURN_END) {
            player.send((byte) 0, "error You can't activate now!");
            return;
        }

        try {
            player.getDashboard().playLeader(Integer.parseInt(args[1]));
        } catch (Exception e){
            player.send((byte) 0, "error " + e.getMessage());
        }
    }

    private void discardLeader(Player player, String... args) {
        if (player != activePlayer) {
            player.send((byte) 0, "error It's not your turn!");
            return;
        }
        if (phase != TURN_START && phase != TURN_END) {
            player.send((byte) 0, "error You can't activate now!");
            return;
        }

        try {
            player.getDashboard().discardLeader(Integer.parseInt(args[1]));
        } catch (Exception e){
            player.send((byte) 0, "error " + e.getMessage());
        }
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
    }



    //sends the message to every player in the match
    private synchronized void broadcast(String message){
        //avoid first turn for disconnected players
        for(Player p : players) {
            p.send((byte) 0, message);
        }
    }


}
