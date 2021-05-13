package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

public class DevelopmentGrid implements Viewable {

    private HashMap<CardCategory, HashMap<Integer, DevelopmentGridCard>> devGrid = new HashMap<>();
    private HashMap<Integer, DevelopmentGridCard> column = new HashMap<>();

    DevelopmentGrid() {


    }

    @Override
    public void update(int[] update) {

    }

    @Override
    public String toString() {
        return null;
    }

}
