package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class ChestBoard extends ItemHolderBoard {
    private final int PANEL_WIDTH = 382;
    private final int PANEL_HEIGHT = 258;
    private boolean visible = false;
    public ChestBoard(MainGame mainGame){
        super(mainGame);
        setItems(mainGame.getPlayer().getInventory().getItems());
    }

    @Override
    public void initUI() {
        getItemPanel().setFillParent(false);
        getItemPanel().setBounds(Gdx.graphics.getWidth() / 2 - PANEL_WIDTH / 2,
                Gdx.graphics.getHeight() / 2 - PANEL_HEIGHT / 2,
                PANEL_WIDTH,
                PANEL_HEIGHT);
        getItemPanel().setBackground(getSkin().getDrawable("window"));
        for(int i = 0; i < 24; i++){
            MovingImageContainer container = new MovingImageContainer();
            container.setBackground(getSkin().getDrawable("round_button"));
            container.fill();
            getItemPanel().add(container).size(50.0f).pad(6.0f);
            container.setBounds(0, 0,30.0f, 30.0f);
            if(i % 6 == 5){
                getItemPanel().row();
            }
        }
        getStage().addActor(getItemPanel());
        toggleVisible();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)){
            toggleVisible();
        }
    }

    @Override
    public void show() {
        initUI();
        initEquipmentItem();
    }

    @Override
    public void resize(float width, float height) {
        getItemPanel().setPosition(width / 2 - PANEL_WIDTH / 2, height / 2 - PANEL_HEIGHT / 2);
    }
}

