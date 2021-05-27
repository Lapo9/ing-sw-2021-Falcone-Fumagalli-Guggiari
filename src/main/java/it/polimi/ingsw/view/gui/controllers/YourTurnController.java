package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YourTurnController extends SceneController{

    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void initializeSubScenes() {

    }

    @Override
    public void update(int[] completeUpdate) {

    }


    @Override
    public void setPlayers(String players) {

    }



    @FXML
    public void startTurnClicked() {
        controllerInterpreter.execute("show dashboard");
    }


}
