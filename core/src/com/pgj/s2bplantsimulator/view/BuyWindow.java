package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.inventory.Inventory;
import com.pgj.s2bplantsimulator.loader.EquipmentsLoader;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

import java.util.*;

public class BuyWindow extends ItemHolderBoard{
    private final float PANEL_WIDTH = 200;
    private final float PANEL_HEIGHT = 298;
    private float PANEL_POS_Y = Gdx.graphics.getHeight() / 2 - PANEL_HEIGHT / 2;
    private float PANEL_POS_X = Gdx.graphics.getWidth() / 2 + 382 / 2 + 20;
    private Set<Item> selectedItems = new HashSet<>();
    private Set<Item> itemsToBuy = new HashSet<>();
    private Set<Item> displayedSellItems = new HashSet<>();
    private Skin skin = ResourceLoader.getInstance().getSkin();
    private Inventory inventory;
    Table buyTable;

    public BuyWindow(MainGame mainGame) {
        super(mainGame);
        inventory = mainGame.getPlayer().getInventory();
        Map<String, Item> items = EquipmentsLoader.getInstance().load(mainGame);
        for(Item item : items.values()){
            itemsToBuy.add(item);
        }
    }

    public void udpate(float dt){

    }
    public void show(){
        initUI();
        updateOnScreenBuyTable();
    }

    @Override
    public void resize(float width, float height) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void initUI() {
        getItemPanel().setFillParent(false);
        getItemPanel().setSize(PANEL_WIDTH, PANEL_HEIGHT);
        getItemPanel().setPosition(PANEL_POS_X, PANEL_POS_Y);
        getItemPanel().align(Align.top);

        buyTable = new Table();
        buyTable.align(Align.topLeft);
        buyTable.pad(15f);
//        buyTable.setFillParent(true);
//        buyTable.align(Align.top);
        Label label = new Label("SHOPPING", skin, "text");
        getItemPanel().add(label).padBottom(10f);



        ScrollPane scrollPane = new ScrollPane(buyTable, skin);
        scrollPane.setFillParent(true);
        scrollPane.setDebug(true);

        getItemPanel().row();
        getItemPanel().add(scrollPane);
        getItemPanel().getCell(scrollPane).size(PANEL_WIDTH, PANEL_HEIGHT);

        TextButton buyButton = new TextButton("Buy", skin, "default");
        buyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setOnBuyButtonClick();
            }
        });

        getItemPanel().row();
        getItemPanel().add(buyButton).size(50, buyButton.getHeight()).padTop(10f);

        setVisible(false);
    }
    public void setOnBuyButtonClick(){
        displayedSellItems.removeIf(item -> (selectedItems.contains(item)));
        for(Item item : selectedItems){
            inventory.addItem(item.getName(), item.getQuantity());
        }
        selectedItems.clear();
        updateOnScreenBuyTable();
    }
    public void updateOnScreenBuyTable(){
        buyTable.clear();
        displayedSellItems = new HashSet<>(itemsToBuy);
        for(Item item : displayedSellItems){
            String textLabel = new String(" " + item.getName() + "x" + item.getQuantity());

            ImageTextButton imageTextButton = new ImageTextButton(null, skin);
            imageTextButton.add(new Label(textLabel, skin));
//            imageTextButton.setDebug(true);
            imageTextButton.align(Align.left);
            imageTextButton.addListener(new ClickListener(){
                private boolean isChecked = false;
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    isChecked = !isChecked;
                    if(isChecked == true){
                        selectedItems.add(item);
                    }else{
                        selectedItems.remove(item);
                    }
                }
            });
            buyTable.add(imageTextButton).padBottom(10f).size(imageTextButton.getWidth(), imageTextButton.getHeight());
            buyTable.row();
        }
    }

    @Override
    public void toggleVisible() {
        super.toggleVisible();
    }


    public void setVisible(boolean visible){
        getItemPanel().setVisible(visible);
    }
}
