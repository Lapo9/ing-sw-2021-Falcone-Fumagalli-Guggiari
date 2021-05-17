package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DevelopmentSpaceController extends SubSceneController {

    @FXML private ImageView card;
    @FXML private ImageView oldCard1;
    @FXML private Text winPointsOldCard1;
    @FXML private ImageView oldCard2;
    @FXML private Text winPointsOldCard2;
    @FXML private ImageView checkBox;
    @FXML private ImageView produce;
    @FXML private Text coin;
    @FXML private Text shield;
    @FXML private Text servant;
    @FXML private Text stone;
    @FXML private ImageView activeSquare;


    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void update(int[] completeUpdate) {
        
    }
    
    

    @FXML
    void activeSquareClicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " " + id);
        controllerInterpreter.execute("reset");
    }

    @FXML
    void checkboxClicked() {
        controllerInterpreter.execute("reset");
        produce.setVisible(!produce.isVisible());
        offlineInfo.setProduction(id, produce.isVisible());
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
        offlineInfo.setSelectedItem("servant  " + id);
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

}
