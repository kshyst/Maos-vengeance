package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.SaveData.ResourceManger;
import com.mygdx.game.Model.SaveData.User;

import java.util.ArrayList;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Mao's Vengeance");
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setWindowIcon("Mao.png");
		new Lwjgl3Application(new MaosVengeance(), config);
	}
}