package ca.csf.rrr.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Enemy extends Scrollable implements Killable {
    private Rectangle boundingRectangle;

    private static final int ENEMY_MIN_GAP = 600;
    private static final int ENEMY_MAX_GAP = 1000;

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

    public void update(float delta) {
        super.update(delta);
        boundingRectangle.setPosition(position);

        if (!isAlive) {
            reset(random.nextInt(ENEMY_MAX_GAP) + ENEMY_MIN_GAP);
            isAlive = true;
        }
    } //

    @Override
    public void onKilled() {
        isAlive = false;

    }

    public Boolean isAlive() {
        return isAlive;
    }
}
