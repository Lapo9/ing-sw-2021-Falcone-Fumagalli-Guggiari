package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.DepotID;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import org.junit.Test;

import java.util.ArrayList;

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
        try{
            prod.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException e){fail();}
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
        try{
            prod.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException e){fail();}
        Pair<SupplyContainer, SupplyContainer> collector = prod.clearSupplies();
        SupplyContainer result = new SupplyContainer(collector.first.sum(collector.second));
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };
        int[] expectedObject = {0, 1, 0, 0, 0};

        assertArrayEquals(actualObject, expectedObject);
    }

    @Test
    public void removeSupply_no_exc() {
        SupplyContainer input = new SupplyContainer(0,1,0,0,0);
        Producer prod = new Producer(input);
        try{
            prod.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
            prod.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
        } catch (SupplyException e){fail();}
        try{
            prod.removeSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e){fail();}
        Pair<SupplyContainer, SupplyContainer> collector = prod.clearSupplies();
        SupplyContainer result = new SupplyContainer(collector.first.sum(collector.second));
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };
        int[] expectedObject = {0, 1, 0, 0, 0};

        assertArrayEquals(actualObject, expectedObject);
    }

    @Test
    public void additionAllowed() {
        SupplyContainer input = new SupplyContainer(0,1,0,0,0);
        Producer prod = new Producer(input);

        assertTrue(prod.additionAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE1));
    }

    @Test
    public void removalAllowed() {
        SupplyContainer input = new SupplyContainer(0,1,0,0,0);
        Producer prod = new Producer(input);
        try{
            prod.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException e){fail();}
        boolean ris1 = false;
        boolean ris2 = true;
        try {
            ris1 = prod.removalAllowed(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
            ris2 = prod.removalAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch(NoSuchMethodException e){fail();}

        assertTrue(ris1);
        assertFalse(ris2);
    }

    @Test
    public void clearSupplies() {
        SupplyContainer input = new SupplyContainer(0,1,0,0,0);
        Producer prod = new Producer(input);
        try{
            prod.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException e){fail();}
        Pair<SupplyContainer, SupplyContainer> collector = prod.clearSupplies();
        SupplyContainer result = new SupplyContainer(collector.first.sum(collector.second));
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };
        int[] expectedObject = {0, 1, 0, 0, 0};

        assertArrayEquals(actualObject, expectedObject);
    }

    @Test
    public void getStatus(){
        Producer prdc = new Producer(new SupplyContainer(1, 0, 0, 0, 0));
        try {
            prdc.swapProduction(WarehouseObjectType.SHIELD);
        }catch (SupplyException e) {fail();}
        try{
            prdc.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(prdc.getStatus());
        int[] expectedResult = {0, //fixedInput
                                4, //fixedOutput
                                2,  //mutableOutput
                                1, 0, 0, 0, 0, //currentSupply
                                0, 0, 0, 0, 0};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2),
                              result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                              result.get(8), result.get(9), result.get(10), result.get(11), result.get(12)};
        assertArrayEquals(expectedResult, actualResult);
    }
}