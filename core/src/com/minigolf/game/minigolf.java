package com.minigolf.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.minigolf.titlescreen;

public class minigolf extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	OrthographicCamera camera;

	public String gamestate = "title";

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);

		switch (gamestate) {
			case "title":
				titlescreen.titleScreen();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
