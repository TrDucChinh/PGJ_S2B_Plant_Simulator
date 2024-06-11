package com.pgj.s2bplantsimulator.inventory;

import com.pgj.s2bplantsimulator.loader.EquipmentsLoader;
import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;

public class Equipment {
    private Item[] items;
    public final int NUM_SLOTS = 6;
    private Item currentItem;

    public Equipment(MainGame gameScreen){
        items = EquipmentsLoader.getInstance().load(gameScreen);
    }

    public Item[] getItems() {
        return items;
    }

    public void setCurrentItem(Item item) {
        currentItem = item;
    }

    public Item getCurrentItem() {
        return currentItem;
    }
}
