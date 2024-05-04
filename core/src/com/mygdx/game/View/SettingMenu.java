package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Control.LoginMenuController;
import com.mygdx.game.Control.ProfileMenuController;
import com.mygdx.game.Control.SettingMenuController;
import com.mygdx.game.Control.SignupMenuController;
import com.mygdx.game.MaosVengeance;

public class SettingMenu implements Screen {

    MaosVengeance game;
    SpriteBatch batch;
    private Stage stage;


    public SettingMenu (MaosVengeance game){
        this.game = game;
        this.batch = game.batch;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(SettingMenuController.settingMenuController.table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        SettingMenuController.settingMenuController.handleMainMenuButtons(game);

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