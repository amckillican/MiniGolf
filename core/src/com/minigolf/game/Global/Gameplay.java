package com.Minigolf.game.Global;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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
    public static float pointAngle = 0;
    public static String shotStr;
    public static World world;
	public static Body player, platform;
	public static Box2DDebugRenderer b2dr;

    public static void gameplay() {

        world = new World(new Vector2(0, 0), false);
		b2dr = new Box2DDebugRenderer();
		player = createPlayer(Minigolf.ball.getX(), Minigolf.ball.getY());
        b2dr.render(world, Minigolf.camera.combined);
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

        // string for displaying number of strokes
        shotStr = "STROKE: " + Minigolf.ball.getShots();

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

            try {
                // pythagorean theorem
                sideA = (Gdx.input.getX() - (Minigolf.ball.getX() + 8));
                sideB = ((765 - Gdx.input.getY()) - (Minigolf.ball.getY() + 8));
                powerLevel = (int) Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2)) / 2;
                // calculating the angle for the pointer
                pointAngle = (float) -(Math.atan2(-sideA, -sideB) * (180 / Math.PI));
            } catch (Exception e) {
            }

            // maximum power level
            if (powerLevel >= 64) {
                powerLevel = 64;
            }

            // croping power meter
            region = new TextureRegion(Minigolf.powerMeterFG, 0, 0, 16, powerLevel);

            // drawing power and arrow
            Minigolf.batch.draw(Minigolf.pointImgRegion, ballPosX, ballPosY, 8f, 8f, 16f, 64f, 1f, 1f, pointAngle);
            Minigolf.batch.draw(Minigolf.powerMeterBG, powerPosX, powerPosY);
            Minigolf.batch.draw(region, powerPosX, powerPosY);
            Minigolf.batch.draw(Minigolf.powerMeterOverlay, powerPosX, powerPosY);
        }

        Minigolf.batch.end();
    }
    public static Body createPlayer(int x, int y){
		Body pBody;
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x+8, y+8);
		def.fixedRotation = true;
		pBody = world.createBody(def);
		CircleShape shape = new CircleShape();
		shape.setRadius(8);
		pBody.createFixture(shape, 1.0f);//.setUserData(this);
		shape.dispose();
		return pBody;
	}
	public void update(float delta){
		world.step(1 / 60f, 6, 2);

	}
    public Body createPlatform(int x,int y){
        Body oBody;
        BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        def.fixedRotation = true;
        oBody = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(8,8);
        oBody.createFixture(shape, 1.0f).setUserData(this);
        shape.dispose();
        return oBody;
    }
}
