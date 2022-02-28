package com.minigolf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class titlescreen {
    static int bgPosX1 = 1360;
    static int bgPosX2 = 0;

    public static void titleScreen() {
        SpriteBatch batch = new SpriteBatch();
        Texture titleBG = new Texture("gfx/bg.png");

        batch.begin();
        batch.draw(titleBG, bgPosX1, 0);
        batch.draw(titleBG, bgPosX2, 0);
        batch.end();

        bgPosX1 -= 1;
        bgPosX2 -= 1;

        if (bgPosX1 <= -1360) {
            bgPosX1 = 1360;
        } else if (bgPosX2 <= -1360) {
            bgPosX2 = 1360;
        }

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
