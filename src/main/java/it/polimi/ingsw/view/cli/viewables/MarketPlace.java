package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.ScreenCLI;
import it.polimi.ingsw.view.cli.View;
import it.polimi.ingsw.view.cli.Viewable;
import it.polimi.ingsw.view.cli.ViewableId;

import java.util.ArrayList;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.BOLD;

public class MarketPlace implements Viewable {

    private ArrayList<Integer> grid = new ArrayList<>();

    MarketPlace() {
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
        String tmp = BOLD("Market Place: \n");
        int i = 0;
        int index = 1;

        tmp = tmp.concat("   ").concat("  ").concat(String.valueOf((char)8201)).concat("1").concat(String.valueOf((char)8201)).concat("   ").concat(String.valueOf((char)8201)).concat(String.valueOf((char)8201)).concat("2").concat(String.valueOf((char)8201)).concat(String.valueOf((char)8201)).concat("   ").concat(String.valueOf((char)8201)).concat("3").concat(String.valueOf((char)8201)).concat(String.valueOf((char)8201)).concat("   ").concat(String.valueOf((char)8201)).concat("4").concat(String.valueOf((char)8201)).concat("\n");

        tmp = tmp.concat("   ╔════╦════╦════╦════╗\n");

        while(i<12){
            tmp = tmp.concat(" ").concat(String.valueOf(index)).concat(" ").concat("║ ").concat(String.valueOf((char)8201)).concat(marbleColor(grid.get(i))).concat(String.valueOf((char)8201)).concat(" ║ ").concat(String.valueOf((char)8201)).concat(marbleColor(grid.get(i+1))).concat(String.valueOf((char)8201)).concat(" ║ ").concat(String.valueOf((char)8201)).concat(marbleColor(grid.get(i+2))).concat(String.valueOf((char)8201)).concat(" ║ ").concat(String.valueOf((char)8201)).concat(marbleColor(grid.get(i+3))).concat(String.valueOf((char)8201)).concat(" ║").concat("\n");
            if(i<8)
                tmp = tmp.concat("   ╠════╬════╬════╬════╣ \n");
            i = i + 4;
            index++;
        }

        tmp = tmp.concat("   ╚════╩════╩════╩════╝\n");

        tmp = tmp.concat("   Slide: ").concat(marbleColor(grid.get(12)));

        return tmp;
    }

    private String marbleColor(int num) {
        if(num == 0)  //blue
            return "\033[0;36m⏺\033[0m";
        else if(num == 1)  //grey
            return "\033[0;37m⏺\033[0m";
        else if(num == 2)   //red
            return "\033[0;31m⏺\033[0m";
        else if(num == 3)   //violet
            return "\033[0;35m⏺\033[0m";
        else if(num == 4)   //white
            return "⏺";
        else                //yellow
            return "\033[0;33m⏺\033[0m";
    }
}
