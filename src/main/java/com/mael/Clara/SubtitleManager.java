package com.mael.Clara;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class SubtitleManager {
        public static void sendSubtitle(Player player, String message, int durationTicks) {
            player.sendTitle("", ChatColor.translateAlternateColorCodes('&', message), 5, durationTicks, 5);
        }

        public static void sendSubtitleToAll(String message, int durationTicks) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendSubtitle(player, message, durationTicks);
            }
        }
    }

