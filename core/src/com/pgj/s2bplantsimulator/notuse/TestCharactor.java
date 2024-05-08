package com.pgj.s2bplantsimulator.notuse;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.pgj.s2bplantsimulator.notuse.constant.CharacterSatus;
import com.pgj.s2bplantsimulator.notuse.constant.Direction;

public class TestCharactor extends Entity {
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 4;
    private float STRAIGHT_SPEED = 4; // 4 pixels per frame
    private float DIAGONAL_SPEED = 2.8F;
    public static int CHARACTER_WIDTH = 48;
    public static int CHARACTER_HEIGHT = 48;
    private Direction direction;
    private CharacterSatus status;

    private Animation[] up;
    private Animation[] down;
    private Animation[] left;
    private Animation[] right;
    private Animation[] stand;

    public TestCharactor() {
        direction = Direction.DOWN;
        status = CharacterSatus.IDLE;
        setPosition(0, 0);
        passable = false;
    }

    public TestCharactor(Texture texture, float x, float y, float speed) {
        this();
        setTexture(texture);
        setPosition(x, y);
        setSpeed(speed);
        setAnimations(texture);
    }

    public void update(MapObjects mapObjects) {
        CharacterMovement movement = new CharacterMovement();
        movement.move(this, mapObjects);
    }

    public void setAnimations(Texture texture) {
        up = new Animation[1];
        down = new Animation[1];
        left = new Animation[1];
        right = new Animation[1];
        stand = new Animation[1];
        TextureRegion[][] rollSpriteSheet = TextureRegion.split(texture, texture.getWidth() / FRAME_ROWS, texture.getHeight() / FRAME_COLS);
        TextureRegion[] rollFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                rollFrames[index++] = rollSpriteSheet[i][j];
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

    }

    public void setSpeed(float speed) {
        this.STRAIGHT_SPEED = speed;
        this.DIAGONAL_SPEED = (float) Math.sqrt(speed * speed / 2);
    }

    public void draw(Batch batch, float stateTime) {
        if (status == CharacterSatus.IDLE) {
            batch.draw((TextureRegion) stand[0].getKeyFrame(stateTime, true), locationX, locationY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        } else {
            if (direction == Direction.DOWN) {
                batch.draw((TextureRegion) down[0].getKeyFrame(stateTime, true), locationX, locationY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
            } else if (direction == Direction.UP) {
                batch.draw((TextureRegion) up[0].getKeyFrame(stateTime, true), locationX, locationY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
            } else if (direction == Direction.LEFT || direction == Direction.DOWNLEFT || direction == Direction.UPLEFT) {
                batch.draw((TextureRegion) left[0].getKeyFrame(stateTime, true), locationX, locationY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
            } else if (direction == Direction.RIGHT || direction == Direction.DOWNRIGHT || direction == Direction.UPRIGHT) {
                batch.draw((TextureRegion) right[0].getKeyFrame(stateTime, true), locationX, locationY, CHARACTER_WIDTH, CHARACTER_HEIGHT);
            }
        }

    }

    public float getSTRAIGHT_SPEED() {
        return STRAIGHT_SPEED;
    }

    public void setSTRAIGHT_SPEED(float STRAIGHT_SPEED) {
        this.STRAIGHT_SPEED = STRAIGHT_SPEED;
    }

    public float getDIAGONAL_SPEED() {
        return DIAGONAL_SPEED;
    }

    public void setDIAGONAL_SPEED(float DIAGONAL_SPEED) {
        this.DIAGONAL_SPEED = DIAGONAL_SPEED;
    }

    public static int getCharacterWidth() {
        return CHARACTER_WIDTH;
    }

    public static void setCharacterWidth(int characterWidth) {
        CHARACTER_WIDTH = characterWidth;
    }

    public static int getCharacterHeight() {
        return CHARACTER_HEIGHT;
    }

    public static void setCharacterHeight(int characterHeight) {
        CHARACTER_HEIGHT = characterHeight;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public CharacterSatus getStatus() {
        return status;
    }

    public void setStatus(CharacterSatus status) {
        this.status = status;
    }

    public Animation[] getUp() {
        return up;
    }

    public void setUp(Animation[] up) {
        this.up = up;
    }

    public Animation[] getDown() {
        return down;
    }

    public void setDown(Animation[] down) {
        this.down = down;
    }

    public Animation[] getLeft() {
        return left;
    }

    public void setLeft(Animation[] left) {
        this.left = left;
    }

    public Animation[] getRight() {
        return right;
    }

    public void setRight(Animation[] right) {
        this.right = right;
    }

    public Animation[] getStand() {
        return stand;
    }

    public void setStand(Animation[] stand) {
        this.stand = stand;
    }

}
