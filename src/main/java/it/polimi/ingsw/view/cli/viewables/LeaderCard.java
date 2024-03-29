package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.CardCategory;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.model.leaders.CardsRequirement;

import java.util.ArrayList;

import static it.polimi.ingsw.model.leaders.LeaderCard.*;
import static it.polimi.ingsw.view.cli.fancy_console.FancyConsole.*;

/**
 * This class creates the viewable of the leader card used in LeaderCardSpace of the player
 */
public class LeaderCard implements Viewable {

    private int id;
    private SupplyContainer suppliesRequirement;
    private ArrayList<CardsRequirement> cardsRequirements;
    private int state;
    private int[] abilityDepot = {0, 0, 0, 0};
    private int abilityMutableOutput;
    private int[] abilityProduction = {0, 0, 0, 0};

    /**
     * Updates the viewable using numbers from the getStatus
     * @param update array composed by the id of the leader card (used to update the requirements), other 4 int to define
     *               the state (inactive/active/discarded), fixed input, fixed output, mutable output, and
     *               other 10 int to define the current supply and the depot ability
     *               (if the id is 0 the card is empty)
     */
    @Override
    public void update(int[] update) {
        id = update[0];
        suppliesRequirement = getSuppliesRequirement(id);
        cardsRequirements = getCardsRequirements(id);

        state = update[1];

        abilityMutableOutput = update[4];
        abilityProduction[0] = update[5];
        abilityProduction[1] = update[6];
        abilityProduction[2] = update[7];
        abilityProduction[3] = update[8];

        abilityDepot[0] = update[10];
        abilityDepot[1] = update[11];
        abilityDepot[2] = update[12];
        abilityDepot[3] = update[13];
    }

    /**
     * Builds the leader card
     * @return string with the leader card
     */
    @Override
    public String toString() {
        return leaderCard();
    }

    /**
     * Composes the leader card using some helper methods
     * @return the leader card to print
     */
    private String leaderCard() {
        String tmp = "";

        tmp = tmp.concat(" ________________________ \n");

        tmp = tmp.concat(requirementsAndState());

        tmp = tmp.concat("|  ____________________  |\n");

        tmp = tmp.concat(ability());

        tmp = tmp.concat("| |____________________| |\n");

        tmp = tmp.concat("|       ").concat("Points:").concat(FRAMED(" " + getWinPoints(id) + " ")).concat("       |\n");

        tmp = tmp.concat("|________________________|\n");

        return tmp;
    }

    /**
     * Gets the requirements of the card and its state from the file that contains all leader cards of the game
     * @return those information in the form of String
     */
    private String requirementsAndState() {
        String tmp = "";
        if(suppliesRequirement.getQuantity() == 0) {    //no supplies requirements, only cards requirements
            if(cardsRequirements.size() == 1)     //one card required, lvl two card
                tmp = tmp.concat("| ").concat(getCardLevelAndType(cardsRequirements.get(0), 1)).concat("           State: |\n").concat("|").concat(getCardLevelAndType(cardsRequirements.get(0), 2)).concat("       ").concat(state());
            else if(cardsRequirements.size() == 2)
                tmp = tmp.concat("| ").concat(getCardLevelAndType(cardsRequirements.get(0), 0)).concat("        State: |\n").concat("| ").concat(getCardLevelAndType(cardsRequirements.get(1), 0)).concat("     ").concat(state());
        }
        else {    //supplies requirements
            tmp = tmp.concat("| ").concat(getSupplies(suppliesRequirement)).concat("    State: |\n").concat("|              ").concat(state());
        }
        return tmp;
    }

    /**
     * Gets the card level and type of the card and its state from the file that contains all leader cards of the game
     * @return those information in the form of String
     */
    private String getCardLevelAndType(CardsRequirement input, Integer index) {
        String tmp = "";
        if(input.reqCard() == CardCategory.GREEN)
            tmp = tmp.concat("\033[0;32m");
        else if(input.reqCard() == CardCategory.BLUE)
            tmp = tmp.concat("\033[0;34m");
        else if(input.reqCard() == CardCategory.YELLOW)
            tmp = tmp.concat("\033[0;33m");
        else if(input.reqCard() == CardCategory.VIOLET)
            tmp = tmp.concat("\033[0;35m");

        if(input.minLevel() == 1)
            tmp = tmp.concat(String.valueOf(input.getNumber())).concat(turnTypeIntoString(input));
        else if(input.minLevel() == 2 && index == 1)
            tmp = tmp.concat("lvl 2");
        else if(input.minLevel() == 2 && index == 2)
            tmp = tmp.concat(turnTypeIntoString(input));

        tmp = tmp.concat("\033[0m");

        return tmp;
    }

