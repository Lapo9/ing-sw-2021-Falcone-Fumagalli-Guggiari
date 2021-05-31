package it.polimi.ingsw.model.development;

import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.SupplyException;

import java.util.ArrayList;


/**
 * A Paycheck can store resources based from their source (strongbox or depot).
 */
public class Paycheck implements AcceptsSupplies, HasStatus {

    private SupplyContainer fromStrongbox = new SupplyContainer(onlyFrom(DepotID.SourceType.STRONGBOX), onlyFrom(DepotID.SourceType.STRONGBOX));
    private SupplyContainer fromDepot = new SupplyContainer(onlyFrom(DepotID.SourceType.DEPOT), onlyFrom(DepotID.SourceType.DEPOT));


    /**
     * Creates a paycheck.
     */
    public Paycheck(){}


    /**
     * Returns the total of the supplies in the paycheck.
     * @return Total supplies in the paycheck
     */
    public SupplyContainer getAll(){
        SupplyContainer tot = new SupplyContainer();
        tot.sum(fromStrongbox).sum(fromDepot);
        return tot;
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
        try {
            fromStrongbox.addSupply(wot, from);
        } catch (SupplyException se){
            fromDepot.addSupply(wot, from);
        }
    }

    /**
     * Removes the supply from the storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException {
        try {
            fromStrongbox.removeSupply(wot, to);
        } catch (SupplyException se){
            fromDepot.removeSupply(wot, to);
        }
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        boolean res = false;
        res |= fromStrongbox.additionAllowed(wot, from);
        res |= fromDepot.additionAllowed(wot, from);
        return res;
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        boolean res = false;
        res |= fromStrongbox.removalAllowed(wot, to);
        res |= fromDepot.removalAllowed(wot, to);
        return res;
    }

    /**
     * Removes all of the supplies in the Paycheck
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return new Pair<>(fromDepot.clearSupplies().first, fromStrongbox.clearSupplies().first);
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of Integer describing what resources are contained in the Paycheck
     */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        status.addAll(fromStrongbox.getStatus());
        status.addAll(fromDepot.getStatus());

        return status;
    }

    /**
     * Returns an integer that indicates the source of the given WarehouseObjectType.
     * @param wot a WarehouseObjectType
     * @return 0 if the given WarehouseObjectType isn't contained in any production depot,
     *         1 if there are at least two different given WarehouseObjectType, one from a depot and the other from the coffer,
     *         2 if the given WarehouseObjectType is from a depot,
     *         3 if the given WarehouseObjectType is from the coffer
     */
    public int getSource(WarehouseObjectType wot) {
        if(fromDepot.getQuantity(wot) != 0 && fromStrongbox.getQuantity(wot) != 0)
            return 1;
        else if(fromDepot.getQuantity(wot) != 0 && fromStrongbox.getQuantity(wot) == 0)
            return 2;
        else if(fromDepot.getQuantity(wot) == 0 && fromStrongbox.getQuantity(wot) != 0)
            return 3;
        else
            return 0;
    }
}
