package it.polimi.ingsw.view.gui.controllers;


import javafx.fxml.FXML;

/**
 * Represents the screen shown at the end of the match
 */
public class EndScreenController extends SceneController{

    /**
     * Unused method of the superClass
     */
    @Override
    public void update(int[] completeUpdate) {

    }

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();
        fadeOut.setToValue(1.0); //don't fade out
    }

    /**
     * Unused method of the superClass
     */
    @Override
    public void initializeSubScenes() {

    }

    /**
     * Unused method of the superClass
     */
    @Override
    public void setPlayers(String players) {

    }

}