    /**
     * Turns the type of the card (CardCategory) into String type
     * @param input the card requirement to convert
     * @return the String corresponding to that category
     */
    private String turnTypeIntoString(CardsRequirement input) {
        String tmp = "";

        if(input.reqCard() == CardCategory.VIOLET)
            tmp = tmp.concat(" violet");
        else if(input.reqCard() == CardCategory.GREEN)
            tmp = tmp.concat(" green ");
        else if(input.reqCard() == CardCategory.BLUE)
            tmp = tmp.concat(" blue  ");
        else if(input.reqCard() == CardCategory.YELLOW)
            tmp = tmp.concat(" yellow");

        return tmp;
    }

    /**
     * Turns the state of the leader card into String type (int to String)
     * @return the String corresponding to the state
     */
    private String state() {
        String tmp = "";
        if(state == 0)   //inactive
            tmp = tmp.concat(" Inactive ").concat("|\n");
        else if(state == 1)   //active
            tmp = tmp.concat("\033[0;32m   Active \033[0m").concat("|\n");
        else             //discarded
            tmp = tmp.concat("\033[0;31mDiscarded \033[0m").concat("|\n");
        return tmp;
    }

    /**
     * Turns the resources required into String
     * @param input the supplies required
     * @return those requirements into String type
     */
    private String getSupplies(SupplyContainer input) {
        String tmp = "";
        int num = 0;
        for (WarehouseObjectType wot:WarehouseObjectType.values()) {
            if(input.getQuantity(wot) != 0)
                break;
            else
                num++;
        }

        if(num == 0)
            tmp = tmp.concat("\033[0;33m5 coins     ");
        else if(num == 1)
            tmp = tmp.concat("\033[0;37m5 stones    ");
        else if(num == 2)
            tmp = tmp.concat("\033[0;35m5 servants  ");
        else if(num == 3)
            tmp = tmp.concat("\033[0;36m5 shields   ");

        tmp = tmp.concat("\033[0m");

        return tmp;
    }

