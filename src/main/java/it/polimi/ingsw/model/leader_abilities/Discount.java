package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.exceptions.SupplyException;
import it.polimi.ingsw.model.LeaderAbility;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;


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
}
