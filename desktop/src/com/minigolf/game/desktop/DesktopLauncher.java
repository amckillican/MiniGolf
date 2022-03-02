package com.minigolf.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.minigolf.game.minigolf;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new minigolf(), config);
		
		// config settings
		config.width = 1360;
		config.height = 765;
		config.resizable = false;
		config.title = "MiniGolf";
	}
}
