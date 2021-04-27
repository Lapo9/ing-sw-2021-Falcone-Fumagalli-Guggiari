package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.match.MatchManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ListeningSocket {

    private ServerSocket listeningSocket;
    private MatchManager matchManager = new MatchManager();


    public ListeningSocket(){}


    public void start() {
        try {
            listeningSocket = new ServerSocket(14009);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        while (true) {
            try {
                Socket client = listeningSocket.accept();
                new Player(client, matchManager);
            } catch (IOException ioe) {ioe.printStackTrace();}
        }
    }

}
