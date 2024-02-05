package com.wildmagicianshowroom.mc.betterserver.commands;

import com.wildmagicianshowroom.mc.betterserver.permissions.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Adventure implements CommandExecutor {
  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      String[] args) {
    if (args.length > 1) {
      sender.sendMessage("§cToo many arguments");
      return false;
    }
    if (args.length == 0) {
      if (!(sender instanceof Player)) return false;
      if (!(sender.hasPermission(Permissions.ADVENTURE))) {
        sender.sendMessage("§0§cNon hai lo spirito di un avventuriero");
        return true;
      }
      if (((Player) sender).getGameMode() == GameMode.ADVENTURE) {
        sender.sendMessage("§a§cSei già un avventuriero");
        return true;
      }
      ((Player) sender).setGameMode(GameMode.ADVENTURE);
      sender.sendMessage("§aSei diventato un avventuriero");
      return true;
    }
    if (!(sender instanceof Player)) return false;
    if (!(sender.hasPermission(Permissions.MODE_CHANGE_OTHERS))) {
      sender.sendMessage("§0§cNon hai il potere di cambiare il cuore degli altri");
      return true;
    }
    Player player = Bukkit.getPlayer(args[0]);
    if (player == null) {
      sender.sendMessage("§cQuesto player non esiste");
      return true;
    }
    if (player.getGameMode() == GameMode.ADVENTURE) {
      sender.sendMessage("§0§cQuesto player è già un avventuriero");
      return true;
    }
    player.setGameMode(GameMode.ADVENTURE);
    player.sendMessage("§aSei diventato un avventuriero");
    sender.sendMessage(String.format("§aHai reso %s un avventuriero", player.getName()));
    return true;
  }
}
