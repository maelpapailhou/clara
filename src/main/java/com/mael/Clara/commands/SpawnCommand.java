package com.mael.Clara.commands;

import com.mael.Clara.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnCommand implements CommandExecutor {

    private Main main;

    public SpawnCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
       if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
           FileConfiguration config = main.getConfig();
           double x = config.getDouble("spawn-coordinates.x");
           double y = config.getDouble("spawn-coordinates.y");
           double z = config.getDouble("spawn-coordinates.z");
           float yaw = (float) config.getDouble("spawn-coordinates.yaw");
           float pitch = (float) config.getDouble("spawn-coordinates.pitch");



           // Créez une nouvelle location avec les coordonnées et la direction
           Location spawnLocation = new Location(p.getWorld(), x, y, z, yaw, pitch);

           p.teleport(spawnLocation);

       }

        return false;
    }
}
