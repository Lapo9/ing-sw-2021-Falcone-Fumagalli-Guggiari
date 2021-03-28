package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.SupplyCard;

/**
 * The DevelopmentGrid class is meant to contain the SupplyCard, divided by level and color in three rows and four columns,
 * every box of the DevelopmentGrid is made of four SupplyCard
 * The first row is for level three SupplyCard, the second row is for level two SupplyCard and the third is for
 * level one SupplyCard.
 * The first column is for green SupplyCard, the second column is for blue SupplyCard, the third is for yellow SupplyCard
 * and the fourth is for purple SupplyCard
 */
public class DevelopmentGrid implements HasStatus{
    ArrayList<ArrayList<SupplyCard>> grid;

    /**
     * Class constructor
     */
    public DevelopmentGrid() {
        grid = new ArrayList<ArrayList<SupplyCard>>();
        for(int i=0; i<12; i++){
            grid.add(new ArrayList<SupplyCard>());
            for(int j=0; j<4; j++)
                grid.get(i).add(new SupplyCard());
        }
    }

    /**
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
     * @return the chosen SupplyCard if the Paycheck contains the right supplies
     * @throws SupplyException if the Paycheck doesn't contain the right supplies to buy the chosen SupplyCard
     * @throws NoSuchCardException if the row x column position is empty
     */
    public SupplyCard buyCard(int column, int row, Paycheck p)throws SupplyException, NoSuchCardException{
        SupplyContainer container = p.getAll();
        SupplyCard temp = new SupplyCard();
        int pos = getPlace(column, row);
        int lastIndex = grid.get(pos).size() - 1;       //lastIndex is the number (-1) of SupplyCard in pos
        if(grid.get(pos).isEmpty())
            throw new NoSuchCardException();
        if(container.confront(grid.get(pos).get(lastIndex.cost)))
            temp = grid.get(pos).remove(lastIndex);
        else
            throw new SupplyException();
        return temp;
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
        if(row == 1)
            return 3;
        else if(row == 2)
            return 2;
        else
            return 1;
    }

    //TODO
    public ArrayList<Integer> getStatus(){

    }
}
