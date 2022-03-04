package com.minigolf.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Vector2;
import com.minigolf.Ball;
import com.minigolf.game.minigolf;

public class help implements Screen, InputProcessor {
    public Ball ball = new Ball(205, 375);
    public boolean win = false;
    public boolean shooting = false;
    public boolean moving = (ball.getXVelocity() != 0 && ball.getYVelocity() != 0);
    public Vector2 touchPos = new Vector2();
    public Vector2 dragPos = new Vector2();
    
    public minigolf game;

    public help (minigolf game){
        this.game = game;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        game.tiledMapRenderer.setView(game.camera);
        game.tiledMapRenderer.render();

        game.batch.begin();
        game.batch.draw(game.ballImg, ball.getX(), ball.getY());
        game.batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // if (!shooting && !moving) {
        //     if (screenX >= ball.getX() && screenX <= (ball.getX() + ball.getSize())) {
        //         if (screenY >= ball.getY() && screenY <= (ball.getY() + ball.getSize())) {
        //             shooting = true;
        //         }
        //     }
        // }
        // if (!shooting && !moving) {
        //     if (screenX >= 0 && screenX <= 50) {
        //         if (screenY >= 0 && screenY <= 100) {
        //             shooting = true;
        //         }
        //     }
        // }

        System.out.println(screenX + " " + screenY);

        if (shooting) {
            game.batch.begin();
            game.batch.draw(game.powerMeterBG, ball.getX() - 40, ball.getY() - 25);
            game.batch.end();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
