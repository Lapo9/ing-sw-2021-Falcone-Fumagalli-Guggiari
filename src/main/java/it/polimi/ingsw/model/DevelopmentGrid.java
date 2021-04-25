package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.exceptions.*;

/**
 * The DevelopmentGrid class is meant to contain the SupplyCard, divided by level and color in three rows and four columns,
 * every box of the DevelopmentGrid is made of four SupplyCard
 * The first row is for level three SupplyCard, the second row is for level two SupplyCard and the third is for
 * level one SupplyCard.
 * The first column is for green SupplyCard, the second column is for blue SupplyCard, the third is for yellow SupplyCard
 * and the fourth is for purple SupplyCard
 */
public class DevelopmentGrid implements HasStatus{
    private ArrayList<ArrayList<DevelopmentCard>> grid;
    private int boughtCards = 0;

    /**
     * Class constructor
     */
    public DevelopmentGrid() {
        grid = new ArrayList<ArrayList<DevelopmentCard>>();
        fill(false);
    }

    /**
     * Class constructor used to create a fixed DevelopmentGrid
     * @param type needed to create a not-random DevelopmentGrid
     */
    public DevelopmentGrid(boolean type){
        grid = new ArrayList<ArrayList<DevelopmentCard>>();
        fill(true);
    }

    /*
     * The getPlace method returns the index of the ArrayList given column and row number
     * @param column is the column number
     * @param row is the row number
     * @return the index of the ArrayList that represents the grid
     */
    private int getPlace(int column, int row){
        return 4 * row + column;
    }

    /**
     * The buyCard method returns the last card in the row x column position in the DevelopmentGrid if the Paycheck
     * contains all the supplies required to buy the SupplyCard
     * @param column is the column number (between 0 and 3)
     * @param row is the row number (between 0 and 2)
     * @param p is a type of SupplyContainer which contains resources to buy a SupplyCard
     * @param leadersSpace is the LeaderCard container
     * @return the chosen SupplyCard if the Paycheck contains the right supplies
     * @throws SupplyException if the Paycheck doesn't contain the right supplies to buy the chosen SupplyCard
     * @throws NoSuchCardException if the row x column position is empty
     */
    public DevelopmentCard buyCard(int column, int row, Paycheck p, LeadersSpace leadersSpace)throws SupplyException, NoSuchCardException{
        SupplyContainer container = p.getAll();
        int pos = getPlace(column, row);
        if(grid.get(pos).isEmpty())
            throw new NoSuchCardException("There isn't a card in position "+column+"; "+row);

        //We have to check if there are any active LeaderCard that have the ability to get a discount
        WarehouseObjectType discountType0 = null;
        int discount0 = 0;
        WarehouseObjectType discountType1 = null;
        int discount1 = 0;
        try{
            LeaderAbility leadAbil0 = leadersSpace.getLeaderAbility(0);
            SupplyContainer abil0 = new SupplyContainer(leadAbil0.getDiscount());
            if(abil0.getQuantity() != 0) {
               if(abil0.getQuantity(WarehouseObjectType.COIN) != 0){
                   discountType0 = WarehouseObjectType.COIN;
                   discount0 = abil0.getQuantity(WarehouseObjectType.COIN);
               }
               else if(abil0.getQuantity(WarehouseObjectType.SERVANT) != 0){
                   discountType0 = WarehouseObjectType.SERVANT;
                   discount0 = abil0.getQuantity(WarehouseObjectType.SERVANT);
               }
               else if(abil0.getQuantity(WarehouseObjectType.SHIELD) != 0){
                   discountType0 = WarehouseObjectType.SHIELD;
                   discount0 = abil0.getQuantity(WarehouseObjectType.SHIELD);
               }
               else if(abil0.getQuantity(WarehouseObjectType.STONE) != 0){
                   discountType0 = WarehouseObjectType.STONE;
                   discount0 = abil0.getQuantity(WarehouseObjectType.STONE);
               }
            }
        }catch(LeaderException | NoSuchMethodException le0){
            discount0 = 0;
        }

        try{
            LeaderAbility leadAbil1 = leadersSpace.getLeaderAbility(1);
            SupplyContainer abil1 = new SupplyContainer(leadAbil1.getDiscount());
            if(abil1.getQuantity() != 0) {
                if(abil1.getQuantity(WarehouseObjectType.COIN) != 0){
                    discountType1 = WarehouseObjectType.COIN;
                    discount1 = abil1.getQuantity(WarehouseObjectType.COIN);
                }
                else if(abil1.getQuantity(WarehouseObjectType.SERVANT) != 0){
                    discountType1 = WarehouseObjectType.SERVANT;
                    discount1 = abil1.getQuantity(WarehouseObjectType.SERVANT);
                }
                else if(abil1.getQuantity(WarehouseObjectType.SHIELD) != 0){
                    discountType1 = WarehouseObjectType.SHIELD;
                    discount1 = abil1.getQuantity(WarehouseObjectType.SHIELD);
                }
                else if(abil1.getQuantity(WarehouseObjectType.STONE) != 0){
                    discountType1 = WarehouseObjectType.STONE;
                    discount1 = abil1.getQuantity(WarehouseObjectType.STONE);
                }
            }
        }catch(LeaderException | NoSuchMethodException le1){
            discount1 = 0;
        }

        SupplyContainer priceContainer = new SupplyContainer(grid.get(pos).get(0).getCost());
        if(discount0 != 0 || discount1 != 0) {
            if(discount0 != 0 && priceContainer.getQuantity(discountType0) != 0)
                priceContainer.removeSupply(discountType0);
            if(discount1 != 0 && priceContainer.getQuantity(discountType1) != 0)
                priceContainer.removeSupply(discountType1);
        }
        if(container.equals(priceContainer)) {
            boughtCards++;
            return grid.get(pos).remove(0);
        }
        else {
            throw new SupplyException("Cannot buy this card because of supplies if paycheck");
        }
    }

