package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.cli.ControllerInterpreter;
import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.UserInterpreter;
import it.polimi.ingsw.view.gui.MessageType;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;


public abstract class SceneController {

    protected UserInterpreter userInterpreter;
    protected ControllerInterpreter controllerInterpreter;
    protected OfflineInfo offlineInfo;
    @FXML private Label message;
    FadeTransition fadeIn = new FadeTransition(Duration.millis(1000));
    FadeTransition fadeOut = new FadeTransition(Duration.millis(5000));


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


    public void initialize(){
        //set values for message animation
        fadeOut.setNode(message);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);

        fadeIn.setNode(message);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.setAutoReverse(false);
        fadeIn.setOnFinished(actionEvent -> fadeOut.playFromStart());
    }


    /**
     * Show the specified message.
     * @param message Message
     * @param type message, error or fatal
     */
    public void setMessage(String message, MessageType type) {
        this.message.setText(message);
        this.message.setTextFill(type.getColor());

        fadeIn.playFromStart();
    }


    public abstract void setPlayers(String players);


}
