package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.LeaderAbility;
import it.polimi.ingsw.model.SupplyContainer;
import it.polimi.ingsw.model.WarehouseObjectType;


/**
 * This implementation of LeaderAbility only supports the getDiscount method, which returns the discount an object of this class gives to the player.
 */
public class Discount implements LeaderAbility {

    private final SupplyContainer discount = new SupplyContainer();


    public Discount(WarehouseObjectType... wots){
        for (WarehouseObjectType wot : wots){
            discount.addSupply(wot);
        }
    }


    @Override
    public SupplyContainer getDiscount() throws UnsupportedOperationException {
        return discount;
    }
}
