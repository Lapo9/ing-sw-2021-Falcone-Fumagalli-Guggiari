package it.polimi.ingsw.model;

/**
 * Contains the numerical IDs of the places where you can store resources.
 */
public enum DepotID {
    WAREHOUSE1(DepotType.WAREHOUSE, DepotType.WAREHOUSE, 1), WAREHOUSE2(DepotType.WAREHOUSE, DepotType.WAREHOUSE, 2), WAREHOUSE3(DepotType.WAREHOUSE, DepotType.WAREHOUSE, 3),
    DEVELOPMENT1_DEPOT(DepotType.DEVELOPMENT, DepotType.WAREHOUSE, 1), DEVELOPMENT2_DEPOT(DepotType.DEVELOPMENT, DepotType.WAREHOUSE, 2), DEVELOPMENT3_DEPOT(DepotType.DEVELOPMENT, DepotType.WAREHOUSE, 3),
    DEVELOPMENT1_COFFER(DepotType.DEVELOPMENT, DepotType.COFFER, 1), DEVELOPMENT2_COFFER(DepotType.DEVELOPMENT, DepotType.COFFER, 2), DEVELOPMENT3_COFFER(DepotType.DEVELOPMENT, DepotType.COFFER, 3),
    LEADER1_DEPOT(DepotType.LEADER, DepotType.WAREHOUSE, 1), LEADER2_DEPOT(DepotType.LEADER, DepotType.WAREHOUSE, 2),
    LEADER1_COFFER(DepotType.LEADER, DepotType.COFFER, 1), LEADER2_COFFER(DepotType.LEADER, DepotType.COFFER, 2),
    COFFER(DepotType.COFFER, DepotType.COFFER, 1),
    PAYCHECK_DEPOT(DepotType.PAYCHECK, DepotType.WAREHOUSE, 1), PAYCHECK_COFFER(DepotType.PAYCHECK, DepotType.COFFER, 2),
    BASE_PRODUCTION_DEPOT(DepotType.BASE_PRODUCTION, DepotType.WAREHOUSE, 1),
    BASE_PRODUCTION_COFFER(DepotType.BASE_PRODUCTION, DepotType.COFFER, 1);

    public enum DepotType {
        //don't you ever even try to change the order!
        WAREHOUSE(0), LEADER(0), DEVELOPMENT(1), COFFER(2), PAYCHECK(3), BASE_PRODUCTION(4), OTHER(5);

        private int order;

        private DepotType(int order){
            this.order = order;
        }

        public int getOrder(){
            return order;
        }
    }

    private DepotType type;
    private int typeOrder;
    private DepotType source;

    private DepotID(DepotType depotType, DepotType source, int typeOrder){
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

    public DepotType getSource() {
        return source;
    }

}
