package com.pgj.s2bplantsimulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;

public abstract class MenuScreenComponent implements Screen {
    private S2BPlantSimulator game;
    private Stage stage;
    private Skin skin;
    private Table panel;
    private MenuGame menuGame;
    public MenuScreenComponent(MenuGame menuGame){
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
        this.game = menuGame.getGame();
        this.menuGame = menuGame;

    }

    @Override
    public void show() {
        panel = new Table();
        panel.setBackground(skin.getDrawable("background_reduced"));
        panel.setFillParent(true);
//        stage.setDebugAll(true);
        stage.addActor(panel);
        Gdx.input.setInputProcessor(stage);
        TextButton backButton = new TextButton("Back", getSkin());
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(menuGame);
            }
        });
        getPanel().add(backButton).size(200, 50).padBottom(20).row();


    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    public Table getPanel() {
        return panel;
    }

    public void setPanel(Table panel) {
        this.panel = panel;
    }

    public S2BPlantSimulator getGame() {
        return game;
    }

    public void setGame(S2BPlantSimulator game) {
        this.game = game;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
