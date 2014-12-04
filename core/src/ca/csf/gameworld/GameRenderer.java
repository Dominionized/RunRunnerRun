package ca.csf.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import ca.csf.gameobjects.Box;
import ca.csf.gameobjects.Grass;
import ca.csf.gameobjects.Runner;
import ca.csf.gameobjects.ScrollHandler;
import ca.csf.gameobjects.Sky;
import ca.csf.rrrhelpers.AssetLoader;

public class GameRenderer {
    private static final int HEIGHT = 320;
    private static final int WIDTH = 480;

    private GameWorld myWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private Runner runner;
    private ScrollHandler scrollHandler;
    private Sky frontSky, backSky;
    private Grass frontGrass, backGrass;

    private Box boxOne, boxTwo, boxThree, boxFour, boxFive;

    private TextureRegion sky, ground;
    private Animation runnerAnimation;
    private TextureRegion runnerIdle, runnerJump;
    private TextureRegion box, enemy;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, WIDTH, HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }


    private void initGameObjects() {
        runner = myWorld.getRunner();
        scrollHandler = myWorld.getScrollHandler();

        frontSky = scrollHandler.getFrontSky();
        backSky= scrollHandler.getBackSky();
        frontGrass = scrollHandler.getFrontGrass();
        backGrass = scrollHandler.getBackGrass();

        boxOne = scrollHandler.getBox1();
        boxTwo = scrollHandler.getBox2();
        boxThree = scrollHandler.getBox3();
        boxFour = scrollHandler.getBox4();
        boxFive = scrollHandler.getBox5();
    }

    private void initAssets() {
        sky = AssetLoader.sky;
        ground = AssetLoader.ground;
        runnerAnimation = AssetLoader.runnerAnimation;
        runnerIdle = AssetLoader.runnerIdle;
        runnerJump = AssetLoader.runnerJump;
        box = AssetLoader.box;
        enemy = AssetLoader.enemy;
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(97 / 255.0f, 211 / 255.0f, 227 / 255.0f, 1);
        shapeRenderer.rect(0, 0, WIDTH, HEIGHT);
        shapeRenderer.end();

        // Begin SpriteBatch
        batcher.begin();
        batcher.enableBlending();
        batcher.draw(AssetLoader.sky, frontSky.getPosition().x, frontSky.getPosition().y, frontSky.getWidth(), frontSky.getHeight());
        batcher.draw(AssetLoader.sky, backSky.getPosition().x, backSky.getPosition().y, backSky.getWidth(), backSky.getHeight());

        batcher.draw(AssetLoader.ground, frontGrass.getPosition().x, frontGrass.getPosition().y, frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(AssetLoader.ground, backGrass.getPosition().x, backGrass.getPosition().y, backGrass.getWidth(), backGrass.getHeight());

        batcher.draw(AssetLoader.box, boxOne.getPosition().x, boxOne.getPosition().y, boxOne.getWidth(), boxOne.getHeight());
        batcher.draw(AssetLoader.box, boxTwo.getPosition().x, boxTwo.getPosition().y, boxTwo.getWidth(), boxTwo.getHeight());
        batcher.draw(AssetLoader.box, boxThree.getPosition().x, boxThree.getPosition().y, boxThree.getWidth(), boxThree.getHeight());
        batcher.draw(AssetLoader.box, boxFour.getPosition().x, boxFour.getPosition().y, boxFour.getWidth(), boxFour.getHeight());
        batcher.draw(AssetLoader.box, boxFive.getPosition().x, boxFive.getPosition().y, boxFive.getWidth(), boxFive.getHeight());

        // Pass in the runTime variable to get the current frame.
        batcher.draw(runnerAnimation.getKeyFrame(runTime),
                runner.getX(), runner.getY(), runner.getWidth(), runner.getHeight());

        // End SpriteBatch
        batcher.end();
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWidth() {
        return WIDTH;
    }
}
