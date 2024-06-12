package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class SeedItem extends Item {
    public SeedItem(String name, int quantity, Image image, MainGame mainGame){
        super();
        setName(name);
        setMainGame(mainGame);
        setQuantity(300);
        for(int i = 0; i < getQuantity() / 64; i++){
            addMovingImage(new MovingImage(image, this));
        }
    }
}
