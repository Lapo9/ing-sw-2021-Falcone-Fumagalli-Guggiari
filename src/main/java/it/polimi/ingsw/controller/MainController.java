package it.polimi.ingsw.controller;

public class MainController {


    public static void main(String[] args) {
        ListeningSocket ls = new ListeningSocket(); //FIXME user has to choose if it is true

        ls.start();
    }

}
