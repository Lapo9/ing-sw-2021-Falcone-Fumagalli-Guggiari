package it.polimi.ingsw.view.gui.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PlayersController extends SubSceneController {

    @FXML private ImageView player1;
    @FXML private ImageView player2;
    @FXML private ImageView player3;
    @FXML private ImageView player4;


    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void update(int[] completeUpdate) {
        
    }
    

    @FXML
    void player1mouseEntered() {
        player1.setEffect(new Glow(1));
        controllerInterpreter.execute("show opponent1");
    }

    @FXML
    void player1mouseExited() {
        player1.setEffect(null);
        controllerInterpreter.execute("hide opponent1");
    }

    @FXML
    void player2mouseEntered() {
        player2.setEffect(new Glow(1));
        controllerInterpreter.execute("show opponent2");
    }

    @FXML
    void player2mouseExited() {
        player2.setEffect(null);
        controllerInterpreter.execute("hide opponent2");
    }

    @FXML
    void player3mouseEntered() {
        player3.setEffect(new Glow(1));
        controllerInterpreter.execute("show opponent3");
    }

    @FXML
    void player3mouseExited() {
        player3.setEffect(null);
        controllerInterpreter.execute("hide opponent3");
    }

    @FXML
    void player4mouseEntered() {
        player4.setEffect(new Glow(1));
        controllerInterpreter.execute("show opponent4");
    }

    @FXML
    void player4mouseExited() {
        player4.setEffect(null);
        controllerInterpreter.execute("hide opponent4");
    }

}
