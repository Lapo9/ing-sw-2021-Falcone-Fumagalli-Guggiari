package it.polimi.ingsw.controller.match;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.controller.Player;
import it.polimi.ingsw.controller.exceptions.MatchException;
import it.polimi.ingsw.model.match_items.DevelopmentGrid;
import it.polimi.ingsw.model.match_items.Marketplace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MatchManager {

    private HashMap<String, Match> activeMatches = new HashMap<>();
    private String recoveryPath = "src/main/resources/files/savedMatches.txt";

    public MatchManager(boolean recovery){

        if (recovery){
            restoreMatches();
        }
    }



    public synchronized void addPlayer(Player p, String matchId, boolean isSinglePlayer) throws MatchException {

        Match specified = activeMatches.get(matchId);

        if(specified != null){
            //if there is a match with that name, add the player to that match
            specified.addPlayer(p);
        }
        else {
            //if not create a new match
            activeMatches.put(matchId, new Match(p, isSinglePlayer, this, matchId));
        }

        p.attachMatch(activeMatches.get(matchId));

    }



    public synchronized void notifyMatchEnded(String matchId){
        activeMatches.remove(matchId);
    }


    /**
     * START_OF_MATCH matchID xXx player1, player2, ... xXx active player index xXx development grid status xXx marketplace status xXx player1 status xXx player2 status xXx ... xXx END_OF_MATCH
     * @param matchId match to save to file
     */
    public synchronized void saveMatchState(String matchId){
        StringBuilder matchStatus = new StringBuilder("START_OF_MATCH ");

        matchStatus.append(matchId).append(" xXx "); //name of the match
        matchStatus.append(activeMatches.get(matchId).isSinglePlayer()).append(" xXx "); //single player match?
        ArrayList<Player> players = activeMatches.get(matchId).getPlayers();
        players.forEach(p -> matchStatus.append(p.getName()).append(" ")); //names of the players
        matchStatus.append(" xXx ");
        matchStatus.append(activeMatches.get(matchId).getActivePlayer()).append(" xXx "); //active player

        Pair<DevelopmentGrid, Marketplace> matchItems = activeMatches.get(matchId).getMatchItems();
        matchStatus.append(matchItems.first.getStatus()).append(" xXx "); //save development grid status
        matchStatus.append(matchItems.second.getStatus()).append(" xXx "); //save marketplace status

        //save players status
        players.forEach(p -> matchStatus.append(p.getDashboard().getStatus()).append(" xXx "));

        StringBuilder finalMatchStatus = new StringBuilder(matchStatus.toString().replaceAll("\\[", ""));
        finalMatchStatus = new StringBuilder(finalMatchStatus.toString().replaceAll("]", ""));


        //save status to file
        Path path = Path.of(recoveryPath);
        String savedMatches = "";
        try {
            savedMatches = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] matches = savedMatches.split("END_OF_MATCH\n\n");

        for (String match : matches) {
            if (!match.contains("START_OF_MATCH " + matchId)) {
                finalMatchStatus.append(match).append("END_OF_MATCH\n\n");
            }
        }

        File file = new File(recoveryPath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(finalMatchStatus.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    private void restoreMatches(){
        Path path = Path.of(recoveryPath);
        String savedMatches = "";
        try {
            savedMatches = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] matches = savedMatches.split("END_OF_MATCH\n\n");


        for (String match : matches){
            String matchId = match.split(" ")[1];

            activeMatches.put(matchId, new Match(match, this, matchId));
        }

    }


}
