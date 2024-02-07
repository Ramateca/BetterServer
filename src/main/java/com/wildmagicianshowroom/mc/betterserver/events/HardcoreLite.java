package com.wildmagicianshowroom.mc.betterserver.events;

import com.wildmagicianshowroom.mc.betterserver.gamemodes.CustomGameMode;
import com.wildmagicianshowroom.mc.betterserver.gamemodes.CustomGameModeHandler;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HardcoreLite implements Listener {

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();
    AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    if (maxHealth == null) {
      player.sendMessage("§c§0Una trama non ancora risolta ti lega a questo mondo");
      return;
    }
    double value = maxHealth.getValue();
    if (value > 2) {
      maxHealth.setBaseValue(value - 2);
      player.sendMessage("§c§Hai perso vigore");
      return;
    }
    if (value != 0) {
      maxHealth.setBaseValue(0.0D);
      CustomGameModeHandler.setGameMode(CustomGameMode.MORTO, player);
    }
  }
}
