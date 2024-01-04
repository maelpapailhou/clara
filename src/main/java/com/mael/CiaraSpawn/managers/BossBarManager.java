package com.mael.CiaraSpawn.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class BossBarManager {

    private BossBar bossBar;
    private List<BarMessage> barMessages;
    private int currentIndex;

    public BossBarManager(JavaPlugin plugin) {
        this.barMessages = new ArrayList<>();
        this.currentIndex = 0;

        // Ajoutez autant de messages que vous le souhaitez avec différentes configurations
        barMessages.add(new BarMessage(ChatColor.BLUE + "Message 1", BarColor.GREEN, 500));
        barMessages.add(new BarMessage(ChatColor.RED + "Message 2", BarColor.RED, 500));

        bossBar = createBossBar(barMessages.get(0));
        bossBar.setProgress(1.0);
        scheduleNextMessage(plugin);
    }

    public void addPlayer(Player player) {
        bossBar.addPlayer(player);
    }

    public void removePlayer(Player player) {
        bossBar.removePlayer(player);
    }

    private BossBar createBossBar(BarMessage barMessage) {
        return Bukkit.createBossBar(
                barMessage.getMessage(),
                barMessage.getBarColor(),
                BarStyle.SOLID);
    }

    private void scheduleNextMessage(JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                currentIndex = (currentIndex + 1) % barMessages.size();
                updateBossBar(barMessages.get(currentIndex));
            }
        }.runTaskTimer(plugin, 0L, 100L); // La tâche se répète toutes les 20 ticks (1 seconde)
    }

    private void updateBossBar(BarMessage barMessage) {
        bossBar.setTitle(barMessage.getMessage());
        bossBar.setColor(barMessage.getBarColor());
        bossBar.setProgress(1.0);
    }

    // Classe interne pour représenter un message de la barre de boss
    private static class BarMessage {
        private String message;
        private BarColor barColor;
        private int duration; // en ticks

        public BarMessage(String message, BarColor barColor, int duration) {
            this.message = message;
            this.barColor = barColor;
            this.duration = duration;
        }

        public String getMessage() {
            return message;
        }

        public BarColor getBarColor() {
            return barColor;
        }

        public int getDuration() {
            return duration;
        }
    }
}
