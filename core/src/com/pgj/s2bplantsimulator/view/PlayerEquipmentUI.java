package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.inventory.Equipment;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.inventory.Tool;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.StageUltis;


public class PlayerEquipmentUI extends ItemHolderUI {
    private Equipment equipment;
    private int currentItemIndex;
    private Image selectedOverlay;
    public PlayerEquipmentUI(MainGame mainGame){
        super(mainGame);
        equipment = mainGame.getPlayer().getEquipment();

        setItems(equipment.getItems());
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
        getItemPanel().setBackground(getSkin().getDrawable("window"));
        getItemPanel().setBounds(Gdx.graphics.getWidth() / 2 - PANEL_WIDTH / 2, PANEL_BOTTOM_PAD, PANEL_WIDTH, PANEL_HEIGHT);

        for(int i = 0; i < 6; i++){
            Container imageContainer = new Container();
            imageContainer.setBackground(getSkin().getDrawable("round_button"));
            imageContainer.fill();
            getItemPanel().add(imageContainer).pad(8.0f).size(50.0f);
        }
        for(Cell cell : super.getItemPanel().getCells()) {
            Container imageContainer = (Container) cell.getActor();
        }
        selectedOverlay = new Image(getSkin().getDrawable("selected_overlay"));
        selectedOverlay.setBounds(0, 0, 50, 50);
        getStage().addActor(selectedOverlay);
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
    public void update(float dt){
        super.update(dt);
        if(getItems()[currentItemIndex] == null){
            equipment.setCurrentItem(null);
        }
        for(int i = 0; i < 6; i++){
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1 + i)){
                equipment.setCurrentItem(getItems()[i]);
                MovingImage movingImage = (MovingImage) ((Container) getItemPanel().getCells().get(i).getActor()).getActor();
                selectedOverlay.setPosition(StageUltis.getInstance().getStagePos(getItemPanel().getCells().get(i).getActor()).x + 1
                        , StageUltis.getInstance().getStagePos(getItemPanel().getCells().get(i).getActor()).y);
                currentItemIndex = i;
            }
        }
    }


}
