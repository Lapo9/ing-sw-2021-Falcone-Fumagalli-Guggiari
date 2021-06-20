package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.TriPredicate;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.leaders.LeadersSpace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The SupplyContainer class can store supplies given a policy that decides what supplies are acceptable/removable based on the current state of the container, the type of the object to be added and the source of the object to be added.
 */
public class SupplyContainer implements AcceptsSupplies, HasStatus{

    private HashMap<WarehouseObjectType, Integer> supplies = new HashMap<>();
    private TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> tryAdd = AcceptStrategy.any();
    private TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> tryRemove = AcceptStrategy.any();


    /**
     * Creates a new empty container that can accept and remove any supply.
     */
    public SupplyContainer() {
        supplies.put(WarehouseObjectType.COIN, 0);
        supplies.put(WarehouseObjectType.SERVANT, 0);
        supplies.put(WarehouseObjectType.SHIELD, 0);
        supplies.put(WarehouseObjectType.STONE, 0);
        supplies.put(WarehouseObjectType.FAITH_MARKER, 0);
    }

    /**
     * Creates a new empty container that can remove any supply and can accept supplies based on the given policy.
     * @param acceptCheck Predicate that decides what supplies can be accepted based on the current state of the container, the type of the object to be added and the source of the object to be added
     */
    public SupplyContainer(TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck) {
        supplies.put(WarehouseObjectType.COIN, 0);
        supplies.put(WarehouseObjectType.SERVANT, 0);
        supplies.put(WarehouseObjectType.SHIELD, 0);
        supplies.put(WarehouseObjectType.STONE, 0);
        supplies.put(WarehouseObjectType.FAITH_MARKER, 0);

        this.tryAdd = acceptCheck;
    }

    /**
     * Creates a new empty container that can accept/remove supplies based on the given policies.
     * @param acceptCheck Predicate that decides what supplies can be accepted based on the current state of the container, the type of the object to be added and the source of the object to be added
     * @param removeCheck Predicate that decides what supplies can be removed from the container based on the current state of the container, the type of the object to be added and the source of the object to be added
     */
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
     * Creates a new container with the specified initial content, that can accept and remove any supply.
     * @param coin Coins
     * @param stone Stones
     * @param servant Servants
     * @param shield Shields
     * @param faithMarker Faith markers
     */
    public SupplyContainer(int coin, int stone, int servant, int shield, int faithMarker) {
        supplies.put(WarehouseObjectType.COIN, coin);
        supplies.put(WarehouseObjectType.SERVANT, servant);
        supplies.put(WarehouseObjectType.SHIELD, shield);
        supplies.put(WarehouseObjectType.STONE, stone);
        supplies.put(WarehouseObjectType.FAITH_MARKER, faithMarker);
    }

    /**
     * Creates a new empty container that can remove any supply and can accept supplies based on the given policy.
     * @param coin Coins
     * @param stone Stones
     * @param servant Servants
     * @param shield Shields
     * @param faithMarker Faith markers
     * @param acceptCheck Predicate that decides what supplies can be accepted based on the current state of the container, the type of the object to be added and the source of the object to be added
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
     * Creates a new empty container that can accept/remove supplies based on the given policies
     * @param coin Coins
     * @param stone Stones
     * @param servant Servants
     * @param shield Shields
     * @param faithMarker Faith markers
     * @param acceptCheck Predicate that decides what supplies can be accepted based on the current state of the container, the type of the object to be added and the source of the object to be added
     * @param removeCheck Predicate that decides what supplies can be removed from the container based on the current state of the container, the type of the object to be added and the source of the object to be added
     */
    public SupplyContainer(int coin, int stone, int servant, int shield, int faithMarker, TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck, TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> removeCheck) {
        supplies.put(WarehouseObjectType.COIN, coin);
        supplies.put(WarehouseObjectType.SERVANT, servant);
        supplies.put(WarehouseObjectType.SHIELD, shield);
        supplies.put(WarehouseObjectType.STONE, stone);
        supplies.put(WarehouseObjectType.FAITH_MARKER, faithMarker);

        this.tryAdd = acceptCheck;
        this.tryRemove = removeCheck;
    }

