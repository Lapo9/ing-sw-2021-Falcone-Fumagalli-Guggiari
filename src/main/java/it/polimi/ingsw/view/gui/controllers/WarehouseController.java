package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.view.gui.WarehouseObjectTypeController;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.util.Locale;


/*
            a3
        a2      b2
    a1      b1      c1
*/

public class WarehouseController extends SubSceneController {

    @FXML private ImageView a1;
    @FXML private ImageView b1;
    @FXML private ImageView c1;
    @FXML private ImageView a2;
    @FXML private ImageView b2;
    @FXML private ImageView a3;
    @FXML private ImageView swap3;
    @FXML private ImageView swap2;
    @FXML private ImageView swap1;
    @FXML private ImageView active3;
    @FXML private ImageView active2;
    @FXML private ImageView active1;


    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void update(int[] completeUpdate) {

    }


    @FXML
    void a1Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh1");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void b1Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh1");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void c1Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh1");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void a2Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh2");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }


    @FXML
    void b2Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh2");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }



    @FXML
    void a3Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh3");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }


    @FXML
    void active1Clicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " wh1");
        controllerInterpreter.execute("reset");
    }

    @FXML
    void active2Clicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " wh2");
        controllerInterpreter.execute("reset");
    }

    @FXML
    void active3Clicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " wh3");
        controllerInterpreter.execute("reset");
    }

    @FXML
    void swap1Clicked() {
        //if no rows were selected, select this row
        if (offlineInfo.getSelectedWarehouseRow().equals("")){
            offlineInfo.setSelectedWarehouseRow("1");
        }
        else {
            userInterpreter.execute("swapRows 1 " + offlineInfo.getSelectedWarehouseRow());
        }
    }

    @FXML
    void swap2Clicked() {
        //if no rows were selected, select this row
        if (offlineInfo.getSelectedWarehouseRow().equals("")){
            offlineInfo.setSelectedWarehouseRow("2");
        }
        else {
            userInterpreter.execute("swapRows 2 " + offlineInfo.getSelectedWarehouseRow());
        }
    }

    @FXML
    void swap3Clicked() {
        //if no rows were selected, select this row
        if (offlineInfo.getSelectedWarehouseRow().equals("")){
            offlineInfo.setSelectedWarehouseRow("3");
        }
        else {
            userInterpreter.execute("swapRows 3 " + offlineInfo.getSelectedWarehouseRow());
        }
    }

}
