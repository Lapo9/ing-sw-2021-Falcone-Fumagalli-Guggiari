package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.view.*;
import it.polimi.ingsw.view.gui.controllers.DashboardController;
import it.polimi.ingsw.view.gui.controllers.SceneController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;


/**
 * This class is responsible to orchestrate the various scenes.
 */
public class ScreenGUI extends Application implements Screen {

    private Stage stage;
    private HashMap<String, Pair<Scene, SceneController>> scenes = new HashMap<>();
    private Pair<Scene, SceneController> activeScene;


    /**
     * Display the selected element
     * @param scene The scene to display
     */
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
                //((DashboardController)scenes.get("dashboard").second).hideUnassignedMarbles();
                stage.setScene(activeScene.first);
                stage.show();
            });
        }
    }


    /**
     * Hides the specified element
     * @param scene The element to be hidden
     */
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

        else if (scene.contains("developmentGrid")){
            if (activeScene.second instanceof DashboardController){
                Platform.runLater( () -> {
                    ((DashboardController) activeScene.second).hideDevelopmentGrid();
                });
            }
        }
    }


    /**
     * Sets the message on screen
     * @param message Content of the message
     * @param type Type of message
     */
    @Override
    public void setMessage(String message, MessageType type) {
        Platform.runLater(() -> activeScene.second.setMessage(message, type));
    }


    /**
     * Tells each scene what are the players and the status of the match
     * @param players String containing the players and their status
     */
    @Override
    public void setPlayers(String players) {
        scenes.forEach((id, scene) -> {Platform.runLater(() -> scene.second.setPlayers(players));});
    }


    /**
     * Starts the GUI
     * @param stage First scene to display
     * @throws Exception
     */
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
        loadView("yourTurn", "/fxml/YourTurn.fxml", ci, ui, oi, mi);
        loadView("endMatch", "/fxml/EndScreen.fxml", ci, ui, oi, mi);
        loadView("wait", "/fxml/WaitScreen.fxml", ci, ui, oi, mi);

        activeScene = scenes.get("welcome");

        stage.setScene(activeScene.first);
        stage.show();
    }


    /**
     * Wrapper to call the launch method to start the GUI
     * @param args --Disabled--
     */
    public static void mainGUI(String[] args) {
        launch(args);
    }


    /**
     * Loads a view to the current stage
     * @param id Id of the scene
     * @param fxml Graphical structure of the scene
     * @param ci Controller interpreter to be used by the scene, to communicate with other scenes and receive updates from the server
     * @param ui User interpreter to be used by the scene, to communicate with the server
     * @param oi Offline info to be used by the scene, to retrieve information about the match offline
     * @param mi Model interpreter to be used by the scene, to receive updates from the model
     * @throws Exception Something went wrong
     */
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

    /**
     * Loads a view to the current stage
     * @param id Id of the scene
     * @param fxml Graphical structure of the scene
     * @param ci Controller interpreter to be used by the scene, to communicate with other scenes and receive updates from the server
     * @param ui User interpreter to be used by the scene, to communicate with the server
     * @param oi Offline info to be used by the scene, to retrieve information about the match offline
     * @throws Exception Something went wrong
     */
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
