package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

public class SellWindow extends ItemHolderBoard{
    private final float PANEL_WIDTH = 200;
    private final float PANEL_HEIGHT = 250;
    private final float PANEL_POS_Y = Gdx.graphics.getHeight() / 2 - PANEL_HEIGHT / 2;
    private final float PANEL_POS_X = Gdx.graphics.getWidth() / 2 - 382 / 2 - 20 - PANEL_WIDTH;
    private Label.LabelStyle labelStyle;

    Button decreaseQuantityButton;
    Button increaseQuantityButton;
    Button sellButton;
    public SellWindow(MainGame mainGame) {
        super(mainGame);
        labelStyle = getSkin().get("text", Label.LabelStyle.class);
    }
    private Label nameLabel;
    private Label priceLabel;
    private Label quantityLabel;
    private Item itemToSell;
    private MovingImageContainer itemToSellContainer;
    @Override
    public void initUI() {
        getItemPanel().setFillParent(false);
        getItemPanel().setSize(PANEL_WIDTH, PANEL_HEIGHT);
        getItemPanel().setPosition(PANEL_POS_X, PANEL_POS_Y);
        getItemPanel().setBackground(getSkin().getDrawable("window"));
        getItemPanel().align(Align.top);

        Label titleLabel = new Label("Item information", labelStyle);
        getItemPanel().add(titleLabel).padTop(20);


        itemToSellContainer = new MovingImageContainer();
        itemToSellContainer.setBackground(getSkin().getDrawable("round_button"));
        itemToSellContainer.fill();
        getItemPanel().row();
        getItemPanel().add(itemToSellContainer).size(70, 70).padTop(10);

        nameLabel = new Label("Name: ", labelStyle);
        nameLabel.setAlignment(Align.left);
        nameLabel.setSize(180, nameLabel.getHeight());
        nameLabel.setWrap(true);
        getItemPanel().row();
        getItemPanel().add(nameLabel).padTop(10);
        getItemPanel().getCell(nameLabel).size(160, nameLabel.getHeight());

        Label quantityLabel = new Label("Quantity: ", labelStyle);
        getItemPanel().row();
        getItemPanel().add(quantityLabel).padTop(10);
        getItemPanel().getCell(quantityLabel).size(160, quantityLabel.getHeight());

        priceLabel = new Label("Price: ", labelStyle);
        getItemPanel().row();
        getItemPanel().add(priceLabel).padTop(10);
        getItemPanel().getCell(priceLabel).size(160, priceLabel.getPrefHeight());

        Table sellButtonTable = new Table();
        sellButtonTable.align(Align.center);
        getItemPanel().row();
        getItemPanel().add(sellButtonTable).padTop(10f);

        decreaseQuantityButton = new Button(getSkin(), "button_decrease");
        sellButtonTable.add(decreaseQuantityButton).pad(10f).size(15, 20);


        sellButton = new TextButton(" Sell ", getSkin());
        sellButtonTable.add(sellButton);
        sellButtonTable.getCell(sellButton).size(60, sellButton.getHeight() - 5);


        increaseQuantityButton = new Button(getSkin(), "button_increase");
        sellButtonTable.add(increaseQuantityButton).pad(10f).size(15, 20);
        toggleVisible();
    }

    @Override
    public void show() {
        initUI();

    }

    @Override
    public void resize(float width, float height) {

    }
    public void update(float dt){
        updateOnScreenSellTable();
    }
    public void resetLabel(){
        nameLabel.setText("Name: ");
    }

    @Override
    public void toggleVisible() {
        super.toggleVisible();
    }
    public void setVisible(boolean visible){
        getItemPanel().setVisible(visible);
    }
    public void updateOnScreenSellTable(){
        if(itemToSell != null){
            itemToSellContainer.setActor(itemToSell.getMovingImageList().get(0));
            nameLabel.setText("Name: " + itemToSell.getName());
            priceLabel.setText("Price: " + itemToSell.getPrice());
            quantityLabel.setText("Quantity: " + itemToSell.getMovingImageList().get(0).getQuantityLabel());
        }
    }

}
