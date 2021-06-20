package it.polimi.ingsw.model.leaders.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.depots.DepotsManager;
import it.polimi.ingsw.model.depots.Warehouse;
import it.polimi.ingsw.model.development.Developments;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.LeadersSpace;
import org.junit.Test;

import java.util.ArrayList;

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
            dpt.addMarble(MarbleColor.GREY, ldrSpc);
        } catch (MarbleException e){fail();}
        catch(SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addMarble_white(){
        Depot dpt = new Depot(WarehouseObjectType.SERVANT);
        LeadersSpace ldrspc = new LeadersSpace();
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.SERVANT), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, new ResourceChecker(new DepotsManager(new Warehouse(ldrspc), ldrspc), new SupplyContainer(2, 0, 0, 0, 0), new Developments()));
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dpt.addMarble(MarbleColor.WHITE, ldrspc);
        } catch (MarbleException e) {
            fail();
        } catch (SupplyException e) {
            fail();
        }

        boolean exc = false;
        try {
            dpt.removeSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {
            exc = true;
        }
        assertFalse(exc);
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

    @Test
    public void getStatus(){
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            dpt.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(dpt.getStatus());
        int[] expectedResult = {0,
                                0,
                                0,
                                0, 0, 0, 0, 0,
                                2, 0, 0, 0, 0};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2),
                              result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                              result.get(8), result.get(9), result.get(10), result.get(11), result.get(12)};
        assertArrayEquals(expectedResult, actualResult);
    }
}