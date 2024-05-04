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
import com.mygdx.game.View.LoginMenu;
import com.mygdx.game.View.ProfileMenu;
import com.mygdx.game.View.SignupMenu;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class AvatarMenuController {
    public static AvatarMenuController avatarMenuController = new AvatarMenuController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private final TextButton backButton = new TextButton("Back", skin , "default");
    private final Label chooseFromDefaultAvatarsLabel = new Label("Choose from default avatars", skin);
    private final Slider chooseFromDefaultAvatars = new Slider(0, 2, 1, false, skin);
    private final TextField imagePath = new TextField( "Enter path", skin);
    private final TextButton chooseAvatar = new TextButton("Choose Avatar", skin , "default");
    public Table table = new Table();


    private AvatarMenuController() {
        table.setFillParent(true);
        table.center();

        table.row().pad(10, 0, 10, 0);
        table.add(chooseFromDefaultAvatarsLabel).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(chooseFromDefaultAvatars).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(imagePath).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(chooseAvatar).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(backButton).fillX().expandX().pad(0 , 800 , 0 , 800);

    }

    public void handleAvatar(MaosVengeance game){
        chooseFromDefaultAvatars.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int avatar = (int) chooseFromDefaultAvatars.getValue();

                MaosVengeance.user.setAvatar(GameAssetsManager.gameAssetsManager.avatars.get(avatar));
            }
        });
    }

    private void backButton(MaosVengeance game){
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(new ProfileMenu(game));
            }
        });
    }

    private void chooseAvatar(MaosVengeance game){
        chooseAvatar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String path = imagePath.getText();
                MaosVengeance.user.setAvatar(path);
            }
        });
    }

    public void handleMainMenuButtons(MaosVengeance game){
        handleAvatar(game);
        backButton(game);
        chooseAvatar(game);
    }

}
