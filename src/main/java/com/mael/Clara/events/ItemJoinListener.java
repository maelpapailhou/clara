package com.mael.Clara.events;

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

import java.util.Collections;

public class ItemJoinListener implements Listener {
    @EventHandler
    public void onJoinItem(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName(ChatColor.AQUA + "Jouer" + ChatColor.GRAY + " (Appuyez)");
        compassMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Ouvrez le menu de jeux"));
        compassMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        compass.setItemMeta(compassMeta);
        player.getInventory().setItem(0, compass);

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) playerHead.getItemMeta();
        headMeta.setOwningPlayer(player);
        headMeta.setDisplayName(ChatColor.AQUA + player.getName() + ChatColor.GRAY + " (Appuyez)");
        playerHead.setItemMeta(headMeta);
        player.getInventory().setItem(4, playerHead);


    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand(); // Obtenir l'item tenu

        // Vérifier si l'item est une boussole
        if (item != null && item.getType() == Material.COMPASS) {
            // Vérifier si le joueur a cliqué avec le bouton droit
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                // Exécutez la commande associée (dans cet exemple, "/spawn")
                String command = "/spawn"; // Changez cette commande en celle que vous souhaitez exécuter
                Bukkit.dispatchCommand(player, command);
            }
        }
    }
    }

