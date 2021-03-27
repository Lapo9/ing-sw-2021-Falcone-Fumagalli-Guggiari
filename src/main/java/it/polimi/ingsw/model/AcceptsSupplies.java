package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

/**
 * This interface manages every object which can get every type of supplies
 */
public interface AcceptsSupplies {

    /**
     * Method that adds the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     */
    public default void addSupply(WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }


    /**
     * Adds the supply to the specified slot of the object
     * @param slot space of the object to remove the supply from
     * @param wot
     * @throws SupplyException
     */
    public default void addSupply(DepotID slot, WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    /**
     * Method that that removes the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     */
    public default void removeSupply(WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }


    /**
     * Removes the resource from the specified slot of the object
     * @param slot space of the object to remove the supply from
     * @param wot type of supply to remove
     * @throws SupplyException If the required resource is not available
     */
    public default void removeSupply(DepotID slot, WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    /**
     * Method that removes all the resources from that object
     * @return The removed resources in a temporary SupplyContainer
     */
    public SupplyContainer clearSupplies() throws UnsupportedOperationException;

}
