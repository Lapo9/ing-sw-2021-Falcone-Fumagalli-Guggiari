package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.LeaderAbility;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.view.ModelInterpreter;
import it.polimi.ingsw.view.cli.viewables.ViewableFactory;

import java.util.Arrays;

import static it.polimi.ingsw.view.cli.ViewableId.*;

/**
 * Class responsible for the application of the updates to the viewables sent by the model.
 */
public class ModelInterpreterCLI implements ModelInterpreter {

    private ViewableFactory items;
    private ControllerInterpreter controllerInterpreter;
    private OfflineInfo offlineInfo;


    /**
     * Creates the model interpreter, and attaches to it the ViewableFactory and the ControllerInterpreter
     * @param viewableFactory Place where the model interpreter can find all of the existing viewables, in order to be able to updated their values
     * @param controllerInterpreter Object responsible to manage to screen
     */
    public ModelInterpreterCLI(ViewableFactory viewableFactory, ControllerInterpreter controllerInterpreter, OfflineInfo offlineInfo) {
        this.items = viewableFactory;
        this.controllerInterpreter = controllerInterpreter;
        this.offlineInfo = offlineInfo;
    }


    @Override
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

        //check if the leaders are activated and are producers. This is needed for the offline info about active productions
        offlineInfo.setLeaderStatus(1, dataToStatus(status[107], status[108]));
        offlineInfo.setLeaderStatus(2, dataToStatus(status[122], status[123]));

        items.update(player, ViewableId.COFFER, Arrays.copyOfRange(status, 1, 5));
        items.update(player, ViewableId.WAREHOUSE, Arrays.copyOfRange(status, 6, 21));
        items.update(player, DEVELOPMENT_SPACE_GRID, Arrays.copyOfRange(status, 21, 75));
        items.update(player, PAYCHECK_STRONGBOX, Arrays.copyOfRange(status, 75, 80));
        items.update(player, PAYCHECK_DEPOTS, Arrays.copyOfRange(status, 80, 85));
        items.update(player, BASE_PRODUCTION, Arrays.copyOfRange(status, 85, 103));
        items.update(4, FAITH_TRACK, new int[] {status[0], status[103], status[104], status[105], status[106]});
        items.update(player, UNASSIGNED_MARBLES, Arrays.copyOfRange(status, 137, 143));
        items.update(player, LEADER_CARD_SPACE, Arrays.copyOfRange(status, 107, 137));
        items.update(player, LEADER_PICK, Arrays.copyOfRange(status, 143, 203));

    }



    private static OfflineInfo.LeaderStatus dataToStatus(int id, int status){
        LeaderAbility la = LeaderCard.getAbility(id);

        if(status == 0){
            return OfflineInfo.LeaderStatus.INACTIVE;
        }
        if (status == 2){
            return OfflineInfo.LeaderStatus.DISCARDED;
        }
        if (LeaderCard.getAbility(id) instanceof Producer){
            return OfflineInfo.LeaderStatus.PRODUCER;
        }
        if (LeaderCard.getAbility(id) instanceof Depot){
            return OfflineInfo.LeaderStatus.DEPOT;
        }
        return OfflineInfo.LeaderStatus.OTHER;
    }



    /*
    Updates the marketplace viewable.
     */
    private void updateMarketplace(int[] status){
        items.update(4, MARKETPLACE, Arrays.copyOfRange(status, 1, status.length));
    }


    /*
    Updates the development grid viewable.
     */
    private void updateDevelopmentGrid(int[] status){
        items.update(4, DEVELOPMENT_GRID, Arrays.copyOfRange(status, 1, status.length));
    }

}
