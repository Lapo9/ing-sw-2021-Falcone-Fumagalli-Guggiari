package it.polimi.ingsw.model.match_items;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.SupplyContainer;
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
        return leaders.remove(0);
    }



    private void fill(){
        for (int i = 1; i <= 16; ++i){
            leaders.add(new LeaderCard(i));
        }
        Collections.shuffle(leaders);
    }

}
