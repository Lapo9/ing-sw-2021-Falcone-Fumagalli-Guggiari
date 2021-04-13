package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.DepotID;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProducerTest {

    @Test
    public void produceTest() {
        //creating the first type of production: input -> COIN, output -> FAITH_MARKER
        Producer prod = new Producer(new SupplyContainer(0,1,0,0,0));

        //swap the default COIN in output, here i decide the output
        try {
            //only one resource in output: the last one swapped -> SERVANT
            prod.swapProduction(WarehouseObjectType.STONE);
            prod.swapProduction(WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}

        SupplyContainer p = prod.produce();

        //output swapped + FAITH_MARKER
        int [] expectedObject = {0, 0, 1, 0, 1};
        int [] actualObject = {p.getQuantity(WarehouseObjectType.COIN),
                p.getQuantity(WarehouseObjectType.STONE),
                p.getQuantity(WarehouseObjectType.SERVANT),
                p.getQuantity(WarehouseObjectType.SHIELD),
                p.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };

        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void checkProduction_no_exc() {
        Producer prod = new Producer(new SupplyContainer(0,1,0,0,0));
        boolean exc = false;
        try {
            prod.checkProduction();
        } catch (SupplyException e){
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void swapProduction_no_exc() {
        Producer prod = new Producer(new SupplyContainer(0,1,0,0,0));
        try {
            //swap FAITH_MARKER with COIN
            prod.swapProduction(WarehouseObjectType.COIN); //calls MutableProduction swapOutput
        } catch (SupplyException e) {
            fail();
        }
        SupplyContainer p = prod.produce();

        assertEquals(1, p.getQuantity(WarehouseObjectType.COIN));
    }

    @Test
    public void addSupply_no_exc() {
        SupplyContainer input = new SupplyContainer(0,1,0,0,0);
        Producer prod = new Producer(input);
        //the only way to test if addSupply works is to test if this method throws an exception
        //FIXME i don't know where the resource goes: In the input(?)
        boolean exc = false;
        try{
            prod.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            exc = true;
        }


        assertFalse(exc);
    }

    @Test
    public void removeSupply_no_exc() {
        SupplyContainer input = new SupplyContainer(0,1,0,0,0);
        Producer prod = new Producer(input);
        try{
            prod.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e){fail();}
        boolean exc = false;
        try{
            prod.removeSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void additionAllowed() {
    }

    @Test
    public void removalAllowed() {
    }

    @Test
    public void clearSupplies() {
    }
}