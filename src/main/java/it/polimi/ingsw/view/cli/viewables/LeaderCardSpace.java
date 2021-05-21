package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

public class LeaderCardSpace implements Viewable {

    ArrayList<LeaderCard> leaderSpace = new ArrayList<>();

    LeaderCardSpace() {
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
    }

    @Override
    public void update(int[] update) {
        int[] update1 = Arrays.copyOfRange(update, 0, 15);
        leaderSpace.get(0).update(update1);
        int[] update2 = Arrays.copyOfRange(update, 15, 30);
        leaderSpace.get(1).update(update2);
    }

    @Override
    public String toString() {
       return buildLeaderSpace();
    }

    private String buildLeaderSpace() {
       String tmp = "";

       tmp = tmp.concat("      Leader card 1      " + "         " + "      Leader card 2      " + "\n");

       for(int i = 0; i<11; i++) {
           tmp = tmp.concat(cutIntoRows(0, i));
           tmp = tmp.concat("         ");
           tmp = tmp.concat(cutIntoRows(1, i));
           tmp = tmp.concat("\n");
       }
       tmp = tmp.concat("\n");

       return tmp;
    }

    private String cutIntoRows(int leaderNumber, int line) {
        String[] rows = leaderSpace.get(leaderNumber).toString().split("\n");
        return rows[line];
    }
}
