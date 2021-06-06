package it.polimi.ingsw.controller;

public class MainController {


    public static void mainServer(String[] args) {
        ListeningSocket ls = new ListeningSocket();

        ls.start();
    }

}
