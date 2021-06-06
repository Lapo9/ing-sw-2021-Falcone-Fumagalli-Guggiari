package it.polimi.ingsw.model;

import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;
import static it.polimi.ingsw.model.WarehouseObjectType.*;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.controller.ModelObserver;
import it.polimi.ingsw.model.depots.DepotsManager;
import it.polimi.ingsw.model.depots.Warehouse;
import it.polimi.ingsw.model.development.*;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.faith_track.FaithTrack;
import it.polimi.ingsw.model.leaders.LeaderCard;
import it.polimi.ingsw.model.leaders.LeadersPick;
import it.polimi.ingsw.model.leaders.LeadersSpace;
import it.polimi.ingsw.model.match_items.DevelopmentGrid;
import it.polimi.ingsw.model.match_items.LeadersList;
import it.polimi.ingsw.model.match_items.MarketDirection;
import it.polimi.ingsw.model.match_items.Marketplace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The dashboard is the access point of the controller to the model.
 * It exposes a function for all of the possible actions a player can perform during a match.
 * Generally, when the action performed goes well, nothing is returned, but if the action violates any of the game rule an exception is thrown.
 */
public class Dashboard implements HasStatus{

    private final String name;
    private Marketplace marketplace;
    private DevelopmentGrid developmentGrid;
    private final Warehouse warehouse = new Warehouse();
    private final SupplyContainer coffer = new SupplyContainer(onlyFrom(DepotID.SourceType.STRONGBOX).and(specificType(WarehouseObjectType.FAITH_MARKER).negate()));
    private final MutableProduction baseProduction = new MutableProduction(2, 1);
    private final FaithTrack faithTrack = new FaithTrack();
    private final LeadersSpace leadersSpace = new LeadersSpace();
    private final Developments developments = new Developments();
    private LeadersList leadersList;
    private final LeadersPick leadersPick = new LeadersPick();
    private MarbleContainer unassignedSupplies = new MarbleContainer(0,0,0,0,0,0);
    private final Paycheck paycheck = new Paycheck();
    private boolean inkwell;
    private final ProductionManager productionManager = new ProductionManager(developments, baseProduction, leadersSpace);
    private final DepotsManager depotsManager = new DepotsManager(warehouse, leadersSpace);
    private final ActionTilesStack actionTilesStack = new ActionTilesStack();
    private int blackCrossPosition = -1;
    private ArrayList<ModelObserver> observers = new ArrayList<ModelObserver>();
    private int boughtCards = 0;

    private final HashMap<DepotID.DepotType, AcceptsSupplies> containers = new HashMap<>();


    /**
     * Creates an empty dashboard specifying if the player is the first one to play, and assign to it the match marketplace and the match development grid.
     * @param inkwell is the player the first one to play?
     * @param marketplace match marketplace, to collect the supplies from
     * @param developmentGrid match development grid, to buy the development cards from
     * @param name player nickname
     * @param leadersList
     * @param isSinglePlayer
     */
    public Dashboard(boolean inkwell, Marketplace marketplace, DevelopmentGrid developmentGrid, String name, LeadersList leadersList, boolean isSinglePlayer){
        this.inkwell = inkwell;
        this.marketplace = marketplace;
        this.developmentGrid = developmentGrid;
        this.leadersList = leadersList;

        this.name = name;

        blackCrossPosition = isSinglePlayer ? 0 : -1;

        containers.put(DepotID.DepotType.WAREHOUSE, depotsManager);
        containers.put(DepotID.DepotType.LEADER_DEPOT, depotsManager);
        containers.put(DepotID.DepotType.DEVELOPMENT, productionManager);
        containers.put(DepotID.DepotType.LEADER_PRODUCTION, productionManager);
        containers.put(DepotID.DepotType.COFFER, coffer);
        containers.put(DepotID.DepotType.PAYCHECK, paycheck);
        containers.put(DepotID.DepotType.BASE_PRODUCTION, productionManager);
    }


