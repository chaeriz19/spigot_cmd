package com.chaeriz.test1.commands;

import com.chaeriz.test1.items.itemManager;
import com.chaeriz.test1.managers.rankManager;
import com.chaeriz.test1.storage.Rank;
import org.bukkit.Bukkit;
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


        // setRank

        if (cmd.getName().equalsIgnoreCase("setrank")) {
            if (!(args.length == 2)) {
                player.sendMessage("§c(X) Invalid usage: '/setrank <player> <rank>'"); return true;
            } else {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player target = Bukkit.getPlayer(args[0]);
                    switch (args[1]) {
                        case "owner":
                            rankManager.setRank(Rank.OWNER, target);
                            return true;
                        case "developer":
                            rankManager.setRank(Rank.DEVELOPER, target);
                            return true;
                        case "member":
                            rankManager.setRank(Rank.MEMBER, target);
                            return true;
                        default:

                            player.sendMessage("§c(X) Please select a valid rank");
                            player.sendMessage("§cavailable ranks: owner, developer, member");

                            return true;
                    }
                } else {
                    player.sendMessage("§c(/setrank) Player not found'"); return true;
                }
            }


        }


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

        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (player.hasPermission("fly.cmd")) {
                if (player.getAllowFlight() == true) {
                    player.setAllowFlight(false);
                    player.sendMessage("§c(X) Flight off");
                } else {
                    player.setAllowFlight(true);
                    player.sendMessage("§a(!) Flight on");
                }
            } else {

                player.sendMessage("§c(X) You don't have permission to use this command! If you");
                player.sendMessage("§cbelieve this is an error, please contact an administrator");

            }
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
                    if (amount < 100) {
                        for (int i = 0; i < amount; i++) {
                            player.getWorld().spawnEntity(player.getLocation(), entity);
                        }

                        player.sendMessage("§a(!) Spawned successfully");
                    } else {
                        player.sendMessage("§c(X) Invalid usage: You're trying to crash");
                        player.sendMessage("§cthe server or something? Jesus.");

                    }


                } catch (IllegalArgumentException e) {
                    player.sendMessage("§c(X) Invalid usage: '/boom <mob> <amount>'");
                }
            } else {
                player.sendMessage("§c(X) Invalid usage: '/boom <mob> <amount>'"); return true;
            }
            return true;
        }
        return true;
    }
}
