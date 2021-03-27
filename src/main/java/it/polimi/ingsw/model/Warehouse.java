package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A Warehouse contains 3 depots, which can contain respectively 1, 2 and 3 resources of one type. Two depots cannot contain the same type.
 * User can add and remove resources from depots, can swap the order of 2 depots and can try to convert a marble to a resource and add it to one of the depots.
 */
public class Warehouse implements AcceptsSupplies{

    /**
     * Creates a new warehouse.
     * @param ls Warehouse needs to know leaders, because if leaders have ability of Depot type, then the Warehouse has the responsibility to manage their resources.
     */
    public Warehouse(LeadersSpace ls){
        leadersSpace = ls;
    }


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
    @Override
    public void addSupply(DepotID row, WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException {
        //check if the user wants to add a resource to one of the leaders or to one of the warehouse spaces
        if(row.getType() == DepotID.DepotType.LEADER){
            leadersSpace.getLeaderAbility(row.getNum()).addSupply(wot);
        }
        else {
            for (int i = 0; i < 3; ++i) {
                if (i != row.getNum() - 1 && depots.get(i).getType() == wot) {
                    throw new SupplyException(); //there is another row that contains the specified type of resource
                }
            }

            depots.get(row.getNum() - 1).addSupply(wot);
        }
    }


    /**
     * Removes the specified resource type from the warehouse.
     * @param wot resource type to remove
     * @throws SupplyException There isn't such resource
     */
    @Override
    public void removeSupply(DepotID row, WarehouseObjectType wot) throws SupplyException, UnsupportedOperationException {
        //check if the user wants to remove a resource from one of the leaders or from one of the warehouse spaces
        if(row.getType() == DepotID.DepotType.LEADER){
            leadersSpace.getLeaderAbility(row.getNum()).addSupply(wot);
        }
        else {
            depots.get(row.getNum() - 1).removeSupply(wot);
        }
    }


    @Override
    public SupplyContainer clearSupplies() {
        SupplyContainer result = new SupplyContainer();
        //clear warehouse spaces
        for(int i = 0; i<3; ++i){
            result.sum(depots.get(i).clearSupplies());
        }

        //clear leader spaces
        for(int i=0; i<2; ++i){
            //in this case I use instanceof because to me it makes more sense to clear only the leaders that are "true" depots, and not production leaders
            if(leadersSpace.getLeaderAbility(i) instanceof Depot){
                result.sum(leadersSpace.getLeaderAbility(i).clearSupplies());
            }
        }

        return result;
    }

    private ArrayList<BoundedSupplyContainer> depots;
    private LeadersSpace leadersSpace;

}
