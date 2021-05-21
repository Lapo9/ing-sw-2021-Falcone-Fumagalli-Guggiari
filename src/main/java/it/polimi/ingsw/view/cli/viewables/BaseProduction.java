package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.WarehouseObjectType.STONE;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class BaseProduction implements Viewable {

    private HashMap<Integer, HashMap<WarehouseObjectType, Integer>> prod = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> mutableInput = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> mutableOutput = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> currentSupply = new HashMap<>();

    BaseProduction() {
        mutableInput.put(WarehouseObjectType.COIN, 0);
        mutableInput.put(WarehouseObjectType.SERVANT, 0);
        mutableInput.put(WarehouseObjectType.SHIELD, 0);
        mutableInput.put(WarehouseObjectType.STONE, 0);
        mutableInput.put(WarehouseObjectType.FAITH_MARKER, 0);

        mutableOutput.put(WarehouseObjectType.COIN, 0);
        mutableOutput.put(WarehouseObjectType.SERVANT, 0);
        mutableOutput.put(WarehouseObjectType.SHIELD, 0);
        mutableOutput.put(WarehouseObjectType.STONE, 0);
        mutableOutput.put(WarehouseObjectType.FAITH_MARKER, 0);

        currentSupply.put(WarehouseObjectType.COIN, 0);
        currentSupply.put(WarehouseObjectType.SERVANT, 0);
        currentSupply.put(WarehouseObjectType.SHIELD, 0);
        currentSupply.put(WarehouseObjectType.STONE, 0);
        currentSupply.put(WarehouseObjectType.FAITH_MARKER, 0);

        prod.put(0, mutableInput);
        prod.put(1, mutableOutput);
        prod.put(2, currentSupply);
    }

    @Override
    public void update(int[] update) {
        mutableInput.put(intToResource(update[5]), 1);
        mutableInput.put(intToResource(update[6]), 1);

        mutableOutput.put(intToResource(update[12]), 1);

        currentSupply.put(WarehouseObjectType.COIN, update[13]);
        currentSupply.put(WarehouseObjectType.SERVANT, update[14]);
        currentSupply.put(WarehouseObjectType.SHIELD, update[15]);
        currentSupply.put(WarehouseObjectType.STONE, update[16]);
        currentSupply.put(WarehouseObjectType.FAITH_MARKER, update[17]);
    }

    private WarehouseObjectType intToResource(int i) {
        if (i == 0){
            return WarehouseObjectType.COIN;
        }
        else if (i == 1){
            return WarehouseObjectType.SERVANT;
        }
        else if (i == 2){
            return WarehouseObjectType.SHIELD;
        }
        else if (i == 3){
            return WarehouseObjectType.STONE;
        }
        else if (i == 4){
            return WarehouseObjectType.FAITH_MARKER;
        }
        else return WarehouseObjectType.NO_TYPE;
    }

    @Override
    public String toString() {
        return BOLD("Base Production:\n") +
                deleteZeros(0, COIN) +
                deleteZeros(0, SERVANT) +
                deleteZeros(0, SHIELD) +
                deleteZeros(0, STONE) +
                deleteZeros(0, FAITH_MARKER) +
                deleteZeros(1, COIN) +
                deleteZeros(1, SERVANT) +
                deleteZeros(1, SHIELD) +
                deleteZeros(1, STONE) +
                deleteZeros(1, FAITH_MARKER)
                + " } " +
                deleteZeros(2, COIN) +
                deleteZeros(2, SERVANT) +
                deleteZeros(2, SHIELD) +
                deleteZeros(2, STONE) +
                deleteZeros(2, FAITH_MARKER) +
                "\n" +
                BOLD("Current Supply: ") + BLACK(BACK_YELLOW(" " + prod.get(2).get(COIN).toString() + " ")) +
                BACK_MAGENTA(" " + prod.get(2).get(SERVANT).toString() + " ") +
                BACK_BLUE( " " + prod.get(2).get(SHIELD).toString() + " ") +
                BLACK(BACK_WHITE(" " + prod.get(2).get(STONE).toString() + " "))
                ;
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
