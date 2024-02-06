package com.wildmagicianshowroom.mc.betterserver.gamemodes;

import com.wildmagicianshowroom.mc.betterserver.events.ChatMorta;
import com.wildmagicianshowroom.mc.betterserver.events.PrigioneBanditi;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum CustomGameModesPredicate {
    MORTE(CustomGameMode.MORTO, (customGameMode, player) -> {
        player.setGameMode(customGameMode.getGameMode());
        ChatMorta.addMorto(player);
        player.sendMessage(ChatMorta.getMorti().toString());
        return true;
    }),
    BANDITISMO(CustomGameMode.BANDITO, (customGameMode, player) -> {
        player.setGameMode(customGameMode.getGameMode());
        PrigioneBanditi.addBandito(player);
        return true;
    });
    private final CustomGameMode gameMode;
    private final BiPredicate<CustomGameMode, Player> predicate;

    CustomGameModesPredicate(CustomGameMode gameMode, BiPredicate<CustomGameMode, Player> predicate) {
        this.gameMode = gameMode;
        this.predicate = predicate;
    }

    public CustomGameMode getGameMode() {
        return gameMode;
    }

    public boolean test(Player player) {
        return predicate.test(gameMode, player);
    }
}
