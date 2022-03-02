package com.minigolf.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.minigolf.endless;
import com.minigolf.help;
import com.minigolf.ninehole;
import com.minigolf.titlescreen;
import com.minigolf.ball;

public class minigolf extends ApplicationAdapter {
	// global initialization of variables
	public static OrthographicCamera camera;
	public static ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public static BitmapFont font;
	
    public static Texture titleBG;
	public static String gamestate;
	public static TiledMap tiledMap;
	public static TiledMapRenderer tiledMapRenderer;

	// initiate before game starts
	@Override
	public void create() {
		// setting window title
		Gdx.graphics.setTitle("MiniGolf");

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

		// initializing tiledMap
		tiledMap = new TmxMapLoader().load("gfx/Tiled/help.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		// starting game state
		gamestate = "title";
	}

	// rendering the game
	@Override
	public void render() {
		// clear the previous frame
		ScreenUtils.clear(0, 0, 0, 1);

		// chosing the game mode
		switch (gamestate) {
			case "title" -> titlescreen.titleScreen();
			case "ninehole" -> ninehole.nineHole();
			case "endless" -> endless.endlessScreen();
			case "help" -> help.helpScreen();
			default -> Gdx.app.exit();
		}
	}

	// closing resources for memory management
	@Override
	public void dispose() {
		batch.dispose();
		titleBG.dispose();
	}
}
