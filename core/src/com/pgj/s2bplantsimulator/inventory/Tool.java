package com.pgj.s2bplantsimulator.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.model.Item;

public class Tool extends Item {
    public Tool(String name, int quantity, Image image){
        super();
        setName(name);
        setBaseImage(image);
        setQuantity(quantity);
        setBuyPrice(250);
        setSellPrice(0);
    }
}
