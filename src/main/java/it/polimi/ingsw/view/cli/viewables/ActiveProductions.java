package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

public class ActiveProductions implements Viewable  {

    private ArrayList<Integer> activeProductions = new ArrayList<>();

    ActiveProductions() {
        activeProductions.add(0, 0); //dev 1
        activeProductions.add(1, 0); //dev 2
        activeProductions.add(2, 0); //dev 3
        activeProductions.add(3, 0); //leader 1
        activeProductions.add(4, 0); //leader 2
        activeProductions.add(5, 0); //base production
    }


    @Override
    public void update(int[] update) {
        activeProductions.add(0, update[0]); //dev 1
        activeProductions.add(1, update[1]); //dev 2
        activeProductions.add(2, update[2]); //dev 3
        activeProductions.add(3, update[3]); //leader 1
        activeProductions.add(4, update[4]); //leader 2
        activeProductions.add(5, update[5]); //base production
    }


    @Override
    public String toString() {
        return FRAMED("Active Productions:\n") +
                printList(0, "Development Space 1: ") +
                printList(1, "Development Space 2: ") +
                printList(2, "Development Space 3: ") +
                printList(3, "Leader Card 1: ") +
                printList(4, "Leader Card 2: ") +
                printList(5, "Base Production: ");
    }

    private String printList(int i, String prod) {
        if (activeProductions.get(i) == 0) {
            return (prod + "\033[0;31m☒\033[0m\n"); //inactive
        }
        else if (activeProductions.get(i) == 1) {
            return (prod + "\033[0;32m☑\033[0m\n"); //active
        }
        else if (activeProductions.get(i) == 2) {
            return ""; //not exists
        }
        else return null;
    }

}
