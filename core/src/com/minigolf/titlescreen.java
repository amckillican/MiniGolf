package com.minigolf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.minigolf.game.minigolf;

public class titlescreen {
    // scrolling background position variables
    static int bgPosX1 = 1360;
    static int bgPosX2 = 0;

    public static void titleScreen() {
        // drawing the background in the correct position
        minigolf.batch.begin();
        minigolf.batch.draw(minigolf.titleBG, bgPosX1, 0);
        minigolf.batch.draw(minigolf.titleBG, bgPosX2, 0);
        minigolf.batch.end();

        // scroll the backgrounds
        bgPosX1 -= 1;
        bgPosX2 -= 1;

        // "leap frog" the backgrounds for infinite scrolling effect
        if (bgPosX1 <= -1360) {
            bgPosX1 = 1360;
        } else if (bgPosX2 <= -1360) {
            bgPosX2 = 1360;
        }

        // exiting the application
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
