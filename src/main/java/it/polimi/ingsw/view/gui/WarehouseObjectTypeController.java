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


    public static void next(WarehouseObjectTypeController wotc){
        switch (wotc){
            case COIN:
                wotc = SERVANT;
                break;
            case SERVANT:
                wotc = SHIELD;
                break;
            case SHIELD:
                wotc = STONE;
                break;
            case STONE:
                wotc = COIN;
                break;
        }
    }


    public static void prev(WarehouseObjectTypeController wotc){
        switch (wotc){
            case COIN:
                wotc = STONE;
                break;
            case SERVANT:
                wotc = COIN;
                break;
            case SHIELD:
                wotc = SERVANT;
                break;
            case STONE:
                wotc = SHIELD;
                break;
        }
    }

}
