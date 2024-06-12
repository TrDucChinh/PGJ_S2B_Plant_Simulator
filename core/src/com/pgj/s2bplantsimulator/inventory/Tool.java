package com.pgj.s2bplantsimulator.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.model.Item;

public class Tool extends Item {
    public Tool(String name, int quantity, Image image, MainGame mainGame){
        super();
        setName(name);
        setMainGame(mainGame);
        setQuantity(quantity);
        for(int i = 0; i <  Math.ceil(Float.valueOf(getQuantity()) / 64); i++){
            addMovingImage(new MovingImage(image, this));
            System.out.println(getMovingImageList().get(i).hashCode());
        }
        System.out.println(this.getQuantity() + " " + getMovingImageList().size());

    }
}
