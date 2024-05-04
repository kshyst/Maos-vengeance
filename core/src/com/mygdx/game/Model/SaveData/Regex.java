package com.mygdx.game.Model.SaveData;

public enum Regex {
    USERNAME("^[a-zA-Z0-9]{3,20}$"),
    PASSWORD("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"),
    AVATAR("^[a-zA-Z0-9]{3,20}$"),
    LASTROUND("^[0-9]{1,5}$"),
    SCORE("^[0-9]{1,5}$"),
    KILLCOUNT("^[0-9]{1,5}$"),
    ACCURACY("^[0-9]{1,5}$"),
    PREFERREDDIFFICULTY("^[0-9]{1,5}$");

    private final String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
