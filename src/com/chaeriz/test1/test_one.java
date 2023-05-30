package com.chaeriz.test1;

import com.chaeriz.test1.commands.commands;
import com.chaeriz.test1.events.events;
import com.chaeriz.test1.items.itemManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class test_one extends JavaPlugin {
    @Override
    public void onEnable() {
        itemManager.init();
        commands commands = new commands();
        getServer().getPluginManager().registerEvents(new events(), this );
        getCommand("heal").setExecutor(commands);
        getCommand("feed").setExecutor(commands);
        getCommand("wand").setExecutor(commands);
        getServer().broadcast("lol", "broadcast idk");
        getCommand("boom").setExecutor(commands);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "test_one is enabled");
    }
    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "test_one is disabled");
    }

}
