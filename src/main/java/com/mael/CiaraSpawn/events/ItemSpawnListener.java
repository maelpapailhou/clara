package com.mael.CiaraSpawn.events;

import com.mael.CiaraSpawn.CiaraSpawn;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemSpawnListener implements Listener {
    private CiaraSpawn main; // Assurez-vous de remplacer "Main" par la classe principale de votre plugin

    public ItemSpawnListener(CiaraSpawn main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLocation = player.getLocation();

        FileConfiguration config = main.getConfig();
        double spawnX = config.getDouble("spawn-coordinates.x");
        double spawnZ = config.getDouble("spawn-coordinates.z");

        double maxDistance = 50.0; // La distance maximale à laquelle l'item doit apparaître

        double distanceX = Math.abs(playerLocation.getX() - spawnX);
        double distanceZ = Math.abs(playerLocation.getZ() - spawnZ);

        if (distanceX >= maxDistance || distanceZ >= maxDistance) {
            // Le joueur est trop éloigné du spawn, ajoutez l'item dans le slot 1 de l'inventaire

            ItemStack sunf = new ItemStack(Material.SUNFLOWER); // Remplacez "YOUR_ITEM_TYPE" par le type d'item que vous souhaitez
            ItemMeta sunfMeta = sunf.getItemMeta();
            sunfMeta.setDisplayName(ChatColor.YELLOW + "Retourner au spawn" + org.bukkit.ChatColor.GRAY + " (Appuyez)");
            sunf.setItemMeta(sunfMeta);

            player.getInventory().setItem(1, sunf);
        } else {
            // Le joueur est dans la zone, supprimez l'item du slot 1 de l'inventaire
            player.getInventory().setItem(1, null);
        }

    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand(); // Obtenir l'item tenu

        // Vérifier si l'item est une boussole
        if (item != null && item.getType() == Material.SUNFLOWER) {
            // Vérifier si le joueur a cliqué avec le bouton droit ou gauche
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK ||
                    event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                // Exécutez la commande associée ("/gameselection" dans cet exemple)
                String command = "spawn"; // Commande que vous souhaitez exécuter
                Bukkit.dispatchCommand(player, command);
            }
        }
    }
}