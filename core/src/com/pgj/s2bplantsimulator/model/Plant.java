package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;
import com.pgj.s2bplantsimulator.controller.RandomPrice;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

public class Plant extends Item{
    private final int startPrice = RandomPrice.randomPrice(GameConstant.minPrice, GameConstant.maxPrice);
    public Plant(String name, int quantity){
        super();
        setName(name);
        setBaseImage(new Image(ResourceLoader.getInstance().getSkin().getDrawable(name+ "_product")));
        setQuantity(quantity);
        // set quantity of plant = 1
        if (name.equals("corn")){
            setPrice(startPrice);
        } else if (name.equals("tomato")) {
            setPrice(startPrice * 75 / 100);
        }
    }
}
