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
    private HashMap<WarehouseObjectType, Integer> currentSupply = new HashMap<>();

    Production() {
        input.put(WarehouseObjectType.COIN, 0);
        input.put(WarehouseObjectType.SERVANT, 0);
        input.put(WarehouseObjectType.SHIELD, 0);
        input.put(WarehouseObjectType.STONE, 0);
        input.put(WarehouseObjectType.FAITH_MARKER, 0);

        output.put(WarehouseObjectType.COIN, 0);
        output.put(WarehouseObjectType.SERVANT, 0);
        output.put(WarehouseObjectType.SHIELD, 0);
        output.put(WarehouseObjectType.STONE, 0);
        output.put(WarehouseObjectType.FAITH_MARKER, 0);

        currentSupply.put(WarehouseObjectType.COIN, 0);
        currentSupply.put(WarehouseObjectType.SERVANT, 0);
        currentSupply.put(WarehouseObjectType.SHIELD, 0);
        currentSupply.put(WarehouseObjectType.STONE, 0);
        currentSupply.put(WarehouseObjectType.FAITH_MARKER, 0);

        prod.put(0, input);
        prod.put(1, output);
        prod.put(2, currentSupply);
    }

    @Override
    public void update(int[] update) {
        input.put(WarehouseObjectType.COIN, update[0]);
        input.put(WarehouseObjectType.SERVANT, update[1]);
        input.put(WarehouseObjectType.SHIELD, update[2]);
        input.put(WarehouseObjectType.STONE, update[3]);
        input.put(WarehouseObjectType.FAITH_MARKER, update[4]); //deleted by deleteZeros in toString

        output.put(WarehouseObjectType.COIN, update[5]);
        output.put(WarehouseObjectType.SERVANT, update[6]);
        output.put(WarehouseObjectType.SHIELD, update[7]);
        output.put(WarehouseObjectType.STONE, update[8]);
        output.put(WarehouseObjectType.FAITH_MARKER, update[9]);

        currentSupply.put(WarehouseObjectType.COIN, update[10]);
        currentSupply.put(WarehouseObjectType.SERVANT, update[11]);
        currentSupply.put(WarehouseObjectType.SHIELD, update[12]);
        currentSupply.put(WarehouseObjectType.STONE, update[13]);
        currentSupply.put(WarehouseObjectType.FAITH_MARKER, update[14]); //invisible in toString

        prod.put(0, input);
        prod.put(1, output);
        prod.put(2, currentSupply);
    }


    @Override
    public String toString() {
        return deleteZeros(0, COIN) +
                deleteZeros(0, SERVANT) +
                deleteZeros(0, SHIELD) +
                deleteZeros(0, STONE) +
                deleteZeros(0, FAITH_MARKER)+
                " }" +
                " " +  deleteZeros(1, COIN) +
                deleteZeros(1, SERVANT) +
                deleteZeros(1, SHIELD) +
                deleteZeros(1, STONE) +
                deleteZeros(1, FAITH_MARKER) + "\n\n" +
                BOLD(" Current Supply: ") + BLACK(BACK_YELLOW(" " + prod.get(2).get(COIN).toString() + " ")) +
                BACK_MAGENTA(" " + prod.get(2).get(SERVANT).toString() + " ") +
                BACK_BLUE( " " + prod.get(2).get(SHIELD).toString() + " ") +
                BLACK(BACK_WHITE(" " + prod.get(2).get(STONE).toString() + " "));
    }

    private String deleteZeros (int i, WarehouseObjectType wot) {
        if (prod.get(i).get(wot) == 0) {
            return "";
        }
        else if (wot == COIN) {
            return BLACK(BACK_YELLOW(" " + prod.get(i).get(COIN).toString() + " "));
        }
        else if (wot == SERVANT){
            return BACK_MAGENTA( " " + prod.get(i).get(SERVANT).toString() + " ");
        }
        else if (wot == SHIELD){
            return BACK_BLUE(" " + prod.get(i).get(SHIELD).toString() + " ");
        }
        else if (wot == STONE){
            return BLACK(BACK_WHITE(" " + prod.get(i).get(STONE).toString() + " ")) ;
        }
        else if (wot == FAITH_MARKER) {
            return BACK_RED(" " + prod.get(1).get(FAITH_MARKER).toString() + " ");
        }
        else return null;
    }

}
