package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class EquipmentSeed extends Item {
    public EquipmentSeed(String name, Image image, MainGame mainGame){
        super.setName(name);
        super.setMovingImage(image);
        super.setMainGame(mainGame);
    }
}
