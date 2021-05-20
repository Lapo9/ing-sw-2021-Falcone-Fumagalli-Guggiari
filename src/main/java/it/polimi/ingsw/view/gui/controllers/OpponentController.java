package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.view.cli.OfflineInfo;
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

import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.getContainedSupplies;
import static java.lang.String.valueOf;

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


    private void updateCoffer(int[] arr){
        coinCoffer.setText(valueOf(arr[0]));
        servantCoffer.setText(valueOf(arr[1]));
        shieldCoffer.setText(valueOf(arr[2]));
        stoneCoffer.setText(valueOf(arr[3]));
    }

    private void updateWarehouse(int[] arr){
        //put images in arrays to make it easier to iterate through them
        ArrayList<ImageView> first = new ArrayList<>(Arrays.asList(a1, b1, c1));
        ArrayList<ImageView> second = new ArrayList<>(Arrays.asList(a2, b2));
        ArrayList<ImageView> third = new ArrayList<>(Arrays.asList(a3));

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

    private void updatePaycheck(int[] arr){
        coinPaycheck.setText(valueOf(arr[0] + arr[5]));
        coinPaycheck.setText(valueOf(arr[1] + arr[6]));
        coinPaycheck.setText(valueOf(arr[2] + arr[7]));
        coinPaycheck.setText(valueOf(arr[3] + arr[8]));
    }

    private void updateBase(int[] arr){
        //TODO
    }

    private void updateLeader1(int[] arr){
        int leaderCardId = arr[0];
        if(leaderCardId == 0){
            return;
        }

        leader1.setImage(new Image(LeaderCard.getUrl(leaderCardId)));

        //if the leader is inactive, make him black and white. If it is discarded hide him. If it is producer or depot show the correct panes.
        if (arr[1] == 0){
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(0.0); //black and white
            leader1.setEffect(tmp);
            leader1group.setVisible(true);
        }
        else if (arr[1] == 2){
            leader1group.setVisible(false);
        }
        else if (LeaderCard.getAbility(leaderCardId) instanceof Producer){
            leader1.setEffect(null); //remove any possible effect
            producerPane1.setVisible(true);
            depotPane1.setVisible(false);
            leader1group.setVisible(true);

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

    private void updateLeader2(int[] arr){
        int leaderCardId = arr[0];
        if(leaderCardId == 0){
            return;
        }

        leader2.setImage(new Image(LeaderCard.getUrl(leaderCardId)));

        //if the leader is inactive, make him black and white. If it is discarded hide him. If it is producer or depot show the correct panes.
        if (arr[1] == 0){
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(0.0); //black and white
            leader2.setEffect(tmp);
            leader2group.setVisible(true);
        }
        else if (arr[1] == 2){
            leader2group.setVisible(false);
        }
        else if (LeaderCard.getAbility(leaderCardId) instanceof Producer){
            leader2.setEffect(null); //remove any possible effect
            producerPane2.setVisible(true);
            depotPane2.setVisible(false);
            leader2group.setVisible(true);

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

    private void updateDevelopment1(int[] arr){
        //TODO
    }

    private void updateDevelopment2(int[] arr){
        //TODO
    }

    private void updateDevelopment3(int[] arr){
        //TODO
    }


    public void show(){
        pane.setVisible(true);
        fadeIn.play();
    }


    public void hide(){
        fadeOut.play();
    }


}
