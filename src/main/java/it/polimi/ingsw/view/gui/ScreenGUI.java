package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.view.Screen;
import it.polimi.ingsw.view.cli.*;
import it.polimi.ingsw.view.gui.controllers.DashboardController;
import it.polimi.ingsw.view.gui.controllers.SceneController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ScreenGUI extends Application implements Screen {

    private Stage stage;
    private HashMap<String, Pair<Scene, SceneController>> scenes = new HashMap<>();
    private Pair<Scene, SceneController> activeScene;


    @Override
    public void show(String scene){
        //if the argument is one of the opponents, then it happens a special show, since in the GUI opponents are not stan alone scenes
        if (scene.contains("opponent")){
            if (activeScene.second instanceof DashboardController){
                ((DashboardController) activeScene.second).showOpponent(Integer.parseInt(scene.substring(scene.length() - 1)));
            }
        }

        else {
            activeScene = scenes.get(scene);
            Platform.runLater(() -> {
                stage.setScene(activeScene.first);
                stage.show();
            });
        }
    }


    @Override
    public void hide(String scene) {
        //this works only if the argument is an opponent
        if (scene.contains("opponent")){
            if (activeScene.second instanceof DashboardController){
                ((DashboardController) activeScene.second).hideOpponent(Integer.parseInt(scene.substring(scene.length() - 1)));
            }
        }
    }


    @Override
    public void setMessage(String message, MessageType type) {
        Platform.runLater(() -> activeScene.second.setMessage(message, type));
    }



    @Override
    public void setPlayers(String players) {
        scenes.forEach((id, scene) -> Platform.runLater(() -> scene.second.setPlayers(players)));
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);

        ServerSocket socket = new ServerSocket();
        OfflineInfo oi = new OfflineInfo();
        ControllerInterpreter ci = new ControllerInterpreter(this, oi);
        ModelInterpreter mi = new ModelInterpreter(null, null, null); //TODO this is different for the gui
        socket.attachInterpreter(mi);
        socket.attachInterpreter(ci);
        UserInterpreter ui = new UserInterpreter(ci, socket, oi);

        loadView("welcome", "/fxml/WelcomeScreen.fxml", ci, ui, oi);
        loadView("lobby", "/fxml/Lobby.fxml", ci, ui, oi);
        loadView("dashboard", "/fxml/Dashboard.fxml", ci, ui, oi);
        //TODO load all the scenes

        activeScene = scenes.get("dashboard");

        stage.setScene(activeScene.first);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



    private void loadView(String id, String fxml, ControllerInterpreter ci, UserInterpreter ui, OfflineInfo oi) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml)); //tell the loader where is the .fxml file of the scene
        Parent root = loader.load();

        Scene tmpScene = new Scene(root);

        SceneController tmpController = loader.getController();
        tmpController.attachControllerInterpreter(ci);
        tmpController.attachUserInterpreter(ui);
        tmpController.attachOfflineInfo(oi);

        tmpController.initializeSubScenes();

        scenes.put(id, new Pair<>(tmpScene, tmpController));
    }


}
