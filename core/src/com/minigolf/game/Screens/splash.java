package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class splash implements Screen {
    public Minigolf spash;

    public splash(Minigolf game) {
        this.spash = game;
    }

    public void render(float delta) {

        Minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Minigolf.shapeRenderer.setColor(1, 1, 1, 1);
        Minigolf.shapeRenderer.rect(1000, 50, 281, 100);
        Minigolf.shapeRenderer.end();

        Minigolf.batch.begin();
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(2);
        Minigolf.font.draw(Minigolf.batch, "EXIT", 1090, 120);
        Minigolf.batch.end();

        if (Gdx.input.isKeyPressed(Keys.B)) {
            Minigolf.startFrame = Minigolf.currentFrame;
            Minigolf.gamestate = "title";
        }
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

    @Override
    public void show() {

    }

}
