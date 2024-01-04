package com.mael.CiaraSpawn.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SubtitleManager {

    public static void sendSubtitle(Player player, String subtitleText) {
        // Clear existing titles
        clearTitles(player);

        // Send subtitle
        player.sendTitle("", ChatColor.translateAlternateColorCodes('&', subtitleText), 10, 40, 10);
    }

    public static void clearTitles(Player player) {
        // Clear existing titles
        player.resetTitle();
    }

    public static void broadcastSubtitle(String subtitleText) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            sendSubtitle(onlinePlayer, subtitleText);
        }
    }

}
