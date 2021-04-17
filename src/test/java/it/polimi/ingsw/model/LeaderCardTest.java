package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.model.leader_abilities.Discount;
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
}