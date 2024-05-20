package com.pgj.s2bplantsimulator.inventory;

import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.loader.EquipmentsLoader;
import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class Equipment {
    private Item[] items;
    private int NUM_SLOTS = 6;
    private Player player;
    private Item currentItem;

    public Equipment(MainGame gameScreen){
        this.player = gameScreen.player;

        items = EquipmentsLoader.getInstance().loader(gameScreen);
    }



    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getNUM_SLOTS() {
        return NUM_SLOTS;
    }

    public void setNUM_SLOTS(int NUM_SLOTS) {
        this.NUM_SLOTS = NUM_SLOTS;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }
}
