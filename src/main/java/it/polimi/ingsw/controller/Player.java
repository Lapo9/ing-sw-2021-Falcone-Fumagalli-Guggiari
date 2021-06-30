package it.polimi.ingsw.controller;

import it.polimi.ingsw.ClientSocket;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.controller.match.Match;
import it.polimi.ingsw.controller.match.MatchManager;
import it.polimi.ingsw.model.Dashboard;
import it.polimi.ingsw.model.match_items.DevelopmentGrid;
import it.polimi.ingsw.model.match_items.LeadersList;
import it.polimi.ingsw.model.match_items.Marketplace;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Class that manages the player of the game
 */
public class Player {

    private ClientSocket socket;
    private Match match;
    private String name;
    private boolean isConnected = false;
    private boolean destroy = false;
    private Dashboard dashboard;
    private int selectedItemsInPreMatch = 0;
    private int selectedLeadersInPreMatch = 0;
    private int order = -1;

    private Thread listenRoutineThread;
    private Thread heartbeatThread;


    /**
     * Class constructor
     * @param client Socket to copy
     * @param matchManager matchManager
     */
    public Player(Socket client, MatchManager matchManager) {
        try {
            this.socket = new ClientSocket(client);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }

        new Thread(() -> handshake(matchManager)).start();
    }

    /**
     * Class constructor
     * @param name of the player
     * @param match where the player wants to join
     * @param order of the player
     * @param dashboard of the player
     * @param marketplace of the match
     * @param developmentGrid of the match
     * @param isSinglePlayer boolean to establish if it is a single player match or not
     * @param leadersList list of leaders to choose
     * @throws Exception checked exception
     */
    public Player(String name, Match match, int order, String dashboard, Marketplace marketplace, DevelopmentGrid developmentGrid, boolean isSinglePlayer, LeadersList leadersList) throws Exception{
        this.name = name;
        this.match = match;
        this.order = order;
        int[] status = Arrays.asList(dashboard.split(", ")).stream().mapToInt(Integer::parseInt).toArray();

        this.dashboard = new Dashboard(status, marketplace, developmentGrid, name, leadersList, isSinglePlayer);

        selectedLeadersInPreMatch = 2;
        selectedItemsInPreMatch = order == 0 ? 0 : order == 1 ? 1 : order == 2 ? 1 : order == 3 ? 2 : 0;
    }

    /**
     * Gets the number of selected items in the pre match session by the player
     * (first player has to select 0 items to take,
     * second and third player have to select one item
     * and the fourth player has to select 2 items)
     * @return number of the items
     */
    public synchronized int getSelectedItemsInPreMatch() {
        return selectedItemsInPreMatch;
    }

    /**
     * Adds the selected items to the counter
     */
    public synchronized void addSelectedItemsInPreMatch() {
        selectedItemsInPreMatch++;
    }

    /**
     * Gets the number of selected leader cards in the pre match session by the player (every player has to select two leaders to take)
     * @return index of the cards
     */
    public synchronized int getSelectedLeadersInPreMatch() {
        return selectedLeadersInPreMatch;
    }

    /**
     * Adds the selected leaders to the counter
     */
    public synchronized void addSelectedLeaderInPreMatch() {
        selectedLeadersInPreMatch++;
    }

    /**
     * Gets the order of the player
     * @return the order of the player
     */
    public synchronized int getOrder() {
        return order;
    }

    /**
     * Sets the order of the player
     * @param order the order to set
     */
    public synchronized void setOrder(int order){
        this.order = order;
    }

    /**
     * Gets the player's name
     * @return a String containing the player's name
     */
    public synchronized String getName(){
        return new String(name);
    }

    /**
     * Gets the player's status in the match
     * @return player's status
     */
    public synchronized boolean isConnected(){
        return isConnected;
    }

    /**
     * Sets the player's status
     * @param isConnected status to set
     */
    private synchronized void setConnected(boolean isConnected){
        this.isConnected = isConnected;
    }

    /**
     * Is the player to destroy?
     * @return true/false
     */
    private synchronized boolean isDestroyed(){
        return destroy;
    }

    /**
     * Set the status of the player
     * @param d status to set
     * @return returns whether it was this thread to change the status
     */
    private synchronized boolean setDestroy(boolean d){
        boolean res = destroy != d;
        destroy = d;
        return res;
    }

    /**
     * Reconnects the player to the match
     * @param replacingPlayer player to reconnect
     */
    public synchronized void reconnect(Player replacingPlayer){
        if(!isConnected()) {
            System.out.print("\n" + name + " reconnected");
            setConnected(true);
            setDestroy(false);
            this.socket = replacingPlayer.socket; //no problems because old this.client is for sure closed if this method is called
            try {
                socket.send("message You are connected!", ClientSocket.packUpStringWithLengthAndType((byte) 0));
            } catch (Exception e) {
                destroy();
                return;
            }

            listenRoutineThread = new Thread(this::listenRoutine);
            heartbeatThread = new Thread(this::heartbeat);
            listenRoutineThread.start();
            heartbeatThread.start();
        }
        else {
            sendController("fatal Wait a few seconds before reconnecting");
        }
    }

