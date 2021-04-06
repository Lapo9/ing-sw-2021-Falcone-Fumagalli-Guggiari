package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.MarbleException;
import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.*;


/**
 * This implementation of LeaderAbility is a depot, so it implements all of the methods to manage a depot (add and remove supplies).
 */
public class Depot implements LeaderAbility {

    private ImmutableBoundedSupplyContainer depot;


    /**
     * Builds a Depot with the specified type and always 2 as maximum storage.
     * @param type type contained by the depot
     * @param abilityID is this leader 1 or 2?
     */
    public Depot(WarehouseObjectType type, int abilityID){
        try {
            depot = new ImmutableBoundedSupplyContainer(2, type);
        } catch (SupplyException se){/*TODO end program*/}

    }


    @Override
    public Pair<WarehouseObjectType, Integer> getDepotInfo() {
        return new Pair<>(depot.getType(), depot.getQuantity());
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
