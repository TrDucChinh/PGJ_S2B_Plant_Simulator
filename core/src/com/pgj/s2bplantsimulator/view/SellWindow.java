package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.inventory.Inventory;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class SellWindow extends ItemHolderBoard{
    private final float PANEL_WIDTH = 200;
    private final float PANEL_HEIGHT = 250;
    private final float PANEL_POS_Y = Gdx.graphics.getHeight() / 2 - PANEL_HEIGHT / 2;
    private final float PANEL_POS_X = Gdx.graphics.getWidth() / 2 - 382 / 2 - 20 - PANEL_WIDTH;
    private Label.LabelStyle labelStyle;
    private Inventory inventory;

    private Button decreaseQuantityButton;
    private Button increaseQuantityButton;
    private Button sellButton;
    private Label amountPriceLabel;
    private Label quantityLabel;
    private Label nameLabel;
    private Item itemToSell;
    private Integer quantityToSell;
    private MovingImageContainer itemToSellContainer;
    private MainGame mainGame;
    private int priceSell = 0;
    public SellWindow(MainGame mainGame) {
        super(mainGame);
        labelStyle = getSkin().get("text", Label.LabelStyle.class);
        inventory = mainGame.getPlayer().getInventory();
        this.mainGame = mainGame;
    }

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

        quantityLabel = new Label("Quantity: ", labelStyle);
        getItemPanel().row();
        getItemPanel().add(quantityLabel).padTop(10);
        getItemPanel().getCell(quantityLabel).size(160, quantityLabel.getHeight());

        amountPriceLabel = new Label("Price: ", labelStyle);
        getItemPanel().row();
        getItemPanel().add(amountPriceLabel).padTop(10);
        getItemPanel().getCell(amountPriceLabel).size(160, amountPriceLabel.getPrefHeight());

        Table sellButtonTable = new Table();
        sellButtonTable.align(Align.center);
        getItemPanel().row();
        getItemPanel().add(sellButtonTable).padTop(10f);

        decreaseQuantityButton = new Button(getSkin(), "button_decrease");
        decreaseQuantityButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(itemToSellContainer.getActor() != null){
                    MovingImage movingImage = (MovingImage) itemToSellContainer.getActor();
                    if(quantityToSell > 1){
                        quantityToSell--;
                    }
                }
            }
        });
        sellButtonTable.add(decreaseQuantityButton).pad(10f).size(15, 20);


        sellButton = new TextButton(" Sell ", getSkin());
        sellButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setOnSellButtonClick();
            }
        });
        sellButtonTable.add(sellButton);
        sellButtonTable.getCell(sellButton).size(60, sellButton.getHeight() - 5);


        increaseQuantityButton = new Button(getSkin(), "button_increase");
        increaseQuantityButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(itemToSellContainer.getActor() != null){
                    MovingImage movingImage = (MovingImage) itemToSellContainer.getActor();
                    if(quantityToSell < movingImage.getItem().getQuantity()){
                        quantityToSell++;
                    }
                }
            }
        });
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
        updateOnScreenLabel();
    }
    public void resetLabel(){
        itemToSell = null;
        quantityToSell = null;
        nameLabel.setText("Name: ");
        amountPriceLabel.setText("Price: ");
        quantityLabel.setText("Quantity: ");
    }

    @Override
    public void toggleVisible() {
        super.toggleVisible();
    }
    public void setVisible(boolean visible){
        getItemPanel().setVisible(visible);
    }
    public void updateOnScreenLabel(){
        if(getItemPanel().isVisible() == true){
            if(itemToSellContainer.getActor() != null){
                MovingImage movingImage = (MovingImage) itemToSellContainer.getActor();
                itemToSell = movingImage.getItem();
                if(quantityToSell == null){
                    quantityToSell = new Integer(movingImage.getQuantityLabel());
                }
                nameLabel.setText("Name: " + itemToSell.getName());
                amountPriceLabel.setText("Price: " + itemToSell.getPrice() * quantityToSell);
                this.priceSell = itemToSell.getPrice() * quantityToSell;
                quantityLabel.setText("Quantity: " + quantityToSell);
            }else{
                resetLabel();
            }
        }
    }
    public void setOnSellButtonClick(){
        if(itemToSell != null){
            inventory.getItems().get(itemToSell.getName()).setQuantity(itemToSell.getQuantity() - quantityToSell);
            itemToSellContainer.setActor(null);
            mainGame.getPlayer().setMoney(mainGame.getPlayer().getMoney() + priceSell);
            resetLabel();
        }
    }

}
