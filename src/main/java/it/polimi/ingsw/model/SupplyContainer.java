package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;

/**
 * The SupplyContainer class can store resources
 */
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
     * Create a supply container and fills it with the passed parameters.
     * @param coin coins
     * @param stone stones
     * @param servant servants
     * @param shield shields
     * @param faithMarker faith markers
     */
    public SupplyContainer(int coin, int stone, int servant, int shield, int faithMarker) {
        this.coin = coin;
        this.stone = stone;
        this.servant = servant;
        this.shield = shield;
        this.faithMarker = faithMarker;
    }

    /**
     * Copy constructor.
     * @param sc object to copy
     */
    public SupplyContainer(SupplyContainer sc){
        this.coin = sc.coin;
        this.servant = sc.servant;
        this.stone = sc.stone;
        this.shield = sc.shield;
        this.faithMarker = sc.faithMarker;
    }

    /**
     * The getQuantity method returns the quantity of the supply type passed as input that currently is in the SupplyContainer
     * @param wots resources to return the sum of the quantity
     * @return is the sum supply quantity of the given type present in the SupplyContainer
     */
    public int getQuantity(WarehouseObjectType... wots){
        int temp = 0;

        for(WarehouseObjectType wot : wots) {
            if (wot == WarehouseObjectType.COIN)
                temp += coin;
            else if (wot == WarehouseObjectType.STONE)
                temp += stone;
            else if (wot == WarehouseObjectType.SERVANT)
                temp += servant;
            else if (wot == WarehouseObjectType.SHIELD)
                temp += shield;
            else if (wot == WarehouseObjectType.FAITH_MARKER)
                temp += faithMarker;
        }
        return temp;
    }

    /**
     * The add method is used when you have two different SupplyContainer, sc1 and sc2, and you want to add all the
     * elements of the second container in the first one
     * @param sc is the SupplyContainer that you want to add to your SupplyContainer
     * @return this object, to enable chain calls.
     */
    public SupplyContainer sum(SupplyContainer sc){
        this.coin = sc.coin + this.coin;
        this.stone = sc.stone + this.stone;
        this.servant = sc.servant + this.servant;
        this.shield = sc.shield + this.shield;
        this.faithMarker = sc.faithMarker + this.faithMarker;
        return this;
    }

    /**
     * The confront method returns true if this.SupplyContainer is equal to sc
     * @param sc is a SupplyContainer
     * @return true if this.SupplyContainer is equal to sc
     */
    public boolean confront(SupplyContainer sc){
        return this.coin == sc.coin && this.faithMarker == sc.faithMarker && this.servant == sc.servant &&
                this.shield == sc.shield && this.stone == sc.stone;
    }

    @Override
    public void addSupply(WarehouseObjectType wot){
        if(wot == null)
            return;
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

    @Override
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

    @Override
    public SupplyContainer clearSupplies(){
        SupplyContainer tmp = new SupplyContainer(this);
        this.coin = 0;
        this.faithMarker = 0;
        this.servant = 0;
        this.shield = 0;
        this.stone = 0;
        return tmp;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus(){
        return new ArrayList<>();
    }
}
