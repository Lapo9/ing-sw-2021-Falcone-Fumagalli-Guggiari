package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.leader_abilities.Depot;
import it.polimi.ingsw.model.leader_abilities.Producer;

import java.util.ArrayList;

/**
 * Manages all the depot that can contain resources from the market and the productions, namely warehouse and leader depots
 */
public class DepotsManager implements AcceptsSupplies {

    private Warehouse warehouse;
    private LeadersSpace leadersSpace;


    public DepotsManager(Warehouse warehouse, LeadersSpace leadersSpace){
        this.warehouse = warehouse;
        this.leadersSpace = leadersSpace;
    }


    @Override
    public void addSupply(DepotID slot, WarehouseObjectType wot, DepotID from) throws SupplyException {
        if (slot.getType() == DepotID.DepotType.WAREHOUSE){
            warehouse.addSupply(slot, wot, from);
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_DEPOT){
            try {
                leadersSpace.getLeaderAbility(slot.getNum()).addSupply(wot, from);
            } catch (LeaderException | NoSuchMethodException e){/*TODO terminate program*/}
        }

        else {
            /*TODO terminate the program*/
        }
    }

    @Override
    public void removeSupply(DepotID from, WarehouseObjectType wot) throws SupplyException {
        if (from.getType() == DepotID.DepotType.WAREHOUSE){
            warehouse.removeSupply(from, wot);
        }

        else if (from.getType() == DepotID.DepotType.LEADER_DEPOT){
            try {
                leadersSpace.getLeaderAbility(from.getNum()).addSupply(wot);
            } catch (LeaderException | NoSuchMethodException e){/*TODO terminate program*/}
        }

        else {
            /*TODO terminate the program*/
        }
    }

    @Override
    public boolean additionAllowed(DepotID slot, WarehouseObjectType wot, DepotID from) {
        if (slot.getType() == DepotID.DepotType.WAREHOUSE){
            return warehouse.additionAllowed(slot, wot, from);
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_DEPOT){
            try {
                return leadersSpace.getLeaderAbility(slot.getNum()).additionAllowed(wot, from);
            } catch (LeaderException | NoSuchMethodException e){return false;}
        }

        else {
            return false;
        }
    }

    @Override
    public boolean removalAllowed(DepotID from, WarehouseObjectType wot, DepotID to) {
        if (from.getType() == DepotID.DepotType.WAREHOUSE){
            return warehouse.removalAllowed(from, wot);
        }

        else if (from.getType() == DepotID.DepotType.LEADER_DEPOT){
            try {
                return leadersSpace.getLeaderAbility(from.getNum()).removalAllowed(wot, to);
            } catch (LeaderException | NoSuchMethodException e){return false;}
        }

        else {
            return false;
        }
    }

    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        SupplyContainer res = new SupplyContainer();

        res.sum(warehouse.clearSupplies().first);

        //clear only depot leaders
        for (int i = 0; i<2; ++i){
            try {
                if (leadersSpace.getLeaderAbility(0) instanceof Depot) {
                    res.sum(leadersSpace.getLeaderAbility(i).clearSupplies().first);
                }
            } catch (LeaderException | NoSuchMethodException e){}
        }

