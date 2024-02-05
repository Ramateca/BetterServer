package com.wildmagicianshowroom.mc.betterserver.permissions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
  public static final Permission PROMOTE =
      new Permission("betterserver.promote", PermissionDefault.OP);
  public static final Permission MODE_CHANGE_OTHERS =
      new Permission("betterserver.modechange.others", PermissionDefault.OP);
  private static final PermissionDefault permissionDefault = PermissionDefault.FALSE;
  public static final Permission HEAL = new Permission("betterserver.heal", permissionDefault);
  public static final Permission MODE_CHANGE =
      parentPermission(
          "betterserver.modechange.*", Arrays.asList("survival", "adventure", "spectator"));
  public static final Permission SURVIVAL =
      new Permission("betterserver.modechange.survival", permissionDefault);
  public static final Permission ADVENTURE =
      new Permission("betterserver.modechange.adventure", permissionDefault);
  public static final Permission SPECTATOR =
      new Permission("betterserver.modechange.spectator", permissionDefault);
  public static final List<Permission> permissionsList =
      Arrays.asList(HEAL, MODE_CHANGE, SURVIVAL, ADVENTURE, SPECTATOR, PROMOTE, MODE_CHANGE_OTHERS);

  private static Permission parentPermission(String parent, List<String> children) {
    Map<String, Boolean> childrenMap =
        children.stream()
            .collect(Collectors.toMap(s -> parent.replace("*", "").concat(s), s -> false));
    return new Permission(parent, permissionDefault, childrenMap);
  }
}
