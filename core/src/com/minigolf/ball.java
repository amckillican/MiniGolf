package com.minigolf;

public class Ball {
    private int x;
    private int y;
    private float xVelocity;
    private float yVelocity;
    private float scale;
    private int size;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.scale = 1;
        this.size = 16;
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

    public float getXVelocity() {
        return this.xVelocity;
    }

    public void setXVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getYVelocity() {
        return this.yVelocity;
    }

    public void setYVelocity(float yVelocity) {
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

    public void updatePos(float xVelocity, float yVelocity){
        setX(this.x + (int) xVelocity);
        setY(this.y + (int) yVelocity);
    }

    public void walls(int screenWidth, int screenHeight){
        if(x >= screenWidth){
            setXVelocity(-this.xVelocity);
        }
        else if (x <= 0){
            setXVelocity(-this.xVelocity);
        }
        else if (y >= screenWidth){
            setYVelocity(-this.yVelocity);
        }
        else if (y <= 0){
            setYVelocity(-this.yVelocity);
        }
    }
}
