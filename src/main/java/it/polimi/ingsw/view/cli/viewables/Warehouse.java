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
        top.add(WarehouseObjectType.NO_TYPE);
        ArrayList<WarehouseObjectType> mid = new ArrayList<>();
        mid.add(WarehouseObjectType.NO_TYPE);
        mid.add(WarehouseObjectType.NO_TYPE);
        ArrayList<WarehouseObjectType> bot = new ArrayList<>();
        bot.add(WarehouseObjectType.NO_TYPE);
        bot.add(WarehouseObjectType.NO_TYPE);
        bot.add(WarehouseObjectType.NO_TYPE);

        level.put(0, top);
        level.put(1, mid);
        level.put(2, bot);
    }

    @Override
    public void update(int[] update) {
        //the array received is composed by 15 numbers representing if the level of the warehouse contains
        //COIN, SERVANT, SHIELD, STONE or FAITH_MARKER (and how many?)
        ArrayList<WarehouseObjectType> top = new ArrayList<>();
        top.add(WarehouseObjectType.NO_TYPE);
        for (int i=0; i<5; i++) {
            if (update[i] == 1) {
                top.remove(0);
                top.add(intToResource((i % 5)));
            }
        }

        ArrayList<WarehouseObjectType> mid = new ArrayList<>();
        mid.add(WarehouseObjectType.NO_TYPE);
        mid.add(WarehouseObjectType.NO_TYPE);
        for (int i=5; i<10; i++) {
            if (update[i] == 1) {
                mid.remove(0);
                mid.add(0, intToResource((i % 5)));
            }
            else if (update[i] == 2) {
                mid.remove(0);
                mid.remove(0);
                mid.add(intToResource((i % 5)));
                mid.add(intToResource((i % 5)));
            }
        }

        ArrayList<WarehouseObjectType> bot = new ArrayList<>();
        bot.add(WarehouseObjectType.NO_TYPE);
        bot.add(WarehouseObjectType.NO_TYPE);
        bot.add(WarehouseObjectType.NO_TYPE);
        for (int i=10; i<15; i++)
            if (update[i] == 1) {
                bot.remove(0);
                bot.add(0, intToResource((i%5)));
            }
            else if (update[i] == 2){
                bot.remove(0);
                bot.remove(0);
                bot.add(0, intToResource((i%5)));
                bot.add(1, intToResource((i%5)));
            }
            else if (update[i] == 3){
                bot.remove(0);
                bot.remove(0);
                bot.remove(0);
                bot.add(intToResource((i%5)));
                bot.add(intToResource((i%5)));
                bot.add(intToResource((i%5)));
            }

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
        return BOLD("Warehouse: ") + "\n\n" + "1     " + resourceToColor(0, 0) + "\n" +
                "2   " +  resourceToColor(1, 0) + " " + resourceToColor(1, 1) + "\n" +
                "3 " + resourceToColor(2, 0) + " " + resourceToColor(2, 1) + " " + resourceToColor(2, 2);
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