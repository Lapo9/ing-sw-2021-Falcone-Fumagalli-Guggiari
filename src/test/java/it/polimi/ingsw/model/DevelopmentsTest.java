package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.DevelopmentException;
import it.polimi.ingsw.exceptions.SupplyException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DevelopmentsTest {

    @Test
    public void produce() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 2, 7, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 2, 0, 2)), new SupplyContainer(0, 5, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlpmt.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            dvlpmt.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        SupplyContainer result = new SupplyContainer(dvlpmt.produce(false, false, true));
        int[] expectedResult = {0, 0, 2, 0, 2};
        int[] actualResult = {result.getQuantity(WarehouseObjectType.COIN),
                              result.getQuantity(WarehouseObjectType.STONE),
                              result.getQuantity(WarehouseObjectType.SERVANT),
                              result.getQuantity(WarehouseObjectType.SHIELD),
                              result.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void checkProduction() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 2, 7, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 2, 0, 2)), new SupplyContainer(0, 5, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlpmt.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
            dvlpmt.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.SHIELD, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        boolean result = true;
        try {
            dvlpmt.checkProduction(false, false, true);
        } catch (SupplyException e) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void addSupply() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlpmt.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
            dvlpmt.addSupply(DepotID.DEVELOPMENT2, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
            dvlpmt.addSupply(DepotID.DEVELOPMENT2, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        Pair<SupplyContainer, SupplyContainer> result = new Pair<>(dvlpmt.clearSupplies(DepotID.DEVELOPMENT2));
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
    public void removeSupply() {
        Developments dvlpmt = new Developments();
        boolean exc = false;
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlpmt.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        try {
            dvlpmt.removeSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void additionAllowed() {
        Developments dvlpmt = new Developments();
            try {
                dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
                dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
                dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertTrue(dvlpmt.additionAllowed(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE2));
    }

    @Test
    public void removalAllowed() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlpmt.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
        } catch (SupplyException e) {fail();}
        assertTrue(dvlpmt.removalAllowed(DepotID.DEVELOPMENT3, WarehouseObjectType.COIN, DepotID.WAREHOUSE2));
    }

    @Test
    public void clearSupplies() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlpmt.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
            dvlpmt.addSupply(DepotID.DEVELOPMENT2, WarehouseObjectType.STONE, DepotID.WAREHOUSE2);
            dvlpmt.addSupply(DepotID.DEVELOPMENT2, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
            dvlpmt.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException e) {fail();}
        assertNull(dvlpmt.clearSupplies());
    }

    @Test
    public void addCardToSpace() {
        Developments dvlpmt = new Developments();
        boolean exc = false;
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        } catch (DevelopmentException e) {
            exc = true;
        }
        assertFalse(exc);
    }

    @Test
    public void buyableLevels() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(dvlpmt.buyableLevels());
        int[] expectedResult = {2, 3, 1};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getCardsTypes() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 2, 7, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 2, 0, 2)), new SupplyContainer(0, 5, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        ArrayList<Pair<CardCategory, Integer>> result = new ArrayList<>(dvlpmt.getCardsTypes());
        int[] expectedResult = {4, 1, 2, 1, 3, 2, 3, 1, 1, 2};
        int[] actualResult = {result.get(0).first == CardCategory.YELLOW ? 1 : result.get(0).first == CardCategory.GREEN ? 2: result.get(0).first == CardCategory.VIOLET ? 3: 4,
                              result.get(0).second,
                              result.get(1).first == CardCategory.YELLOW ? 1 : result.get(1).first == CardCategory.GREEN ? 2: result.get(1).first == CardCategory.VIOLET ? 3: 4,
                              result.get(1).second,
                              result.get(2).first == CardCategory.YELLOW ? 1 : result.get(2).first == CardCategory.GREEN ? 2: result.get(2).first == CardCategory.VIOLET ? 3: 4,
                              result.get(2).second,
                              result.get(3).first == CardCategory.YELLOW ? 1 : result.get(3).first == CardCategory.GREEN ? 2: result.get(3).first == CardCategory.VIOLET ? 3: 4,
                              result.get(3).second,
                              result.get(4).first == CardCategory.YELLOW ? 1 : result.get(4).first == CardCategory.GREEN ? 2: result.get(4).first == CardCategory.VIOLET ? 3: 4,
                              result.get(4).second};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void getWinPoints() {
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(0, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(0, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(0, 2, 7, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 2, 0, 2)), new SupplyContainer(0, 5, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        assertEquals(21, dvlpmt.getWinPoints());
    }

    @Test
    public void getStatus(){
        Developments dvlpmt = new Developments();
        try {
            dvlpmt.addCardToSpace(0, new DevelopmentCard(45, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(34, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));
            dvlpmt.addCardToSpace(1, new DevelopmentCard(11, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(9, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(25, 2, 7, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 2, 0, 2)), new SupplyContainer(0, 5, 0, 0, 0)));
            dvlpmt.addCardToSpace(2, new DevelopmentCard(3, 3, 9, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 2, 0, 0), new SupplyContainer(0, 0, 0, 3, 2)), new SupplyContainer(6, 0, 0, 0, 0)));
        } catch (DevelopmentException e) {fail();}
        try {
            dvlpmt.addSupply(DepotID.DEVELOPMENT1, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
            dvlpmt.addSupply(DepotID.DEVELOPMENT2, WarehouseObjectType.COIN, DepotID.WAREHOUSE2);
            dvlpmt.addSupply(DepotID.DEVELOPMENT3, WarehouseObjectType.SERVANT, DepotID.WAREHOUSE3);
        } catch (SupplyException e) {fail();}
        ArrayList<Integer> result = new ArrayList<>(dvlpmt.getStatus());
        int[] expectedResult = {45,
                                0,
                                0,
                                0, 1, 0, 0, 0,
                                0, 0, 0, 1, 0,
                                0, 1, 0, 0, 0,
                                34,
                                11,
                                0,
                                1, 0, 0, 0, 0,
                                0, 0, 0, 0, 2,
                                1, 0, 0, 0, 0,
                                9,
                                25,
                                3,
                                0, 2, 0, 0, 0,
                                0, 0, 3, 0, 2,
                                0, 1, 0, 0, 0};
        int[] actualResult = {result.get(0),
                              result.get(1),
                              result.get(2),
                              result.get(3), result.get(4), result.get(5), result.get(6), result.get(7),
                              result.get(8), result.get(9), result.get(10), result.get(11), result.get(12),
                              result.get(13), result.get(14), result.get(15), result.get(16), result.get(17),
                              result.get(18),
                              result.get(19),
                              result.get(20),
                              result.get(21), result.get(22), result.get(23), result.get(24), result.get(25),
                              result.get(26), result.get(27), result.get(28), result.get(29), result.get(30),
                              result.get(31), result.get(32), result.get(33), result.get(34), result.get(35),
                              result.get(36),
                              result.get(37),
                              result.get(38),
                              result.get(39), result.get(40), result.get(41), result.get(42), result.get(43),
                              result.get(44), result.get(45), result.get(46), result.get(47), result.get(48),
                              result.get(49), result.get(50), result.get(51), result.get(52), result.get(53)};
        assertArrayEquals(expectedResult, actualResult);
    }
}