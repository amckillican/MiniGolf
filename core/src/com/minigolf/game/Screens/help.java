package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class help implements Screen {
    public Minigolf game;

    public help(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.tiledMapRenderer.setView(game.camera);
        game.tiledMapRenderer.render();

        Minigolf.ball.updatePos();
        Minigolf.ball.walls(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Minigolf.batch.begin();
        Minigolf.batch.draw(game.ballImg, Minigolf.ball.getX(), Minigolf.ball.getY());
        Minigolf.batch.end();

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

        System.out.print("\tx: " + Minigolf.ball.getX());
        System.out.print("\ty: " + Minigolf.ball.getY());
        System.out.print("\txV: " + Minigolf.ball.getXVelocity());
        System.out.println("\tyV: " + Minigolf.ball.getYVelocity());
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
