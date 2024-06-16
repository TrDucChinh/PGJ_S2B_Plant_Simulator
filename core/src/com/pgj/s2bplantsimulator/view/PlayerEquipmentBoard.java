package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.inventory.Inventory;
import com.pgj.s2bplantsimulator.model.Item;
import com.pgj.s2bplantsimulator.model.MovingImage;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.StageUltis;


public class PlayerEquipmentBoard extends ItemHolderBoard {
    private int currentItemIndex;
    private Image selectedOverlay;
    private Inventory inventory;

    public PlayerEquipmentBoard(MainGame mainGame){
        super(mainGame);
        inventory = mainGame.getPlayer().getInventory();
        setItems(inventory.getItems());

    }
    private final float PANEL_WIDTH = 420;
    private final float PANEL_HEIGHT = 70;
    private final float PANEL_BOTTOM_PAD = 30.0f;
    @Override
    public void show() {
        initUI();
        updateItemPanel();
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
            MovingImageContainer imageContainer = new MovingImageContainer();
            imageContainer.setBackground(getSkin().getDrawable("round_button"));
            imageContainer.fill();
            getItemPanel().add(imageContainer).pad(8.0f).size(50.0f);
        }
        for(Cell cell : super.getItemPanel().getCells()) {
            Container imageContainer = (Container) cell.getActor();
        }
        selectedOverlay = new Image(getSkin().getDrawable("selected_overlay"));

        selectedOverlay.setBounds(-200, -200, 60, 60);
        selectedOverlay.setTouchable(Touchable.disabled);
        getStage().addActor(selectedOverlay);


    }


    public void resize(float width, float height){
        getItemPanel().setPosition(width / 2 - PANEL_WIDTH / 2, PANEL_BOTTOM_PAD);
    }
    public void update(float dt){
        super.update(dt);

        for(int i = 0; i < 6; i++){
            if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1 + i)){
                currentItemIndex = i;
            }
        }
        selectedOverlay.setPosition(StageUltis.getInstance().getStagePos(getItemPanel().getCells().get(currentItemIndex).getActor()).x - 4
                , StageUltis.getInstance().getStagePos(getItemPanel().getCells().get(currentItemIndex).getActor()).y - 5);
        Cell selectedCell = getItemPanel().getCells().get(currentItemIndex);
        MovingImageContainer selectedContainer = (MovingImageContainer) selectedCell.getActor();
        if(selectedContainer.getActor() == null) inventory.setCurrentItem(null);
        else {
            Item selectedItem = ((MovingImage)selectedContainer.getActor()).getItem();
            selectedItem.setSelectedContainer(selectedContainer);
            inventory.setCurrentItem( selectedItem);
        }
        if(inventory.getCurrentItem() != null) inventory.getCurrentItem().udpate(dt);
    }

    @Override
    public void updateItemPanel() {
        super.updateItemPanel();
    }
}
