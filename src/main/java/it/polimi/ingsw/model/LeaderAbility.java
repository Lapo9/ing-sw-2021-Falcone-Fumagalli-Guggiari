package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;

/**
 * Interface that manages the ability of Leader Cards
 */
public interface LeaderAbility extends AcceptsSupplies{

    /**
     * Method that manages the following ability: when the player buys a DevelopmentCard, he can do it using less resources
     * @return The discount to apply to the DevelopmentCard
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public SupplyContainer getDiscount() throws LeaderAbilityNotSupportedException;

    /**
     * Method that manages the following ability: when the player gets marbles from market, player can choose one of the resources to get
     * @return The resource to get in exchange for white marble
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public WarehouseObjectType getWhiteBall() throws LeaderAbilityNotSupportedException;

    /**
     * Method that gives information about the quantity of every resource in that deposit
     * @return The pair composed by the resource and its quantity
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public Pair<WarehouseObjectType, Integer> getDepositInfo() throws LeaderAbilityNotSupportedException;


    //fourth ability

    /**
     * This method activates the leader card production
     * @return An extra SupplyContainer that contains the resource
     * @throws SupplyException If the required resource is not available
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public SupplyContainer produce() throws SupplyException, LeaderAbilityNotSupportedException;

    /**
     * This method checks if the LeaderCard actually can activate the production
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public void checkProduction() throws LeaderAbilityNotSupportedException;

    /**
     * Method that set the desired resource in addition to the FAITH_MARKER that player will get from the LeaderCard
     * @param wot Is one of the five types of resources in the game
     * @throws BoundsException If you want to add more than one resource
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public void addOutput(WarehouseObjectType wot) throws BoundsException, LeaderAbilityNotSupportedException;

    /**
     * Method that remove the desired resource that player will get from the LeaderCard
     * @param wot Is one of the five types of resources in the game
     * @throws BoundsException If you want to remove a resource where there is no one
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public void removeOutput(WarehouseObjectType wot) throws BoundsException, LeaderAbilityNotSupportedException;


}
