package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.WarehouseObjectType.STONE;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * Represents the viewable of a production composed by an input, an output and a currentSupply
 */
public class Production implements Viewable {

    private HashMap<Integer, HashMap<WarehouseObjectType, Integer>> prod = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> input = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> output = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> currentSupply = new HashMap<>();

    /**
     * Class constructor
     */
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

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed by 15 int: 5 for the input, 5 for the output and 5 for the currentSupply
     *               array: every element represents the quantity of the resource corresponding to that index
     */
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

    /**
     * Builds the Production
     * @return the string ready to print
     */
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
                BACK_CYAN( " " + prod.get(2).get(SHIELD).toString() + " ") +
                BLACK(BACK_WHITE(" " + prod.get(2).get(STONE).toString() + " "));
    }

    /**
     * Deletes the spaces occupied by the resources not existing
     * @param i (0==input, 1==output, 2=currentSupply)
     * @param wot type of resource to insert in the input/output/currentSupply
     * @return "" if the resource is not in the input/output/currentSupply of the production,
     *         the color if the resource exists in the input/output/currentSupply
     */
    private String deleteZeros (int i, WarehouseObjectType wot) {
        if (prod.get(i).get(wot) == 0) {
            return "";
        }
        else if (wot == COIN) {
            return BLACK(BACK_YELLOW(" " + prod.get(i).get(COIN).toString() + " "));
        }
        else if (wot == SERVANT){
            return BLACK(BACK_MAGENTA( " " + prod.get(i).get(SERVANT).toString() + " "));
        }
        else if (wot == SHIELD){
            return BLACK(BACK_CYAN(" " + prod.get(i).get(SHIELD).toString() + " "));
        }
        else if (wot == STONE){
            return BLACK(BACK_WHITE(" " + prod.get(i).get(STONE).toString() + " ")) ;
        }
        else if (wot == FAITH_MARKER) {
            return BLACK(BACK_RED(" " + prod.get(1).get(FAITH_MARKER).toString() + " "));
        }
        else return null;
    }

}
