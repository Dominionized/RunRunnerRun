package ca.csf.rrr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import ca.csf.rrrhelpers.AssetLoader;
import ca.csf.screens.GameScreen;

public class RunRunnerRunGame extends Game {

	@Override
	public void create () {
        Gdx.app.log("RRRGame", "created");
        AssetLoader.load();
        setScreen(new GameScreen());
	}

    @Override
    public void dispose(){
        super.dispose();
        AssetLoader.dispose();
    }
}
