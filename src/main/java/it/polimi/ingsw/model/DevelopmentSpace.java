package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.DevelopmentException;
import it.polimi.ingsw.exceptions.SupplyException;

import java.util.ArrayList;

/**
 * Class that has the responsibility to manage the cards in a specific development space.
 * You can add a card to the space, activate production of the last card added and get info about the cards present in this development space.
 */
public class DevelopmentSpace implements AcceptsSupplies, HasStatus, WinPointsCountable{

    private ArrayList<SupplyCard> cards = new ArrayList<SupplyCard>();


    /**
     * Creates a development space without any card.
     */
    public DevelopmentSpace(){}


    /**
     * Tries to add a card, if possible.
     * @param card development card to add
     * @throws DevelopmentException If the card to add hasn't the expected level or if the space is full (max 3 cards)
     */
    public void addCard(SupplyCard card) throws DevelopmentException {
        //check if we already have 3 cards or if the card level is not the expected one
        if(cards.size() == 3 || card.getLevel() != cards.size()+1){
            throw new DevelopmentException();
        }

        cards.add(card);
    }


    /**
     * Returns the next card expected level.
     * @return next card expected level
     */
    public int buyableLevel(){
        return cards.size()+1;
    }


    /**
     * Activates production of the last added card.
     * @return A SupplyContainer containing the production output.
     */
    public SupplyContainer produce() {
        return cards.get(cards.size()-1).produce();
    }


    /**
     * Checks if there is the exact amount of supplies to activate production.
     * @throws SupplyException There isn't the right number of supplies to activate production.
     */
    public void checkProduction() throws SupplyException {
        cards.get(cards.size()-1).check();
    }


    /**
     * Returns information about the cards present in this space.
     * @return A list of Pairs<category of the card, level of the card> of all of the cards present in this space.
     */
    public ArrayList<Pair<CardCategory, Integer>> getCardsTypes() {
        ArrayList<Pair<CardCategory, Integer>> result = new ArrayList<>();

        for(int i=0; i<cards.size(); ++i){
            result.add(new Pair<CardCategory, Integer>(cards.get(i).getCategory(), cards.get(i).getLevel()));
        }
        return result;
    }


    @Override
    public void addSupply(WarehouseObjectType wot) throws SupplyException {
        cards.get(cards.size()-1).addSupply(wot);
    }


    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException {
        cards.get(cards.size()-1).removeSupply(wot);
    }


    @Override
    public SupplyContainer clearSupplies() {
        return cards.get(cards.size()-1).clearSupplies();
    }


    @Override
    public int getWinPoints() {
        int result = 0;
        for (int i=0; i<cards.size(); ++i){
            result += cards.get(i).getWinPoints();
        }
        return result;
    }
}
