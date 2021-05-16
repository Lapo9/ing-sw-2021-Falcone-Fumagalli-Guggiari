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
        controllerInterpreter.execute("swapBase");
        next(input1);
        in1.setImage(new Image(input1.getUrl()));
    }

    @FXML
    void in1upClicked() {
        controllerInterpreter.execute("swapBase");
        prev(input1);
        in1.setImage(new Image(input1.getUrl()));
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

