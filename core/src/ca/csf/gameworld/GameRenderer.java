package ca.csf.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.util.List;

import ca.csf.gameobjects.Box;
import ca.csf.gameobjects.Enemy;
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

    private BitmapFont font;
    private BitmapFont fontShadow;

    private Runner runner;
    private ScrollHandler scrollHandler;
    private Sky frontSky, backSky;
    private Grass frontGrass, backGrass;

    private Enemy enemy;
    private List<Box> boxList;

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

        initGameObjects();
    }


    private void initGameObjects() {
        runner = myWorld.getRunner();
        scrollHandler = myWorld.getScrollHandler();

        frontSky = scrollHandler.getFrontSky();
        backSky= scrollHandler.getBackSky();
        frontGrass = scrollHandler.getFrontGrass();
        backGrass = scrollHandler.getBackGrass();

        enemy = scrollHandler.getEnemy();
        boxList = scrollHandler.getBoxList();
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

        for(Box box : boxList){
            batcher.draw(AssetLoader.box, box.getPosition().x, box.getPosition().y, box.getWidth(), box.getHeight());
        }

        if(enemy.isAlive()) {
            batcher.draw(AssetLoader.enemy, enemy.getPosition().x, enemy.getPosition().y, enemy.getWidth(), enemy.getHeight());
        }

        if(runner.getIsJumping()){
            batcher.draw(AssetLoader.runnerJump,
                    runner.getX(), runner.getY(), runner.getWidth(), runner.getHeight());
        } else {
            batcher.draw(AssetLoader.runnerAnimation.getKeyFrame(runTime),
                    runner.getX(), runner.getY(), runner.getWidth(), runner.getHeight());
        }

        String textToDraw = Integer.toString(runner.getPixelDistance()) + " m";

        fontShadow.draw(batcher, textToDraw, 25, 25);
        font.draw(batcher, textToDraw, 25, 25);


        // End SpriteBatch
        batcher.end();

/* BOUNDING BOX
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(0 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);
        shapeRenderer.rect(runner.getBoundingRectagle().getX(), runner.getBoundingRectagle().getY(), runner.getBoundingRectagle().getWidth(), runner.getBoundingRectagle().getHeight());
        shapeRenderer.end();
        */

    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWidth() {
        return WIDTH;
    }
}
