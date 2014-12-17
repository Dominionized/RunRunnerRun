package ca.csf.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.gameobjects.Box;
import ca.csf.gameobjects.Enemy;
import ca.csf.gameobjects.Runner;
import ca.csf.gameobjects.ScrollHandler;
import ca.csf.rrrhelpers.AssetLoader;

public class GameWorld {
//
    private GameRenderer gameRenderer;
    private Runner runner;
    private Enemy enemy;
    private Rectangle groundRect;
    private ScrollHandler scrollHandler;
    private GameState currentState;

    public GameWorld() {
        currentState = GameState.RUNNING;
        groundRect = new Rectangle();
        groundRect.set(0, 320 - 32, 480, 32);

        runner = new Runner(100, 320 - 32 - 108, 64, 64);

        scrollHandler = new ScrollHandler(this);
        enemy = scrollHandler.getEnemy();
        AssetLoader.gameMusic.loop();
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    public void setGameRenderer(GameRenderer gameRenderer) {
        this.gameRenderer = gameRenderer;
    }

    public Rectangle getGroundRect() {
        return groundRect;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public void restart() {
        scrollHandler.onRestart();
        runner.restart();
        runner.setPosition(new Vector2(runner.getPosition().x, 320 - 32 - 64));
        currentState = GameState.RUNNING;

        AssetLoader.dyingMusic.stop();
        AssetLoader.gameMusic.loop();

    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
        }

    }

    public void updateRunning(float delta) {

        if (runner.isAlive()) {

            runner.update(delta);
            scrollHandler.update(delta);


            if (Intersector.overlaps(runner.getBoundingRectangle(), groundRect)) {
                runner.setIsJumping(false);
                runner.setVelocity(new Vector2(0, 0));
                runner.setPosition(new Vector2(runner.getPosition().x, 320 - 32 - 64));
            }

            for (Box box : scrollHandler.getBoxList()) {
                if (Intersector.overlaps(box.getBoundingRectangle(), runner.getBoundingRectangle())) {
                    runner.onKilled();
                }

                if (Intersector.overlaps(box.getBoundingRectangle(), enemy.getBoundingRectangle())) {
                    enemy.onKilled();
                }
            }

            if (Intersector.overlaps(runner.getBoundingRectangle(), enemy.getBoundingRectangle()) && enemy.isAlive()) {
                if (runner.isKicking()) {
                    enemy.onKilled();
                } else {
                    runner.onKilled();
                }
            }

        } else {
            AssetLoader.gameMusic.stop();
            AssetLoader.dyingMusic.play();
            enemy.onKilled();
           runner.setPosition(new Vector2(runner.getPosition().x, 320));
            currentState = GameState.READY;

        }
    }

    public void updateReady(float delta) {


    }

    public boolean isReady() {
        return (currentState == GameState.READY);
    }

    public boolean isRunning() {
        return (currentState == GameState.RUNNING);
    }

    public Runner getRunner() {
        return runner;
    }

    public enum GameState {
        READY, RUNNING
    }
}
