package com.mygdx.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.main.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		/** Configure the application running frames per seconds and windows size **/
		config.height = Game.HEIGHT;
		config.width = Game.WIDTH;
		config.foregroundFPS = 60;
		config.resizable = false;
		config.title = "Project Zeldren";
		new LwjglApplication(new Game(), config);
	}
}
