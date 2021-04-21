package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.SupplyException;

import java.util.ArrayList;

/**
 * The class that manages tho operations on the 2 leaders.
 */
public class LeadersSpace implements HasStatus, WinPointsCountable{

    private ArrayList<LeaderCard> leaders = new ArrayList<>();


    public void addLeader(LeaderCard leader) throws LeaderException {
        if(leaders.size()<2) {
            leaders.add(leader);
        }
        else throw new LeaderException("Cannot add more than 2 leaders");
    }


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
    public void playLeader(int i, ResourceChecker rc) throws SupplyException, LeaderException{
        Pair<SupplyContainer, ArrayList<CardsRequirement>> req = leaders.get(i).getCost(); //get the requirements

        //if requirements are met, activate the card
        if(rc.check(req.first, req.second)){
            leaders.get(i).activate();
        }
        else {
            throw new SupplyException("Cannot activate leader because of resources");
        }
    }

    @Override
    public int getWinPoints() {
        return leaders.get(0).getWinPoints() + leaders.get(1).getWinPoints();
    }

    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        status.addAll(leaders.get(0).getStatus());
        status.addAll(leaders.get(1).getStatus());

        return status;
    }
}
