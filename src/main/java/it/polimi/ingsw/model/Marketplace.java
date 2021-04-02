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

        int y=0, b=0, g=0, w=0, v=0, r=0;

        //if the direction is HORIZONTAL, i have to take the marbles consequently with a cycle for
        if (dir == MarketDirection.HORIZONTAL){

            for(int i = index * 4; i < (index * 4) + 3; i++){
                MarbleColor currentColor = grid.get(i); // get one element from the array list grid

                //increment the counter for the got marble
                if(currentColor == MarbleColor.YELLOW)
                    y++;
                else if(currentColor == MarbleColor.BLUE)
                    b++;
                else if(currentColor == MarbleColor.GREY)
                    g++;
                else if(currentColor == MarbleColor.WHITE)
                    w++;
                else if(currentColor == MarbleColor.VIOLET)
                    v++;
                else
                    r++;
            }
        }

        //if the direction is VERTICAL, i have to take one marble and skip the next three
        else{
            for(int i = index; i < 12; i++){

                MarbleColor currentColor = grid.get(i); // get one element from the array list grid

                //increment the counter for the got marble
                if(currentColor == MarbleColor.YELLOW)
                    y++;
                else if(currentColor == MarbleColor.BLUE)
                    b++;
                else if(currentColor == MarbleColor.GREY)
                    g++;
                else if(currentColor == MarbleColor.WHITE)
                    w++;
                else if(currentColor == MarbleColor.VIOLET)
                    v++;
                else
                    r++;
                i += 3; //so i can take one element and skip the next three
            }
        }

        MarbleContainer mc = new MarbleContainer(y, b, g, w, v, r);

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

                grid.add(index*4, grid.get(index*4+i)); //the second element of the row takes place of the first

                grid.remove(index*4+i);

            }

            grid.add(index*4+3, slide); //add the marble which is in the slide in the last column of the row

            slide = tmp; //the first MarbleColor i removed goes in the slide
        }

        else{

            tmp = grid.get(index); //copy in tmp the MarbleColor in the first row of the selected column, ... slide

            grid.remove(index); //remove that element

            for(int i = 1; i < 3; i++){

                grid.add(index, grid.get(index+4*i)); //add the element below in the "matrix"

                grid.remove(index+4*i);

            }

            grid.add(index+8, slide);
            slide = tmp;
        }

    }


    //TODO
    @Override
    public ArrayList<Integer> getStatus(){
        return new ArrayList<>();
    }

}
