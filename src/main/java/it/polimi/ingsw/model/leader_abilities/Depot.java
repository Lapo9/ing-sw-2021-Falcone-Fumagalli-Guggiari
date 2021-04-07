package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;


/**
 * This implementation of LeaderAbility is a depot, so it implements all of the methods to manage a depot (add and remove supplies).
 */
public class Depot implements LeaderAbility {

    private SupplyContainer depot;


    /**
     * Builds a Depot with the specified type and always 2 as maximum storage.
     * @param type type contained by the depot
     * @param abilityID is this leader 1 or 2?
     */
    public Depot(WarehouseObjectType type){
        depot = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFromMaxSpecificType(type, 2, DepotID.SourceType.DEPOT));
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
    public void addSupply(DepotID to, WarehouseObjectType wot, DepotID from) throws SupplyException {
        if(from.getSource() == DepotID.DepotType.COFFER){
            throw new SupplyException();
        }
        depot.addSupply(wot);
    }


    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException {
        depot.removeSupply(wot);
    }


    @Override
    public SupplyContainer clearSupplies() throws NoSuchMethodException {
        return depot.clearSupplies();
    }


}
