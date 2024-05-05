package com.pgj.s2bplantsimulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.controller.Movement;

public class LoadMap implements Screen {
    public S2BPlantSimulator game;
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public LoadMap(S2BPlantSimulator game) {
        this.game = game;
        game.camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()));
        game.camera.position.set(game.camera.viewportWidth / 2f, game.camera.viewportHeight / 2f, 0);
        game.camera.update();
    }
    @Override
    public void show() {
        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false, 800, 600);
    }

    @Override
    public void render(float v) {
        game.camera.update();
        renderer.setView(game.camera);
        renderer.render();
        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);
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
