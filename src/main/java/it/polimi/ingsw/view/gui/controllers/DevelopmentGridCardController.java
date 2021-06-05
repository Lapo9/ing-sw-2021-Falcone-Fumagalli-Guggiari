package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.development.DevelopmentCard;
import it.polimi.ingsw.view.gui.controllers.SubSceneController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DevelopmentGridCardController extends SubSceneController {

    @FXML private Group cardGroup;
    @FXML private ImageView card;
    @FXML private Group slots;
    @FXML private ImageView slot2;
    @FXML private ImageView slot1;
    @FXML private ImageView slot3;

    private Pair<Integer, Integer> positionInGrid;


    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void update(int[] completeUpdate) {
        if (completeUpdate[0] == 0){
            cardGroup.setVisible(false);
            return;
        }
        card.setImage(new Image(DevelopmentCard.getUrl(completeUpdate[0])));
    }


    public void setPositionInGrid(int row, int column){
        positionInGrid = new Pair<>(row, column);
    }


    @FXML
    void cardEntered() {
        card.setScaleX(1.5);
        card.setScaleY(1.5);
        slots.setVisible(true);
    }

    @FXML
    void cardExited() {
        slots.setVisible(false);
        card.setScaleX(1.0);
        card.setScaleY(1.0);
    }

    @FXML
    void slot1Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("buy " + positionInGrid.first + " " + positionInGrid.second + " 1");
    }

    @FXML
    void slot2Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("buy " + positionInGrid.first + " " + positionInGrid.second + " 2");
    }

    @FXML
    void slot3Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("buy " + positionInGrid.first + " " + positionInGrid.second + " 3");
    }

}
