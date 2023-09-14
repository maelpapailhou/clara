package com.mael.Clara.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandConsole implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            ((Player) commandSender).sendMessage("C'est que pour la console");

        } else {

            System.out.println("Console");
        }



        return false;
    }
}
