package com.pgj.s2bplantsimulator.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.pgj.s2bplantsimulator.common.constant.GameConstant;
import com.pgj.s2bplantsimulator.controller.Random;
import com.pgj.s2bplantsimulator.controller.TileMapHelper;


import com.pgj.s2bplantsimulator.inventory.Inventory;
import com.pgj.s2bplantsimulator.screens.MainGame;
import com.pgj.s2bplantsimulator.loader.ResourceLoader;


import static com.pgj.s2bplantsimulator.common.constant.GameConstant.PPM;

public class Player extends Sprite {
    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isTrading() {
        return isTrading;
    }

    public void setTrading(boolean trading) {
        isTrading = trading;
    }

    public boolean isMove() {
        return moving;
    }

    public void setMove(boolean move) {
        this.moving = move;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public enum State {IDLE, UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, HOE, WATER}

    public enum Direction {UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT}

    private boolean isSleep;
    private boolean isTrading;
    private boolean moving;
    private int money;

    public Item currentItem;
    public Direction direction;
    public State currentState;
    public State previousState;
    public Dirt plantDirt = new Dirt();
    private final Inventory inventory;
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
    public MainGame game;


    public Player(MainGame gameScreen, Body body) {
        this.world = gameScreen.world;
        this.game = gameScreen;
        this.setSleep(false);
        this.setTrading(false);
        this.setMove(true);
        this.tileMapHelper = new TileMapHelper(gameScreen);
        inventory = new Inventory(gameScreen);
        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        this.speed = 10f;
        this.body = body;
        this.money = 1000;
        setBounds(body.getPosition().x, body.getPosition().y, 48 / PPM, 48 / PPM);


        up = new Animation[1];
        down = new Animation[1];
        left = new Animation[1];
        right = new Animation[1];
        stand = new Animation[1];
        hoe = new Animation[1];
        water = new Animation[1];

//        playerTexture = new Texture("Basic Charakter Spritesheet.png");
        playerTexture = ResourceLoader.getInstance().getTexture("Basic Charakter Spritesheet.png");
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

//        playerTexture = new Texture("sprites_basic_pack/Characters/Basic Charakter Actions/Basic Charakter Actions.png");
        playerTexture = ResourceLoader.getInstance().getTexture("sprites_basic_pack/Characters/Basic Charakter Actions/Basic Charakter Actions.png");
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
        currentItem = inventory.getCurrentItem();
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (currentItem != null) {
//                System.out.println("Current Item: " + currentItem.getName());
//                System.out.println(getMoney());
                if (currentItem.equals("Hoe")) {
                    for (Dirt dirt : MainGame.plantDirtList) {
                        if (body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5) {
                            if (!dirt.isDirt) {
                                currentState = State.HOE;
                                plantDirt = new Dirt(dirt.xDirt, dirt.yDirt, dirt.height, dirt.width, "sprites_basic_pack/dirt.png", true, dirt.isWatered, dirt.isPlanted);
                                MainGame.soilList.add(plantDirt);
                                dirt.isDirt = true;
                            }
                        }
                    }
//                    System.out.println(MainGame.soilList.size());
                } else if (currentItem.equals("Watering Pot")) {
                    if (!MainGame.soilList.isEmpty()) {
                        for (Dirt dirt : MainGame.soilList) {
                            if (body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5) {
                                if (!dirt.isWatered && dirt.isDirt) {
                                    dirt.isWatered = true;
                                    currentState = State.WATER;
                                    plantDirt = new Dirt(dirt.xDirt, dirt.yDirt, dirt.height, dirt.width, "sprites_basic_pack/water.png", dirt.isDirt, true, dirt.isPlanted);
                                    MainGame.waterDirt.add(plantDirt);
                                }
                            }
                        }
//                        System.out.println(MainGame.waterDirt.size());
                    }
                } else if (currentItem.equals("Corn Seed")) {
                    // Tạm thời mới trồng trước cây ngô
                    if (!MainGame.soilList.isEmpty()) {
                        for (Dirt dirt : MainGame.soilList) {
                            if (body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5) {
                                if (!dirt.isPlanted && dirt.isWatered) {
                                    MainGame.waterDirt.stream().filter(dirt1 -> dirt1.xDirt == dirt.xDirt && dirt1.yDirt == dirt.yDirt).forEach(dirt1 -> dirt1.isPlanted = true);
                                    dirt.isPlanted = true;
                                    Seed seed = new Seed(dirt.xDirt, dirt.yDirt, dirt.height, dirt.width, "fruit/corn/0.png", "corn");
                                    MainGame.seedList.add(seed);
                                    currentItem.setQuantity(currentItem.getQuantity() - 1);
                                    break;
                                }
                            }
                        }
                    }
                } else if (currentItem.equals("Tomato Seed")) {
                    // Tạm thời mới trồng trước cây cà chua
                    if (!MainGame.soilList.isEmpty()) {
                        for (Dirt dirt : MainGame.soilList) {
                            if (body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5) {
                                if (!dirt.isPlanted && dirt.isWatered) {
                                    MainGame.waterDirt.stream().filter(dirt1 -> dirt1.xDirt == dirt.xDirt && dirt1.yDirt == dirt.yDirt).forEach(dirt1 -> dirt1.isPlanted = true);
                                    dirt.isPlanted = true;
                                    Seed seed = new Seed(dirt.xDirt, dirt.yDirt, dirt.height, dirt.width, "fruit/tomato/0.png", "tomato");
                                    MainGame.seedList.add(seed);
                                    currentItem.setQuantity(currentItem.getQuantity() - 1);
                                    break;
                                }
                            }
                        }
                    }
                }
                else if (currentItem.equals("Axe")) {
                    try {
                        for (Seed seed : MainGame.seedList) {
                            if (body.getPosition().x >= seed.xSeed && body.getPosition().x <= seed.xSeed + 0.5 && body.getPosition().y >= seed.ySeed && body.getPosition().y <= seed.ySeed + 0.5 && seed.harvestable) {
                                inventory.addItem(seed.getName(), Random.randomQuantity(GameConstant.MIN_QUANTITY, GameConstant.MAX_QUANTITY));
                                MainGame.plantDirtList.stream().filter(dirt -> dirt.xDirt == seed.xSeed && dirt.yDirt == seed.ySeed).forEach(dirt -> dirt.isDirt = false);
                                MainGame.soilList.removeIf(dirt -> body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5 && dirt.isWatered && dirt.isPlanted && dirt.isDirt);
                                MainGame.waterDirt.removeIf(dirt -> body.getPosition().x >= dirt.xDirt && body.getPosition().x <= dirt.xDirt + 0.5 && body.getPosition().y >= dirt.yDirt && body.getPosition().y <= dirt.yDirt + 0.5 && dirt.isDirt && dirt.isPlanted && dirt.isWatered);
                            }
                        }
                        MainGame.seedList.removeIf(seed -> body.getPosition().x >= seed.xSeed && body.getPosition().x <= seed.xSeed + 0.5 && body.getPosition().y >= seed.ySeed && body.getPosition().y <= seed.ySeed + 0.5 && seed.harvestable);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //Going to bed
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            if (body.getPosition().x >= game.bedPosition.x && body.getPosition().x <= game.bedPosition.x + 0.5 && body.getPosition().y >= game.bedPosition.y && body.getPosition().y <= game.bedPosition.y + 0.5) {
                this.setSleep(true);
                for (Seed seed : MainGame.seedList) {
                    if (seed.getName().equals("corn")) {
                        seed.age += seed.maxAge * 0.75f;
                    }
                    if (seed.getName().equals("tomato")) {
                        seed.age += seed.maxAge;
                    }

                }
                int dailyPrice = Random.randomPrice(GameConstant.MIN_PRICE, GameConstant.MAX_PRICE);
                if (inventory.getItems().containsKey("corn")) {
                    inventory.getItems().get("corn").setSellPrice(dailyPrice);
                } else if (inventory.getItems().containsKey("tomato")) {
                    inventory.getItems().get("tomato").setSellPrice(dailyPrice * 75 / 100);
                }
            } else if (body.getPosition().x >= game.traderPosition.x && body.getPosition().x <= game.traderPosition.x + 1 && body.getPosition().y >= game.traderPosition.y && body.getPosition().y <= game.traderPosition.y + 1) {
                this.setTrading(!this.isTrading());
                this.setMove(!this.isMove());
            }

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            this.setMove(!this.isMove());
        }


        velX = 0;
        velY = 0;
        if (this.isMove()) {
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
    }

    public boolean isMoving() {
        return (velX != 0 || velY != 0) && (Gdx.input.isKeyPressed(Input.Keys.W)
                || Gdx.input.isKeyPressed(Input.Keys.A)
                || Gdx.input.isKeyPressed(Input.Keys.S)
                || Gdx.input.isKeyPressed(Input.Keys.D));
    }


    public boolean isAction() {
        if (currentItem != null) {
            return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        }
        return false;
    }

}
