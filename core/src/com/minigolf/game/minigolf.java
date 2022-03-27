package com.Minigolf.game;

import com.Minigolf.game.Constructors.Ball;
import com.Minigolf.game.Screens.help;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Minigolf extends Game implements InputProcessor {
	public static Ball ball = new Ball(205, 375);

	public OrthographicCamera camera;
	public TiledMapRenderer tiledMapRenderer;
	public TiledMap tiledMap;
	public ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public BitmapFont font;

	public Texture bg;
	public Texture powerMeterBG;
	public Texture powerMeterFG;
	public Texture powerMeterOverlay;
	public Texture ballImg;

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

		// used for rendering the tile maps
		tiledMap = new TmxMapLoader().load("gfx/Tiled/help.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		batch = new SpriteBatch();
		bg = new Texture("gfx/Tiled/bg.png");
		powerMeterBG = new Texture("gfx/powermeter_bg.png");
		powerMeterFG = new Texture("gfx/powermeter_fg.png");
		powerMeterOverlay = new Texture("gfx/powermeter_overlay.png");
		ballImg = new Texture("gfx/ball.png");
	}

	@Override
	public void dispose() {
		System.out.println("Dispose");
		batch.dispose();
		bg.dispose();
		font.dispose();
		shapeRenderer.dispose();
		tiledMap.dispose();
	}

	@Override
	public void render() {
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

			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

			if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 582) {
				if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
					shapeRenderer.rect(300, 252, 281, 100);
				}
			}

			if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 582) {
				if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
					shapeRenderer.rect(300, 62, 281, 100);

					if (Gdx.input.isTouched()) {
						gamestate = "help";
						startFrame = currentFrame;
						this.setScreen(new help(this));
					}
				}
			}

			if (Gdx.input.getX() >= 772 && Gdx.input.getX() <= 953) {
				if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
					shapeRenderer.rect(772, 252, 281, 100);
				}
			}

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
				System.out.println("Close");
				Gdx.app.exit();
			}
		}

		if (!gamestate.equals("title")) {
			super.render();
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouseDownX = screenX;
		mouseDownY = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
