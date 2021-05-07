package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.DevelopmentException;
import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DevelopmentSpaceTest {

    @Test
    public void addCard_noEx() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        boolean exc = false;
        try {
            dvlspc.addCard(new DevelopmentCard(0,1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addCard(new DevelopmentCard(0, 3, 12, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 1, 3, 0, 0)), new SupplyContainer(0, 4, 4, 0, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(0, 3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addCard(new DevelopmentCard(0, 3, 12, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 1, 3, 0, 0)), new SupplyContainer(0, 4, 4, 0, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertEquals(2, dvlspc.buyableLevel());
    }

    @Test
    public void buyableLevel_three() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertEquals(3, dvlspc.buyableLevel());
    }

    @Test
    public void getCardsTypes() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(0, 3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        ArrayList<Pair<CardCategory, Integer>> result = new ArrayList<>(dvlspc.getCardsTypes());
        int[] expectedResult = {4, 1, 3, 2, 1, 3};
        int[] actualResult = {result.get(0).first == CardCategory.YELLOW ? 1 : result.get(0).first == CardCategory.GREEN ? 2: result.get(0).first == CardCategory.VIOLET ? 3: 4,
                              result.get(0).second,
                              result.get(1).first == CardCategory.YELLOW ? 1 : result.get(1).first == CardCategory.GREEN ? 2: result.get(1).first == CardCategory.VIOLET ? 3: 4,
                              result.get(1).second,
                              result.get(2).first == CardCategory.YELLOW ? 1 : result.get(2).first == CardCategory.GREEN ? 2: result.get(2).first == CardCategory.VIOLET ? 3: 4,
                              result.get(2).second};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void produce() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertTrue(dvlspc.additionAllowed(WarehouseObjectType.COIN, DepotID.WAREHOUSE2));
    }

    @Test
    public void removalAllowed() {
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
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
            dvlspc.addCard(new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(0, 3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertEquals(16, dvlspc.getWinPoints());
    }

    @Test
    public void getStatus3Cards(){
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(3, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(7, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(4, 3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.SHIELD, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(dvlspc.getStatus());
        int[] expectedResult = {3,
                                7,
                                4,
                                0, 0, 2, 0, 0,
                                0, 3, 0, 0, 2,
                                0, 0, 1, 0, 0};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2),
                              result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                              result.get(8), result.get(9), result.get(10), result.get(11), result.get(12),
                              result.get(13), result.get(14), result.get(15), result.get(16), result.get(17)};
        assertArrayEquals(expectedResult, actualResult);
    }


    @Test
    public void getStatus2Cards(){
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(14, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlspc.addCard(new DevelopmentCard(9, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(dvlspc.getStatus());
        int[] expectedResult = {
                14,
                9,
                0,
                1, 0, 0, 0, 0,
                0, 0, 0, 0, 2,
                1, 0, 0, 0, 0};
        int[] actualResult = {result.get(0),
                result.get(1),
                result.get(2),
                result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                result.get(8), result.get(9), result.get(10), result.get(11), result.get(12),
                result.get(13), result.get(14), result.get(15), result.get(16), result.get(17)};
        assertArrayEquals(expectedResult, actualResult);
    }



    @Test
    public void getStatus1Card(){
        DevelopmentSpace dvlspc = new DevelopmentSpace();
        try {
            dvlspc.addCard(new DevelopmentCard(9, 1, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlspc.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(dvlspc.getStatus());
        int[] expectedResult = {
                9,
                0,
                0,
                1, 0, 0, 0, 0,
                0, 0, 0, 0, 2,
                1, 0, 0, 0, 0};
        int[] actualResult = {result.get(0),
                result.get(1),
                result.get(2),
                result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                result.get(8), result.get(9), result.get(10), result.get(11), result.get(12),
                result.get(13), result.get(14), result.get(15), result.get(16), result.get(17)};
        assertArrayEquals(expectedResult, actualResult);
    }



    @Test
    public void getStatusEmpty(){
        DevelopmentSpace dvlspc = new DevelopmentSpace();

        ArrayList<Integer> result = new ArrayList<>(dvlspc.getStatus());
        int[] expectedResult = {
                0,
                0,
                0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0};
        int[] actualResult = {result.get(0),
                result.get(1),
                result.get(2),
                result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                result.get(8), result.get(9), result.get(10), result.get(11), result.get(12),
                result.get(13), result.get(14), result.get(15), result.get(16), result.get(17)};
        assertArrayEquals(expectedResult, actualResult);
    }
}