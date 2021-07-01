package it.polimi.ingsw.view.gui.controllers;


import javafx.fxml.FXML;

/**
 * Represents the screen shown at the end of the match
 */
public class EndScreenController extends SceneController{

    /**
     * Does nothing
     * @param completeUpdate (nothing)
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
     * Does nothing
     */
    @Override
    public void initializeSubScenes() {

    }

    /**
     * Does nothing
     */
    @Override
    public void setPlayers(String players) {

    }

}
