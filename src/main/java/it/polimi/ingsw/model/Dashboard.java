package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The dashboard is the access point of the controller to the model.
 * It exposes a function for all of the possible actions a player can perform during a match.
 * Generally, when the action performed goes well, nothing is returned, but if the action violates any of the game rule an exception is thrown.
 * TODO maybe there should be methods to check if an action is possible before performing it? It can be useful if you want to visualize only the actions that can be executed (for example if you don't have enough supplies to buy a card, then the card is grey and not clickable)
 */
public class Dashboard implements WinPointsCountable, HasStatus{

    private final Marketplace marketplace;
    private final DevelopmentGrid developmentGrid;
    private final Warehouse warehouse = new Warehouse();
    private final SupplyContainer coffer = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX).and(SupplyContainer.AcceptStrategy.specificType(WarehouseObjectType.FAITH_MARKER)).negate());
    private final MutableProduction baseProduction = new MutableProduction(2, 1);
    private final FaithTrack faithTrack = new FaithTrack();
    private final LeadersSpace leadersSpace = new LeadersSpace();
    private final Developments developments = new Developments();
    private MarbleContainer unassignedSupplies;
    private final Paycheck paycheck = new Paycheck();
    private final boolean inkwell;
    private final ProductionManager productionManager = new ProductionManager(developments, baseProduction, leadersSpace);
    private final DepotsManager depotsManager = new DepotsManager(warehouse, leadersSpace);
    private final ActionTilesStack actionTilesStack = new ActionTilesStack();
    private int blackCrossPosition = 0;
    private ArrayList<ModelObserver> observers = new ArrayList<ModelObserver>();

    private final HashMap<DepotID.DepotType, AcceptsSupplies> containers = new HashMap<>();


    /**
     * Creates an empty dashboard specifying if the player is the first one to play, and assign to it the match marketplace and the match development grid.
     * @param inkwell is the player the first one to play?
     * @param marketplace match marketplace, to collect the supplies from
     * @param developmentGrid match development grid, to buy the development cards from
     */
    public Dashboard(boolean inkwell, Marketplace marketplace, DevelopmentGrid developmentGrid){
        this.inkwell = inkwell;
        this.marketplace = marketplace;
        this.developmentGrid = developmentGrid;

        containers.put(DepotID.DepotType.WAREHOUSE, depotsManager);
        containers.put(DepotID.DepotType.LEADER_DEPOT, depotsManager);
        containers.put(DepotID.DepotType.DEVELOPMENT, productionManager);
        containers.put(DepotID.DepotType.LEADER_PRODUCTION, productionManager);
        containers.put(DepotID.DepotType.COFFER, coffer);
        containers.put(DepotID.DepotType.PAYCHECK, paycheck);
        containers.put(DepotID.DepotType.BASE_PRODUCTION, productionManager);
    }



    /**
     * Swaps 2 rows of the Warehouse. If it is not possible a SupplyException is thrown.
     * @param r1 row 1
     * @param r2 row 2
     * @throws SupplyException Cannot swap the 2 rows
     */
    public void swapWarehouseRows(int r1, int r2) throws SupplyException {
        warehouse.swapRows(r1, r2);
    }


    /**
     * Transfers the marbles contained in the selected column/row to the user unassigned objects.
     * @param dir vertical or horizontal
     * @param index column or row number
     */
    public void buySupplies(MarketDirection dir, int index) {
        unassignedSupplies = marketplace.obtain(dir, index);
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
        if(unassignedSupplies.getQuantity(color) == 0) {throw new SupplyException();}

        depotsManager.addMarble(to, color);
        unassignedSupplies.removeMarble(color);
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
        faithTrack.goAhead(unassignedSupplies.getQuantity(MarbleColor.RED));

        unassignedSupplies.clear();
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
        //remove supply from specified container
        containers.get(from.getType()).removeSupply(to, type, from);

        //add supply to specified container, if you cannot, put supply back to original container and throw the exception
        try{
            containers.get(to.getType()).addSupply(to, type, from);
        } catch (SupplyException | LeaderException | NoSuchMethodException e) {
            containers.get(from.getType()).addSupply(from, type, from);
            throw e;
        }
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
            } catch (SupplyException se) {/*TODO this should never happen, so maybe fail the program*/}
        }

        //store outputs in the coffer
        coffer.sum(tmp);

        //go ahead in faith track
        return faithTrack.goAhead(faithPoints);
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
        if(buyableLevels.get(space-1) != developmentGrid.getLevel(column, row)){
            throw new DevelopmentException();
        }

        //buy the card
        developments.addCardToSpace(space, developmentGrid.buyCard(column, row, paycheck, leadersSpace));
    }


    /**
     * Returns the levels of the development card the player can buy
     * @return the levels of the development card the player can buy
     */
    public ArrayList<Integer> buyableDevelopmentLevels() {
        return developments.buyableLevels();
    }


    /**
     * Discards the specified leader and goes ahead one tile on the faith track
     * @param i which leader
     * @return Returns if a vatican report has been issued
     * @throws LeaderException The specified leader cannot be discarded (already discarded or active)
     */
    public boolean discardLeader(int i) throws LeaderException {
        leadersSpace.discardLeader(i);
        return faithTrack.goAhead(1);
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
    }


    /**
     * Moves the player one tile ahead in the faith track.
     * @return Returns if a vatican report has been issued
     */
    public boolean goAhead(){
        return faithTrack.goAhead(1);
    }


    /**
     * Triggers a vatican report.
     */
    public void vaticanReport(){
        faithTrack.vaticanReport();
    }


    /**
     * Swaps one of the mutable inputs/outputs of the base production.
     * @param i 0 = first input, 1 = second input, 2 = output
     * @param wot new supply to insert
     * @throws SupplyException A faith marker or no type supply type was added
     */
    public void swapBaseProduction(int i, WarehouseObjectType wot) throws SupplyException{
        if(wot == WarehouseObjectType.FAITH_MARKER){
            throw new SupplyException();
        }

        productionManager.swapBaseProduction(i, wot);
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
    }


    /**
     * Clears all of the production depots, and reinsert the cleared supplies to their source.
     */
    public void clearProductions(){
        Pair<SupplyContainer, SupplyContainer> destination;
        destination = productionManager.clearSupplies();

        coffer.sum(destination.second);
        depotsManager.allocate(destination.first);
    }


    /**
     * Clears the paycheck, and reinsert the cleared supplies to their source.
     */
    public void clearPaycheck(){
        Pair<SupplyContainer, SupplyContainer> destination;
        destination = paycheck.clearSupplies();

        coffer.sum(destination.second);
        depotsManager.allocate(destination.first);
    }


    /**
     * Returns if the current dashboard belongs to the player who started the match.
     * @return If the current dashboard belongs to the player who started the match
     */
    public boolean hasInkwell(){
        return inkwell;
    }


    /**
     * Checks if the match ended, so if the player got to the last tile in the faith track or id he bought at least 7 development cards.
     * @return If the player triggered an end match condition.
     */
    public boolean isMatchEnded(){
        return developmentGrid.getBoughtCards() >= 7 || faithTrack.getPosition() >= 24;
    }


    /**
     * Returns the depots that can receive the specified supply.
     * @param from Source of the supply
     * @param type Type of the supply
     * @return The depots that can receive the specified supply
     */
    public ArrayList<DepotID> getAllowedDepots(DepotID from, WarehouseObjectType type) {
        ArrayList<DepotID> res = new ArrayList<>();

        res.addAll(depotsManager.getAllowedDepots(from, type));

        res.addAll(productionManager.getAllowedDepots(from, type));

        if (paycheck.additionAllowed(type, from)){
            res.add(DepotID.PAYCHECK);
        }

        if (coffer.additionAllowed(type, from)){
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
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case GREEN:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.GREEN);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case BLUE:
                try {
                    developmentGrid.removeCard(ActionTilesStack.ActionTile.BLUE);
                } catch (NoSuchCardException nsce){
                    return true;
                }
                break;
            case VIOLET:
                try {
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

        return blackCrossPosition >= 24;
    }



    /**
     * Gets the status of the dashboard and send it to all of the observers.
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
     * baseProdOutMutable2 (0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER)
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

        return status;
    }

    private void notifyViews(){
        //get the status of the dashboard
        ArrayList<Integer> status = new ArrayList<>(getStatus());

        //send the status to the observers
        for (ModelObserver mo : modelObservers) {
            mo.updateStatus(status);
        }
    }


    @Override
    public int getWinPoints() {
        //place all "volatile" supplies to their depot
        clearProductions();
        clearPaycheck();

        int supplies = depotsManager.getResourceCount(WarehouseObjectType.COIN, WarehouseObjectType.SERVANT, WarehouseObjectType.SHIELD, WarehouseObjectType.STONE)
                    + coffer.getQuantity(WarehouseObjectType.COIN, WarehouseObjectType.SERVANT, WarehouseObjectType.SHIELD, WarehouseObjectType.STONE);
        return developments.getWinPoints() + leadersSpace.getWinPoints() + faithTrack.getWinPoints() + supplies/5;
    }

    /**
     * Attach an observer.
     * @param mo Observer to notify
     */
    public void observe(ModelObserver mo){
        //TODO
    }

}
