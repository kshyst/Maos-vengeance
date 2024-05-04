package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.SaveData.ResourceManger;
import com.mygdx.game.View.GameScreen;
import com.mygdx.game.View.MainMenu;

import static com.mygdx.game.Model.Player.player;

public class WavePassedController {
    public static WavePassedController wavePassedController = new WavePassedController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private final Label wavePassed = new Label("well done comrade", skin, "title");
    private final Label accuracy = new Label("Accuracy: " + (int)((float) MaosVengeance.hitCount / (float) MaosVengeance.firedCount * 100 ) + "%", skin, "title");
    private final Label killCount = new Label("Kill Count: " + MaosVengeance.killCount, skin, "title");
    private final TextButton exitToMenu = new TextButton("Exit to Menu", skin);
    private final TextButton nextWave = new TextButton("Continue fighting for communists", skin);
    public Table table = new Table();

    private WavePassedController() {
        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(wavePassed).fillX();
        wavePassed.setColor(Color.GREEN);
        table.row().pad(10, 0, 10, 0);
        table.add(accuracy).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(killCount).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(nextWave).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(exitToMenu).fillX();
        table.row().pad(10, 0, 10, 0);

        killCount.setFontScale(0.5f);
        accuracy.setFontScale(0.5f);
    }

    private void exitToMenu(MaosVengeance game){
        exitToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int waveEnded = MaosVengeance.currentWave + 1;
                if (MaosVengeance.user != null){
                    ResourceManger.saveGameProgress(waveEnded);
                }
                else {
                    MaosVengeance.currentWave = waveEnded;
                }
                game.getScreen().dispose();
                game.setScreen(new MainMenu(game));
                MaosVengeance.killCount = 0;
                MaosVengeance.hitCount = 0;
                MaosVengeance.firedCount = 0;
                player.playerHealth = 100;

            }
        });
    }

    private void nextWave(MaosVengeance game){
        nextWave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MaosVengeance.currentWave++;
                game.getScreen().dispose();
                game.setScreen(new GameScreen(game));
                MaosVengeance.killCount = 0;
                MaosVengeance.hitCount = 0;
                MaosVengeance.firedCount = 0;
            }
        });
    }
    private void handleKillCount(){
        killCount.setText("Kill Count: " + MaosVengeance.killCount);
        accuracy.setText("Accuracy: " + (int)((float) MaosVengeance.hitCount / (float) MaosVengeance.firedCount * 100 ) + "%");
    }
    public void handleMainMenuButtons(MaosVengeance game){
        exitToMenu(game);
        nextWave(game);
        handleKillCount();
    }

}