package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YourTurnController extends SceneController{

    @FXML ActionTileController actionTileController;


    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void initializeSubScenes() {
        actionTileController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
    }

    @Override
    public void update(int[] completeUpdate) {
        if (completeUpdate[0] == 6){
            actionTileController.update(completeUpdate);
        }
    }


    @Override
    public void setPlayers(String players) {

    }



    @FXML
    public void startTurnClicked() {
        controllerInterpreter.execute("show dashboard");
    }


}
