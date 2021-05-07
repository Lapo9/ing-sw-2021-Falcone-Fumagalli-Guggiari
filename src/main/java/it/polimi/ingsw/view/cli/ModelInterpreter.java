package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.LeaderCard;
import it.polimi.ingsw.view.cli.viewables.ViewableFactory;

import java.util.Arrays;

/**
 * Class responsible for the application of the updates to the viewables sent by the model.
 */
public class ModelInterpreter {

    private ViewableFactory items;
    private ControllerInterpreter controllerInterpreter;
    private OfflineInfo offlineInfo;


    /**
     * Creates the model interpreter, and attaches to it the ViewableFactory and the ControllerInterpreter
     * @param viewableFactory Place where the model interpreter can find all of the existing viewables, in order to be able to updated their values
     * @param controllerInterpreter Object responsible to manage to screen
     */
    public ModelInterpreter(ViewableFactory viewableFactory, ControllerInterpreter controllerInterpreter, OfflineInfo offlineInfo) {
        this.items = viewableFactory;
        this.controllerInterpreter = controllerInterpreter;
        this.offlineInfo = offlineInfo;
    }


    /**
     * Updates all of the viewables according to the new status.
     * @param status New values for the viewables.
     */
    public synchronized void update(int[] status){
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


    /*
    Updates the viewables related to the specified player.
     */
    private void updatePlayer(int player, int[] status){
        items.update(player, ViewableId.COFFER, Arrays.copyOfRange(status, 1, 5));
        /* TODO add when we have the viewables
        items.update(player, ViewableId.WAREHOUSE, Arrays.copyOfRange(status, 6, 20));
        items.update(player, DEV_SPACE1, Arrays.copyOfRange(status, 21, 38));
        items.update(player, DEV_SPACE2, Arrays.copyOfRange(status, 39, 56));
        items.update(player, DEV_SPACE3, Arrays.copyOfRange(status, 57, 74));
        items.update(player, PAYCHECK, Arrays.copyOfRange(status, 75, 84));
        items.update(player, BASE_PRODUCTION, Arrays.copyOfRange(status, 85, 103));
        */

        //check if the leaders are activated and are producers. This is needed for the offline info about active productions
        //FIXME adding the missing functions to the LeaderCard class
        //offlineInfo.setLeaderProducer(1, LeaderCard.getAbility(status[106]) == LeaderCard.LeaderAbility.PRODUCER && status[107] == 1);
        //offlineInfo.setLeaderProducer(2, LeaderCard.getAbility(status[121]) == LeaderCard.LeaderAbility.PRODUCER && status[102] == 1);

        items.update(player, ViewableId.TEST, status); //TODO test
        //if user has auto-refresh on, then update his screen
        if(offlineInfo.isAutoRefresh()) {
            controllerInterpreter.execute("refresh");
        }
    }


    /*
    Updates the marketplace viewable.
     */
    private void updateMarketplace(int[] status){
        //TODO
    }


    /*
    Updates the development grid viewable.
     */
    private void updateDevelopmentGrid(int[] status){
        //TODO
    }

}
