package it.polimi.ingsw.view.gui.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Group;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DashboardController extends SceneController {

    @FXML private LeaderController leader1Controller;
    @FXML private LeaderController leader2Controller;
    @FXML private WarehouseController warehouseController;
    @FXML private CofferController cofferController;
    @FXML private BaseProductionController baseProductionController;
    @FXML private DevelopmentSpaceController developmentSpace1Controller;
    @FXML private DevelopmentSpaceController developmentSpace2Controller;
    @FXML private DevelopmentSpaceController developmentSpace3Controller;
    @FXML private PopeFavorTilesController popeFavorTiles1Controller;
    @FXML private PopeFavorTilesController popeFavorTiles2Controller;
    @FXML private PopeFavorTilesController popeFavorTiles3Controller;
    @FXML private PlayersController faithTrackPlayersController;

    @FXML private OpponentController opponent1Controller;
    @FXML private OpponentController opponent2Controller;
    @FXML private OpponentController opponent3Controller;
    @FXML private OpponentController opponent4Controller;

    private ArrayList<OpponentController> opponents = new ArrayList<>();


    @Override
    public void initialize() {
        super.initialize();

        opponents.add(opponent1Controller);
        opponents.add(opponent2Controller);
        opponents.add(opponent3Controller);
        opponents.add(opponent4Controller);

    }


    @Override
    public void initializeSubScenes() {
        leader1Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        leader2Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        warehouseController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        cofferController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        baseProductionController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        developmentSpace1Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        developmentSpace2Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        developmentSpace3Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        popeFavorTiles1Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        popeFavorTiles2Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        popeFavorTiles3Controller.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
        faithTrackPlayersController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
    }


    @Override
    public void setPlayers(String players) {

    }


    @Override
    public void update(int[] completeUpdate) {
        Platform.runLater( () -> {
            //if it is an update of one player
            if(completeUpdate[0] < 4) {
                faithTrackPlayersController.update(new int[]{completeUpdate[0], completeUpdate[103]});
                popeFavorTiles1Controller.update(new int[]{completeUpdate[0], completeUpdate[104]});
                popeFavorTiles2Controller.update(new int[]{completeUpdate[0], completeUpdate[105]});
                popeFavorTiles3Controller.update(new int[]{completeUpdate[0], completeUpdate[106]});

                //if it is an update of the owner of this GUI
                if (offlineInfo.getYourName().equals(offlineInfo.getPlayerName(completeUpdate[0]))) {
                    cofferController.update(Arrays.copyOfRange(completeUpdate, 1, 6));
                    warehouseController.update(Arrays.copyOfRange(completeUpdate, 6, 21));
                    developmentSpace1Controller.update(Arrays.copyOfRange(completeUpdate, 21, 39));
                    developmentSpace2Controller.update(Arrays.copyOfRange(completeUpdate, 39, 57));
                    developmentSpace3Controller.update(Arrays.copyOfRange(completeUpdate, 57, 75));
                    baseProductionController.update(Arrays.copyOfRange(completeUpdate, 85, 103));
                    leader1Controller.update(Arrays.copyOfRange(completeUpdate, 107, 122));
                    leader2Controller.update(Arrays.copyOfRange(completeUpdate, 122, 137));
                }
                else {
                    opponents.get(completeUpdate[0]).update(completeUpdate);
                }
            }
        });
    }


    public void showOpponent(int i) {
        opponents.get(i-1).show();
    }


    public void hideOpponent(int i) {
        opponents.get(i-1).hide();
    }


}
