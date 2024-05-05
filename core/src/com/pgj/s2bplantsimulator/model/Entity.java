package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.graphics.Texture;

public class Entity {
    private float locationX, locationY;
    private float width, height;
    private boolean passable;
    private Texture texture;

    public Entity() {
        this.width = 0;
        this.height = 0;
        this.passable = false;
    }
    public void setPosition(float locationX, float locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }


    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
