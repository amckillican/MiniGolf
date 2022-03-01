package com.minigolf;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Color;
import com.minigolf.game.minigolf;

public class titlescreen {
    // scrolling background position variables
    static int bgPosX1 = 1360;
    static int bgPosX2 = 0;

    public static void titleScreen() {
        // drawing the background in the correct position
        minigolf.batch.begin();
        minigolf.batch.draw(minigolf.titleBG, bgPosX1, 0);
        minigolf.batch.draw(minigolf.titleBG, bgPosX2, 0);
        minigolf.batch.end();

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
        minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        minigolf.shapeRenderer.setColor(0, 0, 0, (float) 0.75);
        minigolf.shapeRenderer.rect(300, 252, 281, 100);
        minigolf.shapeRenderer.rect(300, 62, 281, 100);
        minigolf.shapeRenderer.rect(772, 252, 281, 100);
        minigolf.shapeRenderer.rect(772, 62, 281, 100);
        minigolf.shapeRenderer.end();

        // alpha channel end
        Gdx.gl.glDisable(GL30.GL_BLEND);

        // 9-hole hovering rendering
        if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 581) {
            if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
                minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                minigolf.shapeRenderer.setColor(0, 0, 0, 1);
                minigolf.shapeRenderer.rect(300, 252, 281, 100);
                minigolf.shapeRenderer.end();

                // if user clicks button
                if (Gdx.input.isTouched()) {
                    minigolf.gamestate = "ninehole";
                }
            }
        }

        // help hovering rendering
        if (Gdx.input.getX() >= 300 && Gdx.input.getX() <= 581) {
            if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
                minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                minigolf.shapeRenderer.setColor(0, 0, 0, 1);
                minigolf.shapeRenderer.rect(300, 62, 281, 100);
                minigolf.shapeRenderer.end();

                // if user click button
                if (Gdx.input.isTouched()) {
                    minigolf.gamestate = "help";
                }
            }
        }

        // endless hovering rendering
        if (Gdx.input.getX() >= 772 && Gdx.input.getX() <= 1053) {
            if (Gdx.input.getY() >= 413 && Gdx.input.getY() <= 513) {
                minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                minigolf.shapeRenderer.setColor(0, 0, 0, 1);
                minigolf.shapeRenderer.rect(772, 252, 281, 100);
                minigolf.shapeRenderer.end();

                // if user clicks button
                if (Gdx.input.isTouched()) {
                    minigolf.gamestate = "endless";
                }
            }
        }

        // exit hovering rendering
        if (Gdx.input.getX() >= 772 && Gdx.input.getX() <= 1053) {
            if (Gdx.input.getY() >= 603 && Gdx.input.getY() <= 703) {
                minigolf.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                minigolf.shapeRenderer.setColor(0, 0, 0, 1);
                minigolf.shapeRenderer.rect(772, 62, 281, 100);
                minigolf.shapeRenderer.end();

                // if user clicks button
                if (Gdx.input.isTouched()) {
                    minigolf.gamestate = "exit";
                }
            }
        }

        // display the text on the buttons
        minigolf.batch.begin();
        minigolf.font.setColor(Color.WHITE);
        minigolf.font.getData().setScale(2);
        minigolf.font.draw(minigolf.batch, "NINE HOLE", 325, 327);
        minigolf.font.draw(minigolf.batch, "HELP", 385, 133);
        minigolf.font.draw(minigolf.batch, "ENDLESS", 810, 327);
        minigolf.font.draw(minigolf.batch, "EXIT", 865, 133);
        minigolf.font.getData().setScale(1);
        minigolf.font.setColor(Color.BLACK);
        minigolf.font.draw(minigolf.batch, "Created By: Adam Fischer, Ben Smith, Alex McKillican, Clinton Osawe", 5, 30);
        minigolf.batch.end();

        // exiting the application
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
