package com.minigolf.game.desktop;

import com.Minigolf.game.Minigolf;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Minigolf(), config);
		
		// config settings
		config.width = 1360;
		config.height = 765;
		config.resizable = false;
		config.title = "MiniGolf";
	}
}
