package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.pgj.s2bplantsimulator.controller.TileMapHelper;
import com.pgj.s2bplantsimulator.inventory.Chest;
import com.pgj.s2bplantsimulator.inventory.Equipment;

import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.ultis.ResourceLoader;

import static com.pgj.s2bplantsimulator.common.constant.GameConstant.PPM;

public class Player extends Sprite {
    public enum State {IDLE, UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, HOE, WATER}

    public enum Direction {UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT}

    public String currentItem;
    public Direction direction;
    public State currentState;
    public State previousState;
    public Dirt plantDirt = new Dirt();
    public Equipment equipment;
    private Chest chest;
    public TileMapHelper tileMapHelper;

    public Texture playerTexture;
    public Body body;
    public World world;
    public Animation[] up;
    public Animation[] down;
    public Animation[] left;
    public Animation[] right;
    public Animation[] stand;
    public Animation[] hoe;
    public Animation[] water;
    public float speed, velX, velY, stateTimer;


    public Player(MainGame gameScreen, Body body) {
        this.currentItem = "hoe";
        this.world = gameScreen.world;
        this.tileMapHelper = new TileMapHelper(gameScreen);
        equipment = new Equipment(gameScreen);
        chest = new Chest(gameScreen);
        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        this.speed = 10f;
        this.body = body;
        setBounds(body.getPosition().x, body.getPosition().y, 48 / PPM, 48 / PPM);


        up = new Animation[1];
        down = new Animation[1];
        left = new Animation[1];
        right = new Animation[1];
        stand = new Animation[1];
        hoe = new Animation[1];
        water = new Animation[1];
        playerTexture = ResourceLoader.getInstance().getTexture("Basic Charakter Spritesheet.png");
//        playerTexture = new Texture("Basic Charakter Spritesheet.png");
        TextureRegion[][] playerTextureRegion = TextureRegion.split(playerTexture, 48, 48);
        TextureRegion[] rollFrames = new TextureRegion[4 * 4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rollFrames[index++] = playerTextureRegion[i][j];
            }
        }
        down[0] = new Animation(0.2f, rollFrames[0], rollFrames[1], rollFrames[2], rollFrames[3]);
        up[0] = new Animation(0.2f, rollFrames[4], rollFrames[5], rollFrames[6], rollFrames[7]);
        left[0] = new Animation(0.2f, rollFrames[8], rollFrames[9], rollFrames[10], rollFrames[11]);
        right[0] = new Animation(0.2f, rollFrames[12], rollFrames[13], rollFrames[14], rollFrames[15]);
        stand[0] = new Animation(0.2f, rollFrames[0], rollFrames[1]);
        up[0].setPlayMode(Animation.PlayMode.LOOP);
        down[0].setPlayMode(Animation.PlayMode.LOOP);
        left[0].setPlayMode(Animation.PlayMode.LOOP);
        right[0].setPlayMode(Animation.PlayMode.LOOP);
        stand[0].setPlayMode(Animation.PlayMode.LOOP);
        playerTexture = ResourceLoader.getInstance().getTexture("sprites_basic_pack/Characters/Basic Charakter Actions/Basic Charakter Actions.png");
//        playerTexture = new Texture("sprites_basic_pack/Characters/Basic Charakter Actions/Basic Charakter Actions.png");
        playerTextureRegion = TextureRegion.split(playerTexture, 48, 48);
        rollFrames = new TextureRegion[12 * 2];
        index = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 2; j++) {
                rollFrames[index++] = playerTextureRegion[i][j];
            }
        }
        hoe[0] = new Animation(0.1f, rollFrames[0], rollFrames[1]);
        water[0] = new Animation(0.08f, rollFrames[16], rollFrames[17]);
        water[0].setPlayMode(Animation.PlayMode.LOOP);
        hoe[0].setPlayMode(Animation.PlayMode.LOOP);
    }

    public void update(float dt) {
        checkUserInput();
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region;
        switch (currentState) {
            case UP:
                region = (TextureRegion) up[0].getKeyFrame(stateTimer, true);
                break;
            case DOWN:
                region = (TextureRegion) down[0].getKeyFrame(stateTimer, true);
                break;
            case LEFT:
            case UPLEFT:
            case DOWNLEFT:
                region = (TextureRegion) left[0].getKeyFrame(stateTimer, true);
                break;
            case RIGHT:
            case DOWNRIGHT:
            case UPRIGHT:
                region = (TextureRegion) right[0].getKeyFrame(stateTimer, true);
                break;
            case HOE:
                region = (TextureRegion) hoe[0].getKeyFrame(stateTimer, true);
                break;
            case WATER:
                region = (TextureRegion) water[0].getKeyFrame(stateTimer, true);
                break;
            default:
                region = (TextureRegion) stand[0].getKeyFrame(stateTimer, true);
                break;
        }
        stateTimer += dt;
        return region;
    }

    public void checkUserInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            currentState = State.UP;
            direction = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.DOWN;
            direction = Direction.DOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentState = State.LEFT;
            direction = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            currentState = State.RIGHT;
            direction = Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.UPLEFT;
            direction = Direction.UPLEFT;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.UPRIGHT;
            direction = Direction.UPRIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.DOWNLEFT;
            direction = Direction.DOWNLEFT;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.DOWNRIGHT;
            direction = Direction.DOWNRIGHT;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentState = State.IDLE;
            direction = Direction.DOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            currentItem = "hoe";
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            currentItem = "water";
            System.out.println("water");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            currentItem = "seed";
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (currentItem.equals("hoe")) {
                for (Vector4 dirtPos : MainGame.dirtPositionList) {
                    if (body.getPosition().x >= dirtPos.x && body.getPosition().x <= dirtPos.x + 0.5 && body.getPosition().y >= dirtPos.y && body.getPosition().y <= dirtPos.y + 0.5) {
                        currentState = State.HOE;
                        plantDirt = new Dirt(dirtPos.x, dirtPos.y, dirtPos.z, dirtPos.w, "dirt.png", true, false, false);
                        plantDirt.isDirt = true;
                        MainGame.plantDirtList.add(plantDirt);
                    }
                }
            } else if (currentItem.equals("water")) {
                if (!MainGame.plantDirtList.isEmpty()) {
                    for (Dirt dirt : MainGame.plantDirtList) {
                        if (body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5) {
                            if (dirt.isWatered == false) {
                                dirt.isWatered = true;
                                currentState = State.WATER;
                                plantDirt = new Dirt(dirt.xDirt, dirt.yDirt, dirt.height, dirt.width, "water.png", dirt.isDirt, true, dirt.isPlanted);
                                MainGame.soilList.add(plantDirt);
                            }
                        }
                    }
                }
            } else if (currentItem.equals("seed")) { // Tạm thời mới trồng trước cây ngô
                if (!MainGame.plantDirtList.isEmpty()) {
                    for (Dirt dirt : MainGame.plantDirtList) {
                        if (body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5) {
                            if (dirt.isPlanted == false) {
                                dirt.isPlanted = true;
                                Seed seed = new Seed(dirt.xDirt, dirt.yDirt, dirt.height, dirt.width, "seed.png", "corn");
                                MainGame.seedList.add(seed);
                            }
                        }
                    }
                }
            }


        }

        velX = 0;
        velY = 0;
        if (currentState == State.UP) {
            velY = 0.5f;
        } else if (currentState == State.DOWN) {
            velY = -0.5f;
        } else if (currentState == State.LEFT) {
            velX = -0.5f;
        } else if (currentState == State.RIGHT) {
            velX = 0.5f;
        } else if (currentState == State.UPLEFT) {
            velX = -0.35f;
            velY = 0.35f;
        } else if (currentState == State.UPRIGHT) {
            velX = 0.35f;
            velY = 0.35f;
        } else if (currentState == State.DOWNLEFT) {
            velX = -0.35f;
            velY = -0.35f;
        } else if (currentState == State.DOWNRIGHT) {
            velX = 0.35f;
            velY = -0.35f;
        }
        body.setLinearVelocity(velX * speed, velY * speed);
    }

    public Chest getChest() {
        return chest;
    }

    public Equipment getEquipment() {
        return equipment;
    }

}
