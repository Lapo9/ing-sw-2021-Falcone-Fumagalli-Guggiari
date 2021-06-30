package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Represents the coffer on the player dashboard.
 */
public class CofferController extends SubSceneController{

    @FXML private Text shield;
    @FXML private Text stone;
    @FXML private Text coin;
    @FXML private Text servant;
    @FXML private ImageView activeSquare;


    /**
     *  Initialize the class with default values
     */
    @Override
    public void initialize() {
        super.initialize();

        servant.setText("0");
        shield.setText("0");
        stone.setText("0");
        coin.setText("0");
    }


    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        coin.setText(String.valueOf(completeUpdate[0]));
        servant.setText(String.valueOf(completeUpdate[1]));
        shield.setText(String.valueOf(completeUpdate[2]));
        stone.setText(String.valueOf(completeUpdate[3]));
    }


    /**
     * Sets the activeSquare image not visible
     */
    public void reset(){
        activeSquare.setVisible(false);
    }


    /**
     * Sets the activeSquare image visible
     */
    public void setActive(){
        activeSquare.setVisible(true);
    }


    /**
     * Move the selected item of the OfflineInfo class to the coffer
     */
    @FXML
    void activeSquareClicked() {
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }
        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " coffer");
        controllerInterpreter.execute("reset");
    }

    /**
     * Sets to 'coin' the selected item in the OfflineInfo class
     */
    @FXML
    void coinClicked() {
        if(coin.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("coin coffer");
        userInterpreter.execute("selected coin coffer");
    }

    /**
     * Sets to 'servant' the selected item in the OfflineInfo class
     */
    @FXML
    void servantClicked() {
        if(servant.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("servant coffer");
        userInterpreter.execute("selected servant coffer");
    }

    /**
     * Sets to 'shield' the selected item in the OfflineInfo class
     */
    @FXML
    void shieldClicked() {
        if(shield.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("shield coffer");
        userInterpreter.execute("selected shield coffer");
    }

    /**
     * Sets to 'stone' the selected item in the OfflineInfo class
     */
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
