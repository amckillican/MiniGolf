package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class help implements Screen {
    // create screen
    public Minigolf game;

    // create screen
    public help(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // set the camera to the tmx map
        game.tiledMapRenderer.setView(game.camera);
        game.tiledMapRenderer.render();

        // ball physics
        Minigolf.ball.updatePos();
        Minigolf.ball.walls(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // drawing ball
        Minigolf.batch.begin();
        Minigolf.batch.draw(game.ballImg, Minigolf.ball.getX(), Minigolf.ball.getY());
        Minigolf.batch.end();

        // if user clicks and drags on the ball
        // shoot the ball in the intended direction
        if (Minigolf.currentFrame - Minigolf.startFrame >= .5) {
            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() >= Minigolf.ball.getX()
                        && Gdx.input.getX() <= Minigolf.ball.getX() + Minigolf.ball.getSize()) {
                    if (765 - Gdx.input.getY() >= Minigolf.ball.getY()
                            && 765 - Gdx.input.getY() <= Minigolf.ball.getY() + Minigolf.ball.getSize()) {
                        Minigolf.dragging = true;
                    }
                }
            } else if (Minigolf.shoot == true && Minigolf.dragging == true && Minigolf.ball.getXVelocity() == 0
                    && Minigolf.ball.getYVelocity() == 0) {
                Minigolf.ball.setXVelocity(-(Minigolf.mouseUpX - Minigolf.mouseDownX) / 2);
                Minigolf.ball.setYVelocity((Minigolf.mouseUpY - Minigolf.mouseDownY) / 2);
                Minigolf.shoot = false;
                Minigolf.dragging = false;
            }
        }

        Minigolf.batch.begin();

        // display the power meter
        if (Minigolf.dragging) {
            // render on the left size
            if (Minigolf.ball.getX() > 64) {
                // render above the ball
                if (765 - Minigolf.ball.getY() > 64) {
                    Minigolf.batch.draw(game.powerMeterBG, Minigolf.ball.getX() - 32, Minigolf.ball.getY());
                }
                // render below the ball
                if (765 - Minigolf.ball.getY() <= 64) {
                    Minigolf.batch.draw(game.powerMeterBG, Minigolf.ball.getX() - 32, Minigolf.ball.getY() - 64);
                }
            }
            // render on the right size
            else if (Minigolf.ball.getX() <= 64) {
                // render above the ball
                if (765 - Minigolf.ball.getY() > 64) {
                    Minigolf.batch.draw(game.powerMeterBG, Minigolf.ball.getX() + 32, Minigolf.ball.getY());
                }
                // render below the ball
                if (765 - Minigolf.ball.getY() <= 64) {
                    Minigolf.batch.draw(game.powerMeterBG, Minigolf.ball.getX() + 32, Minigolf.ball.getY() - 64);
                }
            }
        }

        Minigolf.batch.end();

        // debugging
        System.out.print("x: " + Minigolf.ball.getX());
        System.out.print(" y: " + (765 - Minigolf.ball.getY()));
        System.out.print(" xV: " + Minigolf.ball.getXVelocity());
        System.out.println(" yV: " + Minigolf.ball.getYVelocity());
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
