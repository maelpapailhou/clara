package com.mael.Clara.events;

import com.mael.Clara.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SetTimeListener implements Listener {

    private final Main main;
    private final World world;

    public SetTimeListener(Main main, World world) {
        this.main = main;
        this.world = world;
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                world.setTime(6000);
            }
        }.runTaskTimer(main, 0L, 20L);
    }


}
