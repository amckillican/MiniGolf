package com.minigolf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.minigolf.game.minigolf;

public class help {

    public static void helpScreen() {
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        minigolf.tiledMapRenderer.setView(minigolf.camera);
        minigolf.tiledMapRenderer.render();

        
    }
}
