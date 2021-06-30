package it.polimi.ingsw.view.gui.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the action tile for the single player mode
 */
public class ActionTileController extends SubSceneController{

    @FXML private ImageView actionTile;

    private ArrayList<Image> tiles = new ArrayList<>(Arrays.asList(
            new Image("/pictures/actionTiles/greenCard.png"),
            new Image("/pictures/actionTiles/blueCard.png"),
            new Image("/pictures/actionTiles/yellowCard.png"),
            new Image("/pictures/actionTiles/violetCard.png"),
            new Image("/pictures/actionTiles/plus2.png"),
            new Image("/pictures/actionTiles/plus1shuffle.png")
    ));

    /**
     * Updates the actionTile image using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        actionTile.setImage(tiles.get(completeUpdate[1]));
    }

    /**
     * Initialize the class
     */
    @Override
    public void initialize() {

    }

}
