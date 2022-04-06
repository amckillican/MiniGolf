package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.Minigolf.game.Global.Gameplay;
import com.badlogic.gdx.Screen;

public class Endless implements Screen {
    public Minigolf game;
    
    // create screen
    public help(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // method for physics, ui, and other things that will be used in other screens
        Gameplay.gameplay();

        

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
