package it.polimi.ingsw.view.gui.controllers;


import javafx.fxml.FXML;

public class EndScreenController extends SceneController{


    @Override
    public void update(int[] completeUpdate) {

    }

    @Override
    public void initializeSubScenes() {

    }

    @Override
    public void setPlayers(String players) {

    }



    @FXML
    void leaveMatchClicked(){
        controllerInterpreter.execute("reset");
        controllerInterpreter.execute("show dashboard");
    }

}
