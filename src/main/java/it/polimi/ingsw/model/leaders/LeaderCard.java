package it.polimi.ingsw.model.leaders;

import it.polimi.ingsw.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.leaders.leader_abilities.*;

/**
 * The LeaderCard class represents a leader card of the game, each card has its own ability, winPoints and requirements
 * needed to buy it. It can be activated if the player has the right quantity of requirements, and it can be discard by
 * the player in exchange for a faith point.
 */
public class LeaderCard implements WinPointsCountable, HasStatus {

    private int id;
    private Pair<SupplyContainer, ArrayList<CardsRequirement>> requirements;
    private LeaderAbility ability;
    private boolean active = false;
    private boolean discarded = false;
    private final int winPoints;

    private static final String path = "src/main/resources/cards_info/LeaderCards.txt";

    /**
     * Creates a leader card.
     * @param id card id
     * @param reqSC a SupplyContainer which contains all the supplies needed to buy the LeaderCard
     * @param reqCR an ArrayList of CardRequirement which contains the details about the SupplyCards needed to buy the LeaderCard
     * @param abil the type of the LeaderCard's ability
     * @param points win points given by this leader when active
     */
    public LeaderCard(int id, SupplyContainer reqSC, ArrayList<CardsRequirement> reqCR, LeaderAbility abil, int points){
        this.id = id;
        requirements = new Pair<SupplyContainer, ArrayList<CardsRequirement>>(reqSC, reqCR);
        ability = abil;
        winPoints = points;
    }

    /**
     * Creates a leader card.
     * @param id card id
     */
    public LeaderCard(int id) {
        this.id = id;
        winPoints = getWinPoints(id);
        ability = getAbility(id);
        requirements = new Pair<SupplyContainer, ArrayList<CardsRequirement>>(getSuppliesRequirement(id), getCardsRequirements(id));
    }

    /**
     * The getCost method returns a pair which contains all the requirements needed to buy the LeaderCard.
     * @return a pair which contains all the requirements needed to buy the LeaderCard
     */
    public Pair<SupplyContainer, ArrayList<CardsRequirement>> getCost(){
        return requirements;
    }

    /**
     * The getAbility returns the ability of the LeaderCard.
     * @return the ability of the LeaderCard
     * @throws LeaderException the LeaderCard is not active yet or if it has been discard
     */
    public LeaderAbility getAbility() throws LeaderException{
        if(!active || discarded)
            throw new LeaderException("The leader is already discarded or it isn't active");
        return ability;
    }


    /**
     * The getAbility returns the ability of the LeaderCard, whether the leader is active or not
     * @return the ability of the LeaderCard
     */
    public LeaderAbility getAbilityTrusted() throws LeaderException{
        return ability;
    }


    /**
     * The activate method activates the LeaderCard.
     * @throws LeaderException leader cannot be activated, because it was already discarded or activated
     */
    public void activate() throws LeaderException{
        if(!discarded && !active) {
            active = true;
        }
        else {
            throw new LeaderException("Cannot activate the leader (already discarded or active)");
        }
    }

    /**
     * The setDiscard method discard the LeaderCard.
     * @throws LeaderException leader cannot be discarded, because it was already discarded or activated
     */
    public void discard() throws LeaderException{
        if(!active && !discarded) {
            discarded = true;
        }
        else {
            throw new LeaderException("Cannot discard the leader (already discarded or active)");
        }
    }

    /**
     * Returns the card win points.
     * @param id card id
     * @return the card win points
     */
    public static Integer getWinPoints(int id) {
        if(id == 0){
            return null;
        }
        File file = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        return scan.nextInt();
    }

    /**
     * Returns the card ability.
     * @param id card id
     * @return the card ability
     */
    public static LeaderAbility getAbility(int id) {
        if(id == 0)
            return null;
        if(id == 0){
            return null;
        }
        File file = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        scan.nextInt();    //points
        int abil = scan.nextInt();
        if(abil == 1)
            return new Market(getAbilityWOT(id));
        else if(abil == 2)
            return new Depot(getAbilityWOT(id));
        else if(abil == 3)
            return new Producer(getAbilitySC(id));
        else
            return new Discount(getAbilityWOT(id));
    }

    /**
     * Returns an integer which represents the type of leader ability (1 = market, 2 = depot, 3 = production, 4 = discount).
     * @param id card id
     * @return the type of leader ability
     */
    public static Integer getAbilityNumber(int id) {
        if(id == 0){
            return null;
        }
        File file = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        scan.nextInt();    //points
        int abil = scan.nextInt();
        return abil;
    }

