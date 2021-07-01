package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.view.gui.WarehouseObjectTypeController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Arrays;

import static it.polimi.ingsw.model.development.DevelopmentCard.*;
import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.getContainedSupplies;
import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.getTypeByNumber;
import static java.lang.String.valueOf;

/**
 * Manages what to view when spying the opponent
 */
public class OpponentController extends SubSceneController{
    @FXML private ImageView leader1;
    @FXML private Group leader1group;
    @FXML private Group producerPane1;
    @FXML private Text coin1;
    @FXML private Text servant1;
    @FXML private Text shield1;
    @FXML private Text stone1;
    @FXML private ImageView mutableProduction1;
    @FXML private Group depotPane1;
    @FXML private ImageView depot12;
    @FXML private ImageView depot11;
    @FXML private ImageView leader2;
    @FXML private Group leader2group;
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
    @FXML private Text stoneBase;
    @FXML private Text shieldBase;
    @FXML private Text servantBase;
    @FXML private Text coinBase;
    @FXML private ImageView card1;
    @FXML private ImageView oldCard11;
    @FXML private Text winPointsOldCard11;
    @FXML private ImageView oldCard12;
    @FXML private Text winPointsOldCard12;
    @FXML private Text coinD1;
    @FXML private Text shieldD1;
    @FXML private Text servantD1;
    @FXML private Text stoneD1;
    @FXML private ImageView coD1;
    @FXML private ImageView seD1;
    @FXML private ImageView shD1;
    @FXML private ImageView stD1;
    @FXML private ImageView card2;
    @FXML private ImageView oldCard21;
    @FXML private Text winPointsOldCard21;
    @FXML private ImageView oldCard22;
    @FXML private Text winPointsOldCard22;
    @FXML private Text coinD2;
    @FXML private Text shieldD2;
    @FXML private Text servantD2;
    @FXML private Text stoneD2;
    @FXML private ImageView coD2;
    @FXML private ImageView seD2;
    @FXML private ImageView shD2;
    @FXML private ImageView stD2;
    @FXML private ImageView card3;
    @FXML private ImageView oldCard31;
    @FXML private Text winPointsOldCard31;
    @FXML private ImageView oldCard32;
    @FXML private Text winPointsOldCard32;
    @FXML private Text coinD3;
    @FXML private Text shieldD3;
    @FXML private Text servantD3;
    @FXML private Text stoneD3;
    @FXML private ImageView coD3;
    @FXML private ImageView seD3;
    @FXML private ImageView shD3;
    @FXML private ImageView stD3;
    @FXML private Text shieldPaycheck;
    @FXML private Text stonePaycheck;
    @FXML private Text coinPaycheck;
    @FXML private Text servantPaycheck;
    @FXML private Group pane;
    @FXML private Text playerName;

    private FadeTransition fadeIn = new FadeTransition(Duration.millis(200));
    private FadeTransition fadeOut = new FadeTransition(Duration.millis(200));

