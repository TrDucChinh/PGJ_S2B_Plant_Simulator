package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.screens.MainGame;

public class HUD implements Screen {
    private final Skin skin;
    private final Stage stage;
    private MainGame mainGame;
    private ChestBoard chestUI;
    private PlayerEquipmentBoard playerEquipmentUI;
    private SellWindow sellWindow;
    private BuyWindow buyWindow;
    private MoneyUI moneyUI;
    private SettingWindow settingWindow;



    public HUD(MainGame mainGame){
        this.mainGame = mainGame;
        stage = new Stage(new ScreenViewport());
//        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
    }
    public void show() {
        Gdx.input.setInputProcessor(stage);
        chestUI = new ChestBoard(mainGame);
        playerEquipmentUI = new PlayerEquipmentBoard(mainGame);
        sellWindow = new SellWindow(mainGame);
        buyWindow = new BuyWindow(mainGame);
        moneyUI = new MoneyUI(mainGame);
        settingWindow = new SettingWindow(mainGame);

        sellWindow.show();
        buyWindow.show();
        playerEquipmentUI.show();
        chestUI.show();
        moneyUI.show();
        settingWindow.show();
        settingWindow.setMainGame(mainGame);

    }

    public void render(float dt) {
        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        playerEquipmentUI.resize(width, height);
        chestUI.resize(width, height);

    }
    public void setUpPlayerData(){
        playerEquipmentUI.setItems(mainGame.getPlayer().getInventory().getItems());
        chestUI.setItems(mainGame.getPlayer().getInventory().getItems());
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

    public void update(float dt){

        playerEquipmentUI.update(dt);
        chestUI.update(dt);
        if(mainGame.getPlayer().isTrading()){
            sellWindow.setVisible(true);
            buyWindow.setVisible(true);
            chestUI.setVisibile(true);
        }else{
            sellWindow.setVisible(false);
            buyWindow.setVisible(false);
        }
        moneyUI.update();
        sellWindow.update(dt);
        buyWindow.update(dt);
        settingWindow.update(dt);
    }

    public Skin getSkin() {
        return skin;
    }

    public Stage getStage() {
        return stage;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public ChestBoard getChestUI() {
        return chestUI;
    }
}
