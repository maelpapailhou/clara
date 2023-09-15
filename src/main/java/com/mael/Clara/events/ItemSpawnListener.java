package com.mael.Clara.events;

import com.mael.Clara.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemSpawnListener implements Listener {
    private Main main; // Assurez-vous de remplacer "Main" par la classe principale de votre plugin

    public ItemSpawnListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();

        FileConfiguration config = main.getConfig();
        double spawnX = config.getDouble("spawn-coordinates.x");
        double spawnZ = config.getDouble("spawn-coordinates.z");

        double maxDistance = 100.0; // La distance maximale à laquelle l'item doit apparaître

        double distanceX = Math.abs(playerLocation.getX() - spawnX);
        double distanceZ = Math.abs(playerLocation.getZ() - spawnZ);

        if (distanceX >= maxDistance || distanceZ >= maxDistance) {
            // Le joueur est trop éloigné du spawn, ajoutez l'item dans le slot 1 de l'inventaire

            ItemStack item = new ItemStack(Material.SUNFLOWER); // Remplacez "YOUR_ITEM_TYPE" par le type d'item que vous souhaitez
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(ChatColor.YELLOW + "Retourner au spawn" + org.bukkit.ChatColor.GRAY + " (Appuyez)");
            item.setItemMeta(itemMeta);

            player.getInventory().setItem(1, item);
        } else {
            // Le joueur est dans la zone, supprimez l'item du slot 1 de l'inventaire
            player.getInventory().setItem(1, null);
        }
    }

}

