package com.pgj.s2bplantsimulator.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.inventory.Tool;
import com.pgj.s2bplantsimulator.model.SeedItem;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentsLoader {
    private static EquipmentsLoader equipmentsLoader;
    private Map<String, Item> items;
    public static EquipmentsLoader getInstance(){
        if(equipmentsLoader == null){
            equipmentsLoader = new EquipmentsLoader();
        }
        return equipmentsLoader;
    }
    public Map<String, Item> load(MainGame mainGame){
        Skin skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
        items = new HashMap<>();
        items.put("Hoe", new Tool("Hoe", 1, new Image(skin.getDrawable("hoe"))));
        items.put("Watering Pot", new Tool("Watering Pot", 1, new Image(skin.getDrawable("water_pot"))));
        items.put("Axe", new Tool("Axe", 1, new Image(skin.getDrawable("axe"))));
        items.put("Corn Seed", new SeedItem("Corn Seed", 10, new Image(skin.getDrawable("corn_seed_equipment"))));
        items.put("Tomato Seed", new SeedItem("Tomato Seed", 10, new Image(skin.getDrawable("tomato_seed_equipment"))));
        return items;
    }
}
