package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.OppSpawner;
import com.mygdx.game.Model.Opps.Tank;
import com.mygdx.game.Model.PlanesArmaments.PlaneBomb;
import com.mygdx.game.Model.PlanesArmaments.RadioActiveBomb;
import com.mygdx.game.Model.Player;

import java.util.Random;

public class PlayerController {
    private static final Player player = Player.player;
    private static final float speed = Player.player.speed;
    public static boolean passWave = false;
    public static int controllerMode = 0;

    public static void controlPlayer(MaosVengeance game){
        handleInput(game);
        updatePlayerPosition();
        airDrag();
        putPlayerInsideBox();
        normalBomb();
        radioActiveBomb();
        clusterBomb();
        handlePlaneDirection();
        cheatCodes(game);
    }

    private static void handleInput(MaosVengeance game){
        if (controllerMode == 0){
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                if (player.velocityY < 1 )
                    player.velocityY += 0.1f;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                if (player.velocityY > -1)
                    player.velocityY -= 0.1f;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                if (player.velocityX > -1)
                    player.velocityX -= 0.1f;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                if (player.velocityX < 1)
                    player.velocityX += 0.1f;
            }
        }
        else {
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                if (player.velocityY < 1 )
                    player.velocityY += 0.1f;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                if (player.velocityY > -1)
                    player.velocityY -= 0.1f;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                if (player.velocityX > -1)
                    player.velocityX -= 0.1f;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                if (player.velocityX < 1)
                    player.velocityX += 0.1f;
            }
        }
    }

    private static void updatePlayerPosition(){
        if (!MaosVengeance.isPaused){
            player.planeSprite.setX(player.planeSprite.getX() + player.velocityX * speed * Gdx.graphics.getDeltaTime());
            player.planeSprite.setY(player.planeSprite.getY() + player.velocityY * speed * Gdx.graphics.getDeltaTime());
            player.rect.move(player.planeSprite.getX() , player.planeSprite.getY());
        }
    }

    private static void airDrag(){
        if(player.velocityX > 0){
            player.velocityX -= 0.01f;
        }
        if(player.velocityX < 0){
            player.velocityX += 0.01f;
        }
        if(player.velocityY > 0){
            player.velocityY -= 0.01f;
        }
        if(player.velocityY < 0){
            player.velocityY += 0.01f;
        }
    }

    private static void putPlayerInsideBox(){
        if(player.planeSprite.getX() < 0 - player.planeSprite.getWidth() + 40){
            player.planeSprite.setX(Gdx.graphics.getWidth() + player.planeSprite.getWidth() - 40);
        }
        if(player.planeSprite.getX() > Gdx.graphics.getWidth() + player.planeSprite.getWidth() - 40){
            player.planeSprite.setX(0 - player.planeSprite.getWidth() + 40);
            System.out.println(player.planeSprite.getX());
        }
        if(player.planeSprite.getY() < 220){
            player.planeSprite.setY(220);
        }
        if(player.planeSprite.getY() > Gdx.graphics.getHeight() - player.planeSprite.getHeight()){
            player.planeSprite.setY(Gdx.graphics.getHeight() - player.planeSprite.getHeight());
        }
    }

    private static void handlePlaneDirection(){
        if (player.velocityX > 0){
            player.planeSprite.setTexture(player.plane);
        }
        if (player.velocityX < 0){
            player.planeSprite.setTexture(player.planeFlipped);
        }
    }
    private static void normalBomb(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !MaosVengeance.isGameFreezed){
            player.activeArmaments.add(new PlaneBomb(player.planeSprite.getX() + player.planeSprite.getWidth() / 2, player.planeSprite.getY() - 10, 500 , 1));
            MaosVengeance.firedCount++;
        }
    }
    private static void radioActiveBomb(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.R) && MaosVengeance.radioActiveBombCount > 0 && !MaosVengeance.isGameFreezed){
            player.activeArmaments.add(new RadioActiveBomb(player.planeSprite.getX() + player.planeSprite.getWidth() / 2, player.planeSprite.getY() - 10, 100));
            MaosVengeance.firedCount++;
            MaosVengeance.radioActiveBombCount--;
        }
    }

    private static void clusterBomb(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.C) && MaosVengeance.clusterBombCount > 0 && !MaosVengeance.isGameFreezed){
            player.activeArmaments.add(new PlaneBomb(player.planeSprite.getX() + player.planeSprite.getWidth() / 2 + 10, player.planeSprite.getY() - 10, 500 , 1));
            player.activeArmaments.add(new PlaneBomb(player.planeSprite.getX() + player.planeSprite.getWidth() / 2 - 10, player.planeSprite.getY() - 10, 500 , -1));
            player.activeArmaments.add(new PlaneBomb(player.planeSprite.getX() + player.planeSprite.getWidth() / 2, player.planeSprite.getY() - 10, 500 , 0));
            player.activeArmaments.add(new PlaneBomb(player.planeSprite.getX() + player.planeSprite.getWidth() / 2 + 20, player.planeSprite.getY() - 10, 500 , 2));
            player.activeArmaments.add(new PlaneBomb(player.planeSprite.getX() + player.planeSprite.getWidth() / 2 - 20, player.planeSprite.getY() - 10, 500 , -2));
            MaosVengeance.firedCount += 5;
            MaosVengeance.clusterBombCount--;
        }
    }
    private static void cheatCodes(MaosVengeance game){
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)){
            Tank tank = new Tank();
            tank.x = new Random().nextInt(Gdx.graphics.getWidth());
            tank.y = new Random().nextInt(150);
            OppSpawner.oppSpawner.opps.add(tank);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            passWave = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB) && MaosVengeance.canFreeze){
            if (MaosVengeance.isGameFreezed){
                MaosVengeance.canFreeze = false;
            }
            MaosVengeance.isGameFreezed = !MaosVengeance.isGameFreezed;

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)){
            MaosVengeance.radioActiveBombCount ++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_RIGHT)){
            MaosVengeance.clusterBombCount ++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)){
            player.playerHealth = 100;
        }
    }
}
