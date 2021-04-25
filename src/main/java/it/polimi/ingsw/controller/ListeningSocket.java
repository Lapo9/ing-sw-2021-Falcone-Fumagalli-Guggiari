package it.polimi.ingsw.controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ListeningSocket {

    ServerSocket listeningSocket;
    Set<Player> players = new HashSet<>();


    public ListeningSocket(){}


    public void start() {
        listeningSocket = new ServerSocket(14009);

        while (true) {
            Socket client = listeningSocket.accept();
            players.add(new Player(client));
        }
    }


}
