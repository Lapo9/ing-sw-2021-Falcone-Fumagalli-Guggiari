package it.polimi.ingsw.model.development;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.DepotID;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.model.development.Production;
import it.polimi.ingsw.model.exceptions.SupplyException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ProductionTest {

    @Test
    public void produce_noAddEx() {
        SupplyContainer in = new SupplyContainer(2, 0, 0, 0, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        try {
            //try to add to the currentSupply the resources needed
            prd.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1); //add to the currentSupply one coin from WAREHOUSE1
            prd.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2); //add to the currentSupply one coin from WAREHOUSE2
        } catch (SupplyException e) {fail();}
        SupplyContainer result = prd.produce();
        int [] objectResExpected = {0, 2, 0, 0, 2};
        int [] objectResActual = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectResExpected, objectResActual);
    }

    @Test
    public void check_noEx() {
        SupplyContainer in = new SupplyContainer(0, 1, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(1, 0, 3,0, 2);
        Production prd = new Production(in, out);
        //try to add supplies from player depots
        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        boolean exc = false;
        //try to check if resources in currentSupply correspond to the input
        try {
            prd.check();
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void check_Ex() {
        SupplyContainer in = new SupplyContainer(0, 2, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        boolean exc = false;
        try {
            prd.check();
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_noEx() {
        SupplyContainer in = new SupplyContainer(0, 2, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT1);
        } catch (SupplyException e) {
            fail();
        }

        int [] expectedObject = {0, 2, 0, 1, 0};

        int [] actualObject = {prd.currentSupply.getQuantity(WarehouseObjectType.COIN),
                prd.currentSupply.getQuantity(WarehouseObjectType.STONE),
                prd.currentSupply.getQuantity(WarehouseObjectType.SERVANT),
                prd.currentSupply.getQuantity(WarehouseObjectType.SHIELD),
                prd.currentSupply.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };

        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void removeSupply_noEx() {
        SupplyContainer in = new SupplyContainer(3, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        try {
            prd.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            prd.removeSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail(); }

        int [] expectedObject = {3, 0, 0, 1, 0};
        int [] actualObject = {prd.currentSupply.getQuantity(WarehouseObjectType.COIN),
                prd.currentSupply.getQuantity(WarehouseObjectType.STONE),
                prd.currentSupply.getQuantity(WarehouseObjectType.SERVANT),
                prd.currentSupply.getQuantity(WarehouseObjectType.SHIELD),
                prd.currentSupply.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };

        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void additionAllowedTest() {
        SupplyContainer in = new SupplyContainer(0, 1, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(1, 0, 3,0, 2);
        Production prd = new Production(in, out);
        //ProductionManager checks if WAREHOUSE1 has one STONE
        assertTrue(prd.additionAllowed(WarehouseObjectType.STONE, DepotID.WAREHOUSE1));
    }

    @Test
    public void removalAllowedTest() {
        SupplyContainer in = new SupplyContainer(0, 1, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(1, 0, 3,0, 2);
        Production prd = new Production(in, out);

        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
        } catch (SupplyException e) {fail();}

        //check if i can remove
        assertTrue(prd.removalAllowed(WarehouseObjectType.STONE, DepotID.COFFER));
        assertFalse(prd.removalAllowed(WarehouseObjectType.COIN, DepotID.COFFER));
        /* ProductionManager manages this case, not Production
        assertFalse(prd.removalAllowed(WarehouseObjectType.SHIELD, DepotID.PAYCHECK));
        assertFalse(prd.removalAllowed(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1));
         */
    }

    @Test
    public void clearSuppliesTest(){
        SupplyContainer in = new SupplyContainer(0, 1, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(1, 0, 3,0, 2);
        Production prd = new Production(in, out);
        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            prd.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> collector = prd.clearSupplies();
        SupplyContainer result = new SupplyContainer(collector.first.sum(collector.second)); //depot that contains all the resources cleared
        int [] expectedObject = {1, 1, 0, 1, 0};
        int [] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };

        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void getStatus(){
        SupplyContainer in = new SupplyContainer(0, 2, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
            prd.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT1);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(prd.getStatus());
        int[] expectedResult = {0, 0, 1, 2, 0,
                                0, 0, 0, 2, 2,
                                0, 0, 1, 2, 0};
        int[] actualResult = {result.get(0), result.get(1), result.get(2), result.get(3), result.get(4),
                              result.get(5), result.get(6), result.get(7), result.get(8), result.get(9),
                              result.get(10), result.get(11), result.get(12), result.get(13), result.get(14)};
        assertArrayEquals(expectedResult, actualResult);
    }
}