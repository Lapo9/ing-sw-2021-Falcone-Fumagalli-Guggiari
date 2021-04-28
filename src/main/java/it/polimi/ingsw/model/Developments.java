package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.DevelopmentException;
import it.polimi.ingsw.exceptions.SupplyException;

import java.util.ArrayList;

/**
 * The class has the responsibility to manage the development spaces on the board.
 * You can add a card to a specified development space, and you can activate the production on the specified spaces.
 * The class also has functions to get information about the cards in the 3 development spaces.
 */
public class Developments implements HasStatus, WinPointsCountable, AcceptsSupplies{

    private ArrayList<DevelopmentSpace> spaces = new ArrayList<>();


    /**
     * Creates the 3 development spaces.
     */
    public Developments(){
        spaces.add(new DevelopmentSpace());
        spaces.add(new DevelopmentSpace());
        spaces.add(new DevelopmentSpace());
    }




    public SupplyContainer getInput(boolean s1, boolean s2, boolean s3) {
        SupplyContainer result = new SupplyContainer();
        boolean[] activeProductions = {s1, s2, s3};

        for(int i = 0; i<3; ++i){
            if (activeProductions[i]) {
                result.sum(spaces.get(i).getInput());
            }
        }
        return result;
    }




    /**
     * Activate the production on the specified development spaces.
     * @param s1 produce on space 1?
     * @param s2 produce on space 2?
     * @param s3 produce on space 3?
     * @return The sum of all the productions.
     */
    public SupplyContainer produce(boolean s1, boolean s2, boolean s3){
        SupplyContainer result = new SupplyContainer();
        boolean[] activeProductions = {s1, s2, s3};

        for(int i = 0; i<3; ++i){
            if (activeProductions[i]) {
                result.sum(spaces.get(i).produce());
            }
        }
        return result;
    }


    /**
     * Checks if there is the exact amount of supplies on the specified development spaces. If everything is ok, no exception is thrown.
     * @param s1 produce on space 1?
     * @param s2 produce on space 2?
     * @param s3 produce on space 3?
     * @throws SupplyException On one of the active spaces there isn't the right amount of supplies.
     */
    public void checkProduction(boolean s1, boolean s2, boolean s3) throws SupplyException {
        boolean[] activeProductions = {s1, s2, s3};
        for(int i = 0; i<3; ++i){
            if(activeProductions[i]){
                spaces.get(i).checkProduction();
            }
        }
    }


    /**
     * Adds the specified supply to the specified development space.
     * @param space where to add the supply to
     * @param wot type of supply to add
     * @throws SupplyException The card on the specified space doesn't accept this type of supply, or there is enough supply of the specified type on the card.
     */
    @Override
    public void addSupply(DepotID space, WarehouseObjectType wot, DepotID from) throws SupplyException {
        spaces.get(space.getNum()).addSupply(wot, from);
    }


    /**
     * Removes the specified type of supply from the specified development space.
     * @param space where to remove the supply from
     * @param wot type of supply to remove
     * @throws SupplyException There isn't the specified type of supply on the specified space.
     */
    @Override
    public void removeSupply(DepotID space, WarehouseObjectType wot, DepotID to) throws SupplyException {
        spaces.get(space.getNum()).removeSupply(wot, to);
    }


    @Override
    public boolean additionAllowed(DepotID space, WarehouseObjectType wot, DepotID from) {
        return spaces.get(space.getNum()).additionAllowed(wot, from);
    }


    @Override
    public boolean removalAllowed(DepotID space, WarehouseObjectType wot, DepotID to) {
        return spaces.get(space.getNum()).removalAllowed(wot, to);
    }



    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(){
        for (int i = 0; i<3; ++i){
            spaces.get(i).clearSupplies();
        }
        return null;
    }


    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(DepotID slot){
        return spaces.get(slot.getNum()).clearSupplies();
    }






    /**
     * Tries to add the specified card to the specified space.
     * @param space where to add the card to
     * @param card card to add
     * @throws DevelopmentException Card level doesn't match required level, or the space is full (max is 3 cards).
     * [pre: space is between 0 and 2]
     */
    public void addCardToSpace(int space, DevelopmentCard card) throws DevelopmentException {
        spaces.get(space).addCard(card);
    }


    /**
     * Returns a list of the levels accepted by each space.
     * @return A list of the card levels accepted by each space.
     */
    public ArrayList<Integer> buyableLevels() {
        ArrayList<Integer> levels = new ArrayList<>();

        for(int i = 0; i<3; ++i){
            levels.add(spaces.get(i).buyableLevel());
        }
        return levels;
    }


    /**
     * Returns a list of all of the cards present on all of the spaces.
     * @return A list of all of the Pairs<category of the card (yellow, green, blue, violet), level of the card> of all of the cards present on all of the spaces.
     */
    public ArrayList<Pair<CardCategory, Integer>> getCardsTypes() {
        ArrayList<Pair<CardCategory, Integer>> result = new ArrayList<>();
        for(int i = 0; i<3; ++i){
            result.addAll(spaces.get(i).getCardsTypes());
        }
        return result;
    }




    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        for (int i = 0; i<spaces.size(); ++i){
            status.addAll(spaces.get(i).getStatus());
        }

        return status;
    }


    @Override
    public int getWinPoints() {
        int totalPoints = 0;
        for(int i = 0; i<3; ++i){
            totalPoints += spaces.get(i).getWinPoints();
        }
        return totalPoints;
    }
}
