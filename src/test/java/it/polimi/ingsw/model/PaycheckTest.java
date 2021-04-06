package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaycheckTest {

    @Test
    public void getAll() {
        Paycheck pychk = new Paycheck();
        boolean exc = false;
        try {
            pychk.addSupply(WarehouseObjectType.SHIELD, DepotID.LEADER1_DEPOT);
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.LEADER2_DEPOT);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = pychk.getAll();
        int[] objectResExpected = {1, 2, 0, 1, 0};
        int[] objectResActual = {result.getQuantity(WarehouseObjectType.COIN),
                                 result.getQuantity(WarehouseObjectType.STONE),
                                 result.getQuantity(WarehouseObjectType.SERVANT),
                                 result.getQuantity(WarehouseObjectType.SHIELD),
                                 result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectResExpected, objectResActual);
    }

    @Test
    public void addSupply_fromCoffer() {
        Paycheck pychk = new Paycheck();
        boolean exc = false;
        try {
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void addSupply_fromDepot() {
        Paycheck pychk = new Paycheck();
        boolean exc = false;
        try {
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.LEADER1_DEPOT);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_fromCoffer() {
        Paycheck pychk = new Paycheck();
        boolean exc = false;
        try {
            pychk.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
        } catch (SupplyException e) {fail();}
        try {
            pychk.removeSupply(DepotID.PAYCHECK_COFFER, WarehouseObjectType.SHIELD);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_fromDepot() {
        Paycheck pychk = new Paycheck();
        boolean exc = false;
        try {
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.LEADER1_DEPOT);
        } catch (SupplyException e) {fail();}
        try {
            pychk.removeSupply(DepotID.PAYCHECK_DEPOT, WarehouseObjectType.STONE);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void clearSupplies() {
        Paycheck pychk = new Paycheck();
        try {
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.LEADER1_DEPOT);
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.LEADER2_DEPOT);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = pychk.clearSupplies();
        int[] objectResExpected = {2, 1, 0, 1, 0};
        int[] objectResActual = {result.getQuantity(WarehouseObjectType.COIN),
                                 result.getQuantity(WarehouseObjectType.STONE),
                                 result.getQuantity(WarehouseObjectType.SERVANT),
                                 result.getQuantity(WarehouseObjectType.SHIELD),
                                 result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectResExpected, objectResActual);
    }
}