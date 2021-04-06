package it.polimi.ingsw.model;

import java.util.ArrayList;

import it.polimi.ingsw.exceptions.*;

/**
 * The Production class represents the production mechanism of the game
 */
public class Production implements AcceptsSupplies, HasStatus{
    protected final SupplyContainer input;
    protected final SupplyContainer output;
    protected SupplyContainer currentSupplyCoffer = new SupplyContainer();
    protected SupplyContainer currentSupplyDepot = new SupplyContainer();


    /**
     * Class constructor
     * @param in is a SupplyContainer which contains the supplies needed as input
     * @param out is a SupplyContainer which contains the supplies produces by output when the production is triggered
     */
    public Production(SupplyContainer in, SupplyContainer out){
        input = in;
        output = out;
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
        return currentSupplyCoffer.sum(currentSupplyDepot);
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
        if(!input.confront(getCurrentSupply()))
            throw new SupplyException();
    }

    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException{
        if(from.getSource() == DepotID.DepotType.COFFER){
            currentSupplyCoffer.addSupply(wot);
        }
        else {
            currentSupplyDepot.addSupply(wot);
        }
    }

    @Override
    public void removeSupply(DepotID from, WarehouseObjectType wot) throws SupplyException{
        if (from.getSource() == DepotID.DepotType.COFFER){
            currentSupplyCoffer.removeSupply(wot);
        }
        else {
            currentSupplyDepot.removeSupply(wot);
        }

    }

    @Override
    public SupplyContainer clearSupplies(){
        return new SupplyContainer(currentSupply.clearSupplies());
    }


    //TODO
    @Override
    public ArrayList<Integer> getStatus() {
        return null;
    }
}
