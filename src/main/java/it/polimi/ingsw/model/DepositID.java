package it.polimi.ingsw.model;

/**
 * Contains the numerical IDs of the places where you can store resources.
 */
public enum DepositID {
    WAREHOUSE1(DepositType.WAREHOUSE, 1), WAREHOUSE2(DepositType.WAREHOUSE, 2), WAREHOUSE3(DepositType.WAREHOUSE, 3),
    DEVELOPMENT1(DepositType.DEVELOPMENT, 1), DEVELOPMENT2(DepositType.DEVELOPMENT, 2), DEVELOPMENT3(DepositType.DEVELOPMENT, 3),
    LEADER1(DepositType.LEADER, 1), LEADER2(DepositType.LEADER, 2),
    COFFER(DepositType.COFFER, 1),
    PAYCHECK_DEPOSIT(DepositType.PAYCHECK, 1), PAYCHECK_COFFER(DepositType.PAYCHECK, 2);

    public enum DepositType {
        WAREHOUSE, DEVELOPMENT, LEADER, PAYCHECK, COFFER;
    }

    private DepositType type;
    private int typeOrder;

    private DepositID(DepositType depositType, int typeOrder){
        this.type = depositType;
        this.typeOrder = typeOrder;
    }

    public DepositType getType(){
        return type;
    }

    public int getNum(){
        return typeOrder;
    }
}
