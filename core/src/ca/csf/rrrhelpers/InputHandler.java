package ca.csf.rrrhelpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import ca.csf.gameobjects.Runner;

public class InputHandler implements InputProcessor {
    private Runner myRunner;

    public InputHandler(Runner runner){
        this.myRunner = runner;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case(Input.Keys.SPACE):
            case(Input.Keys.UP):
            case(Input.Keys.A):
                myRunner.onJump();
                break;
            case(Input.Keys.RIGHT):
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
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        myRunner.onJump();
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
