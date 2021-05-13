package it.polimi.ingsw.model;

import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.model.leaders.CardsRequirement;
import it.polimi.ingsw.model.leaders.LeaderCard;

import java.util.ArrayList;
import java.util.Collections;

public class LeadersList {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();


    public LeadersList(){
        fill();
    }



    public LeaderCard pick() {
        Collections.shuffle(leaders);
        return leaders.remove(0);
    }



    private void fill(){
        //TODO add all of the cards
        SupplyContainer reqSupplyContainer = new SupplyContainer(2, 0, 0, 0, 0);
        ArrayList<CardsRequirement> reqDevelopmentCard = new ArrayList<>();
        CardsRequirement cs1 = new CardsRequirement(1, 1, CardCategory.YELLOW);
        CardsRequirement cs2 = new CardsRequirement(1, 2, CardCategory.BLUE);
        leaders.add(new LeaderCard(1, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
        leaders.add(new LeaderCard(2, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
        leaders.add(new LeaderCard(3, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
        leaders.add(new LeaderCard(4, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
        leaders.add(new LeaderCard(5, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
        leaders.add(new LeaderCard(6, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
        leaders.add(new LeaderCard(7, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
        leaders.add(new LeaderCard(8, reqSupplyContainer, reqDevelopmentCard, new Producer(new SupplyContainer(0,0,1,0,0)), 3));
    }

}
