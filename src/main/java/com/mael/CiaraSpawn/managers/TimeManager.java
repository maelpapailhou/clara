package com.mael.CiaraSpawn.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeManager implements Runnable {

    private final World world;
    private final JavaPlugin plugin;

    public TimeManager(JavaPlugin plugin, World world) {
        this.plugin = plugin;
        this.world = world;
    }

    @Override
    public void run() {
        // Définissez le temps du monde sur "midi" (6000)
        world.setTime(6000);
    }

    public void start() {
        // Exécutez la tâche de gestion du temps toutes les 20 ticks (1 seconde)
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    }
}
