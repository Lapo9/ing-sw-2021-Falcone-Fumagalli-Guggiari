package it.polimi.ingsw.model.development;

import static it.polimi.ingsw.model.SupplyContainer.AcceptStrategy.*;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.SupplyException;

import java.util.ArrayList;


/**
 * A Paycheck can store resources based from their source (strongbox or depot).
 */
public class Paycheck implements AcceptsSupplies, HasStatus {

    private SupplyContainer fromStrongbox = new SupplyContainer(onlyFrom(DepotID.SourceType.STRONGBOX), onlyFrom(DepotID.SourceType.STRONGBOX));
    private SupplyContainer fromDepot = new SupplyContainer(onlyFrom(DepotID.SourceType.DEPOT), onlyFrom(DepotID.SourceType.DEPOT));


    /**
     * Creates a paycheck.
     */
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
        return new Pair<>(fromDepot.clearSupplies().first, fromStrongbox.clearSupplies().first);
    }


    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        status.addAll(fromStrongbox.getStatus());
        status.addAll(fromDepot.getStatus());

        return status;
    }
}
