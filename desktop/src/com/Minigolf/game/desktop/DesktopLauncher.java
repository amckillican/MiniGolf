package com.Minigolf.game.desktop;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		// config settings
		config.setWindowedMode(1360, 765);
		config.setTitle("MiniGolf");
		new Lwjgl3Application(new Minigolf(), config);
	}
}