    /**
     * Sets the dashboard of the player with the dashboard given
     * @param dashboard Dashboard
     */
    public void attachDashboard(Dashboard dashboard){
        this.dashboard = dashboard;
    }

    /**
     * Sets the match of the player with the match given
     * @param match Match
     */
    public void attachMatch(Match match) {
        this.match = match;
    }

    /**
     * Gets the player's dashboard
     * @return player's dashboard
     */
    public Dashboard getDashboard() {
        return dashboard;
    }


    /**
     * Sends a message to the controller
     * @param message to send
     * @return if the message is sent without errors
     */
    public synchronized boolean sendController(String message){
        try {
            socket.send(message, ClientSocket.packUpStringWithLengthAndType((byte) 0));
        } catch (Exception e){
            destroy();
            return false;
        }
        return true;
    }


    /**
     * Sends the status of the model to the controller
     * @param update ArrayList of integer representing the dashboard (HasStatus)
     * @return if the message is sent without errors
     */
    public synchronized boolean sendModel(ArrayList<Integer> update){
        int[] updateArr = update.stream().mapToInt(i -> i).toArray();
        try {
            socket.send(updateArr, ClientSocket.packUpIntsWithLengthAndType((byte) 1));
        } catch (Exception e){
            destroy();
            return false;
        }
        return true;
    }


    /**
     * Send terminate message to the controller
     * @return
     */
    public synchronized boolean sendTerminate() {
        try {
            socket.send("", ClientSocket.packUpStringWithLengthAndType((byte) 2));
        } catch (Exception e){
            destroy();
            return false;
        }
        return true;
    }




    /**
     * Function to accept the player and add it to the specified match
     * @param matchManager MatchManager
     */
    private void handshake(MatchManager matchManager){
        setConnected(true);

        String message = "";
        try {
            //once the player asked to connect, he has 5 seconds to send its name and the match he wants to join
            message = socket.receiveAndTransform(5000, ClientSocket::bytesToString);
        } catch (Exception e) {
            try {
                socket.send("fatal Timeout error", ClientSocket.packUpStringWithLengthAndType((byte) 0));
            } catch (Exception e1){e1.printStackTrace();}
            destroy();
            return;
        }

        String[] tokens = message.split(" "); //assumed the message is in the form "name actualNameXYZ matchID"
        this.name = tokens[1]; //set the name

        try {
            matchManager.addPlayer(this, tokens[2], tokens[3].contains("singlePlayer")); //add the player to the match, if you cannot destroy
        } catch (MatchException me){
            sendController("error " + me.getMessage());
            destroy();
            return;
        }

        //if it was a reconnect, then this player can be destroyed
        if(dashboard == null){
            destroy();
            return;
        }

        try {
            sendController("message You are connected!");
        } catch (Exception e){
            destroy();
            return;
        }
        System.out.print("\n" + name + " got accepted in match " + tokens[2] + " SinglePlayer:" + match.isSinglePlayer());

        listenRoutineThread = new Thread(this::listenRoutine);
        heartbeatThread = new Thread(this::heartbeat);
        listenRoutineThread.start();
        heartbeatThread.start();
    }



    /**
     * Routine performed by the socket to listen. It is performed at max once every 5 seconds
     */
    private void listenRoutine() {
        while (!isDestroyed()) {
            String message;
            try {
                message = socket.receiveAndTransform(10000, ClientSocket::bytesToString); //the client must send an ACK once every 10 seconds, if not the server will consider that player disconnected
            } catch (Exception ioe){
                destroy(); //if nothing arrived (not even the ACK), we assume the player has connection issues, so disconnect him
                return;
            }
            match.update(message, this); //send the command to the match
        }
    }



    /**
     * Send an heartbeat to the player once every 8 seconds to tell him everything is ok
     */
    private void heartbeat(){
        while(!isDestroyed()) {
            sendController("ECG");
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Destroy the player
     */
    public synchronized void destroy() {
        //if this thread called destroy first
        if (setDestroy(true)) {
            System.out.print("\n" + name + " disconnected from match " + match.getMatchId());
            //a new thread wait for the listener an the heartbeat to end. It must be a new thread, if not it happens that the thread that called destroy waits for the join on himself, causing a deadlock.
            new Thread(() -> {
                try {
                    //wait for the threads to end
                    listenRoutineThread.join();
                    heartbeatThread.join();
                } catch (Exception e) {

                }
                try {
                    setConnected(false);
                    match.update("dead", this);
                } catch (NullPointerException npe) {
                    //there wasn't a match
                }
            }).start();
        }
    }

}
