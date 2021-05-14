package it.polimi.ingsw.model.leaders;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.DepotID;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.leader_abilities.Discount;
import it.polimi.ingsw.model.leaders.leader_abilities.LeaderAbility;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LeaderCardTest {

    @Test
    public void leaderCard_classConstructorDiscountAbility() {
        LeaderCard lc = new LeaderCard(13);
        try {
            lc.activate();
        } catch (LeaderException e) {fail();}
        LeaderAbility ability = null;
        try {
            ability = lc.getAbility();
        } catch (LeaderException e) {fail();}
        SupplyContainer sc = null;
        try {
            sc = ability.getDiscount();
        } catch (NoSuchMethodException e) {fail();}
        Pair<SupplyContainer, ArrayList<CardsRequirement>> list = lc.getCost();
        int[] expectedResult = {2,                //winPoints
                                0, 0, 1, 0, 0,    //depot of the ability
                                0, 0, 0, 0, 0,    //supply requirement
                                1, 1, 3,          //card requirement 1
                                1, 1, 1};         //card requirement 2
        int[] actualResult = {lc.getWinPoints(),
                              sc.getQuantity(WarehouseObjectType.COIN), sc.getQuantity(WarehouseObjectType.STONE), sc.getQuantity(WarehouseObjectType.SERVANT), sc.getQuantity(WarehouseObjectType.SHIELD), sc.getQuantity(WarehouseObjectType.FAITH_MARKER),
                              list.first.getQuantity(WarehouseObjectType.COIN), list.first.getQuantity(WarehouseObjectType.STONE), list.first.getQuantity(WarehouseObjectType.SERVANT), list.first.getQuantity(WarehouseObjectType.SHIELD), list.first.getQuantity(WarehouseObjectType.FAITH_MARKER),
                              list.second.get(0).minLevel(), list.second.get(0).getNumber(), list.second.get(0).reqCard() == CardCategory.GREEN ? 1 : list.second.get(0).reqCard() == CardCategory.BLUE ? 2 : list.second.get(0).reqCard() == CardCategory.YELLOW ? 3 :4,
                              list.second.get(1).minLevel(), list.second.get(1).getNumber(), list.second.get(1).reqCard() == CardCategory.GREEN ? 1 : list.second.get(1).reqCard() == CardCategory.BLUE ? 2 : list.second.get(1).reqCard() == CardCategory.YELLOW ? 3 :4};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void leaderCard_classConstructorMarketAbility() {
        LeaderCard lc = new LeaderCard(1);
        try {
            lc.activate();
        } catch (LeaderException e) {fail();}
        LeaderAbility ability = null;
        try {
            ability = lc.getAbility();
        } catch (LeaderException e) {fail();}
        WarehouseObjectType wot = null;
        try {
            wot = ability.transformWhiteMarble();
        } catch (NoSuchMethodException e) {fail();}
        Pair<SupplyContainer, ArrayList<CardsRequirement>> list = lc.getCost();
        int[] expectedResult = {5,                //winPoints
                                3,                //depot of the ability
                                0, 0, 0, 0, 0,    //supply requirement
                                1, 2, 3,          //card requirement 1
                                1, 1, 2};         //card requirement 2
        int[] actualResult = {lc.getWinPoints(),
                              wot == WarehouseObjectType.COIN ? 0 : wot == WarehouseObjectType.STONE ? 2 : wot == WarehouseObjectType.SERVANT ? 3 : wot ==WarehouseObjectType.SHIELD ? 4 : 5,
                              list.first.getQuantity(WarehouseObjectType.COIN), list.first.getQuantity(WarehouseObjectType.STONE), list.first.getQuantity(WarehouseObjectType.SERVANT), list.first.getQuantity(WarehouseObjectType.SHIELD), list.first.getQuantity(WarehouseObjectType.FAITH_MARKER),
                              list.second.get(0).minLevel(), list.second.get(0).getNumber(), list.second.get(0).reqCard() == CardCategory.GREEN ? 1 : list.second.get(0).reqCard() == CardCategory.BLUE ? 2 : list.second.get(0).reqCard() == CardCategory.YELLOW ? 3 :4,
                              list.second.get(1).minLevel(), list.second.get(1).getNumber(), list.second.get(1).reqCard() == CardCategory.GREEN ? 1 : list.second.get(1).reqCard() == CardCategory.BLUE ? 2 : list.second.get(1).reqCard() == CardCategory.YELLOW ? 3 :4};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void leaderCard_classConstructorDepotAbility() {
        LeaderCard lc = new LeaderCard(8);
        try {
            lc.activate();
        } catch (LeaderException e) {fail();}
        LeaderAbility ability = null;
        try {
            ability = lc.getAbility();
        } catch (LeaderException e) {fail();}
        try {
            ability.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE3);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {fail();}
        Pair<WarehouseObjectType, Integer> pair = null;
        try {
            pair = ability.getDepotInfo();
        } catch (NoSuchMethodException e) {fail();}
        Pair<SupplyContainer, ArrayList<CardsRequirement>> list = lc.getCost();
        int[] expectedResult = {3,                //winPoints
                                0, 1,             //depot of the ability
                                0, 0, 0, 5, 0};   //supply requirement
        int[] actualResult = {lc.getWinPoints(),
                              pair.first == WarehouseObjectType.COIN ? 0 : pair.first == WarehouseObjectType.STONE ? 2 : pair.first == WarehouseObjectType.SERVANT ? 3 : pair.first ==WarehouseObjectType.SHIELD ? 4 : 5, pair.second,
                              list.first.getQuantity(WarehouseObjectType.COIN), list.first.getQuantity(WarehouseObjectType.STONE), list.first.getQuantity(WarehouseObjectType.SERVANT), list.first.getQuantity(WarehouseObjectType.SHIELD), list.first.getQuantity(WarehouseObjectType.FAITH_MARKER)};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void leaderCard_classConstructorProducerAbility() {
        LeaderCard lc = new LeaderCard(10);
        try {
            lc.activate();
        } catch (LeaderException e) {fail();}
        LeaderAbility ability = null;
        try {
            ability = lc.getAbility();
        } catch (LeaderException e) {fail();}
        ArrayList<Integer> status = lc.getStatus();
        Pair<SupplyContainer, ArrayList<CardsRequirement>> list = lc.getCost();
        int[] expectedResult = {4,                //winPoints
                                1,                //depot of the ability
                                0, 0, 0, 0, 0,    //supply requirement
                                2, 1, 2};         //card requirement 1
        int[] actualResult = {lc.getWinPoints(),
                              status.get(2),
                              list.first.getQuantity(WarehouseObjectType.COIN), list.first.getQuantity(WarehouseObjectType.STONE), list.first.getQuantity(WarehouseObjectType.SERVANT), list.first.getQuantity(WarehouseObjectType.SHIELD), list.first.getQuantity(WarehouseObjectType.FAITH_MARKER),
                              list.second.get(0).minLevel(), list.second.get(0).getNumber(), list.second.get(0).reqCard() == CardCategory.GREEN ? 1 : list.second.get(0).reqCard() == CardCategory.BLUE ? 2 : list.second.get(0).reqCard() == CardCategory.YELLOW ? 3 : list.second.get(0).reqCard() == CardCategory.VIOLET ? 4 : 5};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void activate_no_exc() {
        SupplyContainer reqSupplyContainer = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard.add(cs1);
        reqDevelopmentCard.add(cs2);
        LeaderAbility la = new Discount(WarehouseObjectType.COIN);

        LeaderCard lc = new LeaderCard(0, reqSupplyContainer, reqDevelopmentCard, la, 5);
        boolean exc = false;
        try {
            lc.activate();
        } catch (LeaderException e) {
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void activate_exc() {
        SupplyContainer reqSupplyContainer = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard.add(cs1);
        reqDevelopmentCard.add(cs2);
        LeaderAbility la = new Discount(WarehouseObjectType.COIN);

        LeaderCard lc = new LeaderCard(0, reqSupplyContainer, reqDevelopmentCard, la, 5);
        boolean exc = false;
        try {
            lc.activate();
        } catch (LeaderException e) {fail();}
        //another activate()
        try {
            lc.activate();
        } catch (LeaderException e) {exc = true;}
        assertTrue(exc);
    }

    @Test
    public void discard_no_exc() {
        SupplyContainer reqSupplyContainer = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard.add(cs1);
        reqDevelopmentCard.add(cs2);
        LeaderAbility la = new Discount(WarehouseObjectType.COIN);
        LeaderCard lc = new LeaderCard(0, reqSupplyContainer, reqDevelopmentCard, la, 5);
        boolean exc = false;
        try {
            lc.discard();
        } catch (LeaderException e) {
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void discard_exc() {
        SupplyContainer reqSupplyContainer = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard.add(cs1);
        reqDevelopmentCard.add(cs2);
        LeaderAbility la = new Discount(WarehouseObjectType.COIN);
        LeaderCard lc = new LeaderCard(0, reqSupplyContainer, reqDevelopmentCard, la, 5);
        boolean exc = false;
        try {
            lc.activate();
        } catch (LeaderException e) {fail();}
        try {
            lc.discard();
        } catch (LeaderException e) {
            exc = true;
        }

        assertTrue(exc);
    }

    @Test
    public void getWinPointsTest() {
        SupplyContainer reqSupplyContainer = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard.add(cs1);
        reqDevelopmentCard.add(cs2);
        LeaderAbility la = new Discount(WarehouseObjectType.COIN);
        LeaderCard lc = new LeaderCard(0, reqSupplyContainer, reqDevelopmentCard, la, 5);

        int expectedWP = 5;
        int actualWP = lc.getWinPoints();

        assertEquals(expectedWP, actualWP);
    }

    @Test
    public void getStatusTest_no_exc() {
        SupplyContainer reqSupplyContainer = new SupplyContainer(1, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard.add(cs1);
        reqDevelopmentCard.add(cs2);
        //leaderAbility Producer
        LeaderAbility la = new Producer(new SupplyContainer(1, 0, 0, 0, 0));

        try {
            la.swapProduction(WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException e) {
            fail();
        }

        try {
            la.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {
            fail();
        }

        //LeaderCard creation
        LeaderCard lc = new LeaderCard(0, reqSupplyContainer, reqDevelopmentCard, la, 5);

        try {
            lc.activate();
        } catch (LeaderException e) {fail();}

        int [] expectedStatus ={
                0, //id
                1, //active
                0, //fixedInput
                4, //fixedOutput -> FAITH_MARKER
                1, //mutableOutput -> SERVANT
                1, 0 ,0 ,0 ,0, //currentSupply
                0, 0, 0, 0, 0}; //for Depot ability

        ArrayList<Integer> status = lc.getStatus();
        int [] actualStatus = {
                status.get(0),
                status.get(1),
                status.get(2),
                status.get(3),
                status.get(4),
                status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                status.get(10), status.get(11), status.get(12), status.get(13), status.get(14)};

        assertArrayEquals(expectedStatus, actualStatus);
    }

    @Test
    public void getStatusTest_exc() {
        SupplyContainer reqSupplyContainer = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard.add(cs1);
        reqDevelopmentCard.add(cs2);
        LeaderAbility la = new Producer(new SupplyContainer(1, 0, 0, 0, 0));

        LeaderCard lc = new LeaderCard(0, reqSupplyContainer, reqDevelopmentCard, la, 5);

        int [] expectedStatus ={
                0, //id
                0, //active/discarded/nothing
                0, //fixedInput
                0, //fixedOutput
                0, //mutableOutput
                0, 0 ,0 ,0 ,0, //currentSupply
                0, 0, 0, 0, 0}; //for Depot ability

        ArrayList<Integer> status = lc.getStatus();
        int [] actualStatus = {
                status.get(0),
                status.get(1),
                status.get(2),
                status.get(3),
                status.get(4),
                status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                status.get(10), status.get(11), status.get(12), status.get(13), status.get(14)};

        assertArrayEquals(expectedStatus, actualStatus);
    }

}