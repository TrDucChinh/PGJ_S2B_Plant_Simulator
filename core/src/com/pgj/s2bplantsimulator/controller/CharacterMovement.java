package com.pgj.s2bplantsimulator.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Vector2;
import com.pgj.s2bplantsimulator.model.Character;
import com.pgj.s2bplantsimulator.model.constant.CharacterSatus;
import com.pgj.s2bplantsimulator.model.constant.Direction;

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

    public void move(Character character, MapObjects mapObjects) {
        moveDirection();
        float locationX = character.getLocationX();
        float locationY = character.getLocationY();

        Vector2 oldPosition = new Vector2(locationX, locationY);
        if (direction == Direction.UP) {
            locationY += character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.DOWN) {
            locationY -= character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.LEFT) {
            locationX -= character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.RIGHT) {
            locationX += character.getSTRAIGHT_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.UPLEFT) {
            locationX -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.UPRIGHT) {
            locationX += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.DOWNLEFT) {
            locationX -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        if (direction == Direction.DOWNRIGHT) {
            locationX += character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
            locationY -= character.getDIAGONAL_SPEED() * Gdx.graphics.getDeltaTime();
        }
        Vector2 newPosition = new Vector2(locationX, locationY);
        CheckCollision checkCollision = new CheckCollision();
        character.setPosition(newPosition.x, newPosition.y);

        character.setStatus(status);
        if (status != CharacterSatus.IDLE) {
            character.setDirection(direction);
        }
    }
}
