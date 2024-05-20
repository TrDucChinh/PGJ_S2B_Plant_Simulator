package com.pgj.s2bplantsimulator.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.inventory.Tool;

public class EquipmentsLoader {
    private static EquipmentsLoader equipmentsLoader;
    private Item[] items;
    private final int NUM_SLOTS = 6;
    public static EquipmentsLoader getInstance(){
        if(equipmentsLoader == null){
            equipmentsLoader = new EquipmentsLoader();
        }
        return equipmentsLoader;
    }
    public Item[] loader(){
        Skin skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
        items = new Item[NUM_SLOTS];
        items[0] = new Tool("Hoe", new Image(skin.getDrawable("hoe")));
        items[1] = new Tool("Watering Pot", new Image(skin.getDrawable("water_pot")));
        items[2] = new Tool("Axe", new Image(skin.getDrawable("axe")));
//        System.out.println(items[0].getName());
        return items;
    }
}
