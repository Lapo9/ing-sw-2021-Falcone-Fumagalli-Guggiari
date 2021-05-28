package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.WarehouseObjectType.STONE;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class BaseProduction implements Viewable {

    private HashMap<Integer, HashMap<WarehouseObjectType, Integer>> prod = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> mutableInput1 = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> mutableInput2 = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> mutableOutput = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> currentSupply = new HashMap<>();

    BaseProduction() {
        mutableInput1.put(WarehouseObjectType.COIN, 0);
        mutableInput1.put(WarehouseObjectType.SERVANT, 0);
        mutableInput1.put(WarehouseObjectType.SHIELD, 0);
        mutableInput1.put(WarehouseObjectType.STONE, 0);
        mutableInput1.put(WarehouseObjectType.FAITH_MARKER, 0);

        mutableInput2.put(WarehouseObjectType.COIN, 0);
        mutableInput2.put(WarehouseObjectType.SERVANT, 0);
        mutableInput2.put(WarehouseObjectType.SHIELD, 0);
        mutableInput2.put(WarehouseObjectType.STONE, 0);
        mutableInput2.put(WarehouseObjectType.FAITH_MARKER, 0);

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

        prod.put(0, mutableInput1);
        prod.put(1, mutableInput2);
        prod.put(2, mutableOutput);
        prod.put(3, currentSupply);
    }

    @Override
    public void update(int[] update) {
        //reset everything
        mutableInput1.put(WarehouseObjectType.COIN, 0);
        mutableInput1.put(WarehouseObjectType.SERVANT, 0);
        mutableInput1.put(WarehouseObjectType.SHIELD, 0);
        mutableInput1.put(WarehouseObjectType.STONE, 0);
        mutableInput1.put(WarehouseObjectType.FAITH_MARKER, 0);

        mutableInput2.put(WarehouseObjectType.COIN, 0);
        mutableInput2.put(WarehouseObjectType.SERVANT, 0);
        mutableInput2.put(WarehouseObjectType.SHIELD, 0);
        mutableInput2.put(WarehouseObjectType.STONE, 0);
        mutableInput2.put(WarehouseObjectType.FAITH_MARKER, 0);

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

        //replace only the resource added to the update
        mutableInput1.put(intToResource(update[5]), 1);
        mutableInput2.put(intToResource(update[6]), 1);

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
                + "} " +
                deleteZeros(2, COIN) +
                deleteZeros(2, SERVANT) +
                deleteZeros(2, SHIELD) +
                deleteZeros(2, STONE) +
                deleteZeros(2, FAITH_MARKER) +
                "\n" +
                "BaseProdCS: " + BLACK(BACK_YELLOW(" " + prod.get(3).get(COIN).toString() + " ")) +
                BACK_MAGENTA(" " + prod.get(3).get(SERVANT).toString() + " ") +
                BACK_BLUE( " " + prod.get(3).get(SHIELD).toString() + " ") +
                BLACK(BACK_WHITE(" " + prod.get(3).get(STONE).toString() + " "))
                ;
    }


    private String deleteZeros (int i, WarehouseObjectType wot) {
        if (prod.get(i).get(wot) == 0) {
            return "";
        }
        else if (wot == COIN) {
            if (prod.get(i).get(COIN) == 1)
                return ("\033[0;33m⏺\033[0m" + " "); //yellow
            else
                return null;
        }
        else if (wot == SERVANT){
            if (prod.get(i).get(SERVANT) == 1)
                return ("\033[0;35m⏺\033[0m" + " "); //violet
            else
                return null;
        }
        else if (wot == SHIELD){
            if (prod.get(i).get(SHIELD) == 1)
                return ("\033[0;36m⏺\033[0m" + " "); //blue
            else
                return null;
        }
        else if (wot == STONE){
            if (prod.get(i).get(STONE) == 1)
                return ("\033[0;37m⏺\033[0m" + " "); //grey
            else
                return null;
        }
        else if (wot == FAITH_MARKER) {
            if (prod.get(i).get(FAITH_MARKER) == 1)
                return ("\033[0;31m⏺\033[0m" + " "); //red
            else
                return null;
        }
        else return null;
    }

}
