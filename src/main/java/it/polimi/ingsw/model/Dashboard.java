package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;

/**
 * TODO
 */
public class Dashboard {

    private Marketplace marketplace;
    private Warehouse warehouse;
    private SupplyContainer coffer;
    private MutableProduction baseProduction;
    private FaithTrack faithTrack;
    private LeadersSpace leadersSpace;
    private Developments developments;
    private MarbleContainer unassignedSupplies;
    private boolean inkwell;


    /**
     * Swaps 2 rows of the Warehouse. Row 1 is the bottom one. If it is not possible a SupplyException is thrown.
     * @param r1
     * @param r2
     * @throws SupplyException
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
     * Transforms a marble into the supply contained in the destination deposit. If the transformation is not possible a MarbleException is thrown.
     * @param to destination deposit (a BoundedSupplyContainer)
     * @param color the color of the marble
     * @throws SupplyException Destination is full
     * @throws MarbleException Destination cannot accept this color of marble
     * @throws LeaderAbilityNotSupportedException Leader hasn't a deposit ability
     */
    public void assignMarble(int to, MarbleColor color) throws SupplyException, MarbleException, LeaderAbilityNotSupportedException{
        if(unassignedSupplies.getQuantity(color) == 0) {throw new SupplyException();}

        if(to <= 2){
            warehouse.addMarble(to, color);
            unassignedSupplies.removeMarble(color);
        }

        else if(to >= 6 && to <= 7){
            leadersSpace.getLeaderAbility(to-6).addMarble();
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
        int totalDiscarded = unassignedSupplies.getQuantity(MarbleColor.WHITE, MarbleColor.BLUE, MarbleColor.GREY, MarbleColor.VIOLET, MarbleColor.YELLOW);

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
    public void moveSupply(int from, int to, WarehouseObjectType type) throws SupplyException, LeaderAbilityNotSupportedException {
        //remove supply from specified container
        if(from <= 2){
            warehouse.removeObject(from, type);
        }
        else if(from >= 3 && from <= 5){
            developments.removeSupply(from-3, type);
        }
        else{
            leadersSpace.getLeaderAbility(from-6).removeSupply(type);
        }

        //add supply to specified container, if you cannot, put supply back to original container
        try {
            if (to <= 2) {
                warehouse.addObject(to, type);
            }
            else if (to >= 3 && from <= 5) {
                developments.addSupply(to - 3, type);
            }
            else {
                leadersSpace.getLeaderAbility(to - 6).addSupply(type);
            }
        }
        catch(SupplyException se){
            if(from <= 2){
                warehouse.addObject(from, type);
            }
            else if(from >= 3 && from <= 5){
                developments.addSupply(from-3, type);
            }
            else{
                leadersSpace.getLeaderAbility(from-6).addSupply(type);
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
        coffer.add(developmentProduction);
        coffer.add(baseProduction);
        coffer.add(leader1Production);
        coffer.add(leader2Production);

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


    public void buyDevelopment(int supplyCardID, int space, SupplyConsumption... sc) throws SupplyException, DevelopmentException {
        ResourcesManager rm = new ResourcesManager(warehouse, coffer, leadersSpace);
        rm.check(sc);


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
     * Attach an observer.
     * @param mo Observer to notify
     */
    public void observe(ModelObserver mo){
        //TODO
    }

}
