package com.wildmagicianshowroom.mc.betterserver;

import com.wildmagicianshowroom.mc.betterserver.commands.Lobby;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class BetterServerBC extends Plugin {

  @Override
  public void onEnable() {
    ProxyServer.getInstance().getPluginManager().registerCommand(this, new Lobby());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
