package ca.csf.rrr.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.rrr.helpers.AssetLoader;


public class Runner extends GameObject implements Killable {

    private static final int JUMP_HEIGHT = 400;
    private static final float KICK_DURATION = 0.5f;
    private static final int SPEED = 5;
    private static final int GRAVITY = 981;
    private static final int RUNNER_SIZE_X = 16;
    private Rectangle boundingRectangle;
    private boolean isAlive;
    private boolean isJumping;
    private boolean isKicking;
    private float kickTime;
    private float distance;

    public Runner(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        boundingRectangle = new Rectangle();
        boundingRectangle.setSize(RUNNER_SIZE_X, height);

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, GRAVITY);
        isAlive = true;
        kickTime = 0;
        isKicking = false;
        distance = 0;
    }

    public void restart() {
        //Reset every possible value

        isAlive = true;
        isJumping = false;
        isKicking = false;

        distance = 0;

    }

    public Boolean isKicking() {
        return isKicking;
    }

    public int getDistance() {
        return (int) distance;
    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        boundingRectangle.setPosition(position.x + 24, position.y);

        if (isKicking) {
            kickTime += delta;
            if (kickTime >= KICK_DURATION) {
                isKicking = false;
                kickTime = 0;
            }
        }
        distance += SPEED * delta;
    }


    public void onJump() {

        if (velocity.y == 0) {
            velocity.y = -JUMP_HEIGHT;
            isJumping = true;
            AssetLoader.jumpingSound.play();
        }

    }

    public void onKick() {
        if (isAlive) {
            isKicking = true;
        }
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public Boolean getIsJumping() {
        return this.isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    @Override
    public void onKilled() {
        this.isAlive = false;
        System.out.println("touch");
    }
}
