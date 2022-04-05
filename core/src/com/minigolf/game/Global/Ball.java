package com.Minigolf.game.Global;

public class Ball {
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private float scale;
    private int size;
    private int friction;
    private int xmaxSpeed;
    private int ymaxSpeed;
    private int shots;
    private boolean win = false;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        xVelocity = 0;
        yVelocity = 0;
        scale = 1;
        size = 16;
        friction = 2;
        xmaxSpeed = 64;
        ymaxSpeed = 36;
        shots = 0;
        win = false;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXVelocity() {
        return this.xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return this.yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getSize() {
        return this.size;
    }

    public int getFriction() {
        return friction;
    }

    public void setFriction(int friction) {
        this.friction = friction;
    }

    public boolean getWin() {
        return win;
    }

    public void Win(int x, int y) {
        win = true;
        this.x = x;
        this.y = y;
        xVelocity = 0;
        yVelocity = 0;
    }

    public int getShots() {
        return shots;
    }

    public void addShot() {
        shots++;
    }

    public void setBall(int x, int y) {
        this.x = x;
        this.y = y;
        xVelocity = 0;
        yVelocity = 0;
        scale = 1;
        size = 16;
        friction = 2;
        xmaxSpeed = 64;
        ymaxSpeed = 36;
        shots = 0;
        win = false;
    }

    public void updatePos() {
        if (xVelocity > xmaxSpeed)
            xVelocity = xmaxSpeed;
        if (yVelocity > ymaxSpeed)
            yVelocity = ymaxSpeed;
        if (xVelocity < -xmaxSpeed)
            xVelocity = -xmaxSpeed;
        if (yVelocity < -ymaxSpeed)
            yVelocity = -ymaxSpeed;

        x += xVelocity;
        y += yVelocity;

        if (xVelocity > 0)
            xVelocity -= friction;
        if (xVelocity < 0)
            xVelocity += friction;
        if (yVelocity > 0)
            yVelocity -= friction;
        if (yVelocity < 0)
            yVelocity += friction;
        if (xVelocity >= -3 && xVelocity <= 3)
            xVelocity = 0;
        if (yVelocity >= -3 && yVelocity <= 3)
            yVelocity = 0;
    }

    public void walls(int screenWidth, int screenHeight) {
        if (x >= screenWidth) {
            x = screenWidth;
            xVelocity *= -1;
        } else if (x <= 0) {
            x = size;
            xVelocity *= -1;
        } else if (y >= screenHeight - size) {
            y = screenHeight - size;
            yVelocity *= -1;
        } else if (y <= 0) {
            y = 0;
            yVelocity *= -1;
        }
    }
}