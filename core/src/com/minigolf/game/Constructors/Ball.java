package com.Minigolf.game.Constructors;

public class Ball {
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private float scale;
    private int size;
    private int friction;
    private int maxSpeed;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.scale = 1;
        this.size = 16;
        this.friction = 2;
        this.maxSpeed = 80;
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

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void updatePos() {
        if (xVelocity > maxSpeed)
            xVelocity = maxSpeed;
        if (yVelocity > maxSpeed)
            yVelocity = maxSpeed;
        if (xVelocity < -maxSpeed)
            xVelocity = -maxSpeed;
        if (yVelocity < -maxSpeed)
            yVelocity = -maxSpeed;

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
        } else if (y + size >= screenHeight) {
            y = screenHeight;
            yVelocity *= -1;
        } else if (y <= size) {
            y = size;
            yVelocity *= -1;
        }
    }
}