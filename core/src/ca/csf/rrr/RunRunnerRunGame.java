package ca.csf.rrr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import ca.csf.rrr.helpers.AssetLoader;
import ca.csf.rrr.screens.GameScreen;

public class RunRunnerRunGame extends Game {

    @Override
    public void create() {
        Gdx.app.log("RRRGame", "created");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