    /**
     * Creates a dashboard given its status.
     * @param status of the dashboard
     * @param name player nickname
     */
    public Dashboard(int[] status, Marketplace marketplace, DevelopmentGrid developmentGrid, String name, LeadersList leadersList, boolean isSinglePlayer) throws SupplyException, DevelopmentException, LeaderException, NoSuchMethodException {
        this.developmentGrid = developmentGrid;
        this.marketplace = marketplace;
        this.leadersList = leadersList;

        boughtCards = status[status.length-2];

        blackCrossPosition = isSinglePlayer ? status[status.length-1] : -1;

        containers.put(DepotID.DepotType.WAREHOUSE, depotsManager);
        containers.put(DepotID.DepotType.LEADER_DEPOT, depotsManager);
        containers.put(DepotID.DepotType.DEVELOPMENT, productionManager);
        containers.put(DepotID.DepotType.LEADER_PRODUCTION, productionManager);
        containers.put(DepotID.DepotType.COFFER, coffer);
        containers.put(DepotID.DepotType.PAYCHECK, paycheck);
        containers.put(DepotID.DepotType.BASE_PRODUCTION, productionManager);

        if(status[0] == 0)
            inkwell = true;
        this.name = name;

        int count = 0;
        count = status[1];
        while(count > 0) {
            coffer.addSupply(COIN, DepotID.COFFER);
            count--;
        }
        count = status[2];
        while(count > 0) {
            coffer.addSupply(SERVANT, DepotID.COFFER);
            count--;
        }
        count = status[3];
        while(count > 0) {
            coffer.addSupply(SHIELD, DepotID.COFFER);
            count--;
        }
        count = status[4];
        while(count > 0) {
            coffer.addSupply(STONE, DepotID.COFFER);
            count--;
        }

        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(status[143]));
        leaderCards.add(new LeaderCard(status[158]));
        leaderCards.add(new LeaderCard(status[173]));
        leaderCards.add(new LeaderCard(status[188]));
        leadersPick.fillWithList(leaderCards);

        Pair<WarehouseObjectType, Integer> tmp = getContainedSupplies(Arrays.copyOfRange(status, 6, 11));
        if(tmp.first != null)
            trustedAddSupply(DepotID.WAREHOUSE1, tmp.first);
        tmp = getContainedSupplies(Arrays.copyOfRange(status, 11, 16));
        if(tmp.first != null) {
            trustedAddSupply(DepotID.WAREHOUSE2, tmp.first);
            if(tmp.second == 2)
                trustedAddSupply(DepotID.WAREHOUSE2, tmp.first);
        }
        tmp = getContainedSupplies(Arrays.copyOfRange(status, 16, 21));
        if(tmp.first != null) {
            trustedAddSupply(DepotID.WAREHOUSE3, tmp.first);
            if(tmp.second != 1) {
                trustedAddSupply(DepotID.WAREHOUSE3, tmp.first);
                if(tmp.second == 3)
                    trustedAddSupply(DepotID.WAREHOUSE3, tmp.first);
            }
        }

        ArrayList<WarehouseObjectType> container;
        if(status[21] != 0)
            developments.addCardToSpace(0, new DevelopmentCard(status[21]));
        if(status[22] != 0)
            developments.addCardToSpace(0, new DevelopmentCard(status[22]));
        if(status[23] != 0)
            developments.addCardToSpace(0, new DevelopmentCard(status[23]));
        container = getSupplies(Arrays.copyOfRange(status, 34, 39));
        while(!container.isEmpty()) {
            developments.addSupply(DepotID.DEVELOPMENT1, container.get(0), DepotID.BASE_PRODUCTION);
            container.remove(0);
        }
        if(status[39] != 0)
            developments.addCardToSpace(1, new DevelopmentCard(status[39]));
        if(status[40] != 0)
            developments.addCardToSpace(1, new DevelopmentCard(status[40]));
        if(status[41] != 0)
            developments.addCardToSpace(1, new DevelopmentCard(status[41]));
        container = getSupplies(Arrays.copyOfRange(status, 52, 57));
        while(!container.isEmpty()) {
            developments.addSupply(DepotID.DEVELOPMENT2, container.get(0), DepotID.BASE_PRODUCTION);
            container.remove(0);
        }
        if(status[57] != 0)
            developments.addCardToSpace(2, new DevelopmentCard(status[57]));
        if(status[58] != 0)
            developments.addCardToSpace(2, new DevelopmentCard(status[58]));
        if(status[59] != 0)
            developments.addCardToSpace(2, new DevelopmentCard(status[59]));
        container = getSupplies(Arrays.copyOfRange(status, 70, 75));
        while(!container.isEmpty()) {
            developments.addSupply(DepotID.DEVELOPMENT3, container.get(0), DepotID.BASE_PRODUCTION);
            container.remove(0);
        }

