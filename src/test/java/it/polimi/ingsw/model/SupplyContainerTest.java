package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SupplyContainerTest {

    @Test
    public void getQuantity_one() {
        SupplyContainer sc = new SupplyContainer(2, 0, 3, 14, 9);
        int res = sc.getQuantity(WarehouseObjectType.SHIELD);
        assertEquals(14, res);
    }

    @Test
    public void getQuantity_multiple() {
        SupplyContainer sc = new SupplyContainer(2, 0, 3, 14, 9);
        int res = sc.getQuantity(WarehouseObjectType.SHIELD, WarehouseObjectType.COIN, WarehouseObjectType.STONE);
        assertEquals(16, res);
    }

    @Test
    public void sum() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(0, 0, 6, 2, 0);

        int[] objectsExpected = {   sc1.getQuantity(WarehouseObjectType.COIN) +         sc2.getQuantity(WarehouseObjectType.COIN),
                                    sc1.getQuantity(WarehouseObjectType.STONE) +        sc2.getQuantity(WarehouseObjectType.STONE),
                                    sc1.getQuantity(WarehouseObjectType.SERVANT) +      sc2.getQuantity(WarehouseObjectType.SERVANT),
                                    sc1.getQuantity(WarehouseObjectType.SHIELD) +       sc2.getQuantity(WarehouseObjectType.SHIELD),
                                    sc1.getQuantity(WarehouseObjectType.FAITH_MARKER) + sc2.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        sc1.sum(sc2);

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                 sc1.getQuantity(WarehouseObjectType.STONE),
                                 sc1.getQuantity(WarehouseObjectType.SERVANT),
                                 sc1.getQuantity(WarehouseObjectType.SHIELD),
                                 sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void confront_equals() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(3, 7, 7, 2, 0);
        boolean res = sc1.confront(sc2);
        assertEquals(true, res);
    }

    @Test
    public void confront_notEquals() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(3, 7, 7, 0, 2);
        boolean res = sc1.confront(sc2);
        assertEquals(false, res);
    }

    @Test
    public void addSupply_one() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        try {
            sc1.addSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {3, 7, 8, 2, 0};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void addSupply_multiple() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        try {
            sc1.addSupply(WarehouseObjectType.SERVANT);
            sc1.addSupply(WarehouseObjectType.FAITH_MARKER);
            sc1.addSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {3, 7, 9, 2, 1};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void removeSupply_oneOk() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        try {
            sc1.removeSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {3, 7, 6, 2, 0};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void removeSupply_multipleOk() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        try {
            sc1.removeSupply(WarehouseObjectType.SERVANT);
            sc1.removeSupply(WarehouseObjectType.COIN);
            sc1.removeSupply(WarehouseObjectType.SERVANT);
        } catch (Exception e) {fail();}

        int[] objectsExpected = {2, 7, 5, 2, 0};

        int[] objectsActual = {  sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsExpected, objectsActual);
    }

    @Test
    public void removeSupply_oneKo() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        boolean exc = false;

        try {
            sc1.removeSupply(WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException se) {exc = true;}

        assertTrue(exc);
    }

    @Test
    public void removeSupply_multipleKo() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        boolean exc = false;

        try {
            sc1.removeSupply(WarehouseObjectType.SHIELD);
            sc1.removeSupply(WarehouseObjectType.SHIELD);
            sc1.removeSupply(WarehouseObjectType.STONE);
        } catch (Exception e) {fail();}

        try {
            sc1.removeSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException se) {exc = true;}

        assertTrue(exc);
    }

    @Test
    public void clearSupplies() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        SupplyContainer res = sc1.clearSupplies();

        int[] objectsResExpected = {3, 7, 7, 2, 0};

        int[] objectsResActual = {  res.getQuantity(WarehouseObjectType.COIN),
                                    res.getQuantity(WarehouseObjectType.STONE),
                                    res.getQuantity(WarehouseObjectType.SERVANT),
                                    res.getQuantity(WarehouseObjectType.SHIELD),
                                    res.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        int[] objectsExpected = {0, 0, 0, 0, 0};

        int[] objectsActual = { sc1.getQuantity(WarehouseObjectType.COIN),
                                sc1.getQuantity(WarehouseObjectType.STONE),
                                sc1.getQuantity(WarehouseObjectType.SERVANT),
                                sc1.getQuantity(WarehouseObjectType.SHIELD),
                                sc1.getQuantity(WarehouseObjectType.FAITH_MARKER)};

        assertArrayEquals(objectsResExpected, objectsResActual);
        assertArrayEquals(objectsExpected, objectsActual);
    }
}