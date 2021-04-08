package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.SupplyException;

public class Paycheck implements AcceptsSupplies {

    private SupplyContainer fromCoffer = new SupplyContainer();
    private SupplyContainer fromDepot = new SupplyContainer();



    public Paycheck(){}


    /**
     * Returns the total of the supplies in the paycheck
     * @return total supplies in the paycheck.
     */
    public SupplyContainer getAll(){
        SupplyContainer tot = new SupplyContainer();
        tot.sum(fromCoffer).sum(fromDepot);
        return tot;
    }


    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        //check where to put the new supply
        if (from == DepotID.COFFER){
            fromCoffer.addSupply(wot);
        }
        else {
            fromDepot.addSupply(wot);
        }
    }

    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID slot) throws SupplyException {
        if (slot == DepotID.PAYCHECK_COFFER){
            fromCoffer.removeSupply(wot);
        }
        else {
            fromDepot.removeSupply(wot);
        }
    }


    @Override
    public SupplyContainer clearSupplies() {
        SupplyContainer result = new SupplyContainer();
        result.sum(fromCoffer.clearSupplies()).sum(fromDepot.clearSupplies());
        return result;
    }
}
