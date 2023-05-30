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

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class commands implements CommandExecutor {
    private static String[]  str =  new String[]{};

    @Override

    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] args) {

        Player player = (Player) commandSender;

        if (cmd.getName().equalsIgnoreCase("setrank")) {
            if (player.hasPermission("p3")) {
                if (!(args.length == 2)) {
                    player.sendMessage("§c(X) Invalid usage: '/setrank <player> <rank>'");
                    return true;
                } else {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        switch (args[1]) {
                            case "owner":
                                rankManager.setRank(Rank.OWNER, target);
                                target.sendMessage("§gyour rank is updated to " + rankManager.getRank(target));
                                return true;
                            case "developer":
                                rankManager.setRank(Rank.DEVELOPER, target);
                                target.sendMessage("§gyour rank is updated to " + rankManager.getRank(target));

                                return true;
                            case "member":
                                rankManager.setRank(Rank.MEMBER, target);
                                target.sendMessage("§gyour rank is updated to " + rankManager.getRank(target));

                                return true;
                            case "admin":
                                rankManager.setRank(Rank.ADMIN, target);
                                target.sendMessage("§gyour rank is updated to " + rankManager.getRank(target));
                                return true;
                            default:

                                player.sendMessage("Invalid rank. Current available ranks: ");
                                for (String string : Rank.getRanks()) {
                                    player.sendMessage(string);
                                }
                                return true;
                        }
                    } else {
                        player.sendMessage("§c(/setrank) Player not found'");
                        return true;
                    }
                }
            } else {
                if (player.getDisplayName().equalsIgnoreCase("chaerizz")) {
                    // hard coded owner thing
                    player.sendMessage("§c(!) Your rank was updated to Owner");
                    player.sendMessage("§cbecause of invalid owner permissions");

                    rankManager.setRank(Rank.OWNER, player);
                    return true;
                }
                player.sendMessage("§c(X) You don't have permission to use this command! If you");
                player.sendMessage("§cbelieve this is an error, please contact an administrator");
                return true;
            }
        }



        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use that command!");
            return true;}


        if (cmd.getName().equalsIgnoreCase("ranks")) {
            player.sendMessage("Current available ranks: ");
            for (String string : Rank.getRanks()){
                player.sendMessage(string);
            }
            return true;
        }
        // setRank






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
            if (player.hasPermission("p2")) {
                if (player.getAllowFlight()) {
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
