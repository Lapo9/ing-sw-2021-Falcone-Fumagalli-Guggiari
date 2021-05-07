package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.MarbleColor;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

import static it.polimi.ingsw.model.MarbleColor.*;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class MarbleContainer implements Viewable {

    private HashMap<MarbleColor, Integer> marbles = new HashMap<>();


    MarbleContainer () {
        marbles.put(MarbleColor.YELLOW, 0);
        marbles.put(MarbleColor.BLUE, 0);
        marbles.put(MarbleColor.GREY, 0);
        marbles.put(MarbleColor.WHITE, 0);
        marbles.put(MarbleColor.VIOLET, 0);
        marbles.put(MarbleColor.RED, 0);
    }

    @Override
    public void update(int[] update) {
        marbles.put(MarbleColor.YELLOW, update[0]);
        marbles.put(MarbleColor.BLUE, update[1]);
        marbles.put(MarbleColor.GREY, update[2]);
        marbles.put(MarbleColor.WHITE, update[3]);
        marbles.put(MarbleColor.VIOLET, update[4]);
        marbles.put(MarbleColor.RED, update[5]);
    }

    @Override
    public String toString() {
        return FRAMED(" MarbleContainer ") + " " + BLACK(BACK_YELLOW(" " + marbles.get(YELLOW).toString() + " ")) +
                                                        BACK_BLUE(" " + marbles.get(BLUE).toString() + " ") +
                                                        WHITE(" " + marbles.get(GREY).toString() + " ") +
                                                        BACK_WHITE( " " + marbles.get(WHITE).toString() + " ") +
                                                        BACK_MAGENTA( " " + marbles.get(VIOLET).toString() + " ") +
                                                        BACK_RED(" " + marbles.get(RED).toString() + " ");
    }
}
