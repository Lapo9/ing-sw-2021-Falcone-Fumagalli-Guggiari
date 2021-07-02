package it.polimi.ingsw.model.development;

import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;

import java.util.ArrayList;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.leaders.LeadersSpace;

/**
 * The Production class represents the production mechanism of the game.
 * It has an input and an output, and can store supplies.
 * When the production is triggered, if the input equals the current supply store, then the current supply store is wiped and the output is returned.
 */
public class Production implements AcceptsSupplies, HasStatus {

    protected final SupplyContainer input;
    protected final SupplyContainer output;
    //currentSupply is the temporary depot to store resources that will be used for production
    //can't accept FAITH_MARKER and can't accept from PAYCHECK
    protected SupplyContainer currentSupply = new SupplyContainer((specificType(WarehouseObjectType.FAITH_MARKER).negate()));


    /**
     * Class constructor
     * @param in SupplyContainer which contains the supplies needed as input
     * @param out SupplyContainer which contains the supplies produces by output when the production is triggered
     */
    public Production(SupplyContainer in, SupplyContainer out){
        input = in;
        output = out;
    }



    /**
     * Gets the input of the Production
     * @return the SupplyContainer containing the input
     */
    public SupplyContainer getInput(){
        return new SupplyContainer(input);
    }




    /**
     * Activates the production. If the input equals the current supply store, then the current supply store is wiped and the output is returned.
     * If not a fatal error is produced and the program is terminated.
     * @return A SupplyContainer containing the output
     */
    public SupplyContainer produce(){
        try {
            check();
        } catch (SupplyException e) {System.exit(1); }
        clearSupplies();
        return new SupplyContainer(output);
    }

    /**
     * Verifies if the current supplies present in the production depot are the same as the input supplies.
     * @throws SupplyException The current supplies present in the production depot are not the same as the input supplies.
     */
    public void check() throws SupplyException{
        if(!input.equals(currentSupply))
            throw new SupplyException("There isn't the correct number of supplies to produce");
    }

    /**
     * Adds the supply to the storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException{
        additionAllowed(wot, from);
        currentSupply.addSupply(wot, from);
    }

    /**
     * Removes the supply from the storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException{
        removalAllowed(wot, to);
        currentSupply.removeSupply(wot);
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return currentSupply.additionAllowed(wot, from) && from != DepotID.PAYCHECK;
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        return currentSupply.removalAllowed(wot, to) && to != DepotID.PAYCHECK;
    }

    /**
     * Removes all of the supplies in the currentSupply
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(){
        return currentSupply.clearSupplies();
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of Integer describing the Production
     */
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        status.addAll(input.getStatus());
        status.addAll(output.getStatus());
        status.addAll(currentSupply.getStatus());

        return status;
    }
}
