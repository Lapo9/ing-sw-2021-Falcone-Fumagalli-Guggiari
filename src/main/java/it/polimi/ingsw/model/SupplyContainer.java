package it.polimi.ingsw.model;

import java.util.ArrayList;

public class SupplyContainer implements AcceptsSupplies, HasStatus{
    private int coins;
    private int stones;
    private int servants;
    private int shields;
    private int faith;

    /**
     * class constructor
     */
    public SupplyContainer() {
        coins=0;
        stones=0;
        servants=0;
        shields=0;
        faith=0;
    }

    /**
     * The getQuantity method returns the quantity of the supply type passed as input that currently is in the SupplyContainer
     * @param wot is one of the five types of resources in the game
     * @return is the supply quantity of the given type present in the SupplyContainer
     */
    public int getQuantity(WarehouseObjectType wot){
        switch(wot){
            case(COINS):
                return coins;
                break;
            case(STONES):
                return stones;
                break;
            case(SERVANTS):
                return servants;
                break;
            case(SHIELDS):
                return shields;
                break;
            default:
                return faith;
        }
    }

    /**
     * The add method is used when you have two different SupplyContainer, sc1 and sc2, and you want to add all the
     * elements of the second container in the first one
     * @param sc is the SupplyContainer that you want to add to your SupplyContainer
     */
    public void add(SupplyContainer sc){
        this.coins = sc.coins + this.coins;
        this.stones = sc.stones + this.stones;
        this.servants = sc.servants + this.servants;
        this.shields = sc.shields + this.shields;
        this.faith = sc.faith + this.faith;
    }

    //TODO
    public ArrayList<Integer> getStatus(){

    }

    /**
     * The addSupply method add one supply of the given type to the SupplyContainer
     * @param wot is one of the five types of resources in the game
     * @throws SupplyException is not required here
     */
    public void addSupply(WarehouseObjectType wot) throws SupplyException{
        switch(wot) {
            case (COINS):
                coins++;
            case (STONES):
                stones++;
            case (SERVANTS):
                servants++;
            case (SHIELDS):
                shields++;
            case(FAITH):
                faith++;
        }
    }

    /**
     * The addSupply method add one supply of the given type to the SupplyContainer
     * @param wot is one of the five types of resources in the game
     * @throws SupplyException if there are zero resources of the given type
     */
    public void removeSupply(WarehouseObjectType wot) throws SupplyException{
        switch(wot){
            case(COINS):
            {
                if(coins <= 0)
                    throw new SupplyException();
                else
                    coins--;
            }
            case(STONES):
            {
                if(stones <= 0)
                    throw new SupplyException();
                else
                    stones--;
            }
            case(SERVANTS):
            {
                if(servants <= 0)
                    throw new SupplyException();
                else
                    servants--;
            }
            case(SHIELDS):
            {
                if(shields <= 0)
                    throw new SupplyException();
                else
                    shields--;
            }
            case(FAITH):
            {
                if(faith <= 0)
                    throw new SupplyException();
                else
                    faith--;
            }
        }
    }

    /**
     * The clearSupply method removes all the resources that are in the SupplyContainer
     * @return an empty SupplyContainer
     */
    public SupplyContainer clearSupply(){
        return new SupplyContainer();
    }
}
