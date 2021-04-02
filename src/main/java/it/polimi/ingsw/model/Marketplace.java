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

    private final ArrayList<MarbleColor> grid = new ArrayList<>();
    private MarbleColor slide; //not final so i can shift


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

        //cycle through the positions of the grid, and add a random marble. Then remove the added marble from the listOfMarbles
        for (int i=0; i<12; ++i){
            int rnd = (int)(Math.random() * (13-i)); //creating random number
            MarbleColor tmp = listOfMarbles.remove(rnd); //remove casually one marble from the list
            grid.add(tmp); //insert the removed marble in the grid
        }
        slide = listOfMarbles.get(0); //insert the remaining marble in the slide

    }


    //returns the index of the array list given row and column
    private int getPos(int r, int c){
        return c+r*4;
    }

    
    /**
     * This method allows to obtain marbles from the Marketplace
     * @param dir Parameter to select one row/column of the Marketplace
     * @param index Index of desired row/column
     * @return A temporary MarbleContainer that contains selected marbles
     */
    public MarbleContainer obtain(MarketDirection dir, int index){

        //create a list of counters for every type of MarbleColor
        ArrayList<Integer> marbleCounters = new ArrayList<>();
        for(int i=0; i < 6; i++)
            marbleCounters.add(i, 0);

        //if the direction is HORIZONTAL, i have to take the marbles consequently with a cycle for
        if (dir == MarketDirection.HORIZONTAL){

            for(int i = 0; i < 3; i++){

                int pos = getPos(index, i);

                MarbleColor currentMarble = grid.get(pos); // get one MarbleColor from the array list grid

                //this instruction increase the counter of the currentMarble in the marbleCounters array list
                //parameter1: counter of the currentColor position in the list MarbleCounters
                //parameter2: the current value of the currentColor counter increased by 1 (to increase the counter)
                marbleCounters.set(currentMarble.ordinal(), marbleCounters.get(currentMarble.ordinal())+1);

            }
        }

        //if the direction is VERTICAL, i have to take one marble and skip the next three
        else{

            for(int i = 0; i < 2; i++){

                int pos = getPos(i, index);

                MarbleColor currentMarble = grid.get(pos); // get one element from the array list grid

                marbleCounters.set(currentMarble.ordinal(), marbleCounters.get(currentMarble.ordinal())+1);

            }
        }

        //create a MarbleContainer by inserting the values of the marbleCounter array list
        MarbleContainer mc = new MarbleContainer(marbleCounters.get(0), marbleCounters.get(1), marbleCounters.get(2), marbleCounters.get(3), marbleCounters.get(4), marbleCounters.get(5));

        shiftMarketplace(dir, index);

        return mc;
    }

    /**
     * This private method allows to shift the market after one player get his marbles
     * @param dir This method can shift in two ways: horizontal or vertical
     * @param index This method positions the slide in the selected row/column and shift the rest
     */
    private void shiftMarketplace(MarketDirection dir, int index){

        MarbleColor tmp;

        if(dir == MarketDirection.HORIZONTAL){

            tmp = grid.get(index*4); //copy in tmp the first MarbleColor of the row, it will be placed in the slide

            grid.remove(index*4); //remove that MarbleColor

            //shift the three elements remaining
            for(int i = 0; i < 3; i++){

                int pos = getPos(index, i);

                grid.add(index*4, grid.get(pos)); //the second element of the row takes place of the first

                grid.remove(pos);

            }

            grid.add(index*4+3, slide); //add the marble which is in the slide in the last column of the row

        }

        else{

            tmp = grid.get(index); //copy in tmp the MarbleColor in the first row of the selected column, ... slide

            grid.remove(index); //remove that element

            for(int i = 1; i < 3; i++){

                int pos = getPos(i, index);

                grid.add(index, grid.get(pos)); //add the element below in the "matrix"

                grid.remove(pos);

            }

            grid.add(index+8, slide); //add the marble in the slide

        }

        slide = tmp; //the first MarbleColor i removed goes in the slide

    }


    //TODO
    @Override
    public ArrayList<Integer> getStatus(){
        return new ArrayList<>();
    }

}
