package com.kilobolt.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kilobolt.screens.GameScreen;

public class ZBGame extends Game {

	@Override
	public void create() {
		// Equivalent of the sout in java
		Gdx.app.log("ZBGame", "created");

        // Attaching GameScreen to ZBGame Class
        setScreen(new GameScreen());
	}
}
