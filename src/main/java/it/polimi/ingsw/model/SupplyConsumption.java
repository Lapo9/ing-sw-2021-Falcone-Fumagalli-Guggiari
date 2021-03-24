package it.polimi.ingsw.model;

import it.polimi.ingsw.model.SupplyContainer;
import java.util.ArrayList;

/**
 * SupplyConsumption is a special type of SupplyContainer which can store the source of the resources that it contains
 */
public class SupplyConsumption implements HasStatus{
    private SupplyContainer sc;
    private int containerID;

    /**
     * Class constructor
     * @param source is the source of the resources contained in the SupplyContainer
     */
    public SupplyConsumption(int source) {
        sc = new SupplyContainer();
        containerID = source;
    }

    /**
     * The getQuantity method returns the quantity of the supply types passed as input that currently are in the SupplyContainer
     * @param wots are the resources that you want to count
     * @return is the sum of the supply types that currently are in the SupplyContainer
     */
    public int getQuantity(WarehouseObjectType ... wots){
        int temp = 0;
        for(WarehouseObjectType wot : wots){
            temp += sc.getQuantity(wot);
        }
        return temp;
    }

    /**
     * The getSource method is used to find the source of the SupplyContainer
     * @return is the source of the SupplyContainer
     */
    public int getSource(){
        return containerID;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus(){

    }
}
