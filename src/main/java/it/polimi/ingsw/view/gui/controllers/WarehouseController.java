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
/**
 * Represents the warehouse
 *
 *             a3
 *         a2      b2
 *     a1      b1      c1
 *
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

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();
    }

    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
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



    /**
     * Makes all of the objects invisible
     */
    private void clearWarehouse() {
        a1.setVisible(false);
        a2.setVisible(false);
        a3.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        c1.setVisible(false);
    }


    /**
     * Resets the green highlighting
     */
    public void reset(){
        active1.setVisible(false);
        active2.setVisible(false);
        active3.setVisible(false);
    }

    /**
     * Sets active the highlighting of the row
     * @param i row chosen
     */
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


    /**
     * Sets to 'a1'(coin, servant, ...) the selected item in the OfflineInfo class and highlights the third row of the warehouse
     */
    @FXML
    void a1Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh3");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    /**
     * Sets to 'b1'(coin, servant, ...) the selected item in the OfflineInfo class and highlights the third row of the warehouse
     */
    @FXML
    void b1Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(b1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh3");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    /**
     * Sets to 'c1'(coin, servant, ...) the selected item in the OfflineInfo class and highlights the third row of the warehouse
     */
    @FXML
    void c1Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(c1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh3");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    /**
     * Sets to 'a2'(coin, servant, ...) the selected item in the OfflineInfo class and highlights the second row of the warehouse
     */
    @FXML
    void a2Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a2.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh2");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    /**
     * Sets to 'b2'(coin, servant, ...) the selected item in the OfflineInfo class and highlights the second row of the warehouse
     */
    @FXML
    void b2Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(b2.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh2");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    /**
     * Sets to 'a3'(coin, servant, ...) the selected item in the OfflineInfo class and highlights the first row of the warehouse
     */
    @FXML
    void a3Clicked() {
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem(WarehouseObjectTypeController.getTypeByUrl(a3.getImage().getUrl()).toString().toLowerCase(Locale.ROOT) + " wh1");
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }


    /**
     * When clicking the green row, it puts the item (marble converted or supply) contained in the offlineInfo in the first row of the warehouse
     */
    @FXML
    void active1Clicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        if (offlineInfo.getSelectedItem().contains("marble")){
            userInterpreter.execute("moveMarble " + offlineInfo.getSelectedItem().split(" ")[1] + " wh1");
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " wh1");
        controllerInterpreter.execute("reset");
    }

    /**
     * When clicking the green row, it puts the item (marble converted or supply) contained in the offlineInfo in the second row of the warehouse
     */
    @FXML
    void active2Clicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        if (offlineInfo.getSelectedItem().contains("marble")){
            userInterpreter.execute("moveMarble " + offlineInfo.getSelectedItem().split(" ")[1] + " wh2");
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " wh2");
        controllerInterpreter.execute("reset");
    }

    /**
     * When clicking the green row, it puts the item (marble converted or supply) contained in the offlineInfo in the third row of the warehouse
     */
    @FXML
    void active3Clicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        if (offlineInfo.getSelectedItem().contains("marble")){
            userInterpreter.execute("moveMarble " + offlineInfo.getSelectedItem().split(" ")[1] + " wh3");
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " wh3");
        controllerInterpreter.execute("reset");
    }

    /**
     * Selects the first row or make the swap with the row already selected
     */
    @FXML
    void swap1Clicked() {
        //if no rows were selected, select this row
        if (offlineInfo.getSelectedWarehouseRow().equals("")){
            controllerInterpreter.execute("reset");
            offlineInfo.setSelectedWarehouseRow("1");
        }
        else {
            userInterpreter.execute("swapRows 1 " + offlineInfo.getSelectedWarehouseRow());
            controllerInterpreter.execute("reset");
        }
    }

    /**
     * Selects the second row or make the swap with the row already selected
     */
    @FXML
    void swap2Clicked() {
        //if no rows were selected, select this row
        if (offlineInfo.getSelectedWarehouseRow().equals("")){
            controllerInterpreter.execute("reset");
            offlineInfo.setSelectedWarehouseRow("2");
        }
        else {
            userInterpreter.execute("swapRows 2 " + offlineInfo.getSelectedWarehouseRow());
            controllerInterpreter.execute("reset");
        }
    }

    /**
     * Selects the third row or make the swap with the row already selected
     */
    @FXML
    void swap3Clicked() {
        //if no rows were selected, select this row
        if (offlineInfo.getSelectedWarehouseRow().equals("")){
            controllerInterpreter.execute("reset");
            offlineInfo.setSelectedWarehouseRow("3");
        }
        else {
            userInterpreter.execute("swapRows 3 " + offlineInfo.getSelectedWarehouseRow());
            controllerInterpreter.execute("reset");
        }
    }

}
