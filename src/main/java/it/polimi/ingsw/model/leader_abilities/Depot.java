package it.polimi.ingsw.model.leader_abilities;


import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;


/**
 * This implementation of LeaderAbility is a depot, so it implements all of the methods to manage a depot (add and remove supplies).
 */
public class Depot implements LeaderAbility {

    private SupplyContainer depot;


    /**
     * Builds a new depot leader with the specified type and always 2 as maximum storage.
     * @param type Type contained by the depot
     */
    public Depot(WarehouseObjectType type){
        depot = new SupplyContainer(onlyFrom(DepotID.SourceType.STRONGBOX).negate().and(maxSpecificType(type, 2)));
    }


    @Override
    public Pair<WarehouseObjectType, Integer> getDepotInfo() {
        Pair<WarehouseObjectType, Integer> res = null;
        try {
            res = new Pair<>(depot.getType(), depot.getQuantity());
        } catch (SupplyException se){/*TODO terminate program*/}
        return res;
    }


    @Override
    public void addMarble(MarbleColor color, LeadersSpace ls) throws MarbleException, SupplyException {
        depot.addMarble(color, ls);
    }


    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        depot.addSupply(wot, from);
    }


    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException {
        depot.removeSupply(wot);
    }


    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return depot.additionAllowed(wot, from);
    }


    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        return depot.removalAllowed(wot, to);
    }


    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return depot.clearSupplies();
    }


    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        //fixed input, fixed output, mutable output, production depot (COIN, SERVANT, SHIELD, STONE, FAITH_MARKER)
        for(int i=0; i<8; ++i){
            status.add(0);
        }

        status.addAll(depot.getStatus());

        return status;
    }
}
