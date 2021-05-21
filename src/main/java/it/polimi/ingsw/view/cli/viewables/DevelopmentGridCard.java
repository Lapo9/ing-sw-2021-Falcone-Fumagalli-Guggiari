package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.development.DevelopmentCard.*;
import static it.polimi.ingsw.model.development.DevelopmentCard.getWinPoints;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class DevelopmentGridCard implements Viewable {

    private Integer id;
    private CardCategory cat;
    private Integer lv;
    private Integer wp;

    private HashMap<WarehouseObjectType, Integer> cost = new HashMap<>();

    private HashMap<Integer, HashMap<WarehouseObjectType, Integer>> prod = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> input = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> output = new HashMap<>();

    DevelopmentGridCard() {
        id = 0; //not visible
        cat = CardCategory.YELLOW;
        lv = 0;
        wp = 0;

        cost.put(WarehouseObjectType.COIN, 0);
        cost.put(WarehouseObjectType.SERVANT, 0);
        cost.put(WarehouseObjectType.SHIELD, 0);
        cost.put(WarehouseObjectType.STONE, 0);

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
        prod.put(0, input);
        prod.put(1, output);
    }

    @Override
    public void update(int[] update) {
        //getting everything from the id
        id = update[0];

        //if the id card is zero, i have to empty the space that it occupies
        if (id == 0) {
            cat = CardCategory.YELLOW;
            lv = 0;
            wp = 0;

            cost.put(WarehouseObjectType.COIN, 0);
            cost.put(WarehouseObjectType.SERVANT, 0);
            cost.put(WarehouseObjectType.SHIELD, 0);
            cost.put(WarehouseObjectType.STONE, 0);

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
            prod.put(0, input);
            prod.put(1, output);

        }
        else {
            //card with id != 0
            cost.put(WarehouseObjectType.COIN, getCost(id).getQuantity(COIN));
            cost.put(WarehouseObjectType.SERVANT, getCost(id).getQuantity(SERVANT));
            cost.put(WarehouseObjectType.SHIELD, getCost(id).getQuantity(SHIELD));
            cost.put(WarehouseObjectType.STONE, getCost(id).getQuantity(STONE));
            cat = getCategory(id);
            lv = getLevel(id);
            wp = getWinPoints(id);

            input.put(WarehouseObjectType.COIN, getInput(update[0]).getQuantity(COIN));
            input.put(WarehouseObjectType.SERVANT, getInput(update[0]).getQuantity(SERVANT));
            input.put(WarehouseObjectType.SHIELD, getInput(update[0]).getQuantity(SHIELD));
            input.put(WarehouseObjectType.STONE, getInput(update[0]).getQuantity(STONE));
            input.put(WarehouseObjectType.FAITH_MARKER, getInput(update[0]).getQuantity(FAITH_MARKER));
            output.put(WarehouseObjectType.COIN, getOutput(update[0]).getQuantity(COIN));
            output.put(WarehouseObjectType.SERVANT, getOutput(update[0]).getQuantity(SERVANT));
            output.put(WarehouseObjectType.SHIELD, getOutput(update[0]).getQuantity(SHIELD));
            output.put(WarehouseObjectType.STONE, getOutput(update[0]).getQuantity(STONE));
            output.put(WarehouseObjectType.FAITH_MARKER, getOutput(update[0]).getQuantity(FAITH_MARKER));
        }
    }

    private String categoryToColor() {
        if (this.cat == CardCategory.YELLOW) {
            return FRAMED(BLACK(BACK_YELLOW(" " + lv + " ")));
        }
        else if (this.cat == CardCategory.GREEN){
            return FRAMED(BLACK(BACK_GREEN(" " + lv + " ")));
        }
        else if (this.cat == CardCategory.VIOLET){
            return FRAMED(BLACK(BACK_MAGENTA(" " + lv + " ")));
        }
        else if (this.cat == CardCategory.BLUE){
            return FRAMED(BLACK(BACK_BLUE(" " + lv + " ")));
        }
        else return null;
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

    @Override
    public String toString() {
        return BOLD("Development Card: ") + id + "\n" +
                rowToString(0) + "\n" + rowToString(1) + "\n" +
                rowToString(2) + "\n" + rowToString(3) + "\n" +
                rowToString(4) + "\n" + rowToString(5) + "\n" +
                rowToString(6) + "\n" + rowToString(7) + "\n" +
                rowToString(8) + "\n" + rowToString(9) + "\n" +
                rowToString(10) + "\n"
                ;
    }

    public String rowToString(int row) {
        String[] rows = printOnlyNotNull().split("\n");
        return rows[row];
    }

    private String calculateSpaces (int i, WarehouseObjectType wot) {
        if (prod.get(i).get(wot) == 0) {
            return "   ";
        }
        else return "";
    }

    private String printOnlyNotNull () {

        if (id == 0) {
            return "+--------------------------------+" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "|                                |" + "\n" +
                    "+--------------------------------+";
        }
        else {
            return "+--------------------------------+" + "\n" +
                    "| " + categoryToColor() + "         " + FRAMED(" Cost: ") + "        " + categoryToColor() + " |" + "\n" +
                    "|                                |" + "\n" +

                    "|          " + BLACK(BACK_YELLOW(" " + cost.get(COIN).toString() + " ")) +
                    BACK_MAGENTA(" " + cost.get(SERVANT).toString() + " ") +
                    BACK_BLUE( " " + cost.get(SHIELD).toString() + " ") +
                    BLACK(BACK_WHITE(" " + cost.get(STONE).toString() + " "))+ "          |" + "\n" +
                    "|                                |" + "\n" +

                    "|         " + FRAMED(" Production: ") + "          |" + "\n" +
                    "|                                |" + "\n" +
                    "| " + calculateSpaces(0, COIN)+ calculateSpaces(0, SERVANT) + calculateSpaces(0, SHIELD) + calculateSpaces(0, STONE) +
                    deleteZeros(0, COIN) +
                    deleteZeros(0, SERVANT) +
                    deleteZeros(0, SHIELD) +
                    deleteZeros(0, STONE) +
                    deleteZeros(0, FAITH_MARKER)+
                    " }" +
                    " " +  deleteZeros(1, COIN) +
                    deleteZeros(1, SERVANT) +
                    deleteZeros(1, SHIELD) +
                    deleteZeros(1, STONE) +
                    deleteZeros(1, FAITH_MARKER) +
                    calculateSpaces(1, COIN)+ calculateSpaces(1, SERVANT) + calculateSpaces(1, SHIELD) + calculateSpaces(1, STONE) + calculateSpaces(1, FAITH_MARKER) +" |" + "\n" +
                    "|                                |" + "\n" +
                    printWinPoints() +
                    "+--------------------------------+";

        }
    }
    private String printWinPoints() {
        if (wp < 9) {
            return "|        " + FRAMED(" Win Points: " + wp + " ") + "         |" + "\n";
        }
        else {
            return "|        " + FRAMED(" Win Points: " + wp + " ") + "        |" + "\n";
        }
    }

}
