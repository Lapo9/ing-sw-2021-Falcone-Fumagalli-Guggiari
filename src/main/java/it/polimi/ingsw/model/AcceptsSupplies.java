package it.polimi.ingsw.model;

/**
 * This interface manages every object which can get every type of supplies
 */
public interface AcceptsSupplies {

    /**
     * Method that adds the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     */
    public void addSupply(WarehouseObjectType wot) throws SupplyException;

    /**
     * Method that that removes the resource to the object
     * @param wot Is one of the five types of resources in the game
     * @throws SupplyException If the required resource is not available
     */
    public void removeSupply(WarehouseObjectType wot) throws SupplyException;

    /**
     * Method that removes all the resources from that object
     * @return The removed resources in a temporary SupplyContainer
     */
    public SupplyContainer clearSupplies();

}
