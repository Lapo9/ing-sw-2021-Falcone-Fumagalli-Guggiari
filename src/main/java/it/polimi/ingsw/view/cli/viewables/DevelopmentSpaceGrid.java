package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.BOLD;

public class DevelopmentSpaceGrid implements Viewable {

    private ArrayList<DevelopmentSpace> developmentSpaces = new ArrayList<>();

    DevelopmentSpaceGrid() {
        developmentSpaces.add(new DevelopmentSpace());
        developmentSpaces.add(new DevelopmentSpace());
        developmentSpaces.add(new DevelopmentSpace());
    }

    @Override
    public void update(int[] update) {
        int[] update1 = Arrays.copyOfRange(update, 0, 18);
        developmentSpaces.get(0).update(update1);
        int[] update2 = Arrays.copyOfRange(update, 18, 36);
        developmentSpaces.get(1).update(update2);
        int[] update3 = Arrays.copyOfRange(update, 36, 54);
        developmentSpaces.get(2).update(update3);
    }

    @Override
    public String toString() {
        return buildDevelopmentSpaceGrid();
    }

    private String buildDevelopmentSpaceGrid() {
        String tmp = "";

        tmp = tmp.concat(BOLD("Development Space\n"));

        for (int i = 0; i < 16; i++) {
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

    private String cutIntoRows(int leaderNumber, int line) {
        String[] rows = developmentSpaces.get(leaderNumber).toString().split("\n");
        return rows[line];
    }
}
