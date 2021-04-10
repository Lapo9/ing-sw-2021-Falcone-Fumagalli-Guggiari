package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DevelopmentCardTest {

    @Test
    public void produce_no_exc() {
        //creation of the test card
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            Assert.fail();
        }
        SupplyContainer result = sc.produce();
        int [] expectedOutput = {0, 0, 0, 0, 2};
        int [] actualOutput = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void check_no_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);

        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            Assert.fail();
        }
        boolean exc = false;
        try{
            sc.check();
        } catch(SupplyException e) {
            exc = true;
        }
        Assert.assertFalse(exc);
    }

    @Test
    public void check_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);

        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            Assert.fail();
        }
        boolean exc = false;
        try{
            sc.check();
        } catch(SupplyException e) {
            exc = true;
        }
        Assert.assertTrue(exc);
    }


    @Test
    public void testGetCost() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);

        int [] expectedCost = {0, 4, 0, 0, 0};
        int [] actualCost = {sc.getCost().getQuantity(WarehouseObjectType.COIN),
                sc.getCost().getQuantity(WarehouseObjectType.STONE),
                sc.getCost().getQuantity(WarehouseObjectType.SERVANT),
                sc.getCost().getQuantity(WarehouseObjectType.SHIELD),
                sc.getCost().getQuantity(WarehouseObjectType.FAITH_MARKER)
        };
        assertArrayEquals(expectedCost, actualCost);
    }

    @Test
    public void testGetLevel() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);

        int expectedLevel = 2;
        int actualLevel = sc.getLevel();

        Assert.assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testGetCategory() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);

        CardCategory expectedCategory = CardCategory.YELLOW;
        CardCategory actualCategory = sc.getCategory();

        Assert.assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void addSupply_no_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);

        boolean exc = false;
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply_no_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard sc = new DevelopmentCard(2, 5, CardCategory.YELLOW, prod, cost);

        //i have to add before trying to remove something from the currentSupply
        try {
            sc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1); //add to currentSupply from WAREHOUSE1
        } catch (SupplyException e) {
            fail();
        }
        boolean exc = false;
        try {
            sc.removeSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1); //remove from currentSupply from WAREHOUSE1
        } catch (SupplyException e){
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void additionAllowed_no_exc() {

    }

    @Test
    public void removalAllowed() {
    }

    @Test
    public void clearSupplies() {
    }

    @Test
    public void getWinPoints() {
    }
}