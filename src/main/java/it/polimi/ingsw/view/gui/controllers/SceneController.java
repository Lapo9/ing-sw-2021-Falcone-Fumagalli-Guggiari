package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.ControllerInterpreter;
import it.polimi.ingsw.view.OfflineInfo;
import it.polimi.ingsw.view.UserInterpreter;
import it.polimi.ingsw.view.gui.MessageType;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;


/**
 * This class abstracts some key components that all of the scenes must have
 */
public abstract class SceneController {

    protected UserInterpreter userInterpreter;
    protected ControllerInterpreter controllerInterpreter;
    protected OfflineInfo offlineInfo;
    @FXML private Label message;
    FadeTransition fadeIn = new FadeTransition(Duration.millis(1000));
    FadeTransition fadeOut = new FadeTransition(Duration.millis(5000));


    /**
     * Updated the current scenes
     * @param completeUpdate Update from the model
     */
    public abstract void update(int[] completeUpdate);

    /**
     * Attaches the specified UserInterpreter to be used by this scene to communicate with the server
     * @param userInterpreter The user interpreter
     */
    public void attachUserInterpreter(UserInterpreter userInterpreter) {
        this.userInterpreter = userInterpreter;
    }


    /**
     * Attaches the specified Controller to be used by this scene to communicate with other scenes and receive updates from the server
     * @param controllerInterpreter The controller interpreter
     */
    public void attachControllerInterpreter(ControllerInterpreter controllerInterpreter) {
        this.controllerInterpreter = controllerInterpreter;
    }


    /**
     * Attaches to this scene the offline info used by the scene to retrieve key information offline, without overloading the server
     * @param offlineInfo Offline information
     */
    public void attachOfflineInfo(OfflineInfo offlineInfo) {
        this.offlineInfo = offlineInfo;
    }


    /**
     * Attached all of the interpreters in a single call
     * @param ci The controller interpreter used by this scene to communicate with other scenes and receive updates from the server
     * @param ui The user interpreter used by this scene to communicate with the server
     * @param oi Offline information used by the scene to retrieve key information offline, without overloading the server
     */
    public void attachInterpreters(ControllerInterpreter ci, UserInterpreter ui, OfflineInfo oi){
        controllerInterpreter = ci;
        userInterpreter = ui;
        offlineInfo = oi;
    }


    /**
     * Initializes the scene, in particular the message label. This method is called when the scene is first created
     */
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
     * Initializes the components of a scene. This method should be called before the first use of the scene.
     */
    public abstract void initializeSubScenes();



    /**
     * Shows the specified message.
     * @param message Message content
     * @param type message, error or fatal
     */
    public void setMessage(String message, MessageType type) {
        this.message.setText(message);
        this.message.setTextFill(type.getColor());

        fadeIn.playFromStart();
    }


    /**
     * Tells the scene what players are present and their status
     * @param players what players are present and their status, in a specific format
     */
    public abstract void setPlayers(String players);


}
