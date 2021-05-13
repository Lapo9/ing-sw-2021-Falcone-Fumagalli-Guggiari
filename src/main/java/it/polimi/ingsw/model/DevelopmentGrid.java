package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.model.development.DevelopmentCard;
import it.polimi.ingsw.model.development.Paycheck;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.leaders.leader_abilities.LeaderAbility;
import it.polimi.ingsw.model.leaders.LeadersSpace;

/**
 * The DevelopmentGrid is meant to contain the SupplyCard, divided by level and color in three rows and four columns,
 * every box of the DevelopmentGrid is made of four SupplyCard.
 * The first row is for level three SupplyCard, the second row is for level two SupplyCard and the third is for
 * level one SupplyCard.
 * The first column is for green SupplyCard, the second column is for blue SupplyCard, the third is for yellow SupplyCard
 * and the fourth is for purple SupplyCard.
 */
public class DevelopmentGrid implements HasStatus{

    private ArrayList<ArrayList<DevelopmentCard>> grid;
    private int boughtCards = 0;


    /**
     * Creates a DevelopmentGrid where cards are placed in a random order, according to rules.
     */
    public DevelopmentGrid() {
        grid = new ArrayList<ArrayList<DevelopmentCard>>();
        fill(false);
    }


    /**
     * Create a fixed DevelopmentGrid.
     * @param type needed to create a not-random DevelopmentGrid
     */
    public DevelopmentGrid(boolean type){
        grid = new ArrayList<ArrayList<DevelopmentCard>>();
        fill(true);
    }


    //Returns the index of the ArrayList given column and row number.
    private int getPlace(int column, int row){
        return 4 * row + column;
    }


