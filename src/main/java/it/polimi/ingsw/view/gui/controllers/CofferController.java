package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CofferController extends SubSceneController{

    @FXML private Text shield;
    @FXML private Text stone;
    @FXML private Text coin;
    @FXML private Text servant;
    @FXML private ImageView activeSquare;


    @Override
    public void initialize() {
        super.initialize();

        servant.setText("0");
        shield.setText("0");
        stone.setText("0");
        coin.setText("0");
    }


    @Override
    public void update(int[] completeUpdate) {
        coin.setText(String.valueOf(completeUpdate[0]));
        servant.setText(String.valueOf(completeUpdate[1]));
        shield.setText(String.valueOf(completeUpdate[2]));
        stone.setText(String.valueOf(completeUpdate[3]));
    }


    public void reset(){
        activeSquare.setVisible(false);
    }


    public void setActive(){
        activeSquare.setVisible(true);
    }


    @FXML
    void activeSquareClicked() {
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }
        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " coffer");
        controllerInterpreter.execute("reset");
    }

    @FXML
    void coinClicked() {
        if(coin.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("coin coffer");
        userInterpreter.execute("selected coin coffer");
    }

    @FXML
    void servantClicked() {
        if(servant.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("servant coffer");
        userInterpreter.execute("selected servant coffer");
    }

    @FXML
    void shieldClicked() {
        if(shield.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("shield coffer");
        userInterpreter.execute("selected shield coffer");
    }

    @FXML
    void stoneClicked() {
        if(stone.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("stone coffer");
        userInterpreter.execute("selected stone coffer");
    }

}
