package com.mael.Clara;

import com.mael.Clara.commands.*;
import com.mael.Clara.events.*;
import com.mael.Clara.menus.GameSelectionMenu;
import com.mael.Clara.menus.ProfileMenu;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private FileConfiguration config; // Déclarer l'objet FileConfiguration
    private static Main instance;

    @Override
    public void onEnable() {

        //Enabling
        instance = this;
        System.out.println("[Clara] Enabling");

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
        this.config = this.getConfig();

        Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);

        // ALways day
        TimeManager timeManager = new TimeManager(this, getServer().getWorlds().get(0));
        timeManager.start();


        }

    @Override
    public void onDisable() {
        System.out.println("[Clara] Disabling");
    }

    public static Main getInstance() {
        return instance;
    }
}
