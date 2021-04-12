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
        for(int i=0; i<12; i++){
            grid.add(new ArrayList<DevelopmentCard>());
            for(int j=0; j<4; j++){
                grid.get(i).add(new DevelopmentCard()); //FIXME we have to choose what card goes where
            }
        }
    }

    /*
     * The getPlace method returns the index of the ArrayList given column and row number
     * @param column is the column number
     * @param row is the row number
     * @return the index of the ArrayList that represents the grid
     */
    private int getPlace(int column, int row){
        return (4*column)+row-5;
    }

    /**
     * The buyCard method returns the last card in the row x column position in the DevelopmentGrid if the Paycheck
     * contains all the supplies required to buy the SupplyCard
     * @param column is the column number
     * @param row is the row number
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
            throw new NoSuchCardException();

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
        }catch(LeaderException le0){
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
        }catch(LeaderException le1){
            discount1 = 0;
        }

        int lastIndex = grid.get(pos).size() - 1;       //lastIndex is the number (-1) of SupplyCard in pos
        SupplyContainer priceContainer = new SupplyContainer(grid.get(pos).get(lastIndex).getCost());
        if(discount0 != 0 || discount1 != 0) {
            if(priceContainer.getQuantity(discountType0, discountType1) != 0)
                if(discount0 != 0)
                    priceContainer.removeSupply(discountType0);
                if(discount1 != 0)
                    priceContainer.removeSupply(discountType1);
        }
        if(container.equals(priceContainer)) {
            return grid.get(pos).remove(lastIndex);
            boughtCards++;
        }
        else {
            throw new SupplyException();
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
        throw new NoSuchCardException();
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
            throw new NoSuchCardException();
        return grid.get(pos).getLevel();
    }

    //TODO
    public ArrayList<Integer> getStatus(){
        return null;
    }
}
