package com.minigolf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.minigolf.game.minigolf;

public class help {

    public static void helpScreen() {
        Ball ball = new Ball(205, 375);

        boolean win = false;
        boolean touched = false;

        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        minigolf.tiledMapRenderer.setView(minigolf.camera);
        minigolf.tiledMapRenderer.render();

        minigolf.batch.begin();
        minigolf.batch.draw(minigolf.ballImg, ball.getX(), ball.getY());
        minigolf.batch.end();

        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() >= ball.getX() && Gdx.input.getX() <= ball.getX() + ball.getSize()) {
                if (Gdx.input.getY() >= ball.getY() && Gdx.input.getY() <= ball.getY() + ball.getSize()) {
                    touched = true;
                }
            }
        } else if (!Gdx.input.isTouched()) {
            touched = false;
        }

        if (touched) {
            minigolf.batch.begin();
            minigolf.batch.draw(minigolf.powerMeter, ball.getX() - 40, ball.getY() - 25);
            minigolf.batch.end();
        }
    }
}
