package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.leader_abilities.Discount;
import it.polimi.ingsw.model.leader_abilities.Producer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LeaderCardTest {

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