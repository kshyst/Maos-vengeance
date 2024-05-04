package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.View.*;

import java.lang.management.ManagementFactory;

public class MainMenuController {
    public static MainMenuController mainMenuController = new MainMenuController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private final Image backgroundImage = new Image(new Texture(Gdx.files.internal(GameAssetsManager.gameAssetsManager.mainMenuBackground)));
    private final TextButton playButton = new TextButton("Play", skin);
    private final TextButton playNewButton = new TextButton("Play New Game", skin);
    private final TextButton closeGameButton = new TextButton("Close Game", skin);
    private final TextButton signInButton = new TextButton("Sign In", skin );
    private final TextButton settingButton = new TextButton("Settings", skin);
    private final TextButton scoreBoardButton = new TextButton("Score Board", skin);
    private final Label loggedInUserUsername = new Label("", skin);
    public Table table = new Table();


    public MainMenuController() {
        table.addActor(backgroundImage);
        backgroundImage.setFillParent(true);
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(playButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(playNewButton).fillX();
        table.row().pad(10, 0, 10 , 0);
        table.add(closeGameButton).fillX();

        table.addActor(signInButton);
        signInButton.setPosition(10 , (float) Gdx.graphics.getHeight() - signInButton.getHeight() - 10);
        table.addActor(loggedInUserUsername);
        loggedInUserUsername.setPosition((float) Gdx.graphics.getWidth() - loggedInUserUsername.getText().length * 15 , (float) Gdx.graphics.getHeight() - loggedInUserUsername.getHeight() - 30);

        table.addActor(settingButton);
        settingButton.setPosition(10, (float) Gdx.graphics.getHeight() - settingButton.getHeight() - 110);
        table.addActor(scoreBoardButton);
        scoreBoardButton.setPosition(10, (float) Gdx.graphics.getHeight() - scoreBoardButton.getHeight() - 210);
    }

    private void playButton( MaosVengeance game){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (MaosVengeance.user != null){
                    MaosVengeance.currentWave = MaosVengeance.user.getLastRound();
                    if (MaosVengeance.currentWave == 0){
                        MaosVengeance.currentWave = 1;
                    }
                }
                game.getScreen().dispose();
                game.setScreen(new GameScreen(game));
            }
        });
    }

    private void playNewButton( MaosVengeance game){
        playNewButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MaosVengeance.currentWave = 1;
                game.getScreen().dispose();
                game.setScreen(new GameScreen(game));
            }
        });
    }

    private void closeGameButton(){
        closeGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void toggleButtonSignIn(MaosVengeance game){
        if (MaosVengeance.user == null){
            signInButton.setText("Sign In");
            signInButton.clearListeners();
            signInButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.getScreen().dispose();
                    game.setScreen(new LoginMenu(game));
                }
            });
        }
        else{
            signInButton.setText("Profile");
            Texture avatarTexture = new Texture(Gdx.files.internal(MaosVengeance.user.getAvatar()));
            Image avatar = new Image(avatarTexture);
            avatar.setWidth(100);
            avatar.setHeight(100);
            table.addActor(avatar);
            avatar.setPosition((float) Gdx.graphics.getWidth() - 500, (float) Gdx.graphics.getHeight() - avatar.getHeight());
            signInButton.clearListeners();
            signInButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    avatar.setVisible(false);
                    game.getScreen().dispose();
                    game.setScreen(new ProfileMenu(game));
                }
            });
        }
    }

    private void settingButton(MaosVengeance game){
        settingButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(new SettingMenu(game));
            }
        });
    }

    private void userNameShow(){
        if (MaosVengeance.user != null){
            loggedInUserUsername.setText("logged in player : " + MaosVengeance.user.getUsername());
            loggedInUserUsername.setPosition((float) Gdx.graphics.getWidth() - loggedInUserUsername.getText().length * 15 , (float) Gdx.graphics.getHeight() - loggedInUserUsername.getHeight() - 30);
        }
        else {
            loggedInUserUsername.setText("");
        }
    }

    private void ScoreBoardButton(MaosVengeance game){
        scoreBoardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScoreBoardController.scoreBoardController = new ScoreBoardController();
                game.getScreen().dispose();
                game.setScreen(new ScoreBoardScreen(game));
            }
        });
    }

    public void handleMainMenuButtons(MaosVengeance game){
        playButton(game);
        closeGameButton();
        toggleButtonSignIn(game);
        userNameShow();
        settingButton(game);
        playNewButton(game);
        ScoreBoardButton(game);
    }

}