package it.polimi.ingsw.view.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.getUrl;

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

    private int input1 = 0;
    private int input2 = 0;
    private int output = 0;

    @Override
    public void initialize() {
        super.initialize();

        in1.setImage(new Image(getUrl(input1)));
        in2.setImage(new Image(getUrl(input2)));
        out.setImage(new Image(getUrl(output)));
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
        controllerInterpreter.execute("swapBase");
        input1++;
        if(input1 == 4)
            input1 = 0;
        in1.setImage(new Image(getUrl(input1)));
    }

    @FXML
    void in1upClicked() {
        controllerInterpreter.execute("swapBase");
        input1--;
        if(input1 == -1)
            input1 = 3;
        in1.setImage(new Image(getUrl(input1)));
    }

    @FXML
    void in2downClicked() {
        controllerInterpreter.execute("swapBase");
        input2++;
        if(input2 == 4)
            input2 = 0;
        in2.setImage(new Image(getUrl(input2)));
    }

    @FXML
    void in2upClicked() {
        controllerInterpreter.execute("swapBase");
        input2--;
        if(input2 == -1)
            input2 = 3;
        in2.setImage(new Image(getUrl(input2)));
    }

    @FXML
    void outDownClicked() {
        controllerInterpreter.execute("swapBase");
        output++;
        if(output == 4)
            output = 0;
        out.setImage(new Image(getUrl(output)));
    }

    @FXML
    void outUpClicked() {
        controllerInterpreter.execute("swapBase");
        output--;
        if(output == -1)
            output = 3;
        out.setImage(new Image(getUrl(output)));
    }

}

