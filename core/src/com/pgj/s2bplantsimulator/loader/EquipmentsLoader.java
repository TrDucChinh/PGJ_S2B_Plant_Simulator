package com.pgj.s2bplantsimulator.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.inventory.Tool;
import com.pgj.s2bplantsimulator.model.SeedItem;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;
import java.util.List;

public class EquipmentsLoader {
    private static EquipmentsLoader equipmentsLoader;
    private List<Item> items;
    private MainGame mainGame;
    public static EquipmentsLoader getInstance(){
        if(equipmentsLoader == null){
            equipmentsLoader = new EquipmentsLoader();
        }
        return equipmentsLoader;
    }
    public List<Item> load(MainGame mainGame){
        this.mainGame = mainGame;
        Skin skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
        items = new ArrayList<>();
        items.add(new Tool("Hoe", 1, new Image(skin.getDrawable("hoe")), this.mainGame));
        items.add(new Tool("Watering Pot", 1, new Image(skin.getDrawable("water_pot")), this.mainGame));
        items.add(new Tool("Axe", 1, new Image(skin.getDrawable("axe")), this.mainGame));
        items.add(new SeedItem("Corn Seed", 300, new Image(skin.getDrawable("corn_seed_equipment")), this.mainGame));
        return items;
    }
}
