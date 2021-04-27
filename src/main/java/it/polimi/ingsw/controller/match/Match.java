package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.controller.MatchPhase;
import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.model.Dashboard;
import it.polimi.ingsw.model.DevelopmentGrid;
import it.polimi.ingsw.model.Marketplace;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static it.polimi.ingsw.controller.MatchPhase.LOBBY;

public class Match {

    private ArrayList<Player> players = new ArrayList<>();
    private Player activePlayer;
    private int activePlayerIndex = 0;
    private DevelopmentGrid developmentGrid = new DevelopmentGrid();
    private Marketplace marketplace = new Marketplace();
    private MatchPhase phase = LOBBY;


    Match(Player leader){
        players.add(leader);
        activePlayer = leader;

        leader.attachDashboard(new Dashboard(false, marketplace, developmentGrid));

        new Thread(/*TODO match thread*/);
    }



    void addPlayer(Player p) throws MatchException {
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


    public synchronized void update(String message, Player player) {
        new Thread(() -> performAction(message, player)).start();
    }



    private synchronized void performAction(String message, Player player) {
        String[] tokens = message.split(" ");

        if(player != activePlayer && !tokens[0].equals("listPlayers")) {
            player.send((byte)0, "" /*TODO*/);
        }
        else {
            if(tokens[0].equals("listPlayers")){
                listPlayers(player);
            }
        }
    }



    //TODO this is only a test, we have to change this
    private void listPlayers(Player player) {
        StringBuilder playersNames = new StringBuilder("error ");

        for (Player p : players){
            playersNames.append(p.getName() + " ");
        }

        player.send((byte)0, playersNames.toString());
    }

}
