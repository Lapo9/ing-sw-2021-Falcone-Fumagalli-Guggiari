package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.Viewable;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

import java.util.HashMap;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.WarehouseObjectType;
import static it.polimi.ingsw.model.development.DevelopmentCard.*;
import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.WarehouseObjectType.STONE;


/**
 * This class creates the viewable of the development card used in the DevelopmentSpace and DevelopmentSpaceGrid
 */
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

    /**
     * Class constructor
     */
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

        //category, level and number of win points
        cat = CardCategory.YELLOW;
        lv = 0;
        wp = 0;
    }

    /**
     * Updates the viewable using numbers from the getStatus (if the id is 0 the card is empty)
     * @param update array composed by the id of the card (used to update the cost, category, level and win points of the card,
     *               those info are from the development cards file) and 15 int to update the card production
     */
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

    /**
     * Prints the development card
     * @return string with the development card
     */
    @Override
    public String toString() {
        return BOLD("Development Card: ") + id + "               " + "\n" + printOnlyNotNull();
    }

    /**
     * Associates a color (from Style) to the category of the card
     * @return the color of the category
     */
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

    /**
     * Helps to "compress" the string, so we have "" if the resource does not exist and we don't have empty spaces
     * @param i index of the production (mutableInput1(0)/mutableInput2(1)/mutableOutput(2))
     * @param wot type of resource, used to convert resource in colour
     * @return one marker for the type of resource or an empty string if the resource does not exist
     */
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

    /**
     * Calculate the spaces to have a proportioned card. If that resource is not in the production, we have to substitute
     * with three empty spaces to put the production in the centre
     * @param i input/output/currentSupply
     * @param wot resource chosen
     * @return three spaces for every resource not existent in the prod
     */
    private String calculateSpaces (int i, WarehouseObjectType wot) {
        if (prod.get(i).get(wot) == 0) {
            return "   "; //three spaces
        }
        else return "";
    }

    /**
     * Prints an empty card if the id is zero and if not, print the card using all helper methods
     * @return the string describing the development card
     */
    private String printOnlyNotNull () {
        //empty card
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

    /**
     * Print the number of win points considering the occurrence of a double digit
     * @return the number of win points framed and the right number of spaces to not ruin the shape of the card
     */
    private String printWinPoints() {
        if (wp <= 9) {
            return "|        " + FRAMED(" Win Points: " + wp + " ") + "         |" + "\n";
        }
        else {
            return "|        " + FRAMED(" Win Points: " + wp + " ") + "        |" + "\n";
        }
    }

}
