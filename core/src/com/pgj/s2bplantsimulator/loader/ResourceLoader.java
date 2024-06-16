package com.pgj.s2bplantsimulator.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.model.Plant;

import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private ResourceLoader() {}
    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, Plant> plants = new HashMap<>();
    private static ResourceLoader resourceLoader;
    public static ResourceLoader getInstance() {
        if(resourceLoader == null){
            resourceLoader = new ResourceLoader();
        }
        return resourceLoader;
    }
    public Texture getTexture(String path){
        if(textures.containsKey(path)){
            return textures.get(path);
        }else{
            Texture texture = new Texture(path);
            textures.put(path, texture);
            return texture;
        }
    }

    public Skin getSkin() {
        return new Skin(Gdx.files.internal("Skin/ui_skin.json"));
    }
    public Item getPlant(String name){
        if(plants.containsKey(name) == false){
            Plant plant = new Plant(name, 0);
            plants.put(name, plant);
        }
        return plants.get(name);
    }
}