    /**
     * Returns the first card in the row x column position in the DevelopmentGrid if the Paycheck contains all the
     * supplies required to buy the SupplyCard.
     * @param column is the column number (between 0 and 3)
     * @param row is the row number (between 0 and 2)
     * @param p contains resources to buy a SupplyCard
     * @param leadersSpace is the LeaderCard container
     * @return the chosen SupplyCard if the Paycheck contains the right supplies
     * @throws SupplyException the Paycheck doesn't contain the right supplies to buy the chosen SupplyCard
     * @throws NoSuchCardException the row x column position is empty
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
     * @param color style to remove
     * @throws NoSuchCardException cards of specified color are not present
     */
    public void removeCard(ActionTilesStack.ActionTile color) throws NoSuchCardException{
        for(int i=0; i<3; ++i){
            if (!grid.get(getPlace(color.ordinal(), 2-i)).isEmpty()){
                grid.get(getPlace(color.ordinal(), 2-i)).remove(0);
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
     * Returns the level of the SupplyCard contained in the given position.
     * @param column is the column number
     * @param row is the row number
     * @return the level of the SupplyCard in the given position
     * @throws NoSuchCardException the place is empty
     */
    public int getLevel(int column, int row) throws NoSuchCardException{
        int pos = getPlace(column, row);
        if(grid.get(pos).isEmpty())
            throw new NoSuchCardException("There isn't a card in position "+column+"; "+row);
        else
            return 3 - row;
    }


    /*The status contains all the id's of the cards in the grid. 0 if the card has been removed.
    *The status is made this way:
    * lvl 3 cards   green
    *               blue
    *               yellow
    *               violet
    *lvl 2 cards   green
    *              blue
    *              yellow
    *              violet
    *lvl 1 cards   green
    *              blue
    *              yellow
    *              blue
    */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();
        for(int i = 0; i<12; i++){
            ArrayList<DevelopmentCard> list = grid.get(i);
            if(list.isEmpty())
                status.add(0);
            else
                status.add(list.get(0).getId());
        }
        return status;
    }


    //Puts the cards in the grid.
    private void fill(boolean type) {
        ArrayList<DevelopmentCard> greenLvlOne = new ArrayList<>();
        greenLvlOne.add(new DevelopmentCard(1));
        greenLvlOne.add(new DevelopmentCard(2));
        greenLvlOne.add(new DevelopmentCard(3));
        greenLvlOne.add(new DevelopmentCard(4));

        ArrayList<DevelopmentCard> blueLvlOne = new ArrayList<>();
        blueLvlOne.add(new DevelopmentCard(5));
        blueLvlOne.add(new DevelopmentCard(6));
        blueLvlOne.add(new DevelopmentCard(7));
        blueLvlOne.add(new DevelopmentCard(8));

        ArrayList<DevelopmentCard> yellowLvlOne = new ArrayList<>();
        yellowLvlOne.add(new DevelopmentCard(9));
        yellowLvlOne.add(new DevelopmentCard(10));
        yellowLvlOne.add(new DevelopmentCard(11));
        yellowLvlOne.add(new DevelopmentCard(12));

        ArrayList<DevelopmentCard> violetLvlOne = new ArrayList<>();
        violetLvlOne.add(new DevelopmentCard(13));
        violetLvlOne.add(new DevelopmentCard(14));
        violetLvlOne.add(new DevelopmentCard(15));
        violetLvlOne.add(new DevelopmentCard(16));

        ArrayList<DevelopmentCard> greenLvlTwo = new ArrayList<>();
        greenLvlTwo.add(new DevelopmentCard(17));
        greenLvlTwo.add(new DevelopmentCard(18));
        greenLvlTwo.add(new DevelopmentCard(19));
        greenLvlTwo.add(new DevelopmentCard(20));

        ArrayList<DevelopmentCard> blueLvlTwo = new ArrayList<>();
        blueLvlTwo.add(new DevelopmentCard(21));
        blueLvlTwo.add(new DevelopmentCard(22));
        blueLvlTwo.add(new DevelopmentCard(23));
        blueLvlTwo.add(new DevelopmentCard(24));

        ArrayList<DevelopmentCard> yellowLvlTwo = new ArrayList<>();
        yellowLvlTwo.add(new DevelopmentCard(25));
        yellowLvlTwo.add(new DevelopmentCard(26));
        yellowLvlTwo.add(new DevelopmentCard(27));
        yellowLvlTwo.add(new DevelopmentCard(28));

        ArrayList<DevelopmentCard> violetLvlTwo = new ArrayList<>();
        violetLvlTwo.add(new DevelopmentCard(29));
        violetLvlTwo.add(new DevelopmentCard(30));
        violetLvlTwo.add(new DevelopmentCard(31));
        violetLvlTwo.add(new DevelopmentCard(32));

        ArrayList<DevelopmentCard> greenLvlThree = new ArrayList<>();
        greenLvlThree.add(new DevelopmentCard(33));
        greenLvlThree.add(new DevelopmentCard(34));
        greenLvlThree.add(new DevelopmentCard(35));
        greenLvlThree.add(new DevelopmentCard(36));

        ArrayList<DevelopmentCard> blueLvlThree = new ArrayList<>();
        blueLvlThree.add(new DevelopmentCard(37));
        blueLvlThree.add(new DevelopmentCard(38));
        blueLvlThree.add(new DevelopmentCard(39));
        blueLvlThree.add(new DevelopmentCard(40));

        ArrayList<DevelopmentCard> yellowLvlThree = new ArrayList<>();
        yellowLvlThree.add(new DevelopmentCard(41));
        yellowLvlThree.add(new DevelopmentCard(42));
        yellowLvlThree.add(new DevelopmentCard(43));
        yellowLvlThree.add(new DevelopmentCard(44));

        ArrayList<DevelopmentCard> violetLvlThree = new ArrayList<>();
        violetLvlThree.add(new DevelopmentCard(45));
        violetLvlThree.add(new DevelopmentCard(46));
        violetLvlThree.add(new DevelopmentCard(47));
        violetLvlThree.add(new DevelopmentCard(48));

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
