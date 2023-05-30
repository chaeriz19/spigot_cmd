package com.chaeriz.test1.events;

import com.chaeriz.test1.items.itemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class events implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.sendMessage(ChatColor.AQUA + "Welcome to the server");
    }
    @EventHandler
    public static void onRightClick(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() != null) {
                if (e.getItem().getItemMeta().equals(itemManager.wand.getItemMeta())) {
                    Player player = e.getPlayer();
                    player.getWorld().createExplosion(player.getLocation(), 5f);
                }
            }
        }
    }
}
