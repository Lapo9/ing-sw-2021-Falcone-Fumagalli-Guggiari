package it.polimi.ingsw.model;

import java.util.ArrayList;
import it.polimi.ingsw.model.PopeFavorTile;

/**
 * The FaithTrack class represents the faith track on the player's personal board
 */

public class FaithTrack implements WinPointsCountable, HasStatus{

    private int position;
    private int winPoints;
    private int vaticanReportCounter;               //counts how many times a vatican report has been issued in the game
    private ArrayList<PopeFavorTile> popeFavors;
    private ArrayList<FaithTrackTiles> track;

    /**
     * The FaithTrackTiles class represents a tile of the FaithTrack
     */
    public class FaithTrackTiles{
        private int points;
        private int number;
        private boolean isPopeSpace;

        /**
         * Class constructor
         * @param num is the tiles number
         */
        public FaithTrackTiles(int num){
            number = num;
            //FIXME
            //va scritto in modo più compatto, devo capire come
            switch(num){
                case 3:
                    points = 1;
                case 6:
                    points = 2;
                case 9:
                    points = 4;
                case 12:
                    points = 6;
                case 15:
                    points = 9;
                case 18:
                    points = 12;
                case 21:
                    points = 16;
                case 24:
                    points = 20;
                default:
                    points = 0;
            }
            if(num == 8 || num == 16 || num == 24)
                isPopeSpace = true;
        }
    }

    /**
     * Class constructor
     */
    public FaithTrack(){
        position = 0;
        winPoints = 0;
        vaticanReportCounter = 0;
        popeFavors = new ArrayList<PopeFavorTile>(3);
        for(int i=2; i<5; i++)
            popeFavors.add(new PopeFavorTile(i));
        track = new ArrayList<FaithTrackTiles>(25);
        for(int i=0; i<25; i++)
            track.add(new FaithTrackTiles(i));

    }

    /**
     * The goAhead method moves the player position ahead of num tiles in the FaithTrack
     * @param num is the number of steps taken by the player
     * @return true if a vaticanReport has been issued
     */
    public boolean goAhead(int num){
        boolean vr;
        boolean res = false;
        for(int i = 0; i < num; i++) {
            vr = goAhead();
            if(vr)
                res = true;
        }
        return res;
    }

    /**
     * The goAhead method moves the player one tile ahead in the FaithTrack
     * @return true is a vaticanReport has been issued
     */
    private boolean goAhead(){
        boolean vr = false;
        position++;
        // You can activate a vaticanReport only if you are the first player to reach the PopeSpace tile
        if(track.get(position).isPopeSpace &&
                ((position == 8 && vaticanReportCounter == 0) ||(position ==16 && vaticanReportCounter == 1)||
                        (position == 24 && vaticanReportCounter == 2))) {
            vaticanReport();
            vr = true;
        }
        winPoints += track.get(position).points;
        return vr;
    }

    /**
     * The vaticanReport method triggers a vatican report
     */
    public void vaticanReport(){
        //Check if the player who called the vaticanReport method is the one who activated it
        if((position == 8 && vaticanReportCounter == 0) ||(position ==16 && vaticanReportCounter == 1)||
                (position == 24 && vaticanReportCounter == 2)){
            popeFavors.get(vaticanReportCounter).activate();
            vaticanReportCounter++;          //update the vaticanReportCounter
        }
        //If you aren't the player who triggered the vaticanReport, the game needs to check your position and if you
        //are in the right vatican report section. If yes, you can activate the right Pope's favor tile
        else{
            //The player isn't in a vatican report section
            if(position < 5 || (position > 8 && position < 12) || position == 17 || position == 18){
                popeFavors.get(vaticanReportCounter).discard();
                vaticanReportCounter++;
            }
            //The player is in the first vatican report section, if he is there when the first vaticanReport has been
            //called, he can turn the first PopeFavorTile
            else if(position >= 5 && position <= 8){
                if(vaticanReportCounter == 0){
                    popeFavors.get(vaticanReportCounter).activate();
                    vaticanReportCounter++;
                }
                else{
                    popeFavors.get(vaticanReportCounter).discard();
                    vaticanReportCounter++;
                }
            }
            //The player is in the second vatican report section, if he is there when the second vaticanReport has been
            //called, he can turn the second PopeFavorTile
            else if(position >= 12 && position <= 16){
                if(vaticanReportCounter == 1){
                    popeFavors.get(vaticanReportCounter).activate();
                    vaticanReportCounter++;
                }
                else{
                    popeFavors.get(vaticanReportCounter).discard();
                    vaticanReportCounter++;
                }
            }
            //The player is in the third vatican report section, if he is there when the third vaticanReport has been
            //called, he can turn the third PopeFavorTile
            else{
                if(vaticanReportCounter == 2){
                    popeFavors.get(vaticanReportCounter).activate();
                    vaticanReportCounter++;
                }
                else{
                    popeFavors.get(vaticanReportCounter).discard();
                    vaticanReportCounter++;
                }
            }
        }
    }

    @Override
    public int getWinPoints() {
        int pftPoints = 0;
        for(int i = 0; i < 3; i++){
            pftPoints += popeFavors.get(i).getWinPoints();
        }
        return winPoints + pftPoints;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus() {

    }
}
