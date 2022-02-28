package com.minigolf.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.minigolf.titlescreen;

public class minigolf extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	public String gamestate = "title";

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);

		switch (gamestate) {
			case "title":
				titlescreen.titleScreen();
				System.out.println("test");
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