    /**
     * Turns the ability of the leader in a String we can print
     * @return the String describing the ability
     */
    private String ability() {
        String tmp = "";

        if(getAbilityNumber(id) == 1) {
            tmp = tmp.concat("| |                    | |\n");
            tmp = tmp.concat("| |").concat("   Market ability   ").concat("| |\n");
            tmp = tmp.concat("| |                    | |\n");
            tmp = tmp.concat("| |");
            if(getAbilityWOT(id) == WarehouseObjectType.COIN)
                tmp = tmp.concat("   white -> ").concat("\033[0;33myellow  \033[0m");
            else if(getAbilityWOT(id) == WarehouseObjectType.SERVANT)
                tmp = tmp.concat("   white -> ").concat("\033[0;35mviolet  \033[0m");
            else if(getAbilityWOT(id) == WarehouseObjectType.SHIELD)
                tmp = tmp.concat("    white -> ").concat("\033[0;36mblue   \033[0m");
            else if(getAbilityWOT(id) == WarehouseObjectType.STONE)
                tmp = tmp.concat("    white -> ").concat("\033[0;37mgrey   \033[0m");
            tmp = tmp.concat("| |\n");
            tmp = tmp.concat("| |                    | |\n");
        }
        else if(getAbilityNumber(id) == 2) {
            tmp = tmp.concat("| |                    | |\n");
            tmp = tmp.concat("| |").concat("    Depot ability   ").concat("| |\n");
            tmp = tmp.concat("| |                    | |\n");
            if(getAbilityWOT(id) == WarehouseObjectType.COIN) {
                tmp = tmp.concat("| |").concat("      ").concat(FRAMED(" " + String.valueOf(abilityDepot[abilityIndex(abilityDepot)]) +" ")).concat(" \033[0;33mcoins\033[0m").concat("     ").concat("| |\n");
            }
            else if(getAbilityWOT(id) == WarehouseObjectType.SERVANT) {
                tmp = tmp.concat("| |").concat("    ").concat(FRAMED(" " + String.valueOf(abilityDepot[abilityIndex(abilityDepot)]) +" ")).concat(" \033[0;35mservants\033[0m").concat("    ").concat("| |\n");
            }
            else if(getAbilityWOT(id) == WarehouseObjectType.SHIELD) {
                tmp = tmp.concat("| |").concat("     ").concat(FRAMED(" " + String.valueOf(abilityDepot[abilityIndex(abilityDepot)]) +" ")).concat(" \033[0;36mshields\033[0m").concat("    ").concat("| |\n");
            }
            else if(getAbilityWOT(id) == WarehouseObjectType.STONE) {
                tmp = tmp.concat("| |").concat("     ").concat(FRAMED(" " + String.valueOf(abilityDepot[abilityIndex(abilityDepot)]) +" ")).concat(" \033[0;37mstones\033[0m").concat("     ").concat("| |\n");
            }

            tmp = tmp.concat("| |                    | |\n");
        }
        else if(getAbilityNumber(id) == 3) {
            tmp = tmp.concat("| |").concat(" Production ability ").concat("| |\n");
            tmp = tmp.concat("| |                    | |\n");
            tmp = tmp.concat("| |  ");

            if(getAbilityWOT(id) == WarehouseObjectType.COIN)
                tmp = tmp.concat(FRAMED(BLACK(BACK_YELLOW(" 1 "))));
            else if(getAbilityWOT(id) == WarehouseObjectType.SERVANT)
                tmp = tmp.concat(FRAMED(BLACK(BACK_MAGENTA(" 1 "))));
            else if(getAbilityWOT(id) == WarehouseObjectType.SHIELD)
                tmp = tmp.concat(FRAMED(BLACK(BACK_CYAN(" 1 "))));
            else if(getAbilityWOT(id) == WarehouseObjectType.STONE)
                tmp = tmp.concat(FRAMED(BLACK(BACK_WHITE(" 1 "))));

            tmp = tmp.concat(" -> ");

            if(abilityMutableOutput == 0)
                tmp = tmp.concat(FRAMED(BLACK(BACK_YELLOW(" 1 "))));
            else if(abilityMutableOutput == 1)
                tmp = tmp.concat(FRAMED(BLACK(BACK_MAGENTA(" 1 "))));
            else if(abilityMutableOutput == 2)
                tmp = tmp.concat(FRAMED(BLACK(BACK_CYAN(" 1 "))));
            else if(abilityMutableOutput == 3)
                tmp = tmp.concat(FRAMED(BLACK(BACK_WHITE(" 1 "))));
            else if(abilityMutableOutput == 4)
                tmp = tmp.concat(FRAMED(BLACK(BACK_RED(" 1 "))));

            tmp = tmp.concat(" + ").concat(FRAMED(BLACK(BACK_RED(" 1 "))));

            tmp = tmp.concat("  | |\n");

            tmp = tmp.concat("| |                    | |\n");

            tmp = tmp.concat("| |    ").concat(BLACK(FRAMED(BACK_YELLOW(" " + abilityProduction[0] + " ")))).concat(BLACK(FRAMED(BACK_MAGENTA(" " + abilityProduction[1] + " ")))).concat(BLACK(FRAMED(BACK_CYAN(" " + abilityProduction[2] + " ")))).concat(BLACK(FRAMED(BACK_WHITE(" " + abilityProduction[3] + " ")))).concat("    | |\n");
        }
        else if(getAbilityNumber(id) == 4) {
            tmp = tmp.concat("| |                    | |\n");
            tmp = tmp.concat("| |").concat("  Discount ability  ").concat("| |\n");
            tmp = tmp.concat("| |                    | |\n");

            tmp = tmp.concat("| | ");
            if(getAbilityWOT(id) == WarehouseObjectType.COIN)
                tmp = tmp.concat("   \033[0;33mcoin discount\033[0m  ");
            else if(getAbilityWOT(id) == WarehouseObjectType.SERVANT)
                tmp = tmp.concat(" \033[0;35mservant discount\033[0m ");
            else if(getAbilityWOT(id) == WarehouseObjectType.SHIELD)
                tmp = tmp.concat("  \033[0;36mshield discount\033[0m ");
            else if(getAbilityWOT(id) == WarehouseObjectType.STONE)
                tmp = tmp.concat("  \033[0;37mstone discount\033[0m  ");
            tmp = tmp.concat(" | |\n");

            tmp = tmp.concat("| |                    | |\n");
        }
        return tmp;
    }

    /**
     * Helper method that, given an array, returns the index of the element that is not zero
     * (used to understand what WarehouseObjectType is content by the Depot of the leader card that has Depot ability)
     * @param array array composed by 4 int (arrayDepots)
     * @return the int corresponding to the resource (contained in the Depot of the leader)
     */
    private int abilityIndex(int[] array) {
        if(array[0] != 0)
            return 0;
        else if(array[1] != 0)
            return 1;
        else if(array[2] != 0)
            return 2;
        else
            return 3;
    }
}