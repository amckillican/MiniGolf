package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

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
        // TODO Auto-generated method stub
        
    }
    @Override
    public void resize(int width, int height) {
		
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
    public void dispose() {
        batch.dispose();
		font.dispose();
        
    }

   
}
