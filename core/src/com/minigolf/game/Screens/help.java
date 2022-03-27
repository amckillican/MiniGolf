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

        String shotStr = "STROKE: " + Minigolf.ball.getShots();

        // display the text on the buttons
        Minigolf.batch.begin();
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(1);
        Minigolf.font.draw(Minigolf.batch, shotStr, 10, 750);
        Minigolf.batch.end();

        // getting ball position every frame
        int ballPosX = Minigolf.ball.getX();
        int ballPosY = Minigolf.ball.getY();

        // ball physics
        Minigolf.ball.updatePos();
        Minigolf.ball.walls(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // if user clicks and drags on the ball
        // shoot the ball in the intended direction
        if (Minigolf.currentFrame - Minigolf.startFrame >= .5) {
            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() >= ballPosX
                        && Gdx.input.getX() <= ballPosX + Minigolf.ball.getSize()) {
                    if (765 - Gdx.input.getY() >= ballPosY
                            && 765 - Gdx.input.getY() <= ballPosY + Minigolf.ball.getSize()) {
                        Minigolf.dragging = true;
                    }
                }
            } else if (Minigolf.shoot == true && Minigolf.dragging == true && Minigolf.ball.getXVelocity() == 0
            && Minigolf.ball.getYVelocity() == 0) {
                Minigolf.ball.setXVelocity(-(Minigolf.mouseUpX - Minigolf.mouseDownX) / 2);
                Minigolf.ball.setYVelocity((Minigolf.mouseUpY - Minigolf.mouseDownY) / 2);
                Minigolf.ball.addShot();
                Minigolf.shoot = false;
                Minigolf.dragging = false;
            }
        }

        Minigolf.batch.begin();

        // drawing ball
        Minigolf.batch.draw(game.ballImg, ballPosX, ballPosY);

        // display the power meter
        if (Minigolf.dragging) {
            // render on the left size
            if (ballPosX > 64) {
                // render above the ball
                if (765 - ballPosY > 64) {
                    Minigolf.batch.draw(game.powerMeterBG, ballPosX - 32, ballPosY);
                }
                // render below the ball
                if (765 - ballPosY <= 64) {
                    Minigolf.batch.draw(game.powerMeterBG, ballPosX - 32, ballPosY - 64);
                }
            }
            // render on the right size
            else if (ballPosX <= 64) {
                // render above the ball
                if (765 - ballPosY > 64) {
                    Minigolf.batch.draw(game.powerMeterBG, ballPosX + 32, ballPosY);
                }
                // render below the ball
                if (765 - ballPosY <= 64) {
                    Minigolf.batch.draw(game.powerMeterBG, ballPosX + 32, ballPosY - 64);
                }
            }
        }

        Minigolf.batch.end();

        // debugging
        System.out.print("x: " + ballPosX);
        System.out.print(" y: " + (765 - ballPosY));
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
