package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fire {
    float time = 0;
    Sprite fireSprite = new Sprite(new Texture(GameAssetsManager.gameAssetsManager.invisiblePic));
    boolean isFireStarted = false;
    public void StartFire() {
        isFireStarted = true;
        Animation<Texture> animation = GameAssetsManager.gameAssetsManager.fireFrames;
        fireSprite.setRegion(animation.getKeyFrame(time));
        if (!animation.isAnimationFinished(time)) {
            time += Gdx.graphics.getDeltaTime();
        }
        else {
            time = 0;
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void fireUpdate(float x, float y , SpriteBatch batch) {
        fireSprite.draw(batch);
        fireSprite.setPosition(x - 50, y);
        fireSprite.setSize(200 , 200);

        if (isFireStarted){
            StartFire();
        }
    }
}
