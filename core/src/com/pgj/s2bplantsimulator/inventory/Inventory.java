package com.pgj.s2bplantsimulator.inventory;

import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.ultis.PlantUltis;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    Item currentItem;
    Player holder;
    public void setCurrentItem(Item item){
        this.currentItem = item;
    }
    public Inventory(Player holder){
        items = new ArrayList<>();
        this.holder = holder;

    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public void loadItems(PlantUltis plantUltis){
        for(int i = 0; i < 10; i++){
            Plant plant = plantUltis.createPlant(0);
            items.add(plant);
        }
    }
    public void addItem(Item item){
        items.add(item);
    }
}
