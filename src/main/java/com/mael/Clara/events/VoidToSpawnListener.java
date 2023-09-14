package com.mael.Clara.events;

import com.mael.Clara.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidToSpawnListener implements Listener {
    private Main main;

    public VoidToSpawnListener(Main main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Vérifiez si le joueur est en Y inférieur à 10
        Player player = event.getPlayer();

        if (event.getPlayer().getLocation().getY() < 20) {
            // Téléportez le joueur aux coordonnées spécifiques (x: 100, y: 100, z: 100)

            FileConfiguration config = main.getConfig();
            double x = config.getDouble("spawn-coordinates.x");
            double y = config.getDouble("spawn-coordinates.y") + 50;
            double z = config.getDouble("spawn-coordinates.z");
            float yaw = (float) config.getDouble("spawn-coordinates.yaw");
            float pitch = (float) config.getDouble("spawn-coordinates.pitch");

            player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
        }
    }

}
