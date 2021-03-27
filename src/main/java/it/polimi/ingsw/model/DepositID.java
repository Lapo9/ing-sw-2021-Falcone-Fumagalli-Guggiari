package it.polimi.ingsw.model;

/**
 * Contains the numerical IDs of the places where you can store resources.
 */
public enum depotID {
    WAREHOUSE1(depotType.WAREHOUSE, 1), WAREHOUSE2(depotType.WAREHOUSE, 2), WAREHOUSE3(depotType.WAREHOUSE, 3),
    DEVELOPMENT1(depotType.DEVELOPMENT, 1), DEVELOPMENT2(depotType.DEVELOPMENT, 2), DEVELOPMENT3(depotType.DEVELOPMENT, 3),
    LEADER1(depotType.LEADER, 1), LEADER2(depotType.LEADER, 2),
    COFFER(depotType.COFFER, 1),
    PAYCHECK_depot(depotType.PAYCHECK, 1), PAYCHECK_COFFER(depotType.PAYCHECK, 2);

    public enum depotType {
        WAREHOUSE, DEVELOPMENT, LEADER, PAYCHECK, COFFER;
    }

    private depotType type;
    private int typeOrder;

    private depotID(depotType depotType, int typeOrder){
        this.type = depotType;
        this.typeOrder = typeOrder;
    }

    public depotType getType(){
        return type;
    }

    public int getNum(){
        return typeOrder;
    }
}
