package com.wildmagicianshowroom.mc.betterserver.events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Furnace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class PrigioneBanditi implements Listener {
  private static final List<Player> listaBanditi = new ArrayList<>();

  public static boolean addBandito(Player player) {
    return listaBanditi.add(player);
  }

  public static boolean removeBandito(Player player) {
    return listaBanditi.remove(player);
  }

  @EventHandler
  public void onChestOpen(InventoryOpenEvent event) {
    InventoryHolder holder = event.getInventory().getHolder();
    boolean isChest = holder instanceof Chest;
    boolean isDoubleChest = holder instanceof DoubleChest;
    boolean isFurnace = holder instanceof Furnace;
    boolean isBarrel = holder instanceof Barrel;
    boolean isBandito = listaBanditi.contains((Player) event.getPlayer());
    boolean isBlockProhibited = isChest || isDoubleChest || isFurnace || isBarrel;
    if (isBlockProhibited && isBandito) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onCreeperExplode(EntityExplodeEvent event) {
    boolean isCreeper = event.getEntityType().equals(EntityType.CREEPER);
    List<Entity> nearbyEntities = event.getEntity().getNearbyEntities(5, 5, 5);
    List<Entity> eventualPlayerList =
        nearbyEntities.stream()
            .filter(entity -> entity instanceof Player)
            .collect(Collectors.toList());
    boolean isBandito = listaBanditi.stream().anyMatch(new HashSet<>(eventualPlayerList)::contains);
    if (isCreeper && isBandito) event.setCancelled(true);
  }

  @EventHandler
  public void onFlintAndSteelUse(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    ItemStack flintAndSteel = new ItemStack(Material.FLINT_AND_STEEL);
    ItemStack mainHand = player.getInventory().getItemInMainHand();
    ItemStack offHand = player.getInventory().getItemInOffHand();
    boolean hasFlintAndSteelInHand = mainHand.isSimilar(flintAndSteel) || offHand.isSimilar(flintAndSteel);
    boolean isBandito = listaBanditi.contains(player);
    if (hasFlintAndSteelInHand && isBandito) event.setCancelled(true);
  }

  @EventHandler
  public void onUseFlameBow(EntityShootBowEvent event) {
    ItemStack bow = event.getBow();
    if ( bow == null ) return;
    boolean hasFlame = bow.containsEnchantment(Enchantment.ARROW_FIRE);
    if (!(event.getEntity() instanceof Player)) return;
    Player player = ((Player) event.getEntity()).getPlayer();
    boolean isBandito = listaBanditi.contains(player);
    if (hasFlame && isBandito) event.setCancelled(true);
  }
}
