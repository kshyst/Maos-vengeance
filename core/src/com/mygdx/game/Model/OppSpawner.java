package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.Opps.*;

import java.util.ArrayList;
import java.util.Random;

public class OppSpawner {
    public static OppSpawner oppSpawner = new OppSpawner();
    public ArrayList<Opp> opps = new ArrayList<>();
    private boolean hasSpawnedOneTimes = false;
    private float timeSeconds = 0f;
    private float truckSpawnPeriod = 3f;
    private float truckSpawnTimer = 3f;
    private float tankSpawnPeriod = 5f;
    private float tankSpawnTimer = 5f;
    private float migSpawnPeriod = 3f ;
    private float migSpawnTimer = 3f ;
    private boolean isMigFlipped = false;

    public void update(float delta , SpriteBatch batch) {
        for (int i = 0; i < opps.size(); i++) {
            opps.get(i).update(delta);
            opps.get(i).render(batch);
        }
        spawnOpp();
        timeSeconds +=Gdx.graphics.getRawDeltaTime();
    }

    private void spawnOpp() {
        spawnTruck();
        if (WaveChanger.canSpawnTank)
            spawnTank();
        if (WaveChanger.canSpawnMig)
            spawnMig();
        if (!hasSpawnedOneTimes) {
            spawnBuilding();
            spawnWall();
            spawnKaleGhandi();
            hasSpawnedOneTimes = true;
        }
    }

    private void spawnTruck() {
        if(timeSeconds > truckSpawnTimer){
            truckSpawnTimer += truckSpawnPeriod;
            opps.add(new Truck());
        }
    }

    private void spawnBuilding() {
        for (int i = 0; i < WaveChanger.buildingCount; i++) {
            opps.add(new Building(new Random().nextInt(Gdx.graphics.getWidth()), new Random().nextInt(150)));
        }
    }

    private void spawnKaleGhandi() {
        for (int i = 0; i < WaveChanger.kaleGhanaCount; i++) {
            opps.add(new KaleGhandi(new Random().nextInt(Gdx.graphics.getWidth()), new Random().nextInt(150)));
        }
    }

    private void spawnTank() {
        if (timeSeconds > tankSpawnTimer){
            tankSpawnTimer += tankSpawnPeriod;
            opps.add(new Tank());
        }
    }

    private void spawnWall() {
        for (int i = 0; i < WaveChanger.wallCount; i++) {
            opps.add(new Wall(new Random().nextInt(Gdx.graphics.getWidth()), new Random().nextInt(150)));
        }
    }

    private void spawnMig() {
        if (timeSeconds > migSpawnTimer){
            migSpawnTimer += migSpawnPeriod;
            if (isMigFlipped){
                opps.add(new Mig(false , new Random().nextInt(700) + 200));
            }
            else {
                opps.add(new Mig(true , new Random().nextInt(700) + 200));
            }
            isMigFlipped = !isMigFlipped;
        }
    }

    public OppSpawner() {
    }
}
