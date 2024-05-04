package com.mygdx.game.Model.Opps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.Player;

public class OppMissle {
    public Texture texture = new Texture(GameAssetsManager.gameAssetsManager.oppMissleTank);
    public Sprite sprite = new Sprite(texture);
    public float x;
    public float y;
    public float damage;
    public float speed;
    public float targetX;
    public float targetY;
    public CollisionRect rect;
    boolean isDestroyed , canMove;
    public float time = 0;
    private float timeSeconds = 0f;
    private float period = 5f;
    public OppMissle(float x, float y, float speed , float period , Texture texture){
        this.sprite.setTexture(texture);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = 5;
        this.rect = new CollisionRect(x, y, texture.getWidth(), texture.getHeight());
        this.targetX = Player.player.planeSprite.getX();
        this.targetY = Player.player.planeSprite.getY();
        this.isDestroyed = false;
        this.canMove = true;
        this.period = period;
        OppMissleController.oppMissles.add(this);
    }

    public void update(float delta) {
        if (!isDestroyed){
            this.targetX = Player.player.planeSprite.getX();
            this.targetY = Player.player.planeSprite.getY();
            if (canMove && !MaosVengeance.isPaused){
                float angle = (float) Math.atan2(targetY - y, targetX - x);
                float xVel = (float) (speed * Math.cos(angle));
                float yVel = (float) (speed * Math.sin(angle));
                x += xVel * delta;
                y += yVel * delta;
                sprite.setRotation((float) (angle * (float) 180 / Math.PI));
                rect.move(x, y);
                dealDamage();
                destroyAfterSomeTime();
            }
            if (y < -50 || y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth() || x < 0 || !canMove){
                destroy();
            }
        }
    }

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

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
        sprite.setPosition(x, y);
    }

    private void dealDamage(){
        if (this.rect.collidesWith(Player.player.rect)){
            Player.player.playerHealth -= this.damage;
            this.destroy();
        }
    }

    private void destroyAfterSomeTime(){
        timeSeconds +=Gdx.graphics.getRawDeltaTime();
        if(timeSeconds > period){
            timeSeconds-=period;
            destroy();
        }
    }
}
