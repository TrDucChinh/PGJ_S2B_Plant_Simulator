package com.pgj.s2bplantsimulator.controller;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.pgj.s2bplantsimulator.model.Dirt;
import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.screens.MainGame;


import static com.pgj.s2bplantsimulator.common.constant.GameConstant.PPM;
import static com.pgj.s2bplantsimulator.screens.MainGame.plantDirtList;


public class TileMapHelper {
    public static TiledMap map;
    public MainGame gameScreen;
    public static float xDirt, yDirt;

    public TileMapHelper(MainGame gameScreen) {
        this.gameScreen = gameScreen;
    }

    public OrthogonalTiledMapRenderer setupMap() {
        map = new TmxMapLoader().load("newMap.tmx");
        parseMapObjects(map.getLayers().get("block").getObjects());
        parseMapObjects(map.getLayers().get("soil").getObjects());

        return new OrthogonalTiledMapRenderer(map);
    }

    public void parseMapObjects(MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects) {
            if (mapObject instanceof PolygonMapObject) {
                createStaticBody((PolygonMapObject) mapObject);
            }
            if (mapObject instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();

                if (rectangleName.equals("Player")) {
                    Body body = BodyHelperService.createBody(
                            rectangle.getX() + rectangle.getWidth() / 2,
                            rectangle.getY() + rectangle.getHeight() / 2,
                            rectangle.getWidth(),
                            rectangle.getHeight(),
                            false,
                            gameScreen.world
                    );
                    gameScreen.player = new Player(gameScreen, body);
                } else if (rectangleName.equals("Dirt")) {
                    xDirt = rectangle.getX() / 32;
                    yDirt = rectangle.getY() / 32;
                    plantDirtList.add(new Dirt(xDirt, yDirt, rectangle.getWidth(), rectangle.getHeight(), "sprites_basic_pack/dirt.png", false, false, false));
                } else if (rectangleName.equals("Bed")) {
                    gameScreen.bedPosition = new Vector2(rectangle.getX() / 32, rectangle.getY() / 32);
                } else if (rectangleName.equals("Trader")) {
                    gameScreen.traderPosition = new Vector2(rectangle.getX() / 32, rectangle.getY() / 32);
                }
            }
        }
    }

    public void createStaticBody(PolygonMapObject mapObject) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = gameScreen.world.createBody(bodyDef);
        Shape shape = createPolygonShape(mapObject);
        body.createFixture(shape, 1000);
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject mapObject) {
        float[] vertices = mapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 current = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
            worldVertices[i] = current;
        }
        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

}
