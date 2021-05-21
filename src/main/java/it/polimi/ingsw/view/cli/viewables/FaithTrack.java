package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class FaithTrack implements Viewable {

    private HashMap<String, ArrayList<Integer>> data = new HashMap<String, ArrayList<Integer>>();
    int playerNumber = 0;
    OfflineInfo info;

    FaithTrack() {
    }

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

    @Override
    public String toString() {
        return getPlayersInfo();
    }

    private String getPlayersInfo() {
        String tmp = BOLD("Faith Track info: \n");
        if(info.getPlayersNum() != 0) {
            tmp += new StringBuilder().append(UNDERLINED(" Player name |")).append(UNDERLINED(" Position |")).append(UNDERLINED(" Pope favor tile 1 |")).append(UNDERLINED(" Pope favor tile 2 |")).append(UNDERLINED(" Pope favor tile 3 ")).append("\n").toString();
        }
        for(int i = 0; i<info.getPlayersNum(); i++)
        tmp += new StringBuilder().
                append(findSpaceNamePre(info.getPlayerName(i))).append(info.getPlayerName(i)).append(findSpaceNamePost(info.getPlayerName(i))).append("|").
                append(findSpacePositionPre(data.get(info.getPlayerName(i)).get(0))).append(data.get(info.getPlayerName(i)).get(0)).append(findSpacePositionPost(data.get(info.getPlayerName(i)).get(0))).append("|").
                append(findSpacePopeTilePre(data.get(info.getPlayerName(i)).get(1))).append(rightColor(data.get(info.getPlayerName(i)).get(1))).append(findSpacePopeTilePost(data.get(info.getPlayerName(i)).get(1))).append("|").
                append(findSpacePopeTilePre(data.get(info.getPlayerName(i)).get(2))).append(rightColor(data.get(info.getPlayerName(i)).get(2))).append(findSpacePopeTilePost(data.get(info.getPlayerName(i)).get(2))).append("|").
                append(findSpacePopeTilePre(data.get(info.getPlayerName(i)).get(3))).append(rightColor(data.get(info.getPlayerName(i)).get(3))).append(findSpacePopeTilePost(data.get(info.getPlayerName(i)).get(3))).append("\n").toString();
        return tmp;
    }

    private String findSpaceNamePre(String name) {
        int num = name.length();
        String tmp = "";
        if(num <= 13)
            num = (13 - num)/2;
        for(int i = 0; i<num; i++)
            tmp = tmp.concat(" ");
        return tmp;
    }

    private String findSpaceNamePost(String name) {
        int num = name.length();
        String tmp = "";
        if(num <= 13)
            num = (13 - num)/2;
        if(name.length() % 2 == 0)
            num++;
        for(int i = 0; i<num; i++)
            tmp = tmp.concat(" ");
        return tmp;
    }

    private String findSpacePositionPre(int num) {
        String numero = String.valueOf(num);
        String tmp = "";
        int index = numero.length();
        if(num < 10)
            tmp = tmp.concat(" ");
        if(numero.length() <= 10)
            index = (10 - index)/2;
        for(int i = 0; i<index; i++)
            tmp = tmp.concat(" ");
        return tmp;
    }

    private String findSpacePositionPost(int num) {
        String numero = String.valueOf(num);
        String tmp = "";
        int index = numero.length();
        if(numero.length() <= 10)
            index = (10 - index)/2;
        for(int i = 0; i<index; i++)
            tmp = tmp.concat(" ");
        return tmp;
    }

    private String findSpacePopeTilePre(int num) {
        /*if(num == 0 | num ==2)
            return new String("    ");
        else
            return new String("     ");*/
        return new String("         ");
    }

    private String findSpacePopeTilePost(int num) {
        /*if(num == 0)
            return new String("     ");
        else if(num == 1)
            return new String("      ");
        else
            return new String("    ");*/
        return new String("         ");
    }

    private String rightColor(int number) {
        if(number == 0)   //inactive
            return "☐"; //Inactive "));
        else if(number == 1)   //active
            return "\033[0;32m☑\033[0m"; //Active "));
        else             //discarded
            return "\033[0;31m☒\033[0m";  //Discarded "));
    }
}

