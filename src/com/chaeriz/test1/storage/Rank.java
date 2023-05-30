package com.chaeriz.test1.storage;

import org.bukkit.ChatColor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public enum Rank {

    OWNER(ChatColor.RED + "OWNER", new String[]{"p0","p1", "p2", "p3"}),
    DEVELOPER(ChatColor.AQUA + "DEVELOPER", new String[]{"p0","p1", "p2"}),
    ADMIN(ChatColor.GREEN + "ADMIN", new String[]{"p0","p1", "p2"}),

    MEMBER(ChatColor.GRAY + "MEMBER", new String[]{"p0"});

    private String prefix;
    private String[] permissions;
    Rank(String prefix, String[] permissions) {
        this.prefix = prefix;
        this.permissions = permissions;
    }
    public String getPrefix(){
        return prefix;
    }
    public static ArrayList<String> getRanks(){
        ArrayList<String> ar = new ArrayList<>();
        for (Rank ranks : Rank.values()) {
            ar.add(ranks.toString());
        }
        return ar;
    }
    public String[] getPermissions(){
        return permissions;
    }
}
