package it.polimi.ingsw.model.leaders.leader_abilities;


import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.exceptions.MarbleException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.leaders.LeadersSpace;

import java.util.ArrayList;


/**
 * This implementation of LeaderAbility is a depot, so it implements all of the methods to manage a depot (add and remove supplies).
 */
public class Depot implements LeaderAbility {

    private SupplyContainer depot;


    /**
     * Builds a new depot leader with the specified type and always 2 as maximum storage.
     * @param type Type contained by the depot
     */
    public Depot(WarehouseObjectType type){
        depot = new SupplyContainer(onlyFrom(DepotID.SourceType.STRONGBOX).negate().and(maxSpecificType(type, 2)));
    }

    /**
     * Method that gives information about the quantity of every resource in that depot
     * @return The pair composed by the resource and its quantity
     */
    @Override
    public Pair<WarehouseObjectType, Integer> getDepotInfo() {
        Pair<WarehouseObjectType, Integer> res = null;
        try {
            res = new Pair<>(depot.getType(), depot.getQuantity());
        } catch (SupplyException se){System.exit(1); /*TODO terminate program*/}
        return res;
    }

    /**
     * Tries to add a marble to a depot container
     * @param color color to add
     * @param ls used to check if a white ball conversion is possible
     * @throws MarbleException cannot add the specified marble color to the depot, or conversion is not possible
     * @throws SupplyException depot is full
     */
    @Override
    public void addMarble(MarbleColor color, LeadersSpace ls) throws MarbleException, SupplyException {
        depot.addMarble(color, ls);
    }

    /**
     * Adds the supply to the storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        depot.addSupply(wot, from) &&
    }

    /**
     * Removes the supply from the storage. Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException {
        depot.removeSupply(wot);
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return depot.additionAllowed(wot, from);
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        return depot.removalAllowed(wot, to);
    }

    /**
     * Removes all of the supplies.
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return depot.clearSupplies();
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of 5 Integer
     */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        //fixed input, fixed output, mutable output, production depot (COIN, SERVANT, SHIELD, STONE, FAITH_MARKER)
        for(int i=0; i<8; ++i){
            status.add(0);
        }

        status.addAll(depot.getStatus());

        return status;
    }
}
