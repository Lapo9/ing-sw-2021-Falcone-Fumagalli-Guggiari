package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * Represents the position of all players in the faith track
 */
public class FaithTrack implements Viewable {

    private HashMap<String, ArrayList<Integer>> data = new HashMap<String, ArrayList<Integer>>();
    int playerNumber = 0;
    OfflineInfo info;

    /**
     * Class constructor
     */
    FaithTrack() {
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed of 5 numbers (player's number in the queue, position of the first player, second, third, fourth)
     * @param info containing the number and the name of the player
     */
    @Override
    public void update(int[] update, OfflineInfo info) {
        playerNumber = info.getPlayersNum();
        if (playerNumber != 0) {                     //check if there are any players left

            String playerName = info.getPlayerName(update[0]);

            if (!data.containsKey(playerName)) {     //creates a map with all the elements
                for (int i = 0; i < info.getPlayersNum(); i++) {
                    if(!data.containsKey(info.getPlayerName(i))) {
                        String tmp = info.getPlayerName(i);
                        data.put(tmp, new ArrayList<>());
                        data.get(tmp).add(0);
                        data.get(tmp).add(0);
                        data.get(tmp).add(0);
                        data.get(tmp).add(0);
                    }
                }
            }

            data.get(playerName).set(0, update[1]);
            data.get(playerName).set(1, update[2]);
            data.get(playerName).set(2, update[3]);
            data.get(playerName).set(3, update[4]);
        }
        this.info = info;
    }

    /**
     * Prints the positions of the players in the faith track in a table and a simple graphic representation of the faith track
     * @return the positions table
     */
    @Override
    public String toString() {
        return getPlayersInfo() + getFaithTrack();
    }

    /**
     * Table
     * @return the positions table to print
     */
    private String getPlayersInfo() {
        String tmp = BOLD("Faith Track info: \n");
        if(info.getPlayersNum() != 0) {
            tmp += new StringBuilder().append(UNDERLINED(" Player name |")).append(UNDERLINED(" Position |")).append(UNDERLINED(" Pope favor tile 1 |")).append(UNDERLINED(" Pope favor tile 2 |")).append(UNDERLINED(" Pope favor tile 3 ")).append("\n").toString();
        }
        for(int i = 0; i<info.getPlayersNum(); i++)
        tmp += new StringBuilder().
                append(findSpaceName(info.getPlayerName(i), 1)).append(info.getPlayerName(i)).append(findSpaceName(info.getPlayerName(i), 2)).append("|").
                append(findSpacePosition(data.get(info.getPlayerName(i)).get(0), 1)).append(data.get(info.getPlayerName(i)).get(0)).append(findSpacePosition(data.get(info.getPlayerName(i)).get(0), 2)).append("|").
                append(rightColor(data.get(info.getPlayerName(i)).get(1))).append("|").
                append(rightColor(data.get(info.getPlayerName(i)).get(2))).append("|").
                append(rightColor(data.get(info.getPlayerName(i)).get(3))).append("\n").toString();
        return tmp;
    }

    /**
     * Helper function needed to find the right amount of space character
     * @param name the name of the player
     * @param type 1 if the function needs to calculate the right amount of spaces before the name, 2 if after the name
     * @return a string made of space character
     */
    private String findSpaceName(String name, int type) {
        int num = name.length();
        String tmp = "";
        if(num <= 13)
            num = (13 - num)/2;
        if(name.length() % 2 == 0 && type == 2)
            num++;
        for(int i = 0; i<num; i++)
            tmp = tmp.concat(" ");
        return tmp;
    }

    /**
     * Helper function needed to find the right amount of space character
     * @param num the position of the player
     * @param type 1 if the function needs to calculate the right amount of spaces before the position number, 2 if after the name
     * @return a string made of space character
     */
    private String findSpacePosition(int num, int type) {
        String numero = String.valueOf(num);
        String tmp = "";
        int index = numero.length();
        if(num < 10 && type == 1)
            tmp = tmp.concat(" ");
        if(numero.length() <= 10)
            index = (10 - index)/2;
        for(int i = 0; i<index; i++)
            tmp = tmp.concat(" ");
        return tmp;
    }

    /**
     * Combine the pope favor tile considered and its status
     * @param number of the chosen pope favor tile
     * @return the status
     */
    private String rightColor(int number) {
        if(number == 0)   //inactive
            return "     inactive      ";
        else if(number == 1)   //active
            return GREEN("      active       ");
        else             //discarded
            return RED("     discarded     ");
    }

    /**
     * Creates a simple graphic representation of the faith track
     * @return the faith track
     */
    private String getFaithTrack() {
        String tmp = "\n";

        tmp = tmp.concat(FRAMED(" 0 |")).concat(FRAMED(" 1 |")).concat(FRAMED(" 2 |")).concat(FRAMED(" 3 |")).concat(FRAMED(" 4 ")).concat(BACK_YELLOW(FRAMED("| 5 |"))).concat(BACK_YELLOW(FRAMED(" 6 |"))).concat(BACK_YELLOW(FRAMED(" 7 "))).concat(BACK_RED(FRAMED("| 8 |")));
        tmp = tmp.concat(FRAMED(" 9 |")).concat(FRAMED(" 10 |")).concat(FRAMED(" 11 ")).concat(BACK_YELLOW(FRAMED("| 12 |"))).concat(BACK_YELLOW(FRAMED(" 13 |"))).concat(BACK_YELLOW(FRAMED(" 14 |"))).concat(BACK_YELLOW(FRAMED(" 15 "))).concat(BACK_RED(FRAMED("| 16 |")));
        tmp = tmp.concat(FRAMED(" 17 |")).concat(FRAMED(" 18 ")).concat(BACK_YELLOW(FRAMED("| 19 |"))).concat(BACK_YELLOW(FRAMED(" 20 |"))).concat(BACK_YELLOW(FRAMED(" 21 |"))).concat(BACK_YELLOW(FRAMED(" 22 |"))).concat(BACK_YELLOW(FRAMED(" 23 "))).concat(BACK_RED(FRAMED("| 24 "))).concat("\n");

        return tmp;
    }
}

