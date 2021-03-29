package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * The MarketPlace class represents the place that contains marbles
 */

public class MarketPlace implements HasStatus{

    /**
     * Class constructor: creates the MarketPlace, places 12 random marbles and places the remaining marble in the 'slide'
     */
    public MarketPlace(){

        int i, j, slide, mp[3][4];

        private totMarbles = new MarbleContainer(2, 2, 2, 4, 2, 1);

        for(i=0; i<mp.length; i++)
            for(j=0; j<mp.length; j++)

                rand = //random number from list of colours

                if (totMarbles.rand > 0)
                    mp[i][j] = rand;

                //now we reduce the counter of the marble inserted

                if (rand = y)
                    totMarbles.y--;

                else if(rand = b)
                    totMarbles.b--;

                else if(rand = g)
                    totMarbles.g--;

                else if(rand = w)
                    totMarbles.w--;

                else if(rand = v)
                    totMarbles.v--;

                else
                    totMarbles.r--;



    }

    /**
     * This method allows to obtain marbles from the MarketPlace
     * @param dir Parameter to select one row/column of the MarketPlace
     * @param index Index of desired row/column
     * @return A temporary MarbleContainer that contains selected marbles
     */
    public MarbleContainer obtain(MarketDirection dir, int index){

        MarbleContainer mc;
        int cnt;

        if (dir == dir.HORIZONTAL){
            for (cnt=0; cnt<=3; cnt++){

            }
        }
        else {

        }

    }

}
