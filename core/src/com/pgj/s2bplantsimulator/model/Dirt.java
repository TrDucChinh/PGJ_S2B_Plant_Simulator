package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.pgj.s2bplantsimulator.common.constant.GameConstant.PPM;

public class Dirt extends Sprite {
    public float xDirt, yDirt, width, height;


    public DirtState currentState = null;

    public Dirt() {
    }

    public Dirt(float x, float y, float width, float height, String texture) {
//        super(new Texture("Basic Charakter Spritesheet.png"));
//        TextureRegion[][] dirt = TextureRegion.split(getTexture(), 48, 48);
        this.xDirt = x;
        this.yDirt = y;
        this.width = width;
        this.height = height;
        setRegion(new Texture(texture));
        setBounds(x - width / PPM, y - height / PPM, 48 / 32f, 48 / 32f);
//        setPosition(x, y);
    }

    public void update(float dt) {
//        setPosition(xDirt - getWidth() / 2, yDirt - getHeight() / 2);
        setPosition(xDirt - 0.5f, yDirt - 0.5f);
    }


}
