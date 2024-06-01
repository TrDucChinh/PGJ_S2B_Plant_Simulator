package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;

public abstract class ItemHolderUI implements UI {
    private Item[] items;
    private Table itemPanel;
    private HUD hud;
    private Skin skin;
    private Stage stage;
    public ItemHolderUI(MainGame mainGame){
        this.hud = mainGame.getHud();
        this.skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
        itemPanel = new Table();
        this.stage = hud.getStage();
        stage.addActor(itemPanel);
    }
    @Override
    public void update(float dt) {
        for(int i = 0; i < items.length; i++){
            Container imageContainer = (Container) itemPanel.getCells().get(i).getActor();
            if(imageContainer.getActor() == null){
                items[i] = null;
            }else{
                MovingImage movingImage = (MovingImage) imageContainer.getActor();
                items[i] = movingImage.getItem();
            }
        }
    }


    public abstract void initUI();
    public abstract void show();

    public Table getItemPanel() {
        return itemPanel;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Skin getSkin() {
        return skin;
    }

    public Stage getStage() {
        return stage;
    }
    public void toggleVisible() {
        itemPanel.setVisible(!itemPanel.isVisible());
    }
}
