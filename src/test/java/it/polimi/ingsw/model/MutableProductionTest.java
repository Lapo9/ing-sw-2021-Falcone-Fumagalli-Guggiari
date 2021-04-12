package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutableProductionTest {

    @Test
    public void produce_fixedInputOutput() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(0, 2, 0, 0, 0), new SupplyContainer(2, 0, 0, 0, 0), 3, 3);
        try {
            mtblprd.swapInput(0, WarehouseObjectType.SHIELD);
            mtblprd.swapOutput(0, WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException e) {fail();}
        try {
            mtblprd.currentSupply.addSupply(WarehouseObjectType.STONE);
            mtblprd.currentSupply.addSupply(WarehouseObjectType.STONE);
            mtblprd.currentSupply.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        SupplyContainer production = new SupplyContainer(mtblprd.produce());
        int[] expectedResult = {2, 0, 0, 0, 1};
        int[] actualResult = {production.getQuantity(WarehouseObjectType.COIN),
                              production.getQuantity(WarehouseObjectType.STONE),
                              production.getQuantity(WarehouseObjectType.SERVANT),
                              production.getQuantity(WarehouseObjectType.SHIELD),
                              production.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void produce_noFixedInputOutput() {
        MutableProduction mtblprd = new MutableProduction(3, 3);
        try {
            mtblprd.swapInput(0, WarehouseObjectType.COIN);
            mtblprd.swapInput(1, WarehouseObjectType.COIN);
            mtblprd.swapInput(2, WarehouseObjectType.SHIELD);
            mtblprd.swapOutput(0, WarehouseObjectType.FAITH_MARKER);
            mtblprd.swapOutput(1, WarehouseObjectType.FAITH_MARKER);
            mtblprd.swapOutput(2, WarehouseObjectType.STONE);
        } catch (SupplyException e) {fail();}
        try {
            mtblprd.currentSupply.addSupply(WarehouseObjectType.COIN);
            mtblprd.currentSupply.addSupply(WarehouseObjectType.COIN);
            mtblprd.currentSupply.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        SupplyContainer production = new SupplyContainer(mtblprd.produce());
        int[] expectedResult = {0, 1, 0, 0, 2};
        int[] actualResult = {production.getQuantity(WarehouseObjectType.COIN),
                              production.getQuantity(WarehouseObjectType.STONE),
                              production.getQuantity(WarehouseObjectType.SERVANT),
                              production.getQuantity(WarehouseObjectType.SHIELD),
                              production.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void check_noEx() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(1, 0, 0, 1, 0), new SupplyContainer(0, 2, 0, 0, 1), 3, 3);
        boolean exc = false;
        try {
            mtblprd.swapInput(0, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            mtblprd.currentSupply.addSupply(WarehouseObjectType.COIN);
            mtblprd.currentSupply.addSupply(WarehouseObjectType.SHIELD);
            mtblprd.currentSupply.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            mtblprd.check();
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void check_ex() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(1, 0, 1, 0, 0), new SupplyContainer(0, 3, 0, 0, 0), 3, 3);
        boolean exc = false;
        try {
            mtblprd.swapInput(0, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            mtblprd.currentSupply.addSupply(WarehouseObjectType.COIN);
            mtblprd.currentSupply.addSupply(WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {fail();}
        try {
            mtblprd.check();
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void swapInput_noEx() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 2, 0, 0, 0), 2, 2);
        boolean exc = false;
        try {
            mtblprd.swapInput(0, WarehouseObjectType.COIN);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void swapInput_faithMarkerEx() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 1, 1, 0), 2, 2);
        boolean exc = false;
        try {
            mtblprd.swapInput(0, WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void swapInput_noTypeEx() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 1, 1, 0), 2, 2);
        boolean exc = false;
        try {
            mtblprd.swapInput(0, WarehouseObjectType.NO_TYPE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void swapOutput_noEx() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(1, 2, 0, 0, 0), new SupplyContainer(0, 0, 1, 1, 0), 3, 3);
        boolean exc = false;
        try {
            mtblprd.swapOutput(0, WarehouseObjectType.SERVANT);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void swapOutput_noTypeEx() {
        MutableProduction mtblprd = new MutableProduction(new SupplyContainer(1, 1, 0, 1, 0), new SupplyContainer(0, 0, 2, 0, 0), 3, 3);
        boolean exc = false;
        try {
            mtblprd.swapOutput(0, WarehouseObjectType.NO_TYPE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }
}