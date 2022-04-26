package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.Minigolf.game.Global.Gameplay;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.*;

public class Endless implements Screen {
    public Minigolf game;
    public static int level = 1;
    public static int change = 1;
    public static ShapeRenderer shapeRenderer;
    public static ShapeRenderer batch;
    ArrayList<Integer> nums = new ArrayList<>();
    Texture darkTileIMG = new Texture("gfx/darktile.png");
    Random rand = new Random();
    public static int track = 0;

    // create screen
    public Endless(Minigolf game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        // method for physics, ui, and other things that will be used in other screens
        Gameplay.gameplay();
        // initializing shape renderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        // Based on the level, create random coordinates and place obsticals
        for (int i = 0; i < (int) Math.pow(level * 2, 1.25); i += 2) {
            int x = rand.nextInt(1361);
            int y = rand.nextInt(766);
            for (int j = 0; j < nums.size(); j += 2) {
                if (x - nums.get(j) > -30 && x - nums.get(j) < 30) {
                    if (y - nums.get(j + 1) > -30 && y - nums.get(j + 1) < 30) {
                        track++;
                    }
                }
            }
            if (track == 0) {
                nums.add(x);
                nums.add(y);
            } else
                track = 0;
            Minigolf.batch.begin();
            Minigolf.batch.draw(darkTileIMG, nums.get(i), nums.get(i + 1));
            Minigolf.batch.end();
        }
        if (change != level) {
            // Collections.shuffle(nums);
            nums.clear();
            change = level;
        }
        // if(level>?) - put in water trap
        // if(level>?) - put in sand trap
        // if(level>?) - put in trap trap
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            level++;
            System.out.println(level);
        }
        if (Gdx.input.isKeyPressed(Keys.P)) {
            level = 1;
            System.out.println(level);
        }
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
