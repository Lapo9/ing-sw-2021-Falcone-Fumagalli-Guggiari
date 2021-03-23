package it.polimi.ingsw.model;

/**
 * This interface manages every object which can get every type of supplies
 */
public interface AcceptsSupplies {

    /**
     * Method that allows every type of object who can storage a supply to get it
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     */
    public void addSupply(WarehouseObjectType wot) throws SupplyException;

    /**
     * Method that allows every type of object who can storage a supply to remove it
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     */
    public void removeSupply(WarehouseObjectType wot) throws SupplyException;

    /**
     * Method that allows to empty every type of object which can storage a supply
     * @return An empty SupplyContainer
     */
    public SupplyContainer clearSupplies(/*TODO*/);

}
