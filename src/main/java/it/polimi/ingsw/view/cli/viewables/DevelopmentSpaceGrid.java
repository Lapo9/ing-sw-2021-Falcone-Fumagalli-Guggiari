package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.BOLD;

/**
 * Represents the three DevelopmentSpace(s) of the player
 */
public class DevelopmentSpaceGrid implements Viewable {

    private ArrayList<DevelopmentSpace> developmentSpaces = new ArrayList<>();

    /**
     * Class constructor
     */
    DevelopmentSpaceGrid() {
        developmentSpaces.add(new DevelopmentSpace());
        developmentSpaces.add(new DevelopmentSpace());
        developmentSpaces.add(new DevelopmentSpace());
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update (3 id of the cards in the single space + 15 int for the production of the top card)x3 (there are three spaces)
     */
    @Override
    public void update(int[] update) {
        int[] update1 = Arrays.copyOfRange(update, 0, 18);
        developmentSpaces.get(0).update(update1);
        int[] update2 = Arrays.copyOfRange(update, 18, 36);
        developmentSpaces.get(1).update(update2);
        int[] update3 = Arrays.copyOfRange(update, 36, 54);
        developmentSpaces.get(2).update(update3);
    }

    /**
     * Prints the three DevelopmentSpaces of the player
     * @return string with the DevelopmentSpaceGrid
     */
    @Override
    public String toString() {
        return buildDevelopmentSpaceGrid();
    }

    /**
     *
     * @return
     */
    private String buildDevelopmentSpaceGrid() {
        String tmp = "";

        tmp = tmp.concat(BOLD("Development Space\n"));

        for (int i = 0; i < 18; i++) {
            tmp = tmp.concat(cutIntoRows(0, i));
            tmp = tmp.concat("         ");
            tmp = tmp.concat(cutIntoRows(1, i));
            tmp = tmp.concat("         ");
            tmp = tmp.concat(cutIntoRows(2, i));
            tmp = tmp.concat("\n");
        }
        tmp = tmp.concat("\n");

        return tmp;
    }

    /**
     * Allows to write the DevelopmentSpace(s) side by side in the DevelopmentSpaceGrid
     * @param leaderNumber the number of the space to print
     * @param line the line to print
     * @return one line composed of the first line of every DevelopmentSpace in the DevelopmentSpaceGrid
     *         (for example for the first line (line==1): +---------------+   +---------------+   +---------------+)
     */
    private String cutIntoRows(int leaderNumber, int line) {
        String[] rows = developmentSpaces.get(leaderNumber).toString().split("\n");
        return rows[line];
    }
}
