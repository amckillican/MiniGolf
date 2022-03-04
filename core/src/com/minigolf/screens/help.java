package com.minigolf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.minigolf.game.minigolf;
import com.badlogic.gdx.graphics.GL30;

public class help implements Screen {
    public minigolf game;

    public help(minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        game.tiledMapRenderer.setView(game.camera);
        game.tiledMapRenderer.render();

        minigolf.batch.begin();
        minigolf.batch.draw(game.ballImg, minigolf.ball.getX(), minigolf.ball.getY());
        minigolf.batch.end();

        minigolf.ball.updatePos();
        minigolf.ball.walls(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (minigolf.currentFrame - minigolf.startFrame >= .5) {
            if (!Gdx.input.justTouched() && minigolf.shooting && !minigolf.win) {
                minigolf.ball.setXVelocity(Gdx.input.getX() - minigolf.ball.getX());
                minigolf.ball.setYVelocity(Gdx.graphics.getHeight() - Gdx.input.getY() - minigolf.ball.getY());
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}