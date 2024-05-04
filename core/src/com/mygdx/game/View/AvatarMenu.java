package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Control.AvatarMenuController;
import com.mygdx.game.Control.MainMenuController;
import com.mygdx.game.Control.SignupMenuController;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;

import java.lang.management.ManagementFactory;

public class AvatarMenu implements Screen {

    MaosVengeance game;
    SpriteBatch batch;
    private Stage stage;


    public AvatarMenu (MaosVengeance game){
        this.game = game;
        this.batch = game.batch;
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(AvatarMenuController.avatarMenuController.table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        AvatarMenuController.avatarMenuController.handleMainMenuButtons(game);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

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
    }

}