        container = getSupplies(Arrays.copyOfRange(status, 75, 80));
        while(!container.isEmpty()) {
            paycheck.addSupply(container.get(0), DepotID.COFFER);
            container.remove(0);
        }
        container = getSupplies(Arrays.copyOfRange(status, 80, 86));
        while(!container.isEmpty()) {
            paycheck.addSupply(container.get(0), DepotID.WAREHOUSE3);
            container.remove(0);
        }

        swapBaseProduction(0, numberToType(status[90]));
        swapBaseProduction(1, numberToType(status[91]));
        swapBaseProduction(2, numberToType(status[97]));
        container = getSupplies(Arrays.copyOfRange(status, 98, 103));
        while(!container.isEmpty()) {
            baseProduction.addSupply(container.get(0), DepotID.DEVELOPMENT2);
            container.remove(0);
        }

        for(int i = 0; i<status[103]; i++)
            goAheadDontTrigger();
        if(status[104] != 0) {
            if (status[104] == 1)
                faithTrack.activatePopeFavorTileTrusted(0);
            else
                faithTrack.discardPopeFavorTileTrusted(0);

            if(status[105] != 0) {
                if (status[105] == 1)
                    faithTrack.activatePopeFavorTileTrusted(1);
                else
                    faithTrack.discardPopeFavorTileTrusted(1);

                if(status[106] != 0) {
                    if (status[106] == 1)
                        faithTrack.activatePopeFavorTileTrusted(2);
                    else
                        faithTrack.discardPopeFavorTileTrusted(2);
                }
            }
        }

        pickLeader(findIndex(status, 1));
        if(status[108] != 0) {
            if(status[108] == 1)
                leadersSpace.activateLeaderCardTrusted(0);
            else
                leadersSpace.discardLeaderCardTrusted(0);
        }
        if(status[111] != 0)
            swapLeaderProduction(0, numberToType(status[111]));
        container = getSupplies(Arrays.copyOfRange(status, 112, 117));
        while(!container.isEmpty()) {
            productionManager.addSupply(DepotID.LEADER1, container.get(0), DepotID.BASE_PRODUCTION);
            container.remove(0);
        }
        container = getSupplies(Arrays.copyOfRange(status, 117, 122));
        while(!container.isEmpty()) {
            depotsManager.addSupply(DepotID.LEADER1, container.get(0), DepotID.BASE_PRODUCTION);
            container.remove(0);
        }
        pickLeader(findIndex(status, 2));
        if(status[123] != 0) {
            if(status[123] == 1)
                leadersSpace.activateLeaderCardTrusted(1);
            else
                leadersSpace.discardLeaderCardTrusted(1);
        }
        if(status[126] != 0)
            swapLeaderProduction(1, numberToType(status[126]));
        container = getSupplies(Arrays.copyOfRange(status, 127, 132));
        while(!container.isEmpty()) {
            productionManager.addSupply(DepotID.LEADER2, container.get(0), DepotID.BASE_PRODUCTION);
            container.remove(0);
        }
        container = getSupplies(Arrays.copyOfRange(status, 132, 137));
        while(!container.isEmpty()) {
            depotsManager.addSupply(DepotID.LEADER2, container.get(0), DepotID.BASE_PRODUCTION);
            container.remove(0);
        }

