package com.minigolf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

public class help extends Game {
    // global initialization of variables
	public static OrthographicCamera camera;
	public static ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public static BitmapFont font;
	
    public static Texture titleBG;
    public static Texture ballImg;
    public static Texture powerMeter;
    public static Texture powerMeterOverlay;
    public static Texture powerMeterBar;
	public static TiledMap tiledMap;
	public static TiledMapRenderer tiledMapRenderer;

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
		ballImg = new Texture("gfx/ball.png");
		powerMeter = new Texture("gfx/powermeter_bg.png");
		powerMeterOverlay = new Texture("gfx/powermeter_overlay.png");
		powerMeterBar = new Texture("gfx/powermeter_fg.png");

		// initializing tiledMap
		tiledMap = new TmxMapLoader().load("gfx/Tiled/help.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void render() {
        Ball ball = new Ball(205, 375);

        boolean win = false;
        boolean touched = false;

        Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.begin();
        batch.draw(ballImg, ball.getX(), ball.getY());
        batch.end();

        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() >= ball.getX() && Gdx.input.getX() <= ball.getX() + ball.getSize()) {
                if (Gdx.input.getY() >= ball.getY() && Gdx.input.getY() <= ball.getY() + ball.getSize()) {
                    touched = true;
                }
            }
        } else if (!Gdx.input.isTouched()) {
            touched = false;
        }

        if (touched) {
            batch.begin();
            batch.draw(powerMeter, ball.getX() - 40, ball.getY() - 25);
            batch.end();
        }
    }

    @Override
    public void dispose() {

    }
}
