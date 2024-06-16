package com.pgj.s2bplantsimulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pgj.s2bplantsimulator.model.Player;
import com.pgj.s2bplantsimulator.screens.MenuGame;

public class S2BPlantSimulator extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public OrthographicCamera camera;
	private MenuGame menuGame;
	@Override
	public void create() {
		menuGame = new MenuGame(this);
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(menuGame);
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

	public MenuGame getMenuGame() {
		return menuGame;
	}


}