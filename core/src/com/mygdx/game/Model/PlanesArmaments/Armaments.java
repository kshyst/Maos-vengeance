package com.mygdx.game.Model.PlanesArmaments;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.Collision.CollisionRect;

public abstract class Armaments {
    public float x;
    public float y;
    public float velocityX;
    public float speed;
    public float damage;
    public CollisionRect rect;
    public boolean isDestroyed = false;
    public boolean canMove = true;
    public abstract void update(float delta);
    public abstract void destroy();
    public abstract void render(SpriteBatch batch);
}
