package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;

public class DashboardController extends SceneController {

    @FXML private LeaderController leader1Controller;
    @FXML private LeaderController leader2Controller;
    @FXML private WarehouseController warehouseController;
    @FXML private CofferController cofferController;
    @FXML private BaseProductionController baseProductionController;
    @FXML private DevelopmentSpaceController developmentSpace1Controller;
    @FXML private DevelopmentSpaceController developmentSpace2Controller;
    @FXML private DevelopmentSpaceController developmentSpace3Controller;
    @FXML private PaycheckController paycheckController;
    @FXML private PopeFavorTilesController popeFavorTiles1Controller;
    @FXML private PopeFavorTilesController popeFavorTiles2Controller;
    @FXML private PopeFavorTilesController popeFavorTiles3Controller;
    @FXML private PlayersController faithTrackPlayersController;


    @Override
    public void initialize() {
        super.initialize();
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
        paycheckController.attachInterpreters(controllerInterpreter, userInterpreter, offlineInfo);
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

    }


}
