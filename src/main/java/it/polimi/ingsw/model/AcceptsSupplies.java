package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

/**
 * This interface manages every object which can get every type of supplies
 */
public interface AcceptsSupplies {

    /**
     * Method that adds the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted)
     * @throws UnsupportedOperationException This object needs more information to store the supply
     */
    public default void addSupply(WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException, LeaderException{
        throw new UnsupportedOperationException();
    }


    /**
     * Adds the supply to the specified slot of the object
     * @param slot space of the object to remove the supply from
     * @param wot type of supply
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted)
     * @throws UnsupportedOperationException This object needs more information to store the supply
     */
    public default void addSupply(DepotID slot, WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException, LeaderException{
        addSupply(wot);
    }


    /**
     * Adds the supply to the specified slot of the object, and gives information about the source of the object
     * @param wot type of supply
     * @param from source of the supply
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted, source not accepted)
     * @throws UnsupportedOperationException This object needs more information to store the supply
     */
    public default void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException, UnsupportedOperationException, LeaderException{
        addSupply(wot);
    }


    /**
     * Adds the supply to the specified slot of the object, and gives information about the source of the object
     * @param slot space of the object to remove the supply from
     * @param wot type of supply
     * @param from source of the supply
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted, source not accepted)
     * @throws UnsupportedOperationException This object needs more information to store the supply
     */
    public default void addSupply(DepotID slot, WarehouseObjectType wot, DepotID from) throws SupplyException, UnsupportedOperationException, LeaderException{
        try {
            //initially try to call the function without the slot (do this before because the from constraint is more important than the slot constraint)
            addSupply(wot, from);
        } catch (UnsupportedOperationException uoe) {
            //if it fails try the last overload remained
            addSupply(slot, wot);
        }
    }


    /**
     * Method that that removes the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     * @throws UnsupportedOperationException This object needs more information to store the supply
     */
    public default void removeSupply(WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException, LeaderException {
        throw new UnsupportedOperationException();
    }


    /**
     * Removes the resource from the specified slot of the object
     * @param slot space of the object to remove the supply from
     * @param wot type of supply to remove
     * @throws SupplyException If the required resource is not available
     * @throws UnsupportedOperationException This object needs more information to store the supply
     */
    public default void removeSupply(DepotID slot, WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException, LeaderException {
        removeSupply(wot);
    }



    /**
     * Method that removes all the resources from that object
     * @return The removed resources in a temporary SupplyContainer
     */
    public SupplyContainer clearSupplies() throws UnsupportedOperationException;

}
