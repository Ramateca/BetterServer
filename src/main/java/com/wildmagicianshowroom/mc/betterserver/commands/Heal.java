package com.wildmagicianshowroom.mc.betterserver.commands;

import com.wildmagicianshowroom.mc.betterserver.permissions.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {
  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (args.length != 0) {
      sender.sendMessage("§cToo many arguments");
      return true;
    }
    if (!(sender instanceof Player)) return true;
    if (!(sender.hasPermission(Permissions.HEAL))) {
      sender.sendMessage("§0§cNon hai la conoscenza per curarti");
      return true;
    }
    if (((Player) sender).hasPotionEffect(PotionEffectType.REGENERATION)) {
      ((Player) sender).removePotionEffect(PotionEffectType.REGENERATION);
      sender.sendMessage("§l§aNon sei più una donna");
      return true;
    }
    if (((Player) sender)
        .addPotionEffect(
            new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 99, false, false))) {
      sender.sendMessage("§l§aOra sei donna");
      return true;
    }
    return false;
  }
}
