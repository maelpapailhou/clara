package com.mael.Clara.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHoverListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();

        // Parcourir tous les joueurs en ligne
        for (Player player : Bukkit.getOnlinePlayers()) {
            String playerName = player.getName();
            String hoverText = ChatColor.YELLOW + "Cliquez pour voir le profil de " + playerName;

            // Remplacer le nom du joueur par le texte hoverable
            message = message.replaceAll("(?i)" + playerName, ChatColor.UNDERLINE + playerName + ChatColor.RESET + ChatColor.GRAY + " (Cliquez pour voir le profil)");

            // Envoyer le message avec le texte hoverable
        }

        e.setMessage(message);
    }
}
