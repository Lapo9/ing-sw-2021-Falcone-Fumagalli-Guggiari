package it.polimi.ingsw.view.gui.controllers;


import javafx.fxml.FXML;

public class EndScreenController extends SceneController{


    @Override
    public void update(int[] completeUpdate) {

    }

    @Override
    public void initialize() {
        super.initialize();
        fadeOut.setToValue(1.0); //don't fade out
    }

    @Override
    public void initializeSubScenes() {

    }

    @Override
    public void setPlayers(String players) {

    }

}
