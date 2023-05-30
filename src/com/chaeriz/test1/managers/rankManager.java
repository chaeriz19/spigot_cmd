package com.chaeriz.test1.managers;

import com.chaeriz.test1.storage.Rank;
import com.chaeriz.test1.test_one;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

public class rankManager {

    public static void setRank (Rank rank, Player player) {
        test_one main = test_one.getMain();
        FileConfiguration config = main.getConfig();

        String uuid = player.getUniqueId().toString();
        config.set(uuid, rank.name());
        main.saveConfig();

        if (config.contains(uuid)) {
            removePermissions(player);
        }
        setPermissions(player);

        nametagManager.setNametag(player);
        nametagManager.newTag(player);
    }
    public static void setPermissions(Player player){
        test_one main = test_one.getMain();
        FileConfiguration config = main.getConfig();
        UUID uuid = player.getUniqueId();
        Rank rank = getRank(player);

        PermissionAttachment attachment = player.addAttachment(main);
        test_one.getPerms().put(uuid, attachment);
        for (String perm : rank.getPermissions()) {
            attachment.setPermission(perm, true);
        }
    }
    public static void removePermissions(Player player){
        test_one main = test_one.getMain();
        FileConfiguration config = main.getConfig();
        UUID uuid = player.getUniqueId();
        Rank rank = getRank(player);

        for (String perm : rank.getPermissions()) {
            test_one.getPerms().get(uuid).unsetPermission(perm);
        }
    }

    public static Rank getRank(Player player) {
        test_one main = test_one.getMain();
        FileConfiguration config = main.getConfig();
        return Rank.valueOf(config.getString(player.getUniqueId().toString()));
    }

}
