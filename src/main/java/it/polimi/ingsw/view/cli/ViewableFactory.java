package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.viewables.SupplyContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewableFactory {

    private ArrayList<HashMap<ViewableId, Viewable>> items = new ArrayList<>();


    public ViewableFactory() {
        items.add(new HashMap<>()); //player 1
        items.add(new HashMap<>()); //player 2
        items.add(new HashMap<>()); //player 3
        items.add(new HashMap<>()); //player 4
        items.add(new HashMap<>()); //marketplace
        items.add(new HashMap<>()); //development grid
    }

    //TODO create method to build all of the implementations of Viewable, and when build one, add it to the items map
    public SupplyContainer buildSupplyContainer(int player, ViewableId viewableId, String name) {
        SupplyContainer res = new SupplyContainer(name);
        items.get(player-1).put(viewableId, res);
        return res;
    }

    public Viewable get(int player, ViewableId id){
        return items.get(player).get(id);
    }

}
