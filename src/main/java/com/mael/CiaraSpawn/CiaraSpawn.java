package com.mael.Clara;

import com.mael.Clara.commands.*;
import com.mael.Clara.events.*;
import com.mael.Clara.managers.BossBarManager;
import com.mael.Clara.managers.TimeManager;
import com.mael.Clara.menus.GameSelectionMenu;
import com.mael.Clara.menus.ProfileMenu;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class CiaraSpawn extends JavaPlugin implements Listener {
    private FileConfiguration config; // Déclarer l'objet FileConfiguration
    private static CiaraSpawn instance;

    @Override
    public void onEnable() {

        //Enabling
        instance = this;
        System.out.println("[CiaraSpawn] Enabling");

        // Commands
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("t").setExecutor(new TimeCommand());
        getCommand("pluginlist").setExecutor(new PluginListCommand());
        getCommand("profile").setExecutor(new ProfileMenu());
        getCommand("clara").setExecutor(new SudoCommand());
        getCommand("gameselection").setExecutor(new GameSelectionMenu());

        // Listeners
        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new BlockInteractionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSpawnLocationListener(this), this);
        getServer().getPluginManager().registerEvents(new FullHealthListener(), this);
        getServer().getPluginManager().registerEvents(new ArrowHitListener(), this);
        getServer().getPluginManager().registerEvents(new FullFoodListener(), this);
        getServer().getPluginManager().registerEvents(new OnPingListener(), this);
        getServer().getPluginManager().registerEvents(new VoidToSpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new ItemJoinListener(), this);
        getServer().getPluginManager().registerEvents(new ItemSpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryProtectionListener(), this);

        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        this.saveDefaultConfig(); // Copier le fichier de configuration par défaut s'il n'existe pas encore

        Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);

        // ALways day
        TimeManager timeManager = new TimeManager(this, getServer().getWorlds().get(0));
        timeManager.start();

        // Boss Bar
        BossBarManager bossBarManager = new BossBarManager(this);
        bossBarManager.startUpdating();
    }

    @Override
    public void onDisable() {
        System.out.println("[CiaraSpawn] Disabling");
    }

    public static CiaraSpawn getInstance() {
        return instance;
    }
}
