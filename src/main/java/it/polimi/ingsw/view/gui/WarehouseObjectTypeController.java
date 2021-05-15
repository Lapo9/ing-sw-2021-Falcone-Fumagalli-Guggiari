package it.polimi.ingsw.view.gui;

public enum WarehouseObjectTypeController {
    COIN, SERVANT, SHIELD, STONE;

    public static String getUrl(int index){
        switch (index) {
            case 0:
                return "pictures/miscellaneous/coin.png";
            case 1:
                return "pictures/miscellaneous/servant.png";
            case 2:
                return "pictures/miscellaneous/shield.png";
            case 3:
                return "pictures/miscellaneous/stone.png";
            default:
                return "";
        }
    }
}
