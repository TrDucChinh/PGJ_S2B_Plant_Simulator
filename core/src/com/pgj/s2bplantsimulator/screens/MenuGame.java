package com.pgj.s2bplantsimulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;


public class MenuGame implements Screen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 80;
    Texture play, playPress, about, aboutPress, replay, replayPress;
    private S2BPlantSimulator game;

    public MenuGame(S2BPlantSimulator game) {
        this.game = game;
        createTexture();
    }

    public void drawButton(Texture button, Texture buttonPress, int y, int choice) {
        int x = (GameConstant.WINDOW_WIDTH - BUTTON_WIDTH) / 2;
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + BUTTON_WIDTH && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() >= y && GameConstant.WINDOW_HEIGHT - Gdx.input.getY() <= y + BUTTON_HEIGHT) {
            game.batch.draw(buttonPress, (float) (GameConstant.WINDOW_WIDTH - BUTTON_WIDTH) / 2, y, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                if (choice == 1) {
//                    game.setScreen(new MainGame(game));
                    game.setScreen(new MainGame(game));
                } else {
                    if (choice == 2) {
                        //options
                        System.out.println("Options");
                    } else {
                        Gdx.app.exit();
                    }
                }
            }
        } else {
            game.batch.draw(button, (float) (GameConstant.WINDOW_WIDTH - BUTTON_WIDTH) / 2, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }

    public void createTexture() {
//        play = new Texture(GameConstant.PLAY_BUTTON);
        play = ResourceLoader.getInstance().getTexture(GameConstant.PLAY_BUTTON);
//        playPress = new Texture(GameConstant.PLAY_PRESS_BUTTON);
        playPress = ResourceLoader.getInstance().getTexture(GameConstant.PLAY_PRESS_BUTTON);
//        about = new Texture(GameConstant.ABOUT_BUTTON);
        about = ResourceLoader.getInstance().getTexture(GameConstant.ABOUT_BUTTON);
//        aboutPress = new Texture(GameConstant.ABOUT_PRESS_BUTTON);
        aboutPress = ResourceLoader.getInstance().getTexture(GameConstant.ABOUT_PRESS_BUTTON);
//        replay = new Texture(GameConstant.REPLAY_BUTTON);
        replay = ResourceLoader.getInstance().getTexture(GameConstant.REPLAY_BUTTON);
//        replayPress = new Texture(GameConstant.REPLAY_PRESS_BUTTON);
        replayPress = ResourceLoader.getInstance().getTexture(GameConstant.REPLAY_PRESS_BUTTON);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        drawButton(play, playPress, 400, 1);
        drawButton(about, aboutPress, 200, 3);
        drawButton(replay, replayPress, 300, 2);
        game.batch.end();
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
}
