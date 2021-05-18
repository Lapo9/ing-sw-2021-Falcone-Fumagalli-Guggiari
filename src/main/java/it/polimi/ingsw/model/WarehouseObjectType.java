package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import java.util.ArrayList;

/**
 * Contains every type of resource.
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
        if(s.equals("faithMarker")){
            return FAITH_MARKER;
        }
        return null;
    }


    /**
     * Given an array that contains one type of WarehouseObjectType, returns a Pair of the WarehouseObjectType and its quantity.
     * @param arr array to convert
     * @return a pair of the WarehouseObjectType and its quantity
     */
    public static Pair<WarehouseObjectType, Integer> getContainedSupplies(int[] arr){
        int i;
        for(i = 0; i < 4; ++i){
            if(arr[i] != 0){
                break;
            }
        }

        //nothing is contained (since it is impossible that faith markers are contained
        if (i > 3){
            return new Pair<>(null, 0);
        }

        if (i == 0){
            return new Pair<>(WarehouseObjectType.COIN, arr[i]);
        }
        if (i == 1){
            return new Pair<>(WarehouseObjectType.SERVANT, arr[i]);
        }
        if (i == 2){
            return new Pair<>(WarehouseObjectType.SHIELD, arr[i]);
        }
        return new Pair<>(WarehouseObjectType.STONE, arr[i]);
    }


    /**
     * Given an array, returns an arrayList that contains the WarehouseObjectType contained in the array.
     * @param arr array to convert
     * @return arrayList of WarehouseObjectType
     */
    public static ArrayList<WarehouseObjectType> getSupplies(int[] arr) {
        ArrayList<WarehouseObjectType> tmp = new ArrayList<>();
        while(arr[0] > 0) {
            tmp.add(COIN);
            arr[0]--;
        }
        while(arr[1] > 0) {
            tmp.add(SERVANT);
            arr[1]--;
        }
        while(arr[2] > 0) {
            tmp.add(SHIELD);
            arr[2]--;
        }
        while(arr[3] > 0) {
            tmp.add(STONE);
            arr[3]--;
        }
        while(arr[4] > 0) {
            tmp.add(FAITH_MARKER);
            arr[4]--;
        }
        return tmp;
    }


    /**
     * Given a number, returns the corresponding WarehouseObjectType.
     * @param number number to convert
     * @return corresponding WarehouseObjectType
     */
    public static WarehouseObjectType numberToType(int number) {
        if(number == 0)
            return COIN;
        else if(number == 1)
            return SERVANT;
        else if(number == 2)
            return SHIELD;
        else if(number == 3)
            return STONE;
        else if(number == 4)
            return FAITH_MARKER;
        else
            return null;
    }
}
