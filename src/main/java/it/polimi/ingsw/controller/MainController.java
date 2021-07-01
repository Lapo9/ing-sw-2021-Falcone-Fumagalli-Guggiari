package it.polimi.ingsw.controller;

/**
 * Manages the connection the server connection
 */
public class MainController {

    /**
     * Main
     * @param args port number
     */
    public static void mainServer(String[] args) {
        ListeningSocket ls = new ListeningSocket();

        ls.start(args[0]);
    }

}
