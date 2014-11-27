package ca.csf.rrr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import ca.csf.screens.GameScreen;

public class RunRunnerRunGame extends Game {

	@Override
	public void create () {
        Gdx.app.log("RRRGame", "created");
        setScreen(new GameScreen());
	}
}
