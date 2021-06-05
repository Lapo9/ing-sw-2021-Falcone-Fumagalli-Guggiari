package it.polimi.ingsw.model;

/**
 * Contains all the colors of the marble.
 */
public enum MarbleColor {

    YELLOW, BLUE, GREY, WHITE, VIOLET, RED;

    /**
     * Given a string containing a marble color, returns the corresponding marble color.
     * @param s a string to convert
     * @return the right marble color
     */
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

    /**
     * Given a number, returns the corresponding marble color.
     * @param num an integer that represents a marble color(0: blue, 1: grey, 2: red, 3: violet, 4: white, 5: yellow)
     * @return the right marble color
     */
    public static MarbleColor numberToColor(Integer num) {
        if (num == 5){
            return YELLOW;
        }
        else if (num == 0){
            return BLUE;
        }
        else if (num == 1){
            return GREY;
        }
        else if (num == 4){
            return WHITE;
        }
        else if (num == 3){
            return VIOLET;
        }
        else if (num == 2){
            return RED;
        }
        return null;
    }
}
