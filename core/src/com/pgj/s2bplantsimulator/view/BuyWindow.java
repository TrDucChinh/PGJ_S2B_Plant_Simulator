package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

import java.awt.*;

public class BuyWindow extends ItemHolderBoard{
    private final float PANEL_WIDTH = 200;
    private final float PANEL_HEIGHT = 298;
    private float PANEL_POS_Y = Gdx.graphics.getHeight() / 2 - PANEL_HEIGHT / 2;
    private float PANEL_POS_X = Gdx.graphics.getWidth() / 2 + 382 / 2 + 20;
    private Skin skin = ResourceLoader.getInstance().getSkin();

    public BuyWindow(MainGame mainGame) {
        super(mainGame);
    }

    public void udpate(float dt){

    }
    public void show(){

        getItemPanel().setFillParent(false);
        getItemPanel().setSize(PANEL_WIDTH, PANEL_HEIGHT);
        getItemPanel().setPosition(PANEL_POS_X, PANEL_POS_Y);

        Table table = new Table();
        table.align(Align.left);
//        table.setFillParent(true);
//        table.align(Align.top);

        for(int i = 0; i < 100; i++){
            ImageTextButton imageTextButton = new ImageTextButton("Buy", skin);
            imageTextButton.setTransform(true);
            table.add(imageTextButton).padTop(10f).padLeft(5f).size(imageTextButton.getWidth(), imageTextButton.getHeight());
            table.row();
        }
        ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setFillParent(true);
        getItemPanel().add(scrollPane);
        getItemPanel().getCell(scrollPane).size(PANEL_WIDTH, PANEL_HEIGHT);


    }

    @Override
    public void resize(float width, float height) {

    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void initUI() {

    }
}
