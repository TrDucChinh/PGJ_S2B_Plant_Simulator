package com.pgj.s2bplantsimulator.notuse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;


public class Demo1 implements Screen {
    float speed = 120;
    private float stateTime;
    public S2BPlantSimulator game;
    public TestCharactor testCharactor;
    private Texture walk;
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;

    private MapObjects mapObjects;

    private SpriteBatch batch;

    public Demo1(S2BPlantSimulator game) {
        this.game = game;
        batch = game.batch;
        walk = new Texture("Basic Charakter Spritesheet.png");
        testCharactor = new TestCharactor(walk, GameConstant.WINDOW_WIDTH / 2, GameConstant.WINDOW_HEIGHT / 2, speed);
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        game.camera = new OrthographicCamera(512, 360);
        mapObjects = map.getLayers().get(3).getObjects();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        stateTime += delta;
        renderer.setView(game.camera);
        renderer.render();
        testCharactor.update(mapObjects);
        batch.begin();
        //set cam theo nhan vat
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.position.set(testCharactor.getLocationX(), testCharactor.getLocationY(), 0);
        if (game.camera.position.x < game.camera.viewportWidth / 2) {
            game.camera.position.x = game.camera.viewportWidth / 2;
        }
        if (game.camera.position.x > map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class) - game.camera.viewportWidth / 2) {
            game.camera.position.x = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class) - game.camera.viewportWidth / 2;
        }
        if (game.camera.position.y < game.camera.viewportHeight / 2) {
            game.camera.position.y = game.camera.viewportHeight / 2;
        }
        if (game.camera.position.y > map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class) - game.camera.viewportHeight / 2) {
            game.camera.position.y = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class) - game.camera.viewportHeight / 2;
        }
        game.camera.update();
        //
        testCharactor.draw(batch, stateTime);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();

    }
}