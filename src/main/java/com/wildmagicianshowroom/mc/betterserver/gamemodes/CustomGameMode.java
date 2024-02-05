package com.wildmagicianshowroom.mc.betterserver.gamemodes;

import org.bukkit.GameMode;

public enum CustomGameMode {
    MORTO(GameMode.SPECTATOR);
    private final GameMode gameMode;

    CustomGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }


}