    /**
     * Initializes the class
     */
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

    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        playerName.setText(offlineInfo.getPlayerName(completeUpdate[0]));
        updateCoffer(Arrays.copyOfRange(completeUpdate, 1, 6));
        updateWarehouse(Arrays.copyOfRange(completeUpdate, 6, 21));
        updatePaycheck(Arrays.copyOfRange(completeUpdate, 75, 85));
        updateBase(Arrays.copyOfRange(completeUpdate, 85, 103));
        updateLeader1(Arrays.copyOfRange(completeUpdate,107, 122));
        updateLeader2(Arrays.copyOfRange(completeUpdate, 122, 137));
        updateDevelopment1(Arrays.copyOfRange(completeUpdate, 21, 39));
        updateDevelopment2(Arrays.copyOfRange(completeUpdate, 39, 57));
        updateDevelopment3(Arrays.copyOfRange(completeUpdate, 57, 75));
    }

    /**
     * Updates opponent's coffer
     * @param arr array made of integer
     */
    private void updateCoffer(int[] arr){
        coinCoffer.setText(valueOf(arr[0]));
        servantCoffer.setText(valueOf(arr[1]));
        shieldCoffer.setText(valueOf(arr[2]));
        stoneCoffer.setText(valueOf(arr[3]));
    }

    /**
     * Updates opponent's warehouse
     * @param arr array made of integer
     */
    private void updateWarehouse(int[] arr){
        //put images in arrays to make it easier to iterate through them
        ArrayList<ImageView> third = new ArrayList<>(Arrays.asList(a1, b1, c1));
        ArrayList<ImageView> second = new ArrayList<>(Arrays.asList(a2, b2));
        ArrayList<ImageView> first = new ArrayList<>(Arrays.asList(a3));

        //get the supply and quantity contained in each row
        Pair<WarehouseObjectTypeController, Integer> firstSupplies = getContainedSupplies(Arrays.copyOfRange(arr, 0, 4));
        Pair<WarehouseObjectTypeController, Integer> secondSupplies = getContainedSupplies(Arrays.copyOfRange(arr, 5, 9));
        Pair<WarehouseObjectTypeController, Integer> thirdSupplies = getContainedSupplies(Arrays.copyOfRange(arr, 10, 14));

        //make everything invisible
        a1.setVisible(false);
        b1.setVisible(false);
        c1.setVisible(false);
        a2.setVisible(false);
        b2.setVisible(false);
        a3.setVisible(false);

        //fill the rows (assumed the model doesn't fail)
        for (int i = 0; i < firstSupplies.second; ++i){
            first.get(i).setImage(new Image(firstSupplies.first.getUrl()));
            first.get(i).setVisible(true);
        }

        for (int i = 0; i < secondSupplies.second; ++i){
            second.get(i).setImage(new Image(secondSupplies.first.getUrl()));
            second.get(i).setVisible(true);
        }

        for (int i = 0; i < thirdSupplies.second; ++i){
            third.get(i).setImage(new Image(thirdSupplies.first.getUrl()));
            third.get(i).setVisible(true);
        }
    }

    /**
     * Updates opponent's paycheck
     * @param arr array made of integer
     */
    private void updatePaycheck(int[] arr){
        coinPaycheck.setText(valueOf(arr[0] + arr[5]));
        servantPaycheck.setText(valueOf(arr[1] + arr[6]));
        shieldPaycheck.setText(valueOf(arr[2] + arr[7]));
        stonePaycheck.setText(valueOf(arr[3] + arr[8]));
    }

    /**
     * Updates opponent's base production
     * @param arr array made of integer
     */
    private void updateBase(int[] arr){
        in1.setImage(new Image(WarehouseObjectTypeController.getTypeByNumber(arr[5]).getUrl()));
        in2.setImage(new Image(WarehouseObjectTypeController.getTypeByNumber(arr[6]).getUrl()));
        out.setImage(new Image(WarehouseObjectTypeController.getTypeByNumber(arr[12]).getUrl()));

        coinBase.setText(String.valueOf(arr[13]));
        servantBase.setText(String.valueOf(arr[14]));
        shieldBase.setText(String.valueOf(arr[15]));
        stoneBase.setText(String.valueOf(arr[16]));
    }

    /**
     * Updates opponent's first leader card
     * @param arr array made of integer
     */
    private void updateLeader1(int[] arr){

        int leaderCardId = arr[0];
        if(leaderCardId == 0){
            return;
        }

        leader1.setImage(new Image(LeaderCard.getUrl(leaderCardId)));

        producerPane1.setVisible(false);

        //if the leader is discarded, make him black and white. If it is producer or depot show the correct panes.
        if (arr[1] == 0){
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(0.0); //black and white
            leader1.setEffect(tmp);
            leader1group.setVisible(false);
        }
        else if (arr[1] == 2){
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(-1.0); //black and white
            leader1.setEffect(tmp);
            leader1group.setVisible(true);
        }
        else if (LeaderCard.getAbility(leaderCardId) instanceof Producer){
            leader1.setEffect(null); //remove any possible effect
            producerPane1.setVisible(true);
            depotPane1.setVisible(false);
            leader1group.setVisible(true);
            mutableProduction1.setVisible(true);
            mutableProduction1.setImage(new Image(getTypeByNumber(arr[4]).getUrl()));

            //set producer pane
            coin1.setText(String.valueOf(arr[5]));
            servant1.setText(String.valueOf(arr[6]));
            shield1.setText(String.valueOf(arr[7]));
            stone1.setText(String.valueOf(arr[8]));
        }
        else if (LeaderCard.getAbility(leaderCardId) instanceof Depot){
            leader1.setEffect(null); //remove any possible effect
            depotPane1.setVisible(true);
            producerPane1.setVisible(false);
            leader1group.setVisible(true);

            Pair<WarehouseObjectTypeController, Integer> contained = getContainedSupplies(Arrays.copyOfRange(arr, 10, 14));
            if (contained.second == 0) {
                depot11.setVisible(false);
                depot12.setVisible(false);
            }
            if (contained.second >= 1){
                depot11.setImage(new Image(contained.first.getUrl()));
                depot11.setVisible(true);
                depot12.setVisible(false);
            }
            if (contained.second >= 2){
                depot12.setImage(new Image(contained.first.getUrl()));
                depot12.setVisible(true);
            }
        }
        else {
            leader1.setEffect(null); //remove any possible effect
            producerPane1.setVisible(false);
            depotPane1.setVisible(false);
            leader1group.setVisible(true);

            //set depot pane
            depot11.setVisible(false);
            depot12.setVisible(false);

            Pair<WarehouseObjectTypeController, Integer> contained = getContainedSupplies(Arrays.copyOfRange(arr, 10, 14));
            if (contained.second >= 1){
                depot11.setImage(new Image(contained.first.getUrl()));
                depot11.setVisible(true);
            }
            if (contained.second >= 2){
                depot12.setImage(new Image(contained.first.getUrl()));
                depot12.setVisible(true);
            }
        }
    }

    /**
     * Updates opponent's second leader card
     * @param arr array made of integer
     */
    private void updateLeader2(int[] arr){
        int leaderCardId = arr[0];
        if(leaderCardId == 0){
            return;
        }

        leader2.setImage(new Image(LeaderCard.getUrl(leaderCardId)));

        producerPane2.setVisible(false);

        //if the leader is discarded, make him black and white. If it is producer or depot show the correct panes.
        if (arr[1] == 0){
            leader2group.setVisible(false);
        }
        else if (arr[1] == 2){
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(-1.0); //black and white
            leader2.setEffect(tmp);
            leader2group.setVisible(true);
        }
        else if (LeaderCard.getAbility(leaderCardId) instanceof Producer){
            leader2.setEffect(null); //remove any possible effect
            producerPane2.setVisible(true);
            depotPane2.setVisible(false);
            leader2group.setVisible(true);
            mutableProduction2.setVisible(true);
            mutableProduction2.setImage(new Image(getTypeByNumber(arr[4]).getUrl()));

            //set producer pane
            coin2.setText(String.valueOf(arr[5]));
            servant2.setText(String.valueOf(arr[6]));
            shield2.setText(String.valueOf(arr[7]));
            stone2.setText(String.valueOf(arr[8]));
        }
        else if (LeaderCard.getAbility(leaderCardId) instanceof Depot){
            leader2.setEffect(null); //remove any possible effect
            depotPane2.setVisible(true);
            producerPane2.setVisible(false);
            leader2group.setVisible(true);

            Pair<WarehouseObjectTypeController, Integer> contained = getContainedSupplies(Arrays.copyOfRange(arr, 10, 14));
            if (contained.second == 0) {
                depot21.setVisible(false);
                depot22.setVisible(false);
            }
            if (contained.second >= 1){
                depot21.setImage(new Image(contained.first.getUrl()));
                depot21.setVisible(true);
                depot22.setVisible(false);
            }
            if (contained.second == 2){
                depot22.setImage(new Image(contained.first.getUrl()));
                depot22.setVisible(true);
            }
        }
        else {
            leader2.setEffect(null); //remove any possible effect
            producerPane2.setVisible(false);
            depotPane2.setVisible(false);
            leader2group.setVisible(true);

            //set depot pane
            depot21.setVisible(false);
            depot22.setVisible(false);

            Pair<WarehouseObjectTypeController, Integer> contained = getContainedSupplies(Arrays.copyOfRange(arr, 10, 14));
            if (contained.second >= 1){
                depot21.setImage(new Image(contained.first.getUrl()));
                depot21.setVisible(true);
            }
            if (contained.second >= 2){
                depot22.setImage(new Image(contained.first.getUrl()));
                depot22.setVisible(true);
            }
        }
    }

    /**
     * Update opponent's development space 1
     * @param arr array made of integer
     */
    private void updateDevelopment1(int[] arr){
        if(arr[2] != 0) {
            card1.setImage(new Image(getUrl(arr[2])));
            oldCard11.setVisible(true);
            oldCard11.setImage(new Image(getDot(getCategory(arr[0]))));
            winPointsOldCard11.setText(String.valueOf(getWinPoints(arr[0])));
            oldCard12.setVisible(true);
            oldCard12.setImage(new Image(getDot(getCategory(arr[1]))));
            winPointsOldCard12.setText(String.valueOf(getWinPoints(arr[1])));
        }
        else if(arr[1] != 0) {
            card1.setImage(new Image(getUrl(arr[1])));
            oldCard11.setVisible(true);
            oldCard11.setImage(new Image(getDot(getCategory(arr[0]))));
            winPointsOldCard11.setText(String.valueOf(getWinPoints(arr[0])));
            oldCard12.setVisible(false);
            winPointsOldCard12.setText(String.valueOf(""));
        }
        else if(arr[0] != 0){
            card1.setImage(new Image(getUrl(arr[0])));
            oldCard11.setVisible(false);
            winPointsOldCard11.setText(String.valueOf(""));
            oldCard12.setVisible(false);
            winPointsOldCard12.setText(String.valueOf(""));
        }
        else {
            oldCard11.setVisible(false);
            winPointsOldCard11.setText(String.valueOf(""));
            oldCard12.setVisible(false);
            winPointsOldCard12.setText(String.valueOf(""));
            coD1.setVisible(false);
            stD1.setVisible(false);
            shD1.setVisible(false);
            seD1.setVisible(false);
            coinD1.setText(String.valueOf(""));
            servantD1.setText(String.valueOf(""));
            shieldD1.setText(String.valueOf(""));
            stoneD1.setText(String.valueOf(""));
            return;
        }

        coD1.setVisible(true);
        stD1.setVisible(true);
        shD1.setVisible(true);
        seD1.setVisible(true);
        coinD1.setText(String.valueOf(arr[13]));
        servantD1.setText(String.valueOf(arr[14]));
        shieldD1.setText(String.valueOf(arr[15]));
        stoneD1.setText(String.valueOf(arr[16]));
    }

    /**
     * Update opponent's development space 2
     * @param arr array made of integer
     */
    private void updateDevelopment2(int[] arr){
        if(arr[2] != 0) {
            card2.setImage(new Image(getUrl(arr[2])));
            oldCard21.setVisible(true);
            oldCard21.setImage(new Image(getDot(getCategory(arr[0]))));
            winPointsOldCard21.setText(String.valueOf(getWinPoints(arr[0])));
            oldCard22.setVisible(true);
            oldCard22.setImage(new Image(getDot(getCategory(arr[1]))));
            winPointsOldCard22.setText(String.valueOf(getWinPoints(arr[1])));
        }
        else if(arr[1] != 0) {
            card2.setImage(new Image(getUrl(arr[1])));
            oldCard21.setVisible(true);
            oldCard21.setImage(new Image(getDot(getCategory(arr[0]))));
            winPointsOldCard21.setText(String.valueOf(getWinPoints(arr[0])));
            oldCard22.setVisible(false);
            winPointsOldCard22.setText(String.valueOf(""));
        }
        else if(arr[0] != 0){
            card2.setImage(new Image(getUrl(arr[0])));
            oldCard21.setVisible(false);
            winPointsOldCard21.setText(String.valueOf(""));
            oldCard22.setVisible(false);
            winPointsOldCard22.setText(String.valueOf(""));
        }
        else {
            oldCard21.setVisible(false);
            winPointsOldCard21.setText(String.valueOf(""));
            oldCard22.setVisible(false);
            winPointsOldCard22.setText(String.valueOf(""));
            coD2.setVisible(false);
            stD2.setVisible(false);
            shD2.setVisible(false);
            seD2.setVisible(false);
            coinD2.setText(String.valueOf(""));
            servantD2.setText(String.valueOf(""));
            shieldD2.setText(String.valueOf(""));
            stoneD2.setText(String.valueOf(""));
            return;
        }

        coD2.setVisible(true);
        stD2.setVisible(true);
        shD2.setVisible(true);
        seD2.setVisible(true);
        coinD2.setText(String.valueOf(arr[13]));
        servantD2.setText(String.valueOf(arr[14]));
        shieldD2.setText(String.valueOf(arr[15]));
        stoneD2.setText(String.valueOf(arr[16]));
    }

    /**
     * Update opponent's development space 3
     * @param arr array made of integer
     */
    private void updateDevelopment3(int[] arr){
        if(arr[2] != 0) {
            card3.setImage(new Image(getUrl(arr[2])));
            oldCard31.setVisible(true);
            oldCard31.setImage(new Image(getDot(getCategory(arr[0]))));
            winPointsOldCard31.setText(String.valueOf(getWinPoints(arr[0])));
            oldCard32.setVisible(true);
            oldCard32.setImage(new Image(getDot(getCategory(arr[1]))));
            winPointsOldCard32.setText(String.valueOf(getWinPoints(arr[1])));
        }
        else if(arr[1] != 0) {
            card3.setImage(new Image(getUrl(arr[1])));
            oldCard31.setVisible(true);
            oldCard31.setImage(new Image(getDot(getCategory(arr[0]))));
            winPointsOldCard31.setText(String.valueOf(getWinPoints(arr[0])));
            oldCard32.setVisible(false);
            winPointsOldCard32.setText(String.valueOf(""));
        }
        else if(arr[0] != 0){
            card3.setImage(new Image(getUrl(arr[0])));
            oldCard31.setVisible(false);
            winPointsOldCard31.setText(String.valueOf(""));
            oldCard32.setVisible(false);
            winPointsOldCard32.setText(String.valueOf(""));
        }
        else {
            oldCard31.setVisible(false);
            winPointsOldCard31.setText(String.valueOf(""));
            oldCard32.setVisible(false);
            winPointsOldCard32.setText(String.valueOf(""));
            coD3.setVisible(false);
            stD3.setVisible(false);
            shD3.setVisible(false);
            seD3.setVisible(false);
            coinD3.setText(String.valueOf(""));
            servantD3.setText(String.valueOf(""));
            shieldD3.setText(String.valueOf(""));
            stoneD3.setText(String.valueOf(""));
            return;
        }

        coD3.setVisible(true);
        stD3.setVisible(true);
        shD3.setVisible(true);
        seD3.setVisible(true);
        coinD3.setText(String.valueOf(arr[13]));
        servantD3.setText(String.valueOf(arr[14]));
        shieldD3.setText(String.valueOf(arr[15]));
        stoneD3.setText(String.valueOf(arr[16]));
    }

    /**
     * Gets the category of the underlying card in the development space
     * @param cat card category
     * @return the colour of the card
     */
    private String getDot(CardCategory cat) {
        if(cat == CardCategory.GREEN)
            return "pictures/miscellaneous/greenDot.png";
        else if(cat == CardCategory.VIOLET)
            return "pictures/miscellaneous/violetDot.png";
        else if(cat == CardCategory.BLUE)
            return "pictures/miscellaneous/cyanDot.png";
        else if(cat == CardCategory.YELLOW)
            return "pictures/miscellaneous/yellowDot.png";
        else
            return null;
    }

    /**
     * Shows opponent's pane
     */
    public void show(){
        pane.setVisible(true);
        fadeIn.play();
    }

    /**
     * Hides opponent's pane
     */
    public void hide(){
        fadeOut.play();
    }


}
