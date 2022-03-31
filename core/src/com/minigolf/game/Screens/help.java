package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.Minigolf.game.Global.Gameplay;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class help implements Screen {
    // create screen
    public Minigolf game;
    public static TiledMap tiledMap;
    public static TiledMapRenderer tiledMapRenderer;

    // create screen
    public help(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // method for physics, ui, and other things that will be used in other screens
        tiledMap = new TmxMapLoader().load("gfx/Tiled/help.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gameplay.gameplay(tiledMapRenderer);

        // debugging
        // System.out.print("x: " + Gameplay.ballPosX);
        // System.out.println(" y: " + (765 - Gameplay.ballPosY));
        // System.out.print(" xV: " + Minigolf.ball.getXVelocity());
        // System.out.println(" yV: " + Minigolf.ball.getYVelocity());
    }

    @Override
    public void dispose() {

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
    public void show() {

    }
}
