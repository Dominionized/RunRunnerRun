package ca.csf.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.gameobjects.Grass;
import ca.csf.gameobjects.Runner;
import ca.csf.gameobjects.ScrollHandler;

public class GameWorld {
    private Runner runner;
    private Rectangle groundRect;
    private ScrollHandler scrollHandler;

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public GameWorld(){
        groundRect = new Rectangle();
        groundRect.set(0, 320-32, 480, 32);

        runner = new Runner(100, 320-32-108, 54, 108);

        scrollHandler = new ScrollHandler(this);

    }

    public void update(float delta){

        runner.update(delta);
        scrollHandler.update(delta);

        if(Intersector.overlaps(runner.getBoundingRectagle(), groundRect)){
            runner.setVelocity(new Vector2(0, 0));
            runner.setPosition(new Vector2(runner.getPosition().x, 320-32-108));
        }

    }

    public Runner getRunner(){
        return runner;
    }
}
