package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class GameBackGround {
    public static GameBackGround gameBackGround = new GameBackGround();

    private final ArrayList<Texture> cloudTextures = new ArrayList<Texture>(){
        {
            add(GameAssetsManager.gameAssetsManager.cloud1Texture);
            add(GameAssetsManager.gameAssetsManager.cloud2Texture);
            add(GameAssetsManager.gameAssetsManager.cloud3Texture);
            add(GameAssetsManager.gameAssetsManager.cloud4Texture);
        }
    };

    private ArrayList<Sprite> clouds = new ArrayList<Sprite>();

    private final float cloudMovementSpeed = 1;

    private GameBackGround(){
    }

    public void update(SpriteBatch batch){
        batch.draw(GameAssetsManager.gameAssetsManager.backGroundGame, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(GameAssetsManager.gameAssetsManager.halfBackgroundGame, 0, 0);
        if (new Random().nextInt(100) % 47 == 0){
            clouds.add(new Sprite(cloudTextures.get(new Random().nextInt(4))));
            clouds.get(clouds.size() - 1).setPosition(-200 , (new Random().nextInt(Gdx.graphics.getHeight()) + 500));
        }
        for (int i = 0; i < clouds.size(); i++){
            clouds.get(i).setPosition(clouds.get(i).getX() + cloudMovementSpeed, clouds.get(i).getY() );
            clouds.get(i).draw(batch);
            if (clouds.get(i).getX() > Gdx.graphics.getWidth() + 300){
                clouds.remove(i);
            }
        }
    }
}
