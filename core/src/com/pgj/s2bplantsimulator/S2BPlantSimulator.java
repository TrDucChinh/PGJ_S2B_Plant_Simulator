package com.pgj.s2bplantsimulator;
import com.pgj.s2bplantsimulator.character.Movement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class S2BPlantSimulator extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new Movement(this));

	}
	@Override
	public void render() {
		super.render();
	}
	@Override
	public void dispose() {
		super.dispose();
	}
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	@Override
	public void pause() {
		super.pause();
	}
	@Override
	public void resume() {
		super.resume();
	}

}