package it.polimi.ingsw.model;

import it.polimi.ingsw.model.SupplyContainer;
import java.util.ArrayList;

/**
 * BoundedSupplyContainer is a class that represents a shelf in the warehouse depot of the personal board of the player
 * It has two main attributes, position, which stands for the number of the shelves in the warehouse and which can be 1, 2 or 3,
 * and max, which represents the max dimension of the shelf, implemented by the SupplyContainer class
 * Conventions: shelf number 1 can contain 3 supplies, shelf number 2 can contain 2 supplies and shelf number 3 can
 * contain 1 supply. A BoundedSupplyContainer can store just one type of supplies
 */
public class BoundedSupplyContainer implements AcceptsSupplies{
    private SupplyContainer sc;
    private int position;
    private int max;

    /**
     * Class constructor
     * @param pos shelf's number
     */
    public BoundedSupplyContainer(int pos){
        sc = new SupplyContainer();
        position = pos;
        max = 4-pos;
    }

    /**
     * The getMax method can be used to find the max dimension of the BoundedSupplyContainer
     * @return max dimension of the BoundedSupplyContainer
     */
    public int getMax(){
        return max;
    }

    /**
     * The getPosition method can be used to find the position of the BoundedSupplyContainer
     * @return the position of the BoundedSupplyContainer
     */
    public int getPosition() {
        return position;
    }

    /**
     * The setMax method modify the dimension of the BoundedSupplyContainer. Since max and position are related, if max
     * changes, position changes
     * @param i is the new max dimension of the BoundedSupplyContainer
     */
    public void setMax(int i){
        max = i;
        if(i==3)
            position = 1;
        else if(i==2)
            position = 2;
        else if(i==1)
            position = 3;
    }

    /**
     * The setPosition method modify the position of the BoundedSupplyContainer. Since max and position are related,
     * if position changes, max changes
     * @param i is the new position of the BoundedSupplyContainer
     */
    public void setPosition(int i) {
        position = i;
        if(i==1)
            max = 3;
        else if(i==2)
            max = 2;
        else if(i==3)
            max = 1;
    }

    /**
     * The getQuantity method can be used to find the number of supplies that are currently in the BoundedSupplyContainer
     * @return number of supplies in the BoundedSupplyContainer
     */
    public int getQuantity(){
        int temp = 0;
        if(getType() == null)
             return temp;
        temp = sc.getQuantity(getType());
        return temp;
    }

    /**
     * The getType method can be used to find the type of the supplies stored in the BoundedSupplyContainer
     * @return the WarehouseObjectType of the supplies contained in the BoundedSupplyContainer
     */
    public WarehouseObjectType getType(){
        if(sc.getQuantity(WarehouseObjectType.COIN) != 0)
            return WarehouseObjectType.COIN;
        else if(sc.getQuantity(WarehouseObjectType.STONE) != 0)
            return WarehouseObjectType.STONE;
        else if(sc.getQuantity(WarehouseObjectType.SERVANT) != 0)
            return WarehouseObjectType.SERVANT;
        else if(sc.getQuantity(WarehouseObjectType.SHIELD) != 0)
            return WarehouseObjectType.SHIELD;
        else
            return null;
    }

    /**
     * The addSupply method adds a supply to the BoundedSupplyContainer
     * @param wot is the type of supply that we want to add
     * @throws SupplyException if the BoundedSupplyContainer is already full or if the supply type passed as an input is
     * different than the type of supplies that already are in the BoundedSupplyContainer
     */
    public void addSupply(WarehouseObjectType wot)throws SupplyException{
        if(getQuantity() == max)
            throw new SupplyException();
        if(wot != getType() && getQuantity() != 0)
            throw new SupplyException();
        sc.addSupply(wot);
    }

    /**
     * The removeSupply method removes a supply from the BoundedSupplyContainer
     * @param wot is the type of supply that we want to remove
     * @throws SupplyExcecption if the BoundedSupplyContainer is already empty or if the supply type passed as an input
     * is different than the type of supplies stored in the BoundedSupplyContainer
     */
    public void removeSupply(WarehouseObjectType wot)throws SupplyExcecption{
        if(getQuantity() == 0)
            throw new SupplyException();
        if(wot != getType())
            throw new SupplyException();
        sc.removeSupply(wot);
    }

    /**
     * The clearSupplies method removes all the supplies from the SupplyContainer
     * @return an empty SupplyContainer
     */
    public SupplyContainer clearSupplies(){
        if(getQuantity() == 0)
            return sc;
        else
            return sc.clearSupplies();
    }

    //TODO
    public ArrayList<Integer> getStatus(){

    }
}
