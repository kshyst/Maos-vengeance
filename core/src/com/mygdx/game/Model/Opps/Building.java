package com.mygdx.game.Model.Opps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.Collectibles.RadioActiveCollectible;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.Fire;
import com.mygdx.game.Model.GameAssetsManager;

public class Building extends Opp {
    public Sprite sprite = new Sprite(GameAssetsManager.gameAssetsManager.buildingTexture);
    public Texture texture = GameAssetsManager.gameAssetsManager.buildingTexture;
    public Building(float x, float y) {
        this.x = x;
        this.y = y;
        this.speed = 0;
        this.health = 200;
        this.damage = 0;
        this.rect = new CollisionRect(x, y, texture.getWidth() - 20, texture.getHeight() -20);
        this.rectAgainstOtherOpps = new CollisionRect(x , y , texture.getWidth() + 20 , 1);
        this.fire = new Fire();
    }

    @Override
    public void update(float delta) {
        if (x < 0 || health <= 0 || y < 0 || y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth()){
            isDestroyed = true;
            new RadioActiveCollectible(x, y);
            MaosVengeance.killCount += 10;
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
        this.fire.fireUpdate(x + 50 , y , batch);
    }
}
