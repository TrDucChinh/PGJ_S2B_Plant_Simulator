package com.pgj.s2bplantsimulator.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;
import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class Transition {
    private SpriteBatch batch;
    private Player player;
    private Texture image;
    private int color;
    private int speed;
    private MainGame mainGame;

    public Transition(Player player) {
        // setup
        this.batch = new SpriteBatch();
        this.player = player;

        // overlay image
        Pixmap pixmap = new Pixmap(GameConstant.WINDOW_WIDTH, GameConstant.WINDOW_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        this.image = new Texture(pixmap);
        pixmap.dispose();

        this.color = 255;
        this.speed = -6;
    }

    public void play() {
        this.color += this.speed;
        if (this.color <= 0) {
            this.speed *= -1;
            this.color = 0;
        }
        if (this.color > 255) {
            this.color = 255;
            this.player.setSleep(false);
            this.speed = -2;
        }

        // Update the color of the image
        Pixmap pixmap = new Pixmap(GameConstant.WINDOW_WIDTH, GameConstant.WINDOW_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(this.color / 255f, this.color / 255f, this.color / 255f, 1));
        pixmap.fill();
        this.image.dispose();
        this.image = new Texture(pixmap);
        pixmap.dispose();

        // Draw the image
        batch.begin();
        batch.draw(this.image, 0, 0);
        batch.end();
    }
}


