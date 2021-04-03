package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoundedSupplyContainerTest {

    @Test
    public void setMax_one() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(3);
        bsc.setMax(1);
        int[] actualObject = { bsc.getMax(),
                               bsc.getPosition()};
        int[] expectedObject= {1, 3};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void setMax_two() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        bsc.setMax(2);
        int[] actualObject = { bsc.getMax(),
                               bsc.getPosition()};
        int[] expectedObject= {2, 2};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void setMax_three() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(2);
        bsc.setMax(3);
        int[] actualObject = { bsc.getMax(),
                               bsc.getPosition()};
        int[] expectedObject= {3, 1};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void setPosition_one() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(2);
        bsc.setPosition(1);
        int[] actualObject = { bsc.getPosition(),
                               bsc.getMax()};
        int[] expectedObject= {1, 3};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void setPosition_two() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(3);
        bsc.setPosition(2);
        int[] actualObject = { bsc.getPosition(),
                               bsc.getMax()};
        int[] expectedObject= {2, 2};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void setPosition_three() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        bsc.setPosition(3);
        int[] actualObject = { bsc.getPosition(),
                               bsc.getMax()};
        int[] expectedObject= {3, 1};
        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void getQuantity_zero() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        assertEquals(0, bsc.getQuantity());
    }

    @Test
    public void getQuantity_notZero() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {
            fail();
        }
        assertEquals(1, bsc.getQuantity());
    }

    @Test
    public void getType_coin() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {
            fail();
        }
        assertEquals(WarehouseObjectType.COIN, bsc.getType());
    }

    @Test
    public void getType_stone() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.STONE);
        } catch (SupplyException e) {
            fail();
        }
        assertEquals(WarehouseObjectType.STONE, bsc.getType());
    }

    @Test
    public void getType_servant() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {
            fail();
        }
        assertEquals(WarehouseObjectType.SERVANT, bsc.getType());
    }

    @Test
    public void getType_shield() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {
            fail();
        }
        assertEquals(WarehouseObjectType.SHIELD, bsc.getType());
    }

    @Test
    public void getType_null() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        assertNull(bsc.getType());
    }

    @Test
    public void addSupply_noEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {
            fail();
        }
        assertEquals(1, bsc.getQuantity());
    }

    @Test
    public void addSupply_nullEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        boolean exc = false;
        try {
            bsc.addSupply(null);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_faithMarkerEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        boolean exc = false;
        try {
            bsc.addSupply(WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_fullEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        boolean exc = false;
        try {
            bsc.addSupply(WarehouseObjectType.SHIELD);
            bsc.addSupply(WarehouseObjectType.SHIELD);
            bsc.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) { fail();}
        try {
            bsc.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addSupply_wrongTypeEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        boolean exc = false;
        try {
            bsc.addSupply(WarehouseObjectType.SHIELD);
            bsc.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            bsc.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeSupply_noEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.COIN);
            bsc.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) { fail();}
        try{
            bsc.removeSupply(WarehouseObjectType.COIN);
        }catch(SupplyException e) { fail();}
        assertEquals(1, bsc.getQuantity());
    }

    @Test
    public void removeSupply_emptyEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        boolean exc = false;
        try{
            bsc.removeSupply(WarehouseObjectType.COIN);
        }catch(SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void removeSupply_wrongTypeEx() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        boolean exc = false;
        try {
            bsc.addSupply(WarehouseObjectType.COIN);
            bsc.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) { fail();}
        try{
            bsc.removeSupply(WarehouseObjectType.SHIELD);
        }catch(SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void clearSupplies_notEmpty() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        try {
            bsc.addSupply(WarehouseObjectType.COIN);
            bsc.addSupply(WarehouseObjectType.COIN);
        } catch (SupplyException e) { fail();}
        SupplyContainer res = bsc.clearSupplies();
        int[] objectResExpected = {2, 0, 0, 0};
        int[] objectResActual = {  res.getQuantity(WarehouseObjectType.COIN),
                                   res.getQuantity(WarehouseObjectType.STONE),
                                   res.getQuantity(WarehouseObjectType.SERVANT),
                                   res.getQuantity(WarehouseObjectType.SHIELD)};
        assertArrayEquals(objectResExpected, objectResActual);
        assertEquals(0, bsc.getQuantity());
    }

    @Test
    public void clearSupplies_empty() {
        BoundedSupplyContainer bsc = new BoundedSupplyContainer(1);
        SupplyContainer res = bsc.clearSupplies();
        int[] objectResExpected = {0, 0, 0, 0};
        int[] objectResActual = {  res.getQuantity(WarehouseObjectType.COIN),
                                   res.getQuantity(WarehouseObjectType.STONE),
                                   res.getQuantity(WarehouseObjectType.SERVANT),
                                   res.getQuantity(WarehouseObjectType.SHIELD)};
        assertArrayEquals(objectResExpected, objectResActual);
        assertEquals(0, bsc.getQuantity());
    }

    @Test
    public void addMarble() {

    }
}