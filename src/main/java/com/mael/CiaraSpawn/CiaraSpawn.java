package com.mael.CiaraSpawn;

import com.mael.CiaraSpawn.menus.GameSelectionMenu;
import com.mael.CiaraSpawn.menus.ProfileMenu;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.mael.CiaraSpawn.commands.*;
import com.mael.CiaraSpawn.events.*;
import com.mael.CiaraSpawn.managers.*;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public final class CiaraSpawn extends JavaPlugin implements Listener {
    private FileConfiguration config;
    private static CiaraSpawn instance;
    private BossBarManager bossBarManager;

    @Override
    public void onEnable() {


        instance = this;
        System.out.println("[CiaraSpawn] Enabling");

        WeatherManager.setClearWeather();

        // Commands
        getCommand("help").setExecutor(new HelpCommand(this.getConfig()));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("t").setExecutor(new TimeCommand());
        getCommand("pluginlist").setExecutor(new PluginListCommand());
        getCommand("profile").setExecutor(new ProfileMenu());
        getCommand("clara").setExecutor(new SudoCommand());
        getCommand("gameselection").setExecutor(new GameSelectionMenu());

        // Listeners
        registerEvent(new PlayerJoinListener());
        registerEvent(new PlayerDropItemListener());
        registerEvent(new BlockInteractionListener());
        registerEvent(new PlayerSpawnLocationListener(this));
        registerEvent(new FullHealthListener());
        registerEvent(new ArrowHitListener());
        registerEvent(new FullFoodListener());
        registerEvent(new OnPingListener());
        registerEvent(new VoidToSpawnListener(this));
        registerEvent(new PlayerChatListener());
        registerEvent(new ItemSpawnListener(this));
        registerEvent(new InventoryProtectionListener());
        registerEvent(new ItemJoinListener(this));

        // Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Set default game mode
        Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);

        // Always day
        TimeManager timeManager = new TimeManager(this, getServer().getWorlds().get(0));
        timeManager.start();

        Player player = Bukkit.getPlayer("NomDuJoueur"); // Remplacez par le nom d'un joueur
        if (player != null) {
            SubtitleManager.sendSubtitle(player, "&aBienvenue sur le serveur !");
        }

        // Enregistrer les événements
        getServer().getPluginManager().registerEvents(this, this);

        //Bossbar
        bossBarManager = new BossBarManager(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        bossBarManager.addPlayer(e.getPlayer());
    }

    @Override
    public void onDisable() {
        System.out.println("[CiaraSpawn] Disabling");

    }

    public static CiaraSpawn getInstance() {
        return instance;
    }

    private void registerEvent(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

}
