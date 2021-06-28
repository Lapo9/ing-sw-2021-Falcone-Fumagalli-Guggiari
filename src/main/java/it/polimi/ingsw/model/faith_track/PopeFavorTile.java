package it.polimi.ingsw.model.faith_track;

import it.polimi.ingsw.model.HasStatus;
import it.polimi.ingsw.model.WinPointsCountable;

import java.util.ArrayList;

/**
 * A PopeFavorTile represent one Pope's Favor tile of the player's personal board
 */
public class PopeFavorTile implements HasStatus, WinPointsCountable {
    private int points;
    private boolean active = false;
    private boolean discarded = false;

    /**
     * Class constructor
     * @param p the points of the PopeFavorTile
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
     * Gets the win points of the object
     * @return the win points of the object
     */
    @Override
    public int getWinPoints(){
        return active ? points : 0;
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of 1 Integer
     */
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        //0 = inactive, 1 = active, 2 = discarded
        int tileState = (active ? 1 : (discarded ? 2 : 0));
        status.add(tileState);

        return status;
    }
}
