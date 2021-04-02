package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.exceptions.BoundsException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.LeaderAbility;
import it.polimi.ingsw.model.MutableProduction;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;


//TODO check the class and write javadoc
public class Producer implements LeaderAbility {

    MutableProduction production;


    public Producer(SupplyContainer fixedInput, SupplyContainer fixedOutput){
        production = new MutableProduction(fixedInput, fixedOutput, 1, 2);
    }


    @Override
    public SupplyContainer produce(boolean prod) {
        return production.produce(prod);
    }


    @Override
    public void checkProduction(boolean prod) throws SupplyException {
        production.check(prod);
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
    public SupplyContainer clearSupplies() {
        production.clearSupplies();
    }
}
