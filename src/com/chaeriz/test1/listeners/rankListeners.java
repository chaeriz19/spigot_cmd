package com.chaeriz.test1.listeners;

import com.chaeriz.test1.managers.nametagManager;
import com.chaeriz.test1.managers.rankManager;
import com.chaeriz.test1.storage.Rank;
import com.chaeriz.test1.test_one;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class rankListeners implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        test_one main = test_one.getMain();
        FileConfiguration config = main.getConfig();
        Player player = e.getPlayer();
        String uuid = player.getUniqueId().toString();
        if (!config.contains(uuid)) {
            rankManager.setRank(Rank.MEMBER, player);
        } else {
            rankManager.removePermissions(player);
            rankManager.setPermissions(player);
        }
        nametagManager.setNametag(player);
        nametagManager.newTag(player);
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        nametagManager.removeTag(e.getPlayer());
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String msg = e.getMessage();
        String username = player.getDisplayName();
        e.setCancelled(true);
        Bukkit.broadcastMessage(rankManager.getRank(player).getPrefix() + ChatColor.WHITE + " " + username + ": " + msg );
    }
}
