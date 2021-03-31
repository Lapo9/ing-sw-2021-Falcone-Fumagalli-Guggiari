package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * The MarketPlace class represents the place that contains marbles
 */

public class Marketplace implements HasStatus{

    private ArrayList<MarbleColor> grid = new ArrayList<>();
    private MarbleColor slide;


    /**
     * Class constructor: creates the Marketplace, places 12 random marbles and places the remaining marble in the 'slide'
     */
    public Marketplace(){

        //list with all of the possible marbles (4 white, 2 blu, 2 yellow, 2 violet, 2 grey, 1 red)
        ArrayList<MarbleColor> listOfMarbles = new ArrayList<>();
        for (int i = 0; i<4; ++i) {
            listOfMarbles.add(MarbleColor.WHITE);
        }
        for (int i = 0; i<2; ++i) {
            listOfMarbles.add(MarbleColor.BLUE);
            listOfMarbles.add(MarbleColor.YELLOW);
            listOfMarbles.add(MarbleColor.VIOLET);
            listOfMarbles.add(MarbleColor.GREY);
        }
        listOfMarbles.add(MarbleColor.RED);

        //cycle through the positions of the grid, adn add a random marble. Then remove the added marble from the listOfMarbles
        for (int i=0; i<12; ++i){
            int rnd = (int)(Math.random() * (13-i));
            MarbleColor tmp = listOfMarbles.remove(rnd);
            grid.add(tmp);
        }
        slide = listOfMarbles.get(0);

    }


    //returns the index given row and column
    private int getPos(int r, int c){
        return c+r*4;
    }

    //returns the row and column given the index
    private Pair<Integer, Integer> getRowCol(int i){
        return new Pair((Integer)(i/4), (Integer)(i%3));
    }

    /**
     * This method allows to obtain marbles from the Marketplace
     * @param dir Parameter to select one row/column of the Marketplace
     * @param index Index of desired row/column
     * @return A temporary MarbleContainer that contains selected marbles
     */
    public MarbleContainer obtain(MarketDirection dir, int index){

        MarbleContainer mc;

        if (dir == dir.HORIZONTAL){



        }
        else {

        }

    }

}
