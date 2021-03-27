package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;

/**
 * The dashboard is the access point of the controller to the model.
 * It exposes a function for all of the possible actions a player can perform during a match.
 * Generally, when an action performed goes well, nothing is returned, but if the action violates any of the game rule an exception is thrown.
 * TODO maybe there should be methods to check if an action is possible before performing it? It can be useful if you want to visualize only the actions that can be executed (for example if you don't have enough supplies to buy a card, then the card is grey and not clickable)
 */
public class Dashboard implements HasStatus{

    private Marketplace marketplace;
    private DevelopmentGrid developmentGrid;
    private Warehouse warehouse = new Warehouse();
    private SupplyContainer coffer = new SupplyContainer();
    private MutableProduction baseProduction = new MutableProduction();
    private FaithTrack faithTrack = new FaithTrack();
    private LeadersSpace leadersSpace = new LeadersSpace();
    private Developments developments = new Developments();
    private MarbleContainer unassignedSupplies;
    private Paycheck paycheck = new Paycheck();
    private boolean inkwell;


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
    }



    /**
     * Swaps 2 rows of the Warehouse. Row 1 is the bottom one. If it is not possible a SupplyException is thrown.
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
     * @param to destination depot (a BoundedSupplyContainer)
     * @param color the color of the marble
     * @throws SupplyException Destination is full
     * @throws MarbleException Destination cannot accept this color of marble
     * @throws LeaderAbilityNotSupportedException Leader hasn't a depot ability
     */
    public void assignMarble(depotID to, MarbleColor color) throws SupplyException, MarbleException, LeaderAbilityNotSupportedException{
        if(unassignedSupplies.getQuantity(color) == 0) {throw new SupplyException();}

        if(to.getType() == depotID.depotType.WAREHOUSE){
            warehouse.addMarble(to.getNum(), color, leadersSpace);
            unassignedSupplies.removeMarble(color);
        }

        else if(to.getType() == depotID.depotType.LEADER){
            leadersSpace.getLeaderAbility(to.getNum()).addMarble();
            unassignedSupplies.removeMarble(color);
        }
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
        for(int i = 0; i < unassignedSupplies.getQuantity(MarbleColor.RED); ++i){
            vaticanReport |= goAhead();
        }

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
    public void moveSupply(depotID from, depotID to, WarehouseObjectType type) throws SupplyException, LeaderAbilityNotSupportedException {

        //deny illegal movements (from/to depots to/from coffer)
        if(((from.getType() == depotID.depotType.WAREHOUSE || from.getType() == depotID.depotType.DEVELOPMENT || from.getType() == depotID.depotType.LEADER) && to.getType() == depotID.depotType.COFFER)
                || (from.getType() == depotID.depotType.COFFER && (to.getType() == depotID.depotType.WAREHOUSE || to.getType() == depotID.depotType.DEVELOPMENT || to.getType() == depotID.depotType.LEADER))){
            throw new SupplyException();
        }

        //remove supply from specified container
        if(from.getType() == depotID.depotType.WAREHOUSE){
            warehouse.removeObject(type);
        }
        else if(from.getType() == depotID.depotType.DEVELOPMENT){
            developments.removeSupply(from.getNum(), type);
        }
        else if(from.getType() == depotID.depotType.LEADER){
            leadersSpace.getLeaderAbility(from.getNum()).removeSupply(type);
        }
        else if(from.getType() == depotID.depotType.COFFER){
            coffer.removeSupply(type);
        }
        else if(from.getType() == depotID.depotType.PAYCHECK){
            paycheck.remove(from.getNum(), to, type);
        }

        //add supply to specified container, if you cannot, put supply back to original container and throw the exception
        try {
            if (to.getType() == depotID.depotType.WAREHOUSE) {
                warehouse.addObject(to.getNum(), type);
            }
            else if (to.getType() == depotID.depotType.DEVELOPMENT) {
                developments.addSupply(to.getNum(), type);
            }
            else if (to.getType() == depotID.depotType.LEADER){
                leadersSpace.getLeaderAbility(to.getNum()).addSupply(type);
            }
            else if (to.getType() == depotID.depotType.COFFER){
                coffer.addSupply(type);
            }
            else if (to.getType() == depotID.depotType.PAYCHECK){
                paycheck.add(from, type);
            }
        }
        catch(SupplyException se){
            if(from.getType() == depotID.depotType.WAREHOUSE){
                warehouse.addObject(from.getNum(), type);
            }
            else if(from.getType() == depotID.depotType.DEVELOPMENT){
                developments.addSupply(from.getNum(), type);
            }
            else if(from.getType() == depotID.depotType.LEADER){
                leadersSpace.getLeaderAbility(from.getNum()).addSupply(type);
            }
            else if(from.getType() == depotID.depotType.COFFER){
                coffer.addSupply(type);
            }
            else if(from.getType() == depotID.depotType.PAYCHECK){
                paycheck.add(from, type);
            }
            throw se;
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
        //get productions outputs
        SupplyContainer developmentProduction = developments.produce(s1, s2, s3);
        SupplyContainer baseProduction = developments.produce(base);

        SupplyContainer leader1Production;
        try {
            leader1Production = leadersSpace.getLeaderAbility(0).produce(l1);
        }
        catch(LeaderAbilityNotSupportedException lanse){
            leader1Production = new SupplyContainer();
        }

        SupplyContainer leader2Production;
        try {
            leader2Production = leadersSpace.getLeaderAbility(1).produce(l2);
        }
        catch(LeaderAbilityNotSupportedException lanse){
            leader2Production = new SupplyContainer();
        }

        //store outputs in the coffer
        coffer.sum(developmentProduction);
        coffer.sum(baseProduction);
        coffer.sum(leader1Production);
        coffer.sum(leader2Production);

        //go ahead in faith track
        boolean vaticanReport = false;
        while(coffer.getQuantity(WarehouseObjectType.FAITH_MARKER) > 0){
            coffer.removeSupply(WarehouseObjectType.FAITH_MARKER);
            vaticanReport |= goAhead();
        }

        return vaticanReport;
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
        developments.checkProduction(s1, s2, s3);
        baseProduction.checkProduction(base);
        try {
            leadersSpace.getLeaderAbility(0).checkProduction(l1);
        }
        catch(LeaderAbilityNotSupportedException lanse){}
        try {
            leadersSpace.getLeaderAbility(1).checkProduction(l2);
        }
        catch(LeaderAbilityNotSupportedException lanse){}
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
        if(buyableLevels.get(space-1 != developmentGrid.getLevel(column, row)){
            throw new DevelopmentException();
        }

        //buy the card
        developments.addCardToSpace(space, developmentGrid.buyCard(column, row, paycheck));
    }


    /**
     * Discards the specified leader and goes ahead one tile on the faith track
     * @param i which leader
     * @return Returns if a vatican report has been issued
     * @throws LeaderException The specified leader cannot be discarded (already discarded or active)
     */
    public boolean discardLeader(int i) throws LeaderException {
        leadersSpace.discardLeader(i);
        return faithTrack.goAhead();
    }


    /**
     * Activates the specified leader.
     * @param i Leader to activate
     * @throws SupplyException Leader activation requirements aren't matched
     * @throws LeaderException The specified leader cannot be played (already discarded or active)
     */
    public void playLeader(int i) throws SupplyException, LeaderException {
        leadersSpace.playLeader(i, new ResourceManager(warehouse, coffer, leadersSpace));
    }


    /**
     * Moves the player one tile ahead in the faith track.
     * @return Returns if a vatican report has been issued
     */
    public boolean goAhead(){
        return faithTrack.goAhead();
    }


    /**
     * Triggers a vatican report.
     */
    public void vaticanReport(){
        faithTrack.vaticanReport();
    }


    /**
     * Returns if the current dashboard belongs to the player who started the match.
     * @return If the current dashboard belongs to the player who started the match
     */
    public boolean hasInkwell(){
        return inkwell;
    }


    /**
     * The status of the dashboard.
     * @return the current status of the dashboard, expressed in a concise way.
     */
    @Override
    public ArrayList<Integer> getStatus(){
        //TODO
    }

    /**
     * Attach an observer.
     * @param mo Observer to notify
     */
    public void observe(ModelObserver mo){
        //TODO
    }

}
