package com.wildmagicianshowroom.mc.betterserver.commands;

import com.wildmagicianshowroom.mc.betterserver.events.ChatMorta;
import com.wildmagicianshowroom.mc.betterserver.permissions.Permissions;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Reborn implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1) return true;
        if (!(sender instanceof Player)) return false;
        if (!(sender.hasPermission(Permissions.REBORN))) return false;
        AttributeInstance maxHealth = ((Player) sender).getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth == null) {
            sender.sendMessage("§c§0Una trama non ancora risolta ti lega a questo mondo");
            return true;
        }
        maxHealth.setBaseValue(20.0D);
        ChatMorta.removeMorto(((Player) sender).getPlayer());
        return true;
    }
}
