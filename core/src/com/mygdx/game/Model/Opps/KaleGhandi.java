package com.mygdx.game.Model.Opps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.GameAssetsManager;

public class KaleGhandi extends Opp {
    public Sprite sprite = new Sprite(GameAssetsManager.gameAssetsManager.kaleGhandiTexture);
    public Texture texture = GameAssetsManager.gameAssetsManager.kaleGhandiTexture;
    public KaleGhandi(float x, float y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
        this.health = 1;
        this.damage = 0;
        this.rect = new CollisionRect(x, y, texture.getWidth(), texture.getHeight());
        this.rectAgainstOtherOpps = new CollisionRect(x , y , texture.getWidth() + 5 , 1);
    }

    @Override
    public void update(float delta) {
        if (x < 0 || health <= 0 || y < 0 || y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth()){
            isDestroyed = true;
        }
        rect.move(x, y);
        rectAgainstOtherOpps.move(x , y);
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }
}
