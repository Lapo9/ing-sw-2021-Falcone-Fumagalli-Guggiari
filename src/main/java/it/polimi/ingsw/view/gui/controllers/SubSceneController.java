package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.cli.ControllerInterpreter;
import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.UserInterpreter;
import it.polimi.ingsw.view.gui.MessageType;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;


public abstract class SubSceneController {

    protected String id;
    protected UserInterpreter userInterpreter;
    protected ControllerInterpreter controllerInterpreter;
    protected OfflineInfo offlineInfo;


    public abstract void update(int[] completeUpdate);

    public void attachUserInterpreter(UserInterpreter userInterpreter) {
        this.userInterpreter = userInterpreter;
    }

    public void attachControllerInterpreter(ControllerInterpreter controllerInterpreter) {
        this.controllerInterpreter = controllerInterpreter;
    }

    public void attachOfflineInfo(OfflineInfo offlineInfo) {
        this.offlineInfo = offlineInfo;
    }

    public void attachInterpreters(ControllerInterpreter ci, UserInterpreter ui, OfflineInfo oi){
        controllerInterpreter = ci;
        userInterpreter = ui;
        offlineInfo = oi;
    }

    public void setId(String id){
        this.id = id;
    }

    public void initialize(){

    }


}
