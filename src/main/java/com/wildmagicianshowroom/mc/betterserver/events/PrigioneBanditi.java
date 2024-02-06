package com.wildmagicianshowroom.mc.betterserver.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class PrigioneBanditi implements Listener {

    private static final List<Player> listaBanditi = new ArrayList<>();

    public static boolean addBandito(Player player) {
        return listaBanditi.add(player);
    }

    public static boolean removeBandito(Player player) {
        return listaBanditi.remove(player);
    }

}
