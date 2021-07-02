package it.polimi.ingsw.model.leaders.leader_abilities;

import it.polimi.ingsw.Pair;
import it.polimi.ingsw.model.development.MutableProduction;
import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;


/**
 * This implementation of LeaderAbility only supports the methods related to the production process.
 */
public class Producer implements LeaderAbility {

    MutableProduction production;


    /**
     * Creates a new producer leader, whose fixed output is always a faith point
     * @param fixedInput Fixed input of the leader
     */
    public Producer(SupplyContainer fixedInput){
        SupplyContainer fixedOutput = new SupplyContainer();
        try {
            fixedOutput.addSupply(WarehouseObjectType.FAITH_MARKER);
        } catch (SupplyException se) {System.exit(1);}

        production = new MutableProduction(fixedInput, fixedOutput, 1, 2);
    }

    /**
     * Gets the input of the MutableProduction
     * @return the SupplyContainer containing the mutable input
     * @throws NoSuchMethodException iif the LeaderCard is not allowed to use that ability
     */
    @Override
    public SupplyContainer getInput() throws NoSuchMethodException {
        return production.getInput();
    }

    /**
     * This method activates the leader card production
     * @return An extra SupplyContainer that contains the resource
     */
    @Override
    public SupplyContainer produce() {
        return production.produce();
    }

    /**
     * This method checks if the LeaderCard actually can activate the production
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void checkProduction() throws SupplyException {
        production.check();
    }

    /**
     * Substitutes the current supply present in the specified output slot with the supply given as argument
     * @param wot supply to replace
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void swapProduction(WarehouseObjectType wot) throws SupplyException{
        production.swapOutput(0, wot);
    }

    /**
     * Adds the supply to the storage. Information about the source of the object needed.
     * Beforehand the corresponding additionAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @throws SupplyException The container cannot accept the supply
     */
    @Override
    public void addSupply(WarehouseObjectType wot, DepotID from) throws SupplyException {
        production.addSupply(wot, from);
    }

    /**
     * Removes the supply from the storage. Information about the destination of the object needed.
     * Beforehand the corresponding removalAllowed method is called to check if the operation can be performed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @throws SupplyException The container cannot remove the supply
     */
    @Override
    public void removeSupply(WarehouseObjectType wot, DepotID to) throws SupplyException {
        production.removeSupply(wot, to);
    }

    /**
     * Checks if the addition of the supply to the storage, coming from the specified source, is allowed.
     * @param wot One of the five types of resources in the game
     * @param from Source of the supply
     * @return Whether the container can accept the supply or not
     */
    @Override
    public boolean additionAllowed(WarehouseObjectType wot, DepotID from) {
        return production.additionAllowed(wot, from);
    }

    /**
     * Checks if the removal of the supply from the storage, direct to the specified destination, is allowed.
     * @param wot One of the five types of resources in the game
     * @param to Destination of the supply
     * @return Whether the container can remove the supply or not
     */
    @Override
    public boolean removalAllowed(WarehouseObjectType wot, DepotID to) throws NoSuchMethodException {
        return production.removalAllowed(wot, to);
    }

    /**
     * Removes all of the supplies.
     * @return A pair of SupplyContainer containing the removed supplies. The first element contains supplies from the depots, the second one supplies from the strongbox.
     */
    @Override
    public Pair<SupplyContainer, SupplyContainer> clearSupplies() {
        return production.clearSupplies();
    }

    /**
     * Allows to receive the status of every object which implements this interface in the form of an ArrayList of Integer
     * @return an ArrayList made of Integer that describes the production of the leader card
     */
    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        ArrayList<Integer> tmp = production.getStatus();

        //transform the sequence COIN, SERVANT, SHIELD, STONE, FAITH_MARKER that is the fixed input of the production into one int that represent what is the only fixed input more shortly (0=COIN, 1=SERVANT, ...)
        int fixedInput = 0;
        for(int i=0; i<5; ++i){
            if(tmp.get(i) != 0){
                fixedInput = i;
                break;
            }
        }

        //same for fixed output
        int fixedOutput = 0;
        for(int i=0; i<5; ++i){
            if(tmp.get(i+6) != 0){
                fixedOutput = i;
                break;
            }
        }

        int mutableOutput = tmp.get(11);

        status.add(fixedInput);
        status.add(fixedOutput);
        status.add(mutableOutput);
        status.addAll(tmp.subList(12, 17)); //current depot

        for(int i=0; i<5; ++i){
            status.add(0); //Producer doesn't have a "warehouse depot"
        }

        return status;
    }
}
