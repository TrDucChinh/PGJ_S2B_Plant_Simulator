package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class SeedItem extends Item {
    public SeedItem(String name, int quantity, Image image, MainGame mainGame){
        super();
        setName(name);
        setMainGame(mainGame);
        setQuantity(quantity);
        int quantityLabelSum = this.getQuantity();
        while(quantityLabelSum >= 64){
            MovingImage movingImage = new MovingImage(image, this);
            movingImage.setQuantityLabel(64);
            quantityLabelSum -= 64;
            addMovingImage(movingImage);
        }
        if(quantityLabelSum > 0){
            MovingImage movingImage = new MovingImage(image, this);
            movingImage.setQuantityLabel(quantityLabelSum);
            addMovingImage(movingImage);
        }
    }
}
