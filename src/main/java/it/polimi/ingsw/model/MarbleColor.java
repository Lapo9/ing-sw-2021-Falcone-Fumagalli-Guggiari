package it.polimi.ingsw.model;

/**
 * Contains all the colors of the marble.
 */
public enum MarbleColor {

    YELLOW, BLUE, GREY, WHITE, VIOLET, RED;



    public static MarbleColor stringToColor(String s){
        if (s.equals("yellow")){
            return YELLOW;
        }
        else if (s.equals("blue")){
            return BLUE;
        }
        else if (s.equals("grey")){
            return GREY;
        }
        else if (s.equals("white")){
            return WHITE;
        }
        else if (s.equals("violet")){
            return VIOLET;
        }
        else if (s.equals("red")){
            return RED;
        }
        return null;
    }
}
