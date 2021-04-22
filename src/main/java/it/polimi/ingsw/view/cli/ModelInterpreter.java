package it.polimi.ingsw.view.cli;

import java.util.Arrays;

public class ModelInterpreter {

    private ViewableFactory items;
    private Screen screen;

    public void update(byte[] status){
        //first byte in the status indicates
        if(status[0] <= 3){
            updatePlayer(status[0], status);
        }
        else if(status[0] == 4){
            updateMarketplace(status);
        }
        else if(status[0] == 5){
            updateDevelopmentGrid(status);
        }
    }



    private void updatePlayer(int player, byte[] status){
        items.get(player, COFFER).update(Arrays.copyOfRange(status, 1, 5));
        items.get(player, WAREHOUSE).update(Arrays.copyOfRange(status, 6, 20));
        items.get(player, DEV_SPACE1).update(Arrays.copyOfRange(status, 21, 38));
        items.get(player, DEV_SPACE2).update(Arrays.copyOfRange(status, 39, 56));
        items.get(player, DEV_SPACE3).update(Arrays.copyOfRange(status, 57, 74));
        items.get(player, PAYCHECK).update(Arrays.copyOfRange(status, 75, 84));
        items.get(player, BASE_PRODUCTION).update(Arrays.copyOfRange(status, 85, 103));
        //TODO ........

        screen.refresh();
    }

}
