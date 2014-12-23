package ca.csf.rrr.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ca.csf.rrr.gameobjects.Box;
import ca.csf.rrr.gameobjects.Enemy;
import ca.csf.rrr.gameobjects.Runner;
import ca.csf.rrr.gameobjects.ScrollHandler;
import ca.csf.rrr.helpers.AssetLoader;

public class GameWorld {

    private GameRenderer gameRenderer;
    private Runner runner;
    private Enemy enemy;
    private Rectangle groundRect;
    private ScrollHandler scrollHandler;
    private GameState currentState;
    private Preferences prefs;

    private final static int RUNNER_WIDTH_HEIGHT = 64;
    private final static int RUNNER_X = 100;
    private final static int RUNNER_X_POSITION = 224;
    private final static int RUNNER_X_POSITION_ON_DEATH = 320;
    private final static int RUNNER_Y = 180;
    private final static int GROUND_Y = 288;
    private final static int GROUND_WIDTH = 480;
    private final static int GROUND_HEIGHT = 32;
    private final static String PREFERENCES = "RRR";

    public GameWorld(int midPointY) {
        currentState = GameState.RUNNING;
        groundRect = new Rectangle();
        groundRect.set(0, GROUND_Y, GROUND_WIDTH, GROUND_HEIGHT);

        runner = new Runner(midPointY + RUNNER_X, RUNNER_Y, RUNNER_WIDTH_HEIGHT, RUNNER_WIDTH_HEIGHT);

        scrollHandler = new ScrollHandler(this);
        enemy = scrollHandler.getEnemy();
        AssetLoader.gameMusic.play();
        AssetLoader.gameMusic.setLooping(true);

        prefs = Gdx.app.getPreferences(PREFERENCES);
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
        runner.setPosition(new Vector2(runner.getPosition().x, RUNNER_X_POSITION));
        currentState = GameState.RUNNING;

        AssetLoader.dyingMusic.stop();
        AssetLoader.gameMusic.play();
        AssetLoader.gameMusic.setLooping(true);

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

        if (currentState != GameState.PAUSE){

        if (runner.isAlive()) {

            runner.update(delta);
            scrollHandler.update(delta);


            if (Intersector.overlaps(runner.getBoundingRectangle(), groundRect)) {
                runner.setIsJumping(false);
                runner.setVelocity(new Vector2(0, 0));
                runner.setPosition(new Vector2(runner.getPosition().x, RUNNER_X_POSITION));
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
                    AssetLoader.enemyDyingSound.play();
                    enemy.onKilled();
                } else {
                    runner.onKilled();
                }
            }

        } else {
            AssetLoader.gameMusic.stop();
            AssetLoader.dyingMusic.play();
            enemy.onKilled();
            runner.setPosition(new Vector2(runner.getPosition().x, RUNNER_X_POSITION_ON_DEATH));
            runner.setVelocity(new Vector2(0,0));

            logHighScore(runner.getDistance());

            currentState = GameState.READY;

        }
    }
    }

    public void updateReady(float delta) {
        //TODO add stuff

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
        READY, RUNNING, PAUSE
    }

    private void logHighScore(int score){
        if (score > prefs.getInteger("highScore", 0)){
            System.out.println("New High Score : " + score + " over " + prefs.getInteger("highScore"));
            prefs.putInteger("highScore", score);
            prefs.flush();
        }
    }

    public void pause(){

        if (currentState == GameState.PAUSE){
            currentState = GameState.RUNNING;
        }
        else{
            currentState = GameState.PAUSE;
        }

    }
}
