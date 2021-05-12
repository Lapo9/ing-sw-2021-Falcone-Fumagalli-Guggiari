package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;
import it.polimi.ingsw.view.cli.ViewableId;
import it.polimi.ingsw.view.cli.viewables.SupplyContainer;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class responsible for the creation of vieawables and for their census.
 */
public class ViewableFactory {

    private ArrayList<HashMap<ViewableId, Viewable>> items = new ArrayList<>();

    /**
     * Constructor
     */
    public ViewableFactory() {
        items.add(new HashMap<>()); //player 1
        items.add(new HashMap<>()); //player 2
        items.add(new HashMap<>()); //player 3
        items.add(new HashMap<>()); //player 4
        items.add(new HashMap<>()); //faith track
        items.add(new HashMap<>()); //marketplace
        items.add(new HashMap<>()); //development grid
    }

    //TODO create method to build all of the implementations of Viewable, and when build one, add it to the items map
    public SupplyContainer buildSupplyContainer(int player, ViewableId viewableId, String name) {
        SupplyContainer res = new SupplyContainer(name);
        items.get(player-1).put(viewableId, res);
        return res;
    }


    //TODO remove test
    public TestViewable buildTestViewable(int player, ViewableId viewableId){
        TestViewable res = new TestViewable();
        items.get(player-1).put(viewableId, res);
        return res;
    }


    public Warehouse buildWarehouse(int player, ViewableId viewableId) {
        Warehouse res = new Warehouse();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    public MarbleContainer buildMarbleContainer(int player, ViewableId viewableId){
        MarbleContainer res = new MarbleContainer();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    public Production buildProduction (int player, ViewableId viewableId){
        Production res = new Production();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    public DevelopmentCard buildDevelopmentCard (int player, ViewableId viewableId) {
        DevelopmentCard res = new DevelopmentCard();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    public FaithTrack buildFaithTrack (ViewableId viewableId) {
        FaithTrack res = new FaithTrack();
        items.get(4).put(viewableId, res);
        return res;
    }

    /**
     * Updates the specified viewable with the specified int array.
     * @param player Player who owns the viewable
     * @param id Id of the viewable
     * @param update New values to insert to the viewable
     */
    public void update(int player, ViewableId id, int[] update){
        items.get(player).get(id).update(update);
    }

}
