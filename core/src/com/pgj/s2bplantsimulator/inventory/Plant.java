package com.pgj.s2bplantsimulator.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;
import com.pgj.s2bplantsimulator.controller.BodyHelperService;


import static com.pgj.s2bplantsimulator.inventory.Plant.PlantState.COLLECTIBLE;
import static com.pgj.s2bplantsimulator.inventory.Plant.PlantState.SEED;

public class Plant extends Item{
    public enum PlantState {GROWING, SEED, COLLECTIBLE}
    private int size = 16;
    private PlantState plantState = SEED;
    private String name;
    private int pricePerItem;
    private int quantity;
    private float timeToCollect;
    private float timeElapsed;
    private Body body;

    public void setProperties(String name, int pricePerItem, float timeToCollect){
        this.name = name;
        this.pricePerItem = pricePerItem;
        this.timeToCollect = timeToCollect;
    }
    public void update(float dt){

            switch (plantState){
                case SEED:

                case GROWING:
                    timeElapsed += dt;
                    if(timeElapsed >= timeToCollect){
                        plantState = COLLECTIBLE;
                    }
                case COLLECTIBLE:

            }

    }
    public void draw(){

    }

    public PlantState getPlantState() {
        return plantState;
    }

    public void setPlantState(PlantState plantState) {
        this.plantState = plantState;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(int pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTimeToCollect() {
        return timeToCollect;
    }

    public void setTimeToCollect(float timeToCollect) {
        this.timeToCollect = timeToCollect;
    }

    public float getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(float timeElapsed) {
        this.timeElapsed = timeElapsed;
    }


    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
