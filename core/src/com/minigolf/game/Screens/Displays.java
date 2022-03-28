package com.Minigolf.game.Screens;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Displays {
    public static TextureRegion region;

    public static int powerPosX;
    public static int powerPosY;
    public static int ballPosX;
    public static int ballPosY;
    public static int powerLevel;

    public static void displays() {
        ballPosX = Minigolf.ball.getX();
        ballPosY = Minigolf.ball.getY();

        // string for displaying number of strokes
        String shotStr = "STROKE: " + Minigolf.ball.getShots();

        Minigolf.batch.begin();

        // displaying number of strokes
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(1);
        Minigolf.font.draw(Minigolf.batch, shotStr, 10, 750);

        // drawing ball
        Minigolf.batch.draw(Minigolf.ballImg, ballPosX, ballPosY);

        // where to render power meter
        if (Minigolf.dragging) {
            // render on the left size
            if (ballPosX > 64) {
                // render above the ball
                if (765 - ballPosY > 64) {
                    powerPosX = ballPosX - 32;
                    powerPosY = ballPosY;
                }
                // render below the ball
                if (765 - ballPosY <= 64) {
                    powerPosX = ballPosX - 32;
                    powerPosY = ballPosY - 64;
                }
            }
            // render on the right size
            else if (ballPosX <= 64) {
                // render above the ball
                if (765 - ballPosY > 64) {
                    powerPosX = ballPosX + 32;
                    powerPosY = ballPosY;
                }
                // render below the ball
                if (765 - ballPosY <= 64) {
                    powerPosX = ballPosX + 32;
                    powerPosY = ballPosY - 64;
                }
            }

            // pythagorean theorem
            int sideA = (Gdx.input.getX() - Minigolf.ball.getX()) / 2;
            int sideB = ((765 - Gdx.input.getY()) - Minigolf.ball.getY()) / 2;
            powerLevel = (int) Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));

            // maximum power level
            if(powerLevel >= 64){
                powerLevel = 64;
            }

            // croping power meter
            region = new TextureRegion(Minigolf.powerMeterFG, 0, 0, 16, powerLevel);

            // drawing power meter
            Minigolf.batch.draw(Minigolf.powerMeterBG, powerPosX, powerPosY);
            Minigolf.batch.draw(region, powerPosX, powerPosY);
            Minigolf.batch.draw(Minigolf.powerMeterOverlay, powerPosX, powerPosY);
        }

        Minigolf.batch.end();
    }
}
