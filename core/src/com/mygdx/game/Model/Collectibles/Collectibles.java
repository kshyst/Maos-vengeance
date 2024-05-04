package com.mygdx.game.Model.Collectibles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.Collision.CollisionRect;

public abstract class Collectibles {
    public float speed;
    public float x;
    public float y;
    public CollisionRect rect;

    public boolean isCollected = false;
    public boolean isDestroyed = false;
    public abstract void update(float delta);
    public abstract void destroy();
    public abstract void render(SpriteBatch batch);
}
