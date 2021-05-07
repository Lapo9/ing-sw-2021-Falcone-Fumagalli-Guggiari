package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.leader_abilities.Depot;
import it.polimi.ingsw.model.leader_abilities.Producer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class that manages tho operations on the 2 leaders.
 */
public class LeadersSpace implements HasStatus, WinPointsCountable{

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
