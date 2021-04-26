package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;

import java.util.HashMap;

public class MatchManager {

    HashMap<String, Match> activeMatches = new HashMap<>();

    public MatchManager(){

    }



    public synchronized void addPlayer(Player p, String matchId) throws MatchException {

        Match specified = activeMatches.get(matchId);

        if(specified != null){
            //if there is a match with that name, add the player to that match
            specified.addPlayer(p);
        }
        else {
            //if not create a new match
            activeMatches.put(matchId, new Match(p));
        }

        p.attachMatch(activeMatches.get(matchId));

    }

}
