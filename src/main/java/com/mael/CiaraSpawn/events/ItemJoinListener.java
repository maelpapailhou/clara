package com.mael.CiaraSpawn.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class ItemJoinListener implements Listener {

    private final JavaPlugin plugin;

    public ItemJoinListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinItem(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        for (String itemKey : plugin.getConfig().getConfigurationSection("join-items").getKeys(false)) {
            String itemName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-items." + itemKey + ".name"));
            List<String> lore = plugin.getConfig().getStringList("join-items." + itemKey + ".lore");
            int slot = plugin.getConfig().getInt("join-items." + itemKey + ".slot");

            ItemStack itemStack;

            if (itemKey.equals("item2")) {
                itemStack = createPlayerHead(player, itemName, lore);
            } else {
                Material material = Material.getMaterial(plugin.getConfig().getString("join-items." + itemKey + ".material"));

                if (material == null) {
                    // Le type de matériau spécifié dans la configuration n'est pas valide.
                    // Vous pouvez ajouter une gestion d'erreur ici si nécessaire.
                    continue;
                }

                itemStack = createItem(material, itemName, lore);
            }

            player.getInventory().setItem(slot, itemStack);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null) {
            String itemName = ChatColor.stripColor(item.getItemMeta().getDisplayName());

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                String command = plugin.getConfig().getString("join-items." + itemName + ".right_click_command");
                if (command != null && !command.isEmpty()) {
                    Bukkit.dispatchCommand(player, command);
                }
            } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                String command = plugin.getConfig().getString("join-items." + itemName + ".left_click_command");
                if (command != null && !command.isEmpty()) {
                    Bukkit.dispatchCommand(player, command);
                }
            }
        }
    }

    private ItemStack createItem(Material material, String itemName, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(itemMeta);
        return item;
    }

    private ItemStack createPlayerHead(Player player, String itemName, List<String> lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setDisplayName(itemName);
        skullMeta.setLore(lore);
        skullMeta.setOwningPlayer(player);
        item.setItemMeta(skullMeta);
        return item;
    }
}
