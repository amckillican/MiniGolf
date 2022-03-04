package com.minigolf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.minigolf.game.minigolf;

public class ninehole implements Screen {
    public minigolf game;

    public ninehole(minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.app.exit();
    }

    @Override
    public void dispose() {

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
}
