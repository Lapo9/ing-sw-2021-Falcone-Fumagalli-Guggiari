package it.polimi.ingsw.model;

/**
 * Contains the numerical IDs of the places where you can store resources.
 */
public enum DepotID {
    WAREHOUSE1(DepotType.WAREHOUSE, 1), WAREHOUSE2(DepotType.WAREHOUSE, 2), WAREHOUSE3(DepotType.WAREHOUSE, 3),
    DEVELOPMENT1(DepotType.DEVELOPMENT, 1), DEVELOPMENT2(DepotType.DEVELOPMENT, 2), DEVELOPMENT3(DepotType.DEVELOPMENT, 3),
    LEADER1(DepotType.LEADER, 1), LEADER2(DepotType.LEADER, 2),
    COFFER(DepotType.COFFER, 1),
    PAYCHECK_DEPOT(DepotType.PAYCHECK, 1), PAYCHECK_COFFER(DepotType.PAYCHECK, 2);

    public enum DepotType {
        //don't you ever even try to change the order!
        WAREHOUSE(0), LEADER(0), DEVELOPMENT(1), COFFER(2), PAYCHECK(3);

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

    private DepotID(DepotType depotType, int typeOrder){
        this.type = depotType;
        this.typeOrder = typeOrder;
    }

    public DepotType getType(){
        return type;
    }

    public int getNum(){
        return typeOrder;
    }

}
