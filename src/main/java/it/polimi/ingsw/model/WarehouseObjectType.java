package it.polimi.ingsw.model;

/** enum containing every type of resource
 * @author Marco Fumagalli
 */

public enum WarehouseObjectType {
    //never ever change the order!!
    COIN, SERVANT, SHIELD, STONE, FAITH_MARKER, NO_TYPE;


    /**
     * Given a string, returns the corresponding WarehouseObjectType.
     * @param s String to convert
     * @return Corresponding WarehouseObjectType
     */
    public static WarehouseObjectType stringToType(String s){
        if(s.equals("coin")){
            return COIN;
        }
        if(s.equals("servant")){
            return SERVANT;
        }
        if(s.equals("shield")){
            return SHIELD;
        }
        if(s.equals("stone")){
            return STONE;
        }
        if(s.equals("faith marker")){
            return FAITH_MARKER;
        }
        return null;
    }
}
