package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the space containing the four LeaderCard(s) picked by the player
 * he will choose two of them
 */
public class LeaderPick implements Viewable {
    ArrayList<LeaderCard> leaderSpace = new ArrayList<>();

    /**
     * Class constructor
     */
    LeaderPick() {
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed of 60 int: (id, state, fixed input, fixed output, fixed output, 5 int for the current supply of the ability
     *               Production, 5 int for the ability Depot if existing)x4
     */
    @Override
    public void update(int[] update) {
        int[] update1 = Arrays.copyOfRange(update, 0, 15); //calls the update of LeaderCardSpace
        leaderSpace.get(0).update(update1);
        int[] update2 = Arrays.copyOfRange(update, 15, 30);
        leaderSpace.get(1).update(update2);
        int[] update3 = Arrays.copyOfRange(update, 30, 45);
        leaderSpace.get(2).update(update3);
        int[] update4 = Arrays.copyOfRange(update, 45, 60);
        leaderSpace.get(3).update(update4);
    }

    /**
     * Prints the four LeaderCard(s) from which the player will have to choose
     * @return string with the LeaderPick
     */
    @Override
    public String toString() {
        return buildLeaderSpace();
    }

    /**
     * Builds the LeaderPick
     * @return the String to print
     */
    private String buildLeaderSpace() {
        String tmp = "";

        tmp = tmp.concat("       Leader card 1      " + "         " + "       Leader card 2      " + "         " + "       Leader card 3      " + "         " + "       Leader card 4      " + "\n");

        for(int i = 0; i<12; i++) {
            tmp = tmp.concat(cutIntoRows(0, i));
            tmp = tmp.concat("         ");
            tmp = tmp.concat(cutIntoRows(1, i));
            tmp = tmp.concat("         ");
            tmp = tmp.concat(cutIntoRows(2, i));
            tmp = tmp.concat("         ");
            tmp = tmp.concat(cutIntoRows(3, i));
            tmp = tmp.concat("\n");
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
