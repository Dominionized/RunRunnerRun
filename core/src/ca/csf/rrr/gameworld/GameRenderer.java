package ca.csf.rrr.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

import ca.csf.rrr.gameobjects.BackgroundLayer;
import ca.csf.rrr.gameobjects.Box;
import ca.csf.rrr.gameobjects.Enemy;
import ca.csf.rrr.gameobjects.Grass;
import ca.csf.rrr.gameobjects.Runner;
import ca.csf.rrr.gameobjects.ScrollHandler;
import ca.csf.rrr.helpers.AssetLoader;

public class GameRenderer {

    private static final int HEIGHT = 320;
    private static final int WIDTH = 480;
    private static final int READY_WINDOW_WIDTH = 300;
    private static final int READY_WINDOW_HEIGHT = 200;
    private static final int READY_WINDOW_PADDING = 30;
    private final GameWorld myWorld;
    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch batcher;
    private final BitmapFont font;
    private final BitmapFont fontShadow;
    private Runner runner;
    private ScrollHandler scrollHandler;
    private BackgroundLayer frontSky, backSky, frontMountains, backMountains;
    private Grass frontGrass, backGrass;
    private Enemy enemy;
    private List<Box> boxList;
    private Rectangle readyWindow;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, WIDTH, HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        font = AssetLoader.font;
        fontShadow = AssetLoader.fontShadow;


        int x = (WIDTH - READY_WINDOW_WIDTH) / 2;
        int y = (HEIGHT - READY_WINDOW_HEIGHT) / 2;
        readyWindow = new Rectangle(x, y, READY_WINDOW_WIDTH, READY_WINDOW_HEIGHT);


        initGameObjects();
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWidth() {
        return WIDTH;
    }

    private void initGameObjects() {
        runner = myWorld.getRunner();
        scrollHandler = myWorld.getScrollHandler();

        frontSky = scrollHandler.getFrontSky();
        backSky = scrollHandler.getBackSky();
        frontMountains = scrollHandler.getFrontMountains();
        backMountains = scrollHandler.getBackMountains();
        frontGrass = scrollHandler.getFrontGrass();
        backGrass = scrollHandler.getBackGrass();

        enemy = scrollHandler.getEnemy();
        boxList = scrollHandler.getBoxList();
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(65 / 255.0f, 146 / 255.0f, 195 / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin SpriteBatch

        batcher.begin();
        batcher.enableBlending();
        batcher.draw(AssetLoader.sky, frontSky.getPosition().x, frontSky.getPosition().y, frontSky.getWidth(), frontSky.getHeight());
        batcher.draw(AssetLoader.sky, backSky.getPosition().x, backSky.getPosition().y, backSky.getWidth(), backSky.getHeight());

        batcher.draw(AssetLoader.mountains, frontMountains.getPosition().x, frontMountains.getPosition().y, frontMountains.getWidth(), frontMountains.getHeight());
        batcher.draw(AssetLoader.mountains, backMountains.getPosition().x, backMountains.getPosition().y, backMountains.getWidth(), backMountains.getHeight());

        batcher.draw(AssetLoader.ground, frontGrass.getPosition().x, frontGrass.getPosition().y, frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(AssetLoader.ground, backGrass.getPosition().x, backGrass.getPosition().y, backGrass.getWidth(), backGrass.getHeight());

        for (Box box : boxList) {
            batcher.draw(AssetLoader.boxAnimation.getKeyFrame(runTime), box.getPosition().x, box.getPosition().y, box.getWidth(), box.getHeight());
        }

        if (enemy.isAlive()) {
            batcher.draw(AssetLoader.enemy, enemy.getPosition().x, enemy.getPosition().y, enemy.getWidth(), enemy.getHeight());
        }

        if (runner.isKicking()) {
            batcher.draw(AssetLoader.runnerKick, runner.getX(), runner.getY(), runner.getWidth(), runner.getHeight());
        } else if (runner.getIsJumping()) {
            batcher.draw(AssetLoader.runnerJump,
                    runner.getX(), runner.getY(), runner.getWidth(), runner.getHeight());
        } else {
            batcher.draw(AssetLoader.runnerAnimation.getKeyFrame(runTime),
                    runner.getX(), runner.getY(), runner.getWidth(), runner.getHeight());
        }

        if (myWorld.isReady()) {
            batcher.draw(AssetLoader.readyMenuBackground, readyWindow.x, readyWindow.y, readyWindow.width, readyWindow.height);
            String textToDraw = "YOU DIED.\n\n" + "Your score : " + runner.getDistance() + "\nHighScore : " + Gdx.app.getPreferences("RRR").getInteger("highScore");
            fontShadow.drawWrapped(batcher, textToDraw, readyWindow.x + READY_WINDOW_PADDING, readyWindow.y + READY_WINDOW_PADDING, readyWindow.width - 2*READY_WINDOW_PADDING, BitmapFont.HAlignment.CENTER);
            font.drawWrapped(batcher, textToDraw, readyWindow.x + READY_WINDOW_PADDING, readyWindow.y + READY_WINDOW_PADDING, readyWindow.width - 2*READY_WINDOW_PADDING, BitmapFont.HAlignment.CENTER);

        } else {

            String scoreToDraw = Integer.toString(runner.getDistance()) + " m";

            fontShadow.draw(batcher, scoreToDraw, 25, 25);
            font.draw(batcher, scoreToDraw, 25, 25);

       }

        // End SpriteBatch
        batcher.end();
    }
}
