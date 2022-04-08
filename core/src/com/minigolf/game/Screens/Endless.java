package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.Minigolf.game.Global.Gameplay;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.*;

public class Endless implements Screen {
    public Minigolf game;
    public static int level = 1000;
    public static ShapeRenderer shapeRenderer;
    public static ShapeRenderer batch;
    Texture darkTileIMG = new Texture("gfx/darktile.png");
    Random rand = new Random();

    
    // create screen
    public Endless(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // method for physics, ui, and other things that will be used in other screens
        Gameplay.gameplay();
        // initializing shape renderer
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
        if (Minigolf.currentFrame == Minigolf.startFrame){
            for(int i = 0; i < Math.ceil(level/2); i++){
                int x = rand.nextInt(1361);
                int y = rand.nextInt(766);
                Minigolf.batch.begin();
                Minigolf.batch.draw(darkTileIMG, x, y);
                Minigolf.batch.end();
            }   
        }
        

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
