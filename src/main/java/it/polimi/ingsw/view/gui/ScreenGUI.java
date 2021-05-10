package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.view.Screen;
import it.polimi.ingsw.view.cli.ControllerInterpreter;
import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.ServerSocket;
import it.polimi.ingsw.view.cli.UserInterpreter;
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
        stage.setScene(scenes.get(scene).first);
        stage.show();
    }


    @Override
    public void setMessage(String message) {

    }


    @Override
    public void refresh() {

    }



    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);

        OfflineInfo oi = new OfflineInfo();
        ControllerInterpreter ci = new ControllerInterpreter(this, oi);
        UserInterpreter ui = new UserInterpreter(ci, new ServerSocket(), oi);

        loadView("welcomeScreen", "/fxml/WelcomeScreen.fxml", ci, ui, oi);
        loadView("lobby", "/fxml/Lobby.fxml", ci,ui, oi);
        //TODO load all the scenes

        activeScene = scenes.get("lobby");

        stage.setScene(activeScene.first);
        stage.show();


        //TODO eliminate this test
        Platform.runLater(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e){}
            activeScene.second.setPlayers("Lapo online Marco offline");
        });
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

        scenes.put(id, new Pair<>(tmpScene, tmpController));
    }


}
