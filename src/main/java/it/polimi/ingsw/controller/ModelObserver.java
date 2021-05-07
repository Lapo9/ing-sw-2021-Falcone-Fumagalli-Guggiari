package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Dashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Has the responsibility to receive updates from various dashboards ad send them to the players.
 */
public class ModelObserver {

    private HashMap<String, Player> players = new HashMap<>();


    public ModelObserver(){}


    /**
     * Registers this observer to the specified player dashboard.
     * @param player Player to attach this observer to
     */
    public synchronized void attachTo(Player player){
        player.getDashboard().attach(this);
        players.put(player.getName(), player); //save this player to the list of known players
    }


    /**
     * Receives an update from an item, adds the required info and send it to all the players.
     * @param updatedItem Item to update, can be marketplace, developmentGrid or one of the players name
     * @param update Update
     */
    public synchronized void update(String updatedItem, ArrayList<Integer> update){
        if(updatedItem.equals("marketplace")){
            ArrayList<Integer> updateToSend = new ArrayList<>();
            updateToSend.add(4);
            updateToSend.addAll(update);
            broadcast(updateToSend);
        }

        else if(updatedItem.equals("developmentGrid")){
            ArrayList<Integer> updateToSend = new ArrayList<>();
            updateToSend.add(5);
            updateToSend.addAll(update);
            broadcast(updateToSend);
        }

        else {
            Player toUpdate = players.get(updatedItem);
            ArrayList<Integer> updateToSend = new ArrayList<>();
            updateToSend.add(toUpdate.getOrder());
            updateToSend.addAll(update);
            broadcast(updateToSend);
        }
    }


     //sends the update to all of the players
    private void broadcast(ArrayList<Integer> update){
        for (Player p : players.values()){
            p.sendModel(update);
        }
    }

}
