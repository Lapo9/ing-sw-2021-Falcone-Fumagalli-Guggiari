package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

public class TestViewable implements Viewable {
    private ArrayList<Integer> state = new ArrayList<>();


    @Override
    public void update(int[] update) {
        state.clear();

        for (int i : update){
            state.add(i);
        }
    }


    @Override
    public String toString() {
        return state.toString();
    }
}
