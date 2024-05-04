package com.mygdx.game.Model.SaveData;

public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String avatar;
    private int lastRound;
    private int score;
    private int killCount;
    private int accuracy;
    private int preferredDifficulty;

    public User(String username, String password, String avatar, int lastRound, int score, int killCount, int accuracy, int preferredDifficulty) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.lastRound = lastRound;
        this.score = score;
        this.killCount = killCount;
        this.accuracy = accuracy;
        this.preferredDifficulty = preferredDifficulty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLastRound() {
        return lastRound;
    }

    public void setLastRound(int lastRound) {
        this.lastRound = lastRound;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getPreferredDifficulty() {
        return preferredDifficulty;
    }

    public void setPreferredDifficulty(int preferredDifficulty) {
        this.preferredDifficulty = preferredDifficulty;
    }
}
