package it.polimi.ingsw.model;

/**
 * Contains the numerical IDs of the places where you can store resources.
 */
public enum DepotID {
    WAREHOUSE1(DepotType.WAREHOUSE, SourceType.DEPOT, 0), WAREHOUSE2(DepotType.WAREHOUSE, SourceType.DEPOT, 1), WAREHOUSE3(DepotType.WAREHOUSE, SourceType.DEPOT, 2),
    DEVELOPMENT1(DepotType.DEVELOPMENT, SourceType.ANY, 1), DEVELOPMENT2(DepotType.DEVELOPMENT, SourceType.ANY, 2), DEVELOPMENT3(DepotType.DEVELOPMENT, SourceType.ANY, 3),
    LEADER1_DEPOT(DepotType.LEADER_DEPOT, SourceType.ANY, 1), LEADER2_DEPOT(DepotType.LEADER_DEPOT, SourceType.ANY, 2),
    LEADER1_PRODUCTION(DepotType.LEADER_PRODUCTION, SourceType.STRONGBOX, 1), LEADER2_PRODUCTION(DepotType.LEADER_PRODUCTION, SourceType.STRONGBOX, 2),
    COFFER(DepotType.COFFER, SourceType.STRONGBOX, 1),
    PAYCHECK(DepotType.PAYCHECK, SourceType.ANY, 1),
    BASE_PRODUCTION(DepotType.BASE_PRODUCTION, SourceType.ANY, 1),
    ANY(DepotType.ANY, SourceType.ANY, 1);

    public enum DepotType {
        //don't you ever even try to change the order!
        WAREHOUSE(0), LEADER_DEPOT(0), LEADER_PRODUCTION(1), DEVELOPMENT(1), COFFER(2), PAYCHECK(3), BASE_PRODUCTION(4), ANY(5);

        private int order;

        private DepotType(int order){
            this.order = order;
        }

        public int getOrder(){
            return order;
        }
    }


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

    public DepotType getType(){
        return type;
    }

    public int getNum(){
        return typeOrder;
    }

    public SourceType getSource() {
        return source;
    }

}
