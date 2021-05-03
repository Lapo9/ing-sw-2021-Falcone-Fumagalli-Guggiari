package it.polimi.ingsw.model;

/**
 * Contains the numerical IDs of the places where you can store resources.
 */
public enum DepotID {
    WAREHOUSE1(DepotType.WAREHOUSE, SourceType.DEPOT, 0), WAREHOUSE2(DepotType.WAREHOUSE, SourceType.DEPOT, 1), WAREHOUSE3(DepotType.WAREHOUSE, SourceType.DEPOT, 2),
    DEVELOPMENT1(DepotType.DEVELOPMENT, SourceType.ANY, 0), DEVELOPMENT2(DepotType.DEVELOPMENT, SourceType.ANY, 1), DEVELOPMENT3(DepotType.DEVELOPMENT, SourceType.ANY, 2),
    LEADER1_DEPOT(DepotType.LEADER_DEPOT, SourceType.DEPOT, 0), LEADER2_DEPOT(DepotType.LEADER_DEPOT, SourceType.DEPOT, 1),
    LEADER1_PRODUCTION(DepotType.LEADER_PRODUCTION, SourceType.ANY, 0), LEADER2_PRODUCTION(DepotType.LEADER_PRODUCTION, SourceType.ANY, 1),
    COFFER(DepotType.COFFER, SourceType.STRONGBOX, 1),
    PAYCHECK(DepotType.PAYCHECK, SourceType.ANY, 1),
    BASE_PRODUCTION(DepotType.BASE_PRODUCTION, SourceType.ANY, 1),
    ANY(DepotType.ANY, SourceType.ANY, 1);

    /**
     * Contains the depot types of the places where you can store resources.
     */
    public enum DepotType {
        //don't you ever even try to change the order!
        WAREHOUSE(0), LEADER_DEPOT(0), LEADER_PRODUCTION(1), DEVELOPMENT(1), COFFER(2), PAYCHECK(3), BASE_PRODUCTION(4), ANY(5);

        private int order;

        private DepotType(int order){
            this.order = order;
        }

        /**
         * Returns the index of the depot type.
         * @return the index of the depot type
         */
        public int getOrder(){
            return order;
        }
    }

    /**
     * Contains the sources from which resources can came from.
     */
    public enum SourceType {
        DEPOT, STRONGBOX, ANY, PAYCHECK;
    }


    private DepotType type;
    private int typeOrder;
    private SourceType source;

    private DepotID(DepotType depotType, SourceType source, int typeOrder){
        this.type = depotType;
        this.typeOrder = typeOrder;
        this.source = source;
    }

    /**
     * Returns the type of the depot.
     * @return the type of the depot
     */
    public DepotType getType(){
        return type;
    }

    /**
     * Returns the number of the depot
     * @return the number of the depot
     */
    public int getNum(){
        return typeOrder;
    }

    /**
     * Returns the source from which resources in the depot can came from.
     * @return the source from which resources in the depot can came from.
     */
    public SourceType getSource() {
        return source;
    }

}
