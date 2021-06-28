package it.polimi.ingsw.model.leaders;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.LeaderAbility;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages tho operations on the 2 leaders of the player
 */
public class LeadersSpace implements HasStatus, WinPointsCountable {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();

    /**
     * Adds the leader selected to the pick of the player
     * @param leader selected to add
     * @throws LeaderException If the leader is active or already discarded
     */
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
     * Returns the leader ability, whether the leader is active or not
     * @param i index of the LeaderCard in the ArrayList
     * @return the ability of the LeaderCard
     */
    public LeaderAbility getLeaderAbilityTrusted(int i) {
        return leaders.get(i).getAbilityTrusted();
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


    /**
     * Activates a leaderCard, given its index.
     * @param index of a leaderCard in the leaderSpace
     * @throws LeaderException if the leaderCard is already discarded or activated
     */
    public void activateLeaderCardTrusted(int index) throws LeaderException {
        leaders.get(index).activate();
    }

    /**
     * Discards a leaderCard, given its index.
     * @param index of a leaderCard in the leaderSpace
     * @throws LeaderException if the leaderCard is already discarded or activated
     */
    public void discardLeaderCardTrusted(int index) throws LeaderException {
        leaders.get(index).discard();
    }

    /**
     * Method that gets the win points of the object
     * @return the win points of the object
     */
    @Override
    public int getWinPoints() {
        return leaders.get(0).getWinPoints() + leaders.get(1).getWinPoints();
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of 30 Integer
     */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        //if leaders aren't still present, add all 0
        try {
            status.addAll(leaders.get(0).getStatus());
        } catch (IndexOutOfBoundsException ioobe){
            status.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        }
        try {
            status.addAll(leaders.get(1).getStatus());
        } catch (IndexOutOfBoundsException ioobe){
            status.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        }

        return status;
    }
}
