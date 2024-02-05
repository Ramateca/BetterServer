package com.wildmagicianshowroom.mc.betterserver.tabcompletion;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PromoteCompletion implements TabCompleter {
  @Override
  public List<String> onTabComplete(
      CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 1) {
      RegisteredServiceProvider<LuckPerms> provider =
          Bukkit.getServicesManager().getRegistration(LuckPerms.class);
      if (provider != null) {
        LuckPerms luckPerms = provider.getProvider();
        Set<Group> loadedGroups = luckPerms.getGroupManager().getLoadedGroups();
        List<String> groupList =
            loadedGroups.stream().map(Group::getDisplayName).collect(Collectors.toList());
        return groupList;
      }
    }
    if (args.length == 2) {
      Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
      List<String> onlinePlayersList =
          onlinePlayers.stream().map(Player::getName).collect(Collectors.toList());
      List<String> offlinePlayersList =
          Arrays.stream(Bukkit.getOfflinePlayers())
              .map(OfflinePlayer::getName)
              .collect(Collectors.toList());
      return Stream.concat(onlinePlayersList.stream(), offlinePlayersList.stream())
          .collect(Collectors.toList());
    }

    return new ArrayList<>();
  }
}
