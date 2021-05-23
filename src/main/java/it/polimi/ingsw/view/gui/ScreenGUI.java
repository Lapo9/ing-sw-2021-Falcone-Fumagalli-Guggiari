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

public class ScreenGUI extends Application implements Screen {

    private Stage stage;
    private HashMap<String, Pair<Scene, SceneController>> scenes = new HashMap<>();
    private Pair<Scene, SceneController> activeScene;


    @Override
    public void show(String scene){

        //if the argument is one of the opponents/unassigned marbles, then it happens a special show, since in the GUI opponents are not stand alone scenes
        if (scene.contains("opponent")){
            if (activeScene.second instanceof DashboardController){
                Platform.runLater( () -> {
                    ((DashboardController) activeScene.second).showOpponent(Integer.parseInt(scene.substring(scene.length()-1)));
                });
            }
        }

        else if (scene.contains("unassignedMarbles")){
            if (activeScene.second instanceof DashboardController){
                Platform.runLater( () -> {
                    ((DashboardController) activeScene.second).showUnassignedMarbles();
                });
            }
        }

        else {
            activeScene = scenes.get(scene);
            Platform.runLater(() -> {
                ((DashboardController)scenes.get("dashboard").second).hideUnassignedMarbles();
                stage.setScene(activeScene.first);
                stage.show();
            });
        }
    }


    @Override
    public void hide(String scene) {
        //this works only if the argument is an opponent or the unassigned marbles
        if (scene.contains("opponent")){
            if (activeScene.second instanceof DashboardController){
                Platform.runLater( () -> {
                    ((DashboardController) activeScene.second).hideOpponent(Integer.parseInt(scene.substring(scene.length() - 1)));
                });
            }
        }

        else if (scene.contains("unassignedMarbles")){
            if (activeScene.second instanceof DashboardController){
                Platform.runLater( () -> {
                    ((DashboardController) activeScene.second).hideUnassignedMarbles();
                });
            }
        }
    }


    @Override
    public void setMessage(String message, MessageType type) {
        Platform.runLater(() -> activeScene.second.setMessage(message, type));
    }



    @Override
    public void setPlayers(String players) {
        scenes.forEach((id, scene) -> {Platform.runLater(() -> scene.second.setPlayers(players));});
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);

        ServerSocket socket = new ServerSocket();
        OfflineInfo oi = new OfflineInfo();
        ControllerInterpreter ci = new ControllerInterpreter(this, oi);
        ModelInterpreterGUI mi = new ModelInterpreterGUI(oi);
        socket.attachInterpreter(mi);
        socket.attachInterpreter(ci);
        UserInterpreter ui = new UserInterpreter(ci, socket, oi);

        loadView("welcome", "/fxml/WelcomeScreen.fxml", ci, ui, oi);
        loadView("lobby", "/fxml/Lobby.fxml", ci, ui, oi);
        loadView("dashboard", "/fxml/Dashboard.fxml", ci, ui, oi, mi);
        loadView("preMatch", "/fxml/PreMatchScreen.fxml", ci, ui, oi, mi);
        //TODO load all the scenes

        activeScene = scenes.get("welcome");

        stage.setScene(activeScene.first);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



    private void loadView(String id, String fxml, ControllerInterpreter ci, UserInterpreter ui, OfflineInfo oi, ModelInterpreterGUI mi) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml)); //tell the loader where is the .fxml file of the scene
        Parent root = loader.load();

        Scene tmpScene = new Scene(root);

        SceneController tmpController = loader.getController();
        tmpController.attachControllerInterpreter(ci);
        tmpController.attachUserInterpreter(ui);
        tmpController.attachOfflineInfo(oi);

        tmpController.initializeSubScenes();

        mi.attach(tmpController); //attach the controller to the model interpreter so that it can receive updates

        scenes.put(id, new Pair<>(tmpScene, tmpController));
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
