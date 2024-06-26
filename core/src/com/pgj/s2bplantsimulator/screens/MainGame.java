package com.pgj.s2bplantsimulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.pgj.s2bplantsimulator.S2BPlantSimulator;
import com.pgj.s2bplantsimulator.controller.TileMapHelper;
import com.pgj.s2bplantsimulator.model.Dirt;
import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.model.Seed;
import com.pgj.s2bplantsimulator.sound.SoundManager;
import com.pgj.s2bplantsimulator.transition.Transition;
import com.pgj.s2bplantsimulator.view.HUD;

import java.util.*;

import static com.pgj.s2bplantsimulator.common.constant.GameConstant.PPM;

public class MainGame implements Screen {
    public float stateTime;
    public S2BPlantSimulator game;
    public World world;
    public Player player;
    public TileMapHelper tileMapHelper;
    public TiledMap map = new TmxMapLoader().load("newMap.tmx");
    public OrthogonalTiledMapRenderer renderer;
    public Box2DDebugRenderer box2DDebugRenderer;
    public static Set<Dirt> plantDirtList = new HashSet<>();
    public static Set<Dirt> waterDirt = new HashSet<>();
    public static Set<Dirt> soilList = new HashSet<>();
    public static Set<Seed> seedList = new HashSet<>();
    //reset day
    public Vector2 bedPosition;
    public Vector2 traderPosition;

    private Transition transition;

    private SoundManager soundManager;
    private HUD hud;

    public int[] Water = new int[]{0}, Grass = new int[]{1}, House = new int[]{2}, HouseFurniture = new int[]{3}, Fence = new int[]{4}, Wood = new int[]{10}, Carpet = new int[]{5}, Trader = new int[]{6}, Desk = new int[]{7}; // Lấy index của layer

    public MainGame(S2BPlantSimulator game) {
        this.world = new World(new Vector2(0, 0), false);
        this.game = game;
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.setDrawBodies(false);
        box2DDebugRenderer.setDrawJoints(false);
        this.tileMapHelper = new TileMapHelper(this);
        this.renderer = tileMapHelper.setupMap();
        hud = new HUD(this);

        this.transition = new Transition(player);
        soundManager = new SoundManager(this);
    }

    @Override
    public void show() {
        soundManager.create();
        game.camera = new OrthographicCamera(512 / 2, 360 / 2);
        hud.show();

    }

    public void update(float dt) {
        world.step(1 / 60f, 6, 2);
        Vector3 position = game.camera.position;
        position.x = player.body.getPosition().x * PPM * 10 / 10f;
        position.y = player.body.getPosition().y * PPM * 10 / 10f;
        game.camera.position.set(position);
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
        for (Seed seed : seedList) {
            seed.update(dt);
        }
        player.update(dt);
        game.camera.update();
        hud.update(dt);
        if (player.isSleep()) {
            this.transition.play();
        }
        soundManager.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(game.camera);
        // render map theo layer index
        renderer.render(Water);
        renderer.render(Grass);
        renderer.render(House);
        renderer.render(HouseFurniture);
        renderer.render(Fence);
        renderer.render(Carpet);
        renderer.render(Trader);
        renderer.render(Desk);
        box2DDebugRenderer.render(world, game.camera.combined.scl(PPM));

        stateTime += delta;
        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);
        this.update(delta);
        try {
            for (Dirt dirt : soilList) {
                if (dirt.isDirt) {
                    game.batch.draw(dirt, dirt.getX() + 0.5f, dirt.getY() + 0.5f, 0.5f, 0.5f);
                }
            }
            if (!waterDirt.isEmpty()) {
                for (Dirt soil : waterDirt) {
                    if (soil.isWatered) {
                        game.batch.draw(soil, soil.getX() + 0.5f, soil.getY() + 0.5f, 0.5f, 0.5f);
                    }
                }

            }
            for (Seed seed : seedList) {
                game.batch.draw(seed, seed.getX() + 0.5f, seed.getY() + 0.5f, 0.5f, 0.5f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (player.isMove()) {
        player.draw(game.batch);
//        }
        game.batch.end();
        renderer.render(Wood);
        hud.render(delta);
        if (player.isSleep()) {
            this.transition.play();
        }
    }

    @Override
    public void resize(int i, int i1) {
        hud.resize(i, i1);
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
        hud.dispose();
        renderer.dispose();
        box2DDebugRenderer.dispose();
    }

    public HUD getHud() {
        return hud;
    }

    public Player getPlayer() {
        return player;
    }

    public S2BPlantSimulator getGame() {
        return game;
    }
}
