package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.SaveData.Regex;
import com.mygdx.game.Model.SaveData.ResourceManger;
import com.mygdx.game.Model.SaveData.User;
import com.mygdx.game.View.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SettingMenuController {
    public static SettingMenuController settingMenuController = new SettingMenuController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private Label settings = new Label("Settings", skin);
    private Label diff = new Label("Difficulty", skin);
    private TextButton backButton = new TextButton("Back", skin , "default");
    private TextButton difficultyButton1 = new TextButton("Easy", skin , "toggle");
    private TextButton difficultyButton2 = new TextButton("Medium", skin , "toggle");
    private TextButton difficultyButton3 = new TextButton("Hard", skin , "toggle");
    private TextButton muteButton = new TextButton("Mute", skin , "default");
    private TextButton changeControls = new TextButton("Change Controls", skin , "default");
    private Label empty = new Label("", skin);

    public Table table = new Table();


    private SettingMenuController() {
        table.setFillParent(true);
        table.center();

        table.add(settings).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.addActor(diff);
        diff.setFontScale(2);
        diff.setPosition((float) Gdx.graphics.getWidth() /2 - diff.getWidth() , 1000);
        table.row().pad(10, 0, 10, 0);
        table.add(difficultyButton1).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(difficultyButton2).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(difficultyButton3).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(empty).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(empty).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(muteButton).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(changeControls).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(backButton).fillX().expandX().pad(0 , 800 , 0 , 800);


        if (MaosVengeance.user != null){
            switch (MaosVengeance.user.getPreferredDifficulty()){
                case 1:
                    difficultyButton1.setChecked(true);
                    difficultyButton2.setChecked(false);
                    difficultyButton3.setChecked(false);
                    break;
                case 2:
                    difficultyButton2.setChecked(true);
                    difficultyButton1.setChecked(false);
                    difficultyButton3.setChecked(false);
                    break;
                case 3:
                    difficultyButton3.setChecked(true);
                    difficultyButton1.setChecked(false);
                    difficultyButton2.setChecked(false);
                    break;
            }
        }
        else {
            difficultyButton2.setChecked(true);
        }
    }

    public void handleSettings(){
        difficultyButton1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MaosVengeance.difficulty = 1;
                difficultyButton1.setChecked(true);
                difficultyButton2.setChecked(false);
                difficultyButton3.setChecked(false);


                if (MaosVengeance.user != null){
                    MaosVengeance.user.setPreferredDifficulty(1);
                    ResourceManger.loadAndSave(GameAssetsManager.gameAssetsManager.saveDataPath);
                }
            }
        });

        difficultyButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MaosVengeance.difficulty = 2;
                difficultyButton1.setChecked(false);
                difficultyButton2.setChecked(true);
                difficultyButton3.setChecked(false);

                if (MaosVengeance.user != null) {
                    MaosVengeance.user.setPreferredDifficulty(2);
                    ResourceManger.loadAndSave(GameAssetsManager.gameAssetsManager.saveDataPath);
                }
            }
        });

        difficultyButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MaosVengeance.difficulty = 3;
                difficultyButton1.setChecked(false);
                difficultyButton2.setChecked(false);
                difficultyButton3.setChecked(true);

                if (MaosVengeance.user != null) {
                    MaosVengeance.user.setPreferredDifficulty(3);
                    ResourceManger.loadAndSave(GameAssetsManager.gameAssetsManager.saveDataPath);
                }
            }
        });
    }

    private void backButton(MaosVengeance game){
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(new MainMenu(game));
            }
        });
    }

    private void muteButton(MaosVengeance game){
        muteButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (MaosVengeance.mute){
                    muteButton.setText("Mute");
                    MaosVengeance.music.setVolume(100);
                }
                else {
                    muteButton.setText("Unmute");
                    MaosVengeance.music.setVolume(0);
                }
                MaosVengeance.mute = !MaosVengeance.mute;
                game.getScreen().dispose();
                game.setScreen(new SettingMenu(game));
            }
        });
    }

    private void changeControls(MaosVengeance game){
        changeControls.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (PlayerController.controllerMode == 1){
                    PlayerController.controllerMode = 0;
                }
                else {
                    PlayerController.controllerMode = 1;
                }
                game.getScreen().dispose();
                game.setScreen(new SettingMenu(game));
            }
        });
    }

    public void handleMainMenuButtons(MaosVengeance game){
        backButton(game);
        handleSettings();
        muteButton(game);
        changeControls(game);
    }

}
