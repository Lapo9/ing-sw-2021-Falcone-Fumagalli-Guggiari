package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;



public class PaycheckController extends SubSceneController {

    @FXML private Text stoneDepot;
    @FXML private Text shieldDepot;
    @FXML private Text servantDepot;
    @FXML private Text coinDepot;
    @FXML private Text stoneStrongbox;
    @FXML private Text shieldStrongbox;
    @FXML private Text servantStrongbox;
    @FXML private Text coinStrongbox;
    @FXML private ImageView activeSquare;


    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void update(int[] completeUpdate) {
        coinStrongbox.setText(String.valueOf(completeUpdate[0]));
        servantStrongbox.setText(String.valueOf(completeUpdate[1]));
        shieldStrongbox.setText(String.valueOf(completeUpdate[2]));
        stoneStrongbox.setText(String.valueOf(completeUpdate[3]));

        coinDepot.setText(String.valueOf(completeUpdate[5]));
        servantDepot.setText(String.valueOf(completeUpdate[6]));
        shieldDepot.setText(String.valueOf(completeUpdate[7]));
        stoneDepot.setText(String.valueOf(completeUpdate[8]));
    }



    public void reset(){
        activeSquare.setVisible(false);
    }


    public void setActive() {
        activeSquare.setVisible(true);
    }


    @FXML
    void activeSquareClicked() {
        if (offlineInfo.getSelectedItem().isBlank()) {
            controllerInterpreter.execute("reset");
            return;
        }
        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " paycheck");
        controllerInterpreter.execute("reset");
    }


    @FXML
    void coinClicked() {
        if(Integer.parseInt(coinDepot.getText()) + Integer.parseInt(coinStrongbox.getText()) == 0){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("coin paycheck");
        userInterpreter.execute("select coin");
    }

    @FXML
    void servantClicked() {
        if(Integer.parseInt(servantDepot.getText()) + Integer.parseInt(servantStrongbox.getText()) == 0){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("servant paycheck");
        userInterpreter.execute("select servant");
    }

    @FXML
    void shieldClicked() {
        if(Integer.parseInt(shieldDepot.getText()) + Integer.parseInt(shieldStrongbox.getText()) == 0){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("shield paycheck");
        userInterpreter.execute("select shield");
    }

    @FXML
    void stoneClicked() {
        if(Integer.parseInt(stoneDepot.getText()) + Integer.parseInt(stoneStrongbox.getText()) == 0){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("stone paycheck");
        userInterpreter.execute("select stone");
    }

}
