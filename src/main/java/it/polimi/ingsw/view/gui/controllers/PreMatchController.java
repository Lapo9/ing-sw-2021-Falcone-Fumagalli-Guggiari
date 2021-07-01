package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.view.gui.WarehouseObjectTypeController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static it.polimi.ingsw.view.gui.WarehouseObjectTypeController.*;

/**
 * Represents the pre match phase
 */
public class PreMatchController extends SceneController{

    @FXML private ImageView pick1;
    @FXML private ImageView tick1;
    @FXML private ImageView pick2;
    @FXML private ImageView tick2;
    @FXML private ImageView pick3;
    @FXML private ImageView tick3;
    @FXML private ImageView pick4;
    @FXML private ImageView tick4;
    @FXML private Group selection1;
    @FXML private ImageView supply1;
    @FXML private Group selection2;
    @FXML private ImageView supply2;
    @FXML private ImageView go;
    @FXML private ImageView resourcesBackground;
    @FXML private DevelopmentGridController developmentGridController;
    @FXML private MarketplaceController marketplaceController;

    private WarehouseObjectTypeController supply1Data = COIN;
    private WarehouseObjectTypeController supply2Data = COIN;
    private ArrayList<Pair<Integer, Boolean>> idsActive = new ArrayList<>();

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();

        tick1.setVisible(false);
        tick2.setVisible(false);
        tick3.setVisible(false);
        tick4.setVisible(false);

        idsActive.add(new Pair<>(0, false));
        idsActive.add(new Pair<>(0, false));
        idsActive.add(new Pair<>(0, false));
        idsActive.add(new Pair<>(0, false));
    }

    /**
     * Does nothing
     */
    @Override
    public void initializeSubScenes() {

    }

    /**
     * Does nothing
     * @param players nothing
     */
    @Override
    public void setPlayers(String players) {

    }

    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        Platform.runLater( ()-> {
            if (completeUpdate[0] == offlineInfo.getPlayerOrder(offlineInfo.getYourName()) - 1) {
                int yourOrderToPlay = offlineInfo.getPlayerOrder(offlineInfo.getYourName());
                selection1.setVisible(yourOrderToPlay > 1);
                selection2.setVisible(yourOrderToPlay > 3);

                for (int i = 0; i < 4; ++i) {
                    idsActive.get(i).first = completeUpdate[i * 15 + 143];
                }

                ArrayList<ImageView> picks = new ArrayList<>(Arrays.asList(pick1, pick2, pick3, pick4));
                for (int i = 0; i < picks.size(); ++i) {
                    if (idsActive.get(i).first != 0) {
                        picks.get(i).setImage(new Image(LeaderCard.getUrl(idsActive.get(i).first)));
                    }
                }
            }

            else if (completeUpdate[0] == 4) {
                marketplaceController.update(Arrays.copyOfRange(completeUpdate, 1, completeUpdate.length));
            }

            else if (completeUpdate[0] == 5) {
                developmentGridController.update(Arrays.copyOfRange(completeUpdate, 1, completeUpdate.length));
            }
        });
    }

    /**
     * Changes the first resource that player can choose
     */
    @FXML
    void arrowDown1Clicked() {
        supply1Data = prev(supply1Data);
        supply1.setImage(new Image(supply1Data.getUrl()));
    }

    /**
     * Changes the second resource that player can choose
     */
    @FXML
    void arrowDown2Clicked() {
        supply2Data = prev(supply2Data);
        supply2.setImage(new Image(supply2Data.getUrl()));
    }

    /**
     * Changes the first resource that player can choose
     */
    @FXML
    void arrowUp1Clicked() {
        supply1Data = next(supply1Data);
        supply1.setImage(new Image(supply1Data.getUrl()));
    }

    /**
     * Changes the second resource that player can choose
     */
    @FXML
    void arrowUp2Clicked() {
        supply2Data = next(supply2Data);
        supply2.setImage(new Image(supply2Data.getUrl()));
    }

    /**
     * Checks the box of the first leader to choose
     */
    @FXML
    void checkBox1Clicked() {
        tick1.setVisible(!tick1.isVisible());
        idsActive.get(0).second = tick1.isVisible();
    }

    /**
     * Checks the box of the second leader to choose
     */
    @FXML
    void checkBox2Clicked() {
        tick2.setVisible(!tick2.isVisible());
        idsActive.get(1).second = tick2.isVisible();
    }

    /**
     * Checks the box of the third leader to choose
     */
    @FXML
    void checkBox3Clicked() {
        tick3.setVisible(!tick3.isVisible());
        idsActive.get(2).second = tick3.isVisible();
    }

    /**
     * Checks the box of the fourth leader to choose
     */
    @FXML
    void checkBox4Clicked() {
        tick4.setVisible(!tick4.isVisible());
        idsActive.get(3).second = tick4.isVisible();
    }

    /**
     * The player is ready to enter in the match
     */
    @FXML
    void goClicked() {
        //extract the selected leaders and check they are exactly 2
        List<Pair<Integer, Boolean>> selectedLeaders = idsActive.stream().filter(pair -> pair.second == true).collect(Collectors.toList());
        if(selectedLeaders.size() != 2){
            controllerInterpreter.execute("error Select 2 leaders!");
            return;
        }

        //send to the server the selected starting supplies (if any)
        int yourOrderToPlay = offlineInfo.getPlayerOrder(offlineInfo.getYourName());
        if (yourOrderToPlay > 1){
            userInterpreter.execute("select " + supply1Data.toString().toLowerCase(Locale.ROOT));
        }
        if (yourOrderToPlay > 3){
            userInterpreter.execute("select " + supply2Data.toString().toLowerCase(Locale.ROOT));
        }

        //send to the server the 2 selected leaders
        userInterpreter.execute("pickLeaders " + (idsActive.indexOf(selectedLeaders.get(0))+1) + " " +(idsActive.indexOf(selectedLeaders.get(1))+1));
    }

    /**
     * The "go" button becomes highlighted
     */
    @FXML
    void goEntered() {
        if(idsActive.stream().filter(pair -> pair.second == true).collect(Collectors.toList()).size() != 2){
            //go.setDisable(true);
            ColorAdjust tmp = new ColorAdjust();
            tmp.setSaturation(-1.0);
            go.setEffect(tmp);
        }
    }

    /**
     * The "go" button returns normal
     */
    @FXML
    void goExited() {
        //go.setDisable(false);
        go.setEffect(null);
    }

    /**
     * Shows the marketplace and development grid if the pointer of the mouse is over the button
     */
    @FXML
    void showMarketplaceEntered() {
        resourcesBackground.setVisible(true);
        marketplaceController.show();
        developmentGridController.show();
    }

    /**
     * Hides the marketplace and development grid if the pointer of the mouse goes away
     */
    @FXML
    void showMarketplaceExited() {
        resourcesBackground.setVisible(false);
        developmentGridController.hide();
        marketplaceController.hide();
    }

}
