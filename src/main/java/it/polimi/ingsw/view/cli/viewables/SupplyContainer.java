package it.polimi.ingsw.view.cli.viewables;

import it.polimi.ingsw.model.WarehouseObjectType;
import it.polimi.ingsw.view.cli.Viewable;

import java.util.HashMap;

public class SupplyContainer implements Viewable {

    private HashMap<WarehouseObjectType, Integer> items = new HashMap<>();
    private String name;

    SupplyContainer(String name){
        this.name = name;
        items.put(WarehouseObjectType.COIN, 0);
        items.put(WarehouseObjectType.SERVANT, 0);
        items.put(WarehouseObjectType.SHIELD, 0);
        items.put(WarehouseObjectType.STONE, 0);
    }

    @Override
    public void update(int[] update) {
        items.put(WarehouseObjectType.COIN, update[0]);
        items.put(WarehouseObjectType.SERVANT, update[1]);
        items.put(WarehouseObjectType.SHIELD, update[2]);
        items.put(WarehouseObjectType.STONE, update[3]);
    }



    @Override
    public String toString() {
        return name + " --> COIN: " + items.get(WarehouseObjectType.COIN) + " SERVANT: " + items.get(WarehouseObjectType.SERVANT) + " SHIELD: " + items.get(WarehouseObjectType.SHIELD) + " STONE: " + items.get(WarehouseObjectType.STONE);
    }

}
