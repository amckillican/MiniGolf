package com.Minigolf.game.Global;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Gameplay {
    public static TextureRegion region;

    public static int powerPosX;
    public static int powerPosY;
    public static int ballPosX;
    public static int ballPosY;
    public static int powerLevel;
    public static int pointPosX;
    public static int pointPosY;
    public static int sideA;
    public static int sideB;
    public static float pointAngle;
    public static String shotStr;

    public static void gameplay() {
        // set the camera to the tmx map
        Minigolf.tiledMapRenderer.setView(Minigolf.camera);
        Minigolf.tiledMapRenderer.render();

        // ball physics
        Minigolf.ball.updatePos();
        Minigolf.ball.walls(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // if user clicks and drags on the ball
        // shoot the ball in the intended direction
        if (Minigolf.currentFrame - Minigolf.startFrame >= .5 && Minigolf.ball.getWin() == false) {
            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() >= Gameplay.ballPosX
                        && Gdx.input.getX() <= Gameplay.ballPosX + Minigolf.ball.getSize()) {
                    if (765 - Gdx.input.getY() >= Gameplay.ballPosY
                            && 765 - Gdx.input.getY() <= Gameplay.ballPosY + Minigolf.ball.getSize()) {
                        Minigolf.dragging = true;
                    }
                }
            } else if (Minigolf.shoot == true && Minigolf.dragging == true && Minigolf.ball.getXVelocity() == 0
                    && Minigolf.ball.getYVelocity() == 0) {
                Minigolf.ball.setXVelocity(-(Minigolf.mouseUpX - Minigolf.mouseDownX) / 2);
                Minigolf.ball.setYVelocity((Minigolf.mouseUpY - Minigolf.mouseDownY) / 2);
                Minigolf.ball.addShot();
                Minigolf.shoot = false;
                Minigolf.dragging = false;
            }
        }

        // getting ball position
        ballPosX = Minigolf.ball.getX();
        ballPosY = Minigolf.ball.getY();

        // if ball enters hole trigger win sequence
        if (ballPosX >= 1085 && ballPosX <= 1115) {
            if (ballPosY >= 375 && ballPosY <= 405) {
                Minigolf.ball.Win(1100, 375);
            }
        }

        // string for displaying number of strokes
        shotStr = "STROKE: " + Minigolf.ball.getShots();

        Minigolf.batch.begin();

        // displaying number of strokes
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(1);
        Minigolf.font.draw(Minigolf.batch, shotStr, 10, 750);

        // drawing ball and hole
        Minigolf.batch.draw(Minigolf.holeImg, 1100, 375);
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

            try {
                // pythagorean theorem
                sideA = (Gdx.input.getX() - Minigolf.ball.getX());
                sideB = ((765 - Gdx.input.getY()) - Minigolf.ball.getY());
                powerLevel = (int) Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
                // calculating the angle for the pointer
                pointAngle = (float) (Math.atan(Math.abs(sideA) / Math.abs(sideB)) * (180 / Math.PI));
            } catch (Exception e) {

            }

            // maximum power level
            if (powerLevel >= 64) {
                powerLevel = 64;
            }

            if (sideA > 0 && sideB > 0) {
                pointAngle = -pointAngle;
            } else if (sideA < 0 && sideB < 0) {
                pointAngle += 90;
            } else if (sideA > 0 && sideB < 0) {
                pointAngle = -pointAngle - 180;
            }

            // croping power meter
            region = new TextureRegion(Minigolf.powerMeterFG, 0, 0, 16, powerLevel);

            // drawing power and arrow
            Minigolf.batch.draw(Minigolf.pointImgRegion, ballPosX, ballPosY, 0f, 0f, 16f, 64f, 1f, 1f, pointAngle);
            Minigolf.batch.draw(Minigolf.powerMeterBG, powerPosX, powerPosY);
            Minigolf.batch.draw(region, powerPosX, powerPosY);
            Minigolf.batch.draw(Minigolf.powerMeterOverlay, powerPosX, powerPosY);
        }

        try {
            System.out.println("x: " + sideA + " y: " + sideB + " a: " + pointAngle);
        } catch (Exception e) {
        }

        Minigolf.batch.end();
    }
}