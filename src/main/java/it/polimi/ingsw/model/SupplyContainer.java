package it.polimi.ingsw.model;

import it.polimi.ingsw.TriPredicate;
import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The SupplyContainer class can store resources
 */
public class SupplyContainer implements AcceptsSupplies, HasStatus{

    HashMap<WarehouseObjectType, Integer> supplies = new HashMap<>();
    TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> tryAdd = AcceptStrategy.any();
    TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> tryRemove = AcceptStrategy.any();


    /**
     * Class constructor
     */
    public SupplyContainer() {
        supplies.put(WarehouseObjectType.COIN, 0);
        supplies.put(WarehouseObjectType.SERVANT, 0);
        supplies.put(WarehouseObjectType.SHIELD, 0);
        supplies.put(WarehouseObjectType.STONE, 0);
        supplies.put(WarehouseObjectType.FAITH_MARKER, 0);
    }

    public SupplyContainer(TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck) {
        supplies.put(WarehouseObjectType.COIN, 0);
        supplies.put(WarehouseObjectType.SERVANT, 0);
        supplies.put(WarehouseObjectType.SHIELD, 0);
        supplies.put(WarehouseObjectType.STONE, 0);
        supplies.put(WarehouseObjectType.FAITH_MARKER, 0);

        this.tryAdd = acceptCheck;
    }


    public SupplyContainer(TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck, TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> removeCheck) {
        supplies.put(WarehouseObjectType.COIN, 0);
        supplies.put(WarehouseObjectType.SERVANT, 0);
        supplies.put(WarehouseObjectType.SHIELD, 0);
        supplies.put(WarehouseObjectType.STONE, 0);
        supplies.put(WarehouseObjectType.FAITH_MARKER, 0);

        this.tryAdd = acceptCheck;
        this.tryRemove = removeCheck;
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
        supplies.put(WarehouseObjectType.COIN, coin);
        supplies.put(WarehouseObjectType.SERVANT, servant);
        supplies.put(WarehouseObjectType.SHIELD, shield);
        supplies.put(WarehouseObjectType.STONE, stone);
        supplies.put(WarehouseObjectType.FAITH_MARKER, faithMarker);
    }

    /**
     * Create a supply container and fills it with the passed parameters.
     * @param coin coins
     * @param stone stones
     * @param servant servants
     * @param shield shields
     * @param faithMarker faith markers
     */
    public SupplyContainer(int coin, int stone, int servant, int shield, int faithMarker, TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck) {
        supplies.put(WarehouseObjectType.COIN, coin);
        supplies.put(WarehouseObjectType.SERVANT, servant);
        supplies.put(WarehouseObjectType.SHIELD, shield);
        supplies.put(WarehouseObjectType.STONE, stone);
        supplies.put(WarehouseObjectType.FAITH_MARKER, faithMarker);

        this.tryAdd = acceptCheck;
    }

    /**
     * Copy constructor.
     * @param sc object to copy
     */
    public SupplyContainer(SupplyContainer sc){
        supplies.put(WarehouseObjectType.COIN, sc.getQuantity(WarehouseObjectType.COIN));
        supplies.put(WarehouseObjectType.SERVANT, sc.getQuantity(WarehouseObjectType.SERVANT));
        supplies.put(WarehouseObjectType.SHIELD, sc.getQuantity(WarehouseObjectType.SHIELD));
        supplies.put(WarehouseObjectType.STONE, sc.getQuantity(WarehouseObjectType.STONE));
        supplies.put(WarehouseObjectType.FAITH_MARKER, sc.getQuantity(WarehouseObjectType.FAITH_MARKER));

        tryAdd = sc.tryAdd;
    }

    public SupplyContainer(SupplyContainer sc, TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck){
        supplies.put(WarehouseObjectType.COIN, sc.getQuantity(WarehouseObjectType.COIN));
        supplies.put(WarehouseObjectType.SERVANT, sc.getQuantity(WarehouseObjectType.SERVANT));
        supplies.put(WarehouseObjectType.SHIELD, sc.getQuantity(WarehouseObjectType.SHIELD));
        supplies.put(WarehouseObjectType.STONE, sc.getQuantity(WarehouseObjectType.STONE));
        supplies.put(WarehouseObjectType.FAITH_MARKER, sc.getQuantity(WarehouseObjectType.FAITH_MARKER));

        this.tryAdd = acceptCheck;
    }




    /**
     * The getQuantity method returns the quantity of the supply type passed as input that currently is in the SupplyContainer
     * @param wots resources to return the sum of the quantity
     * @return is the sum supply quantity of the given type present in the SupplyContainer
     */
    public int getQuantity(WarehouseObjectType... wots){
        int temp = 0;

        for(WarehouseObjectType wot : wots) {
            temp += supplies.get(wot);
        }
        return temp;
    }


    public int getQuantity(){
        int tot = 0;
        for (Map.Entry<WarehouseObjectType, Integer> type : supplies.entrySet()){
            tot += type.getValue();
        }
        return tot;
    }


    /**
     * Returns the type of the container if only one type of object is present.
     * @return The type of the object present in the container, if no objects are present NO_TYPE is returned
     * @throws SupplyException If more than one object type is present
     */
    public WarehouseObjectType getType() throws SupplyException {
        ArrayList<WarehouseObjectType> presentTypes = new ArrayList<>();
        supplies.forEach((type, qty) -> {if(qty != 0) {
                                            presentTypes.add(type);
                                        }});

        if (presentTypes.size() > 1){
            throw new SupplyException();
        }
        else if (presentTypes.size() == 1){
            return presentTypes.get(0);
        }
        else {
            return WarehouseObjectType.NO_TYPE;
        }
    }


