package com.mael.CiaraSpawn.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class WeatherManager {
        public static void setClearWeather() {
            for (World world : Bukkit.getWorlds()) {
                world.setStorm(false);  // Désactiver la pluie
                world.setThundering(false);  // Désactiver l'orage
                world.setWeatherDuration(Integer.MAX_VALUE);  // Temps ensoleillé indéfini
            }
        }
    }

