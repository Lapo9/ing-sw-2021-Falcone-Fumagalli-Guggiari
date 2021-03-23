package it.polimi.ingsw.model;

/**
 * The CardsRequirement class contains the SupplyCard features needed to activate a LeaderCard
 */
public class CardsRequirement {
    private int number;
    private int level;
    private CardCategory category;

    /**
     * Class constructor
     * @param num is the number of SupplyCard needed to activate a LeaderCard
     * @param lev is the minimum level of SupplyCard needed to activate a LeaderCard
     * @param cat is the CardCategory of the SupplyCard needed to activate a LeaderCard
     */
    public CardsRequirement(int num, int lev, CardCategory cat) {
        number = num;
        level = lev;
        category = cat;
    }

    /**
     * The getNumber method can be used to find the number of SupplyCard needed to activate a LeaderCard
     * @return the number of SupplyCard needed to activate a LeaderCard
     */
    public int getNumber(){
        return number;
    }

    /**
     * The minLevel method can be used to find the minimum level of SupplyCard needed to activate a LeaderCard
     * @return the minimum level of SupplyCard needed to activate a LeaderCard
     */
    public int minLevel(){
        return level;
    }

    /**
     * The reqCard method can be used to find the CardCategory of the SupplyCard needed to activate a LeaderCard
     * @return the CardCategory needed to activate a LeaderCard
     */
    public CardCategory reqCard(){
        return category;
    }
}
