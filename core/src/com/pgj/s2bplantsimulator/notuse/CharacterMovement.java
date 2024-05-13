package com.pgj.s2bplantsimulator.notuse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Vector2;
import com.pgj.s2bplantsimulator.notuse.constant.CharacterSatus;
import com.pgj.s2bplantsimulator.notuse.constant.Direction;

public class CharacterMovement {
    private Direction direction;
    private CharacterSatus status;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    public void moveDirection() {
        direction = null;
        status = null;

        isLeftKeyPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        isRightKeyPressed = Gdx.input.isKeyPressed(Input.Keys.D);
        isUpKeyPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        isDownKeyPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        if(!isLeftKeyPressed && !isRightKeyPressed && !isUpKeyPressed && !isDownKeyPressed) {
            status = CharacterSatus.IDLE;
        }
        else {
            status = CharacterSatus.WALKING;
            if(isLeftKeyPressed && !isRightKeyPressed && isUpKeyPressed && !isDownKeyPressed) {
                direction = Direction.UPLEFT;
            }
            else if(isLeftKeyPressed && !isRightKeyPressed && !isUpKeyPressed && isDownKeyPressed) {
                direction = Direction.DOWNLEFT;
            }
            else if(!isLeftKeyPressed && isRightKeyPressed && isUpKeyPressed && !isDownKeyPressed) {
                direction = Direction.UPRIGHT;
            }
            else if(!isLeftKeyPressed && isRightKeyPressed && !isUpKeyPressed && isDownKeyPressed) {
                direction = Direction.DOWNRIGHT;
            }
            else if(isLeftKeyPressed) {
                direction = Direction.LEFT;
            }
            else if(isRightKeyPressed) {
                direction = Direction.RIGHT;
            }
            else if(isUpKeyPressed) {
                direction = Direction.UP;
            }
            else if (isDownKeyPressed){
                direction = Direction.DOWN;
            }
        }
    }

    public void move(TestCharactor testCharactor, MapObjects mapObjects) {
        moveDirection();
        float locationX = testCharactor.getLocationX();
        float locationY = testCharactor.getLocationY();

        Vector2 oldPosition = new Vector2(locationX, locationY);
        if (direction == Direction.UP) {
            locationY += testCharactor.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.DOWN) {
            locationY -= testCharactor.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.LEFT) {
            locationX -= testCharactor.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.RIGHT) {
            locationX += testCharactor.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.UPLEFT) {
            locationX -= testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY += testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.UPRIGHT) {
            locationX += testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY += testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.DOWNLEFT) {
            locationX -= testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY -= testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.DOWNRIGHT) {
            locationX += testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY -= testCharactor.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        Vector2 newPosition = new Vector2(locationX, locationY);
//        CheckCollision checkCollision = new CheckCollision();
        testCharactor.setPosition(newPosition.x, newPosition.y);

        testCharactor.setStatus(status);
        if (status != CharacterSatus.IDLE) {
            testCharactor.setDirection(direction);
        }
    }
}