        unassignedSupplies.sum(new MarbleContainer(status[142], status[137], status[138], status[141], status[140], status[139]));
    }


    /**
     * Swaps 2 rows of the Warehouse. If it is not possible a SupplyException is thrown.
     * @param r1 row 1
     * @param r2 row 2
     * @throws SupplyException Cannot swap the 2 rows
     */
    public void swapWarehouseRows(int r1, int r2) throws SupplyException {
        warehouse.swapRows(r1, r2);
        notifyViews();
    }


    /**
     * Transfers the marbles contained in the selected column/row to the user unassigned objects.
     * @param dir vertical or horizontal
     * @param index column or row number
     */
    public void buySupplies(MarketDirection dir, int index) {
        clearProductions();
        clearPaycheck();
        unassignedSupplies = marketplace.obtain(dir, index);
        notifyViews();
    }


    /**
     * Returns how many non-red marbles are there in the unassigned supplies marble container.
     * @return How many non-red marbles are there in the unassigned supplies marble container.
     */
    public int getUnassignedSuppliesQuantity(){
        return unassignedSupplies.getQuantity(MarbleColor.WHITE, MarbleColor.VIOLET, MarbleColor.BLUE, MarbleColor.GREY, MarbleColor.YELLOW);
    }



    /**
     * Transforms a marble into the supply contained in the destination depot. If the transformation is not possible a MarbleException is thrown.
     * @param to destination depot
     * @param color the color of the marble
     * @throws SupplyException Destination is full
     * @throws MarbleException Destination cannot accept this color of marble
     * @throws NoSuchMethodException Leader hasn't a depot ability
     */
    public void assignMarble(DepotID to, MarbleColor color) throws SupplyException, MarbleException, NoSuchMethodException, LeaderException{
        if(unassignedSupplies.getQuantity(color) == 0) {throw new SupplyException(color.toString() + " marbles are finished :(");}

        depotsManager.addMarble(to, color);
        unassignedSupplies.removeMarble(color);

        notifyViews();
    }


    /**
     * Transforms one of the white marbles in the unassignedSupplies container to the specified color.
     * @param newColor Color to transform the white marble to.
     * @throws MarbleException No white marbles left or there isn't an active leader to perform the conversion.
     */
    public void transformWhiteMarble(MarbleColor newColor) throws MarbleException {
        //check if there is a white marble to transform
        if (unassignedSupplies.getQuantity(MarbleColor.WHITE) == 0){
            throw new MarbleException("No white marbles left");
        }

        for (int i = 0; i<2; ++i) {
            try {
                if (leadersSpace.getLeaderAbility(i).colorWhiteMarble() == newColor) {
                    unassignedSupplies.colorWhiteMarble(newColor);
                    notifyViews();
                    return;
                }
            } catch (NoSuchMethodException | LeaderException e){}
        }

        throw new MarbleException("Cannot transform the white marble to a " + newColor.toString() + " marble");
    }



    /**
     * Discards all the remaining marbles in the unassigned supply container.
     * If the marble is red, then the player goes ahead one tile on the faith track.
     * For each non-red discarded marble, all of the other player go ahead one tile on the faith track.
     * @return How many non-red marbles are discarded. Does the player triggered a vatican report?
     */
    public Pair<Integer, Boolean> discardSupplies(){
        int totalDiscarded = unassignedSupplies.getQuantity(MarbleColor.BLUE, MarbleColor.GREY, MarbleColor.VIOLET, MarbleColor.YELLOW);

        boolean vaticanReport = false;
        vaticanReport = faithTrack.goAhead(unassignedSupplies.getQuantity(MarbleColor.RED));

        unassignedSupplies.clear();

        notifyViews();

        return new Pair<>(totalDiscarded, vaticanReport);
    }


    /**
     * Moves a supply of the specified type from the from container to the to container.
     * The ID number of the containers is as follows:
     * 0 warehouse 1, 1 warehouse 2, 2 warehouse 3, 3 development 1, 4 development 2, 5 development 3, 6 leader 1, 7 leader 2
     * In case of any error, the resource is not moved and a SupplyException is thrown.
     * @param from Source container
     * @param to Destination container
     * @param type Type of resource to move
     * @throws SupplyException Thrown if the source doesn't have the specified type of resource, or if the destination cannot accept the resource
     */
    public void moveSupply(DepotID from, DepotID to, WarehouseObjectType type) throws SupplyException, NoSuchMethodException, LeaderException {
        if (from.equals(to)){
            return;
        }

        int index = 0;

        if(from.getType() == DepotID.DepotType.LEADER_PRODUCTION || from.getType() == DepotID.DepotType.DEVELOPMENT || from.getType() == DepotID.DepotType.BASE_PRODUCTION)
            index = productionManager.getSource(type);
        if(from.getType() == DepotID.DepotType.PAYCHECK)
            index = paycheck.getSource(type);

        //remove supply from specified container
        containers.get(from.getType()).removeSupply(from, type, to);

        if(to == DepotID.COFFER && (index == 1 || index == 3))
            from = DepotID.COFFER;

        //add supply to specified container, if you cannot, put supply back to original container and throw the exception
        try{
            containers.get(to.getType()).addSupply(to, type, from);
        } catch (SupplyException | LeaderException | NoSuchMethodException e) {
            containers.get(from.getType()).addSupply(from, type, from);
            throw e;
        }

        notifyViews();
    }


    /**
     * Adds a resource to a warehouse slot. Used at the beginning of the match to add the starting resources.
     * @param warehouseSlot Where to add the supply
     * @param type Type of the supply to add
     * @throws SupplyException Couldn't add the supply to the specified slot
     */
    public void trustedAddSupply(DepotID warehouseSlot, WarehouseObjectType type) throws SupplyException {
        if(warehouseSlot != DepotID.WAREHOUSE1 && warehouseSlot != DepotID.WAREHOUSE2 && warehouseSlot != DepotID.WAREHOUSE3) {
            throw new SupplyException("You can add only to warehouse slots");
        }

        depotsManager.addSupply(warehouseSlot, type, DepotID.WAREHOUSE1);

        notifyViews();
    }



    /**
     * Activates production in the specified production spaces, and stores the output in the coffer.
     * @param s1 Development space 1
     * @param s2 Development space 2
     * @param s3 Development space 3
     * @param l1 Leader 1 production (if leader 1 has that ability)
     * @param l2 Leader 2 production (if leader 2 has that ability)
     * @param base Production of the board
     * @return Returns if a vatican report has been issued as a result of the production of faith markers
     */
    public boolean produce(boolean s1, boolean s2, boolean s3, boolean l1, boolean l2, boolean base){
        SupplyContainer tmp = productionManager.produce(s1, s2, s3, l1, l2, base);

        //count faith markers
        int faithPoints = tmp.getQuantity(WarehouseObjectType.FAITH_MARKER);

        //remove faith markers
        for (int i = 0; i<faithPoints; ++i){
            try {
                tmp.removeSupply(WarehouseObjectType.FAITH_MARKER);
            } catch (SupplyException se) {System.out.print("BUG"); /*TODO this should never happen, so maybe fail the program*/}
        }

        //store outputs in the coffer
        coffer.sum(tmp);

        //go ahead in faith track
        boolean res = faithTrack.goAhead(faithPoints);
        notifyViews();
        return res;
    }


    /**
     * Checks if there is the exact number of supplies placed on the active production spaces in order to produce.
     * @param s1 Development space 1
     * @param s2 Development space 2
     * @param s3 Development space 3
     * @param l1 Leader 1 production (if leader 1 has that ability)
     * @param l2 Leader 2 production (if leader 2 has that ability)
     * @param base Production of the board
     * @throws SupplyException Thrown if in at least one supply space there isn't the correct amount of resources
     */
    public void checkProduction(boolean s1, boolean s2, boolean s3, boolean l1, boolean l2, boolean base) throws SupplyException {
        productionManager.checkProduction(s1, s2, s3, l1, l2, base);
    }


    /**
     * Tries to buy the card in the specified space of the grid, and places it in the specified development space on the dashboard.
     * @param column column of the selected card in the grid
     * @param row row of the selected card in the grid
     * @param space development space where to place the card
     * @throws SupplyException There isn't the exact number of supplies in the paycheck to buy the card
     * @throws DevelopmentException You cannot place the card you want to buy in the specified development space
     * @throws NoSuchCardException The space in the grid you selected contains no cards
     */
    public void buyDevelopment(int column, int row, int space) throws SupplyException, DevelopmentException, NoSuchCardException {
        ArrayList<Integer> buyableLevels = developments.buyableLevels(); //get what levels you can buy

        //check if you can buy a card of that level in that space
        if(buyableLevels.get(space) != developmentGrid.getLevel(column, row)){
            throw new DevelopmentException("Cannot buy a card of this level for the specified space");
        }

        //buy the card
        developments.addCardToSpace(space, developmentGrid.buyCard(column, row, paycheck, leadersSpace));
        paycheck.clearSupplies();
        boughtCards++;

        notifyViews();
    }


    /**
     * Returns the levels of the development card the player can buy
     * @return the levels of the development card the player can buy
     */
    public ArrayList<Integer> buyableDevelopmentLevels() {
        return developments.buyableLevels();
    }


    /**
     * Fills the leaders pick object with 4 random leaders
     */
    public void fillLeadersPicks(){
        leadersPick.fill(leadersList);
        notifyViews();
    }


    /**
     * Fills the leaders pick object with 4 given leaders.
     */
    public void fillLeadersPicksWithList(ArrayList<LeaderCard> list){
        leadersPick.fillWithList(list);
    }


    /**
     * Pick the specified leader among the 4 leaders picks.
     * @param index What leader to pick
     * @throws LeaderException 2 Leaders added yet.
     */
    public void pickLeader(int index) throws LeaderException{
        leadersPick.pick(index, leadersSpace);
        notifyViews();
    }



    /**
     * Adds the specified leader to the leader space
     * @param leader The leader to add
     * @throws LeaderException There is already the maximum number of leaders (2)
     */
    @Deprecated
    public void addLeader(LeaderCard leader) throws LeaderException{
        leadersSpace.addLeader(leader);
    }


    /**
     * Discards the specified leader and goes ahead one tile on the faith track
     * @param i which leader
     * @return Returns if a vatican report has been issued
     * @throws LeaderException The specified leader cannot be discarded (already discarded or active)
     */
    public boolean discardLeader(int i) throws LeaderException {
        leadersSpace.discardLeader(i);
        boolean res = faithTrack.goAhead(1);
        notifyViews();
        return res;
    }


    /**
     * Activates the specified leader.
     * @param i Leader to activate
     * @throws SupplyException Leader activation requirements aren't matched
     * @throws LeaderException The specified leader cannot be played (already discarded or active)
     */
    public void playLeader(int i) throws SupplyException, LeaderException {
        //put all the cards to their place
        clearProductions();
        clearPaycheck();

        leadersSpace.playLeader(i, new ResourceChecker(depotsManager, coffer, developments));

        notifyViews();
    }


    /**
     * Moves the player one tile ahead in the faith track.
     * @return Returns if a vatican report has been issued
     */
    public boolean goAhead(){
        boolean res = faithTrack.goAhead(1);
        notifyViews();
        return res;
    }


    /**
     * Moves the player one tile ahead in the faith track.
     * @return true a vatican report needs to be issued
     */
    public boolean goAheadDontTrigger(){
        boolean res = faithTrack.goAheadDontTrigger();
        notifyViews();
        return res;
    }


    /**
     * Triggers a vatican report.
     */
    public void vaticanReport(){
        faithTrack.vaticanReport();
        notifyViews();
    }


    /**
     * Swaps one of the mutable inputs/outputs of the base production.
     * @param i 0 = first input, 1 = second input, 2 = output
     * @param wot new supply to insert
     * @throws SupplyException A faith marker or no type supply type was added
     */
    public void swapBaseProduction(int i, WarehouseObjectType wot) throws SupplyException{
        if(wot == WarehouseObjectType.FAITH_MARKER){
            throw new SupplyException("Cannot add FAITH_MARKER base production input or output");
        }

        productionManager.swapBaseProduction(i, wot);
        notifyViews();
    }


    /**
     * Swaps the only mutable output in the specified leader
     * @param i number of leader
     * @param wot new supply to insert
     * @throws SupplyException A no type supply type was added
     * @throws NoSuchMethodException Specified leader is not a Producer
     * @throws LeaderException Specified leader is not active or is discarded
     */
    public void swapLeaderProduction(int i, WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException{
        productionManager.swapLeaderProduction(i, wot);
        notifyViews();
    }


    /**
     * Clears the specified depot, and reinsert the cleared supplies to their source.
     * @param id depot to clear
     * @throws NoSuchMethodException tried to clear a non Depot or Producer leader
     */
    public void clearDepot(DepotID id) throws NoSuchMethodException{
        Pair<SupplyContainer, SupplyContainer> destination;
        destination = containers.get(id.getType()).clearSupplies(id);

        coffer.sum(destination.second);
        depotsManager.allocate(destination.first);

        notifyViews();
    }


    /**
     * Clears all of the production depots, and reinsert the cleared supplies to their source.
     */
    public void clearProductions(){
        Pair<SupplyContainer, SupplyContainer> destination;
        destination = productionManager.clearSupplies();

        if(destination.second.getQuantity() != 0){
            coffer.sum(destination.second);
        }
        if(destination.first.getQuantity() != 0) {
            depotsManager.allocate(destination.first);
        }

        notifyViews();
    }


    /**
     * Clears the paycheck, and reinsert the cleared supplies to their source.
     */
    public void clearPaycheck(){
        Pair<SupplyContainer, SupplyContainer> destination;
        destination = paycheck.clearSupplies();

        coffer.sum(destination.second);
        depotsManager.allocate(destination.first);

        notifyViews();
    }


    /**
     * Returns if the current dashboard belongs to the player who started the match.
     * @return If the current dashboard belongs to the player who started the match
     */
    public boolean hasInkwell(){
        return inkwell;
    }


    /**
     * Assigns the inkwell to the player
     */
    public void giveInkwell(){
        inkwell = true;
    }


    /**
     * Checks if the match ended, so if the player got to the last tile in the faith track or id he bought at least 7 development cards.
     * @return If the player triggered an end match condition.
     */
    public boolean isMatchEnded(){
        return boughtCards >= 7 || faithTrack.getPosition() >= 24;
    }


    /**
     * Returns the depots that can receive the specified supply.
     * @param from Source of the supply
     * @param type Type of the supply
     * @return The depots that can receive the specified supply
     */
    public ArrayList<DepotID> getAllowedDepots(DepotID from, WarehouseObjectType type) {
        ArrayList<DepotID> res = new ArrayList<>();

        int index = 0;

        if(from.getType() == DepotID.DepotType.LEADER_PRODUCTION || from.getType() == DepotID.DepotType.DEVELOPMENT || from.getType() == DepotID.DepotType.BASE_PRODUCTION)
            index = productionManager.getSource(type);
        if(from.getType() == DepotID.DepotType.PAYCHECK)
            index = paycheck.getSource(type);

        if(index == 1)
            res.add(DepotID.COFFER);

        res.addAll(depotsManager.getAllowedDepots(from, type));

        res.addAll(productionManager.getAllowedDepots(from, type));

        if (paycheck.additionAllowed(type, from)){
            res.add(DepotID.PAYCHECK);
        }

        if (coffer.additionAllowed(type, from)){
            res.add(DepotID.COFFER);
        }

        if(index == 3) {
            res = new ArrayList<>();
            res.add(DepotID.COFFER);
        }

        return res;
    }


    //SINGLE PLAYER METHOD
    /**
     * Extracts one tile from the stack, and performs the associated action.
     * @return True if the match is ended, so if the black cross finished the faith track or if all the cards of one color are gone.
     */
    public boolean extractActionTile(){
        ActionTilesStack.ActionTile at = actionTilesStack.extractTile();
        switch (at){
            case YELLOW:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.YELLOW);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.YELLOW);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case GREEN:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.GREEN);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.GREEN);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case BLUE:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.BLUE);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.BLUE);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case VIOLET:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.VIOLET);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.VIOLET);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case PLUS_2:
                for(int i=0; i<2; ++i) {
                    blackCrossPosition++;
                    if (blackCrossPosition == 8 || blackCrossPosition == 16 || blackCrossPosition == 24) {
                        vaticanReport();
                    }
                }
                break;
            case PLUS_1_SHUFFLE:
                blackCrossPosition++;
                if (blackCrossPosition == 8 || blackCrossPosition == 16 || blackCrossPosition == 24) {
                    vaticanReport();
                }
                actionTilesStack.reinsertAll();
                break;
        }

        notifyViews();

        return blackCrossPosition >= 24;
    }


    /**
     * Extract one tile from the stack given an index, and performs the associated action.
     * @param index the index of the tile to extract
     * @return True if the match is ended, so if the black cross finished the faith track or if all the cards of one color are gone.
     */
    public boolean extractActionTileWithIndex(int index){
        ActionTilesStack.ActionTile at = actionTilesStack.extractTile(index);
        switch (at){
            case YELLOW:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.YELLOW);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.YELLOW);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case GREEN:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.GREEN);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.GREEN);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case BLUE:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.BLUE);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.BLUE);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case VIOLET:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.VIOLET);
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.VIOLET);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case PLUS_2:
                for(int i=0; i<2; ++i) {
                    blackCrossPosition++;
                    if (blackCrossPosition == 8 || blackCrossPosition == 16 || blackCrossPosition == 24) {
                        vaticanReport();
                    }
                }
                break;
            case PLUS_1_SHUFFLE:
                blackCrossPosition++;
                if (blackCrossPosition == 8 || blackCrossPosition == 16 || blackCrossPosition == 24) {
                    vaticanReport();
                }
                actionTilesStack.reinsertAll();
                break;
        }

        notifyViews();

        return blackCrossPosition >= 24;
    }



    /**
     * The status is made this way:
     *
     * coffer (SupplyContainer style)
     * wh1 (SupplyContainer style)
     * wh2 (SupplyContainer style)
     * wh3 (SupplyContainer style)
     * devSpace1card1 (ID)
     * devSpace1card2 (ID)
     * devSpace1card3 (ID)
     * devSpace1in (SupplyContainer style)
     * devSpace1out (SupplyContainer style)
     * devSpace1curr (SupplyContainer style)
     * devSpace2card1 (ID)
     * devSpace2card2 (ID)
     * devSpace2card3 (ID)
     * devSpace2in (SupplyContainer style)
     * devSpace2out (SupplyContainer style)
     * devSpace2curr (SupplyContainer style)
     * devSpace3card3 (ID)
     * devSpace3card2 (ID)
     * devSpace3card1 (ID)
     * devSpace3in (SupplyContainer style)
     * devSpace3out (SupplyContainer style)
     * devSpace3curr (SupplyContainer style)
     * paycheckFromStrongbox (SupplyContainer style)
     * paycheckFromDepots (SupplyContainer style)
     * baseProdInFixed (always 00000)
     * baseProdInMutable1 (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * baseProdInMutable2 (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * baseProdOutFixed (always 00000)
     * baseProdOutMutable1 (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * baseProdCurr (SupplyContainer style)
     * faithTrackPos
     * popeTile1 (0 = inactive, 1 = active, 2 = discarded)
     * popeTile2 (0 = inactive, 1 = active, 2 = discarded)
     * popeTile3 (0 = inactive, 1 = active, 2 = discarded)
     * leader1id (ID)
     * leader1state (0 = inactive, 1 = active, 2 = discarded)
     * leader1inFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * leader1outFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * leader1outMutable (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * leader1curr (SupplyContainer style)
     * leader1depot (SupplyContainer style)
     * leader2id (ID)
     * leader2state (0 = inactive, 1 = active, 2 = discarded)
     * leader2inFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * leader2outFixed (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * leader2outMutable (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
     * leader2curr (SupplyContainer style)
     * leader2depot (SupplyContainer style)
     * unassignedMarbles (BLUE, GREY, RED, VIOLET, WHITE, YELLOW)
     * leaderPick1 (same as leader)
     * leaderPick2 (same as leader)
     * leaderPick3 (same as leader)
     * leaderPick4 (same as leader)
     *
     * SupplyContainer style means that there are 5 integers which represents, in this specific order, the number of: COIN, SERVANT, SHIELD, STONE, FAITH_MARKER
     */
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        status.addAll(coffer.getStatus());
        status.addAll(warehouse.getStatus());
        status.addAll(developments.getStatus());
        status.addAll(paycheck.getStatus());
        status.addAll(baseProduction.getStatus());
        status.addAll(faithTrack.getStatus());
        status.addAll(leadersSpace.getStatus());
        status.addAll(unassignedSupplies.getStatus());
        status.addAll(leadersPick.getStatus());
        status.add(boughtCards);
        status.add(blackCrossPosition);

        return status;
    }


    /**
     * Registers an observer to send the updates to.
     * @param observer observer
     */
    public void attach(ModelObserver observer){
        observers.add(observer);
        actionTilesStack.attach(observer); //also attach the observer to the action tile stack
    }



    /*Gets the status of the dashboard and send it to all of the observers.*/
    public void notifyViews(){
        //get the status of the dashboard
        ArrayList<Integer> status = new ArrayList<>(getStatus());

        //send the status to the observers
        for (ModelObserver mo : observers) {
            mo.update(name, status);
        }
    }


    /**
     * Returns win points and the number of resources of the player.
     * @return win points and number of resources of the player
     */
    public Pair<Integer, Integer> getWinPoints() {
        //place all "volatile" supplies to their depot
        clearProductions();
        clearPaycheck();

        int supplies = depotsManager.getResourceCount(WarehouseObjectType.COIN, WarehouseObjectType.SERVANT, WarehouseObjectType.SHIELD, WarehouseObjectType.STONE)
                    + coffer.getQuantity(WarehouseObjectType.COIN, WarehouseObjectType.SERVANT, WarehouseObjectType.SHIELD, WarehouseObjectType.STONE);
        int winPoints = developments.getWinPoints() + leadersSpace.getWinPoints() + faithTrack.getWinPoints() + supplies/5;
        return new Pair<>(winPoints, supplies);
    }



    private int findIndex(int[] status, int number) {
        if(number == 1) {
            if(status[107] == status[143])
                return 0;
            else if (status[107] == status[158])
                return 1;
            else if (status[107] == status[173])
                return 2;
            else if (status[107] == status[188])
                return 3;
            else
                return 0;
        } else if (number == 2) {
            if(status[122] == status[143])
                return 0;
            else if (status[122] == status[158])
                return 1;
            else if (status[122] == status[173])
                return 2;
            else if (status[122] == status[188])
                return 3;
            else
                return 0;
        }
        else
            return 0;
    }
}
