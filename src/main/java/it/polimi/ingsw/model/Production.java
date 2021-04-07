package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.exceptions.*;

/**
 * The Production class represents the production mechanism of the game
 */
public class Production implements AcceptsSupplies, HasStatus{

    protected final SupplyContainer input;
    protected final SupplyContainer output;
    protected SupplyContainer currentSupply = new SupplyContainer(/*TODO maybe cannot accept supplies from Paycheck and cannot accept faith markers?*/);
    ProductionDepotsManager manager;


    /**
     * Class constructor
     * @param in is a SupplyContainer which contains the supplies needed as input
     * @param out is a SupplyContainer which contains the supplies produces by output when the production is triggered
     */
    public Production(SupplyContainer in, SupplyContainer out, ProductionDepotsManager manager){
        input = in;
        output = out;
        this.manager = manager;
    }




    /**
     * The produce method activates the production only if the supplies in the currentSupply are equal to the one in the
     * input SupplyContainer
     * @return a SupplyContainer which container the output resources
     */
    public SupplyContainer produce(){
        try {
            check();
        } catch (SupplyException e) {/*TODO terminate program*/ }
        clearSupplies();
        return new SupplyContainer(output);
    }

    /**
     * The check method verifies if the supplies contained in the currentSupply are right to start a production
     * @throws SupplyException if the currentSupply doesn't contain the right supplies
     */
    public void check() throws SupplyException{
        if(!input.equals(currentSupply))
            throw new SupplyException();
    }

    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException{
        currentSupply.addSupply(wot, from);
        manager.notifyAddition(wot, from);
    }

    @Override
    public void removeSupply(DepotID to, WarehouseObjectType wot) throws SupplyException{
        //ask to the manager if it is possible to remove the specified resource, and check if you actually can remove them (relies on short circuit OR)
        if(currentSupply.checkRemove(wot) || !manager.canDelete(wot, to)){
            throw new SupplyException();
        }
        else {
            currentSupply.removeSupply(wot);
        }
    }

    @Override
    public SupplyContainer clearSupplies(){
        SupplyContainer tmp = new SupplyContainer(currentSupply);
        manager.notifyDeletion(currentSupply.clearSupplies()); //tell the manager about the cleared resources
        return tmp;
    }


    @Override
    public ArrayList<Integer> getStatus(){
        return null; //TODO
    }
}
