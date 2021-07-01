package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Represents the players' interfaces
 */
public class PlayersController extends SubSceneController {

    @FXML private ImageView player1;
    @FXML private ImageView player2;
    @FXML private ImageView player3;
    @FXML private ImageView player4;

    private boolean isSinglePlayer = false;

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

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();

        ArrayList<ImageView> players = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
    }

    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        ArrayList<ImageView> players = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
        for (int i = 0; i < 4 - offlineInfo.getPlayersNum(); ++i){
            players.get(3-i).setVisible(false);
        }

        double toX = centerCoordinates.get(completeUpdate[1]).first + playerOffset.get(completeUpdate[0]).first;
        double toY = centerCoordinates.get(completeUpdate[1]).second + playerOffset.get(completeUpdate[0]).second;

        players.get(completeUpdate[0]).setX(toX);
        players.get(completeUpdate[0]).setY(toY);

        //for single player match
        if (completeUpdate[2] != -1){
            isSinglePlayer = true;
            player4.setVisible(true);
            player4.setX(centerCoordinates.get(completeUpdate[2]).first + playerOffset.get(3).first);
            player4.setY(centerCoordinates.get(completeUpdate[2]).second + playerOffset.get(3).second);
            player4.setImage(new Image("/pictures/miscellaneous/blackCross.png"));
        }

    }

    /**
     * Shows the player 1 when the mouse is over his pawn
     */
    @FXML
    void player1mouseEntered() {
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 1) {
            player1.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent1");
        }
    }

    /**
     * Returns to the player's dashboard
     */
    @FXML
    void player1mouseExited() {
        player1.setEffect(null);
        controllerInterpreter.execute("hide opponent1");
    }

    /**
     * Shows the player 2 when the mouse is over his pawn
     */
    @FXML
    void player2mouseEntered() {
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 2) {
            player2.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent2");
        }
    }

    /**
     * Returns to the player's dashboard
     */
    @FXML
    void player2mouseExited() {
        player2.setEffect(null);
        controllerInterpreter.execute("hide opponent2");
    }

    /**
     * Shows the player 3 when the mouse is over his pawn
     */
    @FXML
    void player3mouseEntered() {
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 3) {
            player3.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent3");
        }
    }

    /**
     * Returns to the player's dashboard
     */
    @FXML
    void player3mouseExited() {
        player3.setEffect(null);
        controllerInterpreter.execute("hide opponent3");
    }

    /**
     * Shows the player 4 when the mouse is over his pawn
     */
    @FXML
    void player4mouseEntered() {
        //player 4 is used ad Lorenzo for single players matches
        if(offlineInfo.getPlayerOrder(offlineInfo.getYourName()) != 4 && !isSinglePlayer) {
            player4.setEffect(new Glow(1));
            controllerInterpreter.execute("show opponent4");
        }
    }

    /**
     * Returns to the player's dashboard
     */
    @FXML
    void player4mouseExited() {
        player4.setEffect(null);
        controllerInterpreter.execute("hide opponent4");
    }

}
