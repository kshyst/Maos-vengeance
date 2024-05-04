package com.mygdx.game.Model.Opps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.Collision.CollisionRect;
import com.mygdx.game.Model.Fire;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.Model.Player;

public class Mig extends Opp{
    public Texture texture = new Texture(GameAssetsManager.gameAssetsManager.migJet);
    public Texture textureFlipped = new Texture(GameAssetsManager.gameAssetsManager.migJetFlipped);
    Sprite alertSprite = new Sprite(new Texture(GameAssetsManager.gameAssetsManager.alert));
    public Sprite sprite = new Sprite(texture);
    private float timeSeconds = 0f;
    private float timeSecondsAnimation = 0f;
    private float period = 1f;
    private float alertTimePeriod = 3f;
    private boolean isFlipped = false;

    private float startingX;
    public Mig(boolean isFlipped , float y) {
        if (isFlipped){
            this.x = Gdx.graphics.getWidth() + 200;
            startingX = Gdx.graphics.getWidth() - 150;
            this.sprite.setTexture(textureFlipped);
            this.speed = 200 * MaosVengeance.difficulty * -1;
        }
        else {
            this.x = -200;
            startingX = 50;
            this.sprite.setTexture(texture);
            this.speed = 200 * MaosVengeance.difficulty;
        }
        this.y = y;
        this.health = 20;
        this.rect = new CollisionRect(x, y, texture.getWidth() - 10 , texture.getHeight() - 10);
        this.fire = new Fire();
        this.isFlipped = isFlipped;
        alertSprite.setPosition(startingX  , y );
    }
    @Override
    public void update(float delta) {
        if (!MaosVengeance.isGameFreezed && !MaosVengeance.isPaused){
            x += speed * delta;
        }
        if (x > Gdx.graphics.getWidth() + 500 || x < -500 || health <= 0){
            isDestroyed = true;
            if (health <= 0){
                MaosVengeance.killCount += 8;
            }
        }
        rect.move(x, y);
        fireMissles();

        if (!isFlipped && x < startingX){
            alert();
        }
        else if (isFlipped && x > startingX){
            alert();
        }
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(x, y);
        sprite.draw(batch);
        this.fire.fireUpdate(x , y , batch);

        if (!isFlipped && x < startingX){
            alertSprite.draw(batch);
        }
        else if (isFlipped && x > startingX) {
            alertSprite.draw(batch);
        }
    }

    private void fireMissles(){
        if (Player.player.planeSprite.getY() - this.y < 500 * MaosVengeance.difficulty){
            timeSeconds +=Gdx.graphics.getRawDeltaTime();
            if(timeSeconds > period){
                timeSeconds-=period;
                new OppMissle(x + (float) texture.getWidth() / 2 , y + (float) texture.getHeight() / 2 , 200 , 3 , new Texture(GameAssetsManager.gameAssetsManager.oppMissleMig));
            }
        }
    }

    private void alert(){
        Animation<Texture> animation = GameAssetsManager.gameAssetsManager.alertFrames;
        alertSprite.setRegion(animation.getKeyFrame(timeSecondsAnimation));
        if (!animation.isAnimationFinished(timeSecondsAnimation)) {
            timeSecondsAnimation += Gdx.graphics.getDeltaTime();
        }
        else {
            timeSecondsAnimation = 0;
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
}
