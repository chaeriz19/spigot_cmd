package com.chaeriz.test1.storage;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.RED + "OWNER", new String[]{"fly.cmd', 'heal.cmd', 'heal.cmd"}),
    DEVELOPER(ChatColor.AQUA + "DEVELOPER", new String[]{"fly.cmd', 'heal.cmd', 'heal.cmd"}),
    MEMBER(ChatColor.GRAY + "MEMBER", new String[]{""});

    private String prefix;
    private String[] permissions;
    Rank(String prefix, String[] permissions) {
        this.prefix = prefix;
        this.permissions = permissions;
    }
    public String getPrefix(){
        return prefix;
    }
    public String[] getPermissions(){
        return permissions;
    }
}