    /**
     * Removes a card of the specified color. It tries to remove first cards of level 1, then 2, then 3.
     * @param color Color to remove
     * @throws NoSuchCardException Cards of specified color are not present
     */
    public void removeCard(ActionTilesStack.ActionTile color) throws NoSuchCardException{
        for(int i=0; i<3; ++i){
            if (!grid.get(getPlace(color.ordinal()+1, 3-i)).isEmpty()){
                grid.remove(getPlace(color.ordinal()+1, 3-i)).get(grid.get(getPlace(color.ordinal()+1, 3-i)).size()-1);
                return;
            }
        }
        throw new NoSuchCardException("There isn't a "+color.toString()+" card");
    }

    /**
     * Returns the number of cards that have been bought.
     * @return the number of cards that have been bought
     */
    public int getBoughtCards() {
        return boughtCards;
    }

    /**
     * The getLevel function returns the level of the SupplyCard contained in row x column space in the DevelopmentGrid
     * @param column is the column number
     * @param row is the row number
     * @return the level of the SupplyCard in the given position
     * @throws NoSuchCardException if the place is empty
     */
    public int getLevel(int column, int row) throws NoSuchCardException{
        int pos = getPlace(column, row);
        if(grid.get(pos).isEmpty())
            throw new NoSuchCardException("There isn't a card in position "+column+"; "+row);
        else
            return 3 - row;
    }

    //TODO
    public ArrayList<Integer> getStatus(){
        return null;
    }


