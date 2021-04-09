package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.LeaderException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.leader_abilities.Depot;
import it.polimi.ingsw.model.leader_abilities.Producer;

import java.util.HashMap;

/**
 * Manages all of those depots that can contain resources both from the strongbox and the depots, namely production depots, leader production depots and paycheck
 */
public class ProductionManager implements AcceptsSupplies{

    HashMap<DepotID.SourceType, SupplyContainer> containers = new HashMap<>(); //place where we store a copy of each resource in real depots

    LeadersSpace leadersSpace;
    MutableProduction baseProduction;
    Developments developments;



    public ProductionManager(Developments developments, MutableProduction baseProduction, LeadersSpace leadersSpace) {
        containers.put(DepotID.SourceType.DEPOT, new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT)));
        containers.put(DepotID.SourceType.STRONGBOX, new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX)));

        this.developments = developments;
        this.baseProduction = baseProduction;
        this.leadersSpace = leadersSpace;
    }


    @Override
    public void addSupply(DepotID slot, WarehouseObjectType wot, DepotID from) throws SupplyException {
        //TODO check if you can add, if not throw
        additionAllowed(slot, wot, from);

        //add supply to common "virtual" reserve
        if (from.getSource() != DepotID.SourceType.ANY) {
            containers.get(from.getSource()).addSupply(wot, from);
        }


        //actual addition, cannot fail cause of the previous check
        if (slot.getType() == DepotID.DepotType.DEVELOPMENT){
            developments.addSupply(slot, wot, from);
        }

        else if (slot.getType() == DepotID.DepotType.BASE_PRODUCTION){
            baseProduction.addSupply(wot, from);
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_PRODUCTION){
            try {
                leadersSpace.getLeaderAbility(slot.getNum()).addSupply(wot, from);
            } catch (LeaderException | NoSuchMethodException e){/*TODO terminate program*/}
        }

        else {
            /*TODO terminate program*/
        }
    }

    @Override
    public void removeSupply(DepotID slot, WarehouseObjectType wot, DepotID to) throws SupplyException {
        //TODO check if you can remove, if not throw
        removalAllowed(slot, wot, to);


        //remove supply to common "virtual" reserve
        if (to.getSource() != DepotID.SourceType.ANY) {
            containers.get(to.getSource()).removeSupply(wot);
        }


        //actual remove, cannot fail cause of the previous check
        try {
            if (slot.getType() == DepotID.DepotType.DEVELOPMENT) {
                developments.removeSupply(slot, wot);
            }

            else if (slot.getType() == DepotID.DepotType.BASE_PRODUCTION) {
                baseProduction.removeSupply(wot);
            }

            else if (slot.getType() == DepotID.DepotType.LEADER_PRODUCTION) {
                try {
                    leadersSpace.getLeaderAbility(slot.getNum()).removeSupply(wot);
                } catch (LeaderException | NoSuchMethodException e) {/*TODO terminate program*/}
            }

            else {
                /*TODO terminate program*/
            }
        } catch(Exception e){/*TODO terminate the program because we checked before, so everything should go smooth*/}
    }

    @Override
    public boolean additionAllowed(DepotID slot, WarehouseObjectType wot, DepotID from){

        if (slot.getType() == DepotID.DepotType.DEVELOPMENT){
            return developments.additionAllowed(slot, wot, from);
        }

        else if (slot.getType() == DepotID.DepotType.BASE_PRODUCTION){
            return baseProduction.additionAllowed(wot, from);
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_PRODUCTION){
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
        if (from.getType() == DepotID.DepotType.DEVELOPMENT){
            return developments.removalAllowed(from, wot, to);
        }

        else if (from.getType() == DepotID.DepotType.BASE_PRODUCTION){
            return baseProduction.removalAllowed(wot, to);
        }

        else if (from.getType() == DepotID.DepotType.LEADER_PRODUCTION){
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
        developments.clearSupplies();
        baseProduction.clearSupplies();

        //clear only producer leaders
        for (int i = 0; i<2; ++i){
            try {
                if (leadersSpace.getLeaderAbility(0) instanceof Producer) {
                    leadersSpace.getLeaderAbility(i).clearSupplies();
                }
            } catch (LeaderException | NoSuchMethodException e){}
        }

        return new Pair<>(containers.get(DepotID.SourceType.DEPOT).clearSupplies().first, containers.get(DepotID.SourceType.DEPOT).clearSupplies().second);
    }

    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(DepotID slot) throws NoSuchMethodException {
        SupplyContainer removed = new SupplyContainer();

        if (slot.getType() == DepotID.DepotType.DEVELOPMENT){
            removed = developments.clearSupplies(slot).first;
        }

        else if (slot.getType() == DepotID.DepotType.BASE_PRODUCTION){
            removed = baseProduction.clearSupplies(slot).first;
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_PRODUCTION){
            try {
                if (leadersSpace.getLeaderAbility(0) instanceof Depot) {
                    removed = leadersSpace.getLeaderAbility(slot.getNum()).clearSupplies(slot).first;
                }
                else {
                    //TODO terminate the program
                }
            } catch (LeaderException | NoSuchMethodException e){/*TODO terminate program*/}
        }

        else {
            /*TODO terminate program*/
        }


        //initially try to remove from the strongbox, if you can't try from the depots
        Pair<SupplyContainer, SupplyContainer> res = new Pair<>(new SupplyContainer(), new SupplyContainer());
        //for each type of supply FIXME
        for (WarehouseObjectType wot : WarehouseObjectType.values()) {
            //for each supply of the processed type removed previously
            for (int i = 0; i<removed.getQuantity(wot); ++i) {
                try {
                    containers.get(DepotID.SourceType.STRONGBOX).removeSupply(wot); //try to remove from strongbox
                    res.second.addSupply(wot); //add supply to return from strongbox
                } catch (SupplyException se){
                    try {
                        containers.get(DepotID.SourceType.DEPOT).removeSupply(wot); //if cannot remove from strongbox try to remove from depots
                        res.first.addSupply(wot); //add supply to return from depots
                    } catch (SupplyException se1){/*TODO terminate program*/}
                }
            }
        }

        return res;
    }


    public SupplyContainer produce(boolean s1, boolean s2, boolean s3, boolean l1, boolean l2, boolean base){
        //get productions outputs
        SupplyContainer developmentProduction = developments.produce(s1, s2, s3);

        SupplyContainer baseProduction = new SupplyContainer();
        if(base) {
            baseProduction = this.baseProduction.produce();
        }

        SupplyContainer leader1Production = new SupplyContainer();
        if(l1) {
            try {
                leader1Production = leadersSpace.getLeaderAbility(0).produce();
            } catch (NoSuchMethodException | LeaderException e) {}
        }

        SupplyContainer leader2Production = new SupplyContainer();
        if(l2) {
            try {
                leader2Production = leadersSpace.getLeaderAbility(1).produce();
            } catch (NoSuchMethodException | LeaderException e) {}
        }

        //sum output in temporary container
        SupplyContainer res = new SupplyContainer();
        res.sum(developmentProduction).sum(baseProduction).sum(leader1Production).sum(leader2Production);
        return res;
    }



    public void checkProduction(boolean s1, boolean s2, boolean s3, boolean l1, boolean l2, boolean base) throws SupplyException{
        developments.checkProduction(s1, s2, s3);

        if(base) {
            baseProduction.check();
        }

        if(l1) {
            try {
                leadersSpace.getLeaderAbility(0).checkProduction();
            } catch (NoSuchMethodException | LeaderException e) {}
        }

        if (l2) {
            try {
                leadersSpace.getLeaderAbility(1).checkProduction();
            } catch (NoSuchMethodException | LeaderException e) {}
        }
    }
}
