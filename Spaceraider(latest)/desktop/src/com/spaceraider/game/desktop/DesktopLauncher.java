package com.spaceraider.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spaceraider.game.Spaceraider;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Raider";
		config.width = 1920;
		config.height = 1080;
		config.resizable = false;
		new LwjglApplication(new Spaceraider("singleplayer"), config);
	}
}
