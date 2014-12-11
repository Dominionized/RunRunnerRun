package ca.csf.rrrhelpers;

import com.badlogic.gdx.InputProcessor;

import ca.csf.gameobjects.Runner;

public class InputHandler implements InputProcessor {
    private Runner myRunner;

    public InputHandler(Runner runner){
        this.myRunner = runner;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        if (character == ' '){
            myRunner.onClick();
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        myRunner.onClick();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
