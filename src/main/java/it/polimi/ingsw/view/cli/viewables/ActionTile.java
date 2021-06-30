package it.polimi.ingsw.view.cli.viewables;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.BOLD;

/**
 * Represents the action tile for the game in the solo mode.
 */
public class ActionTile implements Viewable {
    private int index = -1;

    ActionTile() {}

    /**
     * Updates the viewable with the last extracted tile.
     * @param update status array made by 7 integers that represents the action tile index
     */
    @Override
    public void update(int[] update) {
        index = update[0];
    }

    /**
     * Prints the last extracted tile.
     * @return string with the last extracted tile
     */
    @Override
    public String toString() {
        return getTileInfo();
    }

    /**
     * Returns a different string, according to the last extracted tile.
     * @return string with a message
     */
    private String getTileInfo() {
        if (index == -1){
            return "";
        }

        String tmp = BOLD("Lorenzo's turn: \n");

        tmp = tmp.concat("Lorenzo picked -> ");

        if(index == 0) {   //-2 green cards
            tmp = tmp.concat("remove two\033[0;32m green cards\033[0m from the development grid");
        } else if(index == 1) {  //-2 blue cards
            tmp = tmp.concat("remove two\033[0;36m blue cards\033[0m from the development grid");
        } else if(index == 2) {  //-2 yellow cards
            tmp = tmp.concat("remove two\033[0;33m yellow cards\033[0m from the development grid");
        } else if(index == 3) {  //-2 violet cards
            tmp = tmp.concat("remove two\033[0;35m violet cards\033[0m from the development grid");
        } else if(index == 4) {  //+2 on the faith track
            tmp = tmp.concat("move the black cross 2 spaces forward on the faith track");
        } else if(index == 5) {  //+1 on the faith track and shuffle
            tmp = tmp.concat("move the black cross 1 space forward on the faith track and shuffle the tokens");
        }

        tmp = tmp.concat("\n\n");

        return tmp;
    }
}
