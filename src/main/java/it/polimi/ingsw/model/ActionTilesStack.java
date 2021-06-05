package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.ModelObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The ActionTilesStack contains the solo action tokens, used to play the game in the solo-mode.
 */
public class ActionTilesStack{

    private ArrayList<ActionTile> stack = new ArrayList<>();

    ArrayList<ModelObserver> observers = new ArrayList<>();


    /**
     * Creates a stack of tokens.
     */
    public ActionTilesStack(){
        reinsertAll();
    }


    /**
     * Extracts a random token from the stack.
     * @return one token
     */
    public ActionTile extractTile() {
        Random r = new Random();
        int i = r.nextInt(stack.size());
        ActionTile at = stack.remove(i);
        notifyViews(at);
        return at;
    }


    /**
     * Extracts a token from the stack given an index.
     * @param index index of the token to extract
     * @return one token
     */
    public ActionTile extractTile(int index) {
        return stack.remove(index);
    }


    /**
     * Fill the stack with all the tokens.
     */
    public void reinsertAll(){
        stack.clear();
        stack.add(ActionTile.YELLOW);
        stack.add(ActionTile.GREEN);
        stack.add(ActionTile.BLUE);
        stack.add(ActionTile.VIOLET);
        stack.add(ActionTile.PLUS_2);
        stack.add(ActionTile.PLUS_1_SHUFFLE);
    }


    /**
     * Notify to the ModelObserver the last extracted tile
     */
    public void notifyViews(ActionTile tile){
        ArrayList<Integer> status = new ArrayList<>(Arrays.asList(tile.ordinal()));

        for (ModelObserver mo : observers){
            mo.update("actionTile", status);
        }
    }


    /**
     * Attached a new observer
     * @param modelObserver new observer
     */
    public void attach(ModelObserver modelObserver){
        observers.add(modelObserver);
    }


    /**
     * Contains the types of solo action token.
     */
    public enum ActionTile{
        GREEN, BLUE, YELLOW, VIOLET, PLUS_2, PLUS_1_SHUFFLE
    }
}
