package com.mael.Clara;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class StarterItemManager {
        public static void giveStarterItems(Player player) {
            // Ajouter des items au joueur
            ItemStack item1 = createImmutableItem(Material.DIAMOND_SWORD, ChatColor.RED + "Épée puissante", "/commande1");
            ItemStack item2 = createImmutableItem(Material.BOW, ChatColor.GREEN + "Arc précis", "/commande2");

            player.getInventory().addItem(item1, item2);
        }

        private static ItemStack createImmutableItem(Material material, String displayName, String command) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(displayName);

            // Ajoutez la commande personnalisée comme lore (description)
            meta.setLore(Collections.singletonList(ChatColor.GRAY + "Cliquez pour exécuter : " + command));

            // Rendez l'item immobile
            item.setDurability(Short.MAX_VALUE);

            item.setItemMeta(meta);
            return item;
        }
    }
