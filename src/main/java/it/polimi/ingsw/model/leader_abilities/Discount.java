package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.exceptions.SupplyException;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;

import java.util.ArrayList;


/**
 * This implementation of LeaderAbility only supports the getDiscount method, which returns the discount an object of this class gives to the player.
 */
public class Discount implements LeaderAbility {

    private final SupplyContainer discount = new SupplyContainer(SupplyContainer.AcceptStrategy.max(1));

    /**
     * Creates a new discount leader.
     * @param wot Value of the discount
     */
    public Discount(WarehouseObjectType wot){
        try {
            discount.addSupply(wot);
        } catch (SupplyException se){/*TODO terminate program*/}
    }


    @Override
    public SupplyContainer getDiscount() {
        return discount;
    }


    @Override
    public ArrayList<Integer> getStatus() {
        ArrayList<Integer> status = new ArrayList<>();

        //fixed input, fixed output, mutable output, production depot (COIN, SERVANT, SHIELD, STONE, FAITH_MARKER), "warehouse depot" (COIN, SERVANT, SHIELD, STONE, FAITH_MARKER)
        for(int i=0; i<13; ++i){
            status.add(0);
        }

        return status;
    }

}
