package com.Minigolf.game.Screens;


import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.Input.Keys;
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
        
        Gdx.gl.glDisable(GL30.GL_BLEND);

        Minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Minigolf.shapeRenderer.setColor(0, 0,1, 1);
        Minigolf.shapeRenderer.rect(1000, 50, 281, 100);
        Minigolf.shapeRenderer.end();

        Minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Minigolf.shapeRenderer.setColor(0, 0,1, 1);
        Minigolf.shapeRenderer.rect(100, 50, 281, 100);
        Minigolf.shapeRenderer.end();
    
        
		Minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        if (Gdx.input.getX() >= 1000 && Gdx.input.getX() <= 1285) {
            if (Gdx.input.getY() >= 595 && Gdx.input.getY() <= 703) {
                Minigolf.shapeRenderer.rect(990, 45, 300, 110);
                if (Gdx.input.isTouched()) {
                    Gdx.app.exit();
                }
            }
        }

        Gdx.gl.glDisable(GL30.GL_BLEND);
        if (Gdx.input.getX() >= 100 && Gdx.input.getX() <= 385) {
            if (Gdx.input.getY() >= 595 && Gdx.input.getY() <= 703) {
                Minigolf.shapeRenderer.rect(90, 45, 300, 110);
                if (Gdx.input.isTouched()) {
                    Minigolf.gamestate = "title";
					Minigolf.startFrame = Minigolf.currentFrame;
				
                }
            }
        }
        Gdx.gl.glDisable(GL30.GL_BLEND);
        Minigolf.shapeRenderer.end();

        Minigolf.batch.begin();
        Minigolf.font.setColor(1, 1,1, 1);
		Minigolf.font.getData().setScale(2);
		Minigolf.font.draw(Minigolf.batch, "EXIT", 1090 , 120);
        Minigolf.batch.end();

        if (Gdx.input.isKeyPressed(Keys.ESCAPE) && Minigolf.currentFrame - Minigolf.startFrame >= .5) {
            System.out.println("Close");
            Gdx.app.exit();
        }
        if (Gdx.input.isKeyPressed(Keys.B) && Minigolf.currentFrame - Minigolf.startFrame >= .5) {
            Minigolf.gamestate = "title";
			Minigolf.startFrame = Minigolf.currentFrame;
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
