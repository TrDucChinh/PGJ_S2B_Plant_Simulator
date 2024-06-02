package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pgj.s2bplantsimulator.inventory.Equipment;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class InventoryUI implements Screen {
    private Skin skin;
    private Table equipmentSlot;
    private Stage stage;
    private Equipment equipment;
    private MainGame gameScreen;

    public InventoryUI(MainGame gameScreen){
        this.gameScreen = gameScreen;
        equipment = gameScreen.player.equipment;
        createBasicUI();
        createActionForItems();
        loadEquipmentItemToUI();
    }
    public void show() {
        // luu tam thoi



    }

    public void render(float dt) {

        update();
        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    @Override
    public void pause() {

    }


    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void createBasicUI(){
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
        Gdx.input.setInputProcessor(stage);

        equipmentSlot = new Table();
        equipmentSlot.setName("Picker");
        equipmentSlot.setTouchable(Touchable.enabled);

        equipmentSlot.setFillParent(false);
        equipmentSlot.align(Align.center);
        equipmentSlot.setBackground(skin.getDrawable("window"));
        equipmentSlot.setBounds(Gdx.graphics.getWidth() / 2 - 420 / 2, 30, 420, 70);
        equipmentSlot.setDebug(false);



        for(int i = 0; i < 6; i++){
            Container imageContainer = new Container();
            imageContainer.setDebug(false);
            imageContainer.setBackground(skin.getDrawable("round_button"));
            imageContainer.fill();
            equipmentSlot.add(imageContainer).pad(8.0f).size(50.0f);
            imageContainer.setWidth(50.0f);
            imageContainer.setHeight(50.0f);
        }
        for(Cell cell : equipmentSlot.getCells()) {
            Container imageContainer = (Container) cell.getActor();
//            System.out.println(imageContainer.getX() + " " + imageContainer.getY());
        }

        stage.addActor(equipmentSlot);
    }
    public void loadEquipmentItemToUI(){
        for(Item item  : equipment.getItems()){
            if(item != null){
                for(Cell cell : equipmentSlot.getCells()){
                    Container imageContainer = (Container) cell.getActor();
                    if(imageContainer.getActor() == null){
//                        System.out.println(item.getImage().getOriginX() + " " item.getImage().getOriginX());
                        imageContainer.setActor(item.getImage());
                        item.getImage().setDebug(false);
                        break;
                    }
                }
            }
        }
    }
    public void createActionForItems(){
        for(Item item : equipment.getItems()){
            if(item != null){
                Image image = item.getImage();

                image.addListener(new DragListener(){
                    private float preX;
                    private float preY;
                    private Container baseImageContainer;
                    @Override
                    public void dragStart(InputEvent event, float x, float y, int pointer) {
//                        image.setScaling(Scaling.fit);
                        baseImageContainer = (Container) image.getParent();
                        stage.addActor(image);
                        image.setBounds(event.getStageX(), event.getStageY(), image.getWidth(), image.getHeight());
//                        image.getParent().clear();
                        preX = x;
                        preY = y;

                    }

                    @Override
                    public void drag(InputEvent event, float x, float y, int pointer) {

                        image.moveBy(x, y);
                        preX = x;
                        preY = y;
                    }

                    @Override
                    public void dragStop(InputEvent event, float x, float y, int pointer) {
                        boolean isMoving = false;
                        for(Cell  cell : equipmentSlot.getCells()){
                            Container imageContainer = (Container) cell.getActor();
                            if(imageContainer.getActor() == null){
                                Vector2 pos = getStagePos(imageContainer);
                                System.out.println(pos.x + " " + pos.y + " " + event.getStageX() + " " + event.getStageY());
                                if((pos.x < event.getStageX())
                                        && (event.getStageX() <= pos.x + 50)
                                        && (pos.y <= event.getStageY())
                                        && (event.getStageY() <= pos.y + 50)){
                                    System.out.println("here");
                                    imageContainer.setActor(image);
                                    image.setPosition(0, 0);

                                    isMoving = true;
                                }
                            }
                        }
                        if(isMoving == false){
                            baseImageContainer.setActor(image);
                        }
                    }
                });
            }
        }
    }
    public Vector2 getStagePos(Actor actor){
        float x = actor.getX(), y = actor.getY();
        while(actor.getParent() != null){
            x += actor.getParent().getX();
            y += actor.getParent().getY();
            actor = actor.getParent();
        }
        return new Vector2(x, y);
    }
    public void update(){
//        equipmentSlot.getCells().get(0).setActor(new Image((Tool) (equipment.getItems()[0]).getDrawable()));
    }


}
