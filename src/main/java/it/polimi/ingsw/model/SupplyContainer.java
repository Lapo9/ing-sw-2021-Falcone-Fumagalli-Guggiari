package it.polimi.ingsw.model;

public class SupplyContainer implements AcceptsSupplies, HasStatus{
    private int coin;
    private int stone;
    private int servant;
    private int shield;
    private int faith_marker;

    /**
     * class constructor
     */
    public SupplyContainer() {
        coin=0;
        stone=0;
        servant=0;
        shield=0;
        faith_marker=0;
    }

    /**
     * The getQuantity method returns the quantity of the supply type passed as input that currently is in the SupplyContainer
     * @param wot is one of the five types of resources in the game
     * @return is the supply quantity of the given type present in the SupplyContainer
     */
    public int getQuantity(WarehouseObjectType wot){
        switch(wot){
            case(COIN):
                return coin;
                break;
            case(STONE):
                return stone;
                break;
            case(SERVANT):
                return servant;
                break;
            case(SHIELD):
                return shield;
                break;
            default:
                return faith_marker;
        }
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
        this.faith_marker = sc.faith_marker + this.faith_marker;
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
            case(COIN):
                coin++;
            case(STONE):
                stone++;
            case(SERVANT):
                servant++;
            case(SHIELD):
                shield++;
            case(FAITH_MARKER):
                faith_marker++;
        }
    }

    /**
     * The addSupply method add one supply of the given type to the SupplyContainer
     * @param wot is one of the five types of resources in the game
     * @throws SupplyException if there are zero resources of the given type
     */
    public void removeSupply(WarehouseObjectType wot) throws SupplyException{
        switch(wot){
            case(COIN):
            {
                if(coin <= 0)
                    throw new SupplyException();
                else
                    coin--;
            }
            case(STONE):
            {
                if(stone <= 0)
                    throw new SupplyException();
                else
                    stone--;
            }
            case(SERVANT):
            {
                if(servant <= 0)
                    throw new SupplyException();
                else
                    servant--;
            }
            case(SHIELD):
            {
                if(shield <= 0)
                    throw new SupplyException();
                else
                    shield--;
            }
            case(FAITH_MARKER):
            {
                if(faith_marker <= 0)
                    throw new SupplyException();
                else
                    faith_marker--;
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
