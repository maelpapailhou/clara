package com.mael.CiaraSpawn.commands;

import com.mael.CiaraSpawn.CiaraSpawn;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private CiaraSpawn main;

    public SpawnCommand(CiaraSpawn main) {
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

           Location spawnLocation = new Location(p.getWorld(), x, y, z, yaw, pitch);

           p.teleport(spawnLocation);

       }

        return false;

    }

}