    /**
     * Returns the supply container that contains the details of the leader ability.
     * @param id card id
     * @return the supply container that contains the details of the leader abilty.
     */
    public static SupplyContainer getAbilitySC(int id) {
        if(id == 0){
            return null;
        }
        File file = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        scan.nextInt();   //points
        scan.nextInt();   //ability
        int c = scan.nextInt();
        int st = scan.nextInt();
        int se = scan.nextInt();
        int sh = scan.nextInt();
        int f = scan.nextInt();
        return new SupplyContainer(c, st, se, sh, f);
    }

    /**
     * Returns the warehouse object type of the ability.
     * @param id card id
     * @return the warehouse object type of the ability
     */
    public static WarehouseObjectType getAbilityWOT(int id) {
        if(id == 0){
            return null;
        }
        File file = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        scan.nextInt();   //points
        scan.nextInt();   //ability
        int count = 0;
        while(scan.hasNext("0")){
            scan.nextInt();
            count++;
        }
        switch(count){
            case 0:
                return WarehouseObjectType.COIN;
            case 1:
                return WarehouseObjectType.STONE;
            case 2:
                return WarehouseObjectType.SERVANT;
            case 3:
                return WarehouseObjectType.SHIELD;
            default:
                return WarehouseObjectType.NO_TYPE;
        }
    }

    /**
     * Returns the supplies requirements to buy the card.
     * @param id card id
     * @return the supplies requirements to buy the card
     */
    public static SupplyContainer getSuppliesRequirement(int id) {
        if(id == 0){
            return null;
        }
        File file = new File(path);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        for(int i = 0; i < 7; i++)
            scan.nextInt();
        int c = scan.nextInt();
        int st = scan.nextInt();
        int se = scan.nextInt();
        int sh = scan.nextInt();
        int f = scan.nextInt();
        return new SupplyContainer(c, st, se, sh, f);
    }

    /**
     * Returns the cards requirements to buy the card.
     * @param id card id
     * @return the cards requirements to buy the card
     */
    public static ArrayList<CardsRequirement> getCardsRequirements(int id) {
        if(id == 0){
            return null;
        }
        File file = new File(path);
        ArrayList<CardsRequirement> list = new ArrayList<>();
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        for(int i = 0; i < 12; i++)
            scan.nextInt();
        int num1 = scan.nextInt();
        int lvl1 = scan.nextInt();
        int color = scan.nextInt();
        CardCategory color1;
        switch(color){
            case(1):
               color1 = CardCategory.GREEN;
               break;
            case(2):
                color1 = CardCategory.BLUE;
                break;
            case(3):
                color1 = CardCategory.YELLOW;
                break;
            case(4):
                color1 = CardCategory.VIOLET;
                break;
            default:
                color1 = null;
        }
        list.add(new CardsRequirement(num1, lvl1, color1));
        int num2 = scan.nextInt();
        int lvl2 = scan.nextInt();
        int colors = scan.nextInt();
        CardCategory color2;
        switch(colors){
            case(1):
                color2 = CardCategory.GREEN;
                break;
            case(2):
                color2 = CardCategory.BLUE;
                break;
            case(3):
                color2 = CardCategory.YELLOW;
                break;
            case(4):
                color2 = CardCategory.VIOLET;
                break;
            default:
                color2 = null;
        }
        if(num2 != 0)
            list.add(new CardsRequirement(num2, lvl2, color2));
        return list;
    }

    /**
     * Returns the url to the card image.
     * @param id card id
     * @return the url to the card image
     */
    public static String getUrl(int id) {
        if(id == 0){
            return null;
        }
        File file = new File(path);
        ArrayList<CardsRequirement> list = new ArrayList<>();
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.print("BUG"); //TODO end the program
        }
        scan.useDelimiter(", ");
        while(scan.nextInt() != id)
            scan.nextLine();
        for(int i = 0; i < 18; i++)
            scan.nextInt();
        String tmp = scan.next();
        return tmp;
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of 15 Integer
     */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        status.add(id);
        int leaderState = (active ? 1 : (discarded ? 2 : 0));
        status.add(leaderState);

        try {
            status.addAll(getAbility().getStatus());
        } catch (LeaderException le) {
            status.add(0); //fixed input (PRODUCER)
            status.add(0); //fixed output (PRODUCER)
            status.add(0); //mutable output (PRODUCER)
            for(int i=0; i<5; ++i) {
                status.add(0); //current production depot (PRODUCER)
            }
            for(int i=0; i<5; ++i) {
                status.add(0); //current production depot (PRODUCER)
            }
        }

        return status;
    }

    /**
     * Method that gets the win points of the object
     * @return the win points of the object
     */
    @Override
    public int getWinPoints() {
        return winPoints;
    }
}
