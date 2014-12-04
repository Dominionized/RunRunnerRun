package ca.csf.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.rrrhelpers.AssetLoader;


public class Runner extends GameObject{

    private Rectangle boundingRectagle;

    public Runner(float x, float y, int width, int height){
        this.width = width;
        this.height = height;
        boundingRectagle = new Rectangle();
        boundingRectagle.setSize(width, height);

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));


        position.add(velocity.cpy().scl(delta));

        boundingRectagle.setPosition(position.x, position.y);
    }

    public void onClick() {
        if(velocity.y == 0) {
            velocity.y = -300;
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

    public Rectangle getBoundingRectagle(){
        return boundingRectagle;
    }
}
