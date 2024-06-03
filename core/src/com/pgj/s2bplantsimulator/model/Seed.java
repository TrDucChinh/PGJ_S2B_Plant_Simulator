package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

import static com.pgj.s2bplantsimulator.common.constant.GameConstant.*;

public class Seed extends Sprite {
    public float xSeed, ySeed, width, height;
    public float age = 0;
    public Dirt dirt;
    public float timeToGrow = 0;
    public boolean harvestable = false;
    public float maxAge = 0;
    public String name;

    public Seed() {
    }

    public Seed(float x, float y, float width, float height, String texture, String name) {
        this.name = name;
        this.xSeed = x;
        this.ySeed = y;
        this.width = width;
        this.height = height;
        this.age = 0;
        this.timeToGrow = GROW_SPEED.get(name);
        this.maxAge = MAX_AGE.get(name);
        this.harvestable = false;

        setRegion(ResourceLoader.getInstance().getTexture(texture));
        setBounds(x - width / PPM, y - height / PPM, 48 / 32f, 48 / 32f);
    }

    public void update(float dt) {
        setPosition(xSeed - 0.5f, ySeed - 0.5f);
        age += timeToGrow * dt;
        if (age >= maxAge) {
            age = maxAge;
            harvestable = true;
            System.out.println("Harvestable");
        } else if (age >= 0 && age < 0.25 * maxAge) {
            setRegion(ResourceLoader.getInstance().getTexture("fruit/" + name + "/0.png"));
        } else if (age >= 0.25 * maxAge && age < 0.5 * maxAge) {
            setRegion(ResourceLoader.getInstance().getTexture("fruit/" + name + "/1.png"));
        } else if (age >= 0.5 * maxAge && age < 0.75 * maxAge) {
            setRegion(ResourceLoader.getInstance().getTexture("fruit/" + name + "/2.png"));
        } else if (age >= 0.75 * maxAge && age <= maxAge) {
            setRegion(ResourceLoader.getInstance().getTexture("fruit/" + name + "/3.png"));
        }

    }
}
