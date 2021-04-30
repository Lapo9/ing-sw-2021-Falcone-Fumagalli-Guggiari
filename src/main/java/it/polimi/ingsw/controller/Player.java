package it.polimi.ingsw.controller;

import it.polimi.ingsw.ClientSocket;
import it.polimi.ingsw.Pair;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.controller.match.Match;
import it.polimi.ingsw.controller.match.MatchManager;
import it.polimi.ingsw.model.Dashboard;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class Player {

    ClientSocket socket;
    Match match;
    String name;
    boolean isConnected = false;
    Dashboard dashboard;



    public Player(Socket client, MatchManager matchManager) {
        try {
            this.socket = new ClientSocket(client);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }

        new Thread(() -> handshake(matchManager)).start();
    }


    private void destroy() {
        isConnected = false;
        try {
            TimeUnit.SECONDS.sleep(6); //give time to close connection
        } catch (InterruptedException ie){ie.printStackTrace();}
    }



    public String getName(){
        return new String(name);
    }



    public boolean isConnected(){
        return isConnected;
    }


    public void reconnect(Player replacingPlayer){
        isConnected = true;
        this.socket = replacingPlayer.socket; //no problems because old this.client is for sure closed if this method is called
        try {
            socket.send("message You are connected!", ClientSocket.packUpStringWithLengthAndType((byte) 0));
        } catch (Exception e){
            destroy();
            return;
        }
        new Thread(this::listenRoutine).start(); //old player re-start listening
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



    public synchronized boolean send(byte type, String message){
        try {
            socket.send(message, ClientSocket.packUpStringWithLengthAndType(type));
        } catch (IOException ioe){
            destroy();
            return false;
        }
        return true;
    }



    //function to accept the player and add it to the specified match
    private void handshake(MatchManager matchManager){
        String message = "";
        try {
            //once the player asked to connect, he has 5 seconds to send its name and the match he wants to join
            message = socket.receiveAndTransform(5000, ClientSocket::bytesToString);
        } catch (Exception e) {
            try {
                socket.send("error Timeout error", ClientSocket.packUpStringWithLengthAndType((byte) 0));
            } catch (Exception e1){e1.printStackTrace();}
            destroy();
            return;
        }

        String[] tokens = message.split(" "); //assumed the message is in the form "name actualNameXYZ matchID"
        this.name = tokens[1]; //set the name

        try {
            matchManager.addPlayer(this, tokens[2]); //add the player to the match, if you cannot throw
        } catch (MatchException me){
            try {
                socket.send("error " + me.getMessage(), ClientSocket.packUpStringWithLengthAndType((byte) 0));
            } catch (Exception e){e.printStackTrace();}
            destroy();
            return;
        }

        try {
            socket.send("message You are connected!", ClientSocket.packUpStringWithLengthAndType((byte) 0));
        } catch (Exception e){
            destroy();
            return;
        }
        isConnected = true;

        new Thread(this::listenRoutine).start();
    }


    //routine performed by the socket to listen. It is performed at max once every 5 seconds
    private void listenRoutine() {
        while (isConnected) {
            String message = "";
            try {
                message = socket.receiveAndTransform(5000, ClientSocket::bytesToString); //the client must send an ACK once every 5 seconds, if not the server will consider that player disconnected
            } catch (Exception ioe){
                destroy(); //if nothing arrived (not even the ACK), we assume the player has connection issues, so disconnect him
                return;
            }
            match.update(message, this); //send the command to the match
        }
    }

}
