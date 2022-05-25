package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.Minigolf.game.Global.Ball;
import com.Minigolf.game.Global.Gameplay;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.*;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Endless implements Screen {
    public Minigolf game;
    public static int level = 1;
    public static int change = 1;
    public static boolean start = true;
    public static ShapeRenderer shapeRenderer;
    public static Batch batch;
    public static ArrayList<Integer> nums = new ArrayList<>();
    Texture darkTileIMG = new Texture("gfx/darktile.png");
    Texture sand1 = new Texture("gfx/Sand1.png");
    Texture sand2 = new Texture("gfx/Sand2.png");
    Texture sand3 = new Texture("gfx/Sand3.png");
    Texture BenFace = new Texture("gfx/bg.png");
    Random rand = new Random();
    public static int track = 0;
    public static BitmapFont font;
    public static int sand = 0;
    public static int sandx = 0;
    public static int sandy = 0;

    // create screen
    public Endless(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // Set backround
        Minigolf.batch.begin();
        Minigolf.batch.draw(BenFace, 0, 0);
        Minigolf.batch.end();

        // method for physics, ui, and other things that will be used in other screens
        Gameplay.gameplay();

        // initializing shape renderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        Minigolf.batch.begin();

        // Add sand pit co-ordinates
        if (start) {
            sandx = rand.nextInt(150) + 300;
            sandy = rand.nextInt(766);
            sand = rand.nextInt(3) + 1;
            start = false;
        }

        // Randomly choses a sandpit sprite
        if (sand == 1) {
            Minigolf.batch.draw(sand1, sandx, sandy);
        }
        if (sand == 2) {
            Minigolf.batch.draw(sand2, sandx, sandy);
        }
        if (sand == 3) {
            Minigolf.batch.draw(sand3, sandx, sandy);
        }

        // Based on the level, create random coordinates
        for (int i = 0; i < (int) Math.pow(level * 2, 1.25); i += 2) {
            int x = rand.nextInt(1361);
            int y = rand.nextInt(766);

            // Check if level is completable
            // distance between blocks is larger than ball size
            for (int j = 0; j < nums.size(); j += 2) {
                if (x - nums.get(j) > -35 && x - nums.get(j) < 35) {
                    if (y - nums.get(j + 1) > -35 && y - nums.get(j + 1) < 35) {
                                track++;
                    }
                }
                if(x - 205 > -15 && x - 205 < 15){
                    if(y - 375  > -15 && y - 375 < 15){
                        track++;
                    }
                }
            }

            // Add coordinates to array
            if (track == 0) {
                nums.add(x);
                nums.add(y);
            } else {
                track = 0;
            }

            // Attempts to place block obstacle
            try {
                Minigolf.batch.draw(darkTileIMG, nums.get(i), nums.get(i + 1));
                Body platform = createPlatform(nums.get(i), nums.get(i + 1));
                // If there is no index in the array game doesn't crash
            } catch (Exception e) {}
        }

        // If the level changes clear the array to place new blocks
        if (change != level) {
            nums.clear();
            change = level;
            start = true;
        }

        // if(level>?) - put in water trap
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            level++;
            System.out.println(level);
        }
        if (Gdx.input.isKeyPressed(Keys.P)) {
            level = 1;
            System.out.println(level);
        }

        // Show current level
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(1);
        Minigolf.font.draw(Minigolf.batch, "Level: " + level, 10, 720);
        Minigolf.batch.end();
        //for(int i = 0; i < nums.size(); i+=2){
        //    if(Ball.ball.getx() == nums.get(i) )
        //}

        //boolean isOverlaping = player.overlaps(obstacle);
        //if(isOverlaping) {
            
        //}
    }

    @Override
    public void dispose() {

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
    public void show() {

    }
    public Body createPlatform(int x,int y){
        Body oBody;
        BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        def.fixedRotation = true;
        oBody = Gameplay.world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8,8);
        oBody.createFixture(shape, 1.0f).setUserData(this);
        shape.dispose();
        return oBody;
    }
}
