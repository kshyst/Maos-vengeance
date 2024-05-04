package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.PlanesArmaments.Armaments;

import java.util.ArrayList;

public class Player {
    public static Player player = new Player();
    public Texture plane = new Texture(GameAssetsManager.gameAssetsManager.activePlane);
    public Texture planeFlipped = new Texture(GameAssetsManager.gameAssetsManager.activePlaneFlipped);
    public Sprite planeSprite = new Sprite(plane);
    public float velocityX = 0;
    public float velocityY = 0;
    public float speed = 300;
    public float playerHealth = 100;
    public CollisionRect rect ;
    public ArrayList <Armaments> activeArmaments = new ArrayList<Armaments>();

    private Player(){
        planeSprite.setPosition(100, 100);
        rect = new CollisionRect(100, 100, plane.getWidth(), plane.getHeight());
    }
}
