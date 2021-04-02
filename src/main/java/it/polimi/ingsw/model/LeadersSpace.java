package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.SupplyException;

import java.util.ArrayList;

/**
 * The class that manages tho operations on the 2 leaders.
 */
public class LeadersSpace {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();


    /**
     * Tries to discard the leader card
     * @param i what leader? 0 or 1?
     * @throws LeaderException If the leader is active or already discarded
     */
    public void discardLeader(int i) throws LeaderException {
        leaders.get(i).discard();
    }


    /**
     * Returns the ability of the selected leader
     * @param i what leader? 0 or 1?
     * @return the ability of the leader
     * @throws LeaderException If the leader isn't active yet
     */
    public LeaderAbility getLeaderAbility(int i) throws LeaderException{
        return leaders.get(i).getAbility();
    }


    /**
     * Tries to activate the leader ability
     * @param i what leader? 0 or 1?
     * @throws SupplyException Requirements to activate the leader aren't met
     * @throws LeaderException Leader is already active or discarded
     */
    public void playLeader(int i, ResourcesChecker rc) throws SupplyException, LeaderException{
        Pair<SupplyContainer, ArrayList<CardsRequirement>> req = leaders.get(i).getCost(); //get the requirements

        //if requirements are met, activate the card
        if(rc.check(req.first, req.second)){
            leaders.get(i).activate();
        }
        else {
            throw new SupplyException();
        }
    }


}
