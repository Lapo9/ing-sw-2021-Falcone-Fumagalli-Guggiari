package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DevelopmentCardTest {

    @Test
    public void produce_no_exc() {
        //creation of the test card
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);
        try {
            dc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            Assert.fail();
        }
        SupplyContainer result = dc.produce();
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
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);

        try {
            dc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            Assert.fail();
        }
        boolean exc = false;
        try{
            dc.check();
        } catch(SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void check_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);

        try {
            //add to input one coin, useless for the production
            dc.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e){
            Assert.fail();
        }
        boolean exc = false;
        try{
            dc.check();
        } catch(SupplyException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void testGetCost() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);

        int [] expectedCost = {0, 4, 0, 0, 0};
        int [] actualCost = {dc.getCost().getQuantity(WarehouseObjectType.COIN),
                dc.getCost().getQuantity(WarehouseObjectType.STONE),
                dc.getCost().getQuantity(WarehouseObjectType.SERVANT),
                dc.getCost().getQuantity(WarehouseObjectType.SHIELD),
                dc.getCost().getQuantity(WarehouseObjectType.FAITH_MARKER)
        };
        assertArrayEquals(expectedCost, actualCost);
    }

    @Test
    public void testGetLevel() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);

        int expectedLevel = 2;
        int actualLevel = dc.getLevel();

        Assert.assertEquals(expectedLevel, actualLevel);
    }

    @Test
    public void testGetCategory() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);

        CardCategory expectedCategory = CardCategory.YELLOW;
        CardCategory actualCategory = dc.getCategory();

        Assert.assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void addSupply_no_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);
        try {
            dc.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
            dc.addSupply(WarehouseObjectType.SERVANT, DepotID.COFFER);
        } catch (SupplyException e){ fail();}
        //without clearSupplies i can't see the quantity of resources in a DevelopmentCard
        //the following passages return the currentSupply of prod
        Pair<SupplyContainer, SupplyContainer> collector = dc.clearSupplies();
        SupplyContainer result = new SupplyContainer(collector.first.sum(collector.second));
        int [] expectedObject = {0, 1, 1, 0, 0};
        int [] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };

        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void removeSupply_no_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);
        //i have to add before trying to remove something from the currentSupply
        try {
            dc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1); //add to currentSupply from WAREHOUSE1
            dc.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
            dc.addSupply(WarehouseObjectType.COIN, DepotID.COFFER);
        } catch (SupplyException e) {
            fail();
        }
        try {
            dc.removeSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1); //remove from currentSupply from WAREHOUSE1
        } catch (SupplyException e){fail();}
        Pair<SupplyContainer, SupplyContainer> collector = dc.clearSupplies();
        SupplyContainer result = new SupplyContainer(collector.first.sum(collector.second));
        int [] expectedObject = {2, 0, 0, 0, 0};
        int [] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };

        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void additionAllowed_no_exc() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);
        //FIXME same doubt of ProductionTest
        assertTrue(dc.additionAllowed(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1));
    }

    @Test
    public void removalAllowed() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);
        try {
            dc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e){fail();}

        assertTrue(dc.removalAllowed(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1));
    }

    @Test
    public void clearSupplies() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);
        try {
            dc.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
            dc.addSupply(WarehouseObjectType.SERVANT, DepotID.COFFER);
        } catch (SupplyException e){ fail();}
        Pair<SupplyContainer, SupplyContainer> collector = dc.clearSupplies();
        SupplyContainer result = new SupplyContainer(collector.first.sum(collector.second));
        int [] expectedObject = {0, 1, 1, 0, 0};
        int [] actualObject = {result.getQuantity(WarehouseObjectType.COIN),
                result.getQuantity(WarehouseObjectType.STONE),
                result.getQuantity(WarehouseObjectType.SERVANT),
                result.getQuantity(WarehouseObjectType.SHIELD),
                result.getQuantity(WarehouseObjectType.FAITH_MARKER)
        };

        assertArrayEquals(expectedObject, actualObject);
    }

    @Test
    public void getWinPoints() {
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 2, 5, CardCategory.YELLOW, prod, cost);

        int expectedWP = 5;
        int actualWP = dc.getWinPoints();

        assertEquals(expectedWP, actualWP);
    }

    @Test
    public void getStatus() {
        DevelopmentCard dc = new DevelopmentCard(0, 2, 8, CardCategory.GREEN, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 2, 1)), new SupplyContainer(3, 0, 0, 3, 0));
        try {
            dc.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e){ fail();}
        ArrayList<Integer> result = new ArrayList<>(dc.getStatus());
        int[] expectedResult = {0,
                                1, 0, 0, 0, 0,
                                0, 0, 2, 0, 1,
                                1, 0, 0, 0, 0};
        int[] actualResult = {result.get(0),
                              result.get(1), result.get(2), result.get(3), result.get(4), result.get(5),
                              result.get(6), result.get(7), result.get(8), result.get(9), result.get(10),
                              result.get(11), result.get(12), result.get(13), result.get(14), result.get(15)};
        assertArrayEquals(expectedResult, actualResult);
    }
}