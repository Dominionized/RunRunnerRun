package ca.csf.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.gameobjects.Ground;
import ca.csf.gameobjects.Runner;

public class GameWorld {

    private Runner runner;
    private Rectangle groundRect;

    public GameWorld(){
        groundRect = new Rectangle();
        groundRect.set(0, 320-32, 480, 32);

        runner = new Runner(100, 320-32-54, 27, 54);
    }

    public void update(float delta){
        //Gdx.app.log("GameWorld", "update");
        runner.update(delta);

        if(Intersector.overlaps(runner.getBoundingRectagle(), groundRect)){
            runner.setVelocity(new Vector2(0,0));
            runner.setPosition(new Vector2(100, 320-32-54));
        }
    }

    public Runner getRunner(){
        return runner;
    }
}