    private void fill(boolean type) {
        ArrayList<DevelopmentCard> greenLvlOne = new ArrayList<>();
        greenLvlOne.add(new DevelopmentCard(1, 1, 1, CardCategory.GREEN, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 1)), new SupplyContainer(0, 0, 0, 2, 0)));
        greenLvlOne.add(new DevelopmentCard(2, 1, 2, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 0, 0, 0), new SupplyContainer(0, 0, 1, 0, 0)), new SupplyContainer(0, 1, 1, 1, 0)));
        greenLvlOne.add(new DevelopmentCard(3, 1, 3, CardCategory.GREEN, new Production(new SupplyContainer(0, 0, 2, 0, 0), new SupplyContainer(1, 1, 0, 1, 0)), new SupplyContainer(0, 0, 0, 3, 0)));
        greenLvlOne.add(new DevelopmentCard(4, 1, 4, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 2, 0)));

        ArrayList<DevelopmentCard> blueLvlOne = new ArrayList<>();
        blueLvlOne.add(new DevelopmentCard(5, 1, 1, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 0, 0, 0, 1)), new SupplyContainer(2, 0, 0, 0, 0)));
        blueLvlOne.add(new DevelopmentCard(6, 1, 2, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 1, 0, 0, 0)), new SupplyContainer(1, 1, 1, 0, 0)));
        blueLvlOne.add(new DevelopmentCard(7, 1, 3, CardCategory.BLUE, new Production(new SupplyContainer(0, 2, 0, 0, 0), new SupplyContainer(1, 0, 1, 1, 0)), new SupplyContainer(3, 0, 0, 0, 0)));
        blueLvlOne.add(new DevelopmentCard(8, 1, 4, CardCategory.BLUE, new Production(new SupplyContainer(0, 1, 0, 1, 0), new SupplyContainer(0, 2, 0, 0, 1)), new SupplyContainer(2, 0, 2, 0, 0)));

        ArrayList<DevelopmentCard> yellowLvlOne = new ArrayList<>();
        yellowLvlOne.add(new DevelopmentCard(9, 1, 1, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 0, 0, 0, 1)), new SupplyContainer(0, 2, 0, 0, 0)));
        yellowLvlOne.add(new DevelopmentCard(10, 1, 2, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(1, 0, 0, 0, 0)), new SupplyContainer(1, 1, 0, 1, 0)));
        yellowLvlOne.add(new DevelopmentCard(11, 1, 3, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(1, 1, 1, 0, 0)), new SupplyContainer(0, 3, 0, 0, 0)));
        yellowLvlOne.add(new DevelopmentCard(12, 1, 4, CardCategory.YELLOW, new Production(new SupplyContainer(1, 0, 1, 0, 0), new SupplyContainer(0, 0, 0, 2, 1)), new SupplyContainer(0, 2, 0, 2, 0)));

        ArrayList<DevelopmentCard> violetLvlOne = new ArrayList<>();
        violetLvlOne.add(new DevelopmentCard(13, 1, 1, CardCategory.VIOLET, new Production(new SupplyContainer(0, 1, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 1)), new SupplyContainer(0, 0, 2, 0, 0)));
        violetLvlOne.add(new DevelopmentCard(14, 1, 2, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 1, 0)), new SupplyContainer(1, 0, 1, 1, 0)));
        violetLvlOne.add(new DevelopmentCard(15, 1, 3, CardCategory.VIOLET, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 1, 1, 1, 0)), new SupplyContainer(0, 0, 3, 0, 0)));
        violetLvlOne.add(new DevelopmentCard(16, 1, 4, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 1, 0), new SupplyContainer(0, 2, 0, 0, 1)), new SupplyContainer(0, 2, 2, 0, 0)));

        ArrayList<DevelopmentCard> greenLvlTwo = new ArrayList<>();
        greenLvlTwo.add(new DevelopmentCard(17, 2, 5, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 0, 4, 0)));
        greenLvlTwo.add(new DevelopmentCard(18, 2, 6, CardCategory.GREEN, new Production(new SupplyContainer(0, 0, 1, 1, 0), new SupplyContainer(0, 3, 0, 0, 0)), new SupplyContainer(0, 0, 2, 3, 0)));
        greenLvlTwo.add(new DevelopmentCard(19, 2, 7, CardCategory.GREEN, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 2, 0, 0, 2)), new SupplyContainer(0, 0, 0, 5, 0)));
        greenLvlTwo.add(new DevelopmentCard(20, 2, 8, CardCategory.GREEN, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 2, 1)), new SupplyContainer(3, 0, 0, 3, 0)));

        ArrayList<DevelopmentCard> blueLvlTwo = new ArrayList<>();
        blueLvlTwo.add(new DevelopmentCard(21, 2, 5, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(4, 0, 0, 0, 0)));
        blueLvlTwo.add(new DevelopmentCard(22, 2, 6, CardCategory.BLUE, new Production(new SupplyContainer(1, 1, 0, 0, 0), new SupplyContainer(0, 0, 3, 0, 0)), new SupplyContainer(3, 2, 0, 0, 0)));
        blueLvlTwo.add(new DevelopmentCard(23, 2, 7, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 2, 0, 0), new SupplyContainer(0, 0, 0, 2, 2)), new SupplyContainer(5, 0, 0, 0, 0)));
        blueLvlTwo.add(new DevelopmentCard(24, 2, 8, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(0, 2, 0, 0, 1)), new SupplyContainer(3, 3, 0, 0, 0)));

        ArrayList<DevelopmentCard> yellowLvlTwo = new ArrayList<>();
        yellowLvlTwo.add(new DevelopmentCard(25, 2, 5, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 4, 0, 0, 0)));
        yellowLvlTwo.add(new DevelopmentCard(26, 2, 6, CardCategory.YELLOW, new Production(new SupplyContainer(0, 1, 0, 1, 0), new SupplyContainer(3, 0, 0, 0, 0)), new SupplyContainer(0, 3, 0, 2, 0)));
        yellowLvlTwo.add(new DevelopmentCard(27, 2, 7, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 2, 0, 2)), new SupplyContainer(0, 5, 0, 0, 0)));
        yellowLvlTwo.add(new DevelopmentCard(28, 2, 8, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(2, 0, 0, 0, 1)), new SupplyContainer(0, 3, 3, 0, 0)));

        ArrayList<DevelopmentCard> violetLvlTwo = new ArrayList<>();
        violetLvlTwo.add(new DevelopmentCard(29, 2, 5, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 0, 0, 0, 2)), new SupplyContainer(0, 0, 4, 0, 0)));
        violetLvlTwo.add(new DevelopmentCard(30, 2, 6, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 1, 0, 0), new SupplyContainer(0, 0, 0, 3, 0)), new SupplyContainer(2, 0, 3, 0, 0)));
        violetLvlTwo.add(new DevelopmentCard(31, 2, 7, CardCategory.VIOLET, new Production(new SupplyContainer(0, 2, 0, 0, 0), new SupplyContainer(2, 0, 0, 0, 2)), new SupplyContainer(0, 0, 5, 0, 0)));
        violetLvlTwo.add(new DevelopmentCard(32, 2, 8, CardCategory.VIOLET, new Production(new SupplyContainer(0, 1, 0, 0, 0), new SupplyContainer(0, 0, 2, 0, 1)), new SupplyContainer(0, 0, 3, 3, 0)));

        ArrayList<DevelopmentCard> greenLvlThree = new ArrayList<>();
        greenLvlThree.add(new DevelopmentCard(33, 3, 9, CardCategory.GREEN, new Production(new SupplyContainer(2, 0, 0, 0, 0), new SupplyContainer(0, 3, 0, 0, 2)), new SupplyContainer(0, 0, 0, 6, 0)));
        greenLvlThree.add(new DevelopmentCard(34, 3, 10, CardCategory.GREEN, new Production(new SupplyContainer(1, 0, 1, 0, 0), new SupplyContainer(0, 2, 0, 2, 1)), new SupplyContainer(0, 0, 2, 5, 0)));
        greenLvlThree.add(new DevelopmentCard(35, 3, 11, CardCategory.GREEN, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(1, 0, 0, 0, 3)), new SupplyContainer(0, 0, 0, 7, 0)));
        greenLvlThree.add(new DevelopmentCard(36, 3, 12, CardCategory.GREEN, new Production(new SupplyContainer(0, 1, 0, 0, 0), new SupplyContainer(3, 0, 0, 1, 0)), new SupplyContainer(4, 0, 0, 4, 0)));

        ArrayList<DevelopmentCard> blueLvlThree = new ArrayList<>();
        blueLvlThree.add(new DevelopmentCard(37, 3, 9, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 2, 0, 0), new SupplyContainer(0, 0, 0, 3, 2)), new SupplyContainer(6, 0, 0, 0, 0)));
        blueLvlThree.add(new DevelopmentCard(38, 3, 10, CardCategory.BLUE, new Production(new SupplyContainer(1, 0, 0, 1, 0), new SupplyContainer(0, 2, 2, 0, 1)), new SupplyContainer(5, 2, 0, 0, 0)));
        blueLvlThree.add(new DevelopmentCard(39, 3, 11, CardCategory.BLUE, new Production(new SupplyContainer(0, 1, 0, 0, 0), new SupplyContainer(0, 0, 0, 1, 3)), new SupplyContainer(7, 0, 0, 0, 0)));
        blueLvlThree.add(new DevelopmentCard(40, 3, 12, CardCategory.BLUE, new Production(new SupplyContainer(0, 0, 1, 0, 0), new SupplyContainer(1, 0, 0, 3, 0)), new SupplyContainer(4, 4, 0, 0, 0)));

        ArrayList<DevelopmentCard> yellowLvlThree = new ArrayList<>();
        yellowLvlThree.add(new DevelopmentCard(41, 3, 9, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 2, 0), new SupplyContainer(0, 0, 3, 0, 2)), new SupplyContainer(0, 6, 0, 0, 0)));
        yellowLvlThree.add(new DevelopmentCard(42, 3, 10, CardCategory.YELLOW, new Production(new SupplyContainer(0, 1, 1, 0, 0), new SupplyContainer(2, 0, 0, 2, 1)), new SupplyContainer(0, 5, 2, 0, 0)));
        yellowLvlThree.add(new DevelopmentCard(43, 3, 11, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 0, 1, 0, 3)), new SupplyContainer(0, 7, 0, 0, 0)));
        yellowLvlThree.add(new DevelopmentCard(44, 3, 12, CardCategory.YELLOW, new Production(new SupplyContainer(0, 0, 0, 1, 0), new SupplyContainer(0, 1, 3, 0, 0)), new SupplyContainer(0, 4, 4, 0, 0)));

        ArrayList<DevelopmentCard> violetLvlThree = new ArrayList<>();
        violetLvlThree.add(new DevelopmentCard(45, 3, 9, CardCategory.VIOLET, new Production(new SupplyContainer(0, 2, 0, 0, 0), new SupplyContainer(3, 0, 0, 0, 2)), new SupplyContainer(0, 0, 6, 0, 0)));
        violetLvlThree.add(new DevelopmentCard(46, 3, 10, CardCategory.VIOLET, new Production(new SupplyContainer(0, 1, 0, 1, 0), new SupplyContainer(2, 0, 2, 0, 1)), new SupplyContainer(2, 0, 5, 0, 0)));
        violetLvlThree.add(new DevelopmentCard(47, 3, 11, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 1, 0, 0, 3)), new SupplyContainer(0, 0, 7, 0, 0)));
        violetLvlThree.add(new DevelopmentCard(48, 3, 12, CardCategory.VIOLET, new Production(new SupplyContainer(1, 0, 0, 0, 0), new SupplyContainer(0, 3, 1, 0, 0)), new SupplyContainer(0, 0, 4, 4, 0)));

        if(type){
            //fill the grid with cards in a fixed order
            grid.add(new ArrayList<>(greenLvlThree));
            grid.add(new ArrayList<>(blueLvlThree));
            grid.add(new ArrayList<>(yellowLvlThree));
            grid.add(new ArrayList<>(violetLvlThree));

            grid.add(new ArrayList<>(greenLvlTwo));
            grid.add(new ArrayList<>(blueLvlTwo));
            grid.add(new ArrayList<>(yellowLvlTwo));
            grid.add(new ArrayList<>(violetLvlTwo));

            grid.add(new ArrayList<>(greenLvlOne));
            grid.add(new ArrayList<>(blueLvlOne));
            grid.add(new ArrayList<>(yellowLvlOne));
            grid.add(new ArrayList<>(violetLvlOne));
        }
        else{
            //fill the grid with cards in a random order
            grid.add(new ArrayList<>());
            for (int i = 0; i < 4; i++){
                int rnd = (int)(Math.random() * (4-i));
                DevelopmentCard tmp = greenLvlThree.remove(rnd);
                grid.get(0).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = blueLvlThree.remove(rnd);
                grid.get(1).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = yellowLvlThree.remove(rnd);
                grid.get(2).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = violetLvlThree.remove(rnd);
                grid.get(3).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = greenLvlTwo.remove(rnd);
                grid.get(4).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = blueLvlTwo.remove(rnd);
                grid.get(5).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = yellowLvlTwo.remove(rnd);
                grid.get(6).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = violetLvlTwo.remove(rnd);
                grid.get(7).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = greenLvlOne.remove(rnd);
                grid.get(8).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = blueLvlOne.remove(rnd);
                grid.get(9).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = yellowLvlOne.remove(rnd);
                grid.get(10).add(tmp);
            }

            grid.add(new ArrayList<>());
            for(int i = 0; i < 4; i++){
                int rnd = (int)(Math.random())*(4-i);
                DevelopmentCard tmp = violetLvlOne.remove(rnd);
                grid.get(11).add(tmp);
            }
        }
    }
}
