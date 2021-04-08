package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;

/**
 * The MutableProduction class represent the mutable production mechanism of the game, where the player can choose
 * the input and the output of the production
 */
public class MutableProduction extends Production implements HasStatus{

    private ArrayList<WarehouseObjectType> mutableInput = new ArrayList<>();
    private ArrayList<WarehouseObjectType> mutableOutput = new ArrayList<>();


    /**
     * Class constructor
     * @param in  is a SupplyContainer which contains the supplies needed as input
     * @param out is a SupplyContainer which contains the supplies produces by output when the production is triggered
     * @param dimInput is the max dimension of the input
     * @param dimOutput is the max dimension of the output
     * @param id depot ID of this production
     */
    public MutableProduction(SupplyContainer in, SupplyContainer out, int dimInput, int dimOutput){
        super(in, out);
        int maxInput = dimInput - in.getQuantity();         //maxInput is the dimension of the mutableInput
        int maxOutput = dimOutput - out.getQuantity();      //maxOutput is the dimension of the mutableOutput

        for (int i = 0; i < maxInput; ++i) {
            mutableInput.add(WarehouseObjectType.COIN);
        }
        for (int i = 0; i < maxOutput; ++i) {
            mutableOutput.add(WarehouseObjectType.COIN);
        }
    }

    /**
     * Creates an object without any fixed input or output
     * @param dimInput is the max dimension of the input
     * @param dimOutput is the max dimension of the output
     * @param id depot ID of this production
     */
    public MutableProduction(int dimInput, int dimOutput){
        super(new SupplyContainer(SupplyContainer.AcceptStrategy.none()), new SupplyContainer(SupplyContainer.AcceptStrategy.none())); //0 resources in fixed input/output
        int maxInput = dimInput;         //maxInput is the dimension of the mutableInput
        int maxOutput = dimOutput;      //maxOutput is the dimension of the mutableOutput

        for (int i = 0; i < maxInput; ++i) {
            mutableInput.add(WarehouseObjectType.COIN);
        }
        for (int i = 0; i < maxOutput; ++i) {
            mutableOutput.add(WarehouseObjectType.COIN);
        }
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

        SupplyContainer res = new SupplyContainer();
        res.sum(output);
        mutableOutput.forEach(warehouseObjectType -> {
            try{
                res.addSupply(warehouseObjectType);
            } catch (SupplyException se){/*FIXME end program*/}
        });

        currentSupply.clearSupplies();

        return res;
    }

    /**
     * The check method verifies if the supplies contained in the currentSupply are right to start a production
     */
    @Override
    public void check() throws SupplyException{
        SupplyContainer tmp = new SupplyContainer();

        tmp.sum(input);
        mutableInput.forEach(warehouseObjectType -> {
            try{
                tmp.addSupply(warehouseObjectType);
            } catch (SupplyException se){/*FIXME end program*/}
        });

        if(!tmp.equals(currentSupply))
            throw new SupplyException();
    }


    public void swapInput(int num, WarehouseObjectType newInput){
        mutableInput.set(num, newInput);
    }

    public void swapOutput(int num, WarehouseObjectType newOutput){
        mutableOutput.set(num, newOutput);
    }


    @Override
    public ArrayList<Integer> getStatus(){

    }
}
