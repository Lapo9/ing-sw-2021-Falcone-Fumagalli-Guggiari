package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

//TODO this class needs to be tested
public class DepotsManagerTest {

    @Test
    public void addSupply_warehouse() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> wrhs = new Pair<>(dptmng.clearSupplies(DepotID.WAREHOUSE2));
        SupplyContainer result = new SupplyContainer(wrhs.first.sum(wrhs.second));
        int[] expectedResult = {1, 0, 0, 0, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void addSupply_leader() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> wrhs = new Pair<>(dptmng.clearSupplies(DepotID.LEADER1_DEPOT));
        SupplyContainer result = new SupplyContainer(wrhs.first.sum(wrhs.second));
        int[] expectedResult = {0, 0, 0, 1, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void removeSupply_warehouse() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        try {
            dptmng.removeSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.DEVELOPMENT3);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> wrhs = new Pair<>(dptmng.clearSupplies(DepotID.WAREHOUSE2));
        SupplyContainer result = new SupplyContainer(wrhs.first.sum(wrhs.second));
        int[] expectedResult = {1, 0, 0, 0, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void removeSupply_leader() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        try {
            dptmng.removeSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT3);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> wrhs = new Pair<>(dptmng.clearSupplies(DepotID.LEADER1_DEPOT));
        SupplyContainer result = new SupplyContainer(wrhs.first.sum(wrhs.second));
        int[] expectedResult = {0, 1, 0, 0, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void additionAllowed_warehouse() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        assertTrue(dptmng.additionAllowed(DepotID.WAREHOUSE2, WarehouseObjectType.SHIELD, DepotID.BASE_PRODUCTION));
    }

    @Test
    public void additionAllowed_leader() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        assertTrue(dptmng.additionAllowed(DepotID.LEADER2_DEPOT, WarehouseObjectType.COIN, DepotID.WAREHOUSE3));
    }

    @Test
    public void additionAllowed_false() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        assertFalse(dptmng.additionAllowed(DepotID.COFFER, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3));
    }

    @Test
    public void removalAllowed_warehouse() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        assertTrue(dptmng.removalAllowed(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.DEVELOPMENT3));
    }

    @Test
    public void removalAllowed_leader() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.LEADER2_DEPOT, WarehouseObjectType.SERVANT, DepotID.LEADER2_PRODUCTION);
        } catch (SupplyException e) {fail();}
        assertTrue(dptmng.removalAllowed(DepotID.LEADER2_DEPOT, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT3));
    }

    @Test
    public void removalAllowed_false() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        assertFalse(dptmng.removalAllowed(DepotID.COFFER, WarehouseObjectType.COIN, DepotID.DEVELOPMENT3));
    }

    @Test
    public void clearSupplies_fromAll() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.STONE, DepotID.LEADER2_PRODUCTION);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> wrhs = new Pair<>(dptmng.clearSupplies());
        SupplyContainer result = new SupplyContainer(wrhs.first.sum(wrhs.second));
        int[] expectedResult = {1, 2, 1, 1, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getResourceCount_differentTypeSeparately() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.STONE, DepotID.LEADER2_PRODUCTION);
        } catch (SupplyException e) {fail();}
        int[] expectedResult = {1, 2, 1, 1, 0};
        int[] actualResult = {dptmng.getResourceCount(WarehouseObjectType.COIN),
                              dptmng.getResourceCount(WarehouseObjectType.SERVANT),
                              dptmng.getResourceCount(WarehouseObjectType.SHIELD),
                              dptmng.getResourceCount(WarehouseObjectType.STONE),
                              dptmng.getResourceCount(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getResourceCount_allResources() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.STONE, DepotID.LEADER2_PRODUCTION);
        } catch (SupplyException e) {fail();}
        assertEquals(5, dptmng.getResourceCount(WarehouseObjectType.COIN, WarehouseObjectType.SERVANT, WarehouseObjectType.SHIELD, WarehouseObjectType.STONE, WarehouseObjectType.FAITH_MARKER));
    }

    @Test
    public void getAllowedDepots() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1_DEPOT, WarehouseObjectType.STONE, DepotID.LEADER2_PRODUCTION);
        } catch (SupplyException e) {fail();}
        ArrayList<DepotID> result= new ArrayList<DepotID>(dptmng.getAllowedDepots(DepotID.BASE_PRODUCTION, WarehouseObjectType.SHIELD));
        int[] expectedResult = {1, 0, 1, 0, 1};
        int[] actualResult = {result.contains(DepotID.WAREHOUSE1) ? 1 : 0,
                              result.contains(DepotID.WAREHOUSE2) ? 1 : 0,
                              result.contains(DepotID.WAREHOUSE3) ? 1 : 0,
                              result.contains(DepotID.LEADER1_DEPOT) ? 1 : 0,
                              result.contains(DepotID.LEADER2_DEPOT) ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void addMarble_notWhite() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        try {
            dptmng.addMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
            dptmng.addMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dptmng.addMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | LeaderException | MarbleException | NoSuchMethodException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> wrhs = new Pair<>(dptmng.clearSupplies());
        SupplyContainer result = new SupplyContainer(wrhs.first.sum(wrhs.second));
        int[] expectedResult = {0, 1, 1, 1, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    //TODO
    //The addMarble method needs to be tested when a LeaderCard with market ability is active
}