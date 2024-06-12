package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

public class Plant extends Item{
    public Plant(String name, int quantity){
        super();
        setName(name);
        setBaseImage(new Image(ResourceLoader.getInstance().getSkin().getDrawable(name+ "_product")));
        setQuantity(quantity);
        setName(name);
    }
}
