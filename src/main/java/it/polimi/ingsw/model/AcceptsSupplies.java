package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.exceptions.*;

/**
 * The interface describes an object that can store supplies.
 * Said object must be able to receive a supply, remove a supply and tell the user if the addition/removal of a supply is allowed without actually performing such addition/removal.
 */
public interface AcceptsSupplies {

    /**
     * Adds the supply to the storage. Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @throws SupplyException The container cannot accept the supply
     * @throws NoSuchMethodException More information needed to store the supply
     */
    public default void addSupply(WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException{
        throw new NoSuchMethodException();
    }

    /**
     * Adds the supply to the specified storage. Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param slot Storage to add the supply to
     * @param wot One of the five types of resources in the game
     * @throws SupplyException The container cannot accept the supply
     * @throws NoSuchMethodException More information needed to store the supply
     */
    public default void addSupply(DepotID slot, WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException{
        addSupply(wot);
    }

    /**
     * Adds the supply to the storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     * @throws NoSuchMethodException More information needed to store the supply
     */
    public default void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException, NoSuchMethodException, LeaderException{
        addSupply(wot);
    }

    /**
     * Adds the supply to the specified storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param slot Storage to add the supply to
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     * @throws NoSuchMethodException More information needed to store the supply
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



    /**
     * Removes the supply from the storage. Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @throws SupplyException The container cannot remove the supply
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default void removeSupply(WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException {
        throw new NoSuchMethodException();
    }

    /**
     * Removes the supply from the specified storage. Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param from Storage to remove the supply from
     * @param wot One of the five types of resources in the game
     * @throws SupplyException The container cannot remove the supply
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default void removeSupply(DepotID from, WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException {
        removeSupply(wot);
    }

    /**
     * Removes the supply from the storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException, NoSuchMethodException, LeaderException {
        removeSupply(wot);
    }

    /**
     * Removes the supply from the specified storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param from Storage to remove the supply from
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default void removeSupply(DepotID from, WarehouseObjectType wot, DepotID to) throws SupplyException, NoSuchMethodException, LeaderException {
        try {
            //initially try to call the function without the slot (do this before because the from constraint is more important than the slot constraint)
            removeSupply(wot, to);
        } catch (NoSuchMethodException nsme) {
            //if it fails try the last overload remained
            removeSupply(from, wot);
        }
    }



    /**
     * Checks if the addition of the supply to the storage is allowed.
     * @param wot One of the five types of resources in the game
     * @return Whether the container can accept the supply or not
     * @throws NoSuchMethodException More information needed to store the supply
     */
    public default boolean additionAllowed(WarehouseObjectType wot) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    /**
     * Checks if the addition of the supply to the specified storage is allowed.
     * @param slot Storage to add the supply to
     * @param wot One of the five types of resources in the game
     * @return Whether the container can accept the supply or not
     * @throws NoSuchMethodException More information needed to store the supply
     */
    public default boolean additionAllowed(DepotID slot, WarehouseObjectType wot) throws NoSuchMethodException {
        return additionAllowed(wot);
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     * @throws NoSuchMethodException More information needed to store the supply
     */
    public default boolean additionAllowed(WarehouseObjectType wot, DepotID from) throws NoSuchMethodException {
        return additionAllowed(wot);
    }

    /**
     * Checks if the addition of the supply to the specified storage, coming from the specified source, is allowed.
     * @param slot Storage to add the supply to
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     * @throws NoSuchMethodException More information needed to store the supply
     */
    public default boolean additionAllowed(DepotID slot, WarehouseObjectType wot, DepotID from) throws NoSuchMethodException {
        try {
            //initially try to call the function without the slot (do this before because the from constraint is more important than the slot constraint)
            return additionAllowed(wot, from);
        } catch (NoSuchMethodException nsme) {
            //if it fails try the last overload remained
            return additionAllowed(slot, wot);
        }
    }



    /**
     * Checks if the removal of the supply from the storage is allowed.
     * @param wot One of the five types of resources in the game
     * @return Whether the container can remove the supply or not
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default boolean removalAllowed(WarehouseObjectType wot) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    /**
     * Checks if the removal of the supply from the specified storage is allowed.
     * @param from Storage to remove the supply from
     * @param wot One of the five types of resources in the game
     * @return Whether the container can remove the supply or not
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default boolean removalAllowed(DepotID from, WarehouseObjectType wot) throws NoSuchMethodException {
        return removalAllowed(wot);
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default boolean removalAllowed(WarehouseObjectType wot, DepotID to) throws NoSuchMethodException {
        return removalAllowed(wot);
    }

    /**
     * Checks if the removal of the supply from the specified storage, direct to the specified destination, is allowed.
     * @param from Storage to remove the supply from
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     * @throws NoSuchMethodException More information needed to remove the supply
     */
    public default boolean removalAllowed(DepotID from, WarehouseObjectType wot, DepotID to) throws NoSuchMethodException {
        try {
            //initially try to call the function without the slot (do this before because the from constraint is more important than the slot constraint)
            return removalAllowed(wot, to);
        } catch (NoSuchMethodException nsme) {
            //if it fails try the last overload remained
            return removalAllowed(from, wot);
        }
    }



    /**
     * Removes all of the supplies.
     * @return A pair of SupplyContainer(s) containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() throws NoSuchMethodException;

    /**
     * Removes all of the supplies in the specified storage.
     * @param slot Storage to clear.
     * @return A pair of SupplyContainer(s) containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    public default Pair<SupplyContainer, SupplyContainer> clearSupplies(DepotID slot) throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

}
