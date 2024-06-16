package com.pgj.s2bplantsimulator.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.screens.CreditScreen;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.screens.MenuGame;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

public class SettingWindow implements UI{
    private Stage stage;
    private Table panel;
    private MenuGame menuGame;
    private HUD hud;
    private S2BPlantSimulator game;
    private final int PANEL_WIDTH = 200;
    private final int PANEL_HEIGHT = 140;
    private Skin skin;
    private Player player;
    public SettingWindow(MainGame mainGame){
        this.stage = mainGame.getHud().getStage();
        skin = ResourceLoader.getInstance().getSkin();
        player = mainGame.getPlayer();
    }
    @Override
    public void initUI() {
        panel = new Table();
        panel.setFillParent(false);
        panel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        panel.setPosition(Gdx.graphics.getWidth() / 2 - PANEL_WIDTH / 2, Gdx.graphics.getHeight() / 2 - PANEL_HEIGHT / 2);
        panel.setBackground(skin.getDrawable("round_button"));
        panel.align(Align.top);
        stage.addActor(panel);
        TextButton resume = new TextButton("Resume", skin);
        resume.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                panel.setVisible(false);
                player.setMove(true);
            }
        });
        panel.add(resume).size(150, 50).padTop(10).row();

        TextButton exitToMenu = new TextButton("Exit to menu", skin);
        exitToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(menuGame);

            }
        });
        panel.add(exitToMenu).size(150, 50).padTop(10);
        panel.setVisible(false);
    }

    @Override
    public void update(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            panel.setVisible(!panel.isVisible());
        }
    }

    @Override
    public void show() {
        initUI();
    }

    @Override
    public void resize(float width, float height) {

    }

    public void setMainGame(MainGame mainGame) {
        game = mainGame.getGame();
        menuGame = game.getMenuGame();
    }
}
