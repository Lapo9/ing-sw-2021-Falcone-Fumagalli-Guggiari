package it.polimi.ingsw.view.cli;

import java.util.ArrayList;

public class View {

    private ArrayList<Viewable> items;

    public String toString() {
        StringBuilder res = new StringBuilder();
        for(Viewable item : items){
            res.append(item.toString());
        }

        return res.toString();
    }


    public void addViewable(Viewable viewable){
        items.add(viewable);
    }
}
