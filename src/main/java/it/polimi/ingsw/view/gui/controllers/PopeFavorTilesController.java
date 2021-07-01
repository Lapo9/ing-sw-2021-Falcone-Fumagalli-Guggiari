package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the pope favor tile
 */
public class PopeFavorTilesController extends SubSceneController {

    @FXML private ImageView player1;
    @FXML private ImageView player2;
    @FXML private ImageView player3;
    @FXML private ImageView player4;

    private ArrayList<Pair<String, String>> pictures = new ArrayList<>(Arrays.asList(
            new Pair<>("redSquare.png", "redX.png"),
            new Pair<>("greenSquare.png", "greenX.png"),
            new Pair<>("orangeSquare.png", "orangeX.png"),
            new Pair<>("blueSquare.png", "blueX.png")
    ));

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();

        player1.setVisible(false);
        player2.setVisible(false);
        player3.setVisible(false);
        player4.setVisible(false);
    }

    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        ArrayList<ImageView> players = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));

        if (completeUpdate[1] == 1){
            players.get(completeUpdate[0]).setImage(new Image("/pictures/miscellaneous/" + pictures.get(completeUpdate[0]).first));
        }
        else if (completeUpdate[1] == 2){
            players.get(completeUpdate[0]).setImage(new Image("/pictures/miscellaneous/" + pictures.get(completeUpdate[0]).second));
        }

        players.get(completeUpdate[0]).setVisible(completeUpdate[1] == 1 || completeUpdate[1] == 2);
    }

}
