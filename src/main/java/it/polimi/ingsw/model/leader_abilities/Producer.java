package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.exceptions.BoundsException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;


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
        fixedOutput.addSupply(WarehouseObjectType.FAITH_MARKER);
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
        //remove what is currently inside
        try {
            production.removeOutput(WarehouseObjectType.COIN);
        } catch (BoundsException be) {}
        try {
            production.removeOutput(WarehouseObjectType.SHIELD);
        } catch (BoundsException be) {}
        try {
            production.removeOutput(WarehouseObjectType.STONE);
        } catch (BoundsException be) {}
        try {
            production.removeOutput(WarehouseObjectType.SERVANT);
        } catch (BoundsException be) {}
        try {
            production.removeOutput(WarehouseObjectType.FAITH_MARKER);
        } catch (BoundsException be) {}

        //add what the user wants
        try {
            production.addOutput(wot);
        } catch (BoundsException be) {/*TODO ends program, fatal error*/}
    }


    @Override
    public void addSupply(WarehouseObjectType wot) throws SupplyException {
        //TODO maybe we can put here a check to deny the user to put more resources than necessary
        production.addSupply(wot);
    }


    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException {
        production.removeSupply(wot);
    }


    @Override
    public ArrayList<DepotID> availableDepots(DepotID from, WarehouseObjectType wot) throws NoSuchMethodException {
        return production.availableDepots(from, wot);
    }

    @Override
    public SupplyContainer clearSupplies() {
        return production.clearSupplies();
    }
}
