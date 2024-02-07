package com.wildmagicianshowroom.mc.betterserver.utils.enums;

import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;
import java.util.Optional;

public enum PlayerListColors {
  NOBILE(ChatColor.DARK_RED, ChatColor.BOLD, "Nobile"),
  DONNA(ChatColor.LIGHT_PURPLE, ChatColor.BOLD, "Donna"),
  BANDITO(ChatColor.GREEN, null, "Bandito"),
  POPOLANO(ChatColor.GOLD, null, "Popolano"),
  MORTO(ChatColor.DARK_GRAY, null, "Morto");

  private final ChatColor chatColor;
  private final ChatColor modifier;
  private final String groupDisplayName;

  PlayerListColors(ChatColor chatColor, ChatColor modifier, String groupDisplayName) {
    this.chatColor = chatColor;
    this.modifier = modifier;
    this.groupDisplayName = groupDisplayName;
  }

  public static PlayerListColors getColor(String groupDisplayName) {
    Optional<PlayerListColors> first =
        Arrays.stream(PlayerListColors.values())
            .filter(playerListColors -> playerListColors.groupDisplayName.equals(groupDisplayName))
            .findFirst();
      return first.orElse(null);
  }

  @Override
  public String toString() {
    return chatColor.toString() + modifier.toString();
  }
}
