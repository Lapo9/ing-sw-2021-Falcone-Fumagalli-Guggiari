package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PlayersController extends SubSceneController {

    @FXML private ImageView player1;
    @FXML private ImageView player2;
    @FXML private ImageView player3;
    @FXML private ImageView player4;

    private ArrayList<Pair<Double, Double>> centerCoordinates = new ArrayList<>(Arrays.asList(
            new Pair<>(310.0, 146.0),
            new Pair<>(356.0, 146.0),
            new Pair<>(402.0, 146.0),
            new Pair<>(402.0, 99.0),
            new Pair<>(402.0, 52.0),
            new Pair<>(447.0, 52.0),
            new Pair<>(494.0, 52.0),
            new Pair<>(538.0, 52.0),
            new Pair<>(583.0, 52.0),
            new Pair<>(630.0, 52.0),
            new Pair<>(630.0, 99.0),
            new Pair<>(630.0, 146.0),
            new Pair<>(675.0, 146.0),
            new Pair<>(723.0, 146.0),
            new Pair<>(768.0, 146.0),
            new Pair<>(813.0, 146.0),
            new Pair<>(860.0, 146.0),
            new Pair<>(860.0, 99.0),
            new Pair<>(860.0, 52.0),
            new Pair<>(905.0, 52.0),
            new Pair<>(952.0, 52.0),
            new Pair<>(997.0, 52.0),
            new Pair<>(1042.0, 52.0),
            new Pair<>(1089.0, 52.0),
            new Pair<>(1135.0, 52.0)

    ));

    private ArrayList<Pair<Double, Double>> playerOffset = new ArrayList<>(Arrays.asList(
            new Pair<>(-12.5, -12.5),
            new Pair<>(12.5, -12.5),
            new Pair<>(-12.5, 12.5),
            new Pair<>(12.5, 12.5)
    ));

    private ArrayList<TranslateTransition> moveAnimations = new ArrayList<>(Arrays.asList(
            new TranslateTransition(new Duration(200)),
            new TranslateTransition(new Duration(200)),
            new TranslateTransition(new Duration(200)),
            new TranslateTransition(new Duration(200))
    ));


    @Override
    public void initialize() {
        super.initialize();

        ArrayList<ImageView> players = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));

        for (int i = 0; i < 4; ++i){
            moveAnimations.get(i).setNode(players.get(i));
        }
    }


    @Override
    public void update(int[] completeUpdate) {
        ArrayList<ImageView> players = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
        double fromX = completeUpdate[1] == 0 ? centerCoordinates.get(0).first : centerCoordinates.get(completeUpdate[1] - 1).first + playerOffset.get(completeUpdate[0]).first;
        double fromY = completeUpdate[1] == 0 ? centerCoordinates.get(0).second : centerCoordinates.get(completeUpdate[1] - 1).second + playerOffset.get(completeUpdate[0]).second;
        double toX = centerCoordinates.get(completeUpdate[1]).first + playerOffset.get(completeUpdate[0]).first;
        double toY = centerCoordinates.get(completeUpdate[1]).second + playerOffset.get(completeUpdate[0]).second;

        moveAnimations.get(completeUpdate[0]).setFromX(fromX);
        moveAnimations.get(completeUpdate[0]).setFromY(fromY);
        moveAnimations.get(completeUpdate[0]).setToX(toX);
        moveAnimations.get(completeUpdate[0]).setToY(toY);

        moveAnimations.get(completeUpdate[0]).playFromStart();
    }
    

    @FXML
    void player1mouseEntered() {
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 0) {
            player1.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent1");
        }
    }

    @FXML
    void player1mouseExited() {
        player1.setEffect(null);
        controllerInterpreter.execute("hide opponent1");
    }

    @FXML
    void player2mouseEntered() {
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 1) {
            player2.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent2");
        }
    }

    @FXML
    void player2mouseExited() {
        player2.setEffect(null);
        controllerInterpreter.execute("hide opponent2");
    }

    @FXML
    void player3mouseEntered() {
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 2) {
            player3.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent3");
        }
    }

    @FXML
    void player3mouseExited() {
        player3.setEffect(null);
        controllerInterpreter.execute("hide opponent3");
    }

    @FXML
    void player4mouseEntered() {
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 3) {
            player4.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent4");
        }
    }

    @FXML
    void player4mouseExited() {
        player4.setEffect(null);
        controllerInterpreter.execute("hide opponent4");
    }

}
