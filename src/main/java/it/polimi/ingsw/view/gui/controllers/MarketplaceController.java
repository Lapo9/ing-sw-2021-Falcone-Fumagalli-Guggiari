package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    public void update(int[] completeUpdate) {
        ArrayList<ImageView> marbles = new ArrayList<>(Arrays.asList(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, slide));
        for (int i = 0; i < 13; ++i) {
            marbles.get(i).setImage(new Image(pictures.get(completeUpdate[i])));
        }
    }



    public void show(){
        marketplaceGroup.setVisible(true);
    }


    public void hide(){
        marketplaceGroup.setVisible(false);
    }



    @FXML
    void h1Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace h 1");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    @FXML
    void h1Entered() {
        rowsColsSquares.get(0).forEach(r -> r.setVisible(true));
    }

    @FXML
    void h1Exited() {
        rowsColsSquares.get(0).forEach(r -> r.setVisible(false));
    }

    @FXML
    void h2Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace h 2");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    @FXML
    void h2Entered() {
        rowsColsSquares.get(1).forEach(r -> r.setVisible(true));
    }

    @FXML
    void h2Exited() {
        rowsColsSquares.get(1).forEach(r -> r.setVisible(false));
    }

    @FXML
    void h3Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace h 3");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    @FXML
    void h3Entered() {
        rowsColsSquares.get(2).forEach(r -> r.setVisible(true));
    }

    @FXML
    void h3Exited() {
        rowsColsSquares.get(2).forEach(r -> r.setVisible(false));
    }

    @FXML
    void v1Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 1");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    @FXML
    void v1Entered() {
        rowsColsSquares.get(3).forEach(r -> r.setVisible(true));
    }

    @FXML
    void v1Exited() {
        rowsColsSquares.get(3).forEach(r -> r.setVisible(false));
    }

    @FXML
    void v2Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 2");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    @FXML
    void v2Entered() {
        rowsColsSquares.get(4).forEach(r -> r.setVisible(true));
    }

    @FXML
    void v2Exited() {
        rowsColsSquares.get(4).forEach(r -> r.setVisible(false));
    }

    @FXML
    void v3Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 3");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    @FXML
    void v3Entered() {
        rowsColsSquares.get(5).forEach(r -> r.setVisible(true));
    }

    @FXML
    void v3Exited() {
        rowsColsSquares.get(5).forEach(r -> r.setVisible(false));
    }

    @FXML
    void v4Clicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("marketplace v 4");
        marketplaceGroup.setVisible(false);
        controllerInterpreter.execute("show unassignedMarbles");
    }

    @FXML
    void v4Entered() {
        rowsColsSquares.get(6).forEach(r -> r.setVisible(true));
    }

    @FXML
    void v4Exited() {
        rowsColsSquares.get(6).forEach(r -> r.setVisible(false));
    }

}
