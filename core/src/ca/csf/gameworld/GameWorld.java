package ca.csf.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.gameobjects.Box;
import ca.csf.gameobjects.Grass;
import ca.csf.gameobjects.Runner;
import ca.csf.gameobjects.ScrollHandler;

public class GameWorld {
    private Runner runner;

    public Rectangle getGroundRect() {
        return groundRect;
    }

    private Rectangle groundRect;
    private ScrollHandler scrollHandler;

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public GameWorld(){
        groundRect = new Rectangle();
        groundRect.set(0, 320-32, 480, 32);

        runner = new Runner(100, 320-32-108, 64, 64);

        scrollHandler = new ScrollHandler(this);

    }

    public void update(float delta){
        runner.update(delta);
        scrollHandler.update(delta);

        if(Intersector.overlaps(runner.getBoundingRectagle(), groundRect)){
            runner.setIsJumping(false);
            runner.setVelocity(new Vector2(0, 0));
            runner.setPosition(new Vector2(runner.getPosition().x, 320-32-64));
        }

        for(Box box : scrollHandler.getBoxList()){
            if(Intersector.overlaps(box.getBoundingRectangle(), runner.getBoundingRectagle())){
                runner.onKilled();
            }
        }

    }

    public Runner getRunner(){
        return runner;
    }
}
