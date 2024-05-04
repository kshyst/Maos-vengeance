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

public class ProfileMenuController {
    public static ProfileMenuController profileMenuController = new ProfileMenuController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private TextField newUserName = new TextField("" ,  skin);
    private TextField newPassWord = new TextField("", skin);
    private TextButton changeButtonUsername = new TextButton("Change Username", skin , "default");
    private TextButton changeButtonPassword = new TextButton("Change Password", skin , "default");
    private TextButton logoutButton = new TextButton("Logout", skin , "default");
    private TextButton deleteAccount = new TextButton("Delete Account", skin , "default");
    private TextButton avatarButton = new TextButton("Change Avatar", skin , "default");
    private TextButton backButton = new TextButton("Back", skin , "default");
    private Label changeUserName = new Label("change username", skin);
    private Label changePassword = new Label("change password", skin);
    private Label profile = new Label("Profile", skin);
    private final Label errorLabelUsername = new Label("", skin );
    private final Label errorLabelPassword = new Label("", skin );
    public Table table = new Table();


    private ProfileMenuController() {
        table.setFillParent(true);
        table.center();

        table.addActor(profile);

        table.row().pad(10, 0, 10, 0);
        table.add(changeUserName).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(newUserName).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabelUsername).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(changeButtonUsername).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(changePassword).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(newPassWord).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(errorLabelPassword).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(changeButtonPassword).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(avatarButton).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(deleteAccount).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(100, 0, 10, 0);
        table.add(logoutButton).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(backButton).fillX().expandX().pad(0 , 800 , 0 , 800);

    }

    private void changePassword(MaosVengeance game){
        changeButtonPassword.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String password = newPassWord.getText();

                if (Pattern.compile(Regex.PASSWORD.getRegex()).matcher(password).matches()){
                    ArrayList<User> users = null;
                    try {
                        users = (ArrayList<User>) ResourceManger.load(GameAssetsManager.gameAssetsManager.saveDataPath);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        errorLabelPassword.setText("Error loading users");
                        return;
                    }

                    for (User user : users){
                        if (user.getUsername().equals(MaosVengeance.user.getUsername())){
                            user.setPassword(password);
                            break;
                        }
                    }

                    try {
                        ResourceManger.save(users , GameAssetsManager.gameAssetsManager.saveDataPath);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        errorLabelPassword.setText("Error saving users");
                        return;
                    }

                    errorLabelPassword.setText("Password changed successfully");
                    MaosVengeance.user.setPassword(password);
                    game.getScreen().dispose();
                    game.setScreen(new MainMenu(game));
                }
                else{
                    errorLabelPassword.setText("password must obey this regex : " + Regex.PASSWORD.getRegex());
                }
            }
        });
    }

    private void changeUsername(MaosVengeance game){
        changeButtonUsername.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                String username = newUserName.getText();

                if (Pattern.compile(Regex.USERNAME.getRegex()).matcher(username).matches()){
                    ArrayList<User> users = null;
                    try {
                        users = (ArrayList<User>) ResourceManger.load(GameAssetsManager.gameAssetsManager.saveDataPath);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        errorLabelUsername.setText("Error loading users");
                        return;
                    }

                    for (User user : users){
                        if (user.getUsername().equals(username)){
                            errorLabelUsername.setText("Username already exists");
                            return;
                        }
                    }

                    for (User user : users){
                        if (user.getUsername().equals(MaosVengeance.user.getUsername())){
                            user.setUsername(username);
                            break;
                        }
                    }

                    try {
                        ResourceManger.save(users , GameAssetsManager.gameAssetsManager.saveDataPath);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        errorLabelUsername.setText("Error saving users");
                        return;
                    }


                    errorLabelUsername.setText("Username changed successfully");
                    MaosVengeance.user.setUsername(username);
                    game.getScreen().dispose();
                    game.setScreen(new MainMenu(game));
                }
                else{
                    errorLabelUsername.setText("username must obey this regex : " + Regex.USERNAME.getRegex());
                }
            }
        });
    }

    private void deleteAccount(MaosVengeance game){
        deleteAccount.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ArrayList<User> users ;
                try {
                    users = (ArrayList<User>) ResourceManger.load(GameAssetsManager.gameAssetsManager.saveDataPath);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return;
                }

                for (User user : users){
                    if (user.getUsername().equals(MaosVengeance.user.getUsername())){
                        users.remove(user);
                        break;
                    }
                }

                try {
                    ResourceManger.save(users , GameAssetsManager.gameAssetsManager.saveDataPath);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return;
                }

                MaosVengeance.user = null;
                newUserName.setText("");
                newPassWord.setText("");
                errorLabelPassword.setText("");
                errorLabelUsername.setText("");
                game.getScreen().dispose();
                game.setScreen(new MainMenu(game));
            }
        });
    }

    private void logout(MaosVengeance game){
        logoutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MaosVengeance.user = null;
                newUserName.setText("");
                newPassWord.setText("");
                errorLabelPassword.setText("");
                errorLabelUsername.setText("");
                MainMenuController.mainMenuController = new MainMenuController();
                game.getScreen().dispose();
                game.setScreen(new MainMenu(game));
            }
        });
    }

    private void enterAvatarMenu(MaosVengeance game){
        avatarButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(new AvatarMenu(game));
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

    public void handleMainMenuButtons(MaosVengeance game){
        changePassword(game);
        changeUsername(game);
        logout(game);
        deleteAccount(game);
        enterAvatarMenu(game);
        backButton(game);
    }

}
