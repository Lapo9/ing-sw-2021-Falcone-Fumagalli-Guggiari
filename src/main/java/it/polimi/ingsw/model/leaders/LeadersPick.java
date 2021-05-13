package it.polimi.ingsw.model.leaders;

import it.polimi.ingsw.model.match_items.LeadersList;
import it.polimi.ingsw.model.exceptions.LeaderException;

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
