package com.pgj.s2bplantsimulator.inventory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;
public abstract class Item extends Sprite {
    private String name;
    private MainGame mainGame;
    private MovingImage movingImage;
    public boolean equals(String name){
        return this.name == name;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Image getMovingImage() {
        return movingImage;
    }

    public void setMovingImage(Image image) {
        this.movingImage = new MovingImage(image, this);
    }
}
