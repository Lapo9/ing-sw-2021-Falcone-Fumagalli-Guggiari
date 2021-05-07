package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;


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
    public SupplyContainer getInput() throws NoSuchMethodException {
        return production.getInput();
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


    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        ArrayList<Integer> tmp = production.getStatus();

        //transform the sequence COIN, SERVANT, SHIELD, STONE, FAITH_MARKER that is the fixed input of the production into one int that represent what is the only fixed input more shortly (0=COIN, 1=SERVANT, ...)
        int fixedInput = 0;
        for(int i=0; i<5; ++i){
            if(tmp.get(i) != 0){
                fixedInput = i;
                break;
            }
        }

        //same for fixed output
        int fixedOutput = 0;
        for(int i=0; i<5; ++i){
            if(tmp.get(i+6) != 0){
                fixedOutput = i;
                break;
            }
        }

        int mutableOutput = tmp.get(11);

        status.add(fixedInput);
        status.add(fixedOutput);
        status.add(mutableOutput);
        status.addAll(tmp.subList(12, 17)); //current depot

        for(int i=0; i<5; ++i){
            status.add(0); //Producer doesn't have a "warehouse depot"
        }

        return status;
    }
}
