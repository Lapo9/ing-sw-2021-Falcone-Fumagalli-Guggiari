package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * Contains the SupplyCard features needed to activate a LeaderCard.
 */
public class CardsRequirement{
    private int number;
    private int level;
    private CardCategory category;


    /**
     * Creates a list of requirements.
     * @param num the number of SupplyCard needed to activate a LeaderCard
     * @param lev the minimum level of SupplyCard needed to activate a LeaderCard
     * @param cat the CardCategory of the SupplyCard needed to activate a LeaderCard
     */
    public CardsRequirement(int num, int lev, CardCategory cat) {
        number = num;
        level = lev;
        category = cat;
    }


    /**
     * Finds the number of SupplyCard needed to activate a LeaderCard.
     * @return the number of SupplyCard needed to activate a LeaderCard
     */
    public int getNumber(){
        return number;
    }


    /**
     * Finds the minimum level of SupplyCard needed to activate a LeaderCard.
     * @return the minimum level of SupplyCard needed to activate a LeaderCard
     */
    public int minLevel(){
        return level;
    }


    /**
     * Finds the CardCategory of the SupplyCard needed to activate a LeaderCard.
     * @return the CardCategory of the SupplyCard needed to activate a LeaderCard
     */
    public CardCategory reqCard(){
        return category;
    }
}
