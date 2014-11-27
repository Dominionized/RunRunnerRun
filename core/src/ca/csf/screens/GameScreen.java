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

    public GameScreen(){
        //Gdx.app.log("GameScreen", "attached");

        world = new GameWorld();
        renderer = new GameRenderer(world);
    }

    @Override
    public void render(float delta) {
        runTime += delta;

        world.update(delta);
        renderer.render(runTime);

        Gdx.input.setInputProcessor(new InputHandler(world.getRunner()));
    }

    @Override
    public void resize(int width, int height) {
        //Gdx.app.log("GameScreen", "resize");
    }

    @Override
    public void show() {
        //Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        //Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        //Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        //Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {

    }
}
