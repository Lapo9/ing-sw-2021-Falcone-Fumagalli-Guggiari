package it.polimi.ingsw.view.cli.viewables;

import java.util.ArrayList;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * Represents the MarketPlace composed by 13 marbles (12 in the grid and 1 in the slide)
 */
public class Marketplace implements Viewable {

    private ArrayList<Integer> grid = new ArrayList<>();

    /**
     * Class constructor
     */
    Marketplace() {
        for(int i = 0; i < 13; i++)
            grid.add(0);
    }

    /**
     * Updates the Marketplace after every pick
     * @param update array composed of 13 int (12 for the grid of marbles and the last for the slide)
     *               Index of the array:
     *               0 1 2 3
     *               4 5 6 7
     *               8 9 10 11
     *               12
     *               Every index is an int corresponding to the type of the marble
     */
    @Override
    public void update(int[] update) {
        for(int i = 0; i < update.length; i++) {
            grid.set(i, update[i]);
        }
    }

    /**
     * Builds the Marketplace
     * @return the string to print
     */
    @Override
    public String toString() {
        return marbleGrid();
    }

    /**
     * Composes the grid of marbles (Marketplace) using helper methods
     * @return the string to create the Marketplace
     */
    private String marbleGrid() {
        String tmp = BOLD("Marketplace: \n");
        int i = 0;
        int index = 1;

        tmp = tmp.concat("   ").concat("    1       2       3       4   ").concat("\n");

        tmp = tmp.concat("    _______ _______ _______ _______ \n");

        while(i<12){
            tmp = tmp.concat("   |       |       |       |       |\n");
            tmp = tmp.concat(" ").concat(String.valueOf(index)).concat(" ").concat("|  ").concat(marbleColor(grid.get(i))).concat("  |  ").concat(marbleColor(grid.get(i+1))).concat("  |  ").concat(marbleColor(grid.get(i+2))).concat("  |  ").concat(marbleColor(grid.get(i+3))).concat("  |  <-").concat("\n");
            if(i<=8)
                tmp = tmp.concat("   |_______|_______|_______|_______| \n");
            index++;
            i = i + 4;
        }

        tmp = tmp.concat("    ").concat("   |       |       |       |   ").concat("\n");

        tmp = tmp.concat("   Slide: ").concat(marbleColor(grid.get(12)));

        return tmp;
    }

    /**
     * Convert one int from the update to the corresponding marble
     * @param num int to convert
     * @return string with the color of the marble corresponding
     */
    private String marbleColor(int num) {
        if(num == 0)  //blue
            return FRAMED(BACK_CYAN("   "));
        else if(num == 1)  //grey
            return FRAMED(BACK_WHITE("   "));
        else if(num == 2)   //red
            return FRAMED(BACK_RED("   "));
        else if(num == 3)   //violet
            return FRAMED(BACK_MAGENTA("   "));
        else if(num == 4)   //white
            return FRAMED("   ");
        else                //yellow
            return FRAMED(BACK_YELLOW("   "));
    }
}
