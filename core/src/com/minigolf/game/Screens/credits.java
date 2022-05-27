package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;

//import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class credits implements Screen {
    
    public Minigolf credits;
    public static ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public static BitmapFont font;

    public credits (Minigolf game) {
        this.credits = game;
    

    }
    @Override
    public void render(float delta) {
        

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
        batch.dispose();
		font.dispose();
        
    }

   
}
