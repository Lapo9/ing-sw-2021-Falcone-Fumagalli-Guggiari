package it.polimi.ingsw.model;

import it.polimi.ingsw.model.development.DevelopmentCard;
import it.polimi.ingsw.model.development.Developments;
import it.polimi.ingsw.model.development.Production;
import it.polimi.ingsw.model.exceptions.DevelopmentException;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.LeaderAbility;
import it.polimi.ingsw.model.leaders.CardsRequirement;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.LeadersSpace;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ResourceCheckerTest {

    @Test
    public void checkTest() {
        LeadersSpace ls = new LeadersSpace();

        //create the LeaderSpace
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

        //check if i have enough supplies in coffer, warehouse...
        //check if i have enough cards to satisfy reqDevelopmentCard1 in Developments
        assertTrue(rc.check(new SupplyContainer(2, 1, 0, 0, 0), reqDevelopmentCard1));
    }

    @Test
    public void checkTest_supplyReqNotSatisfied() {
        LeadersSpace ls = new LeadersSpace();

        //create the LeaderSpace
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

        //creating DepotsManager
        DepotsManager dm = new DepotsManager(new Warehouse(), ls);

        //creating my coffer -> not enough
        SupplyContainer coff = new SupplyContainer(1, 1, 0, 0, 0);

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

        //check if i have enough supplies in coffer, warehouse...
        //check if i have enough cards to satisfy reqDevelopmentCard1 in Developments
        assertFalse(rc.check(new SupplyContainer(2, 1, 0, 0, 0), reqDevelopmentCard1));
    }

    @Test
    public void checkTest_cardsReqNotSatisfied() {
        LeadersSpace ls = new LeadersSpace();

        //create the LeaderSpace
        ArrayList<CardsRequirement> reqDevelopmentCard1 = new ArrayList<>();
        reqDevelopmentCard1.add(new CardsRequirement(1, 1, CardCategory.YELLOW));
        reqDevelopmentCard1.add(new CardsRequirement(1, 2, CardCategory.BLUE));
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

        //check if i have enough supplies in coffer, warehouse...
        //check if i have enough cards to satisfy reqDevelopmentCard1 in Developments
        assertFalse(rc.check(new SupplyContainer(2, 1, 0, 0, 0), reqDevelopmentCard1));
    }
}