package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.Viewable;
import it.polimi.ingsw.view.cli.fancy_console.FancyConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PlayersStatus implements Viewable {
    private ArrayList<Pair<String, Integer>> playersStatus = new ArrayList<>(); //0 = online, 1 = offline, 2 = current
    private String matchStatus = "";

    @Override
    public void update(int[] update, OfflineInfo oi) throws NoSuchMethodException {
        for (int i = 0; i < playersStatus.size(); ++i){
            try {
                playersStatus.get(i).first = oi.getPlayerName(i);
            } catch (IndexOutOfBoundsException ioobe){
                playersStatus.get(i).first = "";
            }
            playersStatus.get(i).second = update[i];
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < playersStatus.stream().filter(p -> !p.first.equals("")).count(); ++i){
            switch (playersStatus.get(i).second){
                case 0:
                    res.append(FancyConsole.FRAMED(" " + playersStatus.get(i).first + " "));
                    break;
                case 1:
                    res.append(FancyConsole.FRAMED(FancyConsole.RED(" " + playersStatus.get(i).first + " ")));
                    break;
                case 2:
                    res.append(FancyConsole.FRAMED(FancyConsole.GREEN(" " + playersStatus.get(i).first + " ")));
                    break;
            }
            res.append("\n");
        }

        return res.toString();
    }

}
