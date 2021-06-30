package it.polimi.ingsw.view.gui;

import javafx.scene.paint.Color;


/**
 * Enumeration that associates a message type with the display color
 */
public enum MessageType {
    MESSAGE(Color.GREEN), ERROR(Color.YELLOW), FATAL(Color.RED), NEUTRAL(Color.WHITE);


    private Color color;

    private MessageType(Color color){
        this.color = color;
    }

    /**
     * Returns the color of the type
     * @return Color of the type
     */
    public Color getColor(){
        return color;
    }
}
