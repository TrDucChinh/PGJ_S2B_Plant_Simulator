package com.pgj.s2bplantsimulator.inventory;

import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.view.ChestUI;

import java.util.zip.CheckedInputStream;

public class Chest {
    private Item[] items;
    private final int NUM_SLOTS = 24;
    public Chest(MainGame mainGame){
        items = new Item[NUM_SLOTS];
    }

    public Item[] getItems() {
        return items;
    }
}
