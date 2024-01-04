package com.mael.CiaraSpawn.events;

import com.mael.CiaraSpawn.CiaraSpawn;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VoidToSpawnListener implements Listener {
    private CiaraSpawn main;
    private Map<UUID, Integer> voidCountMap = new HashMap<>();
    private Map<UUID, Long> lastVoidTimeMap = new HashMap<>();
    private long voidCooldownMillis = 5 * 60 * 1000; // 5 minutes en millisecondes


    public VoidToSpawnListener(CiaraSpawn main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        String playerName = player.getName();


        // Vérifiez si le joueur est en Y inférieur à 20
        if (event.getTo() != null && event.getTo().getY() < 20) {
            // Vérifiez si le joueur est en cooldown pour éviter le spam
            long currentTime = System.currentTimeMillis();
            long lastVoidTime = lastVoidTimeMap.getOrDefault(playerUUID, 0L);

            if (currentTime - lastVoidTime >= voidCooldownMillis) {
                // Le joueur n'est pas en cooldown, réinitialisez le compteur
                voidCountMap.put(playerUUID, 1);
                lastVoidTimeMap.put(playerUUID, currentTime);
            } else {
                // Le joueur est en cooldown, incrémente le compteur
                int voidCount = voidCountMap.getOrDefault(playerUUID, 0) + 1;
                voidCountMap.put(playerUUID, voidCount);

                // Envoyez un message approprié en fonction du compteur
                if (voidCount == 2) {
                    player.sendMessage(ChatColor.GRAY + "Vous ne pouvez pas vous suicider.");
                } else if (voidCount == 3) {
                    player.sendMessage(ChatColor.GRAY + "Le Soleil brille, les oiseaux chantent, vous jouez sur CiaraCube. La vie est belle.");
                }
                // Vous pouvez continuer à ajouter des cas pour plus de messages si nécessaire
            }

            FileConfiguration config = main.getConfig();
            double x = config.getDouble("spawn-coordinates.x");
            double y = config.getDouble("spawn-coordinates.y") + 50;
            double z = config.getDouble("spawn-coordinates.z");
            float yaw = (float) config.getDouble("spawn-coordinates.yaw");
            float pitch = (float) config.getDouble("spawn-coordinates.pitch");

            Location newLocation = new Location(player.getWorld(), x, y, z, yaw, pitch);
            player.teleport(newLocation);
            player.playSound(newLocation, Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.0f);

        }
    }

}
