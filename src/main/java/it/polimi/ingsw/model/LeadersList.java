package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class LeadersList {

    private ArrayList<LeaderCard> leaders = new ArrayList<>();


    public LeadersList(){
        fill();
    }



    public LeaderCard pick() {
        Collections.shuffle(leaders);
        return leaders.remove(0);
    }



    private void fill(){
        //TODO add all of the cards
    }

}
