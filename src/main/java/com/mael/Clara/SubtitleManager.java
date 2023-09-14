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

public class SubtitleManager implements Listener {

    private final JavaPlugin plugin;
    private final List<String> subtitleMessages;
    private int currentIndex = 0;

    public SubtitleManager(JavaPlugin plugin) {
        this.plugin = plugin;

        // Ajoutez vos messages de sous-titre dans la liste
        subtitleMessages = new ArrayList<>();
        subtitleMessages.add(ChatColor.RED + "Message 1 en rouge");
        subtitleMessages.add(ChatColor.GREEN + "Message 2 en vert");

        // Planifiez une tâche pour afficher les sous-titres avec des transitions
        new BukkitRunnable() {
            @Override
            public void run() {
                showNextSubtitle();
            }
        }.runTaskTimer(plugin, 0, 60); // Mettez à jour toutes les 3 secondes (60 ticks)
    }

    private void showNextSubtitle() {
        if (currentIndex < subtitleMessages.size()) {
            String message = subtitleMessages.get(currentIndex);
            sendSubtitleToAllPlayers(message);
            currentIndex++;
        } else {
            // Si tous les messages ont été affichés, réinitialisez l'indice
            currentIndex = 0;
        }
    }

    private void sendSubtitleToAllPlayers(String message) {
        TextComponent textComponent = new TextComponent(TextComponent.fromLegacyText(message));

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
        }
    }
}
