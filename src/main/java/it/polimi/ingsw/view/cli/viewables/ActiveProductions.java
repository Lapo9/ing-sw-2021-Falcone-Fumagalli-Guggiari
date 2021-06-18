package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.view.cli.OfflineInfo;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.ArrayList;

import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * Represents a list of the productions of the player. They can be active/inactive/not existing (only for leader production)
 */
public class ActiveProductions implements Viewable  {

    private ArrayList<Integer> activeProductions = new ArrayList<>();

    /**
     * Class constructor
     */
    ActiveProductions() {
        activeProductions.add(0, 0); //dev 1
        activeProductions.add(1, 0); //dev 2
        activeProductions.add(2, 0); //dev 3
        activeProductions.add(3, 0); //leader 1
        activeProductions.add(4, 0); //leader 2
        activeProductions.add(5, 0); //base production
    }

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed of 6 integer which can be 0(inactive)/1(active)/2(not existing)
     */
    @Override
    public void update(int[] update) {
        activeProductions.add(0, update[0]); //dev 1
        activeProductions.add(1, update[1]); //dev 2
        activeProductions.add(2, update[2]); //dev 3
        activeProductions.add(3, update[3]); //leader 1
        activeProductions.add(4, update[4]); //leader 2
        activeProductions.add(5, update[5]); //base production
    }

    /**
     * Prints the active and existing productions
     * @return string with active and existing productions
     */
    @Override
    public String toString() {
        return FRAMED(" Active Productions: \n") +
                printList(0, "Development Space 1: ") +
                printList(1, "Development Space 2: ") +
                printList(2, "Development Space 3: ") +
                printList(3, "Leader Card 1: ") +
                printList(4, "Leader Card 2: ") +
                printList(5, "Base Production: ");
    }

    /**
     *
     * @param i status of the production: 0(inactive)/1(active)/2(not existing)
     * @param prod name of the specific production
     * @return the row with the name of the production and his status (if not existing, it does not print anything)
     */
    private String printList(int i, String prod) {
        if (activeProductions.get(i) == 0) {
            return prod + FRAMED("   ") + "\n"; //inactive
        }
        else if (activeProductions.get(i) == 1) {
            return prod + FRAMED(BACK_GREEN("   ")) + "\n"; //active
        }
        else if (activeProductions.get(i) == 2) {
            return ""; //not exists
        }
        else return null;
    }

}
