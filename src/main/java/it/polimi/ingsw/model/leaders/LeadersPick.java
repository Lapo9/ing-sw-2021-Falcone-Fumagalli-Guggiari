package it.polimi.ingsw.model.leaders;

import it.polimi.ingsw.model.HasStatus;
import it.polimi.ingsw.model.match_items.LeadersList;
import it.polimi.ingsw.model.exceptions.LeaderException;

import java.util.ArrayList;

public class LeadersPick implements HasStatus {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();


    public LeadersPick(){}


    public void fill(LeadersList allLeaders){
        for (int i=0; i < 4; ++i) {
            leaders.add(allLeaders.pick());
        }
    }


    public void fillWithList(ArrayList<LeaderCard> list) {
        leaders.add(list.get(0));
        leaders.add(list.get(1));
        leaders.add(list.get(2));
        leaders.add(list.get(3));
    }


    public void pick(int index, LeadersSpace leadersSpace) throws LeaderException {
        leadersSpace.addLeader(leaders.get(index));
    }


    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 4; ++i){
            res.addAll(leaders.get(i).getStatus());
        }
        return res;
    }
}
