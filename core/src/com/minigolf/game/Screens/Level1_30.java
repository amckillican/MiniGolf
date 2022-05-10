package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.Minigolf.game.Global.Gameplay;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Level1_30 implements Screen {
    public Minigolf game;

    public static int messageX;

    // create screen
    public Level1_30(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // method for physics, ui, and other things that will be used in other screens
        // set the camera to the tmx map
        Minigolf.batch.begin();
        Minigolf.batch.draw(Minigolf.level1BGImg, 0, 0);
        Minigolf.batch.draw(Minigolf.stockLineImg, 0, 40);
        Minigolf.batch.draw(Minigolf.crashImg, 525, 150);
        Minigolf.batch.draw(Minigolf.level1HoleImg, 1100, 375);
        Minigolf.batch.end();

        Gameplay.gameplay();

        // if ball enters hole trigger win sequence
        if (Gameplay.ballPosX >= 1085 && Gameplay.ballPosX <= 1115) {
            if (Gameplay.ballPosY >= 375 && Gameplay.ballPosY <= 410) {
                Minigolf.startFrame = Minigolf.currentFrame;
                Minigolf.ball.Win(1100, 375);

                // transparancy
                Gdx.gl.glEnable(GL30.GL_BLEND);
                Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

                // drawing rectangle
                Minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                Minigolf.shapeRenderer.setColor(0, 0, 0, (float) 0.75);
                Minigolf.shapeRenderer.rect(0, 0, 1360, 765);
                Minigolf.shapeRenderer.end();

                // transparancy
                Gdx.gl.glDisable(GL30.GL_BLEND);

                Minigolf.batch.begin();
                Minigolf.font.getData().setScale(4);
                Minigolf.font.draw(Minigolf.batch, "Fantastic!", 450, messageX);
                Minigolf.batch.end();
            }
        }

        // debugging
        System.out.print("x: " + Gameplay.ballPosX);
        System.out.print(" y: " + (765 - Gameplay.ballPosY));
        System.out.print(" xV: " + Minigolf.ball.getXVelocity());
        System.out.println(" yV: " + Minigolf.ball.getYVelocity());
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
