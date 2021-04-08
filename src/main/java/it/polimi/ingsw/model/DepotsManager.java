package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;

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
    public boolean checkAccept(DepotID slot, WarehouseObjectType wot, DepotID from) {
        if (slot.getType() == DepotID.DepotType.WAREHOUSE){
            return warehouse.checkAccept(slot, wot, from);
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_DEPOT){
            try {
                return leadersSpace.getLeaderAbility(slot.getNum()).checkAccept(wot, from);
            } catch (LeaderException | NoSuchMethodException e){return false;}
        }

        else {
            return false;
        }
    }


    @Override
    public boolean checkRemove(DepotID from, WarehouseObjectType wot, DepotID to) throws NoSuchMethodException {
        if (from.getType() == DepotID.DepotType.WAREHOUSE){
            return warehouse.checkRemove(from, wot, to);
        }

        else if (from.getType() == DepotID.DepotType.LEADER_DEPOT){
            try {
                return leadersSpace.getLeaderAbility(from.getNum()).checkRemove(wot, to);
            } catch (LeaderException | NoSuchMethodException e){return false;}
        }

        else {
            return false;
        }
    }


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



    public void addMarble(DepotID slot, MarbleColor color) throws MarbleException, SupplyException, LeaderException, NoSuchMethodException {
        if (slot.getType() == DepotID.DepotType.WAREHOUSE){
            warehouse.addMarble(slot, color, leadersSpace);
        }

        else if(slot.getType() == DepotID.DepotType.LEADER_DEPOT){
            leadersSpace.getLeaderAbility(slot.getNum()).addMarble(color, leadersSpace);
        }

        else {
            throw new MarbleException();
        }
    }
}
