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
import com.mygdx.game.View.*;

import java.lang.management.ManagementFactory;

import static com.mygdx.game.Model.Player.player;

public class WaveFailedController {
    public static WaveFailedController waveFailedController = new WaveFailedController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));

    private final Label waveFailed = new Label("Back to the rice farms", skin, "title");
    private final TextButton exitToMenu = new TextButton("Exit to Menu", skin);
    public Table table = new Table();

    private WaveFailedController() {
        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(waveFailed).fillX();
        waveFailed.setColor(Color.RED);
        table.row().pad(10, 0, 10, 0);
        table.add(exitToMenu).fillX();
        table.row().pad(10, 0, 10, 0);
    }

    private void exitToMenu(MaosVengeance game){
        exitToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (MaosVengeance.user != null){
                    MaosVengeance.user.setLastRound(1);
                    ResourceManger.saveGameProgress(1);
                }
                else {
                    MaosVengeance.currentWave = 1;
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
    public void handleMainMenuButtons(MaosVengeance game){
        exitToMenu(game);
    }

}