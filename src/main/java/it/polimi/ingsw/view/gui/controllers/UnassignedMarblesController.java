package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.model.MarbleColor;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class UnassignedMarblesController extends SubSceneController {

    @FXML private ImageView marble3;
    @FXML private ImageView marble4;
    @FXML private ImageView marble1;
    @FXML private ImageView marble2;
    @FXML private Group colorPane;
    @FXML private Group unassignedMarblesGroup;

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

        unassignedMarblesGroup.setVisible(false);
        colorPane.setVisible(false);
    }

    @Override
    public void update(int[] completeUpdate) {
        ArrayList<ImageView> marbles = new ArrayList<>(Arrays.asList(marble1, marble2, marble3, marble4));
        int processedMarbles = 0;
        for(int i = 0; i < 6; ++i) {
            for (int j = 0; j < completeUpdate[i]; ++j) {
                marbles.get(processedMarbles).setImage(new Image(pictures.get(i)));
                processedMarbles++;
            }
        }

        for (int i = 0; i < 4; ++i){
            marbles.get(i).setVisible(i < processedMarbles);
        }
    }



    public void show(){
        unassignedMarblesGroup.setVisible(true);
    }

    public void hide(){
        unassignedMarblesGroup.setVisible(false);
    }

    public void reset(){
        colorPane.setVisible(false);
    }



    private MarbleColor urlToColor(String url){
        if (url.contains("/pictures/marbles/blueMarble.png")){
            return MarbleColor.BLUE;
        }
        if (url.contains("/pictures/marbles/greyMarble.png")){
            return MarbleColor.GREY;
        }
        if (url.contains("/pictures/marbles/violetMarble.png")){
            return MarbleColor.VIOLET;
        }
        if (url.contains("/pictures/marbles/yellowMarble.png")){
            return MarbleColor.YELLOW;
        }
        return null;
    }



    @FXML
    void blueClicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("colorMarble blue");
        colorPane.setVisible(false);
    }

    @FXML
    void greyClicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("colorMarble grey");
        colorPane.setVisible(false);
    }

    @FXML
    void violetClicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("colorMarble violet");
        colorPane.setVisible(false);
    }

    @FXML
    void yellowClicked() {
        controllerInterpreter.execute("reset");
        userInterpreter.execute("colorMarble yellow");
        colorPane.setVisible(false);
    }


    @FXML
    void marble1clicked() {
        controllerInterpreter.execute("reset");

        if (marble1.getImage().getUrl().contains("/pictures/marbles/whiteMarble.png")){
            colorPane.setVisible(true);
        }

        else if (marble1.getImage().getUrl().contains("/pictures/marbles/redMarble.png")){
            colorPane.setVisible(false);
        }

        else {
            colorPane.setVisible(false);
            offlineInfo.setSelectedItem("marble " + urlToColor(marble1.getImage().getUrl()).toString().toLowerCase(Locale.ROOT));
            controllerInterpreter.execute("setActive wh1 wh2 wh3");
        }
    }

    @FXML
    void marble2clicked() {
        controllerInterpreter.execute("reset");

        if (marble2.getImage().getUrl().contains("/pictures/marbles/whiteMarble.png")){
            colorPane.setVisible(true);
        }

        else if (marble2.getImage().getUrl().contains("/pictures/marbles/redMarble.png")){
            colorPane.setVisible(false);
        }

        else {
            colorPane.setVisible(false);
            offlineInfo.setSelectedItem("marble " + urlToColor(marble2.getImage().getUrl()).toString().toLowerCase(Locale.ROOT));
            controllerInterpreter.execute("setActive wh1 wh2 wh3");
        }
    }

    @FXML
    void marble3clicked() {
        controllerInterpreter.execute("reset");

        if (marble3.getImage().getUrl().contains("/pictures/marbles/whiteMarble.png")){
            colorPane.setVisible(true);
        }

        else if (marble3.getImage().getUrl().contains("/pictures/marbles/redMarble.png")){
            colorPane.setVisible(false);
        }

        else {
            colorPane.setVisible(false);
            offlineInfo.setSelectedItem("marble " + urlToColor(marble3.getImage().getUrl()).toString().toLowerCase(Locale.ROOT));
            controllerInterpreter.execute("setActive wh1 wh2 wh3");
        }
    }

    @FXML
    void marble4clicked() {
        controllerInterpreter.execute("reset");

        if (marble4.getImage().getUrl().contains("/pictures/marbles/whiteMarble.png")){
            colorPane.setVisible(true);
        }

        else if (marble4.getImage().getUrl().contains("/pictures/marbles/redMarble.png")){
            colorPane.setVisible(false);
        }

        else {
            colorPane.setVisible(false);
            offlineInfo.setSelectedItem("marble " + urlToColor(marble4.getImage().getUrl()).toString().toLowerCase(Locale.ROOT));
            controllerInterpreter.execute("setActive wh1 wh2 wh3");
        }
    }


    @FXML
    void discardClicked(){
        controllerInterpreter.execute("reset");
        userInterpreter.execute("discard");
        unassignedMarblesGroup.setVisible(false);
    }

}
