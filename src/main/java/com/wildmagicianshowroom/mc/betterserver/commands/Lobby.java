package com.wildmagicianshowroom.mc.betterserver.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Lobby extends Command {

  public Lobby() {
    super("lobby");
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    if (args.length > 0) {
      sender.sendMessage(new TextComponent("§cToo many arguments"));
      return;
    }
    if (!(sender instanceof ProxiedPlayer)) return;
    ((ProxiedPlayer) sender).connect(ProxyServer.getInstance().getServerInfo("lobby"));
  }
}
