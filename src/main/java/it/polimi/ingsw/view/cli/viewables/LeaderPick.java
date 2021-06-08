package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

public class LeaderPick implements Viewable {
    ArrayList<LeaderCard> leaderSpace = new ArrayList<>();

    LeaderPick() {
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
    }

    @Override
    public void update(int[] update) {
        int[] update1 = Arrays.copyOfRange(update, 0, 15);
        leaderSpace.get(0).update(update1);
        int[] update2 = Arrays.copyOfRange(update, 15, 30);
        leaderSpace.get(1).update(update2);
        int[] update3 = Arrays.copyOfRange(update, 30, 45);
        leaderSpace.get(2).update(update3);
        int[] update4 = Arrays.copyOfRange(update, 45, 60);
        leaderSpace.get(3).update(update4);
    }

    @Override
    public String toString() {
        return buildLeaderSpace();
    }

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

    private String cutIntoRows(int leaderNumber, int line) {
        String[] rows = leaderSpace.get(leaderNumber).toString().split("\n");
        return rows[line];
    }
}
