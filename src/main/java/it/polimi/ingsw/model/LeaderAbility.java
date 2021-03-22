package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;

/**
 * Interface that manages the ability of Leader Cards
 */
public interface LeaderAbility {

    /**
     * TODO
     * List of attributes
     */

    /**
     * Method that manages the following ability: when the player buys a DevelopmentCard, he can do it using less resources
     * @return The discount to apply to the DevelopmentCard
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public SupplyContainer getDiscount(/*TODO*/) throws LeaderAbilityNotSupportedException;

    /**
     * Method that manages the following ability: when the player gets marbles from market, white marbles become one of the five resources
     * @return The resource to get in exchange for white marble
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public WarehouseObjectType getWhiteBall(/*TODO*/) throws LeaderAbilityNotSupportedException;

    /**
     * Method that gives information about the quantity of every resource in that deposit
     * @return The pair composed by the resource and its quantity
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public Pair<WarehouseObjectType, Integer> getDepositInfo(/*TODO*/) throws LeaderAbilityNotSupportedException;

    /**
     * Method that manages the following ability: when the player activates this ability, he gets a special SupplyContainer that has two spaces
     * @return An extra SupplyContainer
     * @throws SupplyException If the required resource is not available
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public SupplyContainer produce(/*TODO*/) throws SupplyException, LeaderAbilityNotSupportedException;

    /**
     * This method checks if the LeaderCard actually can use the required ability
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public void check(/*TODO*/) throws LeaderAbilityNotSupportedException;

//the following two functions deal with the last ability of the leader card

//maybe we should add two more methods: addInput and removeInput so we can choose what input is required

    /**
     * Method that set the desired resource in addition to the FAITH_MARKER that player will get from the LeaderCard
     * @param wot Is one of the five types of resources in the game
     * @throws BoundsException TODO
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public void addOutput(WarehouseObjectType wot) throws BoundsException, LeaderAbilityNotSupportedException;

    /**
     * Method that remove the desired resource that player will get from the LeaderCard
     * @param wot Is one of the five types of resources in the game
     * @throws BoundsException TODO
     * @throws LeaderAbilityNotSupportedException If the LeaderCard is not allowed to use that ability
     */
    public void removeOutput(WarehouseObjectType wot) throws BoundsException, LeaderAbilityNotSupportedException;

}
