package ca.csf.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import ca.csf.gameworld.GameRenderer;
import ca.csf.gameworld.GameWorld;
import ca.csf.rrrhelpers.InputHandler;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
    }

    @Override
    public void render(float delta) {
        runTime += delta;

        world.update(delta);
        renderer.render(runTime);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
