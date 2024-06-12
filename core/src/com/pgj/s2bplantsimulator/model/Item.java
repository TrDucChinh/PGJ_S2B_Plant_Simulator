package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.view.MovingImageContainer;

import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    private String name;
    private MainGame mainGame;
    private List<MovingImage> movingImageList;
    private MovingImageContainer selectedContainer;
    private int quantity;
    public boolean equals(String name){
        return this.name.equals(name);
    }
    public Item(){
        movingImageList = new ArrayList<>();
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


    public List<MovingImage> getMovingImageList(){
        return movingImageList;
    }
    public void addMovingImage(MovingImage MovingImage) {
        movingImageList.add(MovingImage);
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        MovingImage movingImage = getSelectedImage();
        if(movingImage != null) movingImage.setQuantityLabel(movingImage.getQuantityLabel() + quantity - this.quantity);
        this.quantity = quantity;
    }

    public void setSelectedContainer(MovingImageContainer selectedContainer) {
        this.selectedContainer = selectedContainer;
    }
    public MovingImage getSelectedImage(){
        if(selectedContainer == null) return null;
        Stack stack = selectedContainer.getStack();
        return (MovingImage)  stack.getChildren().get(0);
    }

    public void udpate(float dt){
        if(selectedContainer != null){
            selectedContainer.update(dt);
        }
    }
}
