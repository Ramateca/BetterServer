package com.wildmagicianshowroom.mc.betterserver.gamemodes;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;

public class CustomGameModeHandler {
  public static boolean setGameMode(CustomGameMode gameMode, Player player) {
    Optional<CustomGameModesPredicate> optionalHandler;
    optionalHandler =
        Arrays.stream(CustomGameModesPredicate.values())
            .filter(predicate -> predicate.getGameMode().equals(gameMode))
            .findFirst();
    if (!(optionalHandler.isPresent())) return false;
    CustomGameModesPredicate handler = optionalHandler.get();
    return handler.test(player);
  }
}
