package com.minigolf;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class titlescreen {
    public static void titleScreen() {
        SpriteBatch batch = new SpriteBatch();
        Texture titleBG = new Texture("gfx/bg.png");

        batch.begin();
		batch.draw(titleBG, 0, 0);
		batch.end();
    }
}
