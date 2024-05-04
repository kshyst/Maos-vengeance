package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.SaveData.ResourceManger;
import com.mygdx.game.Model.SaveData.User;
import com.mygdx.game.View.AvatarMenu;
import com.mygdx.game.View.GameScreen;
import com.mygdx.game.View.MainMenu;
import com.mygdx.game.View.SignupMenu;

import java.util.ArrayList;

public class LoginMenuController {
    public static LoginMenuController loginMenuController = new LoginMenuController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private TextField username = new TextField("" ,  skin);
    private TextField password = new TextField("", skin);
    private TextButton dontHaveAccount = new TextButton("don't have an account?", skin , "default");
    private TextButton loginButton = new TextButton("Login", skin , "default");
    private TextButton backButton = new TextButton("Back", skin , "default");
    private Label login = new Label("Login", skin);
    private Label usernameLabel = new Label("Username", skin);
    private Label passwordLabel = new Label("Password", skin);
    private final Label errorLabel = new Label("", skin );
    public Table table = new Table();


    private LoginMenuController() {
        table.setFillParent(true);
        table.center();

        table.addActor(login);
        login.setFontScale(2);
        login.setPosition((float) Gdx.graphics.getWidth() /2 - login.getWidth() / 2, 1000);

        table.row().pad(10, 0, 10, 0);
        table.add(usernameLabel).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(username).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(passwordLabel).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(password).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(errorLabel).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(dontHaveAccount).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(loginButton).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(backButton).fillX().expandX().pad(0 , 800 , 0 , 800);


        username.setWidth(500);
        password.setWidth(500);
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
    }

    private void userNameFieldController(){
    }

    private void passwordFieldController(){
    }

    private void dontHaveAccountButton(MaosVengeance game){
        dontHaveAccount.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(new SignupMenu(game));
                this.cancel();
            }
        });
    }

    private void loginButtonController(MaosVengeance game){
        loginButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String usernameText = username.getText();
                String passwordText = password.getText();

                ArrayList<User> allUsers;
                try {
                    allUsers = (ArrayList<User>) ResourceManger.load(GameAssetsManager.gameAssetsManager.saveDataPath);
                }
                catch (Exception e){
                    return;
                }

                if (usernameText.equals("") || passwordText.equals("")){
                    errorLabel.setText("Username or password cannot be empty");
                    return;
                }

                for (User user : allUsers){
                    if (user.getUsername().equals(usernameText) && user.getPassword().equals(passwordText)) {
                        username.setText("");
                        password.setText("");
                        errorLabel.setText("");
                        MaosVengeance.user = user;
                        game.getScreen().dispose();
                        game.setScreen(new MainMenu(game));
                        return;

                    }
                }

                errorLabel.setText("Username or password is incorrect");
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
        if (username.getFocusTraversal()){
            userNameFieldController();
        }
        if (password.getFocusTraversal()){
            passwordFieldController();
        }
        dontHaveAccountButton(game);
        loginButtonController(game);
        backButton(game);
    }

}
