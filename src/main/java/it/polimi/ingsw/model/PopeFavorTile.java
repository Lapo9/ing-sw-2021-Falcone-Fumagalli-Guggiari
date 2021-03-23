package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * A PopeFavorTile represent one Pope's Favor tile of the player's personal board
 */
public class PopeFavorTile implements HasStatus, WinPointsCountable{
    private int points = 0;
    private int pointsToBe;

    /**
     * Class constructor
     * @param p are the points of the PopeFavorTile
     */
    public PopeFavorTile(int p){
        pointsToBe = p;
    }

    /**
     * The isActive method verifies if the activate method has already been called
     * @return true if the activate method has been called
     */
    public boolean isActive(){
        return points != 0;
    }

    /**
     * The activate method activates the PopeFavorTile
     */
    public void activate(){
        points = pointsToBe;
    }

    //TODO
    @Override
    public int getWinPoints(){

    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus(){

    }
}
