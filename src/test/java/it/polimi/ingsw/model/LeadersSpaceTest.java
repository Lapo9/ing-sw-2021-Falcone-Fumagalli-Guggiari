package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.leader_abilities.Discount;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LeadersSpaceTest {

    @Test
    public void discardLeader_no_exc() {
        ArrayList<LeaderCard> ls = new ArrayList<>();

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
        ls.add(lc1);
        ls.add(lc2);

        boolean exc = false;
        try {
            ls.get(1).discard();
        } catch (LeaderException e) {
            exc = true;
        }

        assertFalse(exc);
    }

    @Test
    public void playLeader_no_exc() {
        ArrayList<LeaderCard> ls = new ArrayList<>();

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
        ls.add(lc1);
        ls.add(lc2);

        //need constructor TODO
        /* try {
            ls.get(0).playLeader(0, reqDevelopmentCard1);
        } catch (LeaderException e) {fail();} */


    }

    @Test
    public void getWinPointsTest() {
        ArrayList<LeaderCard> ls = new ArrayList<>();

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
        ls.add(lc1);
        ls.add(lc2);

        int expectedWP = 8;
        //need constructor TODO
        // int actualWP = ls.getWinPoints();

        //assertEquals(expectedWP, actualWP);
    }

    @Test
    public void getStatusTest() {

    }
}