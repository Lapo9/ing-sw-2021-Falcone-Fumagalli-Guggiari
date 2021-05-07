package it.polimi.ingsw.view.cli;

import java.util.ArrayList;


/**
 * Collection of Viewable(s) to be shown on screen at the same time.
 */
public class View {

    private ArrayList<Viewable> items = new ArrayList<>();



    /**
     * Transforms the view into a string that can be displayed.
     * @return A tring representing the view, based on the toString methods of the contained viewables.
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(Viewable item : items){
            res.append("\n" + item.toString() + "\n");
        }

        return res.toString();
    }


    /**
     * Adds a viewvable to this view.
     * @param viewable Viewable to add
     */
    public void addViewable(Viewable viewable){
        items.add(viewable);
    }
}
