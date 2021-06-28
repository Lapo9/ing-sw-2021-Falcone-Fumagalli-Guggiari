package it.polimi.ingsw.model.development;

import it.polimi.ingsw.model.HasStatus;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.leaders.LeadersSpace;

import java.util.ArrayList;

/**
 * The MutableProduction class represents the mutable production mechanism of the game,
 * where the player can choose the input and the output of the production.
 */
public class MutableProduction extends Production implements HasStatus {

    private ArrayList<WarehouseObjectType> mutableInput = new ArrayList<>();
    private ArrayList<WarehouseObjectType> mutableOutput = new ArrayList<>();


    /**
     * Class constructor.
     * @param in  is a SupplyContainer which contains the supplies needed as fixed input
     * @param out is a SupplyContainer which contains the supplies produced as fixed output when the production is triggered
     * @param dimInput is the maximum size of the input (fixed + mutable)
     * @param dimOutput is the maximum dimension of the output (fixed + mutable)
     */
    public MutableProduction(SupplyContainer in, SupplyContainer out, int dimInput, int dimOutput){
        super(in, out);
        int maxMutableInput = dimInput - in.getQuantity();         //maxMutableInput is the dimension of the mutableInput
        int maxMutableOutput = dimOutput - out.getQuantity();      //maxMutableOutput is the dimension of the mutableOutput

        for (int i = 0; i < maxMutableInput; ++i) {
            mutableInput.add(WarehouseObjectType.COIN);
        }
        for (int i = 0; i < maxMutableOutput; ++i) {
            mutableOutput.add(WarehouseObjectType.COIN);
        }
    }

    /**
     * Creates an object without any fixed input and output.
     * @param dimInput is the maximum size of the input (mutable)
     * @param dimOutput is the maximum dimension of the output (mutable)
     */
    public MutableProduction(int dimInput, int dimOutput){
        super(new SupplyContainer(SupplyContainer.AcceptStrategy.none()), new SupplyContainer(SupplyContainer.AcceptStrategy.none())); //0 resources in fixed input/output
        for (int i = 0; i < dimInput; ++i) {
            mutableInput.add(WarehouseObjectType.COIN);
        }
        for (int i = 0; i < dimOutput; ++i) {
            mutableOutput.add(WarehouseObjectType.COIN);
        }
    }

    /**
     * Gets the mutable input of the MutableProduction
     * @return the SupplyContainer containing the input
     */
    @Override
    public SupplyContainer getInput() {
        SupplyContainer tmp = new SupplyContainer();

        tmp.sum(input);
        mutableInput.forEach(warehouseObjectType -> {
            try{
                tmp.addSupply(warehouseObjectType);
            } catch (SupplyException se){System.exit(1); /*TODO end program*/}
        });

        return tmp;
    }

    /**
     * Activates the production. If the total input (fixed + mutable) equals the current supply store,
     * then the current supply store is wiped and the total output (fixed + mutable) is returned.
     * If not a fatal error is produced and the program is terminated.
     * @return A SupplyContainer containing the total output (fixed + mutable)
     */
    @Override
    public SupplyContainer produce(){
        try{
            check();
        } catch (SupplyException se){System.exit(1); /*TODO end program*/}

        SupplyContainer res = new SupplyContainer();
        res.sum(output);
        mutableOutput.forEach(warehouseObjectType -> {
            try{
                res.addSupply(warehouseObjectType);
            } catch (SupplyException se){System.exit(1); /*TODO end program*/}
        });

        currentSupply.clearSupplies();

        return res;
    }

    /**
     * Verifies if the current supplies present in the production depot are the same as the total input (fixed + mutable) supplies.
     * @throws SupplyException The current supplies present in the production depot are not the same as the total input (fixed + mutable) supplies.
     */
    @Override
    public void check() throws SupplyException{
        SupplyContainer tmp = new SupplyContainer();

        tmp.sum(input);
        mutableInput.forEach(warehouseObjectType -> {
            try{
                tmp.addSupply(warehouseObjectType);
            } catch (SupplyException se){System.exit(1); /*TODO end program*/}
        });

        if(!tmp.equals(currentSupply))
            throw new SupplyException("There isn't the correct number of supplies to produce");
    }


    /**
     * Substitutes the current supply present in the specified input slot with the supply given as argument.
     * @param num Slot
     * @param newInput New supply type to add (cannot be FAITH_MARKER or NO_TYPE)
     */
    public void swapInput(int num, WarehouseObjectType newInput) throws SupplyException{
        if(newInput == WarehouseObjectType.FAITH_MARKER || newInput == WarehouseObjectType.NO_TYPE){
            throw new SupplyException("Cannot use NO_TYPE as input in a MutableProduction");
        }
        mutableInput.set(num, newInput);
    }

    /**
     * Substitutes the current supply present in the specified output slot with the supply given as argument.
     * @param num Slot
     * @param newOutput New supply type to add (cannot be FAITH_MARKER or NO_TYPE)
     */
    public void swapOutput(int num, WarehouseObjectType newOutput) throws SupplyException{
        if(newOutput == WarehouseObjectType.NO_TYPE){
            throw new SupplyException("Cannot use NO_TYPE as output in a MutableProduction");
        }
        mutableOutput.set(num, newOutput);
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList describing the MutableProduction
     */
    @Override
    public ArrayList<Integer> getStatus(){
        ArrayList<Integer> status = new ArrayList<>();

        status.addAll(input.getStatus());

        //0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER
        for(int i = 0; i<mutableInput.size(); ++i) {
            status.add(mutableInput.get(i).ordinal());
        }

        if(mutableInput.size() == 0)
            status.add(0);

        status.addAll(output.getStatus());

        //0 = COIN, 1 = SERVANT, 2 = SHIELD, 3 = STONE, 4 = FAITH_MARKER
        for(int i = 0; i<mutableOutput.size(); ++i) {
            status.add(mutableOutput.get(i).ordinal());
        }

        status.addAll(currentSupply.getStatus());

        return status;
    }
}
