package com.Minigolf.game;

import com.Minigolf.game.Global.Ball;
import com.Minigolf.game.Screens.help;
import com.Minigolf.game.Screens.Endless;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Minigolf extends Game implements InputProcessor {
	// static objects
	public static Ball ball = new Ball(205, 375);
	public static OrthographicCamera camera;
	public static ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public static BitmapFont font;

	// textures
	public static Texture bg;
	public static Texture lightTileIMG;
	public static Texture darkTileIMG;
	public static Texture powerMeterBG;
	public static Texture powerMeterFG;
	public static Texture powerMeterOverlay;
	public static Texture ballImg;
	public static Texture holeImg;
	public static Texture pointImg;
    public static TextureRegion pointImgRegion;

	// variables
	public static int mouseDownX = 0;
	public static int mouseDownY = 0;
	public static int mouseUpX = 0;
	public static int mouseUpY = 0;
	public String gamestate = "title";
	public static float currentFrame = 0;
	public static float startFrame = 0;
	public static boolean shoot = false;
	public static boolean dragging = false;

	int bgPos1 = -1360;
	int bgPos2 = 0;

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

		// used for taking keyboard/mouse inputs
		Gdx.input.setInputProcessor(this);

		// textures
		batch = new SpriteBatch();
		bg = new Texture("gfx/bg.png");
		lightTileIMG = new Texture("gfx/lighttile.png");
		darkTileIMG = new Texture("gfx/darktile.png");
		powerMeterBG = new Texture("gfx/powermeter_bg.png");
		powerMeterFG = new Texture("gfx/powermeter_fg.png");
		powerMeterOverlay = new Texture("gfx/powermeter_overlay.png");
		ballImg = new Texture("gfx/ball.png");
		holeImg = new Texture("gfx/hole.png");
		pointImg = new Texture("gfx/point.png");
		pointImgRegion = new TextureRegion(pointImg, 16, 64);
	}

	@Override
	public void dispose() {
		// dispose the textures
		System.out.println("Dispose");
		batch.dispose();
		bg.dispose();
		font.dispose();
		shapeRenderer.dispose();
	}

	@Override
	public void render() {
		// clear the previous frame
		ScreenUtils.clear(0, 0, 0, 1);

		// keep track of the game time in seconds
		currentFrame += Gdx.graphics.getDeltaTime();

		if (gamestate == "title") {
			// scrolling the background infinitely
			bgPos1 += 1;
			bgPos2 += 1;

			if (bgPos2 >= 1360) {
				bgPos2 = -1360;
			} else if (bgPos1 >= 1360) {
				bgPos1 = -1360;
			}

			// drawing the sprites
			batch.begin();
			batch.draw(bg, bgPos1, 0);
			batch.draw(bg, bgPos2, 0);
			batch.end();

			// transparancy
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
			
			// transparancy
			Gdx.gl.glDisable(GL30.GL_BLEND);
			
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			
			// hovering 9-hole button
			if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 582) {
				if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
					shapeRenderer.rect(300, 252, 281, 100);
				}
			}

			// hovering help button
			if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 582) {
				if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
					shapeRenderer.rect(300, 62, 281, 100);

					if (Gdx.input.isTouched()) {
						gamestate = "help";
						startFrame = currentFrame;
						ball.setBall(205, 375);
						this.setScreen(new help(this));
					}
				}
			}

			// hovering endless button
			if (Gdx.input.getX() >= 772 && Gdx.input.getX() <= 953) {
				if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
					shapeRenderer.rect(772, 252, 281, 100);
					if (Gdx.input.isTouched()) {
						gamestate = "Endless";
						startFrame = currentFrame;
						ball.setBall(205, 375);
						this.setScreen(new Endless(this));
					}
				}
			}

			// hovering exit button
			if (Gdx.input.getX() >= 772 && Gdx.input.getX() <= 953) {
				if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
					shapeRenderer.rect(772, 62, 281, 100);
					if (Gdx.input.isTouched()) {
						Gdx.app.exit();
					}
				}
			}

			shapeRenderer.end();

			// display the text on the buttons
			batch.begin();
			font.setColor(1, 1, 1, 1);
			font.getData().setScale(2);
			font.draw(batch, "NINE HOLE", 325, 327);
			font.draw(batch, "HELP", 385, 133);
			font.draw(batch, "ENDLESS", 810, 327);
			font.draw(batch, "EXIT", 865, 133);
			font.getData().setScale(1);
			font.setColor(0, 0, 0, 1);
			font.draw(batch, "Created By: Adam Fischer, Ben Smith, Alex McKillican, Clinton Osawe", 5, 30);
			batch.end();

			// exiting the application
			if (Gdx.input.isKeyPressed(Keys.ESCAPE) && Minigolf.currentFrame - Minigolf.startFrame >= .5) {
				System.out.println("Close");
				Gdx.app.exit();
			}
		}

		// render the game
		if (!gamestate.equals("title")) {
			super.render();
			if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				startFrame = currentFrame;
				gamestate = "title";
			}
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// get the mouse position
		mouseDownX = screenX;
		mouseDownY = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// get mouse position
		mouseUpX = screenX;
		mouseUpY = screenY;
		shoot = true;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
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
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
