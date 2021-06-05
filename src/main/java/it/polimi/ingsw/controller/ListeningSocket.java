package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.match.MatchManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ListeningSocket {

    private ServerSocket listeningSocket;
    private MatchManager matchManager;


    public ListeningSocket(boolean recovery){
        matchManager = new MatchManager(recovery);
    }


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
                System.out.print("\n One player connected");
            } catch (IOException ioe) {ioe.printStackTrace();}
        }
    }

}
