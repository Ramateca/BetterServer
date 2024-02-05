package com.wildmagicianshowroom.mc.betterserver.tabcompletion;

import com.wildmagicianshowroom.mc.betterserver.permissions.Permissions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModeChangeCompletion implements TabCompleter {
  @Nullable
  @Override
  public List<String> onTabComplete(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (args.length == 1 && sender.hasPermission(Permissions.MODE_CHANGE_OTHERS)) {
      Collection<? extends Player> players = Bukkit.getOnlinePlayers();
      return players.stream().map(Player::getName).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }
}
