package it.polimi.ingsw.model.depots;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.depots.DepotsManager;
import it.polimi.ingsw.model.depots.Warehouse;
import it.polimi.ingsw.model.development.Developments;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.MarbleException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Market;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.LeadersSpace;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DepotsManagerTest {

    @Test
    public void addSupply_warehouse() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> dpt = new Pair<>(dptmng.clearSupplies(DepotID.WAREHOUSE2));
        SupplyContainer result = new SupplyContainer(dpt.first.sum(dpt.second));
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.LEADER1, WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> dpt = new Pair<>(dptmng.clearSupplies(DepotID.LEADER1));
        SupplyContainer result = new SupplyContainer(dpt.first.sum(dpt.second));
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.SERVANT), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.LEADER1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
            dptmng.addSupply(DepotID.LEADER1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dptmng.removeSupply(DepotID.LEADER1, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT3);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> dpt = new Pair<>(dptmng.clearSupplies(DepotID.LEADER1));
        SupplyContainer result = new SupplyContainer(dpt.first.sum(dpt.second));
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        assertTrue(dptmng.additionAllowed(DepotID.LEADER2, WarehouseObjectType.COIN, DepotID.WAREHOUSE3));
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.LEADER2, WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        assertTrue(dptmng.removalAllowed(DepotID.LEADER2, WarehouseObjectType.COIN, DepotID.DEVELOPMENT3));
    }

    @Test
    public void removalAllowed_false() {
        DepotsManager dptmng = new DepotsManager(new Warehouse(), new LeadersSpace());
        assertFalse(dptmng.removalAllowed(DepotID.COFFER, WarehouseObjectType.COIN, DepotID.DEVELOPMENT3));
    }

    @Test
    public void clearSupplies_fromAll() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            dptmng.addSupply(DepotID.LEADER2, WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> dpt = new Pair<>(dptmng.clearSupplies());
        SupplyContainer result = new SupplyContainer(dpt.first.sum(dpt.second));
        int[] expectedResult = {2, 2, 1, 1, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getResourceCount_differentTypeSeparately() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
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
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.WAREHOUSE1, WarehouseObjectType.COIN, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        assertEquals(5, dptmng.getResourceCount(WarehouseObjectType.COIN, WarehouseObjectType.SERVANT, WarehouseObjectType.SHIELD, WarehouseObjectType.STONE, WarehouseObjectType.FAITH_MARKER));
    }

    @Test
    public void getAllowedDepots() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.SHIELD), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.DEVELOPMENT2);
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SERVANT, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.WAREHOUSE3, WarehouseObjectType.SHIELD, DepotID.DEVELOPMENT3);
            dptmng.addSupply(DepotID.LEADER1, WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        ArrayList<DepotID> result= new ArrayList<DepotID>(dptmng.getAllowedDepots(DepotID.BASE_PRODUCTION, WarehouseObjectType.COIN));
        int[] expectedResult = {1, 0, 0, 0, 0};
        int[] actualResult = {result.contains(DepotID.WAREHOUSE1) ? 1 : 0,
                              result.contains(DepotID.WAREHOUSE2) ? 1 : 0,
                              result.contains(DepotID.WAREHOUSE3) ? 1 : 0,
                              result.contains(DepotID.LEADER1) ? 1 : 0,
                              result.contains(DepotID.LEADER2) ? 1 : 0};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void addMarble_notWhite() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addMarble(DepotID.WAREHOUSE1, MarbleColor.GREY);
            dptmng.addMarble(DepotID.WAREHOUSE2, MarbleColor.BLUE);
            dptmng.addMarble(DepotID.WAREHOUSE3, MarbleColor.VIOLET);
        } catch (SupplyException | LeaderException | MarbleException | NoSuchMethodException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> dpt = new Pair<>(dptmng.clearSupplies());
        SupplyContainer result = new SupplyContainer(dpt.first.sum(dpt.second));
        int[] expectedResult = {0, 1, 1, 1, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void addMarble_white(){
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Market(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addMarble(DepotID.WAREHOUSE2, MarbleColor.WHITE);
        } catch (MarbleException | SupplyException | LeaderException | NoSuchMethodException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> dpt = new Pair<>(dptmng.clearSupplies());
        SupplyContainer result = new SupplyContainer(dpt.first.sum(dpt.second));
        int[] expectedResult = {1, 0, 0, 0, 0};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void allocate() {
        LeadersSpace ldrspc = new LeadersSpace();
        Warehouse wrhs = new Warehouse();
        DepotsManager dptmng = new DepotsManager(wrhs, ldrspc);
        ResourceChecker rschck = new ResourceChecker(dptmng, new SupplyContainer(2, 2, 0, 0, 0), new Developments());
        try {
            ldrspc.addLeader(new LeaderCard(43, new SupplyContainer(2, 0, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.STONE), 5));
            ldrspc.addLeader(new LeaderCard(42, new SupplyContainer(0, 2, 0, 0, 0), new ArrayList<>(0), new Depot(WarehouseObjectType.COIN), 3));
        } catch (LeaderException e) {fail();}
        try {
            ldrspc.playLeader(0, rschck);
            ldrspc.playLeader(1, rschck);
        } catch (SupplyException | LeaderException e) {fail();}
        try {
            dptmng.addSupply(DepotID.WAREHOUSE2, WarehouseObjectType.SHIELD, DepotID.BASE_PRODUCTION);
            dptmng.addSupply(DepotID.LEADER2, WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        dptmng.allocate(new SupplyContainer(2, 4, 0, 2, 0));
        ArrayList<Integer> result = wrhs.getStatus();
        ArrayList<Integer> ldrStatus = ldrspc.getStatus();
        int index = 15;
        for(int i = 10; i<= 14; i++) {
            result.add(index, ldrStatus.get(i));
            index++;
        }
        for(int i = 25; i <=29; i++){
            result.add(index, ldrStatus.get(i));
            index++;
        }
        int[] expectedResult = {1, 0, 0, 0, 0,
                                0, 0, 0, 2, 0,
                                0, 0, 3, 0, 0,
                                0, 0, 0, 2, 0,
                                2, 0, 0, 0, 0};
        int[] actualResult = {result.get(0), result.get(1), result.get(2), result.get(3), result.get(4),
                              result.get(5), result.get(6), result.get(7), result.get(8), result.get(9),
                              result.get(10), result.get(11), result.get(12), result.get(13), result.get(14),
                              result.get(15), result.get(16), result.get(17), result.get(18), result.get(19),
                              result.get(20), result.get(21), result.get(22), result.get(23), result.get(24)};
        assertArrayEquals(expectedResult, actualResult);
    }
}