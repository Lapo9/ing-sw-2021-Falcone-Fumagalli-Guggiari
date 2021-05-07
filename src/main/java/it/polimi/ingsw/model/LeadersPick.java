package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.LeaderException;

import java.util.ArrayList;

public class LeadersPick {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();


    public LeadersPick(){}


    public void fill(LeadersList allLeaders){
        for (int i=0; i < 4; ++i) {
            leaders.add(allLeaders.pick());
        }
    }


    public void pick(int index, LeadersSpace leadersSpace) throws LeaderException {
        leadersSpace.addLeader(leaders.get(index));
    }

}
