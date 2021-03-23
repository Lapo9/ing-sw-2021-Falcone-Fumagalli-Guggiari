package it.polimi.ingsw.model;

import it.polimi.ingsw.model.MarbleColor;
import java.util.ArrayList;

public class MarbleContainer {
    private int yellow = 0;
    private int blue = 0;
    private int grey = 0;
    private int white = 0;
    private int violet = 0;
    private int red = 0;

    /**
     * Class constructor
     * @param y number of yellow marbles
     * @param b number of blue marbles
     * @param g number of grey marbles
     * @param w number of white marbles
     * @param v number of violet marbles
     * @param r number of red marbles
     */
    public MarbleContainer(int y, int b, int g, int w, int v, int r){
        yellow = y;
        blue = b;
        grey = g;
        white = w;
        violet = v;
        red = r;
    }

    /**
     * The getQuantity method returns the quantity of the marble type passed as input that currently is in the MarbleContainer
     * @param mc is one of the six types of marble in the game
     * @return is the marble quantity of the given type present in the MarbleContainer
     */
    public int getQuantity(MarbleColor mc){
        int temp = 0;
        if(mc == MarbleColor.YELLOW)
            temp = yellow;
        else if(mc == MarbleColor.BLUE)
            temp = blue;
        else if(mc == MarbleColor.GREY)
            temp = grey;
        else if(mc == MarbleColor.WHITE)
            temp = white;
        else if(mc == MarbleColor.VIOLET)
            temp = violet;
        else if(mc == MarbleColor.RED)
            temp = red;
        return temp;
    }

    //TODO
    public ArrayList<Integer> getStatus(){

    }
}
