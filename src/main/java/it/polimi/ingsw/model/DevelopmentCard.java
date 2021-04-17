package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.SupplyException;

import java.util.ArrayList;

public class DevelopmentCard implements HasStatus, WinPointsCountable, AcceptsSupplies{

    private int id;
    private int level = 0;
    private int winPoints = 0;
    private CardCategory category;
    private Production production;
    private SupplyContainer cost;

    /**
     * Class constructor
     */
    public DevelopmentCard(){

    }

    /**
     * Class constructor: initialize the card
     * @param level Card level
     * @param winPoints Card win points
     * @param category Card color
     * @param production Card power production
     * @param cost Resources needed to buy the card
     */
    public DevelopmentCard(int id, int level, int winPoints, CardCategory category, Production production, SupplyContainer cost) {

        this.id = id;
        this.level = level;
        this.winPoints = winPoints;
        this.category = category;
        this.production = production;
        this.cost = cost;

    }

    /**
     * This method activates the production of the card
     * @return A SupplyContainer containing the resource in output from the card production
     */
    public SupplyContainer produce() {
        return production.produce();
    }

    /**
     * This method checks if the input resources can produce the output
     * @throws SupplyException If there isn't the right number of supplies to activate production
     */
    public void check() throws SupplyException{
        production.check();
    }

    /**
     * This method gets the cost of the card
     * @return The cost of the card
     */
    public SupplyContainer getCost() {
        return cost;
    }

    /**
     * This method gets the level of the card
     * @return The card level
     */
    public int getLevel(){
        return level;
    }

    /**
     * This method gets the color of the card
     * @return The card color from the enum CardCategory
     */
    public CardCategory getCategory() {
        return category;
    }

    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        production.addSupply(wot, from);
    }

    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException {
        production.removeSupply(wot, to);
    }

    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return production.additionAllowed(wot, from);
    }

    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        return production.removalAllowed(wot, to);
    }

    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return production.clearSupplies();
    }

    @Override
    public int getWinPoints() {
        return winPoints;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        status.add(id);
        status.addAll(production.getStatus());

        return status;
    }

}
