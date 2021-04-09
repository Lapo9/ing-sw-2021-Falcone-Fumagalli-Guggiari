package it.polimi.ingsw.model;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.exceptions.SupplyException;


/**
 * A Paycheck can store resources based from their source (strongbox or depot).
 */
public class Paycheck implements AcceptsSupplies {

    private SupplyContainer fromStrongbox = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX), SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.STRONGBOX));
    private SupplyContainer fromDepot = new SupplyContainer(SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT), SupplyContainer.AcceptStrategy.onlyFrom(DepotID.SourceType.DEPOT));


    public Paycheck(){}


    /**
     * Returns the total of the supplies in the paycheck.
     * @return Total supplies in the paycheck
     */
    public SupplyContainer getAll(){
        SupplyContainer tot = new SupplyContainer();
        tot.sum(fromStrongbox).sum(fromDepot);
        return tot;
    }


    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        try {
            fromStrongbox.addSupply(wot, from);
        } catch (SupplyException se){
            fromDepot.addSupply(wot, from);
        }
    }

    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException {
        try {
            fromStrongbox.removeSupply(wot, to);
        } catch (SupplyException se){
            fromDepot.removeSupply(wot, to);
        }
    }


    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        boolean res = false;
        res |= fromStrongbox.additionAllowed(wot, from);
        res |= fromDepot.additionAllowed(wot, from);
        return res;
    }


    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) {
        boolean res = false;
        res |= fromStrongbox.removalAllowed(wot, to);
        res |= fromDepot.removalAllowed(wot, to);
        return res;
    }


    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return new Pair<>(fromDepot.clearSupplies().first, fromStrongbox.clearSupplies().second);
    }
}
