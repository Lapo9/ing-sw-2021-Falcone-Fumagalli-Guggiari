package it.polimi.ingsw.view.cli.viewables;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

import static it.polimi.ingsw.model.development.DevelopmentCard.*;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;


/**
 * Represents the single development space, consisting of one, two or three stacked cards
 */
public class DevelopmentSpace implements Viewable {

    private int num = 0; //for the number of the DevelopmentSpace (there are three fo them)
    private HashMap<Integer, DevelopmentCard> devSpace = new HashMap<>();
    private Integer wp0, wp1;

    /**
     * Class constructor
     */
    DevelopmentSpace () {
        wp0 = 0;
        wp1 = 0;
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed by 3 id of the cards in the DevelopmentSpace (used to update the cost, category, level, win points BUT NOT
     *               the production of the card, those info are from the development cards file), then the array is composed of other 15 int
     *               to update the production of the card on the top
     */
    @Override
    public void update(int[] update) {
        //reinitialize every time the DevelopmentSpace
        devSpace.put(0, new DevelopmentCard());
        devSpace.put(1, new DevelopmentCard());
        devSpace.put(2, new DevelopmentCard());

        //empty space
        if (update[0] == 0 && update[1] == 0 && update[2] == 0) {
            //empty
            devSpace.put(0, new DevelopmentCard());
            devSpace.remove(1);
            devSpace.remove(2);
        }
        //one card
        else if (update[0] != 0 && update[1] == 0 && update[2] == 0) {
            devSpace.remove(1);
            devSpace.remove(2);
            //support array to use easily the update of the single DevelopmentCard
            int [] production = {
                    update[0], update [3], update [4], update [5], update [6], update [7],
                    update [8], update [9], update [10], update [11], update [12],
                    update [13], update [14], update [15], update [16], update [17]
            };

            //just one card to update, the first
            devSpace.get(0).update(production);
        }
        //two cards
        else if (update[0] != 0 && update[1] != 0 && update[2] == 0) {
            devSpace.remove(2);
            //update the top card (with the new production) and only the wp of the other one
            int [] production1 = {
                    update[0], update [3], update [4], update [5], update [6], update [7],
                    update [8], update [9], update [10], update [11], update [12],
                    update [13], update [14], update [15], update [16], update [17]
            };
            int [] production2 = {
                    update[1], update [3], update [4], update [5], update [6], update [7],
                    update [8], update [9], update [10], update [11], update [12],
                    update [13], update [14], update [15], update [16], update [17]
            };

            //updating only wp (i have to eliminate other things
            devSpace.get(0).update(production1);
            wp0 = getWinPoints(update[0]);

            //just one card to update, the first
            devSpace.get(1).update(production2);
        }
        //three cards (full space)
        else if (update[0] != 0 && update[1] != 0) {
            //update the top card and only the wp of the other two
            int [] production1 = {
                    update[0], update [3], update [4], update [5], update [6], update [7],
                    update [8], update [9], update [10], update [11], update [12],
                    update [13], update [14], update [15], update [16], update [17]
            };
            int [] production2 = {
                    update[1], update [3], update [4], update [5], update [6], update [7],
                    update [8], update [9], update [10], update [11], update [12],
                    update [13], update [14], update [15], update [16], update [17]
            };
            int [] production3 = {
                    update[2], update [3], update [4], update [5], update [6], update [7],
                    update [8], update [9], update [10], update [11], update [12],
                    update [13], update [14], update [15], update [16], update [17]
            };
            //updating only wp (i have to eliminate other things
            devSpace.get(0).update(production1);
            devSpace.get(1).update(production2);
            wp0 = getWinPoints(update[0]);
            wp1 = getWinPoints(update[1]);
            //just one card to update, the first
            devSpace.get(2).update(production3);
        }
    }

    /**
     * Prints the development space
     * @return string with the development grid
     */
    @Override
    public String toString() {
        return printOnlyNotNull();
    }

    /**
     * Prints the top card (complete) and the possible underlying cards (only the win points and the card category)
     * @return
     */
    private String printOnlyNotNull () {
        if (devSpace.get(0) != null && devSpace.get(1) != null && devSpace.get(2) != null && wp0 < 10 && wp1 < 10) {
            //i have to print ONLY THE WIN POINTS and the CATEGORY of the first and second card
            return devSpace.get(2).toString() + "\n" +
                    "| "+devSpace.get(1).categoryToColor()+"   " + FRAMED("  Win Points: " + wp1.toString() + " ") + "     "+devSpace.get(1).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+" + "\n" +
                    "| "+devSpace.get(0).categoryToColor()+"   " + FRAMED("  Win Points: " + wp0.toString() + " ") + "     "+devSpace.get(0).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+\n";
        }
        else if (devSpace.get(0) != null && devSpace.get(1) != null && devSpace.get(2) != null && wp0 < 10 && wp1 > 9) {
            //i have to print ONLY THE WIN POINTS and the CATEGORY of the first and second card
            return devSpace.get(2).toString() + "\n" +
                    "| "+devSpace.get(1).categoryToColor()+"   " + FRAMED("  Win Points: " + wp1.toString() + " ") + "    "+devSpace.get(1).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+" + "\n" +
                    "| "+devSpace.get(0).categoryToColor()+"   " + FRAMED("  Win Points: " + wp0.toString() + " ") + "     "+devSpace.get(0).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+\n";
        }
        else if (devSpace.get(0) != null && devSpace.get(1) != null && devSpace.get(2) != null && wp0 >9 && wp1 < 10) {
            //i have to print ONLY THE WIN POINTS and the CATEGORY of the first and second card
            return devSpace.get(2).toString() + "\n" +
                    "| "+devSpace.get(1).categoryToColor()+"   " + FRAMED("  Win Points: " + wp1.toString() + " ") + "     "+devSpace.get(1).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+" + "\n" +
                    "| "+devSpace.get(0).categoryToColor()+"   " + FRAMED("  Win Points: " + wp0.toString() + " ") + "    "+devSpace.get(0).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+\n";
        }
        else if (devSpace.get(0) != null && devSpace.get(1) != null && devSpace.get(2) != null && wp0 >9 && wp1 > 9) {
            //i have to print ONLY THE WIN POINTS and the CATEGORY of the first and second card
            return devSpace.get(2).toString() + "\n" +
                    "| "+devSpace.get(1).categoryToColor()+"   " + FRAMED("  Win Points: " + wp1.toString() + " ") + "    "+devSpace.get(1).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+" + "\n" +
                    "| "+devSpace.get(0).categoryToColor()+"   " + FRAMED("  Win Points: " + wp0.toString() + " ") + "    "+devSpace.get(0).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+\n";
        }
        else if (devSpace.get(0) != null && devSpace.get(1) != null && devSpace.get(2) == null) {
            //i have to print ONLY THE WIN POINTS and the CATEGORY of the first card
            return devSpace.get(1).toString() + "\n" +
                    "| "+devSpace.get(0).categoryToColor()+"   " + FRAMED("  Win Points: " + wp0.toString() + " ") + "     "+devSpace.get(0).categoryToColor()+" |" + "\n" +
                    "+--------------------------------+\n" +
                    "                                  \n" +
                    "                                  \n";
        }
        else if (devSpace.get(0) != null && devSpace.get(1) == null && devSpace.get(2) == null) {
            return devSpace.get(0).toString() + "\n"+
                    "                                  \n" +
                    "                                  \n" +
                    "                                  \n" +
                    "                                  \n";
        }
        else if (devSpace.get(0) == null && devSpace.get(1) == null && devSpace.get(2) == null){
            return "+--------------------------------+" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "+--------------------------------+" + "\n" +
                    "                                  \n" +
                    "                                  \n" +
                    "                                  \n" +
                    "                                  \n";
        }
        return null;
    }
}
