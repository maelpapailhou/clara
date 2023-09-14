package com.mael.Clara;

import com.mael.Clara.commands.*;
import com.mael.Clara.events.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[Clara] Enabled");
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::setDaytime, 0L, 20L); // Répéter toutes les 20 ticks (1 seconde)

        // Commands
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("hello").setExecutor(new CommandArgs());
        getCommand("console").setExecutor(new CommandConsole());
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("t").setExecutor(new TimeCommand());

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new ChatHoverListener(), this);
        getServer().getPluginManager().registerEvents(new BlockInteractionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSpawnLocationListener(), this);
        getServer().getPluginManager().registerEvents(new FullHealthListener(), this);
        getServer().getPluginManager().registerEvents(new ArrowHitListener(), this);
        getServer().getPluginManager().registerEvents(new FullFoodListener(), this);
        getServer().getPluginManager().registerEvents(new OnPingListener(), this);
        getServer().getPluginManager().registerEvents(new VoidToSpawnListener(this), this);

        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);

        TimeManager timeManager = new TimeManager(this, getServer().getWorlds().get(0));

        // Démarrez la gestion du temps
        timeManager.start();


    }
    private void setDaytime() {
        // Obtenez toutes les mondes du serveur et réglez l'heure à jour pour chacun d'eux
        for (World world : Bukkit.getWorlds()) {
            world.setTime(6000); // Réglez l'heure sur le jour (0)
            world.setStorm(false); // Mettez la météo sur clair
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        Egg egg = event.getEgg();
        TNTPrimed tnt = (TNTPrimed) egg.getWorld().spawnEntity(egg.getLocation(), EntityType.PRIMED_TNT);
        tnt.setFuseTicks(0); // Réglez la minuterie de l'explosion en 20 ticks (1 seconde)

        // Réglez la puissance (yield) de l'explosion à un minimum
        tnt.setYield(0.0F);

        event.setHatching(false); // Annulez le lancement de l'œuf

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // Annulez les dégâts pour garantir que le joueur a toujours une santé maximale
            if (player.getHealth() - event.getFinalDamage() <= 0) {
                player.setHealth(20);
                event.setCancelled(true);
            }
        }
    }

}