package com.pgj.s2bplantsimulator.ultis;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;
import com.pgj.s2bplantsimulator.controller.BodyHelperService;
import com.pgj.s2bplantsimulator.inventory.Inventory;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.inventory.Plant;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;

public class PlantUltis {

    ArrayList<Plant> sourcePlants;
    MainGame game;

    public  PlantUltis(){
        loadPlants();
    }
    public void updatePlantInInventory(Inventory inventory, float deltaTime){
        for(Item item : inventory.getItems()){
            if(item instanceof Plant){
                ((Plant) item).update(deltaTime);
            }
        }
    }
    public void drawPlantInInventory(Inventory inventory, SpriteBatch batch){
        for(Item item : inventory.getItems()){
            if(item instanceof Plant){
                ((Plant) item).draw(batch);
            }
        }
    }
    public Plant createPlant(int index){
        Plant plant = new Plant();
        Plant sourcePlant = sourcePlants.get(index);
        plant.setProperties(sourcePlant.getName(), sourcePlant.getPricePerItem(), sourcePlant.getTimeToCollect());
        return plant;
    }

    public void loadPlants(){
        Plant plant = new Plant();
        plant.setProperties("Carrot", 20, 300);
        plant.setTexture(new Texture("sprites_basic_pack/Objects/Basic Plants/Basic_Plants_01.png"));
        sourcePlants.add(plant);
    }

}

