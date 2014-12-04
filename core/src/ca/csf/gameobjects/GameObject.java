package ca.csf.gameobjects;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    public Vector2 position;
    public Vector2 velocity;
    protected Vector2 acceleration;
    protected int width;
    protected int height;

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getVelocity(){ return velocity; }

    public void setVelocity(Vector2 velocity){ this.velocity = velocity; }

    public Vector2 getPosition(){ return position; }

    public void setPosition(Vector2 positon){ this.position = position; }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
