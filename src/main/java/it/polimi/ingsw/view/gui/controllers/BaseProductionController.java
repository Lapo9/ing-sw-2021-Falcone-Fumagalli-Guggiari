package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.gui.WarehouseObjectTypeController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    private WarehouseObjectTypeController input1 = COIN;
    private WarehouseObjectTypeController input2 = COIN;
    private WarehouseObjectTypeController output = COIN;

    @Override
    public void initialize() {
        super.initialize();

        in1.setImage(new Image(input1.getUrl()));
        in2.setImage(new Image(input2.getUrl()));
        out.setImage(new Image(output.getUrl()));
    }

    @Override
    public void update(int[] completeUpdate) {

    }

    @FXML
    void checkBoxClicked() {
        controllerInterpreter.execute("reset");
        produce.setVisible(!produce.isVisible());
        offlineInfo.setProduction(id, produce.isVisible());
    }

    @FXML
    void in1downClicked() {
        controllerInterpreter.execute("reset");
        input1 = next(input1);
        in1.setImage(new Image(input1.getUrl()));
        userInterpreter.execute("swapBase 1");
    }

    @FXML
    void in1upClicked() {
        controllerInterpreter.execute("reset");
        input1 = prev(input1);
        in1.setImage(new Image(input1.getUrl()));
        userInterpreter.execute("swapBase 1");
    }

    @FXML
    void in2downClicked() {
        controllerInterpreter.execute("reset");
        input2 = next(input2);
        in2.setImage(new Image(input2.getUrl()));
        userInterpreter.execute("swapBase 2");
    }

    @FXML
    void in2upClicked() {
        controllerInterpreter.execute("reset");
        input2 = prev(input2);
        in2.setImage(new Image(input2.getUrl()));
        userInterpreter.execute("swapBase 2");
    }

    @FXML
    void outDownClicked() {
        controllerInterpreter.execute("reset");
        output = next(output);
        out.setImage(new Image(output.getUrl()));
        userInterpreter.execute("swapBase 3");
    }

    @FXML
    void outUpClicked() {
        controllerInterpreter.execute("reset");
        output = prev(output);
        out.setImage(new Image(output.getUrl()));
        controllerInterpreter.execute("swapBase 3");
    }

}

