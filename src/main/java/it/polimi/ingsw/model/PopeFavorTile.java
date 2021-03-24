package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * A PopeFavorTile represent one Pope's Favor tile of the player's personal board
 */
public class PopeFavorTile implements HasStatus, WinPointsCountable{
    private int points;
    private boolean active = false;
    private boolean discarded = false;

    /**
     * Class constructor
     * @param p are the points of the PopeFavorTile
     */
    public PopeFavorTile(int p){
        points = p;
    }

    /**
     * Verifies if the tile is active.
     * @return true if the activate method has been called
     */
    public boolean isActive(){
        return active;
    }

    /**
     * Activates the PopeFavorTile, if it hasn't already been discarded.
     */
    public void activate(){
        if(!discarded) {
            active = true;
        }
    }


    /**
     * Discards the tile. If a tile is active cannot be discarded. If a tile is discarded cannot be activated.
     */
    public void discard() {
        if(!active) {
            discarded = true;
        }
    }

    /**
     * Returns the points this object gives you.
     * @return 0 if inactive, 1 to 3 points if active
     */
    @Override
    public int getWinPoints(){
        return active ? points : 0;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus(){

    }
}
