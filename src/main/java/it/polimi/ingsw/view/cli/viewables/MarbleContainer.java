package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.MarbleColor;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.model.MarbleColor.*;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class MarbleContainer implements Viewable {

    private ArrayList<Integer> marbles = new ArrayList<>();

    MarbleContainer () {
        marbles.add(0, 0); //blue
        marbles.add(1, 0); //grey
        marbles.add(2, 0); //red
        marbles.add(3, 0); //violet
        marbles.add(4, 0); //white
        marbles.add(5, 0); //yellow
    }

    @Override
    public void update(int[] update) {
        marbles.add(0, update[0]); //blue
        marbles.add(1, update[1]); //grey
        marbles.add(2, update[2]); //red
        marbles.add(3, update[3]); //violet
        marbles.add(4, update[4]); //white
        marbles.add(5, update[5]); //yellow
    }

    @Override
    public String toString() {
        return FRAMED(" MarbleContainer: ") + "\n" + marbleContainer();

    }

    private String fillMarbleContainer() {
        int sumMarbles = marbles.get(0) + marbles.get(1) + marbles.get(2) + marbles.get(3) + marbles.get(4) + marbles.get(5);
        if (sumMarbles == 0 || sumMarbles == 4) {
            return "";
        }
        else if (sumMarbles == 1) {
            return ("" + ((char)8197) + ((char)8201) + "  ║" + ((char)8196) + ((char)8196) + "   ║" + ((char)8196) + ((char)8196) + "   ║");
        }
        else if (sumMarbles == 2) {
            return ("" + ((char)8197) + ((char)8201) + "  ║" + ((char)8196) + ((char)8196) + "   ║");
        }
        else if (sumMarbles == 3) {
            return ("" + ((char)8197) + ((char)8201) + "  ║");
        }
        else return null;
    }

    private String marbleContainer() {
        if (marbles.get(0) == 0 && marbles.get(1) == 0 && marbles.get(2) == 0 && marbles.get(3) == 0 && marbles.get(4) == 0 && marbles.get(5) == 0) {
            return "╔════╦════╦════╦════╗\n" +
                    "║  " + ((char)8196) + ((char)8196) + " ║  " + ((char)8196) + ((char)8196) + " ║  " + ((char)8196) + ((char)8196) + " ║  " + ((char)8196) + ((char)8196) + " ║" +
                    "\n╚════╩════╩════╩════╝";
        }
        else {
            return "╔════╦════╦════╦════╗\n" +
                    "║ " + ((char)8201) +
                    putMarbles(0, marbles.get(0)) +
                    putMarbles(1, marbles.get(1)) +
                    putMarbles(2, marbles.get(2)) +
                    putMarbles(3, marbles.get(3)) +
                    putMarbles(4, marbles.get(4)) +
                    putMarbles(5, marbles.get(5)) + fillMarbleContainer() +
                    "\n" +
                    "╚════╩════╩════╩════╝";
        }
    }

    private String putMarbles(int marbleIndex, Integer i){
        if (i == 2) {
            return (intToBall(marbleIndex) + ((char)8201) + " ║ " + ((char)8201) + intToBall(marbleIndex) + ((char)8201) + " ║ " + ((char)8201));
        }
        else if (i == 1) {
            return (intToBall(marbleIndex) + ((char)8201) + " ║ " + ((char)8201));
        }
        else return "";
    }

    private String intToBall (Integer i) {
        if (i == 0) {
            return "\033[0;36m⏺\033[0m";
        }
        else if (i == 1) {
            return "\033[0;37m⏺\033[0m";
        }
        else if (i == 2) {
            return "\033[0;31m⏺\033[0m";
        }
        else if (i == 3) {
            return "\033[0;35m⏺\033[0m";
        }
        else if (i == 4) {
            return "⏺";
        }
        else if (i == 5) {
            return "\033[0;33m⏺\033[0m";
        }
        else return null;
    }

}
