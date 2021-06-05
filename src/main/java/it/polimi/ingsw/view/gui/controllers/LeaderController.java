package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.gui.WarehouseObjectTypeController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Locale;

import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.getContainedSupplies;


public class LeaderController extends SubSceneController {

    @FXML private Group leaderGroup;
    @FXML private ImageView leader;
    @FXML private Group producerPane;
    @FXML private Text coin;
    @FXML private Text servant;
    @FXML private Text shield;
    @FXML private Text stone;
    @FXML private ImageView checkBox;
    @FXML private ImageView produce;
    @FXML private ImageView arrowDown;
    @FXML private ImageView arrowUp;
    @FXML private ImageView mutableProduction;
    @FXML private Group depotPane;
    @FXML private ImageView depot2;
    @FXML private ImageView depot1;
    @FXML private ImageView leaderActive;
    @FXML private Group menu;
    @FXML private ImageView activate;
    @FXML private ImageView discard;

    private int number;
    private int leaderCardId;
    private WarehouseObjectTypeController producerInput = WarehouseObjectTypeController.COIN;


    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void update(int[] completeUpdate) {
        if (number == 0){
            number = Integer.parseInt(id.substring(id.length() - 1)); //assuming the ID is in the form of leaderX
        }

        leaderCardId = completeUpdate[0];
        if(leaderCardId == 0){
            return;
        }

        leader.setImage(new Image(LeaderCard.getUrl(leaderCardId)));

        //if the leader is inactive, make him black and white. If it is discarded hide him. If it is producer or depot show the correct panes.
        if (offlineInfo.getLeaderStatus(number) == OfflineInfo.LeaderStatus.INACTIVE){
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(-1.0); //black and white
            leader.setEffect(tmp);
            leaderGroup.setVisible(true);
            producerPane.setVisible(false);
            depotPane.setVisible(false);
        }
        else if (offlineInfo.getLeaderStatus(number) == OfflineInfo.LeaderStatus.DISCARDED){
            leaderGroup.setVisible(false);
        }
        else if (offlineInfo.getLeaderStatus(number) == OfflineInfo.LeaderStatus.PRODUCER){
            leader.setEffect(null); //remove any possible effect
            producerPane.setVisible(true);
            depotPane.setVisible(false);
            leaderGroup.setVisible(true);
        }
        else if (offlineInfo.getLeaderStatus(number) == OfflineInfo.LeaderStatus.DEPOT){
            leader.setEffect(null); //remove any possible effect
            depotPane.setVisible(true);
            producerPane.setVisible(false);
            leaderGroup.setVisible(true);
        }
        else if (offlineInfo.getLeaderStatus(number) == OfflineInfo.LeaderStatus.OTHER){
            leader.setEffect(null); //remove any possible effect
            producerPane.setVisible(false);
            depotPane.setVisible(false);
            leaderGroup.setVisible(true);
        }


        //set producer pane
        coin.setText(String.valueOf(completeUpdate[5]));
        servant.setText(String.valueOf(completeUpdate[6]));
        shield.setText(String.valueOf(completeUpdate[7]));
        stone.setText(String.valueOf(completeUpdate[8]));


        //set depot pane
        depot1.setVisible(false);
        depot2.setVisible(false);

        Pair<WarehouseObjectTypeController, Integer> contained = getContainedSupplies(Arrays.copyOfRange(completeUpdate, 10, 14));
        if (contained.second >= 1){
            depot1.setImage(new Image(contained.first.getUrl()));
            depot1.setVisible(true);
        }
        if (contained.second >= 2){
            depot2.setImage(new Image(contained.first.getUrl()));
            depot2.setVisible(true);
        }
    }


    public void reset(){
        menu.setVisible(false);
        leaderActive.setVisible(false);
    }


    public void setActive(){
        leaderActive.setVisible(true);
    }



    @FXML
    void activateClicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("activateLeader " + number);
    }

    @FXML
    void checkBoxClicked() {
        controllerInterpreter.execute("reset");
        produce.setVisible(!produce.isVisible());
        offlineInfo.setProduction(id, produce.isVisible());
    }

    @FXML
    void depot1Clicked() {
        //if there are no supplies, it shouldn't be possible to click
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(LeaderCard.getAbilityWOT(leaderCardId).toString().toLowerCase(Locale.ROOT) + " " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void depot2Clicked() {
        //if there are no supplies, it shouldn't be possible to click
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(LeaderCard.getAbilityWOT(leaderCardId).toString().toLowerCase(Locale.ROOT) + " " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void discardClicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("discardLeader " + number); //assuming the ID is in the form of leaderX
    }

    @FXML
    void leaderActiveClicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        if (offlineInfo.getSelectedItem().contains("marble")){
            userInterpreter.execute("moveMarble " + offlineInfo.getSelectedItem().split(" ")[1] + " leader" + number);
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " " + id);
        controllerInterpreter.execute("reset");
    }

    @FXML
    void leaderClicked() {
        controllerInterpreter.execute("reset");
        if (offlineInfo.getLeaderStatus(number) == OfflineInfo.LeaderStatus.INACTIVE){
            menu.setVisible(true);
        }
    }

    @FXML
    void coinClicked() {
        //check if there is a supply to pick
        if(coin.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("coin " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void servantClicked() {
        //check if there is a supply to pick
        if(servant.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("servant " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void shieldClicked() {
        //check if there is a supply to pick
        if(shield.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("shield " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void stoneClicked() {
        //check if there is a supply to pick
        if(stone.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("stone " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void arrowUpClicked(){
        controllerInterpreter.execute("reset");
        producerInput = WarehouseObjectTypeController.next(producerInput);
        mutableProduction.setImage(new Image(producerInput.getUrl()));
        userInterpreter.execute("swapLeader " + number + " " + WarehouseObjectTypeController.getTypeByUrl(producerInput.getUrl()).toString().toLowerCase(Locale.ROOT));
    }

    @FXML
    void arrowDownClicked(){
        controllerInterpreter.execute("reset");
        producerInput = WarehouseObjectTypeController.prev(producerInput);
        mutableProduction.setImage(new Image(producerInput.getUrl()));
        userInterpreter.execute("swapLeader " + number + " " + WarehouseObjectTypeController.getTypeByUrl(producerInput.getUrl()).toString().toLowerCase(Locale.ROOT));
    }

    @FXML
    void leaderEntered(){
        leader.setEffect(null);
    }

    @FXML
    void leaderExited(){
        if (offlineInfo.getLeaderStatus(number) == OfflineInfo.LeaderStatus.INACTIVE){
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(-1.0); //black and white
            leader.setEffect(tmp);
        }
    }

}