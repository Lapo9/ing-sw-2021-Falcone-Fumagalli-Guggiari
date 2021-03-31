package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import java.util.ArrayList;

import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.model.CardsRequirement;
import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.SupplyContainer;

/**
 * The LeaderCard class represents a leader card of the game, each card has its own ability, winPoints and requirements
 * needed to buy it. It can be activated if the player has the right quantity of requirements, and it can be discard by
 * the player in exchange for a faith point.
 */
public class LeaderCard implements WinPointsCountable, HasStatus{
    private Pair<SupplyContainer, ArrayList<CardsRequirement>> requirements;
    private LeaderAbility ability;
    private boolean active = false;
    private boolean discard = false;
    private final int winPoints;

    /**
     * Class constructor
     * @param reqSC is a SupplyContainer which contains all the supplies needed to buy the LeaderCard
     * @param reqCR is an ArrayList of CardRequirement which contains the details about the SupplyCards needed to buy the LeaderCard
     * @param abil is the type of the LeaderCard's ability
     */
    public LeaderCard(SupplyContainer reqSC, ArrayList<CardsRequirement> reqCR, LeaderAbility abil, int points){
        requirements = new Pair<SupplyContainer, ArrayList<CardsRequirement>>(reqSC, reqCR);
        ability = abil;
        winPoints = points;
    }

    /**
     * The getCost method returns a pair which contains all the requirements needed to buy the LeaderCard
     * @return a pair which contains all the requirements needed to buy the LeaderCard
     */
    public Pair<SupplyContainer, ArrayList<CardsRequirement>> getCost(){
        return requirements;
    }

    /**
     * The getAbility returns the ability of the LeaderCard
     * @return the ability of the LeaderCard
     * @throws LeaderException if the LeaderCard is not active yet or if it has been discard
     */
    public LeaderAbility getAbility() throws LeaderException{
        if(!active || discard)
            throw new LeaderException();
        return ability;
    }

    /**
     * The activate method activates the LeaderCard
     */
    public void activate(){
        active = true;
    }

    /**
     * The setDiscard method discard the LeaderCard
     */
    public void discard(){
        discard = true;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus() {

    }

    @Override
    public int getWinPoints() {
        return winPoints;
    }
}
