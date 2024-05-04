package com.mygdx.game.Model.PlanesArmaments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.OppSpawner;
import com.mygdx.game.Model.Opps.Opp;
import com.mygdx.game.Model.Player;

public class ClusterBomb extends Armaments{
    public Texture texture = new Texture(GameAssetsManager.gameAssetsManager.smallMissleFrame1 );
    public Sprite sprite = new Sprite(texture);
    public float time = 0;
    public ClusterBomb(float x, float y, float speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.velocityX = 500 * Player.player.velocityX;
        this.damage = 10;
        this.rect = new CollisionRect(x, y, texture.getWidth(), texture.getHeight());
    }
    @Override
    public void update(float delta) {
        if (!isDestroyed){
            if (canMove && !MaosVengeance.isPaused){
                y -= speed * delta;
                x += velocityX * delta;
                if (velocityX > 0){
                    velocityX -= 5f;
                }
                if (velocityX < 0){
                    velocityX += 5f;
                }
                rect.move(x, y);
                for (Opp opp : OppSpawner.oppSpawner.opps){
                    dealDamage(opp);
                }
            }
            if (y < -50 || !canMove){
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        canMove = false;
        Animation<Texture> animation = GameAssetsManager.gameAssetsManager.explosionFrames;
        sprite.setRegion(animation.getKeyFrame(time));
        if (!animation.isAnimationFinished(time)) {
            time += Gdx.graphics.getDeltaTime();
        }
        else {
            time = 0;
        }
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        if (animation.isAnimationFinished(time)){
            isDestroyed = true;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
        sprite.setPosition(x, y);
        if (velocityX> 0){
            sprite.setRotation(-(float) (Math.atan(speed/velocityX) * 180/3.1415f) % 360);
        }
        if (velocityX < 0){
            sprite.setRotation(180 - (float) (Math.atan(speed/velocityX) * 180/3.1415f) % 360);
        }
    }

    private void dealDamage(Opp opp){
        if (this.rect.collidesWith(opp.rect)){
            MaosVengeance.hitCount++;
            opp.health -= this.damage;
            this.destroy();
        }
    }
}
