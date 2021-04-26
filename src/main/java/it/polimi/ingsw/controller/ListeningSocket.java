package it.polimi.ingsw.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ListeningSocket {

    private ServerSocket listeningSocket;


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
            } catch (IOException ioe) {ioe.printStackTrace();}
        }
    }

}
