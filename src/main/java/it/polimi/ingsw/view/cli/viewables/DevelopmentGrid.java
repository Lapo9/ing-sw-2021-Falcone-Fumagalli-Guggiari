package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.view.cli.Viewable;
import it.polimi.ingsw.view.cli.viewables.DevelopmentGridCard.*;

import java.util.ArrayList;
import java.util.HashMap;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class DevelopmentGrid implements Viewable {

    ArrayList<DevelopmentGridCard> devGrid = new ArrayList<>();

    DevelopmentGrid() {
        for (int i = 0; i<12; i++) {
            devGrid.add(new DevelopmentGridCard());
        }
    }

    @Override
    public void update(int[] update) {
        //i receive an id for every card
        for (int i = 0; i<12; i++) {
            int [] updateCard = {update[i]};
            devGrid.get(i).update(updateCard);
        }
    }

    @Override
    public String toString() {
        return BOLD("Development Grid: ") + "\n" + buildDevGrid();
    }

    private String buildDevGrid() {
        StringBuilder developmentGrid = new StringBuilder();

        for (int i = 0; i < 11 ; i++){
            for(int j = 0; j < 4; j++){
                developmentGrid.append(devGrid.get(j).rowToString(i) + "      ");
            }
            developmentGrid.append("\n");
        }
        developmentGrid.append("\n");

        for (int i = 0; i < 11 ; i++){
            for(int j = 4; j < 8; j++){
                developmentGrid.append(devGrid.get(j).rowToString(i) + "      ");
            }
            developmentGrid.append("\n");
        }
        developmentGrid.append("\n");

        for (int i = 0; i < 11 ; i++){
            for(int j = 8; j < 12; j++){
                developmentGrid.append(devGrid.get(j).rowToString(i) + "      ");
            }
            developmentGrid.append("\n");
        }

        return developmentGrid.toString();
    }

}
