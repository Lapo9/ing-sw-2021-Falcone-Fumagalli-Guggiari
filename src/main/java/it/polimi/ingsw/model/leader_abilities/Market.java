package it.polimi.ingsw.model.leader_abilities;

import it.polimi.ingsw.model.LeaderAbility;
import it.polimi.ingsw.model.WarehouseObjectType;


/**
 *  This implementation of LeaderAbility only supports the getWhiteBall method, to transform a white marble
 */
public class Market implements LeaderAbility {

    private final WarehouseObjectType transformTo;


    public Market(WarehouseObjectType transformTo){
        this.transformTo = transformTo;
    }


    @Override
    public WarehouseObjectType transformWhiteMarble() throws UnsupportedOperationException {
        return transformTo;
    }
}
