package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.Player;
import com.mygdx.game.Model.SaveData.ResourceManger;
import com.mygdx.game.View.*;

public class GameUIController {
    public static GameUIController gameUIController = new GameUIController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    public Table table = new Table();
    public Label killCount = new Label("0" , skin , "title");
    public Label waveNumber = new Label("Wave Number: 0" , skin , "title");
    public Label radioActiveBombCount = new Label("RadioActive: 0" , skin , "title");
    public Label clusterBombCount = new Label("Cluster: 0" , skin , "title");
    public Label playerHealth = new Label("HP: 100" , skin , "title");
    public ProgressBar freezeProgressBar = new ProgressBar(0 , 3 , 1 , false , skin);
    public Window pauseWindow = new Window("Paused", skin);
    public TextButton exitToMainMenu = new TextButton("Exit Without Save", skin);
    public TextButton exitToMainMenuSave = new TextButton("Save And Exit", skin);
    public TextButton stopMusic = new TextButton("Stop Music", skin);
    public Label controls = new Label("WASD or UpDownRightLeft Arrow to move, Space to shoot, R radioactive, C cluster", skin, "default");
    private final Slider chooseFromSongs = new Slider(0, 2, 1, false, skin);
    private int killCountOfPlayer = 0;
    private GameUIController() {
        table.setFillParent(true);
        table.center();

        table.addActor(killCount);
        killCount.setPosition(10 , (float) Gdx.graphics.getHeight() - killCount.getHeight() - 10);
        table.addActor(waveNumber);
        waveNumber.setPosition((float) Gdx.graphics.getWidth() - waveNumber.getText().length * 34 , (float) Gdx.graphics.getHeight() - waveNumber.getHeight() - 10);
        table.addActor(radioActiveBombCount);
        radioActiveBombCount.setPosition(10 , (float) Gdx.graphics.getHeight() - radioActiveBombCount.getHeight() - 60);
        table.addActor(clusterBombCount);
        clusterBombCount.setPosition(10, (float) Gdx.graphics.getHeight() - clusterBombCount.getHeight() - 110);
        table.addActor(playerHealth);
        playerHealth.setPosition((float) Gdx.graphics.getWidth() - playerHealth.getText().length * 40 , (float) Gdx.graphics.getHeight() - playerHealth.getHeight() - 60);
        playerHealth.setColor(Color.GREEN);
        table.addActor(freezeProgressBar);
        freezeProgressBar.setPosition((float) Gdx.graphics.getWidth() / 2 - freezeProgressBar.getWidth() / 2, (float) Gdx.graphics.getHeight() - freezeProgressBar.getHeight() - 10);

        table.addActor(pauseWindow);
        pauseWindow.setSize(1500, 700);
        pauseWindow.setPosition((float) Gdx.graphics.getWidth() / 2 - pauseWindow.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - pauseWindow.getHeight() / 2);
        pauseWindow.setVisible(false);
        pauseWindow.setMovable(true);
        pauseWindow.setColor(0.5f, 0.5f, 0.5f, 0.5f);
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(chooseFromSongs).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(controls).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(stopMusic).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(exitToMainMenuSave).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(exitToMainMenu).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);

        playerHealth.setFontScale(0.5f);
        radioActiveBombCount.setFontScale(0.5f);
        clusterBombCount.setFontScale(0.5f);
        killCount.setFontScale(0.5f);
        waveNumber.setFontScale(0.5f);
    }

    private void handleKillCount(){
        killCount.setText("Kill Count: " + MaosVengeance.killCount);
    }

    private void handleWaveNumber(){
        waveNumber.setText("Wave Number: " + MaosVengeance.currentWave);
    }

    private void handleRadioActiveBombCount(){
        radioActiveBombCount.setText("RadioActive: " + MaosVengeance.radioActiveBombCount);
    }

    private void handleFreezeBar(){
        if (MaosVengeance.killCount > killCountOfPlayer){
            killCountOfPlayer = MaosVengeance.killCount;
            freezeProgressBar.setValue(freezeProgressBar.getValue() + 1);
            if (freezeProgressBar.getValue() == 3){
                freezeProgressBar.setValue(0);
                MaosVengeance.canFreeze = true;
            }
            freezeProgressBar.setColor(Color.GREEN);
        }
        else
            freezeProgressBar.setColor(Color.RED);
    }
    private void handleClusterBombCount(){
        clusterBombCount.setText("Cluster: " + MaosVengeance.clusterBombCount);
    }

    private void handlePlayerHealth(){
        playerHealth.setText("HP: " + Player.player.playerHealth);

        if (Player.player.playerHealth < 20){
            playerHealth.setColor(Color.RED);
        }
        else if (Player.player.playerHealth < 60){
            playerHealth.setColor(Color.ORANGE);
        }
        else {
            playerHealth.setColor(Color.GREEN);
        }
    }
    private void pauseWindowHandler(MaosVengeance game){
        if (game.isPaused){
            pauseWindow.setVisible(true);
        } else {
            pauseWindow.setVisible(false);
        }
    }

    private void exitToMainMenuSave(MaosVengeance game){
        exitToMainMenuSave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int waveEnded = MaosVengeance.currentWave;
                ((GameScreen)game.getScreen()).fullDispose();
                game.setScreen(new MainMenu(game));
                if (MaosVengeance.user != null){
                    ResourceManger.saveGameProgress(waveEnded);
                }
                else {
                    MaosVengeance.currentWave = waveEnded;
                }
            }
        });
    }

    private void exitToMainMenu(MaosVengeance game){
        exitToMainMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((GameScreen)game.getScreen()).fullDispose();
                game.setScreen(new MainMenu(game));
            }
        });
    }

    private void musicHandler(MaosVengeance game){
        stopMusic.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (MaosVengeance.music.getVolume() > 0){
                    MaosVengeance.music.setVolume(0);
                    stopMusic.setText("start music");
                }
                else {
                    MaosVengeance.music.setVolume(100);
                    stopMusic.setText("stop music");
                }
                ((GameScreen)game.getScreen()).uiDispose();
                game.setScreen(new GameScreen(game));
            }
        });
    }

    public void musicChanger(){
        chooseFromSongs.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int songNum = (int) chooseFromSongs.getValue();
                MaosVengeance.music.stop();
                switch (songNum){
                    case 0 :
                        MaosVengeance.music = GameAssetsManager.gameAssetsManager.song1Music;
                        break;
                    case 1 :
                        MaosVengeance.music = GameAssetsManager.gameAssetsManager.song2Music;
                        break;
                    case 2 :
                        MaosVengeance.music = GameAssetsManager.gameAssetsManager.song3Music;
                        break;
                }
                MaosVengeance.music.play();
            }
        });
    }

    public void handleMainMenuButtons(MaosVengeance game){
        handleKillCount();
        handleWaveNumber();
        handleRadioActiveBombCount();
        handleClusterBombCount();
        pauseWindowHandler(game);
        exitToMainMenu(game);
        exitToMainMenuSave(game);
        musicHandler(game);
        musicChanger();
        handlePlayerHealth();
        handleFreezeBar();

    }

}