package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.SupplyContainer;

/**
 * The Production class represents the production mechanism of the game
 */
public class Production implements AcceptsSupplies, HasStatus{
    private final SupplyContainer input;
    private final SupplyContainer output;
    private SupplyContainer currentSupply;

    /**
     * Class constructor
     * @param in is a SupplyContainer which contains the supplies needed as input
     * @param out is a SupplyContainer which contains the supplies produces by output when the production is triggered
     */
    public Production(SupplyContainer in, SupplyContainer out){
        input = new SupplyContainer(in);
        output = new SupplyContainer(out);
        currentSupply = new SupplyContainer();
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
        currentSupply.addSupply(wot);
    }

    @Override
    public void removeSupply(WarehouseObjectType wot) throws SupplyException{
        currentSupply.removeSupply(wot);
    }

    @Override
    public SupplyContainer clearSupplies(){
        return new SupplyContainer(currentSupply.clearSupplies());
    }

    //TODO
    @Override
    public ArrayList<Integer> getStatus(){

    }
}
