package com.mygdx.game.Model.Collectibles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class CollectibleSpawner {
    public static ArrayList<Collectibles> collectibles = new ArrayList<Collectibles>();

    public static void update(float delta , SpriteBatch batch){
        for(int i = 0; i < collectibles.size(); i++){
            collectibles.get(i).update(delta);
            collectibles.get(i).render(batch);
            if(collectibles.get(i).isDestroyed){
                collectibles.remove(i);
            }
        }
    }
}
