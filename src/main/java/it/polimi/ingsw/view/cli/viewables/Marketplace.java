package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.ScreenCLI;
import it.polimi.ingsw.view.cli.View;
import it.polimi.ingsw.view.cli.Viewable;
import it.polimi.ingsw.view.cli.ViewableId;

import java.util.ArrayList;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class Marketplace implements Viewable {

    private ArrayList<Integer> grid = new ArrayList<>();

    Marketplace() {
        for(int i = 0; i < 13; i++)
            grid.add(0);
    }

    @Override
    public void update(int[] update) {
        for(int i = 0; i < update.length; i++) {
            grid.set(i, update[i]);
        }
    }

    @Override
    public String toString() {
        return marbleGrid();
    }

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
