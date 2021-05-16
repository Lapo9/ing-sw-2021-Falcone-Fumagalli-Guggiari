package it.polimi.ingsw.view.gui;

public enum WarehouseObjectTypeController {
    COIN("pictures/miscellaneous/coin.png"), SERVANT("pictures/miscellaneous/servant.png"), SHIELD("pictures/miscellaneous/shield.png"), STONE("pictures/miscellaneous/stone.png");


    private String url;

    private WarehouseObjectTypeController(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }


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


    public static WarehouseObjectTypeController getTypeByUrl(String url){
        if (url.equals(COIN.getUrl())){
            return COIN;
        }
        if (url.equals(SERVANT.getUrl())){
            return SERVANT;
        }
        if (url.equals(SHIELD.getUrl())){
            return SHIELD;
        }
        if (url.equals(STONE.getUrl())){
            return STONE;
        }
        return null;
    }

}
