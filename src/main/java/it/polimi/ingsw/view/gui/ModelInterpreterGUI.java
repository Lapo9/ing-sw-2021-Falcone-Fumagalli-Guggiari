package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.LeaderAbility;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.view.ModelInterpreter;
import it.polimi.ingsw.view.OfflineInfo;
import it.polimi.ingsw.view.gui.controllers.SceneController;

import java.util.ArrayList;

/**
 * This class is responsible for receiving updates from the model and forward them to the observer SceneController(s)
 */
public class ModelInterpreterGUI implements ModelInterpreter {

    private ArrayList<SceneController> toNotify = new ArrayList<>();
    private OfflineInfo offlineInfo;



    public ModelInterpreterGUI(OfflineInfo offlineInfo){
        this.offlineInfo = offlineInfo;
    }


    /**
     * Sends the model update to all of the SceneController(s) subscribed
     * @param update Model update
     */
    @Override
    public synchronized void update(int[] update) {
        //update offline info for the leaders
        if(update[0] == offlineInfo.getPlayerOrder(offlineInfo.getYourName()) - 1) {
            offlineInfo.setLeaderStatus(1, dataToStatus(update[107], update[108]));
            offlineInfo.setLeaderStatus(2, dataToStatus(update[122], update[123]));
        }

        for (SceneController sc : toNotify){
            sc.update(update);
        }
    }


    /**
     * Returns the leader status based on its id and current status
     * @param id Leader id
     * @param status Leader status ad defined in Dashboard.getStatus()
     * @return Leader status
     */
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


    /**
     * Attaches a SceneController to the observer list of this object
     * @param controller SceneController to attach
     */
    public void attach(SceneController controller){
        toNotify.add(controller);
    }


}
