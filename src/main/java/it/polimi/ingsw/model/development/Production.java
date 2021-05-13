package it.polimi.ingsw.model.development;

import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;

import java.util.ArrayList;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

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
    protected SupplyContainer currentSupply = new SupplyContainer(  onlyFrom(DepotID.SourceType.PAYCHECK).negate().
                                                                    and(specificType(WarehouseObjectType.FAITH_MARKER).negate()),
                                                                    onlyFrom(DepotID.SourceType.PAYCHECK).negate());
    //TODO maybe add that you cannot add more than the max?


    /**
     * Class constructor
     * @param in SupplyContainer which contains the supplies needed as input
     * @param out SupplyContainer which contains the supplies produces by output when the production is triggered
     */
    public Production(SupplyContainer in, SupplyContainer out){
        input = in;
        output = out;
    }




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
        } catch (SupplyException e) {/*TODO terminate program*/ }
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


    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException{
        currentSupply.addSupply(wot, from);
    }

    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException{
        currentSupply.removeSupply(wot);
    }

    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return currentSupply.additionAllowed(wot, from);
    }

    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        return currentSupply.removalAllowed(wot, to);
    }


    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(){
        return currentSupply.clearSupplies();
    }


    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        status.addAll(input.getStatus());
        status.addAll(output.getStatus());
        status.addAll(currentSupply.getStatus());

        return status;
    }
}