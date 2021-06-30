package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;

import java.util.Arrays;

/**
 * Represents the development card grid
 */
public class DevelopmentGridController extends SubSceneController{

    @FXML PaycheckController paycheckController;
    @FXML DevelopmentGridCardController c00Controller;
    @FXML DevelopmentGridCardController c10Controller;
    @FXML DevelopmentGridCardController c20Controller;
    @FXML DevelopmentGridCardController c01Controller;
    @FXML DevelopmentGridCardController c11Controller;
    @FXML DevelopmentGridCardController c21Controller;
    @FXML DevelopmentGridCardController c02Controller;
    @FXML DevelopmentGridCardController c12Controller;
    @FXML DevelopmentGridCardController c22Controller;
    @FXML DevelopmentGridCardController c03Controller;
    @FXML DevelopmentGridCardController c13Controller;
    @FXML DevelopmentGridCardController c23Controller;

    @FXML Group developmentGroup;


    /**
     * Initialize the class
     */
    @Override
    public void initialize() {
        super.initialize();
    }


    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        if (completeUpdate.length == 48){
            c00Controller.update(new int[]{completeUpdate[0]});
            c10Controller.update(new int[]{completeUpdate[4]});
            c20Controller.update(new int[]{completeUpdate[8]});
            c01Controller.update(new int[]{completeUpdate[1]});
            c11Controller.update(new int[]{completeUpdate[5]});
            c21Controller.update(new int[]{completeUpdate[9]});
            c02Controller.update(new int[]{completeUpdate[2]});
            c12Controller.update(new int[]{completeUpdate[6]});
            c22Controller.update(new int[]{completeUpdate[10]});
            c03Controller.update(new int[]{completeUpdate[3]});
            c13Controller.update(new int[]{completeUpdate[7]});
            c23Controller.update(new int[]{completeUpdate[11]});
        }
        else {
            paycheckController.update(completeUpdate);
        }
    }


    /**
     * Initializes all the cards stack contained in the development card grid
     */
    public void initializeSubScenes(){
        paycheckController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c00Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c10Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c20Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c01Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c11Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c21Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c02Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c12Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c22Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c03Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c13Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        c23Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);

        c00Controller.setPositionInGrid(1, 1);
        c10Controller.setPositionInGrid(2, 1);
        c20Controller.setPositionInGrid(3, 1);
        c01Controller.setPositionInGrid(1, 2);
        c11Controller.setPositionInGrid(2, 2);
        c21Controller.setPositionInGrid(3, 2);
        c02Controller.setPositionInGrid(1, 3);
        c12Controller.setPositionInGrid(2, 3);
        c22Controller.setPositionInGrid(3, 3);
        c03Controller.setPositionInGrid(1, 4);
        c13Controller.setPositionInGrid(2, 4);
        c23Controller.setPositionInGrid(3, 4);
    }


    /**
     * Sets the paycheck activeSquare not visible
     */
    public void reset(){
        paycheckController.reset();
    }


    /**
     * Sets the paycheck activeSquare visible
     */
    public void setActive(){
        paycheckController.setActive();
    }


    /**
     * Sets the development card grid visible
     */
    public void show(){
        developmentGroup.setVisible(true);
    }


    /**
     * Sets the development card grid not visible
     */
    public void hide(){
        developmentGroup.setVisible(false);
    }
}
