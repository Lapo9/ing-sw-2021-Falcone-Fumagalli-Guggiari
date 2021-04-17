package it.polimi.ingsw.model;

import java.util.ArrayList;
import it.polimi.ingsw.model.PopeFavorTile;

/**
 * The FaithTrack class represents the faith track on the player's personal board
 */

public class FaithTrack implements WinPointsCountable, HasStatus{

    private int position = 0;
    private int winPoints = 0;
    private int vaticanReportCounter = 0;               //counts how many times a vatican report has been issued in the game
    private ArrayList<PopeFavorTile> popeFavors;
    private ArrayList<FaithTrackTiles> track;

    /**
     * The FaithTrackTiles class represents a tile of the FaithTrack
     */
    private class FaithTrackTiles{
        private final int points;
        private final int number;
        private boolean isPopeSpace;

        /**
         * Class constructor
         * @param num is the tiles number
         */
        public FaithTrackTiles(int num){
            number = num;
            switch(num){
                case 3:
                    points = 1;
                    isPopeSpace = false;
                    break;
                case 6:
                    points = 2;
                    isPopeSpace = false;
                    break;
                case 8:
                case 16:
                    points = 0;
                    isPopeSpace = true;
                    break;
                case 9:
                    points = 4;
                    isPopeSpace = false;
                    break;
                case 12:
                    points = 6;
                    isPopeSpace = false;
                    break;
                case 15:
                    points = 9;
                    isPopeSpace = false;
                    break;
                case 18:
                    points = 12;
                    isPopeSpace = false;
                    break;
                case 21:
                    points = 16;
                    isPopeSpace = false;
                    break;
                case 24:
                    points = 20;
                    isPopeSpace = true;
                    break;
                default:
                    points = 0;
                    isPopeSpace = false;
            }
        }
    }

    /**
     * Class constructor
     */
    public FaithTrack(){
        popeFavors = new ArrayList<PopeFavorTile>();
        for(int i=2; i<5; i++)
            popeFavors.add(new PopeFavorTile(i));
        track = new ArrayList<FaithTrackTiles>();
        for(int i=0; i<25; i++)
            track.add(new FaithTrackTiles(i));

    }

    /**
     * Moves the player position ahead of num tiles in the FaithTrack
     * @param num is the number of steps taken by the player
     * @return true if a vaticanReport has been issued
     */
    public boolean goAhead(int num){
        boolean vr = false;
        for(int i = 0; i < num; i++) {
            vr |= goAhead();
        }
        return vr;
    }

    /*
     * Moves the player one tile ahead in the FaithTrack
     * @return true is a vaticanReport has been issued
     */
    private boolean goAhead(){
        boolean vr = false;
        position++;
        // You can activate a vaticanReport only if you are the first player to reach the PopeSpace tile
        if(track.get(position).isPopeSpace &&
                ((position == 8 && vaticanReportCounter == 0) ||(position == 16 && vaticanReportCounter == 1)||
                        (position == 24 && vaticanReportCounter == 2))) {
            vaticanReport();
            vr = true;
        }
        winPoints += track.get(position).points;
        return vr;
    }

    /**
     * Triggers a vatican report
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

    public int getPosition(){
        return position;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        status.add(position);

        for (int i = 0; i<popeFavors.size(); ++i){
            status.addAll(popeFavors.get(i).getStatus());
        }

        return status;
    }
}
