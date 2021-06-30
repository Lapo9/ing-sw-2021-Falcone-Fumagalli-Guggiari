package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Pair;


/**
 * Enumeration to associate a WarehouseObjectType with its image and some other properties
 */
public enum WarehouseObjectTypeController {
    COIN("pictures/miscellaneous/coin.png"), SERVANT("pictures/miscellaneous/servant.png"), SHIELD("pictures/miscellaneous/shield.png"), STONE("pictures/miscellaneous/stone.png");


    private String url;

    /**
     * Builds the enum object
     * @param url Location of the image
     */
    private WarehouseObjectTypeController(String url){
        this.url = url;
    }

    /**
     * Returns the location of the image of this resource
     * @return Location of the image
     */
    public String getUrl(){
        return url;
    }


    /**
     * In order to cycle over the resources, the resources must have an order.
     * @param wotc Current resource
     * @return Next resource
     */
    public static WarehouseObjectTypeController next(WarehouseObjectTypeController wotc){
        switch (wotc){
            case COIN:
                return SERVANT;
            case SERVANT:
                return SHIELD;
            case SHIELD:
                return STONE;
            case STONE:
                return COIN;
        }
        return null;
    }


    /**
     * In order to cycle over the resources, the resources must have an order.
     * @param wotc Current resource
     * @return Previous resource
     */
    public static WarehouseObjectTypeController prev(WarehouseObjectTypeController wotc){
        switch (wotc){
            case COIN:
                return STONE;
            case SERVANT:
                return COIN;
            case SHIELD:
                return SERVANT;
            case STONE:
                return SHIELD;
        }
        return null;
    }


    /**
     * Returns the WarehouseObjectType given an image URL
     * @param url Location of the image
     * @return Associated resource
     */
    public static WarehouseObjectTypeController getTypeByUrl(String url){
        if (url.contains(COIN.getUrl())){
            return COIN;
        }
        if (url.contains(SERVANT.getUrl())){
            return SERVANT;
        }
        if (url.contains(SHIELD.getUrl())){
            return SHIELD;
        }
        if (url.contains(STONE.getUrl())){
            return STONE;
        }
        return null;
    }


    /**
     * Returns the supply contained in a warehouse row, and its quantity
     * @param arr Array containing the resources in a warehouse row in this order: coin, servant, shield, stone, (faith point)
     * @return The supply contained in a warehouse row, and its quantity
     */
    public static Pair<WarehouseObjectTypeController, Integer> getContainedSupplies(int[] arr){
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
            return new Pair<>(WarehouseObjectTypeController.COIN, arr[i]);
        }
        if (i == 1){
            return new Pair<>(WarehouseObjectTypeController.SERVANT, arr[i]);
        }
        if (i == 2){
            return new Pair<>(WarehouseObjectTypeController.SHIELD, arr[i]);
        }
        return new Pair<>(WarehouseObjectTypeController.STONE, arr[i]);
    }


    /**
     * Returns the warehouse object type given its cardinal order as defined in next and prev
     * @param number Order of the resource
     * @return Corresponding resource
     */
    public static WarehouseObjectTypeController getTypeByNumber(int number) {
        if(number == 0)
            return COIN;
        else if(number == 1)
            return SERVANT;
        else if(number == 2)
            return SHIELD;
        else if(number == 3)
            return STONE;
        else
            return null;
    }
}
