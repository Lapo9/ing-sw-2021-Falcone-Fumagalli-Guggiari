package it.polimi.ingsw.model.development;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.DevelopmentException;
import it.polimi.ingsw.model.exceptions.SupplyException;

import java.util.ArrayList;

/**
 * Class that has the responsibility to manage the cards in a specific development space.
 * You can add a card to the space, activate production of the last card added and get info about the cards present in this development space.
 */
public class DevelopmentSpace implements AcceptsSupplies, HasStatus, WinPointsCountable {

    private ArrayList<DevelopmentCard> cards = new ArrayList<DevelopmentCard>();


    /**
     * Creates a development space without any card.
     */
    public DevelopmentSpace(){}


    /**
     * Tries to add a card, if possible.
     * @param card development card to add
     * @throws DevelopmentException If the card to add hasn't the expected level or if the space is full (max 3 cards)
     */
    public void addCard(DevelopmentCard card) throws DevelopmentException {
        //check if we already have 3 cards or if the card level is not the expected one
        if(cards.size() == 3 || card.getLevel() != cards.size()+1){
            throw new DevelopmentException("Cannot buy a card of the specified level");
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


    /**
     * Returns the input of the last development card contained in the development space.
     * @return the input of the last development card contained in the development space
     */
    public SupplyContainer getInput() {
        return cards.get(cards.size()-1).produce();
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
     * Adds the supply to the storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        cards.get(cards.size()-1).addSupply(wot, from);
    }

    /**
     * Removes the supply from the storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException {
        cards.get(cards.size()-1).removeSupply(wot, to);
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        if(cards.size() == 0)
            return false;
        else
            return cards.get(cards.size()-1).additionAllowed(wot, from);
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        if(cards.size() == 0)
            return false;
        else
            return cards.get(cards.size()-1).removalAllowed(wot, to);
    }

    /**
     * Removes all of the supplies.
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        if(cards.size() == 0)
            return new Pair<SupplyContainer, SupplyContainer>(new SupplyContainer(), new SupplyContainer());
        else
            return cards.get(cards.size()-1).clearSupplies();
    }

    /**
     * Gets the win points of the object
     * @return the win points of the object
     */
    @Override
    public int getWinPoints() {
        int result = 0;
        for (int i=0; i<cards.size(); ++i){
            result += cards.get(i).getWinPoints();
        }
        return result;
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of 18 Integer
     */
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        //if there are no cards adds all 0
        if(cards.size() == 0){
            for(int i = 0; i<18; ++i){
                status.add(0);
            }
        }

        else {
            //for all the cards but the active one, we only need to save the ID (not the production info)
            for (int i = 0; i < cards.size() - 1; ++i) {
                status.add(cards.get(i).getStatus().get(0));
            }

            //for the active card save everything
            status.addAll(cards.get(cards.size() - 1).getStatus());

            //ads IDs for empty spaces
            for (int i = 0; i < 3 - cards.size(); ++i) {
                status.add(cards.size(), 0);
            }
        }

        return status;
    }
}
