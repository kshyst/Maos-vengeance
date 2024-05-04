package com.mygdx.game.Model.Opps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.Fire;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.OppSpawner;

public class Truck extends Opp{
    public Texture texture = GameAssetsManager.gameAssetsManager.truckTexture;
    public Sprite sprite = new Sprite(texture);
    public Truck() {
        this.x = -200;
        this.y = 100;
        this.speed = 100 * MaosVengeance.difficulty;
        this.health = 30;
        this.damage = 0;
        this.rect = new CollisionRect(x, y, texture.getWidth() - 10 , texture.getHeight() - 10);
        this.rectAgainstOtherOpps = new CollisionRect(x , y , texture.getWidth() + 20 , 1);
        this.fire = new Fire();
    }
    @Override
    public void update(float delta) {
        if (!MaosVengeance.isGameFreezed && !MaosVengeance.isPaused){
            x += speed * delta;
        }
        if (x > Gdx.graphics.getWidth() + 100 || health <= 0){
            isDestroyed = true;
            if (health <= 0){
                MaosVengeance.killCount += 5;
            }
        }
        dodgeObstacles();
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
        this.fire.fireUpdate(x , y + 30 , batch);
    }

    private void dodgeObstacles(){
        for (Opp opp : OppSpawner.oppSpawner.opps){
            if (opp instanceof Building || opp instanceof KaleGhandi || opp instanceof Wall){
                if (opp.rect.collidesWith(rectAgainstOtherOpps)){
                    if (opp.y > y || opp instanceof Building){
                        y -= 3;
                    }
                    if (opp.y < y && !(opp instanceof Building)){
                        y += 3;
                    }
                }
            }
        }
    }
}
