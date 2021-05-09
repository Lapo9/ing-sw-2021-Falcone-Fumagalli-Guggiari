package it.polimi.ingsw.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeScreenController {

    @FXML
    private Button start;




    public void initialize(){

    }




    @FXML
    private void startClicked() {
        start.setStyle("-fx-background-color: #ff0000;"); //TODO test
    }


}
