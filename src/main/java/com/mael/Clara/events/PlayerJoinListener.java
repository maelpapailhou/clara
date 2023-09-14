package com.mael.Clara.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.getPlayer().sendMessage(ChatColor.GREEN + "Vous êtes connecté(e).");
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        e.setJoinMessage(null);

    }

}
