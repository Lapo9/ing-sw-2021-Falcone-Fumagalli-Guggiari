package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.gui.WarehouseObjectTypeController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.*;

public class BaseProductionController extends SubSceneController {

    @FXML private ImageView out;
    @FXML private ImageView in1;
    @FXML private ImageView in2;
    @FXML private ImageView outUp;
    @FXML private ImageView outDown;
    @FXML private ImageView in2up;
    @FXML private ImageView in1up;
    @FXML private ImageView in2down;
    @FXML private ImageView in1down;
    @FXML private ImageView checkBoc;
    @FXML private ImageView produce;
    @FXML private ImageView activeSquare;
    @FXML private Text coin;
    @FXML private Text servant;
    @FXML private Text shield;
    @FXML private Text stone;

    private WarehouseObjectTypeController input1 = COIN;
    private WarehouseObjectTypeController input2 = COIN;
    private WarehouseObjectTypeController output = COIN;

    @Override
    public void initialize() {
        super.initialize();

        in1.setImage(new Image(input1.getUrl()));
        in2.setImage(new Image(input2.getUrl()));
        out.setImage(new Image(output.getUrl()));

        coin.setText("0");
        servant.setText("0");
        shield.setText("0");
        stone.setText("0");
    }

    @Override
    public void update(int[] completeUpdate) {
        input1 = WarehouseObjectTypeController.getTypeByNumber(completeUpdate[5]);
        input2 = WarehouseObjectTypeController.getTypeByNumber(completeUpdate[6]);
        output = WarehouseObjectTypeController.getTypeByNumber(completeUpdate[12]);

        coin.setText(String.valueOf(completeUpdate[13]));
        servant.setText(String.valueOf(completeUpdate[14]));
        shield.setText(String.valueOf(completeUpdate[15]));
        stone.setText(String.valueOf(completeUpdate[16]));
    }


    public void reset(){
        activeSquare.setVisible(false);
    }

    public void setActive(){
        activeSquare.setVisible(true);
    }


    @FXML
    void checkBoxClicked() {
        controllerInterpreter.execute("reset");
        produce.setVisible(!produce.isVisible());
        offlineInfo.setProduction("base", produce.isVisible());
    }

    @FXML
    void in1downClicked() {
        controllerInterpreter.execute("reset");
        input1 = next(input1);
        in1.setImage(new Image(input1.getUrl()));
        userInterpreter.execute("swapBase 1 " + input1.toString().toLowerCase());
    }

    @FXML
    void in1upClicked() {
        controllerInterpreter.execute("reset");
        input1 = prev(input1);
        in1.setImage(new Image(input1.getUrl()));
        userInterpreter.execute("swapBase 1 " + input1.toString().toLowerCase());
    }

    @FXML
    void in2downClicked() {
        controllerInterpreter.execute("reset");
        input2 = next(input2);
        in2.setImage(new Image(input2.getUrl()));
        userInterpreter.execute("swapBase 2 " + input2.toString().toLowerCase());
    }

    @FXML
    void in2upClicked() {
        controllerInterpreter.execute("reset");
        input2 = prev(input2);
        in2.setImage(new Image(input2.getUrl()));
        userInterpreter.execute("swapBase 2 " + input2.toString().toLowerCase());
    }

    @FXML
    void outDownClicked() {
        controllerInterpreter.execute("reset");
        output = next(output);
        out.setImage(new Image(output.getUrl()));
        userInterpreter.execute("swapBase 3 " + output.toString().toLowerCase());
    }

    @FXML
    void outUpClicked() {
        controllerInterpreter.execute("reset");
        output = prev(output);
        out.setImage(new Image(output.getUrl()));
        userInterpreter.execute("swapBase 3 " + output.toString().toLowerCase());
    }

    @FXML
    void coinClicked() {
        if(coin.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("coin base");
        userInterpreter.execute("selected coin base");
    }

    @FXML
    void servantClicked() {
        if(servant.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("servant base");
        userInterpreter.execute("selected servant base");
    }

    @FXML
    void shieldClicked() {
        if(shield.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("shield base");
        userInterpreter.execute("selected shield base");
    }

    @FXML
    void stoneClicked() {
        if(stone.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("stone base");
        userInterpreter.execute("selected stone base");
    }

    @FXML
    void activeSquareClicked() {
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }
        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " base");
        controllerInterpreter.execute("reset");
    }
}

