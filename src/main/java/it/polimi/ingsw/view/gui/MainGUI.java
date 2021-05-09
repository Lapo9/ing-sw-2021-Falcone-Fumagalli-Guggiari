package it.polimi.ingsw.view.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WelcomeScreen.fxml"));
        Scene welcomeScreen = new Scene(root);
        stage.setScene(welcomeScreen);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
