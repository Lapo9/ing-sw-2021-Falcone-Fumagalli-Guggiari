package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepotTest {

    @Test
    public void getDepotInfo_oneSupply() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<WarehouseObjectType, Integer> result =dpt.getDepotInfo();
        assertEquals(WarehouseObjectType.COIN, result.first);
        assertEquals(Integer.valueOf(1), result.second);
    }

    @Test
    public void getDepotInfo_twoSupplies() {
        Depot dpt = new Depot(WarehouseObjectType.STONE);
        try {
            dpt.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
            dpt.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        Pair<WarehouseObjectType, Integer> result =dpt.getDepotInfo();
        assertEquals(WarehouseObjectType.STONE, result.first);
        assertEquals(Integer.valueOf(2), result.second);
    }

    @Test
    public void addMarble_noLeaderWrongTypeEx() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        LeadersSpace ldrSpc = new LeadersSpace();
        boolean exc = false;
        try {
            dpt.addMarble(MarbleColor.YELLOW, ldrSpc);
        } catch (MarbleException e){fail();}
        catch(SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addMarble(){
        //TODO
        //we still need to test what happens if there is a leader card with the 'Market' ability
        //probably the addMarble test above is unnecessary when we add this test cases
    }

    @Test
    public void addSupply_noEx() {
        Depot dpt = new Depot(WarehouseObjectType.SERVANT);
        try {
            dpt.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> collecting = dpt.clearSupplies();
        SupplyContainer result = new SupplyContainer(collecting.first.sum(collecting.second));
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {0, 0, 1, 0};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void addSupply_alreadyFullEx(){
        Depot dpt = new Depot(WarehouseObjectType.SHIELD);
        boolean exc = false;
        try {
            dpt.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
            dpt.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dpt.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_wrongTypeEx(){
        Depot dpt = new Depot(WarehouseObjectType.STONE);
        boolean exc = false;
        try {
            dpt.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeSupply_noEx() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
            dpt.removeSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> collecting = dpt.clearSupplies();
        SupplyContainer result = new SupplyContainer(collecting.first.sum(collecting.second));
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {1, 0, 0, 0};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void removeSupply_alreadyEmptyEx() {
        Depot dpt = new Depot(WarehouseObjectType.STONE);
        boolean exc = false;
        try {
            dpt.removeSupply(WarehouseObjectType.STONE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeSupply_wrongTypeEx() {
        Depot dpt = new Depot(WarehouseObjectType.SHIELD);
        boolean exc = false;
        try {
            dpt.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dpt.removeSupply(WarehouseObjectType.STONE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void additionAllowedTest() {
        Depot dpt = new Depot(WarehouseObjectType.STONE);

        // i can only add STONE
        assertTrue(dpt.additionAllowed(WarehouseObjectType.STONE, DepotID.WAREHOUSE2));
        assertFalse(dpt.additionAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE2));
    }

    @Test
    public void removalAllowedTest() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
            dpt.removeSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}

        assertTrue(dpt.removalAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE3));
        assertFalse(dpt.removalAllowed(WarehouseObjectType.STONE, DepotID.WAREHOUSE2));
    }

    @Test
    public void clearSupplies() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> collecting = dpt.clearSupplies();
        SupplyContainer result = new SupplyContainer(collecting.first.sum(collecting.second));
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {2, 0, 0, 0};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void clearSupplies_alreadyEmpty() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        Pair<SupplyContainer, SupplyContainer> collecting = dpt.clearSupplies();
        SupplyContainer result = new SupplyContainer(collecting.first.sum(collecting.second));
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {0, 0, 0, 0};
        assertArrayEquals(expectedObject, actualObject);
    }
}