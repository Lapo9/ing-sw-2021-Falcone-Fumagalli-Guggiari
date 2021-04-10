package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;

/**
 * The Production class represents the production mechanism of the game.
 * It has an input and an output, and can store supplies.
 * When the production is triggered, if the input equals the current supply store, then the current supply store is wiped and the output is returned.
 */
public class Production implements AcceptsSupplies, HasStatus{

    protected final SupplyContainer input;
    protected final SupplyContainer output;
    //currentSupply is the temporary depot to store resources that will be used for production
    //can't accept FAITH_MARKER and can't accept from PAYCHECK
    protected SupplyContainer currentSupply = new SupplyContainer(  SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.PAYCHECK).negate().
                                                                        and(SupplyContainer.AcceptStrategy.specificType(WarehouseObjectType.FAITH_MARKER).negate()),
                                                                    SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.PAYCHECK).negate());
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
            throw new SupplyException();
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
        return null; //TODO
    }
}
