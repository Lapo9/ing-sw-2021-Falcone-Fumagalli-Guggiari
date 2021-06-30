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



    public Player(Socket client, MatchManager matchManager) {
        try {
            this.socket = new ClientSocket(client);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }

        new Thread(() -> handshake(matchManager)).start();
    }


    public Player(String name, Match match, int order, String dashboard, Marketplace marketplace, DevelopmentGrid developmentGrid, boolean isSinglePlayer, LeadersList leadersList) throws Exception{
        this.name = name;
        this.match = match;
        this.order = order;
        int[] status = Arrays.asList(dashboard.split(", ")).stream().mapToInt(Integer::parseInt).toArray();

        this.dashboard = new Dashboard(status, marketplace, developmentGrid, name, leadersList, isSinglePlayer);

        selectedLeadersInPreMatch = 2;
        selectedItemsInPreMatch = order == 0 ? 0 : order == 1 ? 1 : order == 2 ? 1 : order == 3 ? 2 : 0;
    }


    public synchronized int getSelectedItemsInPreMatch() {
        return selectedItemsInPreMatch;
    }

    public synchronized void addSelectedItemsInPreMatch() {
        selectedItemsInPreMatch++;
    }


    public synchronized int getSelectedLeadersInPreMatch() {
        return selectedLeadersInPreMatch;
    }

    public synchronized void addSelectedLeaderInPreMatch() {
        selectedLeadersInPreMatch++;
    }


    public synchronized int getOrder() {
        return order;
    }

    public synchronized void setOrder(int order){
        this.order = order;
    }


    public synchronized String getName(){
        return new String(name);
    }



    public synchronized boolean isConnected(){
        return isConnected;
    }

    private synchronized void setConnected(boolean isConnected){
        this.isConnected = isConnected;
    }


    private synchronized boolean isDestroyed(){
        return destroy;
    }

    private synchronized boolean setDestroy(boolean d){
        boolean res = destroy != d;
        destroy = d;
        return res; //returns whether it was this thread to change the status
    }


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


    public void attachDashboard(Dashboard dashboard){
        this.dashboard = dashboard;
    }


    public void attachMatch(Match match) {
        this.match = match;
    }


    public Dashboard getDashboard() {
        return dashboard;
    }



    public synchronized boolean sendController(String message){
        try {
            socket.send(message, ClientSocket.packUpStringWithLengthAndType((byte) 0));
        } catch (Exception e){
            destroy();
            return false;
        }
        return true;
    }



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



    public synchronized boolean sendTerminate() {
        try {
            socket.send("", ClientSocket.packUpStringWithLengthAndType((byte) 2));
        } catch (Exception e){
            destroy();
            return false;
        }
        return true;
    }



    //function to accept the player and add it to the specified match
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


    //routine performed by the socket to listen. It is performed at max once every 5 seconds
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


    //send an heartbeat to the player once every 8 seconds to tell him everything is ok
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
