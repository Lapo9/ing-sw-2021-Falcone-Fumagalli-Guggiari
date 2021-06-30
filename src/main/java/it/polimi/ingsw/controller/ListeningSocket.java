package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.match.MatchManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages the socket to create the connection between the players
 */
public class ListeningSocket {

    private ServerSocket listeningSocket;
    private MatchManager matchManager;

    /**
     * Class constructor
     */
    public ListeningSocket(){
        matchManager = new MatchManager();
    }

    /**
     * Starts the connection
     * @param portNumber where to create the connection
     */
    public void start(String portNumber) {
        int pn;
        try {
            pn = Integer.parseInt(portNumber);
        } catch (Exception e){pn = 14009;}
        try {
            listeningSocket = new ServerSocket((pn < 1024 || pn > 49152) ? 14009 : pn);
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
