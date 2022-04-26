package com.Minigolf.game.Screens;


import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;



public class leaderboard implements Screen {
    public Minigolf leaderboard;
    //public static SpriteBatch batch;
    //public static BitmapFont font;

    public leaderboard (Minigolf game) {
        this.leaderboard = game;
    
        
    }
    public void render(float delta) {
    

        
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

        Minigolf.batch.begin();
        Minigolf.font.setColor(0, 0,0, 1);
		Minigolf.font.getData().setScale(2);
		Minigolf.font.draw(Minigolf.batch, "EXIT", 1000, 100);
        Minigolf.batch.end();
        
        Minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Minigolf.shapeRenderer.setColor(1, 1,1, 1);
        Minigolf.shapeRenderer.rect(1000, 62, 281, 100);
        Minigolf.shapeRenderer.end();
    
        Gdx.gl.glDisable(GL30.GL_BLEND);
			
		Minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (Gdx.input.getX() >= 550 && Gdx.input.getX() <= 835) {
            if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
                Minigolf.shapeRenderer.rect(550, 62, 281, 100);
                if (Gdx.input.isTouched()) {
                    Gdx.app.exit();
                }
            }
        }
        Minigolf.shapeRenderer.end();
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
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
}
