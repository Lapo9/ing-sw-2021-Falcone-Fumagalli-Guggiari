package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;

/**
 * The MarbleContainer class is meant to be a temporary container for marbles
 */
public class MarbleContainer implements HasStatus {
    private int yellow;
    private int blue;
    private int grey;
    private int white;
    private int violet;
    private int red;

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
     * Returns the quantity of the marbles color passed as input that currently are in the MarbleContainer
     * @param mcs colors to return the sum of the quantity
     * @return the marble quantity of the given type present in the MarbleContainer
     */
    public int getQuantity(MarbleColor... mcs){

        int temp = 0;
        for(MarbleColor mc : mcs){
            if(mc == MarbleColor.YELLOW)
                temp += yellow;
            else if(mc == MarbleColor.BLUE)
                temp += blue;
            else if(mc == MarbleColor.GREY)
                temp += grey;
            else if(mc == MarbleColor.WHITE)
                temp += white;
            else if(mc == MarbleColor.VIOLET)
                temp += violet;
            else if(mc == MarbleColor.RED)
                temp += red;
        }
        return temp;
    }


    /**
     * Removes a marble of the specified color
     * @param mc color to remove
     * @throws MarbleException There isn't a marble of the specified color
     */
    public void removeMarble(MarbleColor mc) throws MarbleException{
        if(mc == MarbleColor.YELLOW)
        {
            if(yellow <= 0)
                throw new MarbleException();
            else
                yellow--;
        }
        else if(mc == MarbleColor.BLUE)
        {
            if(blue <= 0)
                throw new MarbleException();
            else
                blue--;
        }
        else if(mc == MarbleColor.GREY)
        {
            if(grey <= 0)
                throw new MarbleException();
            else
                grey--;
        }
        else if(mc == MarbleColor.WHITE)
        {
            if(white <= 0)
                throw new MarbleException();
            else
                white--;
        }
        else if(mc == MarbleColor.VIOLET)
        {
            if(violet <= 0)
                throw new MarbleException();
            else
                violet--;
        }
        else if(mc == MarbleColor.RED)
        {
            if(red <= 0)
                throw new MarbleException();
            else
                red--;
        }
    }

    /**
     * Empties the container
     */
    public void clear(){
        yellow = 0;
        blue = 0;
        grey = 0;
        white = 0;
        violet = 0;
        red = 0;
    }

    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        //The getStatus array contains the number of the blue, gray, red, violet, white and yellow marbles contained in the MarbleContainer
        status.add(blue);
        status.add(grey);
        status.add(red);
        status.add(violet);
        status.add(white);
        status.add(yellow);

        return status;
    }

    /**
     * Turns the MarbleColor in input in the right WarehouseObjectType
     * @param mc is a MarbleColor
     * @return the WarehouseObjectType of the MarbleColor in input
     */
    public static WarehouseObjectType colorToSupply(MarbleColor mc){
        WarehouseObjectType wot;

        if (mc == MarbleColor.YELLOW){
            wot = WarehouseObjectType.COIN;
        }
        else if (mc == MarbleColor.BLUE){
            wot = WarehouseObjectType.SHIELD;
        }
        else if (mc == MarbleColor.VIOLET){
            wot = WarehouseObjectType.SERVANT;
        }
        else if (mc == MarbleColor.GREY){
            wot = WarehouseObjectType.STONE;
        }
        else if (mc == MarbleColor.RED){
            wot = WarehouseObjectType.FAITH_MARKER;
        }
        else {wot = null;}

        return wot;
    }
}
