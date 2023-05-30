package com.chaeriz.test1.commands;

import com.chaeriz.test1.items.itemManager;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use that command!");
            return true;}
        Player player = (Player) commandSender;

        // HEAL
        if (cmd.getName().equalsIgnoreCase("heal")) {
            // heal
            double MAX_HEALTH = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
            player.setHealth(MAX_HEALTH);
            player.sendMessage("§a(!) Successfully restored health");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("wand")) {
            player.getInventory().addItem(itemManager.wand);
        }

        // FEED
        if (cmd.getName().equalsIgnoreCase("feed")) {
            // feed
            player.setFoodLevel(20);
            player.sendMessage("§a(!) Successfully restored saturation");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("boom")) {
            if (args.length >= 2) {
                getServer().getConsoleSender().sendMessage(ChatColor.RED + "" + args.length);
                try {
                    EntityType entity = EntityType.valueOf(args[0].toUpperCase());
                    int amount = Integer.parseInt(args[1]);
                    for (int i = 0; i < amount; i++) {
                        player.getWorld().spawnEntity(player.getLocation(), entity);
                    }
                    player.sendMessage("§a(!) Spawned successfully");
                } catch (IllegalArgumentException e) {
                    player.sendMessage("§c(X) Invalid usage: '/boom <mob> <amount>' 1");
                }
            } else {
                player.sendMessage("§c(X) Invalid usage: '/boom <mob> <amount>' 2"); return true;
            }
            return true;
        }
        return true;
    }
}
