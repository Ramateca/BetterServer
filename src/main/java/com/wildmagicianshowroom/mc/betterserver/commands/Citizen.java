package com.wildmagicianshowroom.mc.betterserver.commands;

import com.wildmagicianshowroom.mc.betterserver.gamemodes.CustomGameMode;
import com.wildmagicianshowroom.mc.betterserver.gamemodes.CustomGameModeHandler;
import com.wildmagicianshowroom.mc.betterserver.permissions.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Citizen implements CommandExecutor {

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args) {
        if (args.length > 1) {
            sender.sendMessage("§cToo many arguments");
            return false;
        }
        if (args.length == 0) {
            if (!(sender instanceof Player)) return false;
            if (!(sender.hasPermission(Permissions.CITIZEN))) {
                sender.sendMessage("§0§cLa tua anima non può lasciare il corpo");
                return true;
            }
            if (((Player) sender).getGameMode() == GameMode.ADVENTURE) {
                sender.sendMessage("§a§cSei già un for real");
                return true;
            }
            CustomGameModeHandler.setGameMode(CustomGameMode.MORTO, (Player)sender);
            sender.sendMessage("§aSei diventato un for realers");
            return true;
        }
        if (!(sender instanceof Player)) return false;
        if (!(sender.hasPermission(Permissions.MODE_CHANGE_OTHERS))) {
            sender.sendMessage("§0§cNon hai il potere di decidere il destino degli altri");
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("§cQuesto player non esiste");
            return true;
        }
        if (player.getGameMode() == GameMode.SURVIVAL) {
            sender.sendMessage("§0§cQuesto player è già un for real");
            return true;
        }
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage("§aSei diventato un for realer");
        sender.sendMessage(String.format("§aHai reso %s un for realer", player.getName()));
        return true;
    }
}
