package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;

import java.util.ArrayList;

public class ImmutableBoundedSupplyContainer implements HasStatus, AcceptsSupplies {

    private SupplyContainer sc;
    private final int max;
    private final WarehouseObjectType type;


    public ImmutableBoundedSupplyContainer(int max, WarehouseObjectType type) throws SupplyException{
        sc = new SupplyContainer();

        if(type == WarehouseObjectType.FAITH_MARKER){
            throw new SupplyException();
        }

        this.max = max;
        this.type = type;
    }


    public int getQuantity(){
        return sc.getQuantity(type);
    }



    public WarehouseObjectType getType(){
        return type;
    }



    public int getMax(){
        return max;
    }



    @Override
    public void addSupply(WarehouseObjectType wot) throws SupplyException {
        if(wot != type || getQuantity() == max){
            throw new SupplyException();
        }
        sc.addSupply(wot);
    }


    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException {
        if (wot != type || getQuantity() == 0){
            throw new SupplyException();
        }
        sc.removeSupply(wot);
    }


    @Override
    public SupplyContainer clearSupplies() throws NoSuchMethodException {
        return sc.clearSupplies();
    }



    /**
     * Tries to convert the marble to the supply it can contain, and, if possible, adds the marble to the container.
     * @param color Color of the marble to add
     * @param ls leaders space, utilized to check if a white marble is convertible
     * @throws MarbleException Cannot convert the marble to the desired supply type
     * @throws SupplyException The container is full
     */
    public void addMarble(MarbleColor color, LeadersSpace ls) throws MarbleException, SupplyException{
        WarehouseObjectType wot = MarbleContainer.colorToSupply(color);

        //if wot is null, then we must deal with a white ball
        if (wot == null){
            //try if first leader is able to convert the white ball to something
            try {
                if (ls.getLeaderAbility(0).transformWhiteMarble() == getType()){
                    wot = getType(); //does the leader convert the white ball to the required type?
                }
            } catch (NoSuchMethodException | LeaderException e){} //if the first leader cannot transform a white ball or isn't active yet, try the second leader

            //same as above
            try {
                if (ls.getLeaderAbility(1).transformWhiteMarble() == getType()){
                    wot = getType();
                }
            } catch (NoSuchMethodException | LeaderException e){}
        }

        if(wot != null && wot == type) {
            addSupply(wot);
        }
        else {
            throw new MarbleException();
        }
    }


    @Override
    public ArrayList<Integer> getStatus() {
        //TODO
    }
}
