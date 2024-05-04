package com.mygdx.game.Model.Collectibles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.Opps.Opp;
import com.mygdx.game.Model.Player;

public class RadioActiveCollectible extends Collectibles {
    public Sprite sprite = new Sprite(GameAssetsManager.gameAssetsManager.radioActiveCollectibleTexture);
    public Texture texture = GameAssetsManager.gameAssetsManager.radioActiveCollectibleTexture;
    public RadioActiveCollectible(float x, float y) {
        this.x = x;
        this.y = y;
        this.speed = 200;
        this.rect = new CollisionRect(x, y, texture.getWidth(), texture.getHeight());
        CollectibleSpawner.collectibles.add(this);
    }

    @Override
    public void update(float delta) {
        if (!MaosVengeance.isPaused && !MaosVengeance.isGameFreezed){
            y += speed * delta;
        }
        if (x < 0  || y < 0 || y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth()){
            destroy();
        }
        collect();
        rect.move(x, y);
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (!isDestroyed){
            sprite.setPosition(x, y);
            sprite.draw(batch);
        }
    }

    public void collect() {
        if (rect.collidesWith(Player.player.rect)){
            MaosVengeance.radioActiveBombCount++;
            destroy();
        }
    }
}
