package com.wildmagicianshowroom.mc.betterserver.gamemodes;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;

public class CustomGameModeHandler {
  public static void setGameMode(CustomGameMode gameMode, Player player) {
    Optional<CustomGameModesPredicate> optionalHandler;
    optionalHandler =
        Arrays.stream(CustomGameModesPredicate.values())
            .filter(predicate -> predicate.getGameMode().equals(gameMode))
            .findFirst();
    if (!(optionalHandler.isPresent())) return;
    CustomGameModesPredicate handler = optionalHandler.get();
    handler.test(player);
  }
}
