package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Random;

public class ActionTilesStack {

    private ArrayList<ActionTile> stack = new ArrayList<>();


    public ActionTilesStack(){
        reinsertAll();
    }



    public ActionTile extractTile() {
        Random r = new Random();
        int i = r.nextInt(stack.size()+1);
        ActionTile at = stack.remove(i);
        return at;
    }


    public void reinsertAll(){
        stack.clear();
        stack.add(ActionTile.YELLOW);
        stack.add(ActionTile.GREEN);
        stack.add(ActionTile.BLUE);
        stack.add(ActionTile.VIOLET);
        stack.add(ActionTile.PLUS_2);
        stack.add(ActionTile.PLUS_1_SHUFFLE);
    }


    public enum ActionTile{
        GREEN, BLUE, YELLOW, VIOLET, PLUS_2, PLUS_1_SHUFFLE
    }

}
