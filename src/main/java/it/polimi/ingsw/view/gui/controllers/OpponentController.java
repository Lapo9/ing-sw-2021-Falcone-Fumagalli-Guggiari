package it.polimi.ingsw.view.gui.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class OpponentController extends SubSceneController{
    @FXML private ImageView leader1;
    @FXML private Group producerPane1;
    @FXML private Text coin1;
    @FXML private Text servant1;
    @FXML private Text shield1;
    @FXML private Text stone1;
    @FXML private ImageView mutableProduction1;
    @FXML private Group depotPane;
    @FXML private ImageView depot12;
    @FXML private ImageView depot11;
    @FXML private ImageView leader2;
    @FXML private Group producerPane2;
    @FXML private Text coin2;
    @FXML private Text servant2;
    @FXML private Text shield2;
    @FXML private Text stone2;
    @FXML private ImageView mutableProduction2;
    @FXML private Group depotPane2;
    @FXML private ImageView depot22;
    @FXML private ImageView depot21;
    @FXML private ImageView c1;
    @FXML private ImageView b1;
    @FXML private ImageView a1;
    @FXML private ImageView b2;
    @FXML private ImageView a2;
    @FXML private ImageView a3;
    @FXML private Text shieldCoffer;
    @FXML private Text stoneCoffer;
    @FXML private Text coinCoffer;
    @FXML private Text servantCoffer;
    @FXML private ImageView out;
    @FXML private ImageView in1;
    @FXML private ImageView in2;
    @FXML private Text stone;
    @FXML private Text shield;
    @FXML private Text servant;
    @FXML private Text coin;
    @FXML private ImageView card1;
    @FXML private ImageView oldCard11;
    @FXML private Text winPointsOldCard11;
    @FXML private ImageView oldCard12;
    @FXML private Text winPointsOldCard12;
    @FXML private Text coinD1;
    @FXML private Text shieldD1;
    @FXML private Text servantD1;
    @FXML private Text stoneD1;
    @FXML private ImageView card2;
    @FXML private ImageView oldCard21;
    @FXML private Text winPointsOldCard21;
    @FXML private ImageView oldCard22;
    @FXML private Text winPointsOldCard22;
    @FXML private Text coinD2;
    @FXML private Text shieldD2;
    @FXML private Text servantD2;
    @FXML private Text stoneD2;
    @FXML private ImageView card3;
    @FXML private ImageView oldCard31;
    @FXML private Text winPointsOldCard31;
    @FXML private ImageView oldCard32;
    @FXML private Text winPointsOldCard32;
    @FXML private Text coinD3;
    @FXML private Text shieldD3;
    @FXML private Text servantD3;
    @FXML private Text stoneD3;
    @FXML private Text shieldPaycheck;
    @FXML private Text stonePaycheck;
    @FXML private Text coinPaycheck;
    @FXML private Text servantPaycheck;
    @FXML private Group pane;

    private FadeTransition fadeIn = new FadeTransition(Duration.millis(200));
    private FadeTransition fadeOut = new FadeTransition(Duration.millis(200));


    @Override
    public void initialize() {
        super.initialize();
        pane.setVisible(false);

        //in and out animation
        fadeIn.setNode(pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeOut.setNode(pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(event -> pane.setVisible(false));
    }


    @Override
    public void update(int[] completeUpdate) {

    }


    public void show(){
        pane.setVisible(true);
        fadeIn.play();
    }


    public void hide(){
        fadeOut.play();
    }


}
