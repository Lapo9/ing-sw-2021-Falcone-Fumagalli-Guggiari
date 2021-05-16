package it.polimi.ingsw.view.gui.controllers;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class WelcomeScreenController extends SceneController {


    @FXML private Button go;
    @FXML private TextField name;
    @FXML private TextField matchId;
    @FXML private TextField serverIp;
    @FXML private TextField serverPort;
    @FXML private Rectangle decoA1;
    @FXML private Rectangle decoA2;
    @FXML private Rectangle decoA3;

    RotateTransition A1 = new RotateTransition(Duration.millis(6300));
    RotateTransition A2 = new RotateTransition(Duration.millis(8200));
    RotateTransition A3 = new RotateTransition(Duration.millis(9060));


    @Override
    public void initialize(){
        super.initialize();

        A1.setNode(decoA1);
        A2.setNode(decoA2);
        A3.setNode(decoA3);

        A1.setFromAngle(360);
        A1.setToAngle(0);
        A1.setCycleCount(-1);
        A2.setFromAngle(0);
        A2.setToAngle(360);
        A2.setCycleCount(-1);
        A3.setFromAngle(0);
        A3.setToAngle(360);
        A3.setCycleCount(-1);

        A1.play();
        A2.play();
        A3.play();
    }


    @Override
    public void initializeSubScenes() {}


    @Override
    public void update(int[] completeUpdate) {}


    @FXML
    private void goClicked() {
        userInterpreter.execute("connect " + serverIp.getText() + " " + serverPort.getText() + " " + name.getText() + " " + matchId.getText());
    }


    @FXML
    private void singlePlayerClicked() {
        //TODO
    }


    @Override
    public void setPlayers(String players) {}
}
