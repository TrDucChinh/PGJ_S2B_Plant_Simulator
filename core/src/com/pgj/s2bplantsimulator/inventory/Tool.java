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
        MovingImage movingImage = new MovingImage(image, this);
        movingImage.setQuantityLabel(1);
        addMovingImage(movingImage);
    }
}
