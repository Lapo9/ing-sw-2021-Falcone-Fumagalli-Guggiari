package it.polimi.ingsw.view.cli.viewables;


import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class Warehouse implements Viewable {

    //the level of the SupplyContainer and the resources contained
    private HashMap<Integer, ArrayList<WarehouseObjectType>> level = new HashMap<>();

    Warehouse () {
        ArrayList<WarehouseObjectType> top = new ArrayList<>();
        top.add(WarehouseObjectType.COIN);
        ArrayList<WarehouseObjectType> mid = new ArrayList<>();
        mid.add(WarehouseObjectType.SERVANT);
        mid.add(WarehouseObjectType.SHIELD);
        ArrayList<WarehouseObjectType> bot = new ArrayList<>();
        bot.add(WarehouseObjectType.STONE);
        bot.add(WarehouseObjectType.FAITH_MARKER);
        bot.add(WarehouseObjectType.NO_TYPE);

        level.put(0, top);
        level.put(1, mid);
        level.put(2, bot);
    }

    @Override
    public void update(int[] update) {
        ArrayList<WarehouseObjectType> top = new ArrayList<>();
        top.add(intToResource(update[0]));
        ArrayList<WarehouseObjectType> mid = new ArrayList<>();
        mid.add(intToResource(update[1]));
        mid.add(intToResource(update[2]));
        ArrayList<WarehouseObjectType> bot = new ArrayList<>();
        bot.add(intToResource(update[3]));
        bot.add(intToResource(update[4]));
        bot.add(intToResource(update[5]));

        level.put(0, top);
        level.put(1, mid);
        level.put(2, bot);
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
        return BOLD(" Warehouse: ") + "\n\n" + "     " + resourceToColor(0, 0) + "\n" +
                    "   " +  resourceToColor(1, 0) + " " + resourceToColor(1, 1) + "\n" +
                    " " + resourceToColor(2, 0) + " " + resourceToColor(2, 1) + " " + resourceToColor(2, 2);
    }

    private String resourceToColor (int lvl, int i) {
        if (this.level.get(lvl).get(i).ordinal() == 0) {
            return FRAMED(BLACK(BACK_YELLOW("   ")));
        }
        else if (this.level.get(lvl).get(i).ordinal() == 1) {
            return FRAMED(BLACK(BACK_MAGENTA("   ")));
        }
        else if (this.level.get(lvl).get(i).ordinal() == 2) {
            return FRAMED(BLACK(BACK_CYAN("   ")));
        }
        else if (this.level.get(lvl).get(i).ordinal() == 3) {
            return FRAMED(BLACK(BACK_BLACK("   ")));
        }
        else if (this.level.get(lvl).get(i).ordinal() == 4) {
            return FRAMED(BLACK(BACK_RED("   ")));
        }
        else return FRAMED(BLACK(BACK_WHITE("   ")));
    }

}