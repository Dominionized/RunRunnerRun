package ca.csf.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Scrollable implements Killable {
    public Rectangle bouncingRectangle;
    public Boolean isAlive;

    public Enemy(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        isAlive = true;
    }

    public Rectangle getBouncingRectangle() {
        return bouncingRectangle;
    }

    @Override
    public void onKilled() {
        isAlive = false;
    }
}
