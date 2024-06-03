package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

import static com.pgj.s2bplantsimulator.common.constant.GameConstant.PPM;

public class Dirt extends Sprite {
    public float xDirt, yDirt, width, height;
    public boolean isDirt = false;
    public boolean isWatered = false;
    public boolean isPlanted = false;


    public Dirt() {
    }

    public Dirt(float x, float y, float width, float height, String texturePath, boolean isDirt, boolean isWatered, boolean isPlanted) {
        this.isDirt = isDirt;
        this.isWatered = isWatered;
        this.isPlanted = isPlanted;

        this.xDirt = x;
        this.yDirt = y;
        this.width = width;
        this.height = height;
        setRegion(ResourceLoader.getInstance().getTexture(texturePath));
        setBounds(x - width / PPM, y - height / PPM, 48 / 32f, 48 / 32f);
    }

    public void update(float dt) {
        setPosition(xDirt - 0.5f, yDirt - 0.5f);
    }


}
