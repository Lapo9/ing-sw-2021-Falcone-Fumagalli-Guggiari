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

    private ArrayList<DevelopmentSpace> spaces;


    /**
     * Creates the 3 development spaces.
     */
    public Developments(){
        spaces.add(new DevelopmentSpace());
        spaces.add(new DevelopmentSpace());
        spaces.add(new DevelopmentSpace());
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
        //check if the resource comes from an acceptable source
        if(from.getType() == DepotID.DepotType.COFFER || from == DepotID.PAYCHECK_COFFER){
            throw new SupplyException();
        }

        spaces.get(space.getNum()).addSupply(wot);
    }


    /**
     * Removes the specified type of supply from the specified development space.
     * @param space where to remove the supply from
     * @param wot type of supply to remove
     * @throws SupplyException There isn't the specified type of supply on the specified space.
     */
    @Override
    public void removeSupply(DepotID space, WarehouseObjectType wot) throws SupplyException {
        spaces.get(space.getNum()).removeSupply(wot);
    }


    @Override
    public ArrayList<DepotID> availableDepots(DepotID from, WarehouseObjectType wot) {
        ArrayList<DepotID> res = new ArrayList<>();
        for (DevelopmentSpace space : spaces){
            res.addAll(space.availableDepots(from, wot));
        }
        return res;
    }

    /**
     * Clear all of the supplies in the spaces.
     * @return Supplies removed
     */
    @Override
    public SupplyContainer clearSupplies(){
        SupplyContainer result = new SupplyContainer();

        for (int i = 0; i<3; ++i){
            result.sum(spaces.get(i).clearSupplies());
        }
        return result;
    }


    /**
     * Tries to add the specified card to the specified space.
     * @param space where to add the card to
     * @param card card to add
     * @throws DevelopmentException Card level doesn't match required level, or the space is full (max is 3 cards).
     */
    public void addCardToSpace(int space, SupplyCard card) throws DevelopmentException {
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
        //TODO
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
