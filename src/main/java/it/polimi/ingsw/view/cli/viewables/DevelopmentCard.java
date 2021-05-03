package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.model.WarehouseObjectType.*;
import static it.polimi.ingsw.model.WarehouseObjectType.STONE;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;


public class DevelopmentCard implements Viewable {

    private HashMap<WarehouseObjectType, Integer> cost = new HashMap<>();
    private Production prod;
    private CardCategory cat;
    private ArrayList<Integer> LvWp = new ArrayList<>();


    DevelopmentCard() {
        cost.put(WarehouseObjectType.COIN, 0);
        cost.put(WarehouseObjectType.SERVANT, 0);
        cost.put(WarehouseObjectType.SHIELD, 0);
        cost.put(WarehouseObjectType.STONE, 0);
        this.prod = new Production();
        this.cat = CardCategory.YELLOW;
        LvWp.add(0);
        LvWp.add(0);
    }

    @Override
    public void update(int[] update) {

    }

    private String categoryToColor() {
        if (this.cat == CardCategory.YELLOW) {
            return FRAMED(BLACK(BACK_YELLOW(" " + LvWp.get(0).toString() + " ")));
        }
        else if (this.cat == CardCategory.GREEN){
            return FRAMED(BLACK(BACK_GREEN(" " + LvWp.get(0).toString() + " ")));
        }
        else if (this.cat == CardCategory.VIOLET){
            return FRAMED(BLACK(BACK_MAGENTA(" " + LvWp.get(0).toString() + " ")));
        }
        else if (this.cat == CardCategory.BLUE){
            return FRAMED(BLACK(BACK_BLUE(" " + LvWp.get(0).toString() + " ")));
        }
        return null;
    }

    @Override
    public String toString() {
        return BOLD("Development Card") + "\n" +
                " ____________________________________" + "\n" +
                "|   " + categoryToColor() + "         " + FRAMED(" Cost: ") + "        " + categoryToColor() + "   |" + "\n" +
                "|                                    |" + "\n" +
                "|            " + BLACK(BACK_YELLOW(" " + cost.get(COIN).toString() + " ")) +
                BACK_BLUE(" " + cost.get(SERVANT).toString() + " ") +
                BACK_MAGENTA( " " + cost.get(SHIELD).toString() + " ") +
                BLACK(BACK_WHITE(" " + cost.get(STONE).toString() + " "))+ "            |" + "\n" +
                "|                                    |" + "\n" +
                "|           " + FRAMED(" Production: ") + "            |" + "\n" +
                "|                                    |" + "\n" +
                "|   " + prod.toString() + "   |" +"\n" +
                "|                                    |" + "\n" +
                "|                                    |" + "\n" +
                "|                 " + FRAMED(" " + LvWp.get(1).toString() + " ") + "                |" + "\n" +
                "|____________________________________|";


    }




}
