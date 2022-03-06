/*
*
* Current Notes For Game:
* - Game crashes when moving ball on help menu
* - Cause unknown as there is no error message on crash
* - Restart computer after crash or memory will build up and not be released
*   - Will cause bluescreens/system crashes
*
*/

package com.minigolf.game;

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
import com.minigolf.Ball;
import com.minigolf.screens.endless;
import com.minigolf.screens.help;
import com.minigolf.screens.ninehole;

public class minigolf extends Game implements InputProcessor {
    // objects
    public OrthographicCamera camera;
    public TiledMapRenderer tiledMapRenderer;
    public TiledMap tiledMap;
    public ShapeRenderer shapeRenderer;

    // static objects
    public static SpriteBatch batch;
    public static BitmapFont font;

    // static constructors
    public static Ball ball = new Ball(205, 375);

    // textures
    public Texture titleBG;
    public Texture powerMeterBG;
    public Texture powerMeterFG;
    public Texture powerMeterOverlay;
    public Texture ballImg;

    // static variables
    public static float currentFrame = 0;
    public static float startFrame = 0;
    public static boolean win = false;
    public static boolean shooting = false;

    // variables
    public String gamestate = "title";
    public int bgPosX1 = 1360;
    public int bgPosX2 = 0;

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

        // used for taking keyboard/mouse inputs
        Gdx.input.setInputProcessor(this);

        // used for rendering the tile maps
        tiledMap = new TmxMapLoader().load("gfx/Tiled/help.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        // initializing images
        batch = new SpriteBatch();
        titleBG = new Texture("gfx/Tiled/bg.png");
        powerMeterBG = new Texture("gfx/powermeter_bg.png");
        powerMeterFG = new Texture("gfx/powermeter_fg.png");
        powerMeterOverlay = new Texture("gfx/powermeter_overlay.png");
        ballImg = new Texture("gfx/ball.png");
    }

    // closing resources for memory management
    @Override
    public void dispose() {
        batch.dispose();
        titleBG.dispose();
        powerMeterBG.dispose();
        powerMeterFG.dispose();
        powerMeterOverlay.dispose();
        ballImg.dispose();
    }

    // rendering the game
    @Override
    public void render() {
        // keep track of the game time in seconds
        currentFrame += Gdx.graphics.getDeltaTime();

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
                    this.setScreen(new ninehole(this));
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
                    gamestate = "help";
                    startFrame = currentFrame;
                    this.setScreen(new help(this));
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
                    this.setScreen(new endless(this));
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

        if (!gamestate.equals("title")) {
            super.render();
        }
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (gamestate != "tile") {
            if (ball.getXVelocity() == 0 && ball.getYVelocity() == 0 && !win) {
                shooting = true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        shooting = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
