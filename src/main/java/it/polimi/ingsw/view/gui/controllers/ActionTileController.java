package it.polimi.ingsw.view.gui.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

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


    @Override
    public void update(int[] completeUpdate) {
        actionTile.setImage(tiles.get(completeUpdate[1]));
    }


    @Override
    public void initialize() {

    }

}
