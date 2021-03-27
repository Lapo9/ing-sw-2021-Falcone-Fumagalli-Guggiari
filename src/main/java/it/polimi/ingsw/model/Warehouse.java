package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A Warehouse contains 3 depots, which can contain respectively 1, 2 and 3 resources of one type. Two depots cannot contain the same type.
 * User can add and remove resources from depots, can swap the order of 2 depots and can try to convert a marble to a resource and add it to one of the depots.
 */
public class Warehouse {


    /**
     * Returns how many resources of the specified types are present in the warehouse
     * @param wots types to count
     * @return how many resources of the specified types are present in the warehouse
     */
    public int getResourceCount(WarehouseObjectType... wots){
        int count = 0;

        for(BoundedSupplyContainer depot : depots){
            for(WarehouseObjectType wot : wots) {
                if(depot.getType() == wot) {
                    count += depot.getQuantity();
                }
            }
        }

        return count;
    }


    /**
     * Swaps the specified rows, if possible.
     * @param r1 first row (1<r1<3)
     * @param r2 second row (1<r1<3)
     * @throws SupplyException Swap isn't possible (one row has too many elements to fit the other row)
     */
    public void swapRows(int r1, int r2) throws SupplyException {
        if(r1==r2){return;}

        //find what depot has the maximum and minimum position
        BoundedSupplyContainer rMax = depots.get(r1>r2 ? r1-1 : r2-1);
        BoundedSupplyContainer rMin = depots.get(r1>r2 ? r2-1 : r1-1);

        //if the depot in min position has less or equal elements than the elements the depot in max position can contain, then the swap is possible
        if(rMin.getQuantity() <= rMax.getMax()){
            //swap position of the 2 depots
            int tmpPos = rMin.getPosition();
            rMin.setPosition(rMax.getPosition());
            rMax.setPosition(tmpPos);

            //swap the order of the depots in the array list
            Collections.swap(depots, r1-1, r2-1);
        }

        else{
            throw new SupplyException();
        }

    }


    /**
     * Tries to convert the marble to the specified row supply type, and, if possible, adds it to the row.
     * @param row row to add the marble to
     * @param color color of the marble to add
     * @param ls leaders (utilized to know if a conversion is possible)
     * @throws SupplyException Specified depot is full
     * @throws MarbleException Marble cannot be converted to a compatible supply type
     */
    public void addMarble(int row, MarbleColor color, LeadersSpace ls) throws SupplyException, MarbleException {
        depots.get(row-1).addMarble(color, ls);
    }


    /**
     * Tries to add the specified resource to the specified row.
     * @param row row to add the resource to
     * @param wot type of resource
     * @throws SupplyException Row cannot accept the specified resource. Probably the row is full or accepts a different type, or there is another row with the same type.
     */
    public void addToRow(int row, WarehouseObjectType wot) throws SupplyException {
        for(int i = 0; i<3; ++i){
            if(i != row-1 && depots.get(i).getType() == wot){
                throw new SupplyException(); //there is another row that contains the specified type of resource
            }
        }

        depots.get(row-1).addSupply(wot);
    }


    /**
     * Removes the specified resource type from the warehouse. It is not necessary to specify the row, since there can be only one row with the specified resource type.
     * @param wot resource type to remove
     * @throws SupplyException There isn't such resource
     */
    public void removeObject(WarehouseObjectType wot) throws SupplyException {
        //find which row contains the specified type of resource
        BoundedSupplyContainer tmp = null;
        for(BoundedSupplyContainer depot : depots){
            if(depot.getType() == wot){
                tmp = depot;
            }
        }

        //there isn't a row that has the specified supply type
        if(tmp == null){
            throw new SupplyException();
        }

        //remove
        tmp.removeSupply(wot);
    }




    private ArrayList<BoundedSupplyContainer> depots;

}
