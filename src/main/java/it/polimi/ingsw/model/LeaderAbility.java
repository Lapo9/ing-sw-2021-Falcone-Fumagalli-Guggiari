package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;

/**
 * Interface that manages the ability of Leader Cards
 */
public interface LeaderAbility extends AcceptsSupplies{

    /**
     * First ability: when the player buys a SupplyCard, he can do it using less resources
     * @return The discount to apply to the SupplyCard
     * @throws NoSuchMethodException If the LeaderCard is not allowed to use that ability
     */
    public default SupplyContainer getDiscount() throws NoSuchMethodException{
        throw new NoSuchMethodException();
    };


    /**
     * Third ability: when the player gets white marbles from market, player can choose one of the resources to get
     * @return The resource to get in exchange for white marble
     * @throws NoSuchMethodException If the LeaderCard is not allowed to use that ability
     */
    public default WarehouseObjectType transformWhiteMarble() throws NoSuchMethodException{
        throw new NoSuchMethodException();
    };


    /**
     * Method that gives information about the quantity of every resource in that depot
     * @return The pair composed by the resource and its quantity
     * @throws NoSuchMethodException If the LeaderCard is not allowed to use that ability
     */
    public default Pair<WarehouseObjectType, Integer> getDepotInfo() throws NoSuchMethodException{
        throw new NoSuchMethodException();
    };


    /**
     * Tries to add a marble to a depot container
     * @param color color to add
     * @param ls used to check if a white ball conversion is possible
     * @throws MarbleException cannot add the specified marble color to the depot, or conversion is not possible
     * @throws SupplyException depot is full
     * @throws NoSuchMethodException If the LeaderCard is not allowed to use that ability
     */
    public default void addMarble(MarbleColor color, LeadersSpace ls) throws MarbleException, SupplyException, NoSuchMethodException {
        throw new NoSuchMethodException();
    }


    /**
     * This method activates the leader card production
     * @return An extra SupplyContainer that contains the resource
     * @throws NoSuchMethodException If the LeaderCard is not allowed to use that ability
     */
    public default SupplyContainer produce() throws NoSuchMethodException{
        throw new NoSuchMethodException();
    };


    /**
     * This method checks if the LeaderCard actually can activate the production
     * @throws NoSuchMethodException If the LeaderCard is not allowed to use that ability
     */
    public default void checkProduction() throws SupplyException, NoSuchMethodException{
        throw new NoSuchMethodException();
    };


    public default void swapProduction(WarehouseObjectType wot) throws SupplyException, NoSuchMethodException{
        throw new NoSuchMethodException();
    }


    /**
     * Method that set the desired resource in addition to the FAITH_MARKER that player will get from the LeaderCard
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If you want to add more than one resource
     * @throws NoSuchMethodException If the LeaderCard is not allowed to use that ability
     */
    public default void changeOutput(WarehouseObjectType wot) throws SupplyException, NoSuchMethodException{
        throw new NoSuchMethodException();
    };


    @Override
    public default Pair<SupplyContainer, SupplyContainer> clearSupplies() throws NoSuchMethodException{
        throw new NoSuchMethodException();
    }


}
