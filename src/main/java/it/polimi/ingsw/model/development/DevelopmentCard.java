package it.polimi.ingsw.model.development;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.CardsRequirement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the development card of the game. Every card has its own unique id and has  level, win points,
 * category, production and cost.
 */
public class DevelopmentCard implements HasStatus, WinPointsCountable, AcceptsSupplies {

    private int id;
    private int level = 0;
    private int winPoints = 0;
    private CardCategory category;
    private Production production;
    private SupplyContainer cost;

    private static final String path  = "src/mainCLI/resources/cards_info/DevelopmentCards.txt";


    /**
     * Class constructor: initialize the card.
     * @param id Card id
     * @param level Card level
     * @param winPoints Card win points
     * @param category Card color
     * @param production Card power production
     * @param cost Resources needed to buy the card
     */
    public DevelopmentCard(int id, int level, int winPoints, CardCategory category, Production production, SupplyContainer cost) {

        this.id = id;
        this.level = level;
        this.winPoints = winPoints;
        this.category = category;
        this.production = production;
        this.cost = cost;

    }

    /**
     * Class constructor: initialize the card.
     * @param id Card id
     */
    public DevelopmentCard(int id){
        this.id = id;
        this.level = getLevel(id);
        this.winPoints = getWinPoints(id);
        this.category = getCategory(id);
        this.production = new Production(getInput(id), getOutput(id));
        this.cost = getCost(id);
    }

    /**
     * Returns the input of the production of the development card.
     * @return the input of the production of the development card
     */
    public SupplyContainer getInput() {
        return production.getInput();
    }

    /**
     * Activates the production of the card.
     * @return a SupplyContainer containing the resource in output from the card production
     */
    public SupplyContainer produce() {
        return production.produce();
    }

    /**
     * Checks if the input resources can produce the output.
     * @throws SupplyException there isn't the right number of supplies to activate production
     */
    public void check() throws SupplyException{
        production.check();
    }

    /**
     * Returns the cost of the card.
     * @return the cost of the card
     */
    public SupplyContainer getCost() {
        return cost;
    }

    /**
     * Returns the level of the card.
     * @return the level of the card
     */
    public int getLevel(){
        return level;
    }

    /**
     * Returns the color of the card.
     * @return the color of the card
     */
    public CardCategory getCategory() {
        return category;
    }

    /**
     * Returns the id of the card.
     * @return the id of the card
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the level of the card.
     * @param id card id
     * @return the level of the card
     */
    public static int getLevel(int id) {
        if(id == 0)
            return 0;
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
     * Returns the win points of the card.
     * @param id card id
     * @return the win points of the card
     */
    public static int getWinPoints(int id) {
        if(id == 0)
            return 0;
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
        scan.nextInt();    //lvl
        return scan.nextInt();
    }

    /**
     * Returns the card category.
     * @param id card id
     * @return the card category
     */
    public static CardCategory getCategory(int id) {
        if(id == 0)
            return null;
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
        scan.nextInt();         //lvl
        scan.nextInt();         //winPoints
        int color = scan.nextInt();
        if(color == 1)
            return CardCategory.GREEN;
        else if(color == 2)
            return CardCategory.BLUE;
        else if(color == 3)
            return CardCategory.YELLOW;
        else
            return CardCategory.VIOLET;
    }

    /**
     * Returns the input of the card production.
     * @param id card id
     * @return the input of the card production
     */
    public static SupplyContainer getInput(int id) {
        if(id == 0)
            return null;
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
        scan.nextInt();
        scan.nextInt();
        scan.nextInt();
        int c = scan.nextInt();
        int st = scan.nextInt();
        int se = scan.nextInt();
        int sh = scan.nextInt();
        int f = scan.nextInt();
        return new SupplyContainer(c, st, se, sh, f);
    }

    /**
     * Returns the output of the card production.
     * @param id card id
     * @return the output of the card production
     */
    public static SupplyContainer getOutput(int id) {
        if(id == 0)
            return null;
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
        for(int i = 0; i < 8; i++)
            scan.nextInt();
        int c = scan.nextInt();
        int st = scan.nextInt();
        int se = scan.nextInt();
        int sh = scan.nextInt();
        int f = scan.nextInt();
        return new SupplyContainer(c, st, se, sh, f);
    }

    /**
     * Returns the card cost.
     * @param id card id
     * @return the card cost
     */
    public static SupplyContainer getCost(int id) {
        if(id == 0)
            return null;
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
        for(int i = 0; i < 13; i++)
            scan.nextInt();
        int c = scan.nextInt();
        int st = scan.nextInt();
        int se = scan.nextInt();
        int sh = scan.nextInt();
        int f = scan.nextInt();
        return new SupplyContainer(c, st, se, sh, f);
    }

    /**
     * Returns the url to the card image.
     * @param id card id
     * @return the url to the card image
     */
    public static String getUrl(int id) {
        if(id == 0)
            return null;
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
        for(int i = 0; i < 18; i++)
            scan.nextInt();
        String tmp = scan.next();
        return tmp;
    }

    /**
     * Adds the supply to the storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        production.addSupply(wot, from);
    }

    /**
     * Removes the supply from the storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException {
        production.removeSupply(wot, to);
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return production.additionAllowed(wot, from);
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        return production.removalAllowed(wot, to);
    }

    /**
     * Removes all of the supplies.
     * @return A pair of SupplyContainer containing the removed supplies from the currentSupply of the production. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return production.clearSupplies();
    }

    /**
     * Gets the win points of the object
     * @return the win points of the object
     */
    @Override
    public int getWinPoints() {
        return winPoints;
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of 16 Integer
     */
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        status.add(id);
        status.addAll(production.getStatus());

        return status;
    }

}
