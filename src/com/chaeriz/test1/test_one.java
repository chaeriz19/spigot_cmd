package com.chaeriz.test1;

import com.chaeriz.test1.commands.commands;
import com.chaeriz.test1.events.events;
import com.chaeriz.test1.items.itemManager;
import com.chaeriz.test1.listeners.rankListeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ObjectInputFilter;
import java.util.HashMap;
import java.util.UUID;

public class test_one extends JavaPlugin {

    private static HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
    private static test_one main;

    @Override
    public void onEnable() {
        main = this;
        itemManager.init();
        commands commands = new commands();
        getServer().getPluginManager().registerEvents(new events(), this );
        getServer().getPluginManager().registerEvents(new rankListeners(), this);
        getCommand("fly").setExecutor(commands);
        getCommand("ranks").setExecutor(commands);
        getCommand("heal").setExecutor(commands);
        getCommand("feed").setExecutor(commands);
        getCommand("wand").setExecutor(commands);
        getCommand("boom").setExecutor(commands);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "test_one is enabled");
        getCommand("setrank").setExecutor(commands);
        // ranks

        getConfig().options().copyDefaults( true);
        saveConfig();

    }
    @Override
    public void onDisable(){

        getServer().getConsoleSender().sendMessage(ChatColor.RED + "test_one is disabled");
        perms.clear();
    }
    public static test_one getMain(){
        return main;
    }
    public static HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }
}
