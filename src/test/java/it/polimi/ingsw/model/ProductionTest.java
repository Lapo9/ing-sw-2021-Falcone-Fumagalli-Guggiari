package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductionTest {

    @Test
    public void produce_noEx() {
        SupplyContainer in = new SupplyContainer(2, 0, 0, 0, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        try {
            prd.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
            prd.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = prd.produce();
        int[] objectResExpected = {0, 2, 0, 0, 2};
        int[] objectResActual = {result.getQuantity(WarehouseObjectType.COIN),
                                 result.getQuantity(WarehouseObjectType.STONE),
                                 result.getQuantity(WarehouseObjectType.SERVANT),
                                 result.getQuantity(WarehouseObjectType.SHIELD),
                                 result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectResExpected, objectResActual);
    }

    //maybe test what happens if produce launches an exceptions

    @Test
    public void check_noEx() {
        SupplyContainer in = new SupplyContainer(0, 1, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(1, 0, 3,0, 2);
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
    public void addSupply() {
        SupplyContainer in = new SupplyContainer(0, 2, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        boolean exc =false;
        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT1_DEPOT);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_noEx() {
        SupplyContainer in = new SupplyContainer(3, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 2, 0,0, 2);
        Production prd = new Production(in, out);
        boolean exc =false;
        try {
            prd.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            prd.addSupply(WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT2_DEPOT);
        } catch (SupplyException e) {fail();}
        try {
            prd.removeSupply(DepotID.COFFER, WarehouseObjectType.STONE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

}