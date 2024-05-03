package com.pgj.s2bplantsimulator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.pgj.s2bplantsimulator.view.GameMap;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class S2BPlantSimulator extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameMap gameMap;
	OrthographicCamera camera;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameMap = new GameMap();
        try {
            gameMap.loadMap("map.tmx");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 720);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		gameMap.drawMap(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {

		batch.dispose();
		img.dispose();
	}
}
