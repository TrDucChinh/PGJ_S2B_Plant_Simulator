package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.view.MovingImageContainer;

import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    private String name;
    private List<MovingImage> movingImageList;
    private MovingImageContainer selectedContainer;
    private Image baseImage;
    private int quantity;
    private int price;
    public boolean equals(String name){
        return this.name.equals(name);
    }
    public Item(){
        movingImageList = new ArrayList<>();
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
        udpateMovingImageList(this.quantity, quantity);
        this.quantity = quantity;
    }
    public void udpateMovingImageList(int bef, int aft){
        int deltaQuantity = aft - bef;
        if(deltaQuantity > 0){
            while(deltaQuantity > 0){
                MovingImage movingImage;
                int quantityLabel;
                if(movingImageList.size() == 0){
                    movingImage = new MovingImage(baseImage,this);
                    quantityLabel = Math.min(64, deltaQuantity);
                    movingImageList.add(movingImage);
                }else{
                    movingImage = movingImageList.getLast();
                    if(movingImage.getQuantityLabel() < 64) {
                        quantityLabel = Math.min(64 - movingImage.getQuantityLabel(), deltaQuantity);
                    }else {
                        movingImage = new MovingImage(baseImage,this);
                        movingImageList.add(movingImage);
                        quantityLabel = Math.min(64, deltaQuantity);
                    }
                }
                movingImage.setQuantityLabel(movingImage.getQuantityLabel() + quantityLabel);
                deltaQuantity -= quantityLabel;
            }
        }else{
            while(deltaQuantity < 0){
                MovingImage movingImage = movingImageList.getLast();
                int quantityLabel = Math.min(-deltaQuantity, movingImage.getQuantityLabel());
                movingImage.setQuantityLabel(movingImage.getQuantityLabel() - quantityLabel);
                if(movingImage.getQuantityLabel() == 0){
                    movingImageList.remove(movingImage);
                }
                deltaQuantity += quantityLabel;
            }
        }
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

    public void setBaseImage(Image baseImage) {
        this.baseImage = baseImage;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return price;
    }
}
