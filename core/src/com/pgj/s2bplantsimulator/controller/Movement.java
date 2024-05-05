package com.pgj.s2bplantsimulator.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;


public class Movement extends InputAdapter implements Screen {
    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;
    //    private OrthographicCamera camera;
    private float speed = 100;
    private S2BPlantSimulator game;
    private Texture img;
    private float playerX = Gdx.graphics.getWidth() / 2 - 32;
    private float playerY = Gdx.graphics.getHeight() / 2 - 32;
    private float stateTime;
    private Animation[] up;
    private Animation[] down;
    private Animation[] left;
    private Animation[] right;
    private Animation[] stand;
    private Sprite sprite;

    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;

    public Movement(S2BPlantSimulator game) {
        this.game = game;
        sprite = new Sprite(new Texture("Basic Charakter Spritesheet.png"));
        sprite.setPosition(0, 0);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.camera = new OrthographicCamera(30, 30 * (Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()));
        game.camera.position.set(game.camera.viewportWidth / 2f, game.camera.viewportHeight / 2f, 0);
        game.camera.update();


        img = new Texture("Basic Charakter Spritesheet.png");
        up = new Animation[1];
        down = new Animation[1];
        left = new Animation[1];
        right = new Animation[1];
        stand = new Animation[1];
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(img, img.getWidth() / FRAME_ROWS, img.getHeight() / FRAME_COLS);
        TextureRegion[] rollFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                rollFrames[index++] = rollSpriteSheet[i][j];
            }
        }
        up[0] = new Animation(0.2f, rollFrames[0], rollFrames[1], rollFrames[2], rollFrames[3]);
        down[0] = new Animation(0.2f, rollFrames[4], rollFrames[5], rollFrames[6], rollFrames[7]);
        left[0] = new Animation(0.2f, rollFrames[8], rollFrames[9], rollFrames[10], rollFrames[11]);
        right[0] = new Animation(0.2f, rollFrames[12], rollFrames[13], rollFrames[14], rollFrames[15]);
        stand[0] = new Animation(0.2f, rollFrames[0], rollFrames[1]);
        up[0].setPlayMode(Animation.PlayMode.LOOP);
        down[0].setPlayMode(Animation.PlayMode.LOOP);
        left[0].setPlayMode(Animation.PlayMode.LOOP);
        right[0].setPlayMode(Animation.PlayMode.LOOP);
        stand[0].setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false, 800, 600);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        renderer.setView(game.camera);
        renderer.render();
        stateTime += delta;
        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);

//        game.batch.draw(sprite, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Không cho đi chéo =))
        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.batch.draw((TextureRegion) stand[0].getKeyFrame(stateTime, true), playerX, playerY);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerY += speed * Gdx.graphics.getDeltaTime();
            game.batch.draw((TextureRegion) up[0].getKeyFrame(stateTime, true), playerX, playerY);
            game.camera.translate(0, speed * Gdx.graphics.getDeltaTime());
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerY -= speed * Gdx.graphics.getDeltaTime();
            game.batch.draw((TextureRegion) down[0].getKeyFrame(stateTime, true), playerX, playerY);
            game.camera.translate(0, -speed * Gdx.graphics.getDeltaTime());
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerX -= speed * Gdx.graphics.getDeltaTime();
            game.batch.draw((TextureRegion) left[0].getKeyFrame(stateTime, true), playerX, playerY);
            game.camera.translate(-speed * Gdx.graphics.getDeltaTime(), 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerX += speed * Gdx.graphics.getDeltaTime();
            game.batch.draw((TextureRegion) right[0].getKeyFrame(stateTime, true), playerX, playerY);
            game.camera.translate(speed * Gdx.graphics.getDeltaTime(), 0);
        }

        if (playerX < -18) {
            playerX = -18;
            game.camera.translate(speed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (playerX > Gdx.graphics.getWidth()-30) {
            playerX = Gdx.graphics.getWidth()-30;
            game.camera.translate(-speed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (playerY < -18) {
            playerY = -18;
            game.camera.translate(0, speed * Gdx.graphics.getDeltaTime());
        }
        if (playerY > Gdx.graphics.getHeight() - 30) {
            playerY = Gdx.graphics.getHeight() - 30;
            game.camera.translate(0, -speed * Gdx.graphics.getDeltaTime());
        }


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