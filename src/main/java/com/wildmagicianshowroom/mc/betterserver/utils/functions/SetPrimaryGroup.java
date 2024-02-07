package com.wildmagicianshowroom.mc.betterserver.utils.functions;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.ScopedNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class SetPrimaryGroup {
    public static boolean setPrimaryGroup(Player player, String groupDisplayName) {
        AtomicBoolean result = new AtomicBoolean(false);
        RegisteredServiceProvider<LuckPerms> provider =
                Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider == null) return result.get();
        LuckPerms luckPerms = provider.getProvider();
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        Optional<Group> nextGroupOptional =
                luckPerms.getGroupManager().getLoadedGroups().stream()
                        .filter(group -> Objects.equals(group.getDisplayName(), groupDisplayName))
                        .findFirst();
        if (!(nextGroupOptional.isPresent())) return result.get();
        Group nextGroup = nextGroupOptional.get();
        luckPerms
                .getUserManager()
                .modifyUser(
                        user.getUniqueId(),
                        u -> {
                            Optional<Node> optionalNode =
                                    u.getNodes().stream()
                                            .filter(node -> node.getKey().contains("group.".concat(u.getPrimaryGroup())))
                                            .findFirst();
                            if (!(optionalNode.isPresent())) return;
                            DataMutateResult remove = u.data().remove(optionalNode.get());
                            System.out.println(optionalNode.get().getKey());
                            ScopedNode<?, ?> build =
                                    Node.builder("group.".concat(nextGroup.getName())).value(true).build();
                            System.out.println(build.getKey());
                            DataMutateResult add = u.data().add(build);
                            if (remove.wasSuccessful() && add.wasSuccessful()) result.set(true);
                        });
        return result.get();
    }
}
