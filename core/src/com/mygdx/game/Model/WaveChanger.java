package com.mygdx.game.Model;

import com.mygdx.game.Control.PlayerController;
import com.mygdx.game.Control.WavePassedController;
import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.SaveData.ResourceManger;
import com.mygdx.game.View.GameScreen;
import com.mygdx.game.View.WaveEnds.GameCompeleteScreen;
import com.mygdx.game.View.WaveEnds.WavePassedScreen;

public class WaveChanger {
    private static int wave1KillNeeded = 20;
    private static int wave2KillNeeded = 40;
    private static int wave3KillNeeded = 60;
    public static int buildingCount = 1;
    public static int wallCount = 3;
    public static int kaleGhanaCount = 5;
    public static boolean canSpawnTank = false;
    public static boolean canSpawnMig = false;
    public static void waveChange(MaosVengeance game){
        switch (MaosVengeance.currentWave){
            case 1:
                if (MaosVengeance.killCount >= wave1KillNeeded || PlayerController.passWave){
                    buildingCount = 1;
                    wallCount = 7;
                    kaleGhanaCount = 10;
                    canSpawnTank = true;
                    canSpawnMig = false;
                    game.getScreen().dispose();
                    game.setScreen(new WavePassedScreen(game));
                    PlayerController.passWave = false;
                    if (MaosVengeance.user != null){
                        MaosVengeance.user.setScore(MaosVengeance.user.getScore() + MaosVengeance.killCount * 10 * MaosVengeance.hitCount);
                        ResourceManger.loadAndSave(GameAssetsManager.gameAssetsManager.saveDataPath);
                    }
                }

                break;
            case 2:
                if (MaosVengeance.killCount >= wave2KillNeeded || PlayerController.passWave){
                    buildingCount = 2;
                    wallCount = 10;
                    kaleGhanaCount = 15;
                    canSpawnTank = true;
                    canSpawnMig = true;
                    game.getScreen().dispose();
                    game.setScreen(new WavePassedScreen(game));
                    PlayerController.passWave = false;
                    if (MaosVengeance.user != null){
                        MaosVengeance.user.setScore(MaosVengeance.user.getScore() + MaosVengeance.killCount * 10 * MaosVengeance.hitCount);
                        ResourceManger.loadAndSave(GameAssetsManager.gameAssetsManager.saveDataPath);
                    }
                }
                break;
            case 3:
                if (MaosVengeance.killCount >= wave3KillNeeded || PlayerController.passWave){
                    buildingCount = 3;
                    wallCount = 15;
                    kaleGhanaCount = 20;
                    canSpawnTank = false;
                    canSpawnMig = false;
                    game.getScreen().dispose();
                    game.setScreen(new GameCompeleteScreen(game));
                    PlayerController.passWave = false;
                    if (MaosVengeance.user != null){
                        MaosVengeance.currentWave = 0;
                        MaosVengeance.user.setLastRound(1);
                        MaosVengeance.user.setScore(MaosVengeance.user.getScore() + MaosVengeance.killCount * 10 * MaosVengeance.hitCount);
                        ResourceManger.loadAndSave(GameAssetsManager.gameAssetsManager.saveDataPath);
                    }
                }
                break;
        }

    }
}
