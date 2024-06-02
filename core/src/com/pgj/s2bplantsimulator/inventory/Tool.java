package com.pgj.s2bplantsimulator.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class Tool extends Item {
    public Tool(String name, Image image, MainGame mainGame){
        super.setName(name);
        super.setMovingImage(image);
        super.setMainGame(mainGame);
    }

}
