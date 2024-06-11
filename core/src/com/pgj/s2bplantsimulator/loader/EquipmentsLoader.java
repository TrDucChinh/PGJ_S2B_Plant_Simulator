package com.pgj.s2bplantsimulator.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.inventory.Tool;
import com.pgj.s2bplantsimulator.model.EquipmentSeed;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.model.Seed;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;

public class EquipmentsLoader {
    private static EquipmentsLoader equipmentsLoader;
    private Item[] items;
    private final int NUM_SLOTS = 6;
    private MainGame mainGame;
    public static EquipmentsLoader getInstance(){
        if(equipmentsLoader == null){
            equipmentsLoader = new EquipmentsLoader();
        }
        return equipmentsLoader;
    }
    public Item[] load(MainGame mainGame){
        this.mainGame = mainGame;
        Skin skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
        items = new Item[NUM_SLOTS];
        items[0] = new Tool("Hoe",  new Image(skin.getDrawable("hoe")), this.mainGame);
        items[1] = (new Tool("Watering Pot",  new Image(skin.getDrawable("water_pot")), this.mainGame));
        items[2] = (new Tool("Axe",  new Image(skin.getDrawable("axe")), this.mainGame));
        items[3] = new EquipmentSeed("Corn Seed", new Image(skin.getDrawable("corn_seed_equipment")), this.mainGame);
        return items;
    }
}
