package it.polimi.ingsw.model;

import it.polimi.ingsw.model.development.DevelopmentCard;
import it.polimi.ingsw.model.development.Developments;
import it.polimi.ingsw.model.development.Production;
import it.polimi.ingsw.model.exceptions.DevelopmentException;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leader_abilities.Depot;
import it.polimi.ingsw.model.leader_abilities.Discount;
import it.polimi.ingsw.model.leader_abilities.LeaderAbility;
import it.polimi.ingsw.model.leader_abilities.Producer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LeadersSpaceTest {

    @Test
    public void addLeader_no_exc() {
        LeadersSpace ls = new LeadersSpace();

        //create the first LeaderCard
        SupplyContainer reqSupplyContainer1 = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        CardsRequirement cs11 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs12 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard1.add(cs11);
        reqDevelopmentCard1.add(cs12);
        LeaderAbility la1 = new Discount(WarehouseObjectType.COIN);
        LeaderCard lc1 = new LeaderCard(0, reqSupplyContainer1, reqDevelopmentCard1, la1, 5);

        //create the second LeaderCard
        SupplyContainer reqSupplyContainer2 = new SupplyContainer(0, 1, 1, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard2 = new ArrayList<>();
        CardsRequirement cs21 = new CardsRequirement(2, 1, CardCategory.YELLOW);
        CardsRequirement cs22 = new CardsRequirement(1, 3, CardCategory.BLUE);
        reqDevelopmentCard2.add(cs21);
        reqDevelopmentCard2.add(cs22);
        LeaderAbility la2 = new Discount(WarehouseObjectType.SHIELD);
        LeaderCard lc2 = new LeaderCard(0, reqSupplyContainer2, reqDevelopmentCard2, la2, 3);

        boolean exc = false;
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {
            exc = true;
        }
        try {
            ls.addLeader(lc2);
        } catch (LeaderException e) {
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void addLeader_alreadyFull() {
        LeadersSpace ls = new LeadersSpace();

        //create the first LeaderCard
        SupplyContainer reqSupplyContainer1 = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        CardsRequirement cs11 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs12 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard1.add(cs11);
        reqDevelopmentCard1.add(cs12);
        LeaderAbility la1 = new Discount(WarehouseObjectType.COIN);
        LeaderCard lc1 = new LeaderCard(0, reqSupplyContainer1, reqDevelopmentCard1, la1, 5);

        boolean exc = false;
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {
            fail();
        }
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {
            fail();
        }
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {
            exc = true;
        }
        assertTrue(exc);
    }

    @Test
    public void discardLeader_no_exc() {
        LeadersSpace ls = new LeadersSpace();

        //create the first LeaderCard
        SupplyContainer reqSupplyContainer1 = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        CardsRequirement cs11 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs12 = new CardsRequirement(1, 2, CardCategory.BLUE);
        reqDevelopmentCard1.add(cs11);
        reqDevelopmentCard1.add(cs12);
        LeaderAbility la1 = new Discount(WarehouseObjectType.COIN);
        LeaderCard lc1 = new LeaderCard(0, reqSupplyContainer1, reqDevelopmentCard1, la1, 5);

        //create the second LeaderCard
        SupplyContainer reqSupplyContainer2 = new SupplyContainer(0, 1, 1, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard2 = new ArrayList<>();
        CardsRequirement cs21 = new CardsRequirement(2, 1, CardCategory.YELLOW);
        CardsRequirement cs22 = new CardsRequirement(1, 3, CardCategory.BLUE);
        reqDevelopmentCard2.add(cs21);
        reqDevelopmentCard2.add(cs22);
        LeaderAbility la2 = new Discount(WarehouseObjectType.SHIELD);
        LeaderCard lc2 = new LeaderCard(0, reqSupplyContainer2, reqDevelopmentCard2, la2, 3);

        //create the LeaderSpace
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {
            fail();
        }
        try {
            ls.addLeader(lc2);
        } catch (LeaderException e) {
            fail();
        }

        boolean exc = false;
        try {
            ls.discardLeader(1);
        } catch (LeaderException e) {
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void playLeader_no_exc() {
        LeadersSpace ls = new LeadersSpace();

        //create the first LeaderCard
        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.YELLOW));
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        LeaderAbility la1 = new Depot(WarehouseObjectType.COIN);
        LeaderCard lc1 = new LeaderCard(0, new SupplyContainer(2, 1, 0, 0, 0), reqDevelopmentCard1, la1, 5);
        //add the first card to the LeaderSpace
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {fail();}

        //create the second LeaderCard
        ArrayList<CardsRequirement> reqDevelopmentCard2 = new ArrayList<>();
        reqDevelopmentCard2.add(new CardsRequirement(1, 1, CardCategory.GREEN));
        reqDevelopmentCard2.add(new CardsRequirement(1, 3, CardCategory.VIOLET));
        LeaderAbility la2 = new Depot(WarehouseObjectType.SHIELD);
        LeaderCard lc2 = new LeaderCard(0, new SupplyContainer(0, 1, 1, 0, 0), reqDevelopmentCard2, la2, 3);
        //add the second card to the LeaderSpace
        try {
            ls.addLeader(lc2);
        } catch (LeaderException e) {fail();}

        //creating DepotsManager
        DepotsManager dm = new DepotsManager(new Warehouse(), ls);

        //creating my coffer
        SupplyContainer coff = new SupplyContainer(2, 1, 0, 0, 0);

        //creating Developments
        //creating cards to add
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 1, 5, CardCategory.YELLOW, prod, cost);

        SupplyContainer cost2 = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in2 = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out2 = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod2 = new Production(in2, out2);
        DevelopmentCard dc2 = new DevelopmentCard(0, 1, 5, CardCategory.BLUE, prod2, cost2);

        Developments developments = new Developments();
        try {
            developments.addCardToSpace(0, dc);
        } catch (DevelopmentException e) {fail();}
        try {
            developments.addCardToSpace(1, dc2);
        } catch (DevelopmentException e) {fail();}

        //creating ResourceChecker
        ResourceChecker rc = new ResourceChecker(dm, coff, developments);

        boolean exc = false;
        try {
            ls.playLeader(0, rc);
        } catch (LeaderException | SupplyException e) {
            exc = true;
        } //caught

        assertFalse(exc);
    }

    @Test
    public void playLeader_supplyExc() {
        LeadersSpace ls = new LeadersSpace();

        //create the first LeaderCard
        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.YELLOW));
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        LeaderAbility la1 = new Depot(WarehouseObjectType.COIN);
        LeaderCard lc1 = new LeaderCard(0, new SupplyContainer(2, 1, 0, 0, 0), reqDevelopmentCard1, la1, 5);
        //add the first card to the LeaderSpace
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {fail();}

        //create the second LeaderCard
        ArrayList<CardsRequirement> reqDevelopmentCard2 = new ArrayList<>();
        reqDevelopmentCard2.add(new CardsRequirement(1, 1, CardCategory.GREEN));
        reqDevelopmentCard2.add(new CardsRequirement(1, 3, CardCategory.VIOLET));
        LeaderAbility la2 = new Depot(WarehouseObjectType.SHIELD);
        LeaderCard lc2 = new LeaderCard(0, new SupplyContainer(0, 1, 1, 0, 0), reqDevelopmentCard2, la2, 3);
        //add the second card to the LeaderSpace
        try {
            ls.addLeader(lc2);
        } catch (LeaderException e) {fail();}

        //creating DepotsManager
        DepotsManager dm = new DepotsManager(new Warehouse(), ls);

        //creating my coffer
        SupplyContainer coff = new SupplyContainer(0, 1, 1, 0, 0);

        //creating Developments
        //creating cards to add
        SupplyContainer cost = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod = new Production(in, out);
        DevelopmentCard dc = new DevelopmentCard(0, 1, 5, CardCategory.YELLOW, prod, cost);

        SupplyContainer cost2 = new SupplyContainer(0, 4, 0, 0,0);
        SupplyContainer in2 = new SupplyContainer(0, 0, 0, 1, 0);
        SupplyContainer out2 = new SupplyContainer(0, 0, 0, 0, 2);
        Production prod2 = new Production(in2, out2);
        DevelopmentCard dc2 = new DevelopmentCard(0, 1, 5, CardCategory.BLUE, prod2, cost2);

        Developments developments = new Developments();
        try {
            developments.addCardToSpace(0, dc);
        } catch (DevelopmentException e) {fail();}
        try {
            developments.addCardToSpace(1, dc2);
        } catch (DevelopmentException e) {fail();}

        //creating ResourceChecker
        ResourceChecker rc = new ResourceChecker(dm, coff, developments);

        boolean exc = false;
        try {
            ls.playLeader(0, rc);
        } catch (LeaderException e) {
            fail();
        } catch (SupplyException e) {
            exc = true; //caught
        }

        assertTrue(exc);
    }

    @Test
    public void getWinPointsTest() {
        LeadersSpace ls = new LeadersSpace();

        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.YELLOW));
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        LeaderAbility la1 = new Depot(WarehouseObjectType.COIN);
        LeaderCard lc1 = new LeaderCard(0, new SupplyContainer(2, 1, 0, 0, 0), reqDevelopmentCard1, la1, 5);
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {fail();}

        ArrayList<CardsRequirement> reqDevelopmentCard2 = new ArrayList<>();
        reqDevelopmentCard2.add(new CardsRequirement(1, 1, CardCategory.GREEN));
        reqDevelopmentCard2.add(new CardsRequirement(1, 3, CardCategory.VIOLET));
        LeaderAbility la2 = new Depot(WarehouseObjectType.SHIELD);
        LeaderCard lc2 = new LeaderCard(0, new SupplyContainer(0, 1, 1, 0, 0), reqDevelopmentCard2, la2, 3);
        try {
            ls.addLeader(lc2);
        } catch (LeaderException e) {fail();}

        int expectedWP = 8;

        int actualWP = ls.getWinPoints();

        assertEquals(expectedWP, actualWP);
    }

    @Test
    public void getStatusTest() {
        LeadersSpace ls = new LeadersSpace();

        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.YELLOW));
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.BLUE));
        LeaderAbility la1 = new Producer(new SupplyContainer(1, 0, 0, 0, 0));
        try {
            la1.swapProduction(WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException e) {
            fail();
        }
        try {
            la1.addSupply(WarehouseObjectType.COIN, DepotID.WAREHOUSE1);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {
            fail();
        }

        //first LeaderCard
        LeaderCard lc1 = new LeaderCard(0, new SupplyContainer(2, 1, 0, 0, 0), reqDevelopmentCard1, la1, 5);
        try {
            ls.addLeader(lc1);
        } catch (LeaderException e) {fail();}

        ArrayList<CardsRequirement> reqDevelopmentCard2 = new ArrayList<>();
        reqDevelopmentCard2.add(new CardsRequirement(1, 1, CardCategory.GREEN));
        reqDevelopmentCard2.add(new CardsRequirement(1, 3, CardCategory.VIOLET));
        LeaderAbility la2 = new Producer(new SupplyContainer(1, 0, 0, 0, 0));
        try {
            la2.swapProduction(WarehouseObjectType.SERVANT);
        } catch (SupplyException | NoSuchMethodException e) {
            fail();
        }
        try {
            la2.addSupply(WarehouseObjectType.STONE, DepotID.WAREHOUSE1);
        } catch (SupplyException | NoSuchMethodException | LeaderException e) {
            fail();
        }

        //second LeaderCard
        LeaderCard lc2 = new LeaderCard(0, new SupplyContainer(0, 1, 1, 0, 0), reqDevelopmentCard2, la2, 3);
        try {
            ls.addLeader(lc2);
        } catch (LeaderException e) {fail();}

        //activate cards
        try {
            lc1.activate();
        } catch (LeaderException e) {fail();}
        try {
            lc2.activate();
        } catch (LeaderException e) {fail();}

        //now test getStatus
        int [] expectedStatus ={
                0, //id
                1, //active
                0, //fixedInput
                4, //fixedOutput -> FAITH_MARKER
                1, //mutableOutput -> SERVANT
                1, 0 ,0 ,0 ,0, //currentSupply
                0, 0, 0, 0, 0, //for Depot ability
                0, //id
                1, //active
                0, //fixedInput
                4, //fixedOutput -> FAITH_MARKER
                1, //mutableOutput -> SERVANT
                0, 0, 0, 1, 0, //currentSupply -> STONE n 4th place because it is the order in WarehouseObjectType
                0, 0, 0, 0, 0
        };

        ArrayList<Integer> status = ls.getStatus();
        int [] actualStatus = {
                status.get(0),
                status.get(1),
                status.get(2),
                status.get(3),
                status.get(4),
                status.get(5), status.get(6), status.get(7), status.get(8), status.get(9),
                status.get(10), status.get(11), status.get(12), status.get(13), status.get(14),
                status.get(15),
                status.get(16),
                status.get(17),
                status.get(18),
                status.get(19),
                status.get(20), status.get(21), status.get(22), status.get(23), status.get(24),
                status.get(25), status.get(26), status.get(27), status.get(28), status.get(29)
        };

        assertArrayEquals(expectedStatus, actualStatus);
    }
}