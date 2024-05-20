package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pgj.s2bplantsimulator.inventory.Equipment;
import com.pgj.s2bplantsimulator.inventory.Inventory;
import com.pgj.s2bplantsimulator.inventory.Item;
import com.pgj.s2bplantsimulator.inventory.Tool;

public class InventoryUI implements Screen {
    private Skin skin;
    private Table equipmentSlot;
    private Stage stage;
    private Equipment equipment;

    public void show() {
        equipment = new Equipment();
        createBasicUI();
        System.out.println(equipment.getItems().length);
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
        equipmentSlot.setName("FRAME");
        equipmentSlot.setTouchable(Touchable.enabled);
        equipmentSlot.padTop(5.0f);
        equipmentSlot.padBottom(10.0f);
        equipmentSlot.align(Align.bottom);
        equipmentSlot.setFillParent(true);


        Container container = new Container();

        Table Frame = new Table();
        Frame.setTouchable(Touchable.enabled);

        Image image = new Image(skin, "round_button");
        Frame.add(image).pad(8.0f).size(50.0f);

        image = new Image(skin, "round_button");
        Frame.add(image).pad(8.0f).size(50.0f);

        image = new Image(skin, "round_button");
        Frame.add(image).pad(8.0f).size(50.0f);

        image = new Image(skin, "round_button");
        Frame.add(image).pad(8.0f).size(50.0f);

        image = new Image(skin, "round_button");
        Frame.add(image).pad(8.0f).size(50.0f);

        image = new Image(skin, "round_button");
        Frame.add(image).pad(8.0f).size(50.0f);
        container.setActor(Frame);
        container.setBackground(skin.getDrawable("window"));
        equipmentSlot.add(container).minSize(0.0f).maxSize(0.0f).prefWidth(420.0f).prefHeight(75.0f);
        stage.addActor(equipmentSlot);

        equipmentSlot = new Table();
        equipmentSlot.setName("Picker");
        equipmentSlot.setTouchable(Touchable.enabled);
        equipmentSlot.padRight(4.0f);
        equipmentSlot.padBottom(30.0f);
        equipmentSlot.align(Align.bottom);
        equipmentSlot.setFillParent(true);

        equipmentSlot.add().spaceLeft(30.0f).spaceRight(30.0f).prefSize(35.0f);

        equipmentSlot.add().spaceLeft(30.0f).spaceRight(30.0f).prefSize(35.0f);

        equipmentSlot.add().spaceLeft(30.0f).spaceRight(30.0f).prefSize(35.0f);

        equipmentSlot.add().spaceLeft(30.0f).spaceRight(30.0f).prefSize(35.0f);

        equipmentSlot.add().spaceLeft(30.0f).spaceRight(30.0f).prefSize(35.0f);

        equipmentSlot.add().spaceLeft(30.0f).spaceRight(30.0f).prefSize(35.0f);
        stage.addActor(equipmentSlot);

    }
    public void update(){
//        equipmentSlot.getCells().get(0).setActor(new Image((Tool) (equipment.getItems()[0]).getDrawable()));
        for(Item item : equipment.getItems()){
            if(item != null){

                for(Cell cell : equipmentSlot.getCells()){
                    if(cell.getActor() == null){
                        cell.setActor(item.getImage());
                        System.out.println("Here");
                        break;
                    }
                }
            }
        }
    }
}
