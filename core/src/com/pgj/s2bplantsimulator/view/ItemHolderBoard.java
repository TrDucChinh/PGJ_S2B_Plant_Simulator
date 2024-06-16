package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

import java.util.List;
import java.util.Map;

public abstract class ItemHolderBoard implements UI {
    private Map<String, Item> items;
    private Table itemPanel;
    private HUD hud;
    private Skin skin;
    private Stage stage;
    public MainGame mainGame;
    public ItemHolderBoard(MainGame mainGame){
        this.hud = mainGame.getHud();
        this.skin = ResourceLoader.getInstance().getSkin();
        itemPanel = new Table();
        this.stage = hud.getStage();
        stage.addActor(itemPanel);
        updateItemPanel();
        this.mainGame = mainGame;
    }
    @Override
    public void update(float dt) {
        for(Cell cell : itemPanel.getCells()){
            if(cell.getActor() instanceof MovingImageContainer){
                MovingImageContainer movingImageContainer = (MovingImageContainer) cell.getActor();
                if(movingImageContainer.getActor() != null){
                    movingImageContainer.update(dt);
                }
            }

        }
        updateItemPanel();
    }
    public void updateItemPanel(){
        for(Cell cell : itemPanel.getCells()){
            if(cell.getActor() != null && cell.getActor() instanceof MovingImageContainer){
                MovingImageContainer movingImageContainer = (MovingImageContainer) cell.getActor();
                for(Item item : items.values()){
                    for(MovingImage movingImage : item.getMovingImageList()){

                        if(movingImage.getParent() == null){
                            movingImageContainer.setActor(movingImage);
                            break;
                        }
                    }
                    if(movingImageContainer.getActor() != null) break;
                }
            }
        }

    }


    public abstract void initUI();
    public abstract void show();

    public void setItems(Map<String, Item> items) {
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
    public void setVisibile(boolean visible){
        itemPanel.setVisible(visible);
    }
    public boolean isVisible(){
        return itemPanel.isVisible();
    }

    public Table getItemPanel() {
        return itemPanel;
    }
}
