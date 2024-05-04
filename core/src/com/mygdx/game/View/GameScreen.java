package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Control.GameUIController;
import com.mygdx.game.Control.MainMenuController;
import com.mygdx.game.Control.PlayerController;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.*;
import com.mygdx.game.Model.Collectibles.CollectibleSpawner;
import com.mygdx.game.Model.Collectibles.Collectibles;
import com.mygdx.game.Model.Opps.OppMissle;
import com.mygdx.game.Model.Opps.OppMissleController;
import com.mygdx.game.Model.PlanesArmaments.Armaments;
import com.mygdx.game.View.WaveEnds.WaveFailedScreen;

import java.util.ArrayList;
import java.util.Stack;

import static com.mygdx.game.Model.Player.player;

public class GameScreen implements Screen {

    public SpriteBatch batch;
    MaosVengeance game;
    private Stage stage;
    public GameScreen (MaosVengeance game){
        this.game = game;
        this.batch = game.batch;

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(GameUIController.gameUIController.table);
    }

    @Override
    public void render(float v) {
        renderGameScreen();
        WaveChanger.waveChange(game);
        PlayerController.controlPlayer(game);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        GameUIController.gameUIController.handleMainMenuButtons(game);
        pause();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.isPaused = !game.isPaused;
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.stage.dispose();
        player.activeArmaments.clear();
        CollectibleSpawner.collectibles = new ArrayList<>();
        OppMissleController.oppMissles = new ArrayList<>();
        OppSpawner.oppSpawner = new OppSpawner();
        GameUIController.gameUIController.pauseWindow.setVisible(false);
        MaosVengeance.isPaused = false;
        MaosVengeance.isGameFreezed =false;
        MaosVengeance.clusterBombCount = 0;
        MaosVengeance.radioActiveBombCount = 0;
    }

    public void uiDispose(){
        this.stage.dispose();
    }

    public void fullDispose(){
        this.dispose();
        MaosVengeance.killCount = 0;
        MaosVengeance.hitCount = 0;
        MaosVengeance.firedCount = 0;
        player.playerHealth = 100;
    }

    private void renderGameScreen() {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        //background
        GameBackGround.gameBackGround.update(batch);
        //armaments
        for (Armaments armament : player.activeArmaments) {
            armament.render(batch);
            armament.update(Gdx.graphics.getDeltaTime());
        }
        //plane
        player.planeSprite.draw(batch);
        //opps
        OppSpawner.oppSpawner.update(Gdx.graphics.getDeltaTime(), batch);
        //check for dead opps
        for (int i = 0; i < OppSpawner.oppSpawner.opps.size(); i++) {
            if (OppSpawner.oppSpawner.opps.get(i).isDestroyed) {
                OppSpawner.oppSpawner.opps.remove(i);
            }
        }
        //check for dead armaments
        for (int i = 0; i < player.activeArmaments.size(); i++) {
            if (player.activeArmaments.get(i).isDestroyed) {
                player.activeArmaments.remove(i);
            }
        }
        //check for dead player
        if (player.playerHealth <= 0) {
            ((GameScreen) game.getScreen()).fullDispose();
            game.setScreen(new WaveFailedScreen(game));
        }
        addAndRemoveFreezeScreen();
        CollectibleSpawner.update(Gdx.graphics.getDeltaTime(), batch);
        OppMissleController.update(Gdx.graphics.getDeltaTime(), batch);
        batch.end();

    }

    private void addAndRemoveFreezeScreen(){
        Sprite freezeSprite = new Sprite(GameAssetsManager.gameAssetsManager.freezeTexture);
        freezeSprite.setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        if (MaosVengeance.isGameFreezed){
            freezeSprite.draw(batch);
        }
    }
}
