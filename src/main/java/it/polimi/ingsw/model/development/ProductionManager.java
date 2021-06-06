package it.polimi.ingsw.model.development;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.LeaderException;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.leaders.leader_abilities.Producer;
import it.polimi.ingsw.model.leaders.LeadersSpace;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages all of those depots that can contain resources both from the strongbox and the depots, namely production depots, leader production depots and paycheck
 */
public class ProductionManager implements AcceptsSupplies {

    HashMap<DepotID.SourceType, SupplyContainer> containers = new HashMap<>(); //place where we store a copy of each resource in real depots

    LeadersSpace leadersSpace;
    MutableProduction baseProduction;
    Developments developments;


    /**
     * Creates a production manager.
     * @param developments the player's developments
     * @param baseProduction the player's base production
     * @param leadersSpace the player's leader space
     */
    public ProductionManager(Developments developments, MutableProduction baseProduction, LeadersSpace leadersSpace) {
        containers.put(DepotID.SourceType.DEPOT, new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT)));
        containers.put(DepotID.SourceType.STRONGBOX, new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX)));

        this.developments = developments;
        this.baseProduction = baseProduction;
        this.leadersSpace = leadersSpace;
    }

    /**
     * Adds the supply to the specified storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param slot Storage to add the supply to
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(DepotID slot, WarehouseObjectType wot, DepotID from) throws SupplyException {
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
            } catch (LeaderException | NoSuchMethodException e){System.out.print("BUG"); /*TODO terminate program*/}
        }

        else {
            System.out.print("BUG"); /*TODO terminate program*/
        }
    }

    /**
     * Removes the supply from the specified storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param slot Storage to remove the supply from
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(DepotID slot, WarehouseObjectType wot, DepotID to) throws SupplyException {
        removalAllowed(slot, wot, to);


        //remove supply to common "virtual" reserve
        if (to.getSource() != DepotID.SourceType.ANY) {
            containers.get(to.getSource()).removeSupply(wot);
        }


        //actual remove, cannot fail cause of the previous check
        try {
            if (slot.getType() == DepotID.DepotType.DEVELOPMENT) {
                developments.removeSupply(slot, wot, to);
            }

            else if (slot.getType() == DepotID.DepotType.BASE_PRODUCTION) {
                baseProduction.removeSupply(wot, to);
            }

            else if (slot.getType() == DepotID.DepotType.LEADER_PRODUCTION) {
                try {
                    leadersSpace.getLeaderAbility(slot.getNum()).removeSupply(wot, to);
                } catch (LeaderException | NoSuchMethodException e) {System.out.print("BUG"); /*TODO terminate program*/}
            }

            else {
                System.out.print("BUG"); /*TODO terminate program*/
            }
        } catch(Exception e){
            e.printStackTrace();System.out.print("BUG"); /*TODO terminate the program because we checked before, so everything should go smooth*/}
    }

    /**
     * Checks if the addition of the supply to the specified storage, coming from the specified source, is allowed.
     * @param slot Storage to add the supply to
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
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

    /**
     * Checks if the removal of the supply from the specified storage, direct to the specified destination, is allowed.
     * @param from Storage to remove the supply from
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
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

    /**
     * Removes all of the supplies.
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        developments.clearSupplies();
        baseProduction.clearSupplies();

        //clear only producer leaders
        for (int i = 0; i<2; ++i){
            try {
                if (leadersSpace.getLeaderAbility(i) instanceof Producer) {
                    leadersSpace.getLeaderAbility(i).clearSupplies();
                }
            } catch (LeaderException | NoSuchMethodException e){}
        }

        return new Pair<>(containers.get(DepotID.SourceType.DEPOT).clearSupplies().first, containers.get(DepotID.SourceType.STRONGBOX).clearSupplies().first);
    }

    /**
     * Removes all of the supplies in the specified storage.
     * @param slot Storage to clear.
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies(DepotID slot) throws NoSuchMethodException {
        SupplyContainer removed = new SupplyContainer();

        if (slot.getType() == DepotID.DepotType.DEVELOPMENT){
            removed = developments.clearSupplies(slot).first;
        }

        else if (slot.getType() == DepotID.DepotType.BASE_PRODUCTION){
            removed = baseProduction.clearSupplies().first;
        }

        else if (slot.getType() == DepotID.DepotType.LEADER_PRODUCTION){
            try {
                if (leadersSpace.getLeaderAbility(slot.getNum()) instanceof Producer) {
                    removed = leadersSpace.getLeaderAbility(slot.getNum()).clearSupplies().first;
                }
                else {
                    System.out.print("BUG"); //TODO terminate the program
                }
            } catch (LeaderException | NoSuchMethodException e){System.out.print("BUG"); /*TODO terminate program*/}
        }

        else {
            System.out.print("BUG"); /*TODO terminate program*/
        }


        return removeFromSourceContainers(removed); //remove the resources from the source containers (first try to remove from strongbox, then from depots)
    }


    /**
     * Removes the resources from the source containers (first try to remove from strongbox, then from depots)
     * @param toRemove the SupplyContainer to empty
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    private Pair<SupplyContainer, SupplyContainer> removeFromSourceContainers(SupplyContainer toRemove) {
        //initially try to remove from the strongbox, if you can't try from the depots
        Pair<SupplyContainer, SupplyContainer> res = new Pair<>(new SupplyContainer(), new SupplyContainer());
        //for each type of supply
        for (WarehouseObjectType wot : WarehouseObjectType.values()) {
            //for each supply of the processed type removed previously
            if (wot != WarehouseObjectType.NO_TYPE) {
                for (int i = 0; i < toRemove.getQuantity(wot); ++i) {
                    try {
                        containers.get(DepotID.SourceType.STRONGBOX).removeSupply(wot); //try to remove from strongbox
                        res.second.addSupply(wot); //add supply to return from strongbox
                    } catch (SupplyException se) {
                        try {
                            containers.get(DepotID.SourceType.DEPOT).removeSupply(wot); //if cannot remove from strongbox try to remove from depots
                            res.first.addSupply(wot); //add supply to return from depots
                        } catch (SupplyException se1) {System.out.print("BUG"); /*TODO terminate program*/}
                    }
                }
            }
        }

        return res;
    }




    /**
     * Activates production in the specified production spaces
     * @param s1 Development space 1
     * @param s2 Development space 2
     * @param s3 Development space 3
     * @param l1 Leader 1 production (if leader 1 has that ability)
     * @param l2 Leader 2 production (if leader 2 has that ability)
     * @param base Production of the board
     * @return Returns the sum of the output of the active productions
     */
    public SupplyContainer produce(boolean s1, boolean s2, boolean s3, boolean l1, boolean l2, boolean base){
        //get productions outputs
        SupplyContainer developmentProduction = developments.produce(s1, s2, s3);
        SupplyContainer developmentsInputs = developments.getInput(s1, s2, s3);

        SupplyContainer baseProduction = new SupplyContainer();
        SupplyContainer baseInputs = new SupplyContainer();
        if(base) {
            baseProduction = this.baseProduction.produce();
            baseInputs = this.baseProduction.getInput();
        }

        SupplyContainer leader1Production = new SupplyContainer();
        SupplyContainer leader1Inputs = new SupplyContainer();
        if(l1) {
            try {
                leader1Production = leadersSpace.getLeaderAbility(0).produce();
                leader1Inputs = leadersSpace.getLeaderAbility(0).getInput();
            } catch (NoSuchMethodException | LeaderException e) {}
        }

        SupplyContainer leader2Production = new SupplyContainer();
        SupplyContainer leader2Inputs = new SupplyContainer();
        if(l2) {
            try {
                leader2Production = leadersSpace.getLeaderAbility(1).produce();
                leader2Inputs = leadersSpace.getLeaderAbility(1).getInput();
            } catch (NoSuchMethodException | LeaderException e) {}
        }

        //sum inputs in temporary container and remove them from the source containers
        SupplyContainer tmp = new SupplyContainer();
        tmp.sum(developmentsInputs).sum(baseInputs).sum(leader1Inputs).sum(leader2Inputs);
        removeFromSourceContainers(tmp); //remove the resources from the source containers (first try to remove from strongbox, then from depots)


        //sum output in temporary container
        SupplyContainer res = new SupplyContainer();
        res.sum(developmentProduction).sum(baseProduction).sum(leader1Production).sum(leader2Production);
        return res;
    }

    /**
     * Checks if the production in the specified productions spaces can be triggered
     * @param s1 Development space 1
     * @param s2 Development space 2
     * @param s3 Development space 3
     * @param l1 Leader 1 production (if leader 1 has that ability)
     * @param l2 Leader 2 production (if leader 2 has that ability)
     * @param base Production of the board
     * @throws SupplyException if the supplies on the productions spaces are not the right amount
     */
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

    /**
     * Swaps one of the mutable inputs/outputs of the base production.
     * @param i 0 = first input, 1 = second input, 2 = output
     * @param wot new supply to insert
     * @throws SupplyException A faith marker or no type supply type was added
     */
    public void swapBaseProduction(int i, WarehouseObjectType wot) throws SupplyException{
        if (i==2){
            baseProduction.swapOutput(0, wot);
        }
        else {
            baseProduction.swapInput(i, wot);
        }
    }

    /**
     * Swaps the only mutable output in the specified leader
     * @param i number of leader
     * @param wot new supply to insert
     * @throws SupplyException A no type supply type was added
     * @throws NoSuchMethodException Specified leader is not a Producer
     * @throws LeaderException Specified leader is not active or is discarded
     */
    public void swapLeaderProduction(int i, WarehouseObjectType wot) throws SupplyException, NoSuchMethodException, LeaderException{
        leadersSpace.getLeaderAbility(i).swapProduction(wot);
    }


    /**
     * Returns the depots that can receive the specified supply.
     * @param from Source of the supply
     * @param type Type of the supply
     * @return The depots that can receive the specified supply
     */
    public ArrayList<DepotID> getAllowedDepots(DepotID from, WarehouseObjectType type){
        ArrayList<DepotID> res = new ArrayList<>();

        for(DepotID id : DepotID.values()) {
            if (additionAllowed(id, type, from)) {
                res.add(id);
            }
        }

        return res;
    }


    /**
     * Returns an integer that indicates the source of the given WarehouseObjectType.
     * @param wot a WarehouseObjectType
     * @return 0 if the given WarehouseObjectType isn't contained in any production depot,
     *         1 if there are at least two different given WarehouseObjectType, one from a depot and the other from the coffer,
     *         2 if the given WarehouseObjectType is from a depot,
     *         3 if the given WarehouseObjectType is from the coffer
     */
    public int getSource(WarehouseObjectType wot) {
        if(containers.get(DepotID.SourceType.DEPOT).getQuantity(wot) != 0 && containers.get(DepotID.SourceType.STRONGBOX).getQuantity(wot) != 0)
            return 1;
        else if(containers.get(DepotID.SourceType.DEPOT).getQuantity(wot) != 0 && containers.get(DepotID.SourceType.STRONGBOX).getQuantity(wot) == 0)
            return 2;
        else if(containers.get(DepotID.SourceType.DEPOT).getQuantity(wot) == 0 && containers.get(DepotID.SourceType.STRONGBOX).getQuantity(wot) != 0)
            return 3;
        else
            return 0;
    }
}
