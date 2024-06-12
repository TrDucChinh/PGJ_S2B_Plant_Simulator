package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class SeedItem extends Item {
    public SeedItem(String name, int quantity, Image image){
        super();
        setName(name);
        setBaseImage(image);
        setQuantity(quantity);
    }
}
