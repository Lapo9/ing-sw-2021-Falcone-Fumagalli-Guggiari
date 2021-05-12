package it.polimi.ingsw.view.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OfflineInfo {
    private HashMap<String, Boolean> activeProductions = new HashMap<>();
    private boolean isLeader1Producer = false;
    private boolean isLeader2Producer = false;
    private ArrayList<String> playersNamesInOrder = new ArrayList<>();
    private boolean autoRefresh = true;


    public OfflineInfo(){
        reset();
    }


    public synchronized void reset(){
        activeProductions.put("dev1", false);
        activeProductions.put("dev2", false);
        activeProductions.put("dev3", false);
        activeProductions.put("leader1", false);
        activeProductions.put("leader2", false);
        activeProductions.put("base", false);

        isLeader1Producer = false;
        isLeader2Producer = false;
    }



    public synchronized void setLeaderProducer(int leader, boolean isProducer){
        if (leader == 1){
            isLeader1Producer = isProducer;
        }
        else if (leader == 2){
            isLeader2Producer = isProducer;
        }
    }



    public synchronized void setProduction(String production, boolean isActive){
        activeProductions.put(production, isActive);
    }


    public synchronized String getProductionsAsArgs() {
        StringBuilder args = new StringBuilder(" ");
        args.append(activeProductions.get("dev1"))
            .append(activeProductions.get("dev2"))
            .append(activeProductions.get("dev3"))
            .append(isLeader1Producer ? activeProductions.get("leader1") : false)
            .append(isLeader2Producer ? activeProductions.get("leader2") : false)
            .append(activeProductions.get("base"));
        return args.toString();
    }



    public synchronized int[] getProductionsAsArray() {
        int[] res = new int[6];
        res[0] = activeProductions.get("dev1") ? 1 : 0;
        res[1] = activeProductions.get("dev2") ? 1 : 0;
        res[2] = activeProductions.get("dev3") ? 1 : 0;
        res[3] = isLeader1Producer ? (activeProductions.get("leader1") ? 1 : 0) : 0;
        res[4] = isLeader2Producer ? (activeProductions.get("leader2") ? 1 : 0) : 0;
        res[5] = activeProductions.get("base") ? 1 : 0;

        return res;
    }



    public int getPlayerOrder(String playerName){
        return playersNamesInOrder.indexOf(playerName) +1;
    }


    public int getPlayersNum(){
        return playersNamesInOrder.size();
    }

    public String getPlayerName(int index) {
        return playersNamesInOrder.get(index);
    }


    public void setPlayers(String... playersNamesInOrder){
        this.playersNamesInOrder.addAll(Arrays.asList(playersNamesInOrder));
    }


    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }


    public boolean isAutoRefresh() {
        return autoRefresh;
    }
}
