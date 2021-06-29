package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.view.cli.viewables.ViewableFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Class managing the info of the match
 */
public class OfflineInfo {

    /**
     * Enum describing the status of the LeaderCard(s)
     */
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
    private boolean matchStarted = false;

    /**
     * Class constructor
     */
    public OfflineInfo(){
        reset();
    }

    /**
     * Resets the match
     */
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

    /**
     * Sets the ViewableFactory used
     * @param factory to use
     */
    public synchronized void attachFactory(ViewableFactory factory){
        this.factory = factory;
    }


    /**
     * Sets the status of the two leader
     * @param leader first leader status
     * @param status second leader status
     */
    public synchronized void setLeaderStatus(int leader, LeaderStatus status){
        if (leader == 1){
            leader1Status = status;
        }
        else if (leader == 2){
            leader2Status = status;
        }
    }


    /**
     * Gets the status of the selected leader
     * @param leader index of the leader
     * @return the status of the leader
     */
    public synchronized LeaderStatus getLeaderStatus(int leader){
        if (leader == 1){
            return leader1Status;
        }
        else if (leader == 2){
            return leader2Status;
        }
        return null;
    }

    /**
     * Sets the status of the match
     * @param matchStarted status
     */
    public synchronized void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    /**
     * Is the match started?
     * @return 1 = started, 0 = not started
     */
    public synchronized boolean isMatchStarted() {
        return matchStarted;
    }

    /**
     * Sets the production active or not active
     * @param production the production chosen
     * @param isActive active/not active
     */
    public synchronized void setProduction(String production, boolean isActive){
        activeProductions.put(production, isActive);
        if(factory != null){
            factory.update(0, ViewableId.ACTIVE_PRODUCTIONS, getProductionsAsArray());
        }
    }

    /**
     * Gets the status of the productions as a String
     * @return String containing the status of every production
     */
    public synchronized String getProductionsAsArgs() {
        return " " + activeProductions.get("dev1") + " " +
                activeProductions.get("dev2") + " " +
                activeProductions.get("dev3") + " " +
                (leader1Status == LeaderStatus.PRODUCER ? activeProductions.get("leader1") : false) + " " +
                (leader2Status == LeaderStatus.PRODUCER ? activeProductions.get("leader2") : false) + " " +
                activeProductions.get("base");
    }


    /**
     * Gets the status of the productions as an array of int
     * @return array containing the list of status of every production
     */
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


    /**
     * Gets the order of the chosen opponent
     * @param playerName name of the player
     * @return order of the player
     */
    public synchronized int getOpponentOrder(String playerName){
        return playersNamesInOrder.stream().filter(player -> !player.equals(yourName)).collect(Collectors.toList()).indexOf(playerName) + 1;
    }


    /**
     * Gets the order of the player
     * @param playerName name of the player
     * @return order of the player
     */
    public synchronized int getPlayerOrder(String playerName){
        return playersNamesInOrder.indexOf(playerName) +1;
    }

    /**
     * Gets the number of the participants
     * @return number of the players
     */
    public synchronized int getPlayersNum(){
        return playersNamesInOrder.size();
    }

    /**
     * Gets the player's name
     * @param index of the player chosen
     * @return the player's name
     */
    public synchronized String getPlayerName(int index) {
        return playersNamesInOrder.get(index);
    }

    /**
     * Sets the players putting them in an ArrayList
     * @param playersNamesInOrder name of every player
     */
    public synchronized void setPlayers(String... playersNamesInOrder){
        this.playersNamesInOrder.addAll(Arrays.asList(playersNamesInOrder));
    }

    /**
     * Gets the selected item
     * @return the selected item
     */
    public synchronized String getSelectedItem(){
        return selectedItem;
    }

    /**
     * Sets the selected item
     * @param item the item to set
     */
    public synchronized void setSelectedItem(String item){
        selectedItem = item;
    }

    /**
     * Gets the selected row of the Warehouse
     * @return that row
     */
    public synchronized String getSelectedWarehouseRow() {
        return selectedWarehouseRow;
    }

    /**
     * Sets the selected row of the Warehouse
     * @param selectedWarehouseRow row to set
     */
    public synchronized void setSelectedWarehouseRow(String selectedWarehouseRow) {
        this.selectedWarehouseRow = selectedWarehouseRow;
    }

    /**
     * Sets the name of the player
     * @param name of the player
     */
    public synchronized void setYourName(String name){
        yourName = name;
    }

    /**
     * Gets the name of the player
     * @return his name
     */
    public synchronized String getYourName(){
        return yourName;
    }
}
