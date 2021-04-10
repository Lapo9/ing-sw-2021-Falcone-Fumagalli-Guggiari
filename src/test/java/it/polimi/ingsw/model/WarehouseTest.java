package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarehouseTest {

    @Test
    public void getResourceCount() {

    }

    @Test
    public void swapRows() {
    }

    //TODO
    @Test
    public void addMarble() {
    }

    @Test
    public void addSupply_oneNoEx() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = wrhs.clearSupplies().first;
        int[] objectsExpected = {0, 0, 0, 1, 0};
        int[] objectsActual = { result.getQuantity(WarehouseObjectType.COIN),
                                result.getQuantity(WarehouseObjectType.STONE),
                                result.getQuantity(WarehouseObjectType.SERVANT),
                                result.getQuantity(WarehouseObjectType.SHIELD),
                                result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addSupply_wrongSourceEx() {
        Warehouse wrhs =  new Warehouse();
        boolean exc = false;
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SHIELD, DepotID.COFFER);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_fullEx() {
        Warehouse wrhs =  new Warehouse();
        boolean exc = false;
        try {
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeSupply() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = wrhs.clearSupplies().first;
        int[] objectsExpected = {0, 0, 0, 1, 0};
        int[] objectsActual = { result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void additionAllowed_wrongTypeFalse() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        assertFalse(wrhs.additionAllowed(DepotID.WAREHOUSE3, WarehouseObjectType.COIN, DepotID.WAREHOUSE1));
    }

    @Test
    public void additionAllowed_alreadyFullFalse() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        assertFalse(wrhs.additionAllowed(DepotID.WAREHOUSE1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3));
    }

    @Test
    public void removalAllowed() {
    }

    @Test
    public void clearSupplies() {
    }

    @Test
    public void testClearSupplies() {
    }
}