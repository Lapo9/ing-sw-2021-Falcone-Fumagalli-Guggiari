package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class DevelopmentGrid implements Viewable {

    private HashMap<CardCategory, HashMap<Integer, DevelopmentGridCard>> devGrid = new HashMap<>();
    private HashMap<Integer, DevelopmentGridCard> column0 = new HashMap<>();
    private HashMap<Integer, DevelopmentGridCard> column1 = new HashMap<>();
    private HashMap<Integer, DevelopmentGridCard> column2 = new HashMap<>();
    private HashMap<Integer, DevelopmentGridCard> column3 = new HashMap<>();

    DevelopmentGrid() {
        column0.put(0, new DevelopmentGridCard());
        column0.put(1, new DevelopmentGridCard());
        column0.put(2, new DevelopmentGridCard());

        column1.put(0, new DevelopmentGridCard());
        column1.put(1, new DevelopmentGridCard());
        column1.put(2, new DevelopmentGridCard());

        column2.put(0, new DevelopmentGridCard());
        column2.put(1, new DevelopmentGridCard());
        column2.put(2, new DevelopmentGridCard());

        column3.put(0, new DevelopmentGridCard());
        column3.put(1, new DevelopmentGridCard());
        column3.put(2, new DevelopmentGridCard());

        devGrid.put(CardCategory.GREEN, column0);
        devGrid.put(CardCategory.BLUE, column1);
        devGrid.put(CardCategory.YELLOW, column2);
        devGrid.put(CardCategory.VIOLET, column3);
    }

    @Override
    public void update(int[] update) {
        int [] update0 = {update[0]};
        //private method intToArray(int i)
        column0.get(0).update(update0);

    }

    @Override
    public String toString() {
        return BOLD("Development Grid: ") + "\n" +
                //first row
                devGrid.get(CardCategory.GREEN).get(0).toString() + "   " + devGrid.get(CardCategory.BLUE).get(0).toString() + "   " +
                devGrid.get(CardCategory.YELLOW).get(0).toString() + "   " + devGrid.get(CardCategory.VIOLET).get(0).toString()

                //second row

                ;
    }

}
