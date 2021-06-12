package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.model.CardCategory;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import static it.polimi.ingsw.model.development.DevelopmentCard.*;

public class DevelopmentSpaceController extends SubSceneController {

    @FXML private ImageView card;
    @FXML private ImageView oldCard1;
    @FXML private Text winPointsOldCard1;
    @FXML private ImageView oldCard2;
    @FXML private Text winPointsOldCard2;
    @FXML private ImageView checkBox;
    @FXML private ImageView produce;
    @FXML private Text coin;
    @FXML private Text shield;
    @FXML private Text servant;
    @FXML private Text stone;
    @FXML private ImageView activeSquare;
    @FXML private Group developmentGroup;


    @Override
    public void initialize() {
        super.initialize();

        coin.setText("0");
        servant.setText("0");
        shield.setText("0");
        stone.setText("0");
        oldCard1.setVisible(false);
        winPointsOldCard1.setText("");
        oldCard2.setVisible(false);
        winPointsOldCard2.setText("");

        developmentGroup.setVisible(false);
    }


    @Override
    public void update(int[] completeUpdate) {

        if(completeUpdate[2] != 0) {
            card.setImage(new Image(getUrl(completeUpdate[2])));
            oldCard1.setVisible(true);
            oldCard1.setImage(new Image(getDot(getCategory(completeUpdate[0]))));
            winPointsOldCard1.setText(String.valueOf(getWinPoints(completeUpdate[0])));
            oldCard2.setVisible(true);
            oldCard2.setImage(new Image(getDot(getCategory(completeUpdate[1]))));
            winPointsOldCard2.setText(String.valueOf(getWinPoints(completeUpdate[1])));
            developmentGroup.setVisible(true);
        }
        else if(completeUpdate[1] != 0) {
            card.setImage(new Image(getUrl(completeUpdate[1])));
            oldCard1.setVisible(true);
            oldCard1.setImage(new Image(getDot(getCategory(completeUpdate[0]))));
            winPointsOldCard1.setText(String.valueOf(getWinPoints(completeUpdate[0])));
            developmentGroup.setVisible(true);
            oldCard2.setVisible(false);
        }
        else if(completeUpdate[0] != 0){
            card.setImage(new Image(getUrl(completeUpdate[0])));
            developmentGroup.setVisible(true);
            oldCard1.setVisible(false);
            oldCard2.setVisible(false);
        }
        else {
            developmentGroup.setVisible(false);
        }

        coin.setText(String.valueOf(completeUpdate[13]));
        servant.setText(String.valueOf(completeUpdate[14]));
        shield.setText(String.valueOf(completeUpdate[15]));
        stone.setText(String.valueOf(completeUpdate[16]));
    }
    
    private String getDot(CardCategory cat) {
        if(cat == CardCategory.GREEN)
            return "pictures/miscellaneous/greenDot.png";
        else if(cat == CardCategory.VIOLET)
            return "pictures/miscellaneous/violetDot.png";
        else if(cat == CardCategory.BLUE)
            return "pictures/miscellaneous/cyanDot.png";
        else if(cat == CardCategory.YELLOW)
            return "pictures/miscellaneous/yellowDot.png";
        else
            return null;
    }


    public void reset(){
        activeSquare.setVisible(false);
    }


    public void setActive(){
        activeSquare.setVisible(true);
    }


    @FXML
    void activeSquareClicked() {
        //this should never happen
        if (offlineInfo.getSelectedItem().isBlank()){
            controllerInterpreter.execute("reset");
            return;
        }

        userInterpreter.execute("move " + offlineInfo.getSelectedItem() + " " + id);
        controllerInterpreter.execute("reset");
    }

    @FXML
    void checkboxClicked() {
        controllerInterpreter.execute("reset");
        produce.setVisible(!produce.isVisible());
        offlineInfo.setProduction(id, produce.isVisible());
    }

    @FXML
    void coinClicked() {
        //check if there is a supply to pick
        if(coin.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("coin " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void servantClicked() {
        //check if there is a supply to pick
        if(servant.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("servant " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void shieldClicked() {
        //check if there is a supply to pick
        if(shield.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("shield " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

    @FXML
    void stoneClicked() {
        //check if there is a supply to pick
        if(stone.getText().equals("0")){
            return;
        }
        controllerInterpreter.execute("reset");
        offlineInfo.setSelectedItem("stone " + id);
        userInterpreter.execute("selected " + offlineInfo.getSelectedItem());
    }

}
