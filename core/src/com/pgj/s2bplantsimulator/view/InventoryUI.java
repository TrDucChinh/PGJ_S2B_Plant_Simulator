package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
//        equipmentSlot.setDebug(true);


        for(int i = 0; i < 6; i++){
            Container imageContainer = new Container();
//            imageContainer.setDebug(true);
            imageContainer.setBackground(skin.getDrawable("round_button"));
            imageContainer.fill();
            equipmentSlot.add(imageContainer).pad(8.0f).size(50.0f);
            imageContainer.setWidth(50.0f);
            imageContainer.setHeight(50.0f);
        }
        for(Cell cell : equipmentSlot.getCells()){
            Container imageContainer = (Container) cell.getActor();
            System.out.println(imageContainer.getWidth() + " " + imageContainer.getHeight());
        }
        stage.addActor(equipmentSlot);

    }
    public void loadEquipmentItemToUI(){
        for(Item item  : equipment.getItems()){
            if(item != null){
                for(Cell cell : equipmentSlot.getCells()){
                    Container imageContainer = (Container) cell.getActor();
                    if(imageContainer.getActor() == null){
                        imageContainer.setActor(item.getImage());
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
                    @Override
                    public void dragStart(InputEvent event, float x, float y, int pointer) {
                        image.getParent().toFront();
                        preX = image.getX();
                        preY = image.getY();
                    }

                    @Override
                    public void drag(InputEvent event, float x, float y, int pointer) {
                        image.moveBy(x - image.getWidth() / 2, y - image.getHeight() / 2);
                    }

                    @Override
                    public void dragStop(InputEvent event, float x, float y, int pointer) {
                        boolean moving = false;
                        Container oldImageContainer = (Container) image.getParent();
                        for(Cell cell : equipmentSlot.getCells()){
                            Container imageContainer = (Container) cell.getActor();
                            if(imageContainer.getActor() == null){
                                if(image.getX() > imageContainer.getX() && image.getX() < imageContainer.getX() + imageContainer.getWidth() && image.getY() > imageContainer.getY() && image.getY() < imageContainer.getY() + imageContainer.getHeight()){
                                    image.remove();
                                    imageContainer.setActor(image);
                                    moving = true;
                                    break;
                                }
                            }
                        }
                        if(moving == false){
                            image.setPosition(preX, preY);
                        }
                    }
                });
            }
        }
    }
    public void update(){
//        equipmentSlot.getCells().get(0).setActor(new Image((Tool) (equipment.getItems()[0]).getDrawable()));
    }


}
