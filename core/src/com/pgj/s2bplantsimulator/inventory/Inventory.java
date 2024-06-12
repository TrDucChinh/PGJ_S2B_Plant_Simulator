package com.pgj.s2bplantsimulator.inventory;

import com.pgj.s2bplantsimulator.loader.EquipmentsLoader;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private Item currentItem;

    public Inventory(MainGame mainGame){
        items = new ArrayList<>();
        items = EquipmentsLoader.getInstance().load(mainGame);
    }
    public void setCurrentItem(Item item){
        this.currentItem = item;
    }
    public List<Item> getItems() {
        return items;
    }

    public Item getCurrentItem() {
        return currentItem;
    }
}
