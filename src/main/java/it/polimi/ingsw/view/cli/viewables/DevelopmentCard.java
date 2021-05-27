package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

import java.util.HashMap;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.WarehouseObjectType;
import static it.polimi.ingsw.model.development.DevelopmentCard.*;
import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.WarehouseObjectType.STONE;



public class DevelopmentCard implements Viewable {

    //card id
    private Integer id;

    //cost in resources of the card
    private HashMap<WarehouseObjectType, Integer> cost = new HashMap<>();

    //production provided by the card
    private HashMap<Integer, HashMap<WarehouseObjectType, Integer>> prod = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> input = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> output = new HashMap<>();
    private HashMap<WarehouseObjectType, Integer> currentSupply = new HashMap<>();

    //category of the card
    private CardCategory cat;
    //level of the card
    private Integer lv;
    //win points of the card
    private Integer wp;

    DevelopmentCard() {
        id = 0; //not visible
        cost.put(WarehouseObjectType.COIN, 0);
        cost.put(WarehouseObjectType.SERVANT, 0);
        cost.put(WarehouseObjectType.SHIELD, 0);
        cost.put(WarehouseObjectType.STONE, 0);

        //production
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

        cat = CardCategory.YELLOW;
        lv = 0;
        wp = 0;
    }

    @Override
    public void update(int[] update) {
        //the card will receive its id (to update cost, category, level and wp) and its three supplyContainer to update its production

        //getting cost, category, level and win points from the id
        id = update[0];

        if (id == 0) {
            cost.put(WarehouseObjectType.COIN, 0);
            cost.put(WarehouseObjectType.SERVANT, 0);
            cost.put(WarehouseObjectType.SHIELD, 0);
            cost.put(WarehouseObjectType.STONE, 0);

            //production
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

            cat = CardCategory.YELLOW;
            lv = 0;
            wp = 0;
        }
        else {
            cost.put(WarehouseObjectType.COIN, getCost(id).getQuantity(COIN));
            cost.put(WarehouseObjectType.SERVANT, getCost(id).getQuantity(SERVANT));
            cost.put(WarehouseObjectType.SHIELD, getCost(id).getQuantity(SHIELD));
            cost.put(WarehouseObjectType.STONE, getCost(id).getQuantity(STONE));
            cat = getCategory(id);
            lv = getLevel(id);
            wp = getWinPoints(id);

            //update production
            input.put(WarehouseObjectType.COIN, update[1]);
            input.put(WarehouseObjectType.SERVANT, update[2]);
            input.put(WarehouseObjectType.SHIELD, update[3]);
            input.put(WarehouseObjectType.STONE, update[4]);
            input.put(WarehouseObjectType.FAITH_MARKER, update[5]);

            output.put(WarehouseObjectType.COIN, update[6]);
            output.put(WarehouseObjectType.SERVANT, update[7]);
            output.put(WarehouseObjectType.SHIELD, update[8]);
            output.put(WarehouseObjectType.STONE, update[9]);
            output.put(WarehouseObjectType.FAITH_MARKER, update[10]);

            currentSupply.put(WarehouseObjectType.COIN, update[11]);
            currentSupply.put(WarehouseObjectType.SERVANT, update[12]);
            currentSupply.put(WarehouseObjectType.SHIELD, update[13]);
            currentSupply.put(WarehouseObjectType.STONE, update[14]);
            currentSupply.put(WarehouseObjectType.FAITH_MARKER, update[15]);
        }
    }

    public String categoryToColor() {
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
        return BOLD("Development Card: ") + id + "               " + "\n" + printOnlyNotNull();
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
                    "|      CS: " + BLACK(BACK_YELLOW(" " + currentSupply.get(COIN).toString() + " ")) +
                    BACK_MAGENTA(" " + currentSupply.get(SERVANT).toString() + " ") +
                    BACK_BLUE( " " + currentSupply.get(SHIELD).toString() + " ") +
                    BLACK(BACK_WHITE(" " + currentSupply.get(STONE).toString() + " "))+ "          |" + "\n" +
                    "|                                |" + "\n" +
                    printWinPoints() +
                    "+--------------------------------+";
        }
    }

    private String printWinPoints() {
        if (wp <= 9) {
            return "|        " + FRAMED(" Win Points: " + wp + " ") + "         |" + "\n";
        }
        else {
            return "|        " + FRAMED(" Win Points: " + wp + " ") + "        |" + "\n";
        }
    }

}
