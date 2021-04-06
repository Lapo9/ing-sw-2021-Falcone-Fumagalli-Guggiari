package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.SupplyContainer;

/**
 * The Production class represents the production mechanism of the game
 */
public class Production implements AcceptsSupplies, HasStatus{
    protected final SupplyContainer input;
    protected final SupplyContainer output;
    protected SupplyContainer currentSupply;
    protected DepotID depotId;


    /**
     * Class constructor
     * @param in is a SupplyContainer which contains the supplies needed as input
     * @param out is a SupplyContainer which contains the supplies produces by output when the production is triggered
     */
    public Production(SupplyContainer in, SupplyContainer out, DepotID id){
        input = in;
        output = out;
        currentSupply = new SupplyContainer();
        depotId = id;
    }

    /**
     * The getInput method returns the input SupplyContainer
     * @return the input SupplyContainer
     */
    protected SupplyContainer getInput() {
        return input;
    }

    /**
     * The getOutput method returns the output SupplyContainer
     * @return the output SupplyContainer
     */
    protected SupplyContainer getOutput(){
        return output;
    }

    /**
     * The getCurrentSupply method returns the currentSupply
     * @return the currentSupply
     */
    protected SupplyContainer getCurrentSupply(){
        return currentSupply;
    }

    /**
     * The produce method activates the production only if the supplies in the currentSupply are equal to the one in the
     * input SupplyContainer
     * @return a SupplyContainer which container the output resources
     */
    public SupplyContainer produce(){
        try {
            check();
        } catch (SupplyException e) {
            //FIXME
            //Idk what to put in here
        }
        return new SupplyContainer(output);
    }

    /**
     * The check method verifies if the supplies contained in the currentSupply are right to start a production
     * @throws SupplyException if the currentSupply doesn't contain the right supplies
     */
    public void check() throws SupplyException{
        if(!input.confront(currentSupply))
            throw new SupplyException();
    }

    @Override
    public void addSupply(WarehouseObjectType wot) throws SupplyException{
        if(availableDepots(null, wot).contains(depotId)) {
            currentSupply.addSupply(wot);
        }
        else {
            throw new SupplyException();
        }
    }

    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException{
        currentSupply.removeSupply(wot);
    }

    @Override
    public SupplyContainer clearSupplies(){
        return new SupplyContainer(currentSupply.clearSupplies());
    }


    @Override
    public ArrayList<DepotID> availableDepots(DepotID from, WarehouseObjectType wot) {
        ArrayList<DepotID> res = new ArrayList<>();
        if(currentSupply.getQuantity(wot) >= input.getQuantity(wot)){
            return res;
        }
        res.add(depotId);
        return res;
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus(){

    }
}
