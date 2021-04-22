package it.polimi.ingsw.view.cli;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewableFactory {

    private ArrayList<HashMap<ViewableID, Viewable>> items = new ArrayList<>();

    //TODO create method to build all of the implementations of Viewable, and when build one, add it to the items map

    public Viewable get(int player, ViewableID id){
        return items.get(player).get(id);
    }

}
