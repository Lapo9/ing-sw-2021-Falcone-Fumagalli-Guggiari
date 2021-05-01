package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.model.Dashboard;
import it.polimi.ingsw.model.DevelopmentGrid;
import it.polimi.ingsw.model.Marketplace;
import it.polimi.ingsw.view.cli.fancy_console.FancyConsole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static it.polimi.ingsw.controller.match.MatchPhase.LOBBY;
import static it.polimi.ingsw.controller.match.MatchPhase.PRE_MATCH;

public class Match {

    private ArrayList<Player> players = new ArrayList<>();
    private Player activePlayer;
    private int activePlayerIndex = 0;
    private DevelopmentGrid developmentGrid = new DevelopmentGrid();
    private Marketplace marketplace = new Marketplace();
    private MatchPhase phase = LOBBY;
    private HashMap<String, Consumer<Player>> commands = new HashMap<>();


    Match(Player leader){
        players.add(leader);
        activePlayer = leader;
        setDefaultCommands();

        leader.attachDashboard(new Dashboard(false, marketplace, developmentGrid));

        new Thread(/*TODO match thread*/);
    }



    void addPlayer(Player p) throws MatchException {
        if(phase != LOBBY){
            throw new MatchException("Match already started");
        }
        if(players.stream().anyMatch(player -> player.getName().equals(p.getName()) && player.isConnected())){
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
    private int playerOrder(Player p) {
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
            commands.get(tokens[0]).accept(player);
        } catch (NullPointerException npe){
            //command not recognized
            player.send((byte) 0, "error " + tokens[0] + " is not a recognized command");
        }
    }



    //actions
    private void listPlayers(Player player) {
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

    private void dead(Player player) {
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

    private void start(Player player) {
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





    //sets the list of default commands and their respective actions
    private void setDefaultCommands() {
        commands.put("start", this::start);
        commands.put("listPlayers", this::listPlayers);
        commands.put("dead", this::dead);
        commands.put("ECG", p -> {});
        //TODO add all the commands
    }

}
