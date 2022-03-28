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

    public static void gameplay() {
        // set the camera to the tmx map
        Minigolf.tiledMapRenderer.setView(Minigolf.camera);
        Minigolf.tiledMapRenderer.render();

        // ball physics
        Minigolf.ball.updatePos();
        Minigolf.ball.walls(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // if user clicks and drags on the ball
        // shoot the ball in the intended direction
        if (Minigolf.currentFrame - Minigolf.startFrame >= .5) {
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
        
        ballPosX = Minigolf.ball.getX();
        ballPosY = Minigolf.ball.getY();
        
        if(ballPosX >= 1100 && ballPosX <= 1116){
            if(ballPosY >= 350 && ballPosY <= 369){
                Minigolf.ball.setWin(true);
                System.out.println("win");
            }
        }

        // string for displaying number of strokes
        String shotStr = "STROKE: " + Minigolf.ball.getShots();

        Minigolf.batch.begin();
        
        // displaying number of strokes
        Minigolf.font.setColor(1, 1, 1, 1);
        Minigolf.font.getData().setScale(1);
        Minigolf.font.draw(Minigolf.batch, shotStr, 10, 750);
        
        // drawing ball and hole
        Minigolf.batch.draw(Minigolf.ballImg, ballPosX, ballPosY);
        Minigolf.batch.draw(Minigolf.holeImg, 1100, 375);

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
            if (powerLevel >= 64) {
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
