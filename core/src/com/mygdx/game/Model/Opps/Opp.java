package com.mygdx.game.Model.Opps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.Fire;

public abstract class Opp {
    public float speed;
    public float health;
    public float damage;
    public float x;
    public float y;
    public CollisionRect rect;
    public CollisionRect rectAgainstOtherOpps;
    public Fire fire;

    public boolean isDestroyed = false;
    public abstract void update(float delta);
    public abstract void destroy();
    public abstract void render(SpriteBatch batch);
}