        return new Pair<>(res, new SupplyContainer());
    }

    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(DepotID slot) {
        SupplyContainer result = new SupplyContainer();

        if (slot.getType() == DepotID.DepotType.WAREHOUSE){
            result = warehouse.clearSupplies(slot).first;
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_DEPOT){
            try {
                if (leadersSpace.getLeaderAbility(slot.getNum()) instanceof Depot) {
                    result = leadersSpace.getLeaderAbility(slot.getNum()).clearSupplies().first;
                }
                else {
                    //TODO terminate the program
                }
            } catch (LeaderException | NoSuchMethodException e){/*TODO terminate program*/}
        }

        else {
            /*TODO terminate the program*/
        }

        return new Pair<>(result, new SupplyContainer());
    }


    /**
     * Returns the number of the given supplies contained in warehouse and in leader depots
     * @param wots Type of resources
     * @return Number of the given resources
     */
    public int getResourceCount(WarehouseObjectType... wots){
        int count = 0;

        count += warehouse.getResourceCount(wots);

        for (int i=0; i<2; ++i){
            Pair<WarehouseObjectType, Integer> leaderDepot;
            for(WarehouseObjectType wot : wots) {
                try{
                    leaderDepot = leadersSpace.getLeaderAbility(i).getDepotInfo();
                } catch(NoSuchMethodException | LeaderException e) {break;}
                if(leaderDepot.first == wot) {
                    count += leaderDepot.second;
                }
            }
        }

        return count;
    }


    /**
     * Returns the depots that can receive the specified supply.
     * @param from Source of the supply
     * @param type Type of the supply
     * @return The depots that can receive the specified supply
     */
    public ArrayList<DepotID> getAllowedDepots(DepotID from, WarehouseObjectType type) {
        ArrayList<DepotID> res = new ArrayList<>();
        res.addAll(warehouse.getAllowedDepots(from, type));

        try {
            if(leadersSpace.getLeaderAbility(0).additionAllowed(type, from) && leadersSpace.getLeaderAbility(0) instanceof Depot) {
                res.add(DepotID.LEADER1_DEPOT);
            }
            else if(leadersSpace.getLeaderAbility(0).additionAllowed(type, from) && leadersSpace.getLeaderAbility(0) instanceof Producer) {
                res.add(DepotID.LEADER1_PRODUCTION);
            }
        } catch (Exception e){}

        try {
            if(leadersSpace.getLeaderAbility(1).additionAllowed(type, from) && leadersSpace.getLeaderAbility(1) instanceof Depot) {
                res.add(DepotID.LEADER2_DEPOT);
            }
            else if(leadersSpace.getLeaderAbility(1).additionAllowed(type, from) && leadersSpace.getLeaderAbility(1) instanceof Producer) {
                res.add(DepotID.LEADER2_PRODUCTION);
            }
        } catch (Exception e){}

        return res;
    }


    /**
     * Adds the supplies in the container passed as argument to the warehouse and leaders depots. In the process it is likely that the order of the depots in the warehouse will be modified.
     * @param sc SupplyContainer containing the resources to add to the depots
     */
    public void allocate(SupplyContainer sc){
        //first, place as supplies as you can in the leaders depots
        for (WarehouseObjectType wot : WarehouseObjectType.values()){
            if(wot != WarehouseObjectType.NO_TYPE) {
                int qty = sc.getQuantity(wot);
                //for each leader, try to add the current type of resource (as long as there is a supply of the current type)
                //the cycle is repeated as most 4 times because if the 2 leaders both have the same contained supply, then at most you can add 4 supplies of that type to the leaders depots
                for (int i = 0; i < 4 && qty > 0; ++i) {
                    try {
                        if (leadersSpace.getLeaderAbility(i / 2) instanceof Depot && leadersSpace.getLeaderAbility(i / 2).additionAllowed(wot, DepotID.WAREHOUSE1)) {
                            sc.removeSupply(wot);
                            leadersSpace.getLeaderAbility(i / 2).addSupply(wot, DepotID.WAREHOUSE1);
                            qty--;
                        }
                    } catch (LeaderException le) {
                    } catch (SupplyException | NoSuchMethodException se) {/*TODO terminate*/}
                }
            }
        }


        //second, reorganize the warehouse with the remaining resources
        warehouse.allocate(sc);
    }


    /**
     * Tries to add the marble to the given depot.
     * @param slot ID of the depot
     * @param color Style of the marble
     * @throws MarbleException Wrong depot ID
     * @throws SupplyException Cannot add the marble
     * @throws LeaderException No active leader yet
     * @throws NoSuchMethodException No leader with the market ability
     */
    public void addMarble(DepotID slot, MarbleColor color) throws MarbleException, SupplyException, LeaderException, NoSuchMethodException {
        if (slot.getType() == DepotID.DepotType.WAREHOUSE){
            warehouse.addMarble(slot, color, leadersSpace);
        }

        else if(slot.getType() == DepotID.DepotType.LEADER_DEPOT){
            leadersSpace.getLeaderAbility(slot.getNum()).addMarble(color, leadersSpace);
        }

        else {
            throw new MarbleException("Cannot add this marble ("+color+") to the specified depot (" +slot.toString() + ")");
        }
    }
}
