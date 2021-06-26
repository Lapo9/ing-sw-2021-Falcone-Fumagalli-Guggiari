package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.Viewable;
import it.polimi.ingsw.view.cli.ViewableId;
import it.polimi.ingsw.view.cli.viewables.SupplyContainer;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class responsible for the creation of viewables and for their census.
 */
public class ViewableFactory {

    private ArrayList<HashMap<ViewableId, Viewable>> items = new ArrayList<>();
    private OfflineInfo offlineInfo;

    /**
     * Constructor
     */
    public ViewableFactory(OfflineInfo offlineInfo) {
        this.offlineInfo = offlineInfo;

        items.add(new HashMap<>()); //player 1
        items.add(new HashMap<>()); //player 2
        items.add(new HashMap<>()); //player 3
        items.add(new HashMap<>()); //player 4
        items.add(new HashMap<>()); //others
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @param name of the player
     * @return the viewable to build
     */
    public SupplyContainer buildSupplyContainer(int player, ViewableId viewableId, String name) {
        SupplyContainer res = new SupplyContainer(name);
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public Warehouse buildWarehouse(int player, ViewableId viewableId){
        Warehouse res = new Warehouse();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public MarbleContainer buildMarbleContainer(int player, ViewableId viewableId){
        MarbleContainer res = new MarbleContainer();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public Production buildProduction (int player, ViewableId viewableId){
        Production res = new Production();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public DevelopmentCard buildDevelopmentCard (int player, ViewableId viewableId) {
        DevelopmentCard res = new DevelopmentCard();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds players' viewable and puts it in the ArrayList items
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public FaithTrack buildFaithTrack (ViewableId viewableId) {
        FaithTrack res = new FaithTrack();
        items.get(4).put(viewableId, res);
        return res;
    }

    /**
     * Builds players' viewable and puts it in the ArrayList items
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public Marketplace buildMarketplace (ViewableId viewableId) {
        Marketplace res = new Marketplace();
        items.get(4).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public LeaderCard buildLeaderCard (int player, ViewableId viewableId) {
        LeaderCard res = new LeaderCard();
        items.get(player - 1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public DevelopmentSpace buildDevelopmentSpace (int player, ViewableId viewableId) {
        DevelopmentSpace res = new DevelopmentSpace();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public DevelopmentGridCard buildDevelopmentGridCard (int player, ViewableId viewableId) {
        DevelopmentGridCard res = new DevelopmentGridCard();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds players' viewable and puts it in the ArrayList items
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public DevelopmentGrid buildDevelopmentGrid (ViewableId viewableId){
        DevelopmentGrid res = new DevelopmentGrid();
        items.get(4).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public ActiveProductions buildActiveProductions (int player, ViewableId viewableId) {
        ActiveProductions res = new ActiveProductions();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public BaseProduction buildBaseProduction (int player, ViewableId viewableId) {
        BaseProduction res = new BaseProduction();
        items.get(player-1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @param type of the leader card
     * @return the viewable to build
     */
    public LeaderCardSpace buildLeaderCardSpace (int player, ViewableId viewableId, boolean type) {
        LeaderCardSpace res = new LeaderCardSpace(type);
        items.get(player - 1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public LeaderPick buildLeaderPick (int player, ViewableId viewableId) {
        LeaderPick res = new LeaderPick();
        items.get(player - 1).put(viewableId, res);
        return res;
    }

    /**
     * Builds player's viewable and puts it in the ArrayList items
     * @param player number of the player who owns the viewable
     * @param viewableId id of the viewable
     * @return the viewable to build
     */
    public DevelopmentSpaceGrid buildDevelopmentSpacerGrid(int player, ViewableId viewableId) {
        DevelopmentSpaceGrid res = new DevelopmentSpaceGrid();
        items.get(player - 1).put(viewableId, res);
        return res;
    }

    public ActionTile buildActionTile(ViewableId viewableId){
        ActionTile actionTile = new ActionTile();
        items.get(4).put(viewableId, actionTile);
        return actionTile;
    }

    /**
     * Updates the specified viewable with the specified int array.
     * @param player Player who owns the viewable
     * @param id Id of the viewable
     * @param update New values to insert to the viewable
     */
    public synchronized void update(int player, ViewableId id, int[] update){
        //the first item element is the owner of this cli. If the owner is not actually the first player, we have to shift
        int you = offlineInfo.getPlayerOrder(offlineInfo.getYourName())-1;
        if(player == you){
            player = 0;
        }
        else if(player < you){
            player++;
        }

        try {
            items.get(player).get(id).update(update, offlineInfo);
        } catch (NoSuchMethodException nsme){

            nsme.printStackTrace();
        } catch (NullPointerException e) {
        }
    }

}
