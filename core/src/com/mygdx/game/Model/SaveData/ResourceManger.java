package com.mygdx.game.Model.SaveData;

import com.mygdx.game.MaosVengeance;
import com.mygdx.game.Model.GameAssetsManager;
import com.mygdx.game.View.GameScreen;
import com.mygdx.game.View.MainMenu;

import java.io.*;
import java.util.ArrayList;

import static com.mygdx.game.Model.Player.player;

public class ResourceManger {
    public static void save(Serializable data , String path) throws Exception{
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Object load(String path) throws Exception{
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object data = in.readObject();
            in.close();
            fileIn.close();
            return data;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    public static void loadAndSave(String path) {
        ArrayList<User> users;
        try {
            users = (ArrayList<User>) ResourceManger.load(path);
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }

        for (User user : users) {
            if (user.getUsername().equals(MaosVengeance.user.getUsername())) {
                users.remove(user);
                break;
            }
        }

        users.add(MaosVengeance.user);

        try {
            ResourceManger.save(users , path);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveGameProgress(int waveEnded){
        MaosVengeance.user.setKillCount(MaosVengeance.user.getKillCount() + MaosVengeance.killCount);
        if (MaosVengeance.firedCount != 0){
            float accuracy = (float) MaosVengeance.hitCount / (float) MaosVengeance.firedCount * 100;
            MaosVengeance.user.setAccuracy((int) Math.floor(accuracy));
        }
        else {
            MaosVengeance.user.setAccuracy(0);
        }
        MaosVengeance.user.setLastRound(waveEnded);
        ResourceManger.loadAndSave(GameAssetsManager.gameAssetsManager.saveDataPath);
    }
}
