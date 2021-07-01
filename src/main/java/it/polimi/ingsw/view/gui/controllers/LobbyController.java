package it.polimi.ingsw.view.gui.controllers;

import it.polimi.ingsw.Pair;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the lobby of the game
 */
public class LobbyController extends SceneController {

    @FXML private Button start;
    @FXML private Button exit;
    @FXML private Label player1;
    @FXML private Label player2;
    @FXML private Label player3;
    @FXML private Label player4;
    @FXML private ImageView status1;
    @FXML private ImageView status2;
    @FXML private ImageView status3;
    @FXML private ImageView status4;
    @FXML private Label text;

    ArrayList<Pair<Label, ImageView>> players = new ArrayList<>();

    /**
     * Initializes the class
     */
    @Override
    public void initialize() {
        super.initialize();

        players.add(new Pair<>(player1, status1));
        players.add(new Pair<>(player2, status2));
        players.add(new Pair<>(player3, status3));
        players.add(new Pair<>(player4, status4));

        InputStream rules = getClass().getResourceAsStream("/files/LobbyText.txt");
        Scanner s = new Scanner(rules).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        text.setText(result);
    }

    /**
     * Does nothing
     */
    @Override
    public void initializeSubScenes() {}

    /**
     * Does nothing
     */
    @Override
    public void update(int[] completeUpdate) {

    }

    /**
     * Sets the players who join the match on the screen
     * @param players what players are present and their status, in a specific format
     */
    @Override
    public void setPlayers(String players) {
        //clear the table
        for (int i = 0; i < 4; ++i){
            this.players.get(i).first.setText("");
            this.players.get(i).second.setVisible(false);
        }

        String[] playersAndStatus = players.split(" ");

        for (int i = 0; i < playersAndStatus.length-1; i+=2){
            this.players.get(i/2).second.setVisible(true);
            this.players.get(i/2).first.setText(playersAndStatus[i+1]);
            this.players.get(i/2).second.setImage(new Image(playersAndStatus[i].equals("on") ? "/pictures/miscellaneous/greenDot.png" :
                                                                                            (playersAndStatus[i].equals("curr") ? "/pictures/miscellaneous/blueDot.png" : "/pictures/miscellaneous/redDot.png")));
        }
    }


    /**
     * Starts the match with the button
     */
    @FXML
    private void startClicked(){
        userInterpreter.execute("start");
    }


    /**
     * Exit from the lobby
     */
    @FXML
    private void exitClicked(){
        userInterpreter.execute("exit");
    }


}
