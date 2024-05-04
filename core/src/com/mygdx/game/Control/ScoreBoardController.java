package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.SaveData.ResourceManger;
import com.mygdx.game.Model.SaveData.User;
import com.mygdx.game.View.*;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class ScoreBoardController {
    public static ScoreBoardController scoreBoardController = new ScoreBoardController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private final TextButton closeButton = new TextButton("Back To MainMenu", skin);
    private final Window scoreBoardWindow = new Window("Score Board", skin);
    private final Window killScoreBoardWindow = new Window("Kill Score Board", skin);
    private final Window waveScoreBoardWindow = new Window("Hardness Score Board", skin);
    private final Window accuracyScoreBoardWindow = new Window("Accuracy Score Board", skin);

    public Table table = new Table();
    private ArrayList<User> users;

    public ScoreBoardController() {
        try{
            users = (ArrayList<User>) ResourceManger.load(GameAssetsManager.gameAssetsManager.saveDataPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setFillParent(true);
        table.center();

        table.addActor(scoreBoardWindow);
        table.addActor(killScoreBoardWindow);
        table.addActor(waveScoreBoardWindow);
        table.addActor(accuracyScoreBoardWindow);
        table.addActor(closeButton);

        scoreBoardWindow.setPosition(10, (float) Gdx.graphics.getHeight() - scoreBoardWindow.getHeight() - 20);
        killScoreBoardWindow.setPosition((float) Gdx.graphics.getWidth() - 10, (float) Gdx.graphics.getHeight() - killScoreBoardWindow.getHeight() - 20);
        waveScoreBoardWindow.setPosition(10, (float) Gdx.graphics.getHeight() - waveScoreBoardWindow.getHeight() - 1020);
        accuracyScoreBoardWindow.setPosition((float) Gdx.graphics.getWidth() - 10, (float) Gdx.graphics.getHeight() - accuracyScoreBoardWindow.getHeight() - 1020);
        closeButton.setPosition((float) Gdx.graphics.getWidth() / 2 - closeButton.getWidth() / 2, 100);

        scoreBoardWindow.setSize(700 , 500);
        killScoreBoardWindow.setSize(700 , 500);
        waveScoreBoardWindow.setSize(700 , 500);
        accuracyScoreBoardWindow.setSize(700 , 500);

        scoreBoardWindow();
        setKillScoreBoardWindow();
        setWaveScoreBoardWindow();
        setAccuracyScoreBoardWindow();
    }

    private void closeButton( MaosVengeance game){
        closeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(new MainMenu(game));
            }
        });
    }

    private void setKillScoreBoardWindow(){
        ArrayList<User> userScores = new ArrayList<>();
        // Sort the users by score
        userScores.addAll(users);
        userScores.sort((o1, o2) -> o2.getKillCount() - o1.getKillCount());
        for (int i = 0; i < 10 && i < userScores.size(); i++){
            Label name = new Label((i + 1) + "." + userScores.get(i).getUsername() + " : " + userScores.get(i).getKillCount(), skin);
            switch (i){
                case 0:
                    name.setColor(Color.GOLD);
                    break;
                case 1:
                    name.setColor(Color.GRAY);
                    break;
                case 2:
                    name.setColor(Color.BROWN);
                    break;
                default:
                    name.setColor(Color.WHITE);
                    break;
            }
            killScoreBoardWindow.add(name);
            killScoreBoardWindow.row();
        }
    }

    private void setWaveScoreBoardWindow(){
        ArrayList<User> userScores = new ArrayList<>();
        // Sort the users by score
        userScores.addAll(users);
        userScores.sort((o1, o2) -> o2.getLastRound() - o1.getLastRound());
        for (int i = 0; i < 10 && i < userScores.size(); i++){
            Label name = new Label((i + 1) + "." + userScores.get(i).getUsername() + " : " + userScores.get(i).getLastRound() * userScores.get(i).getScore(), skin);
            switch (i){
                case 0:
                    name.setColor(Color.GOLD);
                    break;
                case 1:
                    name.setColor(Color.GRAY);
                    break;
                case 2:
                    name.setColor(Color.BROWN);
                    break;
                default:
                    name.setColor(Color.WHITE);
                    break;
            }
            waveScoreBoardWindow.add(name);
            waveScoreBoardWindow.row();
        }
    }

    private void setAccuracyScoreBoardWindow(){
        ArrayList<User> userScores = new ArrayList<>();
        // Sort the users by score
        userScores.addAll(users);
        userScores.sort((o1, o2) -> o2.getAccuracy() - o1.getAccuracy());
        for (int i = 0; i < 10 && i < userScores.size(); i++){
            Label name = new Label((i + 1) + "." + userScores.get(i).getUsername() + " : " + userScores.get(i).getAccuracy(), skin);
            switch (i){
                case 0:
                    name.setColor(Color.GOLD);
                    break;
                case 1:
                    name.setColor(Color.GRAY);
                    break;
                case 2:
                    name.setColor(Color.BROWN);
                    break;
                default:
                    name.setColor(Color.WHITE);
                    break;
            }
            accuracyScoreBoardWindow.add(name);
            accuracyScoreBoardWindow.row();
        }
    }

    private void scoreBoardWindow(){
        ArrayList<User> userScores = new ArrayList<>();
        // Sort the users by score
        userScores.addAll(users);
        userScores.sort((o1, o2) -> o2.getScore() - o1.getScore());
        for (int i = 0; i < 10 && i < userScores.size(); i++){
            Label name = new Label((i + 1) + "." + userScores.get(i).getUsername() + " : " + userScores.get(i).getScore(), skin);
            switch (i){
                case 0:
                    name.setColor(Color.GOLD);
                    break;
                case 1:
                    name.setColor(Color.GRAY);
                    break;
                case 2:
                    name.setColor(Color.BROWN);
                    break;
                default:
                    name.setColor(Color.WHITE);
                    break;
            }
            scoreBoardWindow.add(name);
            scoreBoardWindow.row();
        }
    }

    public void handleMainMenuButtons(MaosVengeance game){
        closeButton(game);
    }

}