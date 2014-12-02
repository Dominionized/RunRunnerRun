package ca.csf.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable extends GameObject{

    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.position = new Vector2(x,y);
        this.velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));

        // If the scrollable object is no longer visible
        if (position.x + width < 0){
            isScrolledLeft = false;
        }
    }

    public boolean isScrolledLeft(){
        return this.isScrolledLeft;
    }

    public float getTailX() {
        return position.x + width;
    }
}
