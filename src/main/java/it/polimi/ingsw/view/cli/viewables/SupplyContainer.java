package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.WarehouseObjectType;

import java.util.HashMap;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;
import static it.polimi.ingsw.model.WarehouseObjectType.*;

/**
 * Represents the SupplyContainer
 */
public class SupplyContainer implements Viewable {

    private HashMap<WarehouseObjectType, Integer> items = new HashMap<>();
    private String name;

    /**
     * Class constructor
     * @param name of the container (Coffer, ...)
     */
    SupplyContainer(String name){
        this.name = name;
        items.put(WarehouseObjectType.COIN, 0);
        items.put(WarehouseObjectType.SERVANT, 0);
        items.put(WarehouseObjectType.SHIELD, 0);
        items.put(WarehouseObjectType.STONE, 0);
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update 4 int for every type of resource it can contains
     */
    @Override
    public void update(int[] update) {
        items.put(WarehouseObjectType.COIN, update[0]);
        items.put(WarehouseObjectType.SERVANT, update[1]);
        items.put(WarehouseObjectType.SHIELD, update[2]);
        items.put(WarehouseObjectType.STONE, update[3]);
    }


    /**
     * Builds the SupplyContainer
     * @return the string ready to print
     */
    @Override
    public String toString() {
        return FRAMED(" " + name + " ") + " " +  BLACK(BACK_YELLOW(" " + items.get(COIN).toString() + " ")) +
                                                    BLACK(BACK_MAGENTA(" " + items.get(SERVANT).toString() + " ")) +
                                                    BLACK(BACK_BLUE( " " + items.get(SHIELD).toString() + " ")) +
                                                    BLACK(BACK_WHITE(" " + items.get(STONE).toString() + " "));
    }

}
