package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.Minigolf.game.Global.Gameplay;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    ArrayList<Integer> nums = new ArrayList<>();
    Texture darkTileIMG = new Texture("gfx/darktile.png");
    Texture sand1 = new Texture("gfx/Sand1.png");
    Texture sand2 = new Texture("gfx/Sand2.png");
    Texture sand3 = new Texture("gfx/Sand3.png");
    Texture BenFace = new Texture("gfx/backround.jpg");
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

        // Based on the level, create random coordinates
        for (int i = 0; i < (int) Math.pow(level * 2, 1.25); i += 2) {
            int x = rand.nextInt(1361);
            int y = rand.nextInt(766);
            // Check if level is completable, distance between blocks is larger than ball size
            for (int j = 0; j < nums.size(); j += 2) {
                if (x - nums.get(j) > -30 && x - nums.get(j) < 30) {
                    if (y - nums.get(j + 1) > -30 && y - nums.get(j + 1) < 30) {
                        track++;
                    }
                }
            }
            // Add co-ordinates to array
            if (track == 0) {
                nums.add(x);
                nums.add(y);
            } else {
                track = 0;
            }
            Minigolf.batch.begin();
            // Add sand pit co-ordinates
            if (start) {
                sandx = rand.nextInt(400);
                sandy = rand.nextInt(766);
                sand = rand.nextInt(3) + 1;
                start = false;
            }
            // Randomly choses a sandpit sprite and places it
            if(sand==1){
                Minigolf.batch.draw(sand1, sandx, sandy);
            }
            if(sand==2){
                Minigolf.batch.draw(sand2, sandx, sandy);
            }
            if(sand==3){
                Minigolf.batch.draw(sand3, sandx, sandy);
            }
            // Attempts to place block obstacle
            try {
                Minigolf.batch.draw(darkTileIMG, nums.get(i), nums.get(i + 1));
                // If there is no index in the array game doesn't crash
            } catch (Exception e) {
            }
            Minigolf.batch.end();
        }
        // If the level changes clear the array to place new blocks
        if (change != level) {
            // Collections.shuffle(nums);
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
        Minigolf.batch.begin();
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(1);
        Minigolf.font.draw(Minigolf.batch, "Level: " + level, 10, 720);
        Minigolf.batch.end();
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
}
