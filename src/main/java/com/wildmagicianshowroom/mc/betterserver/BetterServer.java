package com.wildmagicianshowroom.mc.betterserver;

import com.wildmagicianshowroom.mc.betterserver.commands.*;
import com.wildmagicianshowroom.mc.betterserver.events.*;
import com.wildmagicianshowroom.mc.betterserver.gui.TabList;
import com.wildmagicianshowroom.mc.betterserver.permissions.Permissions;
import com.wildmagicianshowroom.mc.betterserver.tabcompletion.*;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterServer extends JavaPlugin {

  private Boolean isEnabled;
  private List<Permission> permissionsList;

  @Override
  public void onEnable() {
    this.isEnabled = true;
    this.permissionsList = Permissions.permissionsList;

    for (Permission perm : this.permissionsList) {
      if (perm != null) Bukkit.getServer().getPluginManager().addPermission(perm);
    }

    getCommand("heal").setExecutor(new Heal());
    getCommand("survival").setExecutor(new Survival());
    getCommand("survival").setTabCompleter(new ModeChangeCompletion());
    getCommand("spectator").setExecutor(new Spectator());
    getCommand("spectator").setTabCompleter(new ModeChangeCompletion());
    getCommand("adventure").setExecutor(new Adventure());
    getCommand("adventure").setTabCompleter(new ModeChangeCompletion());
    getCommand("promote").setExecutor(new Promote());
    getCommand("promote").setTabCompleter(new PromoteCompletion());
    getCommand("reborn").setExecutor(new Reborn());
    Bukkit.getServer().getPluginManager().registerEvents(new TabList(), this);
    Bukkit.getServer().getPluginManager().registerEvents(new HardcoreLite(), this);
    Bukkit.getServer().getPluginManager().registerEvents(new ChatMorta(), this);
    Bukkit.getServer().getPluginManager().registerEvents(new PrigioneBanditi(), this);
  }

  @Override
  public void onDisable() {
    if (!this.isEnabled) return;
    for (Permission perm : this.permissionsList) {
      if (perm != null) {
        Bukkit.getServer().getPluginManager().removePermission(perm);
      }
    }
  }
}
