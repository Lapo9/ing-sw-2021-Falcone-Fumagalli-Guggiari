package it.polimi.ingsw.view.cli.viewables;

import java.util.ArrayList;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * Represents the grid composed by every card of teh game
 */
public class DevelopmentGrid implements Viewable {

    ArrayList<DevelopmentGridCard> devGrid = new ArrayList<>();

    /**
     * Class constructor
     */
    DevelopmentGrid() {
        for (int i = 0; i<12; i++) {
            devGrid.add(new DevelopmentGridCard());
        }
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed by 12 id of the cards in the grid (used to update the cost, category, level, win points AND the production
     *               of the card, those info are from the development cards file)
     */
    @Override
    public void update(int[] update) {
        //i receive an id for every card
        for (int i = 0; i<12; i++) {
            int [] updateCard = {update[i]};
            devGrid.get(i).update(updateCard);
        }
    }

    /**
     * Prints the development grid
     * @return string with the development grid
     */
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
