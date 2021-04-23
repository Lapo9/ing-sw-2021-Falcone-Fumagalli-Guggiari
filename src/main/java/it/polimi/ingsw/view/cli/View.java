package it.polimi.ingsw.view.cli;

import java.util.ArrayList;

public class View {

    private ArrayList<Viewable> items = new ArrayList<>();

    public String toString() {
        StringBuilder res = new StringBuilder();
        for(Viewable item : items){
            res.append("\n" + item.toString() + "\n");
        }

        return res.toString();
    }


    public void addViewable(Viewable viewable){
        items.add(viewable);
    }
}
