package com.minigolf.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class minigolf extends Game {
	// global initialization of variables
	public static OrthographicCamera camera;
	public static ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public static BitmapFont font;
	
    public static Texture titleBG;

	// scrolling background position variables
	public static int bgPosX1 = 1360;
	public static int bgPosX2 = 0;
	
	// initiate before game starts
	@Override
	public void create() {
		// set the camera to the window resolution
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// initiating the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();

		// initializing shape renderer
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		// initializing font
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"));

		// initializing images
		batch = new SpriteBatch();
		titleBG = new Texture("gfx/Tiled/bg.png");
	}

	// rendering the game
	@Override
	public void render() {
		// clear the previous frame
		ScreenUtils.clear(0, 0, 0, 1);

		// drawing the background in the correct position
        batch.begin();
        batch.draw(titleBG, bgPosX1, 0);
        batch.draw(titleBG, bgPosX2, 0);
        batch.end();

        // scroll the backgrounds
        bgPosX1 -= 1;
        bgPosX2 -= 1;

        // "leap frog" the backgrounds for infinite scrolling effect
        if (bgPosX1 <= -1360) {
            bgPosX1 = 1360;
        } else if (bgPosX2 <= -1360) {
            bgPosX2 = 1360;
        }

        // alpha channel
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

        // drawing rectangle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, (float) 0.75);
    	shapeRenderer.rect(300, 252, 281, 100);
        shapeRenderer.rect(300, 62, 281, 100);
        shapeRenderer.rect(772, 252, 281, 100);
        shapeRenderer.rect(772, 62, 281, 100);
        shapeRenderer.end();

        // alpha channel end
        Gdx.gl.glDisable(GL30.GL_BLEND);

        // 9-hole hovering rendering
        if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 581) {
            if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0, 0, 0, 1);
                shapeRenderer.rect(300, 252, 281, 100);
                shapeRenderer.end();

                // if user clicks button
                if (Gdx.input.isTouched()) {
                    // gamestate = "ninehole";
                }
            }
        }

        // help hovering rendering
        if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 581) {
            if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0, 0, 0, 1);
                shapeRenderer.rect(300, 62, 281, 100);
                shapeRenderer.end();

                // if user click button
                if (Gdx.input.isTouched()) {
                    // gamestate = "help";
                }
            }
        }

        // endless hovering rendering
        if (Gdx.input.getX() >= 772 && Gdx.input.getX() <= 1053) {
            if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0, 0, 0, 1);
                shapeRenderer.rect(772, 252, 281, 100);
                shapeRenderer.end();

                // if user clicks button
                if (Gdx.input.isTouched()) {
                    // gamestate = "endless";
                }
            }
        }

        // exit hovering rendering
        if (Gdx.input.getX() >= 772 && Gdx.input.getX() <= 1053) {
            if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0, 0, 0, 1);
                shapeRenderer.rect(772, 62, 281, 100);
                shapeRenderer.end();
                // if user clicks button
                if (Gdx.input.isTouched()) {
                    Gdx.app.exit();
                }
            }
        }

        // display the text on the buttons
        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(batch, "NINE HOLE", 325, 327);
        font.draw(batch, "HELP", 385, 133);
        font.draw(batch, "ENDLESS", 810, 327);
        font.draw(batch, "EXIT", 865, 133);
        font.getData().setScale(1);
        font.setColor(Color.BLACK);
        font.draw(batch, "Created By: Adam Fischer, Ben Smith, Alex McKillican, Clinton Osawe", 5, 30);
        batch.end();
		
		// exiting the application
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
	}

	// closing resources for memory management
	@Override
	public void dispose() {
		batch.dispose();
		titleBG.dispose();
	}
}
