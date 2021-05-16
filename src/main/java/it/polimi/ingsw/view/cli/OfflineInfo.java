package it.polimi.ingsw.view.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class OfflineInfo {


    public enum LeaderStatus{
        INACTIVE, DISCARDED, PRODUCER, DEPOT, OTHER;
    }


    private HashMap<String, Boolean> activeProductions = new HashMap<>();
    private LeaderStatus leader1Status = LeaderStatus.INACTIVE;
    private LeaderStatus leader2Status = LeaderStatus.INACTIVE;
    private ArrayList<String> playersNamesInOrder = new ArrayList<>();
    private boolean autoRefresh = true;
    private String selectedItem = "";
    private String selectedWarehouseRow = "";


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

        leader1Status = LeaderStatus.INACTIVE;
        leader2Status = LeaderStatus.INACTIVE;

        selectedItem = "";
    }



    public synchronized void setLeaderStatus(int leader, LeaderStatus status){
        if (leader == 1){
            leader1Status = status;
        }
        else if (leader == 2){
            leader2Status = status;
        }
    }



    public synchronized LeaderStatus getLeaderStatus(int leader){
        if (leader == 1){
            return leader1Status;
        }
        else if (leader == 2){
            return leader2Status;
        }
        return null;
    }



    public synchronized void setProduction(String production, boolean isActive){
        activeProductions.put(production, isActive);
    }


    public synchronized String getProductionsAsArgs() {
        StringBuilder args = new StringBuilder(" ");
        args.append(activeProductions.get("dev1"))
            .append(activeProductions.get("dev2"))
            .append(activeProductions.get("dev3"))
            .append(leader1Status==LeaderStatus.PRODUCER ? activeProductions.get("leader1") : false)
            .append(leader2Status==LeaderStatus.PRODUCER ? activeProductions.get("leader2") : false)
            .append(activeProductions.get("base"));
        return args.toString();
    }



    public synchronized int[] getProductionsAsArray() {
        int[] res = new int[6];
        res[0] = activeProductions.get("dev1") ? 1 : 0;
        res[1] = activeProductions.get("dev2") ? 1 : 0;
        res[2] = activeProductions.get("dev3") ? 1 : 0;
        res[3] = leader1Status==LeaderStatus.PRODUCER ? (activeProductions.get("leader1") ? 1 : 0) : 2;
        res[4] = leader2Status==LeaderStatus.PRODUCER ? (activeProductions.get("leader2") ? 1 : 0) : 2;
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


    public String getSelectedItem(){
        return selectedItem;
    }


    public void setSelectedItem(String item){
        selectedItem = item;
    }


    public String getSelectedWarehouseRow() {
        return selectedWarehouseRow;
    }


    public void setSelectedWarehouseRow(String selectedWarehouseRow) {
        this.selectedWarehouseRow = selectedWarehouseRow;
    }
}
