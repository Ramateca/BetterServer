package com.wildmagicianshowroom.mc.betterserver.commands;

import com.wildmagicianshowroom.mc.betterserver.permissions.Permissions;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import static com.wildmagicianshowroom.mc.betterserver.utils.functions.SetPrimaryGroup.setPrimaryGroup;

public class Promote implements CommandExecutor {
  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (args.length != 2) return false;
    if (!(sender instanceof Player)) return false;
    if (!(sender.hasPermission(Permissions.PROMOTE))) return false;
    String gruopName = args[0];
    Player target = Bukkit.getPlayer(args[1]);
    if (target == null) {
      sender.sendMessage("§cQuesto player non esiste");
      return true;
    }
    RegisteredServiceProvider<LuckPerms> provider =
        Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    if (provider == null) {
      sender.sendMessage("§cLuckperms non risponde");
      return true;
    }
    LuckPerms luckPerms = provider.getProvider();
    Set<Group> loadedGroups = luckPerms.getGroupManager().getLoadedGroups();
    final List<String> groupList =
        loadedGroups.stream().map(Group::getDisplayName).collect(Collectors.toList());
    groupList.remove("Morto");
    if (!(groupList.contains(gruopName))) {
      sender.sendMessage("§cIl gruppo non esiste");
      return true;
    }
    User user = luckPerms.getPlayerAdapter(Player.class).getUser(target);
    Optional<Group> nextGroupOptional =
        luckPerms.getGroupManager().getLoadedGroups().stream()
            .filter(group -> Objects.equals(group.getDisplayName(), gruopName))
            .findFirst();
    if (!(nextGroupOptional.isPresent())) {
      sender.sendMessage("§cIl gruppo non esiste");
      return true;
    }
    Group nextGroup = nextGroupOptional.get();
    if (!setPrimaryGroup(target, nextGroup.getDisplayName())){
      sender.sendMessage("§cQualcosa è andato storto");
      return true;
    };
    return true;
  }
}