    /**
     * Copy constructor.
     * @param sc Container to copy
     */
    public SupplyContainer(SupplyContainer sc){
        supplies.put(WarehouseObjectType.COIN, sc.getQuantity(WarehouseObjectType.COIN));
        supplies.put(WarehouseObjectType.SERVANT, sc.getQuantity(WarehouseObjectType.SERVANT));
        supplies.put(WarehouseObjectType.SHIELD, sc.getQuantity(WarehouseObjectType.SHIELD));
        supplies.put(WarehouseObjectType.STONE, sc.getQuantity(WarehouseObjectType.STONE));
        supplies.put(WarehouseObjectType.FAITH_MARKER, sc.getQuantity(WarehouseObjectType.FAITH_MARKER));

        tryAdd = sc.tryAdd;
        tryRemove = sc.tryRemove;
    }

    /**
     * Copy constructor, but the accept policy is changed.
     * @param sc Container to copy
     * @param acceptCheck Predicate that decides what supplies can be accepted based on the current state of the container, the type of the object to be added and the source of the object to be added
     */
    public SupplyContainer(SupplyContainer sc, TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck){
        supplies.put(WarehouseObjectType.COIN, sc.getQuantity(WarehouseObjectType.COIN));
        supplies.put(WarehouseObjectType.SERVANT, sc.getQuantity(WarehouseObjectType.SERVANT));
        supplies.put(WarehouseObjectType.SHIELD, sc.getQuantity(WarehouseObjectType.SHIELD));
        supplies.put(WarehouseObjectType.STONE, sc.getQuantity(WarehouseObjectType.STONE));
        supplies.put(WarehouseObjectType.FAITH_MARKER, sc.getQuantity(WarehouseObjectType.FAITH_MARKER));

        this.tryAdd = acceptCheck;
        tryRemove = sc.tryRemove;
    }




    /**
     * Returns the amount of the supply types passed as arguments currently stored in the container.
     * @param wots Supplies to return the sum of the quantity
     * @return The amount of the supply types passed as arguments currently stored in the container
     */
    public int getQuantity(WarehouseObjectType... wots){
        int temp = 0;

        for(WarehouseObjectType wot : wots) {
            temp += supplies.get(wot);
        }
        return temp;
    }

    /**
     * Returns the amount of all of the supplies currently stored in the container.
     * @return The amount of all of the supplies currently stored in the container
     */
    public int getQuantity(){
        int tot = 0;
        for (Map.Entry<WarehouseObjectType, Integer> type : supplies.entrySet()){
            tot += type.getValue();
        }
        return tot;
    }


    /**
     * Returns the type of the container if only one type of supply is present.
     * @return The type of the object present in the container, if no objects are present NO_TYPE is returned
     * @throws SupplyException If more than one object type is present
     */
    public WarehouseObjectType getType() throws SupplyException {
        ArrayList<WarehouseObjectType> presentTypes = new ArrayList<>();
        supplies.forEach((type, qty) -> {if(qty != 0) {
                                            presentTypes.add(type);
                                        }});

        if (presentTypes.size() > 1){
            throw new SupplyException("This SupplyContainer contains more than one single type");
        }
        else if (presentTypes.size() == 1){
            return presentTypes.get(0);
        }
        else {
            return WarehouseObjectType.NO_TYPE;
        }
    }


    /**
     * Modifies the accept policy.
     * @param acceptCheck New accept policy
     */
    public void setAcceptCheck(TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> acceptCheck) {
        this.tryAdd = acceptCheck;
    }

    /**
     * Modifies the remove policy.
     * @param removeCheck New remove policy
     */
    public void setRemoveCheck(TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> removeCheck) {
        this.tryRemove = removeCheck;
    }


    /**
     * Adds to this container the container passed ad argument.
     * @param sc The container that you want to add to this container
     * @return This container, to enable chain calls.
     */
    public SupplyContainer sum(SupplyContainer sc){
        supplies.forEach((type, qty) -> supplies.put(type, qty += sc.getQuantity(type)));
        return this;
    }

    /**
     * Compare the content of this container with the content of the container passed as argument.
     * @param sc Container to confront to this
     * @return True if the content of this container is the same as the content of the container passed as argument
     */
    public boolean equals(SupplyContainer sc){
        boolean equals = true;
        for (Map.Entry<WarehouseObjectType, Integer> type : supplies.entrySet()){
            equals &= type.getValue() == sc.getQuantity(type.getKey());
        }
        return equals;
    }



