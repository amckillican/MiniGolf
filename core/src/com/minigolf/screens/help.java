package com.minigolf.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.math.Vector2;
import com.minigolf.Ball;
import com.minigolf.game.minigolf;

public class help implements Screen, InputProcessor {
    
    public minigolf game;

    public help (minigolf game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Ball ball = new Ball(205, 375);

        boolean win = false;
        boolean shooting = false;

        Vector2 touchPos = new Vector2();
        Vector2 dragPos = new Vector2();

        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        game.tiledMapRenderer.setView(game.camera);
        game.tiledMapRenderer.render();

        game.batch.begin();
        game.batch.draw(game.ballImg, ball.getX(), ball.getY());
        game.batch.end();

        if (!shooting && Gdx.input.isTouched()) {
            if (Gdx.input.getX() >= ball.getX() && Gdx.input.getX() <= ball.getX() + ball.getSize()) {
                if (Gdx.input.getY() >= ball.getY() && Gdx.input.getY() <= ball.getY() + ball.getSize()) {
                    shooting = true;
                }
            }
        } else if (!Gdx.input.isTouched()) {
            shooting = false;
        }

        if (shooting) {
            game.batch.begin();
            game.batch.draw(game.powerMeter, ball.getX() - 40, ball.getY() - 25);
            game.batch.end();
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
}
