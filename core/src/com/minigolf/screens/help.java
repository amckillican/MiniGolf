package com.minigolf.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.minigolf.game.minigolf;

public class help implements Screen {    
    public minigolf game;
    public float xVelocity = 0;
    public float yVelocity = 0;

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
        game.batch.draw(game.ballImg, minigolf.ball.getX(), minigolf.ball.getY());
        game.batch.end();

        minigolf.ball.updatePos(xVelocity, yVelocity);
        minigolf.ball.walls(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if(minigolf.currentFrame - minigolf.startFrame >= .5){
            if(!Gdx.input.justTouched() && minigolf.shooting) {
                xVelocity = Gdx.input.getX() - minigolf.ball.getX();
                yVelocity = Gdx.graphics.getHeight() - Gdx.input.getY() - minigolf.ball.getY();
            }
        }
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
    public void dispose() {
        
    }
}