    /**
     * Adds the supply to the storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        if (additionAllowed(wot, from)){
            supplies.put(wot, supplies.get(wot)+1);
        }
        else {
            throw new SupplyException("Cannot add "+wot.toString()+" to the specified depot");
        }
    }

    /**
     * Adds the supply to the storage. Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(WarehouseObjectType wot) throws SupplyException{
        if (additionAllowed(wot)){
            supplies.put(wot, supplies.get(wot)+1);
        }
        else {
            throw new SupplyException("Cannot add "+wot.toString()+" to the specified depot");
        }
    }

    /**
     * Removes the supply from the storage. Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException{
        if (removalAllowed(wot)) {
            supplies.put(wot, supplies.get(wot)-1);
        }
        else {
            throw new SupplyException("Cannot remove "+wot.toString()+" from the specified depot");
        }
    }

    /**
     * Removes the supply from the storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException{
        if (removalAllowed(wot, to)) {
            supplies.put(wot, supplies.get(wot)-1);
        }
        else {
            throw new SupplyException("Cannot remove "+wot.toString()+" from the specified depot");
        }
    }

    /**
     * Checks if the addition of the supply to the storage is allowed.
     * @param wot One of the five types of resources in the game
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot){
        return tryAdd.test(this, wot, DepotID.ANY);
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from){
        return tryAdd.test(this, wot, from);
    }

    /**
     * Checks if the removal of the supply from the storage is allowed.
     * @param wot One of the five types of resources in the game
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot){
        return supplies.get(wot) > 0 && tryRemove.test(this, wot, DepotID.ANY);
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to){
        return supplies.get(wot) > 0 && tryRemove.test(this, wot, to);
    }

    /**
     * Removes all of the supplies.
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(){
        SupplyContainer tmp = new SupplyContainer(this);
        supplies.forEach((type, qty) -> {supplies.put(type, 0);});
        return new Pair<>(tmp, new SupplyContainer());
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
            if (cannotAdd){throw new SupplyException("Cannot add this color ("+color.toString()+") of marble to the specified SupplyContainer");}
            else {throw new MarbleException("Cannot add this color ("+color.toString()+") of marble to the specified SupplyContainer");}
        }
        else {
            addSupply(newType);
        }

    }


    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of 5 Integer
     */
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        status.add(supplies.get(WarehouseObjectType.COIN));
        status.add(supplies.get(WarehouseObjectType.SERVANT));
        status.add(supplies.get(WarehouseObjectType.SHIELD));
        status.add(supplies.get(WarehouseObjectType.STONE));
        status.add(supplies.get(WarehouseObjectType.FAITH_MARKER));

        return status;
    }


    /**
     * A list of static functions that return common TriPredicate that can be used as accept/remove policy for a SupplyContainer.
     */
    public static class AcceptStrategy {

        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> any(){
            return (supplyContainer, warehouseObjectType, depotID) -> true;
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> none(){
            return (supplyContainer, warehouseObjectType, depotID) -> false;
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> max(int max){
            return (supplyContainer, warehouseObjectType, depotID) -> {
                return supplyContainer.getQuantity() < max;
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> min(int min){
            return (supplyContainer, warehouseObjectType, depotID) -> {
                return supplyContainer.getQuantity() > min;
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> oneType(){
            return (container, type, depot) -> {
                boolean res = false;
                try {
                    res = container.getType() == type || container.getType() == WarehouseObjectType.NO_TYPE;
                } catch (SupplyException se) {System.exit(1); /*TODO end program?*/}
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
                } catch (SupplyException se){System.exit(1); /*TODO terminate*/}
                return maxOneType(max).test(container, type, depot) && !isPresent;
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> maxSpecificType(WarehouseObjectType type, int max){
            return (container, wot, depot) -> {
                return container.getQuantity() < max && specificType(type).test(container, wot, depot);
            };
        }


        public static TriPredicate<SupplyContainer, WarehouseObjectType, DepotID> onlyFrom(DepotID.SourceType source, LeadersSpace leadersSpace){
            return (container, type, depot) -> {
                return depot.getSource(leadersSpace) == source;
            };
        }


}
