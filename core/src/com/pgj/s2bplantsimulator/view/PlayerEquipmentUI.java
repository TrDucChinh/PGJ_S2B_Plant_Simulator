package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.screens.MainGame;


public class PlayerEquipmentUI extends ItemHolderUI {
    public PlayerEquipmentUI(MainGame mainGame){
        super(mainGame);
        setItems(mainGame.getPlayer().getEquipment().getItems());
    }
    private final float PANEL_WIDTH = 420;
    private final float PANEL_HEIGHT = 70;
    private final float PANEL_BOTTOM_PAD = 30.0f;
    @Override
    public void show() {
        initUI();
        initEquipmentItem();
    }

    @Override

    public void initUI(){
        getItemPanel().setName("Picker");
        getItemPanel().setTouchable(Touchable.enabled);
        getItemPanel().setFillParent(false);
        getItemPanel().align(Align.center);
        getItemPanel().setBackground(super.getSkin().getDrawable("window"));
        getItemPanel().setBounds(Gdx.graphics.getWidth() / 2 - PANEL_WIDTH / 2, PANEL_BOTTOM_PAD, PANEL_WIDTH, PANEL_HEIGHT);

        for(int i = 0; i < 6; i++){
            Container imageContainer = new Container();
            imageContainer.setBackground(super.getSkin().getDrawable("round_button"));
            imageContainer.fill();
            super.getItemPanel().add(imageContainer).pad(8.0f).size(50.0f);
        }
        for(Cell cell : super.getItemPanel().getCells()) {
            Container imageContainer = (Container) cell.getActor();
        }
    }
    public void initEquipmentItem(){
        for(Item item  : getItems()){
            if(item != null){
                for(Cell cell : getItemPanel().getCells()){
                    Container imageContainer = (Container) cell.getActor();
                    if(imageContainer.getActor() == null){
                        imageContainer.setActor(item.getMovingImage());
                        break;
                    }
                }
            }
        }
    }

    public void resize(float width, float height){
        super.getItemPanel().setPosition(width / 2 - PANEL_WIDTH / 2, PANEL_BOTTOM_PAD);
    }


}
