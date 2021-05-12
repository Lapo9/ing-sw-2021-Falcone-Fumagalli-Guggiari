package it.polimi.ingsw.view.gui;

import javafx.scene.paint.Color;

public enum MessageType {
    MESSAGE(Color.GREEN), ERROR(Color.YELLOW), FATAL(Color.RED), NEUTRAL(Color.WHITE);


    private Color color;

    private MessageType(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
}
