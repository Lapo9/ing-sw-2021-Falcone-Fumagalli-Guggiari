package it.polimi.ingsw.model;

import static org.junit.Assert.*;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.*;
import org.junit.Test;

//TODO
//check SupplyContainer test class first
public class PaycheckTest {

    @Test
    public void getAll() {
        Paycheck pychk = new Paycheck();
        boolean exc = false;
        try {
            pychk.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
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
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
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
            pychk.removeSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
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
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        try {
            pychk.removeSupply( WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void clearSupplies() {
        Paycheck pychk = new Paycheck();
        try {
            pychk.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.SHIELD, DepotID.COFFER);
            pychk.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> collecting = pychk.clearSupplies();
        SupplyContainer result = new SupplyContainer(collecting.first.sum(collecting.second));
        int[] objectResExpected = {2, 1, 0, 1, 0};
        int[] objectResActual = {result.getQuantity(WarehouseObjectType.COIN),
                                 result.getQuantity(WarehouseObjectType.STONE),
                                 result.getQuantity(WarehouseObjectType.SERVANT),
                                 result.getQuantity(WarehouseObjectType.SHIELD),
                                 result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(objectResExpected, objectResActual);
    }
}