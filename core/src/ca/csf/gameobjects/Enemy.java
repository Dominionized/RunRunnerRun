package ca.csf.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Enemy extends Scrollable implements Killable {
    private Rectangle boundingRectangle;

    private Boolean isAlive;
    private Random random = new Random();

    public Enemy(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        isAlive = true;

        boundingRectangle = new Rectangle(x, y, width, height);
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void update(float delta){
        super.update(delta);
        boundingRectangle.setPosition(position);

        if(!isAlive){
            reset(random.nextInt(1000)+600);
            isAlive = true;
        }
    }

    @Override
    public void onKilled() {
        isAlive = false;
    }

    public Boolean isAlive() {
        return isAlive;
    }
}
