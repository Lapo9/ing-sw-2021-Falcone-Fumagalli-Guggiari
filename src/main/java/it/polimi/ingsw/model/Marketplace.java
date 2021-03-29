package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * The MarketPlace class represents the place that contains marbles
 */

public class Marketplace implements HasStatus{

    /**
     * List of int that correspond to y, b, g, w, v, r
     * //TODO
     */
    private List<Integer> marblesList = new ArrayList<>();
        list.add(y);
        list.add(b);
        list.add(g);
        list.add(w);
        list.add(v);
        list.add(r);

    /**
     * This method calculate a random marble to insert in the Marketplace
     * @param marblesList List of type of marbles
     * @return Random marble
     */
    public int randomMarble(List<Integer> marblesList){
        Random rand = new Random();
        return marblesList.get(rand.nextInt(marblesList.size()));
    }

    /**
     * Class constructor: creates the Marketplace, places 12 random marbles and places the remaining marble in the 'slide'
     */
    public Marketplace(){

        int i, j, slide, rand, mp[3][4];

        totMarbles = new MarbleContainer(2, 2, 2, 4, 2, 1);

        for(i=0; i<mp.length; i++) {
            for (j = 0; j < mp.length; j++) {

                rand = randomMarble(marblesList);

                if (totMarbles.rand > 0)
                    mp[i][j] = rand;
                    //if all 'x' marbles have been placed, then go back and re-extract another rand marble
                else
                    //if it is the first column, j can't be lower zero
                    if (j > 0)
                        j--;
                    else
                        i--;

                //now we reduce the counter of the marble inserted

                if (rand == y)
                    totMarbles.y--;

                else if (rand == b)
                    totMarbles.b--;

                else if (rand == g)
                    totMarbles.g--;

                else if (rand == w)
                    totMarbles.w--;

                else if (rand == v)
                    totMarbles.v--;

                else
                    totMarbles.r--;

            }
        }

        //insert the remaining marble in the slide
        for(i=0; i<5; i++)
            if(totMarbles.y > 0)
                slide=y;
            //if series? easier way?
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
