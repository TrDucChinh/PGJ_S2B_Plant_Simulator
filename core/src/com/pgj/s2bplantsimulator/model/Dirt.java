package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pgj.s2bplantsimulator.loader.ResourceLoader;

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

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Dirt dirt = (Dirt) obj;

        // Compare relevant fields
        if (Float.compare(dirt.xDirt, xDirt) != 0) return false;
        if (Float.compare(dirt.yDirt, yDirt) != 0) return false;
        if (Float.compare(dirt.width, width) != 0) return false;
        if (Float.compare(dirt.height, height) != 0) return false;
        if (isDirt != dirt.isDirt) return false;
        if (isWatered != dirt.isWatered) return false;
        return isPlanted == dirt.isPlanted;

    }
    @Override
    public int hashCode() {
        int result = (xDirt != +0.0f ? Float.floatToIntBits(xDirt) : 0);
        result = 31 * result + (yDirt != +0.0f ? Float.floatToIntBits(yDirt) : 0);
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        result = 31 * result + (isDirt ? 1 : 0);
        result = 31 * result + (isWatered ? 1 : 0);
        result = 31 * result + (isPlanted ? 1 : 0);
        return result;
    }

    public void update(float dt) {
        setPosition(xDirt - 0.5f, yDirt - 0.5f);
    }


}
