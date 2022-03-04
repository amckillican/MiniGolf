package com.minigolf;

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
        this.friction = 1;
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

        setX(x + xVelocity);
        setY(y + yVelocity);

        if (xVelocity > 0)
            xVelocity -= friction;
        if (xVelocity < 0)
            xVelocity += friction;
        if (yVelocity > 0)
            yVelocity -= friction;
        if (yVelocity < 0)
            yVelocity += friction;
    }

    public void walls(int screenWidth, int screenHeight) {
        if (x >= screenWidth - size) {
            setXVelocity(-this.xVelocity);
        } else if (x <= 0) {
            setXVelocity(-this.xVelocity);
        } else if (y >= screenHeight - size) {
            setYVelocity(-this.yVelocity);
        } else if (y <= 0) {
            setYVelocity(-this.yVelocity);
        }
    }
}
