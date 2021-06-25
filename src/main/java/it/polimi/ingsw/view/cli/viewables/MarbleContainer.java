package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.MarbleColor;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.model.MarbleColor.*;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * Contains the marbles picked from the Marketplace
 */
public class MarbleContainer implements Viewable {

    private ArrayList<Integer> marbles = new ArrayList<>();

    /**
     * Class constructor
     */
    MarbleContainer () {
        marbles.add(0, 0); //blue
        marbles.add(1, 0); //grey
        marbles.add(2, 0); //red
        marbles.add(3, 0); //violet
        marbles.add(4, 0); //white
        marbles.add(5, 0); //yellow
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array of 6 int (every int correspond to the number of the corresponding marble)
     *               example: 1 0 0 2 0 0 --> we have 1 shield, 2 servant and an empty space in the container
     */
    @Override
    public void update(int[] update) {
        marbles.add(0, update[0]); //blue
        marbles.add(1, update[1]); //grey
        marbles.add(2, update[2]); //red
        marbles.add(3, update[3]); //violet
        marbles.add(4, update[4]); //white
        marbles.add(5, update[5]); //yellow
    }

    /**
     * Builds the MarbleContainer
     * @return string ready to print
     */
    @Override
    public String toString() {
        return FRAMED(" MarbleContainer: ") + "\n" + marbleContainer();

    }

    /**
     * Fills the MarbleContainer with empty spaces, when we remove a marble (to put the corresponding resource
     * in the Warehouse) we have to empty the MarbleContainer
     * @return string with graphic changes
     */
    private String fillMarbleContainer() {
        int sumMarbles = marbles.get(0) + marbles.get(1) + marbles.get(2) + marbles.get(3) + marbles.get(4) + marbles.get(5);
        if (sumMarbles == 0 || sumMarbles == 4) {
            return "";
        }
        else if (sumMarbles == 1) {
            return "    |" + "     |" + "     |";
        }
        else if (sumMarbles == 2) {
            return "    |" + "     |";
        }
        else if (sumMarbles == 3) {
            return "    |";
        }
        else return null;
    }

    /**
     * Composes the MarbleContainer using some helper methods
     * @return string with the MarbleContainer
     */
    private String marbleContainer() {
        if (marbles.get(0) == 0 && marbles.get(1) == 0 && marbles.get(2) == 0 && marbles.get(3) == 0 && marbles.get(4) == 0 && marbles.get(5) == 0) {
            return " _____ _____ _____ _____\n" +
                   "|     |     |     |     |\n" +
                   "|     |     |     |     |\n" +
                   "|_____|_____|_____|_____|";
        }
        else {
            return  " _____ _____ _____ _____\n" +
                    "|     |     |     |     |\n" +
                    "| " +
                    putMarbles(0, marbles.get(0)) +
                    putMarbles(1, marbles.get(1)) +
                    putMarbles(2, marbles.get(2)) +
                    putMarbles(3, marbles.get(3)) +
                    putMarbles(4, marbles.get(4)) +
                    putMarbles(5, marbles.get(5)) + fillMarbleContainer() +
                    "\n" +
                    "|_____|_____|_____|_____|";
        }
    }

    /**
     * Places the marbles in the MarbleContainer from the ArrayList of marbles
     * @param marbleIndex index of the marble (0==SHIELD, 1==COIN, ...)
     * @param i idex of the ArrayList of marbles
     * @return
     */
    private String putMarbles(int marbleIndex, Integer i){
        if (i == 2) {
            return intToBall(marbleIndex) + " | " +  intToBall(marbleIndex) + " | ";
        }
        else if (i == 1) {
            return intToBall(marbleIndex)  + " | ";
        }
        else return "";
    }

    /**
     * Convert one int from the update to the corresponding marble
     * @param i int to convert
     * @return string with the color of the marble corresponding
     */
    private String intToBall (Integer i) {
        if (i == 0) {
            return FRAMED(BACK_CYAN("   "));
        }
        else if (i == 1) {
            return FRAMED(BACK_WHITE("   "));
        }
        else if (i == 2) {
            return FRAMED(BACK_RED("   "));
        }
        else if (i == 3) {
            return FRAMED(BACK_MAGENTA("   "));
        }
        else if (i == 4) {
            return FRAMED("   ");
        }
        else if (i == 5) {
            return FRAMED(BACK_YELLOW("   "));
        }
        else return null;
    }

}
