package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.ControllerInterpreter;
import it.polimi.ingsw.view.OfflineInfo;
import it.polimi.ingsw.view.UserInterpreter;


/**
 * Abstracts the concept of a sub-component inside a SceneController
 */
public abstract class SubSceneController {

    protected String id;
    protected UserInterpreter userInterpreter;
    protected ControllerInterpreter controllerInterpreter;
    protected OfflineInfo offlineInfo;


    /**
     * Updates the component based of the slice of the model updated passed by the relative SceneController
     * @param completeUpdate slice of the model updated passed by the relative SceneController
     */
    public abstract void update(int[] completeUpdate);


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
     * If this component has an ID, it can be set here
     * @param id Component ID, to differentiate between similar scene components (for example the 2 leaders)
     */
    public void setId(String id){
        this.id = id;
    }


    /**
     * Initializes the sub-scene, in particular the message label. This method is called when the scene is first created
     */
    public void initialize(){

    }


}
