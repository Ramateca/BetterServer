package com.wildmagicianshowroom.mc.betterserver.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatMorta implements Listener {

  private static final List<Player> listaMorti = new ArrayList<>();

  @EventHandler
  public void onChatEvent(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    if (listaMorti.contains(player)) {
      event.setCancelled(true);
      player.sendMessage("sceeeeemo");
    }
  }

  public static List<Player> getMorti() {
    return listaMorti;
  }

  public static boolean addMorto(Player player) {
    return listaMorti.add(player);
  }

  public static boolean removeMorto(Player player) {
    return listaMorti.remove(player);
  }
}
