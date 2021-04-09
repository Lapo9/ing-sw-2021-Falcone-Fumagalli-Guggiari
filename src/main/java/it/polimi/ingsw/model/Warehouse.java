package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.leader_abilities.Depot;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A Warehouse contains 3 depots, which can contain respectively 1, 2 and 3 resources of one type. Two depots cannot contain the same type.
 * User can add and remove resources from depots, can swap the order of 2 depots and can try to convert a marble to a resource and add it to one of the depots.
 */
public class Warehouse implements AcceptsSupplies {

    private ArrayList<SupplyContainer> depots = new ArrayList<>();



    /**
     * Creates a new warehouse.
     * @param ls Warehouse needs to know leaders, because if leaders have ability of Depot type, then the Warehouse has the responsibility to manage their resources.
     */
    public Warehouse(){
        SupplyContainer s1 = new SupplyContainer();
        SupplyContainer s2 = new SupplyContainer();
        SupplyContainer s3 = new SupplyContainer();

        s1.setAcceptCheck(SupplyContainer.AcceptStrategy.maxOneTypeNotPresentIn(1, s2, s3).and(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT)));
        s2.setAcceptCheck(SupplyContainer.AcceptStrategy.maxOneTypeNotPresentIn(2, s1, s3).and(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT)));
        s3.setAcceptCheck(SupplyContainer.AcceptStrategy.maxOneTypeNotPresentIn(3, s1, s2).and(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT)));

        depots.add(s1);
        depots.add(s2);
        depots.add(s3);
    }


    /**
     * Returns how many resources of the specified types are present in the warehouse
     * @param wots types to count
     * @return how many resources of the specified types are present in the warehouse
     */
    public int getResourceCount(WarehouseObjectType... wots){
        int count = 0;
        for(SupplyContainer depot : depots){
            count += depot.getQuantity(wots);
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

        //find what depot has the maximum and minimum position, and store the remaining container in a third variable
        SupplyContainer rMax = depots.get(Math.max(r1, r2) -1);
        SupplyContainer rMin = depots.get(Math.min(r1, r2) -1);
        SupplyContainer theThird = depots.get((r1==1 && r2==2) ? 3 : ((r1==1 && r2==3) ? 2 : 1));

        //if the depot in min position (so with higher capacity) has less or equal elements than the elements in the depot in max position can contain, then the swap is possible
        if(rMin.getQuantity() <= 4 - Math.max(r1, r2)){
            //swap the max accepted by the depots
            rMin.setAcceptCheck(SupplyContainer.AcceptStrategy.maxOneTypeNotPresentIn(4-Math.max(r1, r2), rMax, theThird));
            rMax.setAcceptCheck(SupplyContainer.AcceptStrategy.maxOneTypeNotPresentIn(4-Math.min(r1, r2), rMin, theThird));

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
     * @throws SupplyException Specified depot is full
     * @throws MarbleException Marble cannot be converted to a compatible supply type
     * @throws NoSuchMethodException Leader cannot accept a marble
     */
    public void addMarble(DepotID row, MarbleColor color, LeadersSpace leadersSpace) throws SupplyException, MarbleException {
        depots.get(row.getNum()).addMarble(color, leadersSpace);
    }


    /**
     * Tries to add the specified resource to the specified row. Checks if the source of the resource is acceptable.
     * @param row row to add the resource to
     * @param wot type of resource
     * @param from source of the supply
     * @throws SupplyException Row cannot accept the specified resource. Probably the row is full or accepts a different type, or there is another row with the same type.
     * @throws NoSuchMethodException This object needs more information to store the supply
     */
    @Override
    public void addSupply(DepotID row, WarehouseObjectType wot, DepotID from) throws SupplyException{
        depots.get(row.getNum()).addSupply(wot, from);
    }

    /**
     * Removes the specified resource type from the warehouse.
     * @param wot resource type to remove
     * @throws SupplyException There isn't such resource
     */
    @Override
    public void removeSupply(DepotID row, WarehouseObjectType wot) throws SupplyException{
        depots.get(row.getNum()).removeSupply(wot);
    }

    @Override
    public boolean additionAllowed(DepotID row, WarehouseObjectType wot, DepotID from) {
        return depots.get(row.getNum()).additionAllowed(wot, from);
    }

    @Override
    public boolean removalAllowed(DepotID row, WarehouseObjectType wot) {
        return depots.get(row.getNum()).removalAllowed(wot);
    }

    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        SupplyContainer result = new SupplyContainer();

        for(int i = 0; i<3; ++i){
            result.sum(depots.get(i).clearSupplies().first);
        }

        return new Pair<>(result, new SupplyContainer());
    }

    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(DepotID slot) {
        return depots.get(slot.getNum()).clearSupplies();
    }

}
