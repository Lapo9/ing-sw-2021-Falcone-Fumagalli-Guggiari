package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.SupplyContainer;

import java.util.ArrayList;

/**
 * The MutableProduction class represent the mutable production mechanism of the game, where the player can choose
 * the input and the output of the production
 */
public class MutableProduction extends Production implements HasStatus{
    private SupplyContainer mutableInput;
    private SupplyContainer mutableOutput;
    private final int maxInput;
    private final int maxOutput;

    /**
     * Class constructor
     * @param in  is a SupplyContainer which contains the supplies needed as input
     * @param out is a SupplyContainer which contains the supplies produces by output when the production is triggered
     * @param dimInput is the max dimension of the input
     * @param dimOutput is the max dimension of the output
     * @param id depot ID of this production
     */
    public MutableProduction(SupplyContainer in, SupplyContainer out, int dimInput, int dimOutput, DepotID id){
        super(in, out, id);
        maxInput = dimInput - in.getQuantity();         //maxInput is the dimension of the mutableInput
        maxOutput = dimOutput - out.getQuantity();      //maxOutput is the dimension of the mutableOutput
        mutableInput = new SupplyContainer();
        mutableOutput = new SupplyContainer();
    }



    /**
     * Creates an object without any fixed input or output
     * @param dimInput is the max dimension of the input
     * @param dimOutput is the max dimension of the output
     * @param id depot ID of this production
     */
    public MutableProduction(int dimInput, int dimOutput, DepotID id){
        super(new SupplyContainer(), new SupplyContainer(), id); //0 resources in fixed input/output
        maxInput = dimInput;         //maxInput is the dimension of the mutableInput
        maxOutput = dimOutput;      //maxOutput is the dimension of the mutableOutput
        mutableInput = new SupplyContainer();
        mutableOutput = new SupplyContainer();
    }



    /**
     * The produce method activates the production is the isActive parameter is true
     * @return a SupplyContainer containing the output + mutableOutput
     */
    @Override
    public SupplyContainer produce(){
        try{
            check();
        } catch (SupplyException se){/*FIXME end program*/}
        return new SupplyContainer(mutableOutput.sum(getOutput()));
    }

    /**
     * The check method verifies if the supplies contained in the currentSupply are right to start a production
     */
    @Override
    public void check() throws SupplyException{
        SupplyContainer temp = getInput().sum(mutableInput);
        if(temp.confront(getCurrentSupply()))
            throw new SupplyException();
    }



    @Override
    public ArrayList<DepotID> availableDepots(DepotID from, WarehouseObjectType wot) {
        ArrayList<DepotID> res = new ArrayList<>();
        if(currentSupply.getQuantity(wot) >= input.sum(mutableInput).getQuantity(wot)){
            return res;
        }
        res.add(depotId);
        return res;
    }



    /**
     * The addInput method adds a WarehouseObjectType to the mutableInput
     * @param wot is the type of supply that the player wants to add to the mutableInput SupplyContainer
     * @throws BoundsException if the mutableInput is already full
     */
    public void addInput(WarehouseObjectType wot)throws BoundsException {
        int temp = mutableInput.getQuantity();
        if (temp >= maxInput)
            throw new BoundsException();

        mutableInput.addSupply(wot);

    }

    /**
     * The addOutput method adds a WarehouseObjectType to the mutableOuput
     * @param wot is the type of supply that the player wants to add to the mutableOutput SupplyContainer
     * @throws BoundsException if the mutableOutput is already full
     */
    public void addOutput(WarehouseObjectType wot) throws BoundsException{
        int temp = mutableOutput.getQuantity();
        if (temp >= maxOutput)
            throw new BoundsException();

        mutableOutput.addSupply(wot);
    }

    /**
     * The removeInput method removes a WarehouseObjectType from the mutableInput
     * @param wot is the type of supply that the player wants to remove from the mutableInput SupplyContainer
     * @throws BoundsException if the mutableInput is already empty
     */
    public void removeInput(WarehouseObjectType wot) throws BoundsException{
        int temp = mutableInput.getQuantity();
        if (temp == 0)
            throw new BoundsException();
        try {
            mutableInput.removeSupply(wot);
        } catch (SupplyException e) {
            //FIXME
        }
    }

    /**
     * The removeOutput method removes a WarehouseObjectType from the mutableOutput
     * @param wot is the type of supply that the player wants to remove from the mutableOutput SupplyContainer
     * @throws BoundsException if the mutableOutput is already empty
     */
    public void removeOutput(WarehouseObjectType wot) throws BoundsException{
        int temp = mutableOutput.getQuantity();
        if (temp == 0)
            throw new BoundsException();

        mutableOutput.addSupply(wot);
    }

    @Override
    public ArrayList<Integer> getStatus(){

    }
}
