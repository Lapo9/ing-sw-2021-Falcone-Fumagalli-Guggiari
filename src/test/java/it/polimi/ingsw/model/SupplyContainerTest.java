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
    public void getQuantity_all() {
        SupplyContainer sc = new SupplyContainer(6, 2, 0, 1, 9);
        int res = sc.getQuantity();
        assertEquals(18, res);
    }

    @Test
    public void getType_emptyContainer(){
        SupplyContainer sc = new SupplyContainer();
        WarehouseObjectType result = null;
        try {
            result = sc.getType();
        } catch (SupplyException e) {fail();}
        assertEquals(WarehouseObjectType.NO_TYPE, result);
    }

    @Test
    public void getType_noException(){
        SupplyContainer sc = new SupplyContainer(0, 2, 0, 0, 0);
        WarehouseObjectType result = null;
        try {
            result = sc.getType();
        } catch (SupplyException e) {fail();}
        assertEquals(WarehouseObjectType.STONE, result);
    }

    @Test
    public void getType_moreThanOneTypeEx(){
        SupplyContainer sc = new SupplyContainer(0, 2, 0, 1, 0);
        WarehouseObjectType result = null;
        boolean exc = false;
        try {
            result = sc.getType();
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    //TODO
    //Test setAcceptCheck and setRemoveCheck

    //TODO
    //There is something wrong in here
    @Test
    public void sum() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(1, 0, 6, 2, 0);

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
    public void equals_true() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(3, 7, 7, 2, 0);
        boolean res = sc1.equals(sc2);
        assertTrue(res);
    }

    @Test
    public void equals_false() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);
        SupplyContainer sc2 = new SupplyContainer(3, 7, 7, 0, 2);
        boolean res = sc1.equals(sc2);
        assertFalse(res);
    }

    //TODO
    //We need to check addSupply(wot, from) and removeSupply(wot, to)
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

    //TODO
    //We need to check additionAllowed and removalAllowed

    @Test
    public void clearSupplies() {
        SupplyContainer sc1 = new SupplyContainer(3, 7, 7, 2, 0);

        SupplyContainer res = sc1.clearSupplies().first;

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