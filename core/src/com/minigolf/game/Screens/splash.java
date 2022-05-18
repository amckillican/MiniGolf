package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;

public class splash implements Screen {
    public Minigolf spash;

    public splash(Minigolf game) {
        this.spash = game;
    }

    public void render(float delta) {
        /*
        */
    

        Minigolf.batch.begin();
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(2);
        Minigolf.font.draw(Minigolf.batch, "press any key to start ", 400, 400);
        Minigolf.font.getData().setScale(1);
        Minigolf.font.draw(Minigolf.batch, "Created By: Adam Fischer, Ben Smith, Alex McKillican, Clinton Osawe", 5, 30);
        Minigolf.batch.end();

        if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
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
