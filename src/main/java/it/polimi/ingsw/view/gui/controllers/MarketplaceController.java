package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the marketplace
 */
public class MarketplaceController extends SubSceneController {

    @FXML private Rectangle r7;
    @FXML private Rectangle r6;
    @FXML private Rectangle r5;
    @FXML private Rectangle r4;
    @FXML private Rectangle r11;
    @FXML private Rectangle r10;
    @FXML private Rectangle r9;
    @FXML private Rectangle r8;
    @FXML private Rectangle r3;
    @FXML private Rectangle r2;
    @FXML private Rectangle r1;
    @FXML private Rectangle r0;
    @FXML private ImageView m11;
    @FXML private ImageView m10;
    @FXML private ImageView m9;
    @FXML private ImageView m8;
    @FXML private ImageView m7;
    @FXML private ImageView m6;
    @FXML private ImageView m5;
    @FXML private ImageView m4;
    @FXML private ImageView m3;
    @FXML private ImageView m2;
    @FXML private ImageView m1;
    @FXML private ImageView m0;
    @FXML private ImageView slide;
    @FXML private Group marketplaceGroup;


    private ArrayList<ArrayList<ImageView>> rowsColsMarbles;

    private ArrayList<ArrayList<Rectangle>> rowsColsSquares;

    private ArrayList<String> pictures = new ArrayList<>(Arrays.asList(
            "/pictures/marbles/blueMarble.png",
            "/pictures/marbles/greyMarble.png",
            "/pictures/marbles/redMarble.png",
            "/pictures/marbles/violetMarble.png",
            "/pictures/marbles/whiteMarble.png",
            "/pictures/marbles/yellowMarble.png"
    ));

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();

        marketplaceGroup.setVisible(false);

        rowsColsMarbles =  new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(m0, m1, m2, m3)),
                new ArrayList<>(Arrays.asList(m4, m5, m6, m7)),
                new ArrayList<>(Arrays.asList(m8, m9, m10, m11)),

                new ArrayList<>(Arrays.asList(m0, m4, m8)),
                new ArrayList<>(Arrays.asList(m1, m5, m9)),
                new ArrayList<>(Arrays.asList(m2, m6, m10)),
                new ArrayList<>(Arrays.asList(m3, m7, m11))));

        rowsColsSquares= new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(r0, r1, r2, r3)),
                new ArrayList<>(Arrays.asList(r4, r5, r6, r7)),
                new ArrayList<>(Arrays.asList(r8, r9, r10, r11)),

                new ArrayList<>(Arrays.asList(r0, r4, r8)),
                new ArrayList<>(Arrays.asList(r1, r5, r9)),
                new ArrayList<>(Arrays.asList(r2, r6, r10)),
                new ArrayList<>(Arrays.asList(r3, r7, r11))));

    }

    /**
     * Updates the class status using values from the model
     * @param completeUpdate array made of integer
     */
    @Override
    public void update(int[] completeUpdate) {
        ArrayList<ImageView> marbles = new ArrayList<>(Arrays.asList(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, slide));
        for (int i = 0; i < 13; ++i) {
            marbles.get(i).setImage(new Image(pictures.get(completeUpdate[i])));
        }
    }


    /**
     * Shows the marketplace
     */
    public void show(){
        marketplaceGroup.setVisible(true);
    }

    /**
     * Hides the marketplace
     */
    public void hide(){
        marketplaceGroup.setVisible(false);
    }


    /**
     * Picks the marbles selected in the row 1
     */
    @FXML
    void h1Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace h 1");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    /**
     * Surround the row to show the marbles that the player will get, if he clicks that arrow
     */
    @FXML
    void h1Entered() {
        rowsColsSquares.get(0).forEach(r -> r.setVisible(true));
    }

    /**
     * Remove the surround effect
     */
    @FXML
    void h1Exited() {
        rowsColsSquares.get(0).forEach(r -> r.setVisible(false));
    }

    /**
     * Picks the marbles selected in the row 2
     */
    @FXML
    void h2Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace h 2");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    /**
     * Surround the row to show the marbles that the player will get, if he clicks that arrow
     */
    @FXML
    void h2Entered() {
        rowsColsSquares.get(1).forEach(r -> r.setVisible(true));
    }

    /**
     * Remove the surround effect
     */
    @FXML
    void h2Exited() {
        rowsColsSquares.get(1).forEach(r -> r.setVisible(false));
    }

    /**
     * Picks the marbles selected in the row 3
     */
    @FXML
    void h3Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace h 3");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    /**
     * Surround the row to show the marbles that the player will get, if he clicks that arrow
     */
    @FXML
    void h3Entered() {
        rowsColsSquares.get(2).forEach(r -> r.setVisible(true));
    }

    /**
     * Remove the surround effect
     */
    @FXML
    void h3Exited() {
        rowsColsSquares.get(2).forEach(r -> r.setVisible(false));
    }

    /**
     * Picks the marbles selected in the column 1
     */
    @FXML
    void v1Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 1");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    /**
     * Surround the column to show the marbles that the player will get, if he clicks that arrow
     */
    @FXML
    void v1Entered() {
        rowsColsSquares.get(3).forEach(r -> r.setVisible(true));
    }

    /**
     * Remove the surround effect
     */
    @FXML
    void v1Exited() {
        rowsColsSquares.get(3).forEach(r -> r.setVisible(false));
    }

    /**
     * Picks the marbles selected in the column 2
     */
    @FXML
    void v2Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 2");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    /**
     * Surround the column to show the marbles that the player will get, if he clicks that arrow
     */
    @FXML
    void v2Entered() {
        rowsColsSquares.get(4).forEach(r -> r.setVisible(true));
    }

    /**
     * Remove the surround effect
     */
    @FXML
    void v2Exited() {
        rowsColsSquares.get(4).forEach(r -> r.setVisible(false));
    }

    /**
     * Picks the marbles selected in the column 3
     */
    @FXML
    void v3Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 3");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    /**
     * Surround the column to show the marbles that the player will get, if he clicks that arrow
     */
    @FXML
    void v3Entered() {
        rowsColsSquares.get(5).forEach(r -> r.setVisible(true));
    }

    /**
     * Remove the surround effect
     */
    @FXML
    void v3Exited() {
        rowsColsSquares.get(5).forEach(r -> r.setVisible(false));
    }

    /**
     * Picks the marbles selected in the column 4
     */
    @FXML
    void v4Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 4");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    /**
     * Surround the column to show the marbles that the player will get, if he clicks that arrow
     */
    @FXML
    void v4Entered() {
        rowsColsSquares.get(6).forEach(r -> r.setVisible(true));
    }

    /**
     * Remove the surround effect
     */
    @FXML
    void v4Exited() {
        rowsColsSquares.get(6).forEach(r -> r.setVisible(false));
    }

}
