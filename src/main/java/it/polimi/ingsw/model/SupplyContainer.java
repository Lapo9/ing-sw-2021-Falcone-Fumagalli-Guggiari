package it.polimi.ingsw.model;

import it.polimi.ingsw.model.WarehouseObjectType;
import java.util.ArrayList;

public class SupplyContainer implements AcceptsSupplies, HasStatus{
    private int coin = 0;
    private int stone = 0;
    private int servant = 0;
    private int shield = 0;
    private int faithMarker = 0;

    /**
     * class constructor
     */
    public SupplyContainer() {

    }

    /**
     * The getQuantity method returns the quantity of the supply type passed as input that currently is in the SupplyContainer
     * @param wot is one of the five types of resources in the game
     * @return is the supply quantity of the given type present in the SupplyContainer
     */
    public int getQuantity(WarehouseObjectType wot){
        int temp = 0;
        if(wot == WarehouseObjectType.COIN)
            temp = coin;
        else if(wot == WarehouseObjectType.STONE)
            temp = stone;
        else if(wot == WarehouseObjectType.SERVANT)
            temp = servant;
        else if(wot == WarehouseObjectType.SHIELD)
            temp = shield;
        else if(wot == WarehouseObjectType.FAITH_MARKER)
            temp = faithMarker;
        return temp;
    }

    /**
     * The add method is used when you have two different SupplyContainer, sc1 and sc2, and you want to add all the
     * elements of the second container in the first one
     * @param sc is the SupplyContainer that you want to add to your SupplyContainer
     */
    public void add(SupplyContainer sc){
        this.coin = sc.coin + this.coin;
        this.stone = sc.stone + this.stone;
        this.servant = sc.servant + this.servant;
        this.shield = sc.shield + this.shield;
        this.faithMarker = sc.faithMarker + this.faithMarker;
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
        if(wot == WarehouseObjectType.COIN)
            coin++;
        else if(wot == WarehouseObjectType.STONE)
            stone++;
        else if(wot == WarehouseObjectType.SERVANT)
            servant++;
        else if(wot == WarehouseObjectType.SHIELD)
            shield++;
        else if(wot == WarehouseObjectType.FAITH_MARKER)
            faithMarker++;
    }

    /**
     * The addSupply method add one supply of the given type to the SupplyContainer
     * @param wot is one of the five types of resources in the game
     * @throws SupplyException if there are zero resources of the given type
     */
    public void removeSupply(WarehouseObjectType wot) throws SupplyException{
        if(wot == WarehouseObjectType.COIN)
        {
            if(coin <= 0)
                throw new SupplyException();
            else
                coin--;
        }
        else if(wot == WarehouseObjectType.STONE)
        {
            if(stone <= 0)
                throw new SupplyException();
            else
                stone--;
        }
        else if(wot == WarehouseObjectType.SERVANT)
        {
            if(servant <= 0)
                throw new SupplyException();
            else
                servant--;
        }
        else if(wot == WarehouseObjectType.SHIELD)
        {
            if(shield <= 0)
                throw new SupplyException();
            else
                shield--;
        }
        else if(wot == WarehouseObjectType.FAITH_MARKER)
        {
            if(faithMarker <= 0)
                throw new SupplyException();
            else
                faithMarker--;
        }
    }

    /**
     * The clearSupplies method removes all the resources that are in the SupplyContainer
     * @return an empty SupplyContainer
     */
    public SupplyContainer clearSupplies(){
        this.coin = 0;
        this.faithMarker = 0;
        this.servant = 0;
        this.shield = 0;
        this.stone = 0;
        return this;
    }
}
