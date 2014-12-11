package ca.csf.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.rrrhelpers.AssetLoader;


public class Runner extends GameObject implements Killable{

    private static final int JUMP_HEIGHT = 400;
    private Rectangle boundingRectagle;
    private boolean isAlive;
    private boolean isJumping;
    private boolean isKicking;
    private float pixelDistance;
    private float kickTime;
    private final float KICK_DURATION = 30;
    private float distance;
    private final int speed = 5;

    public Runner(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        boundingRectagle = new Rectangle();
        boundingRectagle.setSize(16, height);

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 981);
        AssetLoader.gameMusic.loop();
        isAlive = true;
        pixelDistance = 0;
        kickTime = 0;
        isKicking = false;
        distance = 0;
    }

    public Boolean isKicking(){
        return isKicking;
    }

    public int getDistance() {
        return (int) distance;
    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        boundingRectagle.setPosition(position.x + 24, position.y);

        pixelDistance += speed*delta;

        if(isKicking){
            kickTime += delta;
            if(kickTime == KICK_DURATION){
                isKicking = false;
                kickTime = 0;
            }
        }
        distance += speed*delta;
    }

    public void onClick() {
        if(velocity.y == 0) {
            velocity.y = -JUMP_HEIGHT;
            isJumping = true;
        }

    }

    public void onKick(){
        isKicking = true;
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
    public Rectangle getBoundingRectagle(){
        return boundingRectagle;
    }
    public Boolean isAlive(){ return isAlive; }
    public Boolean getIsJumping(){return this.isJumping;}
    public void setIsJumping(boolean isJumping){ this.isJumping = isJumping; }

    @Override
    public void onKilled() {
        this.isAlive = false;
        AssetLoader.gameMusic.stop();
        System.out.println("touch");
    }
}
