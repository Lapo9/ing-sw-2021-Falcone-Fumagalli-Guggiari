package it.polimi.ingsw.model.match_items;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.model.leaders.CardsRequirement;
import it.polimi.ingsw.model.leaders.LeaderCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the list of all leader cards in the game. We will pick four cards to show to the player and he will choose two of them
 */
public class LeadersList {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();

    /**
     * Class constructor
     */
    public LeadersList(){
        fill();
    }


    /**
     * Picks the first card of the list (already shuffled)
     * @return the random LeaderCard
     */
    public LeaderCard pick() {
        return leaders.remove(0);
    }


    /**
     * Fills the list of LeaderCard with the existing 16 cards and shuffles it
     */
    private void fill(){
        for (int i = 1; i <= 16; ++i){
            leaders.add(new LeaderCard(i));
        }
        Collections.shuffle(leaders);
    }

}
