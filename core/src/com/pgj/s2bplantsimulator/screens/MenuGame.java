package com.pgj.s2bplantsimulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;


public class MenuGame implements Screen {

    private Stage stage;
    private Skin skin = new Skin(Gdx.files.internal("Skin/ui_skin.json"));
    private S2BPlantSimulator game;
    private CreditScreen creditScreen;
    private HelpScreen helpScreen;
    private MainGame mainGame;
    public MenuGame(S2BPlantSimulator game) {
        stage = new Stage();
//        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);
        this.game = game;
        mainGame = new MainGame(this.game);
        creditScreen = new CreditScreen(this);
        helpScreen = new HelpScreen(this);
    }
    public void initUI(){
        Table table = new Table();
        table.setFillParent(true);
        table.setBackground(skin.getDrawable("background_reduced"));
        stage.addActor(table);

        TextButton playButton = new TextButton("Play", skin);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(mainGame);

            }
        });
        Label label = new Label("S2B PLANT SIMULATOR", skin, "game-open-title");
        label.setAlignment(Align.center);
        table.add(label).padBottom(20).size(label.getWidth() + 20, label.getHeight() + 20).row();
        table.add(playButton).padBottom(20).row();
        table.getCell(playButton).size(200, 50);

        TextButton helpButton = new TextButton("Help", skin);
        helpButton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(helpScreen);
            }
        });
        table.add(helpButton).size(200, 50).padBottom(20).row();

        TextButton creditButton = new TextButton("Credit", skin);
        table.add(creditButton).size(200, 50).padBottom(20).row();
        creditButton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(creditScreen);
            }
        });

        TextButton exitButton = new TextButton("Exit", skin);
        table.add(exitButton).size(90, 50).padRight(10);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    @Override
    public void render(float v) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    @Override
    public void dispose() {

    }

    public S2BPlantSimulator getGame() {
        return game;
    }

    public void setGame(S2BPlantSimulator game) {
        this.game = game;
    }
}
