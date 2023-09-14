package com.mael.Clara.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandArgs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("hello")) {
                    ((Player) commandSender).sendMessage("yop");

                }

            }

        }

        return false;
    }
}
