package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;

/**
 * This interface manages every object which can get every type of supplies
 */
public interface AcceptsSupplies {

    /**
     * Method that adds the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted)
     * @throws NoSuchMethodException This object needs more information to store the supply
     */
    public default void addSupply(WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException{
        throw new NoSuchMethodException();
    }

    /**
     * Adds the supply to the specified slot of the object
     * @param slot space of the object to remove the supply from
     * @param wot type of supply
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted)
     * @throws NoSuchMethodException This object needs more information to store the supply
     */
    public default void addSupply(DepotID slot, WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException{
        addSupply(wot);
    }

    /**
     * Adds the supply to the specified slot of the object, and gives information about the source of the object
     * @param wot type of supply
     * @param from source of the supply
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted, source not accepted)
     * @throws NoSuchMethodException This object needs more information to store the supply
     */
    public default void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException, NoSuchMethodException, LeaderException{
        addSupply(wot);
    }

    /**
     * Adds the supply to the specified slot of the object, and gives information about the source of the object
     * @param slot space of the object to remove the supply from
     * @param wot type of supply
     * @param from source of the supply
     * @throws SupplyException If the container cannot accept the supply (already full, type not accepted, source not accepted)
     * @throws NoSuchMethodException This object needs more information to store the supply
     */
    public default void addSupply(DepotID slot, WarehouseObjectType wot, DepotID from) throws SupplyException, NoSuchMethodException, LeaderException{
        try {
            //initially try to call the function without the slot (do this before because the from constraint is more important than the slot constraint)
            addSupply(wot, from);
        } catch (NoSuchMethodException uoe) {
            //if it fails try the last overload remained
            addSupply(slot, wot);
        }
    }



    public default boolean checkAccept(WarehouseObjectType wot) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }


    public default boolean checkAccept(DepotID slot, WarehouseObjectType wot) throws NoSuchMethodException {
        return checkAccept(wot);
    }


    public default boolean checkAccept(WarehouseObjectType wot, DepotID from) throws NoSuchMethodException {
        return checkAccept(wot);
    }


    public default boolean checkAccept(DepotID slot, WarehouseObjectType wot, DepotID from) throws NoSuchMethodException {
        try {
            //initially try to call the function without the slot (do this before because the from constraint is more important than the slot constraint)
            return checkAccept(wot, from);
        } catch (NoSuchMethodException uoe) {
            //if it fails try the last overload remained
            return checkAccept(slot, wot);
        }
    }



    /**
     * Method that that removes the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     * @throws NoSuchMethodException This object needs more information to store the supply
     */
    public default void removeSupply(WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException {
        throw new NoSuchMethodException();
    }


    public default void removeSupply(DepotID to, WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException {
        removeSupply(wot);
    }


    public default void removeSupply(DepotID from, WarehouseObjectType wot, DepotID to) throws SupplyException, NoSuchMethodException, LeaderException {
        removeSupply(from, wot);
    }



    public default boolean checkRemove (WarehouseObjectType wot) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }


    public default boolean checkRemove(DepotID to, WarehouseObjectType wot) throws NoSuchMethodException {
        return checkRemove(wot);
    }


    public default boolean checkRemove(DepotID from, WarehouseObjectType wot, DepotID to) throws NoSuchMethodException {
        return checkRemove(from, wot);
    }


    /**
     * Method that removes all the resources from that object
     * @return The removed resources in a temporary SupplyContainer
     */
    @Deprecated
    public SupplyContainer clearSupplies() throws NoSuchMethodException;

}
