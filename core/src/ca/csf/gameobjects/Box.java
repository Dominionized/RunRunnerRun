package ca.csf.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class Box extends Scrollable{


    private Rectangle boundingRectangle;

    public Box(float x, float y, int width, int height, float scrollSpeed) {

        super(x, y, width, height, scrollSpeed);
        this.boundingRectangle = new Rectangle(x,y,width,height);
    }

    public void update(float delta){
        boundingRectangle.setPosition(position);
    }


}
