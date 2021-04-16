package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.DevelopmentException;
import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DevelopmentSpaceTest {

    @Test
    public void addCard_noEx() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        boolean exc = false;
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void addCard_wrongLevelEx() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        boolean exc = false;
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addCard(new DevelopmentCard(3, 12, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 1, 3, 0, 0)), new SupplyContainer(0, 4, 4, 0, 0)));
        } catch (DevelopmentException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void addCard_fullEx() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        boolean exc = false;
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addCard(new DevelopmentCard(3, 12, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 1, 3, 0, 0)), new SupplyContainer(0, 4, 4, 0, 0)));
        } catch (DevelopmentException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void buyableLevel_one() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        assertEquals(1, dvlspc.buyableLevel());
    }

    @Test
    public void buyableLevel_two() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertEquals(2, dvlspc.buyableLevel());
    }

    @Test
    public void buyableLevel_three() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertEquals(3, dvlspc.buyableLevel());
    }

    @Test
    public void getCardsTypes() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        ArrayList<Pair<CardCategory, Integer>> result = new ArrayList<>(dvlspc.getCardsTypes());
        int[] expectedResult = {1, 2, 3};
        int[] actualResult = {result.get(0).second,
                              result.get(1).second,
                              result.get(2).second};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void produce() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            dvlspc.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = new SupplyContainer(dvlspc.produce());
        int[] expectedResult = {2, 0, 0, 0, 1};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void checkProduction() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            dvlspc.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        boolean result = true;
        try {
            dvlspc.checkProduction();
        } catch (SupplyException e) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void addSupply() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        boolean exc = false;
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void removeSupply() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        boolean exc = false;
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        try {
            dvlspc.removeSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void additionAllowed() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertTrue(dvlspc.additionAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE2));
    }

    @Test
    public void removalAllowed() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        assertTrue(dvlspc.removalAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE2));
    }

    @Test
    public void clearSupplies() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE3);
            dvlspc.addSupply(WarehouseObjectType.SERVANT, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = new Pair<>(dvlspc.clearSupplies());
        SupplyContainer resultSC = result.first.sum(result.second);
        int[] expectedResult = {0, 1, 1, 0, 0};
        int[] actualResult = {resultSC.getQuantity(WarehouseObjectType.COIN),
                              resultSC.getQuantity(WarehouseObjectType.STONE),
                              resultSC.getQuantity(WarehouseObjectType.SERVANT),
                              resultSC.getQuantity(WarehouseObjectType.SHIELD),
                              resultSC.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getWinPoints() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertEquals(16, dvlspc.getWinPoints());
    }
}