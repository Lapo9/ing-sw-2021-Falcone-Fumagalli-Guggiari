package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.view.gui.WarehouseObjectTypeController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.getContainedSupplies;


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
        //put images in arrays to make it easier to iterate through them
        ArrayList<ImageView> third = new ArrayList<>(Arrays.asList(a1, b1, c1));
        ArrayList<ImageView> second = new ArrayList<>(Arrays.asList(a2, b2));
        ArrayList<ImageView> first = new ArrayList<>(Arrays.asList(a3));

        //get the supply and quantity contained in each row
        Pair<WarehouseObjectTypeController, Integer> firstSupplies = getContainedSupplies(Arrays.copyOfRange(completeUpdate, 0, 4));
        Pair<WarehouseObjectTypeController, Integer> secondSupplies = getContainedSupplies(Arrays.copyOfRange(completeUpdate, 5, 9));
        Pair<WarehouseObjectTypeController, Integer> thirdSupplies = getContainedSupplies(Arrays.copyOfRange(completeUpdate, 10, 14));

        clearWarehouse(); //make everything invisible

        //fill the rows (assumed the model doesn't fail)
        for (int i = 0; i < firstSupplies.second; ++i){
            first.get(i).setImage(new Image(firstSupplies.first.getUrl()));
            first.get(i).setVisible(true);
        }

        for (int i = 0; i < secondSupplies.second; ++i){
            second.get(i).setImage(new Image(secondSupplies.first.getUrl()));
            second.get(i).setVisible(true);
        }

        for (int i = 0; i < thirdSupplies.second; ++i){
            third.get(i).setImage(new Image(thirdSupplies.first.getUrl()));
            third.get(i).setVisible(true);
        }
    }



    //makes all of the objects invisible
    private void clearWarehouse() {
        a1.setVisible(false);
        a2.setVisible(false);
        a3.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        c1.setVisible(false);
    }



    public void reset(){
        active1.setVisible(false);
        active2.setVisible(false);
        active3.setVisible(false);
    }


    public void setActive(int i){
        switch (i){
            case 1:
                active1.setVisible(true);
                break;
            case 2:
                active2.setVisible(true);
                break;
            case 3:
                active3.setVisible(true);
                break;
        }
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
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(b1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh1");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void c1Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(c1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh1");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void a2Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a2.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh2");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }


    @FXML
    void b2Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(b2.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh2");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }



    @FXML
    void a3Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a3.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh3");
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
