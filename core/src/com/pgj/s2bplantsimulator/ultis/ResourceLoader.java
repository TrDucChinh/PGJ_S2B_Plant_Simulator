package com.pgj.s2bplantsimulator.ultis;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class ResourceLoader {
    private ResourceLoader() {}
    private static Map<String, Texture> textures = new HashMap<>();
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
}
