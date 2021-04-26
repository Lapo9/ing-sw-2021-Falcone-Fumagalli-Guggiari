package it.polimi.ingsw.controller;

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

public class Player {

    Socket client;
    Match match;
    String name;
    boolean isConnected;
    Dashboard dashboard;



    public Player(Socket client, MatchManager matchManager) {
        this.client = client;

        new Thread(() -> handshake(matchManager));
    }



    public String getName(){
        return new String(name);
    }



    public boolean isConnected(){
        return isConnected;
    }


    public void reconnect(Player replacingPlayer){
        this.client = replacingPlayer.client;
        replacingPlayer.client = null;
        isConnected = true;
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




    private String listen(int milliseconds){
        StringBuilder message = new StringBuilder(); //string where to put the message
        try {
            InputStream in = client.getInputStream();
            DataInputStream dataIn = new DataInputStream(in);

            int length = 0; //length in byte of the payload
            client.setSoTimeout(milliseconds); //wait max 5 seconds, if nothing arrives, the player disconnected
            try {
                length = dataIn.readByte(); //first byte indicates the length of the message
            } catch (SocketTimeoutException ste) {
                isConnected = false;
                try {
                    client.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                return null;
            }

            byte[] byteString = new byte[9700]; //TODO check max bytes for TCP packet
            int byteReadTot = 0; //number of bytes read
            boolean done = false; //we read all of the bytes?

            while (!done) {
                int currentBytesRead = 0;
                currentBytesRead = dataIn.read(byteString); //read as many bytes as you can and store how many you read
                byteReadTot += currentBytesRead; //add the read bytes to the total

                //if we read less bytes than the length of the message, put all of the just read bytes in the message string
                if (byteReadTot <= length) {
                    message.append(new String(byteString, 0, currentBytesRead, StandardCharsets.UTF_8));
                }
                //if we read too many bytes, store in the string only the necessary bytes, and discard what remains
                else {
                    message.append(new String(byteString, 0, byteReadTot - length, StandardCharsets.UTF_8));
                }

                //if we read everything, exit the loop
                if (message.length() == length) {
                    done = true;
                }
            }
        } catch (Exception e) {
            isConnected = false;
            try {
                client.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }

        return message.toString();
    }


    public synchronized boolean send(char type, String message){
        //the format is the actual message, preceded by its length in bytes and its type
        byte[] stringByte = message.getBytes(StandardCharsets.UTF_8); //convert the message to bytes
        byte[] stringWithInfo = new byte[message.length()+2]; //create a new array where to save the length of the message + the message
        stringWithInfo[0] = (byte) message.length(); //add the length of the message as first element
        stringWithInfo[1] = (byte) type; //add the type of message

        //copy the message in the new array
        for(int i=0; i<stringByte.length; ++i){
            stringWithInfo[i+1] = stringByte[i];
        }

        //send the new message
        try {
            OutputStream out = client.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(out);
            dataOut.write(stringWithInfo);
        } catch (Exception e) {
            isConnected = false;
            try {
                client.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return false;
        }

        return true;
    }



    //function to accept the player and add it to the specified match
    private void handshake(MatchManager matchManager){
        try {
            //once the player asked to connect, he has 5 seconds to send its name and the match he wants to join
            String message = listen(5000);
            if (message == null){
                //TODO tell player he is out
            }

            String[] tokens = message.split(" "); //assumed the message is in the form "name actualNameXYZ matchID"
            this.name = tokens[1]; //set the name

            try {
                matchManager.addPlayer(this, tokens[2]); //add the player to the match, if you cannot throw
            } catch (MatchException me){
                //TODO tell the player the error and delete him
                client.close(); //close connection
            }
            isConnected = true;

        } catch(Exception e){
            /*TODO tell the player something bad happened and delete this player*/
            try {
                client.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        new Thread(this::listenRoutine).start();
    }


    //routine performed by the socket to listen. It is performed at max once every 5 seconds
    private void listenRoutine() {
        while (true) {
            String message = listen(5000); //the client must send an ACK once every 5 seconds, if not the server will consider that player disconnected

            if(message == null){
                //TODO tell server player is dead
                try {
                    client.close(); //disconnect
                } catch (IOException ioe) {ioe.printStackTrace();}
            }
            else {
                match.update(message, this); //send the command to the match
            }
        }
    }

}
