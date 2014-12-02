package ca.csf.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import ca.csf.gameobjects.Runner;
import ca.csf.rrrhelpers.AssetLoader;

public class GameRenderer {
    private static final int HEIGHT = 320;
    private static final int WIDTH = 480;

    private GameWorld myWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    public GameRenderer(GameWorld world){
        myWorld = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, WIDTH, HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime){
        Runner runner = myWorld.getRunner();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, WIDTH, HEIGHT);
        shapeRenderer.end();

        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency.
        batcher.disableBlending();

        batcher.draw(AssetLoader.sky, 0, 0, WIDTH, HEIGHT*7/8);
        batcher.draw(AssetLoader.ground, 0, HEIGHT-HEIGHT/8, WIDTH, HEIGHT/8);

        batcher.enableBlending();

        // Pass in the runTime variable to get the current frame.
        batcher.draw(AssetLoader.runnerAnimation.getKeyFrame(runTime),
                runner.getX(), runner.getY(), runner.getWidth(), runner.getHeight());

        // End SpriteBatch
        batcher.end();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(runner.getBoundingRectagle().x, runner.getBoundingRectagle().y, runner.getBoundingRectagle().width, runner.getBoundingRectagle().height);
        shapeRenderer.end();
    }
}
