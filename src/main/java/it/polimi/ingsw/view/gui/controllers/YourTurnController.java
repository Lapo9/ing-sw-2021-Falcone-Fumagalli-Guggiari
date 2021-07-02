package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Represents the "your turn" scene
 */
public class YourTurnController extends SceneController{

    @FXML ActionTileController actionTileController;

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();
    }

    /**
     * Initializes the sub scenes
     */
    @Override
    public void initializeSubScenes() {
        actionTileController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
    }

    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        if (completeUpdate[0] == 6){
            actionTileController.update(completeUpdate);
        }
    }

    /**
     * Unused method of the superClass
     */
    @Override
    public void setPlayers(String players) {

    }


    /**
     * Starts the turn of the player
     */
    @FXML
    public void startTurnClicked() {
        controllerInterpreter.execute("show dashboard");
    }


}
