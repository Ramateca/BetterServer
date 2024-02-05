package com.wildmagicianshowroom.mc.betterserver.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.w3c.dom.events.EventTarget;
import org.bukkit.*;

import java.lang.annotation.Annotation;

public class HarcoreLite implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player player=event.getEntity();
        AttributeInstance maxHealt= player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if(maxHealt== null){
            player.sendMessage("§c§0Una trama non ancora risolta ti lega a questo mondo");
            return;
        }

        double value = maxHealt.getValue();
        if(value>2){
            maxHealt.setBaseValue(value-2);
            player.sendMessage("§c§Hai perso vigore");
            return;
        }
        player.setGameMode(GameMode.SPECTATOR);


        return;
    }
}
