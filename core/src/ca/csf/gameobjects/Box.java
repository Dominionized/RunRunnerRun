package ca.csf.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Box extends Scrollable{


    private Rectangle boundingRectangle;

    public Box(float x, float y, int width, int height, float scrollSpeed) {

        super(x, y, width, height, scrollSpeed);
        this.boundingRectangle = new Rectangle(x,y,width,height);
    }

    public void update(float delta){
        super.update(delta);
        boundingRectangle.setPosition(position);
    }

    public void reset(float newX) {

        super.reset(newX);

    }

    public boolean collides(Runner runner) {
        if (position.x < runner.getX() + runner.getWidth()) {
            return (Intersector.overlaps(runner.getBoundingRectagle(), boundingRectangle)
                    || Intersector.overlaps(runner.getBoundingRectagle(), boundingRectangle)
                    || Intersector.overlaps(runner.getBoundingRectagle(), boundingRectangle) || Intersector
                    .overlaps(runner.getBoundingRectagle(), boundingRectangle));
        }
        return false;
    }


}
