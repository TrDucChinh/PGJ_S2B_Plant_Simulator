package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.pgj.s2bplantsimulator.screens.MainGame;

import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    private String name;
    private MainGame mainGame;
    private List<MovingImage> movingImageList;
    private int quantity;
    private Drawable drawable;
    public boolean equals(String name){
        return this.name.equals(name);
    }
    public Item(){
        movingImageList = new ArrayList<>();

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

    public MovingImage getMovingImage(){
        return movingImageList.get(movingImageList.size() - 1);
    }

    public List<MovingImage> getMovingImageList(){
        return movingImageList;
    }
    public void addMovingImage(Image image) {
        movingImageList.add(new MovingImage(image, this));
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
