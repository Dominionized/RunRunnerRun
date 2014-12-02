package ca.csf.gameobjects;

import com.badlogic.gdx.math.Vector2;

import java.awt.Rectangle;

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

        if (velocity.y >= 200) {
            velocity.y = 200;
        }

        position.add(velocity.cpy().scl(delta));

        boundingRectagle.setLocation((int)position.x, (int)position.y);


    }

    public void onClick() {
        velocity.y = -300;
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
