package com.kilobolt.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kilobolt.screens.GameScreen;
import com.kilobolt.zbhelpers.AssetLoader;

public class ZBGame extends Game {

	@Override
	public void create() {
		// Equivalent of the sout in java
		Gdx.app.log("ZBGame", "created");

        // Load all the textures
		AssetLoader.load();

        // Attaching GameScreen to ZBGame Class
        setScreen(new GameScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
