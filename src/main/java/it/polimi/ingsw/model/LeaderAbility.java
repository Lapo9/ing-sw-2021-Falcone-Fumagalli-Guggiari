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
     * @throws UnsupportedOperationException If the LeaderCard is not allowed to use that ability
     */
    public default SupplyContainer getDiscount() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    };

    /**
     * Third ability: when the player gets white marbles from market, player can choose one of the resources to get
     * @return The resource to get in exchange for white marble
     * @throws UnsupportedOperationException If the LeaderCard is not allowed to use that ability
     */
    public default WarehouseObjectType getWhiteBall() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    };

    /**
     * Method that gives information about the quantity of every resource in that depot
     * @return The pair composed by the resource and its quantity
     * @throws UnsupportedOperationException If the LeaderCard is not allowed to use that ability
     */
    public default Pair<WarehouseObjectType, Integer> getDepotInfo() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    };

    //fourth ability

    /**
     * This method activates the leader card production
     * @return An extra SupplyContainer that contains the resource
     * @throws SupplyException If the required resource is not available
     * @throws UnsupportedOperationException If the LeaderCard is not allowed to use that ability
     */
    public default SupplyContainer produce(boolean prod) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    };

    /**
     * This method checks if the LeaderCard actually can activate the production
     * @throws UnsupportedOperationException If the LeaderCard is not allowed to use that ability
     */
    public default void checkProduction(boolean prod) throws SupplyException, UnsupportedOperationException{
        throw new UnsupportedOperationException();
    };

    /**
     * Method that set the desired resource in addition to the FAITH_MARKER that player will get from the LeaderCard
     * @param wot Is one of the five types of resources in the game
     * @throws BoundsException If you want to add more than one resource
     * @throws UnsupportedOperationException If the LeaderCard is not allowed to use that ability
     */
    public default void addOutput(WarehouseObjectType wot) throws BoundsException, UnsupportedOperationException{
        throw new UnsupportedOperationException();
    };

    /**
     * Method that remove the desired resource that player will get from the LeaderCard
     * @param wot Is one of the five types of resources in the game
     * @throws BoundsException If you want to remove a resource where there is no one
     * @throws UnsupportedOperationException If the LeaderCard is not allowed to use that ability
     */
    public default void removeOutput(WarehouseObjectType wot) throws BoundsException, UnsupportedOperationException{
        throw new UnsupportedOperationException();
    };


}
