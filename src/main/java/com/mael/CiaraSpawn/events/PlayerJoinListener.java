package com.mael.CiaraSpawn.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerJoinListener implements Listener {
    private Map<UUID, Long> lastLoginMap = new HashMap<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        e.getPlayer().sendMessage(ChatColor.GREEN + "X êtes connecté(e).");
        e.setJoinMessage(null);

        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();

            UUID playerUUID = e.getPlayer().getUniqueId();
            String playerName = e.getPlayer().getName();
            long lastLoginTime = lastLoginMap.getOrDefault(playerUUID, 0L);
            long currentTime = System.currentTimeMillis();
            long timeDifference = currentTime - lastLoginTime;
            lastLoginMap.put(playerUUID, currentTime);
            String welcomeMessage;
            if (timeDifference >= 10 * 24 * 60 * 60 * 1000) {
                welcomeMessage = "C, " + playerName + " !";
            } else {
                welcomeMessage = "CC, " + playerName + " !";
            }

            e.getPlayer().sendMessage(welcomeMessage);
            e.getPlayer().sendMessage("Dernière connexion : " + dateFormat.format(new Date(lastLoginTime)));
        }
    }

