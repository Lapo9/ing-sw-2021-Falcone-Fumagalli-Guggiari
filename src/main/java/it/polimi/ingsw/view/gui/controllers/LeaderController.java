package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.view.cli.OfflineInfo;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Locale;


public class LeaderController extends SubSceneController {

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


    @Override
    public void initialize() {
        super.initialize();
        number = 1;// Integer.parseInt(id.substring(id.length() - 1)); //assuming the ID is in the form of leaderX
    }

    @Override
    public void update(int[] completeUpdate) {

    }

    @FXML
    void activateClicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("activateLeader" + number);
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
        userInterpreter.execute("discardLeader" + number); //assuming the ID is in the form of leaderX
    }

    @FXML
    void leaderActiveClicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset"); //TODO add reset to controllerInterpreter
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
        controllerInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void servantClicked() {
        //check if there is a supply to pick
        if(servant.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("servant " + id);
        controllerInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void shieldClicked() {
        //check if there is a supply to pick
        if(shield.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("shield " + id);
        controllerInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void stoneClicked() {
        //check if there is a supply to pick
        if(stone.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("stone " + id);
        controllerInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

}