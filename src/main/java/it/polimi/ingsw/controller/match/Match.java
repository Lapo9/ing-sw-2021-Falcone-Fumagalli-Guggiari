package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cli.fancy_console.FancyConsole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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
    private void listPlayers(Player player, String... args) {
        StringBuilder playersNames = new StringBuilder("message ");

        for (Player p : players){
            //if the player is disconnected make him red
            if(p.isConnected()) {
                playersNames.append(FancyConsole.reset() + FancyConsole.FRAMED(" " + p.getName() + " ") + " ");
            }
            else {
                playersNames.append(FancyConsole.FRAMED(FancyConsole.RED(" " + p.getName() + " ")) + " ");
            }
        }

        player.send((byte)0, playersNames.toString());
    }

    private void dead(Player player, String... args) {
        switch (phase) {
            case LOBBY:
                //if the leader disconnected and there is another active player replace him
                if(player.equals(activePlayer)) {
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

            case TURN_START:
            case TURN_END:
                update("endTurn", player); //simply end turn
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
        //TODO give inkwell to player 1
        //tell the players the match started
        for (int i = 0; i < players.size(); ++i){
            players.get(i).send((byte) 0, "start " + (i+1));
        }

        phase = PRE_MATCH;

        //TODO put players 3 and 4 one step ahead in the faith track

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
            player.getDashboard().moveSupply(DepotID.WAREHOUSE3, DepotID.WAREHOUSE1, WarehouseObjectType.stringToType(args[1]));
        } catch (Exception e){
            try {
                player.getDashboard().moveSupply(DepotID.WAREHOUSE3, DepotID.WAREHOUSE2, WarehouseObjectType.stringToType(args[1]));
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
            if((player.getSelectedItemsInPreMatch() != 0 && playerOrder(p) == 0) ||
               (player.getSelectedItemsInPreMatch() != 1 && playerOrder(p) == 1) ||
               (player.getSelectedItemsInPreMatch() != 1 && playerOrder(p) == 2) ||
               (player.getSelectedItemsInPreMatch() != 2 && playerOrder(p) == 3)){
                done = false;
            }
        }

        if (done){
            phase = LEADER_SELECTION;
            //TODO tell the players to select their leaders
        }
    }






    //sets the list of default commands and their respective actions
    private void setDefaultCommands() {
        commands.put("start", this::start);
        commands.put("listPlayers", this::listPlayers);
        commands.put("dead", this::dead);
        commands.put("select", this::select);
        commands.put("ECG", (p, s) -> {});
        //TODO add all the commands
    }

}
