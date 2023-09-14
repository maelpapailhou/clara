package com.mael.Clara.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
       if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
           Location spawnLocation = new Location(p.getWorld(), -497, 119, 9);

           p.teleport(spawnLocation);

       }

        return false;
    }
}
