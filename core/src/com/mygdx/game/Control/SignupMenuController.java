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
import com.mygdx.game.View.SignupMenu;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class SignupMenuController {
    public static SignupMenuController signupMenuController = new SignupMenuController();
    private final Skin skin = new Skin(Gdx.files.internal(GameAssetsManager.gameAssetsManager.uiSkin));
    private TextField username = new TextField("" ,  skin);
    private TextField password = new TextField("", skin);
    private TextButton loginButton = new TextButton("Sign Up", skin , "default");
    private TextButton backButton = new TextButton("Back", skin , "default");
    private Label signup = new Label("Sign Up", skin);
    private Label usernameLabel = new Label("Username", skin);
    private Label passwordLabel = new Label("Password", skin);
    private final Label errorLabel = new Label("", skin );
    public Table table = new Table();


    private SignupMenuController() {
        table.setFillParent(true);
        table.center();

        table.addActor(signup);
        signup.setFontScale(2);
        signup.setPosition((float) Gdx.graphics.getWidth() /2 - signup.getWidth() / 2, 1000);

        table.row().pad(10, 0, 10, 0);
        table.add(usernameLabel).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(username).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(passwordLabel).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(10, 0, 10, 0);
        table.add(password).fillX().expandX().pad(0 , 800 , 0 , 800);
        table.row().pad(20, 0, 10, 0);
        table.add(errorLabel);
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

    private void signUpButtonController(MaosVengeance game){
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
                    errorLabel.setText("Error loading save data");
                    return;
                }

                if (usernameText.equals("") || passwordText.equals("")){
                    errorLabel.setText("Username or password cannot be empty");
                    return;
                }

                if (!Pattern.compile(Regex.USERNAME.getRegex()).matcher(usernameText).matches()){
                    errorLabel.setText("make your username obey this regex = " + Regex.USERNAME.getRegex());
                    return;
                }

                if (!Pattern.compile(Regex.PASSWORD.getRegex()).matcher(passwordText).matches()){
                    errorLabel.setText("make your password obey this regex = " + Regex.PASSWORD.getRegex());
                    return;
                }

                for (User user : allUsers){
                    if (user.getUsername().equals(usernameText)){
                        errorLabel.setText("Username already exists");
                        return;
                    }
                }

                String avatarDefault = GameAssetsManager.gameAssetsManager.avatars.get(new Random().nextInt(GameAssetsManager.gameAssetsManager.avatars.size()));

                allUsers.add(new User(usernameText , passwordText , avatarDefault , 0 , 0 , 0 , 0 , 0));

                try {
                    ResourceManger.save(allUsers , GameAssetsManager.gameAssetsManager.saveDataPath);
                }
                catch (Exception e){
                    errorLabel.setText("Error saving data");
                    return;
                }

                username.setText("");
                password.setText("");
                errorLabel.setText("");

                game.getScreen().dispose();
                game.setScreen(new LoginMenu(game));

                System.out.println("User signed up");
            }
        });
    }

    private void backButtonController(MaosVengeance game){
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(new LoginMenu(game));
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
        backButtonController(game);
        signUpButtonController(game);
    }

}
