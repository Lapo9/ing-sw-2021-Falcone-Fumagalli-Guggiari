package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.development.DevelopmentCard;
import it.polimi.ingsw.view.gui.controllers.SubSceneController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a stack of cards in the development card grid.
 */
public class DevelopmentGridCardController extends SubSceneController {

    @FXML private Group cardGroup;
    @FXML private ImageView card;
    @FXML private Group slots;
    @FXML private ImageView slot2;
    @FXML private ImageView slot1;
    @FXML private ImageView slot3;

    private Pair<Integer, Integer> positionInGrid;


    /**
     * Initialize the class
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
        if (completeUpdate[0] == 0){
            cardGroup.setVisible(false);
            return;
        }
        card.setImage(new Image(DevelopmentCard.getUrl(completeUpdate[0])));
    }


    /**
     * Sets the card stack position in the grid of 3 x 4 card stacks
     * @param row integer which represents the row number (between 1 and 3)
     * @param column integer which represents the column number (between 1 and 4)
     */
    public void setPositionInGrid(int row, int column){
        positionInGrid = new Pair<>(row, column);
    }


    /**
     * Expands the last card image when the pointers goes above it
     */
    @FXML
    void cardEntered() {
        card.setScaleX(1.5);
        card.setScaleY(1.5);
        slots.setVisible(true);
    }

    /**
     * Reduce the last card image when the pointers goes away from it
     */
    @FXML
    void cardExited() {
        slots.setVisible(false);
        card.setScaleX(1.0);
        card.setScaleY(1.0);
    }

    /**
     * Moves the selected card to the slot 1 in the development space
     */
    @FXML
    void slot1Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("buy " + positionInGrid.first + " " + positionInGrid.second + " 1");
    }

    /**
     * Moves the selected card to the slot 2 in the development space
     */
    @FXML
    void slot2Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("buy " + positionInGrid.first + " " + positionInGrid.second + " 2");
    }

    /**
     * Moves the selected card to the slot 3 in the development space
     */
    @FXML
    void slot3Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("buy " + positionInGrid.first + " " + positionInGrid.second + " 3");
    }

}
