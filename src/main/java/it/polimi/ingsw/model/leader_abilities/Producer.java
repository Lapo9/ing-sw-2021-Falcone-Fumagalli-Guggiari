package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;


/**
 * This implementation of LeaderAbility only supports the methods related to the production process.
 */
public class Producer implements LeaderAbility {

    MutableProduction production;


    /**
     * Creates a new producer leader, whose fixed output is always a faith point
     * @param fixedInput Fixed input of the leader
     */
    public Producer(SupplyContainer fixedInput){
        SupplyContainer fixedOutput = new SupplyContainer();
        try {
            fixedOutput.addSupply(WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException se) {/*TODO terminate program*/}

        production = new MutableProduction(fixedInput, fixedOutput, 1, 2);
    }


    @Override
    public SupplyContainer produce() {
        return production.produce();
    }


    @Override
    public void checkProduction() throws SupplyException {
        production.check();
    }


    @Override
    public void swapProduction(WarehouseObjectType wot) throws SupplyException{
        production.swapOutput(0, wot);
    }


    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        production.addSupply(wot, from);
    }


    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException {
        production.removeSupply(wot, to);
    }


    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return production.additionAllowed(wot, from);
    }


    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) throws NoSuchMethodException {
        return production.removalAllowed(wot, to);
    }


    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return production.clearSupplies();
    }
}
