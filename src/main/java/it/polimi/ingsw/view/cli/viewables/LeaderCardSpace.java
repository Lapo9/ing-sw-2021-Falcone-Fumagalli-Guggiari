package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * Represents the space containing the two LeaderCard(s) of the player
 */
public class LeaderCardSpace implements Viewable {

    private ArrayList<LeaderCard> leaderSpace = new ArrayList<>();
    private boolean type;  //true if it's the player's LeaderCardSpace
    private int state1;
    private int state2;

    /**
     * Class constructor
     * @param type of the LeaaderCard (Depot, Producer...)
     */
    LeaderCardSpace(boolean type) {
        this.type = type;
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed of 30 int: (id, state, fixed input, fixed output, fixed output, 5 int for the current supply of the ability
     *               Production, 5 int for the ability Depot if existing)x2
     */
    @Override
    public void update(int[] update) {
        int[] update1 = Arrays.copyOfRange(update, 0, 15);
        leaderSpace.get(0).update(update1);
        state1 = update1[1];
        int[] update2 = Arrays.copyOfRange(update, 15, 30);
        leaderSpace.get(1).update(update2);
        state2 = update2[1];
    }

    /**
     * Prints the two LeaderCard(s) of the player
     * @return string with the LeaderCardSpace
     */
    @Override
    public String toString() {
       return buildLeaderSpace();
    }

    /**
     * Builds the LeaderCardSpace
     * @return the String to print
     */
    private String buildLeaderSpace() {
        String tmp = "";
        tmp = tmp.concat(BOLD("Leader cards: \n"));
        if(type || (state2 != 0 && state1 != 0)) {
            tmp = tmp.concat("       Leader card 1      " + "         " + "       Leader card 2      " + "\n");

            for (int i = 0; i < 12; i++) {
                tmp = tmp.concat(cutIntoRows(0, i));
                tmp = tmp.concat("         ");
                tmp = tmp.concat(cutIntoRows(1, i));
                tmp = tmp.concat("\n");
            }
        } else {
            if(state1 == 0 && state2 == 0) {
                tmp = tmp.concat("       Leader card 1      " + "         " + "       Leader card 2      " + "\n");

                tmp = tmp.concat(" ________________________           ________________________ \n");

                for(int i = 0; i<10; i++)
                    tmp = tmp.concat("|                        |         |                        |\n");

                tmp = tmp.concat("|________________________|         |________________________|\n");
            }
            else if (state2 != 0) {
                tmp = tmp.concat("       Leader card 1      " + "         " + "       Leader card 2      " + "\n");

                for (int i = 0; i < 12; i++) {
                    if(i == 0)
                        tmp = tmp.concat(" ________________________ ");
                    else if(i == 11)
                        tmp = tmp.concat("|________________________|");
                    else
                        tmp = tmp.concat("|                        |");
                    tmp = tmp.concat("         ");
                    tmp = tmp.concat(cutIntoRows(1, i));
                    tmp = tmp.concat("\n");
                }
            }
            else {
                tmp = tmp.concat("       Leader card 1      " + "         " + "       Leader card 2      " + "\n");

                for (int i = 0; i < 12; i++) {
                    tmp = tmp.concat(cutIntoRows(0, i));
                    tmp = tmp.concat("         ");
                    if(i == 0)
                        tmp = tmp.concat(" ________________________ ");
                    else if(i == 11)
                        tmp = tmp.concat("|________________________|");
                    else
                        tmp = tmp.concat("|                        |");
                    tmp = tmp.concat("\n");
                }
            }
        }
        return tmp;
    }

    /**
     * Allows to print the two cards one beside the other
     * @param leaderNumber number of the LeaderCard, we will return the line number "line" of this card
     * @param line of the LeaderCard to print
     * @return the line chosen of the LeaderCard chosen
     */
    private String cutIntoRows(int leaderNumber, int line) {
        String[] rows = leaderSpace.get(leaderNumber).toString().split("\n");
        return rows[line];
    }
}
