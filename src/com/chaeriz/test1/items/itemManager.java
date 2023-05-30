package com.chaeriz.test1.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class itemManager {
    public static ItemStack wand;
    public static void init() {
        create_wand();
    }
    private static void create_wand() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Wand");
        List<String> lore = new ArrayList<>();
        lore.add("§7 This is a fucking crazy");
        lore.add("§7 magic stick or whatever.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.KNOCKBACK, 50, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wand = item;
    }

}
