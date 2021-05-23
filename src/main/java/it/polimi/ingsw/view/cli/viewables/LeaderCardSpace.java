package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.Arrays;

public class LeaderCardSpace implements Viewable {

    private ArrayList<LeaderCard> leaderSpace = new ArrayList<>();
    private boolean type;  //true if it's the player's LeaderCardSpace
    private int state1;
    private int state2;

    LeaderCardSpace(boolean type) {
        this.type = type;
        leaderSpace.add(new LeaderCard());
        leaderSpace.add(new LeaderCard());
    }

    @Override
    public void update(int[] update) {
        int[] update1 = Arrays.copyOfRange(update, 0, 15);
        leaderSpace.get(0).update(update1);
        state1 = update1[1];
        int[] update2 = Arrays.copyOfRange(update, 15, 30);
        leaderSpace.get(1).update(update2);
        state2 = update2[1];
    }

    @Override
    public String toString() {
       return buildLeaderSpace();
    }

    private String buildLeaderSpace() {
        String tmp = "";
        if(type || (state2 != 0 && state1 != 0)) {
            tmp = tmp.concat("      Leader card 1      " + "         " + "      Leader card 2      " + "\n");

            for (int i = 0; i < 11; i++) {
                tmp = tmp.concat(cutIntoRows(0, i));
                tmp = tmp.concat("         ");
                tmp = tmp.concat(cutIntoRows(1, i));
                tmp = tmp.concat("\n");
            }
            tmp = tmp.concat("\n");
        } else {
            if(state1 == 0 && state2 == 0) {
                tmp = tmp.concat("      Leader card 1      " + "         " + "      Leader card 2      " + "\n");

                tmp = tmp.concat("╔═══════════════════════╗         ╔═══════════════════════╗\n");

                for(int i = 0; i<9; i++)
                    tmp = tmp.concat("║                       ║         ║                       ║\n");

                tmp = tmp.concat("╚═══════════════════════╝         ╚═══════════════════════╝\n");

                tmp = tmp.concat("\n");
            }
            else if (state2 != 0) {
                tmp = tmp.concat("      Leader card 1      " + "         " + "      Leader card 2      " + "\n");

                for (int i = 0; i < 11; i++) {
                    if(i == 0)
                        tmp = tmp.concat("╔═══════════════════════╗");
                    else if(i == 10)
                        tmp = tmp.concat("╚═══════════════════════╝");
                    else
                        tmp = tmp.concat("║                       ║");
                    tmp = tmp.concat("         ");
                    tmp = tmp.concat(cutIntoRows(1, i));
                    tmp = tmp.concat("\n");
                }

                tmp = tmp.concat("\n");
            }
            else {
                tmp = tmp.concat("      Leader card 1      " + "         " + "      Leader card 2      " + "\n");

                for (int i = 0; i < 11; i++) {
                    tmp = tmp.concat(cutIntoRows(0, i));
                    tmp = tmp.concat("         ");
                    if(i == 0)
                        tmp = tmp.concat("╔═══════════════════════╗");
                    else if(i == 10)
                        tmp = tmp.concat("╚═══════════════════════╝");
                    else
                        tmp = tmp.concat("║                       ║");
                    tmp = tmp.concat("\n");
                }

                tmp = tmp.concat("\n");
            }
        }
        return tmp;
    }

    private String cutIntoRows(int leaderNumber, int line) {
        String[] rows = leaderSpace.get(leaderNumber).toString().split("\n");
        return rows[line];
    }
}
