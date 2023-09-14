package com.mael.Clara.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class PlayerSpawnLocationListener implements Listener {

    @EventHandler
    public void spawnLocation(PlayerSpawnLocationEvent e) {
        Player player = e.getPlayer();

        Location spawnLocation = new Location(player.getWorld(), -497, 119, 9);
        e.setSpawnLocation(spawnLocation);

    }
}
