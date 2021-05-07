package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.WarehouseObjectType.STONE;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class Production implements Viewable {

    private HashMap<Integer, HashMap<WarehouseObjectType, Integer>> prod = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> input = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> output = new HashMap<>();

    Production() {
        input.put(WarehouseObjectType.COIN, 0);
        input.put(WarehouseObjectType.SERVANT, 0);
        input.put(WarehouseObjectType.SHIELD, 0);
        input.put(WarehouseObjectType.STONE, 0);

        output.put(WarehouseObjectType.COIN, 0);
        output.put(WarehouseObjectType.SERVANT, 0);
        output.put(WarehouseObjectType.SHIELD, 0);
        output.put(WarehouseObjectType.STONE, 0);
        output.put(WarehouseObjectType.FAITH_MARKER, 0);

        prod.put(0, input);
        prod.put(1, output);
    }

    @Override
    public void update(int[] update) {

        //update[] for production is composed of 9 int, first four numbers are for the input (a SupplyContainer),
        // the following five numbers are for the output (+ FAITH_MARKER)
        input.put(WarehouseObjectType.COIN, update[0]);
        input.put(WarehouseObjectType.SERVANT, update[1]);
        input.put(WarehouseObjectType.SHIELD, update[2]);
        input.put(WarehouseObjectType.STONE, update[3]);

        output.put(WarehouseObjectType.COIN, update[4]);
        output.put(WarehouseObjectType.SERVANT, update[5]);
        output.put(WarehouseObjectType.SHIELD, update[6]);
        output.put(WarehouseObjectType.STONE, update[7]);
        output.put(WarehouseObjectType.FAITH_MARKER, update[8]);

        prod.put(0, input);
        prod.put(1, output);
    }


    @Override
    public String toString() {
        return BLACK(BACK_YELLOW(" " + prod.get(0).get(COIN).toString() + " ")) +
                BACK_BLUE(" " + prod.get(0).get(SERVANT).toString() + " ") +
                BACK_MAGENTA( " " + prod.get(0).get(SHIELD).toString() + " ") +
                BLACK(BACK_WHITE(" " + prod.get(0).get(STONE).toString() + " ")) +
                " }" +
                " " +  BLACK(BACK_YELLOW(" " + prod.get(1).get(COIN).toString() + " ")) +
                BACK_BLUE(" " + prod.get(1).get(SERVANT).toString() + " ") +
                BACK_MAGENTA( " " + prod.get(1).get(SHIELD).toString() + " ") +
                BLACK(BACK_WHITE(" " + prod.get(1).get(STONE).toString() + " ")) +
                BACK_RED(" " + prod.get(1).get(FAITH_MARKER).toString() + " ");
    }
}
