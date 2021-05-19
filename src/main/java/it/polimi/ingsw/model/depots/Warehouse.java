package it.polimi.ingsw.model.depots;

import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.leaders.LeadersSpace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A Warehouse contains 3 depots, which can contain respectively 1, 2 and 3 resources of one type. Two depots cannot contain the same type.
 * User can add and remove resources from depots, can swap the order of 2 depots and can try to convert a marble to a resource and add it to one of the depots.
 */
public class Warehouse implements AcceptsSupplies, HasStatus {

    private ArrayList<SupplyContainer> depots = new ArrayList<>();



    /**
     * Creates a new warehouse.
     */
    public Warehouse(){
        SupplyContainer s1 = new SupplyContainer();
        SupplyContainer s2 = new SupplyContainer();
        SupplyContainer s3 = new SupplyContainer();

        s1.setAcceptCheck(maxOneTypeNotPresentIn(1, s2, s3).and(onlyFrom(DepotID.SourceType.STRONGBOX).negate()));
        s2.setAcceptCheck(maxOneTypeNotPresentIn(2, s1, s3).and(onlyFrom(DepotID.SourceType.STRONGBOX).negate()));
        s3.setAcceptCheck(maxOneTypeNotPresentIn(3, s1, s2).and(onlyFrom(DepotID.SourceType.STRONGBOX).negate()));

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
     * @param r2 second row (1<r2<3)
     * @throws SupplyException Swap isn't possible (one row has too many elements to fit the other row)
     */
    public void swapRows(int r1, int r2) throws SupplyException {
        if(r1==r2){return;}

        //find what depot has the maximum and minimum position, and store the remaining container in a third variable
        SupplyContainer rMax = depots.get(Math.max(r1, r2) -1);
        SupplyContainer rMin = depots.get(Math.min(r1, r2) -1);
        SupplyContainer theThird = depots.get((r1==1 && r2==2) ? 2 : ((r1==1 && r2==3) ? 1 : 0));

        //if the depot in min position (so with higher capacity) has less or equal elements than the elements in the depot in max position can contain, then the swap is possible
        //if(rMin.getQuantity() <= 4 - Math.max(r1, r2)){
        if(rMax.getQuantity() <= Math.min(r1, r2)){
            int max = Math.max(r1, r2);
            int min = Math.min(r1, r2);

            //swap the max accepted by the depots
            rMin.setAcceptCheck(maxOneTypeNotPresentIn(max == 2 ? 2 : 3, rMax, theThird).and(onlyFrom(DepotID.SourceType.STRONGBOX).negate()));
            rMax.setAcceptCheck(maxOneTypeNotPresentIn(min == 1 ? 1 : 2, rMin, theThird).and(onlyFrom(DepotID.SourceType.STRONGBOX).negate()));

            //swap the order of the depots in the array list
            Collections.swap(depots, r1-1, r2-1);
        }

        else{
            throw new SupplyException("Cannot swap the specified rows ("+r1+", "+r2+")");
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

    /**
     * Returns the depots that can receive the specified supply.
     * @param from Source of the supply
     * @param type Type of the supply
     * @return The depots that can receive the specified supply
     */
    public ArrayList<DepotID> getAllowedDepots(DepotID from, WarehouseObjectType type) {
        ArrayList<DepotID> res = new ArrayList<>();

        for (int i=0; i<3; ++i){
            if(depots.get(i).additionAllowed(type, from)){
                if(i == 0){
                    res.add(DepotID.WAREHOUSE1);
                }
                else if(i == 1){
                    res.add(DepotID.WAREHOUSE2);
                }
                else {
                    res.add(DepotID.WAREHOUSE3);
                }
            }
        }
        return res;
    }


    /**
     * Adds the supplies in the container passed as argument to the warehouse depots. In the process it is likely that the order of the depots will be modified.
     * @param sc SupplyContainer containing the resources to add to the warehouse
     */
    public void allocate(SupplyContainer sc){
        //empty the warehouse and re-add everything
        for(int i=0; i<3; ++i){
            sc.sum(depots.get(i).clearSupplies().first);
        }

        //create ArrayList of sc
        List<Pair<WarehouseObjectType, Integer>> listOfSc = new ArrayList<>();
        for(WarehouseObjectType wot : WarehouseObjectType.values()){
            if(wot != WarehouseObjectType.NO_TYPE)
                listOfSc.add(new Pair<>(wot, sc.getQuantity(wot)));
        }

        //keep only types that have at least 1 unit
        listOfSc = listOfSc.stream().filter(pair -> pair.second > 0).collect(Collectors.toList());

        //order the list
        listOfSc = listOfSc.stream().sorted((pair1, pair2) -> pair1.second<pair2.second ? 1 : (pair1.second==pair2.second ? 0 : -1)).collect(Collectors.toList());

        //if we have more than 3 items, we're fucked
        if(listOfSc.size() > 3){
            //TODO terminate
        }

        //add to the depots
        for(int i=0; i<listOfSc.size(); ++i){
            for (int j = 0; j < listOfSc.get(i).second; ++j) {
                try {
                    depots.get(2-i).addSupply(listOfSc.get(i).first);
                } catch (SupplyException se) {se.printStackTrace();}
            }
        }

    }



    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        for (int i = 0; i<depots.size(); ++i){
            status.addAll(depots.get(i).getStatus());
        }

        return status;
    }
}
