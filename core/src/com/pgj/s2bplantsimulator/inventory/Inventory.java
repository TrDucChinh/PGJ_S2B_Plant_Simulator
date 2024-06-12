package com.pgj.s2bplantsimulator.inventory;

import com.pgj.s2bplantsimulator.loader.EquipmentsLoader;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.model.Plant;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Item> items;
    private Item currentItem;

    public Inventory(MainGame mainGame){
        items = new HashMap<>();
        items = EquipmentsLoader.getInstance().load(mainGame);
    }
    public void setCurrentItem(Item item){
        this.currentItem = item;
    }
    public Map<String, Item> getItems() {
        return items;
    }

    public Item getCurrentItem() {
        return currentItem;
    }
    public void addPlant(String name, int quantity){
        if(items.containsKey(name) == false){
            Plant plant = new Plant(name, 0);
            items.put(name, plant);
        }
        items.get(name).setQuantity(items.get(name).getQuantity() + quantity);
    }
}