    public void setAcceptCheck(TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck) {
        this.tryAdd = acceptCheck;
    }


    public void setRemoveCheck(TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> removeCheck) {
        this.tryRemove = removeCheck;
    }



    /**
     * The add method is used when you have two different SupplyContainer, sc1 and sc2, and you want to add all the
     * elements of the second container in the first one
     * @param sc is the SupplyContainer that you want to add to your SupplyContainer
     * @return this object, to enable chain calls.
     */
    public SupplyContainer sum(SupplyContainer sc){
        supplies.forEach((type, qty) -> {qty += sc.getQuantity(type);});
        return this;
    }


    /**
     * The confront method returns true if this.SupplyContainer is equal to sc
     * @param sc is a SupplyContainer
     * @return true if this.SupplyContainer is equal to sc
     */
    public boolean equals(SupplyContainer sc){
        boolean equals = true;
        for (Map.Entry<WarehouseObjectType, Integer> type : supplies.entrySet()){
            equals &= type.getValue() == sc.getQuantity(type.getKey());
        }
        return equals;
    }




    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        if (checkAccept(wot, from)){
            supplies.put(wot, supplies.get(wot)+1);
        }
        else {
            throw new SupplyException();
        }
    }

    @Override
    public void addSupply(WarehouseObjectType wot) throws SupplyException{
        if (checkAccept(wot)){
            supplies.put(wot, supplies.get(wot)+1);
        }
        else {
            throw new SupplyException();
        }
    }

    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException{
        if (checkRemove(wot)) {
            supplies.put(wot, supplies.get(wot)-1);
        }
        else {
            throw new SupplyException();
        }
    }

    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException{
        if (checkRemove(wot, to)) {
            supplies.put(wot, supplies.get(wot)-1);
        }
        else {
            throw new SupplyException();
        }
    }


    @Override
    public boolean checkAccept(WarehouseObjectType wot){
        return tryAdd.test(this, wot, DepotID.ANY);
    }

    @Override
    public boolean checkAccept(WarehouseObjectType wot, DepotID from){
        return tryAdd.test(this, wot, from);
    }


    @Override
    public boolean checkRemove(WarehouseObjectType wot){
        return supplies.get(wot) > 0 && tryRemove.test(this, wot, DepotID.ANY);
    }

    @Override
    public boolean checkRemove(WarehouseObjectType wot, DepotID to){
        return supplies.get(wot) > 0 && tryRemove.test(this, wot, to);
    }


    @Override
    public SupplyContainer clearSupplies(){
        SupplyContainer tmp = new SupplyContainer(this);
        supplies.forEach((type, qty) -> {supplies.put(type, 0);});
        return tmp;
    }


    /**
     * Tries to add the marble to the container.
     * @param color color of the marble to add
     * @param ls where to look for white marble transformations
     * @throws SupplyException Transformation succeeded but cannot add
     * @throws MarbleException Cannot transform marble
     */
    public void addMarble(MarbleColor color, LeadersSpace ls) throws SupplyException, MarbleException{

        WarehouseObjectType newType = MarbleContainer.colorToSupply(color);

        if (newType == null) {
            boolean cannotAdd = false;
            for(int i = 0; i<2; ++i) {
                try {
                    addSupply(ls.getLeaderAbility(i).transformWhiteMarble());
                    return; //if added, we are done
                }   catch (LeaderException | NoSuchMethodException e){/*couldn't transform, try next*/}
                    catch (SupplyException se){cannotAdd = true;}
            }
            if (cannotAdd){throw new SupplyException();}
            else {throw new MarbleException();}
        }
        else {
            addSupply(newType);
        }

    }


    //TODO
    @Override
    public ArrayList<Integer> getStatus(){
        return new ArrayList<>();
    }



    public static class AcceptStrategy {

        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> any(){
            return (supplyContainer, warehouseObjectType, depotID) -> true;
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> oneType(){
            return (container, type, depot) -> {
                boolean res = false;
                try {
                    res = container.getType() != type || container.getType() == WarehouseObjectType.NO_TYPE;
                } catch (SupplyException se) {/*TODO end program?*/}
                return res;
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> specificType(WarehouseObjectType type){
            return (container, wot, depot) -> {
                return wot == type;
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> maxOneType(int max){
            return (container, type, depot) -> {
                return oneType().test(container, type, depot) && container.getQuantity() < max;
            };
        }



        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> maxOneTypeNotPresentIn(int max, SupplyContainer... scs){
            return (container, type, depot) -> {
                boolean isPresent = false;
                try {
                    for (SupplyContainer sc : scs){
                        isPresent |= sc.getType()==type;
                    }
                } catch (SupplyException se){/*TODO terminate*/}
                return maxOneType(max).test(container, type, depot) && !isPresent;
            };
        }



        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> maxSpecificType(WarehouseObjectType type, int max){
            return (container, wot, depot) -> {
                return container.getQuantity() < max && specificType(type).test(container, wot, depot);
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> onlyFrom(DepotID.SourceType source){
            return (container, type, depot) -> {
                return depot.getSource() == source || depot.getSource() == DepotID.SourceType.ANY;
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> onlyFromMaxOneType(int max, DepotID.SourceType source){
            return (container, type, depot) -> {
                return onlyFrom(source).test(container, type, depot) && maxOneType(max).test(container, type, depot);
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> onlyFromMaxSpecificType(WarehouseObjectType type, int max, DepotID.SourceType source){
            return (container, wot, depot) -> {
                return onlyFrom(source).test(container, wot, depot) && maxSpecificType(type, max).test(container, wot, depot);
            };
        }
    }

}
