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


    public void addLeader(LeaderCard leader) throws LeaderException {
        if(leaders.size()<2) {
            leaders.add(leader);
            //set the leader depot type and source
            if (leaders.size() == 1) {
                if (leader.getAbilityTrusted() instanceof Depot) {
                    DepotID.LEADER1.setSource(DepotID.SourceType.DEPOT);
                    DepotID.LEADER1.setType(DepotID.DepotType.LEADER_DEPOT);
                }
                else if (leader.getAbilityTrusted() instanceof Producer) {
                    DepotID.LEADER1.setSource(DepotID.SourceType.ANY);
                    DepotID.LEADER1.setType(DepotID.DepotType.LEADER_PRODUCTION);
                }
                else {
                    DepotID.LEADER1.setSource(DepotID.SourceType.NONE);
                    DepotID.LEADER1.setType(DepotID.DepotType.NONE);
                }
            }
            else if (leaders.size() == 2) {
                if (leader.getAbilityTrusted() instanceof Depot) {
                    DepotID.LEADER2.setSource(DepotID.SourceType.DEPOT);
                    DepotID.LEADER2.setType(DepotID.DepotType.LEADER_DEPOT);
                }
                else if (leader.getAbilityTrusted() instanceof Producer) {
                    DepotID.LEADER2.setSource(DepotID.SourceType.ANY);
                    DepotID.LEADER2.setType(DepotID.DepotType.LEADER_PRODUCTION);
                }
                else {
                    DepotID.LEADER2.setSource(DepotID.SourceType.NONE);
                    DepotID.LEADER2.setType(DepotID.DepotType.NONE);
                }
            }

            //TODO debug remove later
            System.out.print(DepotID.LEADER1.getType(this).toString());
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
