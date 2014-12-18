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

    public GameWorld() {
        currentState = GameState.RUNNING;
        groundRect = new Rectangle();
        groundRect.set(0, 288, 480, 32);

        runner = new Runner(100, 180, 64, 64);

        scrollHandler = new ScrollHandler(this);
        enemy = scrollHandler.getEnemy();
        AssetLoader.gameMusic.play();
        AssetLoader.gameMusic.setLooping(true);

        prefs = Gdx.app.getPreferences("RRR");
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
        runner.setPosition(new Vector2(runner.getPosition().x, 224));
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

        if (runner.isAlive()) {

            runner.update(delta);
            scrollHandler.update(delta);


            if (Intersector.overlaps(runner.getBoundingRectangle(), groundRect)) {
                runner.setIsJumping(false);
                runner.setVelocity(new Vector2(0, 0));
                runner.setPosition(new Vector2(runner.getPosition().x, 224));
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
            runner.setPosition(new Vector2(runner.getPosition().x, 320));
            runner.setVelocity(new Vector2(0,0));

            logHighScore(runner.getDistance());

            currentState = GameState.READY;

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
        READY, RUNNING
    }

    private void logHighScore(int score){
        if (score > prefs.getInteger("highScore", 0)){
            System.out.println("New High Score : " + score + " over " + prefs.getInteger("highScore"));
            prefs.putInteger("highScore", score);
            prefs.flush();
        }
    }
}
