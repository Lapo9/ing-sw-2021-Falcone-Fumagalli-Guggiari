package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class WarehouseTest {

    @Test
    public void getResourceCount_oneType() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        assertEquals(1, wrhs.getResourceCount(WarehouseObjectType.COIN));
    }

    @Test
    public void getResourceCount_moreThanOneType() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.LEADER1_DEPOT);
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SHIELD, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        assertEquals(3, wrhs.getResourceCount(WarehouseObjectType.COIN, WarehouseObjectType.SHIELD));
    }

    @Test
    public void swapRows_noEx() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SERVANT, DepotID.PAYCHECK);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.LEADER1_DEPOT);
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        try {
            wrhs.swapRows(2, 3);
        } catch (SupplyException e) {fail();}
        SupplyContainer result3 = new SupplyContainer(wrhs.clearSupplies(DepotID.WAREHOUSE3).first);
        int[] objectsExpected3 = {2, 0, 0, 0, 0};
        int[] objectsActual3 = { result3.getQuantity(WarehouseObjectType.COIN),
                                result3.getQuantity(WarehouseObjectType.STONE),
                                result3.getQuantity(WarehouseObjectType.SERVANT),
                                result3.getQuantity(WarehouseObjectType.SHIELD),
                                result3.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        SupplyContainer result2 = new SupplyContainer(wrhs.clearSupplies(DepotID.WAREHOUSE2).first);
        int[] objectsExpected2 = {0, 0, 0, 1, 0};
        int[] objectsActual2 = { result2.getQuantity(WarehouseObjectType.COIN),
                                result2.getQuantity(WarehouseObjectType.STONE),
                                result2.getQuantity(WarehouseObjectType.SERVANT),
                                result2.getQuantity(WarehouseObjectType.SHIELD),
                                result2.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectsExpected2, objectsActual2);
        assertArrayEquals(objectsExpected3, objectsActual3);
    }

    @Test
    public void swapRows_ex() {
        Warehouse wrhs =  new Warehouse();
        boolean exc = false;
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SERVANT, DepotID.PAYCHECK);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.LEADER1_DEPOT);
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        try {
            wrhs.swapRows(1, 2);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    //TODO
    //Test addMarble with white marble and leaderSpace active with market ability

    @Test
    public void addMarble_notWhite() {
        Warehouse wrhs =  new Warehouse();
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            wrhs.addMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE, ldrspc);
            wrhs.addMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE, ldrspc);
            wrhs.addMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET, ldrspc);
        } catch (SupplyException | MarbleException e) {fail();}
        SupplyContainer result = wrhs.clearSupplies().first;
        int[] objectsExpected = {0, 0, 1, 2, 0};
        int[] objectsActual = { result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addSupply_oneNoEx() {
        Warehouse wrhs = new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SHIELD, DepotID.BASE_PRODUCTION);
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
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.COFFER);
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
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.PAYCHECK);
        } catch (SupplyException e) {fail();}
        try {
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.PAYCHECK);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_sameTypeDifferentLineEx() {
        Warehouse wrhs =  new Warehouse();
        boolean exc = false;
        try {
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_differentTypeSameLineEx() {
        Warehouse wrhs =  new Warehouse();
        boolean exc = false;
        try {
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        try {
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SHIELD, DepotID.BASE_PRODUCTION);
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
            wrhs.removeSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = wrhs.clearSupplies().first;
        int[] objectsExpected = {0, 0, 0, 0, 0};
        int[] objectsActual = { result.getQuantity(WarehouseObjectType.COIN),
                                result.getQuantity(WarehouseObjectType.STONE),
                                result.getQuantity(WarehouseObjectType.SERVANT),
                                result.getQuantity(WarehouseObjectType.SHIELD),
                                result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void additionAllowed_true() {
        Warehouse wrhs =  new Warehouse();
        assertTrue(wrhs.additionAllowed(DepotID.WAREHOUSE3, WarehouseObjectType.COIN, DepotID.PAYCHECK));
    }

    @Test
    public void additionAllowed_wrongTypeFalse() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.STONE, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        assertFalse(wrhs.additionAllowed(DepotID.WAREHOUSE3, WarehouseObjectType.COIN, DepotID.WAREHOUSE2));
    }

    @Test
    public void additionAllowed_alreadyFullFalse() {
        Warehouse wrhs =  new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        assertFalse(wrhs.additionAllowed(DepotID.WAREHOUSE1, WarehouseObjectType.SERVANT, DepotID.PAYCHECK));
    }

    @Test
    public void removalAllowed_true() {
        Warehouse wrhs = new Warehouse();
        try{
            wrhs.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.COIN, DepotID.PAYCHECK);
        }catch(SupplyException e){fail();}
        assertTrue(wrhs.removalAllowed(DepotID.WAREHOUSE3, WarehouseObjectType.COIN));
    }

    @Test
    public void removalAllowed_emptyFalse() {
        Warehouse wrhs = new Warehouse();
        assertFalse(wrhs.removalAllowed(DepotID.WAREHOUSE3, WarehouseObjectType.COIN));
    }

    @Test
    public void clearSupplies_checkAllSupplies() {
        Warehouse wrhs = new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.SHIELD, DepotID.LEADER1_DEPOT);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.PAYCHECK);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e){fail();}
        SupplyContainer result = new SupplyContainer(wrhs.clearSupplies().first);
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        int[] expectedResult = {0, 0, 2, 1, 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void clearSupplies_checkEmptiness() {
        Warehouse wrhs = new Warehouse();
        try {
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.PAYCHECK);
            wrhs.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e){fail();}
        wrhs.clearSupplies();
        SupplyContainer result = new SupplyContainer(wrhs.clearSupplies().first);
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        int[] expectedResult = {0, 0, 0, 0, 0};
        assertArrayEquals(expectedResult, actualResult);
    }
}