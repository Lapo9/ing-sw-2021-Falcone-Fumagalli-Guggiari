package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;


//TODO check the class and write javadoc
public class Producer implements LeaderAbility {

    MutableProduction production;


    /**
     * Creates a new producer leader, whose fixed output is always a faith point
     * @param fixedInput what is the fixed input of the leader?
     * @param leaderID is this leader 1 or 2?
     */
    public Producer(SupplyContainer fixedInput, int leaderID){
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
    public void changeOutput(WarehouseObjectType wot) {
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
    public SupplyContainer clearSupplies() {
        return production.clearSupplies();
    }
}
