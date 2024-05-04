package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.SaveData.User;
import com.mygdx.game.View.MainMenu;

import java.util.ArrayList;

public class MaosVengeance extends Game  {

	public static User user;
	public static int currentWave = 1;
	public static int difficulty = 1;
	public static boolean mute = false;
	public static int clusterBombCount;
	public static boolean isPaused = false;
	public static boolean isGameFreezed = false;
	public static int killCount = 0;
	public static int hitCount = 0;
	public static int firedCount = 0;
	public static int radioActiveBombCount = 0;

	public SpriteBatch batch;
	public static Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenu(this));

		music = Gdx.audio.newMusic(Gdx.files.internal("Songs/1.mp3"));
		music.play();
		music.setLooping(true);

	}

	@Override
	public void render () {
		super.render();
		if (user != null){
			System.out.println(user.getPreferredDifficulty());
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

}