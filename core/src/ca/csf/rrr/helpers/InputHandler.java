package ca.csf.rrr.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import ca.csf.rrr.gameobjects.Runner;
import ca.csf.rrr.gameworld.GameWorld;
import ca.csf.rrr.screens.GameScreen;

public class InputHandler implements InputProcessor {
    private final GameWorld myWorld;
    private final Runner myRunner;

    public InputHandler(GameWorld world) {
        myWorld = world;
        this.myRunner = world.getRunner();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (myWorld.isReady()) {
            myWorld.restart();
            return true;
        }
        switch (keycode) {
            case (Input.Keys.SPACE):
            case (Input.Keys.UP):
            case (Input.Keys.A):
                myRunner.onJump();
                break;
            case (Input.Keys.RIGHT):
                myRunner.onKick();
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        if (character == 'q'){

            myWorld.pause();

        }


        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isRunning()) {
            if (screenX < (Gdx.graphics.getWidth() / 2)) {
                myRunner.onJump();
            } else {
                myRunner.onKick();
            }
        } else if (myWorld.isReady()) {
            myWorld.restart();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
