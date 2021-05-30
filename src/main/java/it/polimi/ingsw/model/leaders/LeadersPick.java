package it.polimi.ingsw.model.leaders;

import it.polimi.ingsw.model.HasStatus;
import it.polimi.ingsw.model.match_items.LeadersList;
import it.polimi.ingsw.model.exceptions.LeaderException;

import java.util.ArrayList;

/**
 * Manages the leader cards chosen by the player
 */
public class LeadersPick implements HasStatus {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();

    /**
     * Class constructor
     */
    public LeadersPick(){}

    /**
     * Fills this list with four random leaders from the LeadersList
     * @param allLeaders the random list of all leader cards
     */
    public void fill(LeadersList allLeaders){
        for (int i=0; i < 4; ++i) {
            leaders.add(allLeaders.pick());
        }
    }

    /**
     * Fills this list with four leaders given in the form of a list of four LeaderCard
     * @param list the list of four leaders
     */
    public void fillWithList(ArrayList<LeaderCard> list) {
        leaders.add(list.get(0));
        leaders.add(list.get(1));
        leaders.add(list.get(2));
        leaders.add(list.get(3));
    }

    /**
     * Adds the card chosen by the player to his LeadersSpace
     * @param index the index of the chosen card (1/2/3/4)
     * @param leadersSpace the space of the player
     * @throws LeaderException if the chosen LeaderCard cannot be taken
     */
    public void pick(int index, LeadersSpace leadersSpace) throws LeaderException {
        leadersSpace.addLeader(leaders.get(index));
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList describing the chosen leader cards
     */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 4; ++i){
            res.addAll(leaders.get(i).getStatus());
        }
        return res;
    }
}
