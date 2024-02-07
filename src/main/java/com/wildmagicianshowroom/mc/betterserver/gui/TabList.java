package com.wildmagicianshowroom.mc.betterserver.gui;

import com.wildmagicianshowroom.mc.betterserver.utils.enums.PlayerListColors;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

public class TabList implements Listener {
  @EventHandler
  public void onPlayerEnter(PlayerLoginEvent event) {
    Player player = event.getPlayer();
    RegisteredServiceProvider<LuckPerms> provider =
        Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    if (provider == null) return;
    LuckPerms luckPerms = provider.getProvider();
    User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
    String groupName =
        luckPerms.getGroupManager().getGroup(user.getPrimaryGroup()).getDisplayName();
    if (groupName == null) groupName = user.getPrimaryGroup();
    player.setPlayerListHeader("PSAIDPAIDOJWEFPJWEOPFJW");
    player.setPlayerListName(
        String.format(
            "%s%s%s %s",
            PlayerListColors.getColor(groupName), groupName, ChatColor.WHITE, player.getName()));
    player.setDisplayName(
        String.format(
            "%s%s%s %s",
            PlayerListColors.getColor(groupName), groupName, ChatColor.WHITE, player.getName()));
  }

  @EventHandler
  public void onChatEvent(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    RegisteredServiceProvider<LuckPerms> provider =
        Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    if (provider == null) return;
    LuckPerms luckPerms = provider.getProvider();
    User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
    String groupName =
        luckPerms.getGroupManager().getGroup(user.getPrimaryGroup()).getDisplayName();
    if (groupName == null) groupName = user.getPrimaryGroup();
    event.setFormat(
        String.format(
            "%s%s%s %s: %s",
            PlayerListColors.getColor(groupName), groupName, ChatColor.WHITE, player.getName(), event.getMessage()));
  }
}
