package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;
import com.pgj.s2bplantsimulator.controller.Random;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

public class Plant extends Item{
    private final int startPrice = Random.randomPrice(GameConstant.MIN_PRICE, GameConstant.MAX_PRICE);
    public Plant(String name, int quantity){
        super();
        setName(name);
        setBaseImage(new Image(ResourceLoader.getInstance().getSkin().getDrawable(name+ "_product")));
        setQuantity(quantity);
        // set quantity of plant = 1
        if (name.equals("corn")){
            setSellPrice(startPrice);
        } else if (name.equals("tomato")) {
            setSellPrice(startPrice * 75 / 100);
        }
    }
}
