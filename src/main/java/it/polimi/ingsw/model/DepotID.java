package it.polimi.ingsw.model;

import it.polimi.ingsw.model.leaders.LeadersSpace;
import it.polimi.ingsw.model.leaders.leader_abilities.Depot;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;

/**
 * Contains the numerical IDs of the places where you can store resources.
 */
public enum DepotID {
    WAREHOUSE1(DepotType.WAREHOUSE, SourceType.DEPOT, 0), WAREHOUSE2(DepotType.WAREHOUSE, SourceType.DEPOT, 1), WAREHOUSE3(DepotType.WAREHOUSE, SourceType.DEPOT, 2),
    DEVELOPMENT1(DepotType.DEVELOPMENT, SourceType.ANY, 0), DEVELOPMENT2(DepotType.DEVELOPMENT, SourceType.ANY, 1), DEVELOPMENT3(DepotType.DEVELOPMENT, SourceType.ANY, 2),
    LEADER1(null, null, 0), LEADER2(null, null, 1),
    COFFER(DepotType.COFFER, SourceType.STRONGBOX, 1),
    PAYCHECK(DepotType.PAYCHECK, SourceType.ANY, 1),
    BASE_PRODUCTION(DepotType.BASE_PRODUCTION, SourceType.ANY, 1),
    ANY(DepotType.ANY, SourceType.ANY, 1);

    /**
     * Contains the depot types of the places where you can store resources.
     */
    public enum DepotType {
        //don't you ever even try to change the order!
        WAREHOUSE(0), LEADER_DEPOT(0), LEADER_PRODUCTION(1), DEVELOPMENT(1), COFFER(2), PAYCHECK(3), BASE_PRODUCTION(4), ANY(5), NONE(6);

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
        DEPOT, STRONGBOX, ANY, PAYCHECK, NONE;
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
     * @param leadersSpace
     */
    public DepotType getType(LeadersSpace leadersSpace){
        if (type != null){
            return type;
        }

        if (leadersSpace.getLeaderAbilityTrusted(typeOrder) instanceof Depot){
            return DepotType.LEADER_DEPOT;
        }

        if (leadersSpace.getLeaderAbilityTrusted(typeOrder) instanceof Producer){
            return DepotType.LEADER_PRODUCTION;
        }

        return DepotType.NONE;
    }

    /**
     * Sets the type of the depot
     * @param type depot type
     */
    public void setType(DepotType type) {
        this.type = type;
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


    /**
     * Sets the source of the depot
     * @param source depot sourc
     */
    public void setSource(SourceType source) {
        this.source = source;
    }

    public static DepotID stringToId(String s){
        if (s.equals("wh1")){
            return WAREHOUSE1;
        }
        else if (s.equals("wh2")){
            return WAREHOUSE2;
        }
        else if (s.equals("wh3")){
            return WAREHOUSE3;
        }
        else if (s.equals("dev1")){
            return DEVELOPMENT1;
        }
        else if (s.equals("dev2")){
            return DEVELOPMENT2;
        }
        else if (s.equals("dev3")){
            return DEVELOPMENT3;
        }
        else if (s.equals("coffer")){
            return COFFER;
        }
        else if (s.equals("paycheck")){
            return PAYCHECK;
        }
        else if (s.equals("base")){
            return BASE_PRODUCTION;
        }
        else if (s.equals("leader1")){
            return LEADER1;
        }
        else if (s.equals("leader2")){
            return LEADER2;
        }

        return null;

    }


}
