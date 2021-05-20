package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.viewables.ViewableFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class OfflineInfo {


    public enum LeaderStatus{
        INACTIVE, DISCARDED, PRODUCER, DEPOT, OTHER;
    }


    private String yourName;
    private HashMap<String, Boolean> activeProductions = new HashMap<>();
    private LeaderStatus leader1Status = LeaderStatus.INACTIVE;
    private LeaderStatus leader2Status = LeaderStatus.INACTIVE;
    private ArrayList<String> playersNamesInOrder = new ArrayList<>();
    private String selectedItem = "";
    private String selectedWarehouseRow = "";
    private ViewableFactory factory;


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


    public synchronized void attachFactory(ViewableFactory factory){
        this.factory = factory;
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
        if(factory != null){
            factory.update(1, ViewableId.ACTIVE_PRODUCTIONS, getProductionsAsArray());
        }
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



    private synchronized int[] getProductionsAsArray() {
        int[] res = new int[6];
        res[0] = activeProductions.get("dev1") ? 1 : 0;
        res[1] = activeProductions.get("dev2") ? 1 : 0;
        res[2] = activeProductions.get("dev3") ? 1 : 0;
        res[3] = leader1Status==LeaderStatus.PRODUCER ? (activeProductions.get("leader1") ? 1 : 0) : 2;
        res[4] = leader2Status==LeaderStatus.PRODUCER ? (activeProductions.get("leader2") ? 1 : 0) : 2;
        res[5] = activeProductions.get("base") ? 1 : 0;

        return res;
    }



    public synchronized int getOpponentOrder(String playerName){
        return playersNamesInOrder.stream().filter(player -> !player.equals(yourName)).collect(Collectors.toList()).indexOf(playerName) + 1;
    }



    public synchronized int getPlayerOrder(String playerName){
        return playersNamesInOrder.indexOf(playerName) +1;
    }


    public synchronized int getPlayersNum(){
        return playersNamesInOrder.size();
    }

    public synchronized String getPlayerName(int index) {
        return playersNamesInOrder.get(index);
    }


    public synchronized void setPlayers(String... playersNamesInOrder){
        this.playersNamesInOrder.addAll(Arrays.asList(playersNamesInOrder));
    }



    public synchronized String getSelectedItem(){
        return selectedItem;
    }


    public synchronized void setSelectedItem(String item){
        selectedItem = item;
    }


    public synchronized String getSelectedWarehouseRow() {
        return selectedWarehouseRow;
    }


    public synchronized void setSelectedWarehouseRow(String selectedWarehouseRow) {
        this.selectedWarehouseRow = selectedWarehouseRow;
    }


    public synchronized void setYourName(String name){
        yourName = name;
    }


    public synchronized String getYourName(){
        return yourName;
    }
}
