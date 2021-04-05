package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.LeadersSpace;
import it.polimi.ingsw.model.MarbleColor;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepotTest {

    @Test
    public void getDepotInfo_empty() {
        Depot dpt = new Depot(WarehouseObjectType.SHIELD);
        Pair<WarehouseObjectType, Integer> result =dpt.getDepotInfo();
        assertEquals(WarehouseObjectType.SHIELD, result.first);
        assertEquals(Integer.valueOf(0), result.second);
    }

    @Test
    public void getDepotInfo_oneSupply() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        Pair<WarehouseObjectType, Integer> result =dpt.getDepotInfo();
        assertEquals(WarehouseObjectType.COIN, result.first);
        assertEquals(Integer.valueOf(1), result.second);
    }

    @Test
    public void getDepotInfo_twoSupplies() {
        Depot dpt = new Depot(WarehouseObjectType.STONE);
        try {
            dpt.addSupply(WarehouseObjectType.STONE);
            dpt.addSupply(WarehouseObjectType.STONE);
        } catch (SupplyException e) {fail();}
        Pair<WarehouseObjectType, Integer> result =dpt.getDepotInfo();
        assertEquals(WarehouseObjectType.STONE, result.first);
        assertEquals(Integer.valueOf(2), result.second);
    }

    @Test
    public void addMarble_noLeaderNoEx() {
        Depot dpt = new Depot(WarehouseObjectType.SERVANT);
        LeadersSpace ldrSpc = new LeadersSpace();
        try {
            dpt.addMarble(MarbleColor.BLUE, ldrSpc);
        } catch (MarbleException | SupplyException e) {fail();}
        SupplyContainer result = dpt.clearSupplies();
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {0, 0, 1, 0};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void addMarble_noLeaderWrongTypeEx() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        LeadersSpace ldrSpc = new LeadersSpace();
        boolean exc = false;
        try {
            dpt.addMarble(MarbleColor.YELLOW, ldrSpc);
        } catch (MarbleException e){
            exc =true;
        }catch(SupplyException e) {fail();}
        assertTrue(exc);
    }

    @Test
    public void addMarble(){
        //TODO
        //we still need to test what happens if there is a leader card with the 'Market' ability
    }

    @Test
    public void addSupply_noEx() {
        Depot dpt = new Depot(WarehouseObjectType.SERVANT);
        try {
            dpt.addSupply(WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = dpt.clearSupplies();
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
            dpt.addSupply(WarehouseObjectType.SHIELD);
            dpt.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dpt.addSupply(WarehouseObjectType.SHIELD);
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
            dpt.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeSupply_noEx() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN);
            dpt.addSupply(WarehouseObjectType.COIN);
            dpt.removeSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = dpt.clearSupplies();
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
            dpt.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            dpt.removeSupply(WarehouseObjectType.STONE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void clearSupplies() {
        Depot dpt = new Depot(WarehouseObjectType.COIN);
        try {
            dpt.addSupply(WarehouseObjectType.COIN);
            dpt.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = dpt.clearSupplies();
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
        SupplyContainer result = dpt.clearSupplies();
        int[] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD)};
        int[] expectedObject = {0, 0, 0, 0};
        assertArrayEquals(expectedObject, actualObject);
    }
}